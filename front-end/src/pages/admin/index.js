import React, { Component } from "react";

import UserService from "../../services/user-service";
import EventBus from "../../common/EventBus";
import UserTable from "../../components/dataTableUser";
export default class HomeAdmin extends Component {
  constructor(props) {
    super(props);

    this.state = {
      content: ""
    };
  }

  componentDidMount() {
    // UserService.getAdminBoard().then(
    //   response => {
    //     this.setState({
    //       content: response.data
    //     });
    //   },
    //   error => {
    //     this.setState({
    //       content:
    //         (error.response &&
    //           error.response.data &&
    //           error.response.data.message) ||
    //         error.message ||
    //         error.toString()
    //     });

    //     if (error.response && error.response.status === 401) {
    //       EventBus.dispatch("logout");
    //     }
    //   }
    // );
  }

  render() {
    return (
      <div className="container" style={{ width: "100%"}}>
        <header className="jumbotron">
          Hello Admin
        </header>
        <UserTable/>
      </div>
    );
  }
}
