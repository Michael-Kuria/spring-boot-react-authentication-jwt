import { useEffect, useRef, useState } from "react";

import "./LoginForm.css";
import { getToken, login } from "../client/Login";

const LoginForm = ({ setBearerToken }) => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const usernameRef = useRef();

  const resetForm = () => {
    setUsername("");
    setPassword("");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const values = {
      email: username,
      password: password,
    };
    console.log(values);

    login(values)
      .catch((error) => console.log(error))
      .finally(() => {
        getToken()
          .then((response) => {
            console.log(response);
            return response.json();
          })
          .then((data) => {
            console.log(data);
            setBearerToken(data.token);
          })
          .catch((error) => console.log(error));
      });

    resetForm();
  };

  useEffect(() => {
    usernameRef.current.focus();
  }, []);

  return (
    <div className="login">
      <div className="login__form form">
        <h2>Login</h2>
        <div>
          <form onSubmit={(e) => handleSubmit(e)}>
            <div className="login__field">
              <label htmlFor="username">Username</label>
              <input
                className="form__input"
                placeholder="Email"
                ref={usernameRef}
                type="text"
                id="username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </div>
            <div className="login__field">
              <label htmlFor="password">Password</label>
              <input
                className="form__input"
                placeholder="Password"
                type="password"
                id="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
              />
            </div>
            <button type="submit" className="btn login__btn">
              Login
            </button>
          </form>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
