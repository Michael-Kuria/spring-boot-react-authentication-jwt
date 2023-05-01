import { useState } from "react";
import "./App.css";
import { AuthProvider } from "./components/context/AuthProvider";
import { Router, Routes, Route } from "react-router-dom";
import Login from "./components/home/Login";
import ProtectedRoute from "./components/helpers/ProtectedRoute";
import Home from "./components/home/Home";

function App() {
  return (
    <AuthProvider>
      <main>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <Home />
              </ProtectedRoute>
            }
          />
        </Routes>
      </main>
    </AuthProvider>
  );
}

export default App;
