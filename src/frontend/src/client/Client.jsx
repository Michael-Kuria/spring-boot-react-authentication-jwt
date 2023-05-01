import axios from "axios";

export const Client = {
  authenticate,
  getStudents,
};

const instance = axios.create({
  baseURL: "https://localhost:8080/",
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
