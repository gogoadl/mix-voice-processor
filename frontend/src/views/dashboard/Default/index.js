import { useEffect, useState } from "react";
import axios from "../../../services/index";
import { store } from "../../../store/index";
import { LOGIN_USER, LOGOUT_USER } from "../../../store/actions";
import { useSelector, useDispatch } from "react-redux";
// material-ui
import { Grid } from "@mui/material";
import getPost from "../../../services/post";
// project imports
import ShortsCard from "./ShortsCard";
import EarningCard from "./EarningCard";
import PopularCard from "./PopularCard";
import TotalOrderLineChartCard from "./TotalOrderLineChartCard";
import TotalIncomeDarkCard from "./TotalIncomeDarkCard";
import TotalIncomeLightCard from "./TotalIncomeLightCard";
import TotalGrowthBarChart from "./TotalGrowthBarChart";
import { gridSpacing } from "../../../store/constant";

// ==============================|| DEFAULT DASHBOARD ||============================== //

const Dashboard = () => {
  const [isLoading, setLoading] = useState(true);
  const [src, setSrc] = useState();
  const dispatch = useDispatch();
  useEffect(() => {
    setLoading(false);
    axios
      .get("http://localhost:9001/video/downloadtest")
      .then((response) => {
        console.log(response.data);
        setSrc(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
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
  }, []);

  return (
    <Grid container spacing={gridSpacing}>
      <Grid item xs={12}></Grid>
      <Grid item xs={12}>
        <Grid container spacing={gridSpacing}>
          <Grid item xs={12} md={12}>
            <ShortsCard isLoading={isLoading} url={src} />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Dashboard;
