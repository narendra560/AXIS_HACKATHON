import React from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

class HelloComp extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        txtUsername: "Infosys",
        formErrors: {}
      };
      this.validate = event => {
        if (Object.keys(this.state.formErrors).length> 0) {
          event.preventDefault();
        }
      };
      this.getName = event => {
        this.setState({ txtUsername: event.target.value });
        var formErrorsCopy = this.state.formErrors;
        if (event.target.value.length < 5) {  
          formErrorsCopy.txtUsername = "Cannot be less than 5";
        } else {
          delete formErrorsCopy["txtUsername"];
        }
        this.setState({ formErrors: formErrorsCopy});
      };
    }
    render() {
      return (
        <form onSubmit={this.validate}>
          <input
            type="text"
            name="txtUsername"
            value={this.state.txtUsername}
            onChange={this.getName}
          />
          <div id="errorMsg">{this.state.formErrors.txtUsername}</div>
          <button type="submit" className="btn btn-primary">
            Login
          </button>
        </form>
      );
    }
  }
    
export default HelloComp;