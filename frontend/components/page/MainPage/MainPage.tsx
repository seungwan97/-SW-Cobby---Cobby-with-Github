import { Fragment } from "react";
import * as style from "@/components/page/MainPage/MainComponents/style/MainPage";

import CobbyInfo from "./MainComponents/CobbyInfo";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import GithubStatus from "./MainComponents/GithubStatus";
import CurrentCommit from "./MainComponents/CurrentCommit";

const MainPage = () => {
  return (
    <Fragment>
      <style.MainPageContent>
        <CobbyInfo />
        <GithubStatus />
        <CurrentCommit />
      </style.MainPageContent>
      <style.MainPageMargin />
      <BottomNavBar />
    </Fragment>
  );
};
export default MainPage;
