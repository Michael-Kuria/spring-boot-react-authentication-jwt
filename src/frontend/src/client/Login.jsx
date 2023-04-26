export function login(values) {
  return fetch("http://localhost:8080/login", {
    headers: {
      "Content-Type": "application/json",
    },
    method: "POST",
    body: JSON.stringify(values),
  });
}

export function getToken() {
  return fetch("http://localhost:8080/login", {
    mode: "cors",
  });
}

export function getStudents(token) {
  const headers = { Authorization: "Bearer " + token };
  return fetch("http://localhost:8080/api/students", {
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${token}`,
    },
  });
}
