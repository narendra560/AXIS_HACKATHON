import React from "react";
import { FaSearch } from 'react-icons/fa';
import 'bootstrap/dist/css/bootstrap.min.css';
import './ListJob.css'
import axios from 'axios';



class ListJobComp extends React.Component {

    constructor() {
        super()
        this.state = {'jobs':[{}]}
        this.fetch = this.fetch.bind(this);
    }
    componentDidMount() {
       setInterval(this.fetch(),1000)
    }
    fetch() {
        try {            
            axios.get('http://10.217.60.74:3011/job/getAllJobsPostedByMe', {
              headers: {'userId':'101','Access-Control-Allow-Origin':'true'}}
              ).then(response => {
                console.log(response.data)
                this.setState({'jobs':response.data})
            })
          } catch (error) {
            console.error('Error fetching data:', error);
          }
    }
    formatDate(dateString) {
        const date = new Date(dateString);
        const day = date.getDate().toString().padStart(2, '0');
        const month = (date.getMonth() + 1).toString().padStart(2, '0');
        const year = date.getFullYear();
      
        return `${month}-${day}-${year}`;
      }
    createTable = () => {
        // create table using state variable jobs and return it
        var table = []
        var jobs = this.state.jobs
        for(let i=0;i<jobs.length;i++){
            table.push(<tr><td>{jobs[i].jobId}</td><td>{jobs[i].jobTitle}</td><td>{jobs[i].yearsOfExperience}+</td><td>{this.formatDate(jobs[i].jobPostedOn)}</td><td>{jobs[i].jobStatus}</td></tr>)
        }
        return table
    }
    render() {
        return (
            <React.Fragment>
                <div className="header">
                    <h1 style={{ color: '#0c095e' }}>Jobs Posted By You</h1>
                    <div className="search-bar">
                        <input type="text" placeholder="Search by Skills" />
                        <button type="submit">
                        <FaSearch />
                        </button>
                    </div>
                    <p className="new_job">POST A NEW JOB</p>
                </div>
                <table>
                    <thead>
                        <tr>
                        <th>Job Id</th>
                        <th>Title</th>
                        <th>YOE</th>
                        <th>Posted On</th>
                        <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.createTable()}
                    </tbody>
                </table>
            </React.Fragment>
        )
    }

}
export default ListJobComp;