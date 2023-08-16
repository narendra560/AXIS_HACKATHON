import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';
import './ListJob.css'


class NavBarComp extends React.Component {

    render() {
        return (
            <div className="topnav">
             <a className="active" href="/"><u>HOME</u></a>
             <a href="jobs">JOBS</a>
            </div>
        )
    }
}

export default NavBarComp;
