import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

import axios from "axios";
import { getNickname } from "../api/users/users";
import { getStatus } from "../api/users/users";
import { GetServerSideProps } from "next";
import { InferGetServerSidePropsType } from "next";

//main page
const MainFunc = ({
  nicknameData,
  statusData,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <MainPage nicknameData={nicknameData} statusData={statusData} />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainFunc;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const userId = "1";
  const token = "token";

  const nicknameRes = await getNickname(token, userId);
  const nicknameData = nicknameRes.data;
  console.log(nicknameData.content);

  const statusRes = await getStatus(token, userId);
  const statusData = statusRes.data;
  console.log(statusData.content);

  return {
    props: {
      nicknameData: nicknameData.content,
      statusData: statusData.content,
    },
  };
};
