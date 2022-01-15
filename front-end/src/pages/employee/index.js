import React, { Component } from "react";

import UserService from "../../services/user-service";
import EventBus from "../../common/EventBus";
import JobView from "../../components/jobView";
export default class HomeEmployee extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    
  }

  render() {
    return (
      <div className="container">
        <header className="jumbotron">
            Hello employee
        </header>
        <JobView/>
      </div>
    );
  }
}
