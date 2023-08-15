from flask import Flask, request, jsonify
import openai
import requests
import PyPDF2
import constants
from flask_cors import CORS
import ast
import smtplib
from email.mime.multipart import MIMEMultipart
from email.mime.text import MIMEText
from jinja2 import Environment, FileSystemLoader
import os



app = Flask(__name__)
CORS(app)

openai.api_key = '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'

def call_gpt(message):
    response = openai.ChatCompletion.create(
        model="gpt-3.5-turbo",
        messages= message
        )
    return response.choices[0].message.content
    
def extract_text(pdf_file_path):
    text = ""
    with open(pdf_file_path, "rb") as file:
        pdf_reader = PyPDF2.PdfReader(file)
        for page_num in range(len(pdf_reader.pages)):
            page = pdf_reader.pages[page_num]
            text += page.extract_text()
    return text

def generate_resume_summary(resume_path):
    resume_path = request.args.get('resume_path')
    extracted_text = extract_text(resume_path)
    messages=[
                {"role": "system", "content": constants.SUMMARY_GEN_SYSTEM},
                {"role": "user", "content":f"Generate summary for the following text: '{extracted_text}'"}
            ]
    return call_gpt(messages)

@app.route('/generate/job/skills', methods=['GET'])
def generate_skills():
    job_description = request.args.get('job_description')
    messages=[
                {"role": "system", "content": constants.SKILLS_GENERATOR_SYSTEM},
                {"role": "user", "content":f"Generate skills for the following job description: '{job_description}'"}
            ]
    try:
        skills = call_gpt(messages)
        skills = ast.literal_eval(skills)
        print(skills)
    except Exception as e:
        print(e)
        skills = []
    return jsonify(skills)

@app.route('/reformat/job', methods=['GET'])
def reformat_description():
    job_description = request.args.get('job_description')
    messages = [
        {"role": "system", "content": constants.JOB_DESCRIPTION_REFORMAT_SYSTEM},
        {"role": "user", "content":f"Reformat the following job description: '{job_description}'"}
    ]
    return call_gpt(messages)

@app.route('/generate/score', methods=['GET'])
def generate_score():
    resume_path = request.args.get('resume_path')
    job_description = request.args.get('job_description')
    application_id = request.args.get('application_id')
    
    summary = generate_resume_summary(resume_path)
    print("summary extracted")
    messages = [
        {"role": "system", "content": constants.SCORE_GENERATOR_SYSTEM},
        {"role": "user", "content":f"Generate score for the following resume: '{summary}' and job description: '{job_description}'"}
    ]
    print("calling gpt")
    response = call_gpt(messages)
    score = int(response)
    update_score(application_id, summary,score)
    return "success"
    

def update_score(application_id, summary,score,remarks=""):
    # Write code to call PUT API to update score
    uri = f'http://localhost:3011/job/application/update/{application_id}?summary={summary}&score={score}&remarks={remarks}'
    requests.put(uri)
    
@app.route('/send/emails', methods=['POST'])
def send_email():
    sender_email = 'axishackathon97@gmail.com'
    sender_password = 'jujottcjpefsaxiq'
    data = request.get_json()
    recipient_email = data['email']
    event_id = data['event_id']
    subject = constants.email_subjects[data['event_id']]
    current_directory = os.path.dirname(os.path.abspath(__file__))
    # Construct the path to the 'templates' folder relative to the script's directory
    templates_path = os.path.join(current_directory, 'templates')
    env = Environment(loader=FileSystemLoader(templates_path))
    template = env.get_template(constants.email_templates[event_id])
    # Load the HTML template

    # Render the template with dynamic data
    html_content = template.render(subject=subject, data=data)

    # Create a MIME message
    msg = MIMEMultipart('alternative')
    msg['From'] = sender_email
    msg['To'] = recipient_email
    msg['Subject'] = subject

    # Attach the HTML content to the email
    msg.attach(MIMEText(html_content, 'html'))

    # Connect to the SMTP server and send the email
    with smtplib.SMTP('smtp.gmail.com', 587) as server:
        server.starttls()
        server.login(sender_email, sender_password)
        server.sendmail(sender_email, recipient_email, msg.as_string())

    print("Email sent successfully!")
    return "success"

if __name__ == '__main__':
    app.run()
    