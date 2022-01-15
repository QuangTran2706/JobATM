import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/user/';

class UserService {
  getAllUser() {
    return axios.get(API_URL + 'getAllUsers');
  }

  deleteUser(id) {
    return axios.delete(API_URL + 'delete/' + id);
  }

  update(user) {
    var data = JSON.stringify(user);
    console.log(data);
    return axios.post(API_URL + "update", data, {
      headers: {
        // Overwrite Axios's automatically set Content-Type
        'Content-Type': 'application/json'
      }
    });
  }

  add(user) {
    var data = JSON.stringify(user);
    console.log(data);
    return axios.post(API_URL + "update", data, {
      headers: {
        // Overwrite Axios's automatically set Content-Type
        'Content-Type': 'application/json'
      }
    });
  }

  getQrCode(link) {
    return axios.post(API_URL + 'qr-code', {
      "urlLink": link
    });
  }

  applyJob(jobId) {
    var user = JSON.parse(localStorage.getItem("user"))
    return axios.post(API_URL + "applyjob/" + user.id +"/" + jobId)
  }

  getUserBoard() {
    return axios.get(API_URL + 'user', { headers: authHeader() });
  }

  getModeratorBoard() {
    return axios.get(API_URL + 'mod', { headers: authHeader() });
  }

  getAdminBoard() {
    return axios.get(API_URL + 'admin', { headers: authHeader() });
  }
}

export default new UserService();
