import React, { useEffect, useRef } from "react";
import Hls from "hls.js";

const VideoPlayer = ({ src, type }) => {
  const videoRef = useRef();

  useEffect(() => {
    if (type === "m3u8" && Hls.isSupported() && src !== undefined) {
      const hls = new Hls();

      hls.loadSource(src);
      hls.attachMedia(videoRef.current);
    }
  }, [src, type]);

  return type === "m3u8" ? <video ref={videoRef} controls /> : <video ref={videoRef} src={src} controls />;
};

export default VideoPlayer;
