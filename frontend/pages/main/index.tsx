import { useRouter } from "next/router";
import { Fragment, useEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import { getCookie } from "@/util/cookie";

import {
  getNicknameAndGithubURL,
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
  // const userId = context.req.headers.cookie?.split("; ")[4].replace("Authorization=", "");
  const userId = "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZDJkMDlmNC1lOTA0LTQyZDMtOTQwMy0wMzJkODE0ZDVhNjYiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0MjUxOTM0LCJleHAiOjE2ODQyNTU1MzR9.p9miuyHDFwDG3ImN31G17LfapE3Y17ZM2YpNaeq9jG0";
  console.log(userId);
  const token = "token";

  const nicknameRes = await getNicknameAndGithubURL(userId);
  const nicknameData = nicknameRes.data;

  const statusRes = await getStatus(userId);
  const statusData = statusRes.data;

  const commitRes = await getCommitInfo(userId);
  const commitData = commitRes.data;

  const attendanceRes = await getAttendanceInfo(userId);
  const attendanceData = attendanceRes.data;

  const avatarRes = await getAvatarInfo(userId);
  const avatarData = avatarRes.data;

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
