import axios from "axios";
import authHeader from "./auth-header";

const API_URL = "http://localhost:8080/job/";

class JobService {

  getAllJobs() {
    return axios.get(API_URL + "getAll");
  }

  updateJob(job) {
    var data = JSON.stringify(job);
    console.log(data);
    return axios.post(API_URL + "update", data, {
      headers: {
        // Overwrite Axios's automatically set Content-Type
        "Content-Type": "application/json",
      },
    });
  }

  deleteJob(id) {
    return axios.delete(API_URL + "delete/" + id);
  }

  getDateBetween(startDate, endDate) {
      return axios.get(API_URL + "getBetween?startDate="+startDate+"&endDate="+endDate);
  }

  getJobByDate(date) {
      return axios.get(API_URL + "getByDay?date=" + date);
  }

}
export default new JobService();
