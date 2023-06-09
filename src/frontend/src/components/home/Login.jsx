import { useState } from "react";
import { useAuth } from "../context/AuthProvider";
import { Navigate } from "react-router-dom";
import { Button, Form, Grid, Message, Segment } from "semantic-ui-react";
import { Client } from "../../client/Client";
import { parseJwt } from "../helpers/Utils";

const Login = () => {
  const [email, setEmail] = useState();
  const [password, setPassword] = useState();
  const [isError, setIsError] = useState(false);
  const { userLogin, isAuthenticated } = useAuth();

  function handleInputChange(e, stateFunction) {
    stateFunction(e.target.value);
  }

  const handleSubmit = (e) => {
    e.preventDefault();

    if (!(email && password)) {
      setIsError(true);
      return;
    }
    Client.authenticate(email, password)
      .then((response) => {
        const { token } = response.data;
        const data = parseJwt(token);
        const user = { data, token };

        userLogin(JSON.stringify(user));
        setPassword("");
        setEmail("");
        setIsError("");
      })
      .catch((error) => {
        console.log(error.message);
        setIsError(true);
      });
  };

  if (isAuthenticated()) {
    return <Navigate to="/" />;
  } else {
    return (
      <Grid textAlign="center">
        <Grid.Column style={{ maxWidth: 450 }}>
          <Form size="large" onSubmit={(e) => handleSubmit(e)}>
            <Segment>
              <Form.Input
                fluid
                autoFocus
                name="email"
                icon="mail"
                iconPosition="left"
                placeholder="email"
                onChange={(e) => handleInputChange(e, setEmail)}
              />
              <Form.Input
                fluid
                autoFocus
                name="password"
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                type="password"
                onChange={(e) => handleInputChange(e, setPassword)}
              />
              <Button color="violet" fluid size="large">
                Login
              </Button>
            </Segment>
          </Form>
          {isError && (
            <Message negative>
              The email or password provided are incorrect
            </Message>
          )}
        </Grid.Column>
      </Grid>
    );
  }
};

export default Login;
