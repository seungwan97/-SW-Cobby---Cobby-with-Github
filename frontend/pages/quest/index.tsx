// 유저별 도전과제 페이지
// 유저별 마이페이지
// import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import QuestPage from "@/components/page/QuestPage/QuestPage";
import { GetServerSideProps, InferGetServerSidePropsType } from "next";
import { getQuests } from "../api/main";

const DUMMY_DATA = [
  {
    category: "Level",
    title: "Reaching Level",
    goal: 10,
    progress: 70,
    award: "/Character/Cobby.png",
  },
  {
    category: "Level",
    title: "Reaching Level",
    goal: 20,
    progress: 70,
    award: "/Character/Cobby.png",
  },
  {
    category: "Level",
    title: "Reaching Level",
    goal: 30,
    progress: 70,
    award: "/Character/Cobby.png",
  },
  {
    category: "Level",
    title: "Reaching Level",
    goal: 40,
    progress: 70,
    award: "/Character/Cobby.png",
  },
];

//QuestPage
const QuestFunc = ({
  questData,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  // const router = useRouter(); // router.query.userId
  return (
    <Fragment>
      <page.PageWrapper>
        <QuestPage questData={questData} />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default QuestFunc;

export const getServerSideProps: GetServerSideProps = async (context) => {
  const token = "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZDJkMDlmNC1lOTA0LTQyZDMtOTQwMy0wMzJkODE0ZDVhNjYiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0MjUxOTM0LCJleHAiOjE2ODQyNTU1MzR9.p9miuyHDFwDG3ImN31G17LfapE3Y17ZM2YpNaeq9jG0";

  const questRes = await getQuests(token);
  const questData = questRes.data;
  console.log(questData.content);

  return {
    props: {
      questData: questData.content,
    },
  };
};
