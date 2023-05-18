import { useRouter } from "next/router";
import { Fragment, useEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

import {
  getNicknameAndGithubURL,
  getStatus,
  getCommitInfo,
  getAttendanceInfo,
} from "../api/user";

import { getAvatarInfo } from "../api/main";

import { GetServerSideProps } from "next";
import { InferGetServerSidePropsType } from "next";
import cookie from "react-cookies";
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
    const token = cookie.load("Authorization");
    alert(error);
    router.push("/");
    return;
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
    // const token = `Bearer-${context.req.headers.cookie?.split("Bearer-")[1].replace("\r\n", "")}`;
    // const token2 = await cookie.load("Authorization");


    const cookieString: any = context.req.headers.cookie?.split("; ");
    let result: any = {};

    for (var i = 0; i < cookieString.length; i++) {
      var cur = cookieString[i].split("=");
      result[cur[0]] = cur[1];
    }
    const token = result["Authorization"];


    /*
    SESSIONID=60F0E5EFFE64C8BC0BB1BB97FB663C22; refreshToken=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YzUyMDMzYy01YWUyLTRmODEtYjVlYi03ZDE2ZGJjNmZmMGIiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0NDEzNDA5LCJleHAiOjI1NTUzMjU0MDl9.gKpTIzqBRzQQ6KoZpY_FVZ6Wk1AUkQ9_LxFc4wHDDig; Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YzUyMDMzYy01YWUyLTRmODEtYjVlYi03ZDE2ZGJjNmZmMGIiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0NDEzNDA5LCJleHAiOjE2ODUwMTgyMDl9.c6kZgB3P7bhvTJDja1dKe79NKz67emqHKExE7NZz2rc
    */
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
        error: e,
      },
    };
  }
};
