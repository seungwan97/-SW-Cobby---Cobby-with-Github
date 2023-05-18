import { Fragment } from "react";
import * as style from "@/components/page/MainPage/MainComponents/style/MainPage";

import CobbyInfo from "./MainComponents/CobbyInfo";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import GithubStatus from "./MainComponents/GithubStatus";
import CurrentCommit from "./MainComponents/CurrentCommit";
interface Props {
  nicknameData: {
    nickname: string;
  };
  statusData: {
    commitCnt: number;
    starCnt: number;
  };
  commitData: {
    relayCnt: number;
    todayCnt: number;
  };
  attendanceData: {
    relayCnt: number;
  };
  avatarData: {
    level: number;
    exp: number;
    prevExp: number;
    nextExp: number;
    outfits: {
      head: string;
      effect: string;
      body: string;
    };
  };
}
const MainPage = (props: Props) => {
  const { nicknameData } = props;
  const { statusData } = props;
  const { commitData } = props;
  const { attendanceData } = props;
  const { avatarData } = props;

  console.log(props);

  return (
    <Fragment>
      <style.MainPageContent>
        <CobbyInfo nicknameData={nicknameData} avatarData={avatarData} />
        <GithubStatus statusData={statusData} commitData={commitData} />
        <CurrentCommit
          nicknameData={nicknameData}
          attendanceData={attendanceData}
        />
      </style.MainPageContent>
      <style.MainPageMargin />
      <BottomNavBar />
    </Fragment>
  );
};
export default MainPage;
