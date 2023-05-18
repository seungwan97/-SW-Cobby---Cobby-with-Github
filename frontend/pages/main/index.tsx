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
  error,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const router = useRouter();

  if (error) {
    // 오류 처리 로직
    alert("페이지에 접근할 수 없습니다. 다시 로그인해주세요");
    router.push("/");
  }

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
  try {
    const token = context.req.headers.cookie?.replace("Authorization=", "");

    console.log(token);

    const nicknameRes = await getNicknameAndGithubURL(`${token}`);
    const nicknameData = nicknameRes.data;

    const statusRes = await getStatus(`${token}`);
    const statusData = statusRes.data;

    const commitRes = await getCommitInfo(`${token}`);
    const commitData = commitRes.data;

    const attendanceRes = await getAttendanceInfo(`${token}`);
    const attendanceData = attendanceRes.data;

    const avatarRes = await getAvatarInfo(`${token}`);
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
  } catch (e) {
    return {
      props: {
        error: "An error occurred",
      },
    };
  }
};
