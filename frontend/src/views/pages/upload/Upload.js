import React, { useState } from "react";
import { Link } from "react-router-dom";
import instance from "../../../services/index";
// material-ui
import { useTheme } from "@mui/material/styles";
import {
  Button,
  Divider,
  Grid,
  Stack,
  Typography,
  useMediaQuery,
  InputLabel,
  OutlinedInput,
  TextField,
  ButtonBase,
} from "@mui/material";

// project imports
import UploadWrapper from "./UploadWrapper";
import UploadCardWrapper from "./UploadCardWrapper";
import MyDropzone from "./DropZone";

const Upload = () => {
  const theme = useTheme();
  const matchDownSM = useMediaQuery(theme.breakpoints.down("md"));
  const [uploadedPath, setUploadedPath] = useState("");
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const handleUploadChange = (path) => {
    setUploadedPath(path);
  };

  const cancelShorts = () => {
    console.log("cancel");
  };
  const uploadShorts = () => {
    instance
      .post("http://localhost:9001/shorts/upload", {
        title: title,
        content: content,
        path: uploadedPath,
      })
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
    console.log("upload");
  };
  return (
    <UploadWrapper>
      <Grid container direction="column" justifyContent="flex-end" sx={{ minHeight: "100vh" }}>
        <Grid item xs={12} md={12}>
          <Grid container justifyContent="center" alignItems="center" sx={{ minHeight: "calc(100vh - 68px)" }}>
            <Grid item sx={{ m: { xs: 1, sm: 3 }, mb: 0 }}>
              <UploadCardWrapper>
                <Grid container spacing={2}>
                  <Grid item xs={12}>
                    <Typography variant="h4">영상 업로드</Typography>
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      required
                      id="outlined-required"
                      value={title}
                      onInput={(e) => setTitle(e.target.value)}
                      fullWidth
                      placeholder="제목을 입력하세요."
                    />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField
                      multiline
                      rows={5}
                      id="outlined"
                      value={content}
                      onInput={(e) => setContent(e.target.value)}
                      fullWidth
                      placeholder="시청자에게 동영상에 대해 설명해주세요."
                    />
                  </Grid>
                </Grid>

                <Grid container spacing={2} alignItems="center" justifyContent="center">
                  <Grid item sx={{ mb: 6 }}></Grid>
                  <Grid item xs={12}>
                    <Grid
                      container
                      direction={matchDownSM ? "column-reverse" : "row"}
                      alignItems="center"
                      justifyContent="center"
                    >
                      <Grid item sx={{ mb: 6 }}></Grid>
                      <Grid item>
                        <MyDropzone changeValue={handleUploadChange}></MyDropzone>
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
                <Grid container alignItems="center" justifyContent="flex-end">
                  <Grid item xs={5}>
                    <Button sx={{ m: 1 }} variant="contained" color="error" onClick={cancelShorts}>
                      취소
                    </Button>
                    <Button sx={{ m: 1 }} variant="contained" color="success" onClick={uploadShorts}>
                      업로드
                    </Button>
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
