import PropTypes from "prop-types";

// material-ui
import { Box } from "@mui/material";

// project import
import MainCard from "../../../ui-component/cards/MainCard";

// ==============================|| AUTHENTICATION CARD WRAPPER ||============================== //

const UploadCardWrapper = ({ children, ...other }) => (
  <MainCard
    sx={{
      margin: { xs: 2.5, md: 3 },
      "& > *": {
        flexGrow: 1,
        flexBasis: "50%",
      },
    }}
    content={false}
    {...other}
  >
    <Box sx={{ p: { xs: 2, sm: 3, xl: 5 } }}>{children}</Box>
  </MainCard>
);

UploadCardWrapper.propTypes = {
  children: PropTypes.node,
};

export default UploadCardWrapper;
