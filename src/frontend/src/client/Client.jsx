import axios from "axios";
import { parseJwt } from "../components/helpers/Utils";

export const Client = {
  authenticate,
  getStudents,
  auth,
};

const instance = axios.create({
  baseURL: "http://localhost:8080",
});

instance.interceptors.request.use(
  function (config) {
    if (config.headers.Authorization) {
      const token = config.headers.Authorization.split(" ")[1];
      const data = parseJwt(token);
      if (Date.now() > data.exp * 1000) {
        window.location.href = "/login";
      }
    }

    return config;
  },
  function (error) {
    return Promise.reject(error);
  }
);

function authenticate(email, password) {
  return instance.post(
    "/login",
    { email, password },
    {
      headers: { "Content-type": "application/json" },
    }
  );
}

function getStudents(user) {
  return instance.get("/api/students", {
    headers: { Authorization: `Bearer ${user.token}` },
  });
}

function auth(email, password) {
  return fetch("https://localhost:8080/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(email, password),
  });
}
