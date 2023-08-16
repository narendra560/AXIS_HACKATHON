import React from "react";
import './jobs.css'
import axios from "axios";

class JobsComp extends React.Component {

    constructor(){
        super()
        this.state = {'jobs':[{}],'clickedOn':{}}
    }
    componentDidMount() {
        setInterval(this.fetch(),1000)
     }
    fetch() {
        try {            
            axios.get('http://10.217.60.74:3011/all/jobs', {
              headers: {'userId':'101','Access-Control-Allow-Origin':'true'}}
              ).then(response => {
                console.log(response.data)
                this.setState({'jobs':response.data,'clickedOn':response.data[0]})
            })
          } catch (error) {
            console.error('Error fetching data:', error);
          }
    }

    updateDescription = (id) => {
        console.log(id)
        var job = this.state.jobs[id]
        console.log(job)
        this.setState({'clickedOn':job})
    }


    render() {
        return (
    <React.Fragment>
        <div className="jobs_search-bar">
            <input type="text" placeholder="&#128270; Search by Skills" className="search-input" />
            <input type="text" placeholder="&#127760; Search by location" className="search-input" />
            <button className="go-button">Go</button>
        </div>
        <main className="main-container">
        <div className="container-wrapper">
            <div className="container">
            {this.state.jobs.map((element,index)=>(
                <div className="container-item" key={element.jobId} onClick={()=>this.updateDescription(index)}>
                <h3 style={{ fontFamily: 'Poppins', fontWeight: 600 }}>{element.jobTitle}</h3>
                <i className="fas fa-map-marker-alt"></i><span style={{ fontFamily: 'Raleway', color: '#888', fontSize: '15px' }}>{element.jobLocation}</span>
                <h4 style={{ fontFamily: 'Work Sans Extra Light', fontWeight: 'normal', color: 'black' }}>{element.jobCategory}</h4>
                </div>
            ))}
            </div>
        </div>
        <div className="right-section">
            <div className="java-developer-heading">
            <h2>{this.state.clickedOn.jobTitle}</h2>
            <i className="fas fa-map-marker-alt"></i><span style={{ fontFamily: 'Raleway', color: '#888', fontSize: '15px' }}>{this.state.clickedOn.jobLocation}</span><br /><br />
            <button className="apply-button" style={{ backgroundColor: 'rgb(8, 8, 110)', fontSize: '17px', color: 'white', width: '10%', padding: '1%', fontFamily: 'Raleway' }}
                onMouseOver={(e) => { e.target.style.backgroundColor = 'white'; e.target.style.color = 'darkblue'; e.target.style.borderColor = 'darkblue'; }}
                onMouseOut={(e) => { e.target.style.backgroundColor = 'darkblue'; e.target.style.color = 'white'; e.target.style.borderColor = 'darkblue'; }}>Apply</button>
            </div>
            <div className="horizontal-line"></div>
            <div className="job-description container">
            <h2>Job description</h2>
            <div className="job-description">
                {this.state.clickedOn.jobDescription}
            </div>
            </div>
        </div>
        </main>
    </React.Fragment>
        )
    }
}
export default JobsComp