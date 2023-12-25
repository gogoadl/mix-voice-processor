import { useEffect, useState } from "react";
import axios from "../../../services/index";
import { store } from "../../../store/index";
import { LOGIN_USER, LOGOUT_USER } from "../../../store/actions";
import { useSelector, useDispatch } from "react-redux";
// material-ui
import { Grid } from "@mui/material";
// project imports
import ShortsCard from "./ShortsCard";
import { gridSpacing } from "../../../store/constant";
import { CLOUDFRONT_URL } from "../../../services/constant";

// ==============================|| DEFAULT DASHBOARD ||============================== //

const Dashboard = () => {
  const [isLoading, setLoading] = useState(true);
  const [shorts, setShorts] = useState(null);
  const dispatch = useDispatch();
  useEffect(() => {
    console.log(localStorage.getItem("token"));
    if (localStorage.getItem("token")) {
      axios
        .get("http://localhost:9001/user/me")
        .then((response) => {
          console.log(response.data);
          dispatch({ type: LOGIN_USER, data: response.data });
        })
        .catch((error) => {
          console.log(error);
        });
    }
    axios
      .get("http://localhost:9001/shorts/")
      .then((response) => {
        console.log(response);
        setShorts(response.data);
        setLoading(false);
      })
      .catch((error) => {});
  }, []);

  return (
    <Grid container spacing={gridSpacing}>
      <Grid item xs={12}></Grid>
      <Grid item xs={12}>
        <Grid container spacing={gridSpacing}>
          <Grid item xs={12} md={12}>
            <ShortsCard isLoading={isLoading} url={CLOUDFRONT_URL + shorts?.url + "/master.m3u8"} />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Dashboard;
