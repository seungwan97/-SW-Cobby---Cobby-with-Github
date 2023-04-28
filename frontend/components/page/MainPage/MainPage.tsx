import { Fragment } from "react";
import * as style from "@/components/page/MainPage/MainComponents/style/MainPage";

import CobbyInfo from "./MainComponents/CobbyInfo";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import ExpBar from "./MainComponents/ExpBar";

const MainPage = () => {
  return (
    <Fragment>
      <style.MainPageContent>
        <CobbyInfo />
        <ExpBar />
      </style.MainPageContent>
      <style.MainPageMargin />
      <BottomNavBar />
    </Fragment>
  );
};
export default MainPage;
