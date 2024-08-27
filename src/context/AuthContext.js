import React, { createContext, useState, useEffect } from "react";

export const AuthContext = createContext();

const AuthProvider = ({ children }) => {
  const [isLoggedIn, setIsLoggedIn] = useState(() => {
    const savedState = localStorage.getItem("isLoggedIn");
    return savedState ? JSON.parse(savedState) : false;
  });

  const [isAdminLoggedIn, setAdminIsLoggedIn] = useState(() => {
    const savedState = localStorage.getItem("isLoggedIn");
    return savedState ? JSON.parse(savedState) : false;
  });

  const [userId, setUserId] = useState(() => {
    const savedState = localStorage.getItem("userId");
    return savedState ? parseInt(savedState, 10) : 0; // Convert to integer
  });

  const [adminId, setAdminId] = useState(() => {
    const savedState = localStorage.getItem("adminId");
    return savedState ? parseInt(savedState, 10) : 0; // Convert to integer
  });

  const [propertyId, SetPropertyId] = useState(() => {
    const savedState = localStorage.getItem("propertyId");
    return savedState ? parseInt(savedState, 10) : 0; // Convert to integer
  });

  useEffect(() => {
    localStorage.setItem("isLoggedIn", JSON.stringify(isLoggedIn));
  }, [isLoggedIn]);

  useEffect(() => {
    localStorage.setItem("isAdminLoggedIn", JSON.stringify(isAdminLoggedIn));
  }, [isAdminLoggedIn]);

  useEffect(() => {
    localStorage.setItem("userId", JSON.stringify(userId));
  }, [userId]);

  useEffect(() => {
    localStorage.setItem("userId", JSON.stringify(propertyId));
  }, [propertyId]);

  const loginin = (id) => {
    setIsLoggedIn(true);
    setUserId(id);
  };

  const logout = () => {
    setIsLoggedIn(false);
    setAdminIsLoggedIn(false);
    setUserId(0);
  };

  const adminLoginin = (id) => {
    setAdminIsLoggedIn(true);
    setAdminId(id);
    console.log(id);
    
  };

  const adminLogout = () => {
    setAdminIsLoggedIn(false);
    setUserId(0);
  };

  const getUserId = () => {
    return parseInt(userId, 10); 
  };

  const getPropertyId = () => {
    return parseInt(propertyId, 10);

  };

  const setPropertyId = (id) => {
    console.log(id);
    SetPropertyId(id);
  };

  return (
    <AuthContext.Provider value={{ isLoggedIn, isAdminLoggedIn, loginin, logout, getUserId , getPropertyId ,setPropertyId ,adminLogout,adminLoginin }}>
      {children}
    </AuthContext.Provider>
  );
};

export default AuthProvider;
