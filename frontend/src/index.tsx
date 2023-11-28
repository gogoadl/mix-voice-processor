import * as React from "react";
import * as ReactDOM from "react-dom/client";
import { BrowserRouter } from "react-router-dom";
import { createStore } from "redux";
import { Provider } from "react-redux";
import App from "./App";
import reducer from "./store/reducer";
import config from "./config";
import "./assets/scss/style.scss";
import * as serviceWorker from "./serviceWorker";
const root = ReactDOM.createRoot(document.getElementById("root")!);
const store = createStore(reducer);
root.render(
  <Provider store={store}>
    <BrowserRouter basename={config.basename}>
      <App />
    </BrowserRouter>
  </Provider>
);
// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
