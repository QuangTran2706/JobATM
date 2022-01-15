import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "signin", {
        username,
        password,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(username, email, password, role) {
    var payload = {
      username: username,
      email: email,
      password: password,
      role: ["employee"],
    };
    console.log(role);
    if (role === "admin") {
      payload = {
        username: username,
        email: email,
        password: password,
        role: ["admin"],
      };
    }

    if (role === "employer") {
      payload = {
        username: username,
        email: email,
        password: password,
        role: ["employer"],
      };
    }
    console.log(payload);
    return axios.post(API_URL + "signup", JSON.stringify(payload), {
      headers: {
        // Overwrite Axios's automatically set Content-Type
        "Content-Type": "application/json",
      },
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem("user"));
  }
}

export default new AuthService();
