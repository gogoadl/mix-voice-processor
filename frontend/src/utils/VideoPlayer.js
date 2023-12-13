import React, { useEffect, useRef } from "react";
import styled from "styled-components";
import Hls from "hls.js";

const VideoAspectRatio = styled.video`
  aspect-ratio: 9 / 16;
  width: 100%;
  border-radius: 20px;
`;

const VideoPlayer = ({ src, type }) => {
  const videoRef = useRef();

  useEffect(() => {
    if (type === "m3u8" && Hls.isSupported() && src !== undefined) {
      const hls = new Hls();

      hls.loadSource(src);
      hls.attachMedia(videoRef.current);
    }
  }, [src, type]);

  return type === "m3u8" ? <VideoAspectRatio ref={videoRef} controls /> : <video ref={videoRef} src={src} controls />;
};

export default VideoPlayer;
