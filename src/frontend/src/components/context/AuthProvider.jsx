/**
 * Provide login, logout, authenticated user token and username
 */

import { createContext, useContext, useEffect, useState } from "react";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = localStorage.getItem("user");
    if (storedUser) setUser(storedUser);
  }, []);

  function userLogin(user) {
    setUser(user);
    localStorage.setItem("user", user);
  }

  function userLogout() {
    setUser(null);
    localStorage.removeItem("user");
  }

  function isAuthenticated() {
    return user !== null;
  }

  return (
    <AuthContext.Provider
      value={{ user, userLogin, userLogout, isAuthenticated }}
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
