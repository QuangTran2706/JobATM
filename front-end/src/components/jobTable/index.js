import React, { useState, useEffect } from "react";
import { forwardRef } from "react";
import Grid from "@material-ui/core/Grid";

import MaterialTable from "material-table";
import AddBox from "@material-ui/icons/AddBox";
import ArrowDownward from "@material-ui/icons/ArrowDownward";
import Check from "@material-ui/icons/Check";
import ChevronLeft from "@material-ui/icons/ChevronLeft";
import ChevronRight from "@material-ui/icons/ChevronRight";
import Clear from "@material-ui/icons/Clear";
import DeleteOutline from "@material-ui/icons/DeleteOutline";
import Edit from "@material-ui/icons/Edit";
import FilterList from "@material-ui/icons/FilterList";
import FirstPage from "@material-ui/icons/FirstPage";
import LastPage from "@material-ui/icons/LastPage";
import Remove from "@material-ui/icons/Remove";
import SaveAlt from "@material-ui/icons/SaveAlt";
import Search from "@material-ui/icons/Search";
import ViewColumn from "@material-ui/icons/ViewColumn";
import Alert from "@material-ui/lab/Alert";

import UserService from "../../services/user-service";
import AuthService from "../../services/auth-service";
import JobService from "../../services/job-service";

const tableIcons = {
  Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
  Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
  Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
  DetailPanel: forwardRef((props, ref) => (
    <ChevronRight {...props} ref={ref} />
  )),
  Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
  Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
  Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
  FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
  LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
  NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
  PreviousPage: forwardRef((props, ref) => (
    <ChevronLeft {...props} ref={ref} />
  )),
  ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
  Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
  SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
  ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
  ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />),
};

function isValidDate(dateString) {
  var regEx = /^\d{4}-\d{2}-\d{2}$/;
  if (!dateString.match(regEx)) return false; // Invalid format
  var d = new Date(dateString);
  var dNum = d.getTime();
  if (!dNum && dNum !== 0) return false; // NaN value, Invalid date
  return d.toISOString().slice(0, 10) === dateString;
}

function JobTable() {
  var columns = [
    { title: "id", field: "id", hidden: true },
    { title: "Job title", field: "jobTitle" },
    { title: "Company", field: "companyName" },
    { title: "Position", field: "position" },
    { title: "Category", field: "jobCategory" },
    { title: "Salary", field: "salary" },
    { title: "Date Posted", field: "datePosted" },
  ];
  const [data, setData] = useState([]); //table data
  const [imgSrc, setImgSrc] = useState([]); // Image src for QR code
  //for error handling
  const [iserror, setIserror] = useState(false);
  const [errorMessages, setErrorMessages] = useState([]);

  useEffect(() => {
    JobService.getAllJobs()
      .then((res) => {
        console.log(res.data);
        setData(res.data);
      })
      .catch((error) => {
        console.log("Error");
      });
  }, []);

  const handleRowUpdate = (newData, oldData, resolve) => {
    //validation
    let errorList = [];
    if (newData.jobTitle === "") {
      errorList.push("Please enter jobtitle");
    }
    if (newData.position === "") {
      errorList.push("Please enter position");
    }
    if (newData.jobCategory === "") {
      errorList.push("Please enter job category");
    }
    if (newData.salary === "") {
      errorList.push("Please enter salary");
    }
    if (
      newData.datePosted === "" ||
      isValidDate(newData.datePosted) === false
    ) {
      errorList.push("Please enter date with format yyyy-mm-dd");
    }

    if (errorList.length < 1) {
      JobService.updateJob(newData)
        .then((res) => {
          const dataUpdate = [...data];
          const index = oldData.tableData.id;
          dataUpdate[index] = newData;

          setData([...dataUpdate]);
          resolve();
          setIserror(false);
          setErrorMessages([]);
        })
        .catch((error) => {
          setErrorMessages(["Update failed! Server error"]);
          setIserror(true);
          resolve();
        });
    } else {
      setErrorMessages(errorList);
      setIserror(true);
      resolve();
    }
  };

  const handleRowAdd = (newData, resolve) => {
    //validation
    let errorList = [];
    if (newData.jobTitle === "") {
      errorList.push("Please enter jobtitle");
    }
    if (newData.position === "") {
      errorList.push("Please enter position");
    }
    if (newData.jobCategory === "") {
      errorList.push("Please enter job category");
    }
    if (newData.salary === "") {
      errorList.push("Please enter salary");
    }
    if (
      newData.datePosted === "" ||
      isValidDate(newData.datePosted) === false
    ) {
      errorList.push("Please enter date with format yyyy-mm-dd");
    }
    if (errorList.length < 1) {
      //no error
      JobService.updateJob(newData)
        .then((res) => {
          let dataToAdd = [...data];
          dataToAdd.push(newData);
          setData(dataToAdd);
          resolve();
          setErrorMessages([]);
          setIserror(false);
        })
        .catch((error) => {
          setErrorMessages(["Cannot add data. Server error!"]);
          setIserror(true);
          resolve();
        });
    } else {
      setErrorMessages(errorList);
      setIserror(true);
      resolve();
    }
  };

  const handleRowDelete = (oldData, resolve) => {
    JobService.deleteJob(oldData.id)
      .then((res) => {
        const dataDelete = [...data];
        const index = oldData.tableData.id;
        dataDelete.splice(index, 1);
        setData([...dataDelete]);
        resolve();
      })
      .catch((error) => {
        setErrorMessages(["Delete failed! Server error"]);
        setIserror(true);
        resolve();
      });
  };

  const handleGetQrCodeButton = () => {
    var currentUrl = window.location.href;
    UserService.getQrCode(currentUrl)
      .then((res) => {
        const url = window.URL.createObjectURL(new Blob([res.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'qrcode.png');
        document.body.appendChild(link);
        link.click();
        // Do whatever with the img
        //document.getElementById("img").setAttribute("src", img);
      })
      .catch((error) => {
        setErrorMessages(["get QR code failed! Server error"]);
      });
  };

  return (
    <div className="">
      <Grid container spacing={6}>
        <Grid item xs={1}></Grid>
        <Grid item xs={12}>
          <div>
            {iserror && (
              <Alert severity="error">
                {errorMessages.map((msg, i) => {
                  return <div key={i}>{msg}</div>;
                })}
              </Alert>
            )}
          </div>
          <MaterialTable
            title="Job management for employer"
            columns={columns}
            data={data}
            icons={tableIcons}
            editable={{
              onRowUpdate: (newData, oldData) =>
                new Promise((resolve) => {
                  handleRowUpdate(newData, oldData, resolve);
                }),
              onRowAdd: (newData) =>
                new Promise((resolve) => {
                  handleRowAdd(newData, resolve);
                }),
              onRowDelete: (oldData) =>
                new Promise((resolve) => {
                  handleRowDelete(oldData, resolve);
                }),
            }}
            detailPanel={(rowData) => {
              return (
                <div style={{ padding: "30px" }}>
                  <h4> Job description </h4>
                  <p>{rowData.description}</p>
                  <button onClick={handleGetQrCodeButton}>Get QR code</button>
                  <img src={{uri: imgSrc}} alt="" width="500px" />
                </div>
              );
            }}
          />
        </Grid>
        <Grid item xs={3}></Grid>
      </Grid>
    </div>
  );
}

export default JobTable;
