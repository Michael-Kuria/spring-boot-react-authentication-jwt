import { useState } from "react";
import "./App.css";
import LoginForm from "./components/LoginForm";
import StudentPage from "./components/Studentpage/StudentPage";
import { AuthProvider } from "./Utils/AuthContext";

function App() {
  const [bearerToken, setBearerToken] = useState("");

  return (
    <AuthProvider>
      <main>
        <LoginForm setBearerToken={setBearerToken} />

        <div className="div">
          {bearerToken !== "" ? <StudentPage token={bearerToken} /> : ""}
          <div>{bearerToken}</div>
          <div></div>
        </div>
      </main>
    </AuthProvider>
  );
}

export default App;
