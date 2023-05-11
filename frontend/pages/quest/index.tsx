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
  const userId = "9302629d-ae6a-43b6-a965-996d5429783c";

  const questRes = await getQuests(userId);
  const questData = questRes.data;
  console.log(questData.content);

  return {
    props: {
      questData: questData.content,
    },
  };
};
