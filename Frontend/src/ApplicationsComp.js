import React from "react";
import './index.css'
import axios from "axios";

class ApplicationsComp extends React.Component {

    constructor() {
        super();
        this.state = {'applications':[],'description':'','title':''}
    }

    componentDidMount() {
        this.fetchApplications()
        this.getDescription()
    }

    fetchApplications = ()=> {
        try {            
            axios.get('http://10.217.60.74:3011/job/getAllApplicationsForJob/'+this.props.job_id, {
              headers: {'userId':'101'}}
              ).then(response => {
                console.log(response.data)
                this.setState({'applications':response.data})
            })
          } catch (error) {
            console.error('Error fetching data:', error);
          }
    }

    getDescription = () => {
        try {            
            axios.get('http://10.217.60.74:3011/job/'+this.props.job_id, {
              headers: {'userId':'101'}}
              ).then(response => {
                console.log(response.data.jobDescription)
                this.setState({'description':response.data.jobDescription,'title':response.data.jobTitle});
            })
          } catch (error) {
            console.error('Error fetching data:', error);
          }
    }

    createTable = () => {
        // create table using state variable applications and return it
        var table = []
        var applications = this.state.applications
        for(let i=0;i<applications.length;i++){
            table.push(<tr key={applications[i].applicationId}><td>{applications[i].userId}</td><td><a href={"file://"+applications[i].resumePath} target="_blank" rel="noopener noreferrer">view</a></td><td>{applications[i].resumeScore}</td><td>{applications[i].resumeRemarks}</td><td>{applications[i].applicationStatus}</td></tr>)
        }
        return table
    }

    render() {
        return (
            <React.Fragment>
                <div className="job_meta">
                    <h1>{this.state.title}</h1>
                    <h6>JOB ID: {this.props.job_id}</h6>
                </div>
                <div className="horizantal">
                    <div className="division">
                        <h1>Description</h1>
                        <p>
                            {this.state.description}
                        </p>
                    </div>
                    <div className="division">
                        <h2>Applications</h2>
                        <table className="applications_table">
                            <thead>
                                <tr>
                                    <th>User Id</th>
                                    <th>Resume</th>
                                    <th>Score</th>
                                    <th>Remarks</th>
                                    <th>Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.createTable()}
                            </tbody>
                        </table>
                    </div>
                </div>
            </React.Fragment>
        )
    }
}

export default ApplicationsComp;