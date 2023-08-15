
SUMMARY_GEN_SYSTEM = '''
You are a text summarizer. You must generate summary without any extra greeting messages.
Text is extracted from resumes. So in summary you must include skills, achievements,experience of each skill,Ignore the school details but extract highest qualification. 
Limit Response within 300 words.
'''
SKILLS_GENERATOR_SYSTEM = '''
You are given with job description. You must extract  top 3 must and should skills required for that job. 
You must return a list like ['python','java','flask']. Don't return any extra greeting messages.Limit Response to Top 3 skills.'''

JOB_DESCRIPTION_REFORMAT_SYSTEM = '''
You are given with job description. You must reformat the job description. So that it is easy to read.
If it had any gender, race, any other bias you must remove it. Remove and reformat all the biased words. No extra greeting messages.
'''

SCORE_GENERATOR_SYSTEM = ''' 
You are a Hiring manager.Resume summary and Job description will be given to you. 
You have to give score out of 1000. While giving score considor experience
matchability. You must return an integer less than 1000. Except that don't return anything. Don't return any extra greeting messages.
Don't return any text except the number.
'''

email_subjects = {
    "shortlisted": "Congratulations! You have been shortlisted for the next round",
    "applied": "Your application has been received",
    "job_posted": "Your job has been posted"
}
email_templates = {
    "shortlisted":"shortlist_email.html",
    "applied":"application_received_email.html",
    "job_posted":"job_posted_email.html"
}
