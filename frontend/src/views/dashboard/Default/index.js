import { useEffect, useState } from "react";
import axios from "../../../services/index";
// material-ui
import { Grid } from "@mui/material";
import getPost from "../../../services/post";
// project imports
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
  }, []);

  return (
    <Grid container spacing={gridSpacing}>
      <Grid item xs={12}>
        <video controls name="media">
          <source src="http://localhost:9001/video/downloadtest" type="video/mp4"></source>
        </video>
        {/* <Grid container spacing={gridSpacing}>
          <Grid item lg={4} md={6} sm={6} xs={12}>
            <EarningCard isLoading={isLoading} />
          </Grid>
          <Grid item lg={4} md={6} sm={6} xs={12}>
            <TotalOrderLineChartCard isLoading={isLoading} />
          </Grid>
          <Grid item lg={4} md={12} sm={12} xs={12}>
            <Grid container spacing={gridSpacing}>
              <Grid item sm={6} xs={12} md={6} lg={12}>
                <TotalIncomeDarkCard isLoading={isLoading} />
              </Grid>
              <Grid item sm={6} xs={12} md={6} lg={12}>
                <TotalIncomeLightCard isLoading={isLoading} />
              </Grid>
            </Grid>
          </Grid> */}
        {/* </Grid> */}
      </Grid>
      <Grid item xs={12}>
        <Grid container spacing={gridSpacing}>
          <Grid item xs={12} md={8}>
            <TotalGrowthBarChart isLoading={isLoading} />
          </Grid>
          <Grid item xs={12} md={4}>
            <PopularCard isLoading={isLoading} />
          </Grid>
        </Grid>
      </Grid>
    </Grid>
  );
};

export default Dashboard;
