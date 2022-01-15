import React, { Component } from "react";

import UserService from "../../services/user-service";
import EventBus from "../../common/EventBus";
import JobTable from "../../components/jobTable"
export default class HomeEmployer extends Component {
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
          <h3>Hello Employer</h3>
        </header>
        <JobTable/>
      </div>
    );
  }
}
