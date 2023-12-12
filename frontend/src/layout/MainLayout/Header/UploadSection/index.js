import { useState, useRef, useEffect } from "react";
import { Link } from "react-router-dom";

// material-ui
import { useTheme } from "@mui/material/styles";
import { styled } from "@mui/material/styles";
import {
  Avatar,
  Box,
  Button,
  ButtonBase,
  CardActions,
  Chip,
  ClickAwayListener,
  Divider,
  Grid,
  Paper,
  Popper,
  Stack,
  TextField,
  Typography,
  useMediaQuery,
} from "@mui/material";
import CloudUploadIcon from "@mui/icons-material/CloudUpload";
// ==============================|| NOTIFICATION ||============================== //

const UploadSection = () => {
  const theme = useTheme();
  const matchesXs = useMediaQuery(theme.breakpoints.down("md"));

  const [open, setOpen] = useState(false);
  const [value, setValue] = useState("");
  /**
   * anchorRef is used on different componets and specifying one type leads to other components throwing an error
   * */
  const anchorRef = useRef(null);

  const handleToggle = () => {
    setOpen((prevOpen) => !prevOpen);
  };

  return (
    <Box
      sx={{
        ml: 2,
        mr: 3,
        [theme.breakpoints.down("md")]: {
          mr: 2,
        },
      }}
    >
      <ButtonBase sx={{ borderRadius: "12px" }}>
        <Button component={Link} to="/upload" variant="contained" startIcon={<CloudUploadIcon />} href="">
          업로드
        </Button>
      </ButtonBase>
    </Box>
  );
};

export default UploadSection;
