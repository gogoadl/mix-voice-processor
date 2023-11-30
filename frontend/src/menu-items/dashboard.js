// assets
import { IconHome, IconVideo } from "@tabler/icons";

// constant
const icons = { IconHome, IconVideo };

// ==============================|| DASHBOARD MENU ITEMS ||============================== //

const Home = {
  id: "Home",
  title: "Home",
  type: "group",
  children: [
    {
      id: "default",
      title: "Home",
      type: "item",
      url: "/",
      icon: icons.IconHome,
      breadcrumbs: false,
    },
    {
      id: "shorts",
      title: "shorts",
      type: "item",
      url: "/Home/shorts",
      icon: icons.IconVideo,
      breadcrumbs: false,
    },
  ],
};

export default Home;
