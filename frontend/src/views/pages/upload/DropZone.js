import React, { useCallback } from "react";
import { useDropzone } from "react-dropzone";
import styled from "styled-components";

import { useTheme } from "@mui/material/styles";
import { Stack, Typography, useMediaQuery } from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";

import axios from "../../../services/index";

const getColor = (props) => {
  if (props.isDragAccept) {
    return "#00e676";
  }
  if (props.isDragReject) {
    return "#ff1744";
  }
  if (props.isFocused) {
    return "#2196f3";
  }
  return "#eeeeee";
};

const Container = styled.div`
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 100px;
  border-width: 2px;
  border-radius: 2px;
  border-color: ${(props) => getColor(props)};
  border-style: dashed;
  background-color: #fafafa;
  color: #bdbdbd;
  outline: none;
  transition: border 0.24s ease-in-out;
`;

const StyledDropzone = (changeValue) => {
  const theme = useTheme();
  const matchDownSM = useMediaQuery(theme.breakpoints.down("md"));
  const onDrop = useCallback((acceptedFiles) => {
    // Do something with the files
    console.log(acceptedFiles);
    handleDrop(acceptedFiles);
  }, []);
  const handleDrop = (files) => {
    let formData = new FormData();
    const config = {
      header: { "content-type": "multipart/form-data" },
    };
    formData.append("file", files[0]);

    axios
      .post("http://localhost:9001/video/upload", formData, config)
      .then((response) => {
        console.log(response);
        changeValue("path");
      })
      .catch((e) => {
        console.log(e);
      });
  };
  const { getRootProps, isFocused, isDragAccept, isDragReject, isDragActive } = useDropzone({
    accept: { "video/*": [] },
    onDrop: onDrop,
  });

  return (
    <div className="container">
      <Container {...getRootProps({ isFocused, isDragAccept, isDragReject })}>
        <Stack alignItems="center" justifyContent="center" spacing={1}>
          <CloudUploadIcon fontSize="large" />
          <br />
          <Typography color={theme.palette.secondary.main} gutterBottom variant={matchDownSM ? "h3" : "h2"}>
            업로드할 동영상 선택
          </Typography>
          <Typography variant="caption" fontSize="16px" textAlign="center">
            또는 파일을 끌어서 놓기
            <br />
            MP4 또는 WebM
            <br />
            720X1280 해상도 이상
            <br />
            최대 1분
          </Typography>
        </Stack>
      </Container>
    </div>
  );
};

export default StyledDropzone;
