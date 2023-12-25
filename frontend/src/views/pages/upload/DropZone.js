import React, { useCallback, useEffect, useState } from "react";
import { useDropzone } from "react-dropzone";
import styled from "styled-components";

import { useTheme } from "@mui/material/styles";
import { Stack, Typography, useMediaQuery } from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
import { CLOUDFRONT_URL } from "../../../services/constant";
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

const StyledDropzone = (props) => {
  const theme = useTheme();
  const matchDownSM = useMediaQuery(theme.breakpoints.down("md"));
  const [thumbnail, setThumbnail] = useState("");
  const [preview, setPreview] = useState(null);

  useEffect(() => {
    if (thumbnail.length !== 0) {
      setPreview(<img className="preview" src={CLOUDFRONT_URL + thumbnail}></img>);
      return () => {};
    }
  }, [thumbnail]);
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
        let thumbnailUrl = JSON.stringify(response.data);
        console.log(thumbnailUrl);
        thumbnailUrl = thumbnailUrl.replace(/\"/gi, "");
        console.log(thumbnailUrl);
        setThumbnail(thumbnailUrl);
        props.changeValue(thumbnailUrl);
      })
      .catch((e) => {
        console.log(e);
      });
  };
  const { getRootProps, isfocused, isdragaccept, isdragreject, isDragActive } = useDropzone({
    accept: { "video/*": [] },
    onDrop: onDrop,
  });
  if (thumbnail.length === 0) {
    return (
      <div className="container">
        <Container {...getRootProps({ isfocused, isdragaccept, isdragreject })}>
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
  } else {
    <div className="container">
      <Container>{preview}</Container>
    </div>;
  }
};

export default StyledDropzone;
