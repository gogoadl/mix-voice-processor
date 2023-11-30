import { lazy } from "react";

// project imports
import Loadable from "../ui-component/Loadable";
import MainLayout from "../layout/MainLayout";

// login option 3 routing
const AuthLogin = Loadable(lazy(() => import("../views/pages/authentication/authentication/Login")));
const AuthRegister = Loadable(lazy(() => import("../views/pages/authentication/authentication/Register")));

// ==============================|| AUTHENTICATION ROUTING ||============================== //

const AuthenticationRoutes = {
  path: "/",
  element: <MainLayout />,
  children: [
    {
      path: "/pages/login",
      element: <AuthLogin />,
    },
    {
      path: "/pages/register",
      element: <AuthRegister />,
    },
  ],
};

export default AuthenticationRoutes;
