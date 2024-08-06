import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { Login } from "./components/jsx/Login.jsx";
import { Registration } from "./components/jsx/Registration.jsx";
import { MainMenu } from "./components/jsx/MainMenu.jsx";
const router = createBrowserRouter([
  {
    path: "/",
    element: <Login> </Login>,
  },
  {
    path: "/main",
    element: <MainMenu></MainMenu>
  },
  {
    path: "/registration",
    element: <Registration/>
  }
]);

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <RouterProvider router={router} />
  </React.StrictMode>
);
