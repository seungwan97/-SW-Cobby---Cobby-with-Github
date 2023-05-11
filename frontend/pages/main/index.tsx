import { useRouter } from "next/router";
import { Fragment, useEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

import {
  getNickname,
  getStatus,
  getCommitInfo,
  getAttendanceInfo,
} from "../api/user";

import { getAvatarInfo } from "../api/main";

import { GetServerSideProps } from "next";
import { InferGetServerSidePropsType } from "next";

//main page
const MainFunc = ({
  nicknameData,
  statusData,
  commitData,
  attendanceData,
  avatarData,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  // useEffect(() => {
  //   localStorage.setItem("nickname", JSON.stringify(nicknameData.nickname));
  // }, []);
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <MainPage
          nicknameData={nicknameData}
          statusData={statusData}
          commitData={commitData}
          attendanceData={attendanceData}
          avatarData={avatarData}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainFunc;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const userId = "9302629d-ae6a-43b6-a965-996d5429783c";
  const token = "token";

  const nicknameRes = await getNickname(userId);
  const nicknameData = nicknameRes.data;
  console.log(nicknameData.content);

  const statusRes = await getStatus(userId);
  const statusData = statusRes.data;
  console.log(statusData.content);

  const commitRes = await getCommitInfo(userId);
  const commitData = commitRes.data;
  console.log(commitData.content);

  const attendanceRes = await getAttendanceInfo(userId);
  const attendanceData = attendanceRes.data;
  console.log(attendanceData.content);

  const avatarRes = await getAttendanceInfo(userId);
  const avatarData = avatarRes.data;

  console.log(avatarData.content);

  return {
    props: {
      nicknameData: nicknameData.content,
      statusData: statusData.content,
      commitData: commitData.content,
      attendanceData: attendanceData.content,
      avatarData: avatarData.content,
    },
  };
};
