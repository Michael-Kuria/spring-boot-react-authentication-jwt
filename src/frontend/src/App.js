import { useState } from "react";
import "./App.css";
import LoginForm from "./components/LoginForm";
import StudentPage from "./components/Studentpage/StudentPage";

function App() {
  const [bearerToken, setBearerToken] = useState("");

  return (
    <main>
      <LoginForm setBearerToken={setBearerToken} />

      <div className="div">
        {bearerToken !== "" ? <StudentPage token={bearerToken} /> : ""}
        <div>{bearerToken}</div>
        <div></div>
      </div>
    </main>
  );
}

export default App;
