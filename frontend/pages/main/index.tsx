import { useRouter } from "next/router";
import { Fragment, useEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

import { getNickname } from "../api/users";

import { getStatus } from "../api/stat";

import { getAttendanceInfo, getCommitInfo } from "../api/activityLog";
import { GetServerSideProps } from "next";
import { InferGetServerSidePropsType } from "next";

//main page
const MainFunc = ({
  nicknameData,
  statusData,
  commitData,
  attendanceData,
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
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainFunc;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const userId = "aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa";
  const token = "token";

  const nicknameRes = await getNickname(token, userId);
  const nicknameData = nicknameRes.data;
  console.log(nicknameData.content);

  const statusRes = await getStatus(token, userId);
  const statusData = statusRes.data;
  console.log(statusData.content);

  const commitRes = await getCommitInfo(token, userId);
  const commitData = commitRes.data;
  console.log(commitData.content);

  const attendanceRes = await getAttendanceInfo(token, userId);
  const attendanceData = attendanceRes.data;
  console.log(attendanceData.content);
  return {
    props: {
      nicknameData: nicknameData.content,
      statusData: statusData.content,
      commitData: commitData.content,
      attendanceData: attendanceData.content,
    },
  };
};
