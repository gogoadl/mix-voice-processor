import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";

function Redirect() {
  const location = useLocation();
  const params = new URLSearchParams(location.search);
  const token = params.get("token");
  const refreshToken = params.get("refreshToken");

  useEffect(() => {
    localStorage.setItem("token", token!);
    localStorage.setItem("refreshToken", refreshToken!);
  });
  window.location.href = "/";

  return;
}

export default Redirect;
