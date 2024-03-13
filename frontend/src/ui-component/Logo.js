import logo from '../assets/images/logo3.svg';

import { styled } from "@mui/material/styles";

const StyledLogo = styled('img')({
  height: '48px',
  borderRadius: "25%"
});
const Logo = () => {
  return (
      <StyledLogo src={logo} alt="MixVoice" />
  );
};

export default Logo;
