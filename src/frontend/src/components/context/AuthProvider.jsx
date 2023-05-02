/**
 * Provide login, logout, authenticated user token and username
 */

import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(getUser());

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) {
      setUser((prev) => JSON.parse(storedUser));
    }
  }, []);

  function getUser() {
    return JSON.parse(localStorage.getItem("user"));
  }

  function userLogin(user) {
    setUser(user);
    localStorage.setItem("user", user);
  }

  function userLogout() {
    setUser(null);
    localStorage.removeItem("user");
  }

  function isAuthenticated() {
    let user = localStorage.getItem("user");

    if (!user) return false;

    // if user has token expired, logout user
    user = JSON.parse(user);
    if (Date.now() > user.data.exp * 1000) {
      userLogout();
      return false;
    }
    return true;
  }

  return (
    <AuthContext.Provider
      value={{ user, getUser, userLogin, userLogout, isAuthenticated }}
    >
      {children}
    </AuthContext.Provider>
  );
};

/**
 * Custom hook for the use context
 *
 * @returns AuthContext
 */
export function useAuth() {
  return useContext(AuthContext);
}
