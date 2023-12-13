import { Link } from "react-router-dom";

// material-ui
import { useTheme } from "@mui/material/styles";
import { Button, Divider, Grid, Stack, Typography, useMediaQuery } from "@mui/material";

// project imports
import UploadWrapper from "./UploadWrapper";
import UploadCardWrapper from "./UploadCardWrapper";
import MyDropzone from "./DropZone";

const Upload = () => {
  const theme = useTheme();
  const matchDownSM = useMediaQuery(theme.breakpoints.down("md"));

  return (
    <UploadWrapper>
      <Grid container direction="column" justifyContent="flex-end" sx={{ minHeight: "100vh" }}>
        <Grid item xs={12} md={12}>
          <Grid container justifyContent="center" alignItems="center" sx={{ minHeight: "calc(100vh - 68px)" }}>
            <Grid item sx={{ m: { xs: 1, sm: 3 }, mb: 0 }}>
              <UploadCardWrapper>
                <Grid container spacing={2} alignItems="center" justifyContent="center">
                  <Grid item sx={{ mb: 6 }}></Grid>
                  <Grid item xs={12}>
                    <Grid
                      container
                      direction={matchDownSM ? "column-reverse" : "row"}
                      alignItems="center"
                      justifyContent="center"
                    >
                      <Grid item>
                        <MyDropzone></MyDropzone>
                      </Grid>
                    </Grid>
                  </Grid>
                  <Grid item xs={12}></Grid>
                  <Grid item xs={12}>
                    <Divider />
                  </Grid>
                  <Grid item xs={12}>
                    <Grid item container direction="column" alignItems="center" xs={12}></Grid>
                  </Grid>
                </Grid>
              </UploadCardWrapper>
            </Grid>
          </Grid>
        </Grid>
      </Grid>
    </UploadWrapper>
  );
};

export default Upload;
