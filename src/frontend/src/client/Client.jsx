import axios from "axios";

export const Client = {
  authenticate,
  getStudents,
  auth,
};

const instance = axios.create({
  baseURL: "http://localhost:8080",
});

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
    headers: { Authorization: `Bearer ${user}` },
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
