// assets
import { IconLogin } from "@tabler/icons";

// constant
const icons = {
  IconLogin,
};

// ==============================|| EXTRA PAGES MENU ITEMS ||============================== //

const myPage = {
  id: "myPage",
  title: "나",
  caption: "My Pages",
  type: "group",
  children: [
    {
      id: "login",
      title: "Login",
      type: "item",
      url: "/pages/login",
      icon: icons.IconLogin,
    },
  ],
};

export default myPage;
