// 유저별 도전과제 페이지
// 유저별 마이페이지
// import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import QuestPage from "@/components/page/QuestPage/QuestPage";
import { GetServerSideProps, InferGetServerSidePropsType } from "next";
import { getQuests } from "../api/main";
import { useRouter } from "next/router";

//QuestPage
const QuestFunc = ({
  questData,
}: // error,
InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const router = useRouter();

  // if (error) {
  //   console.log(error);

  //   // 오류 처리 로직
  //   alert("페이지에 접근할 수 없습니다. 다시 로그인해주세요");
  //   router.push("/");
  //   return;
  // }
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
  // try {
  const token = context.req.headers.cookie?.replace("Authorization=", "");
  const questRes = await getQuests(`${token}`);
  const questData = questRes.data.content;
  console.log(questData);

  // const num = questData.length;
  // for (let i = 0; i < num; i++) {
  //   if (questData[i].questId === -1) {
  //     questData.splice(i, 1);
  //   }
  // }
  // console.log(questData);

  return {
    props: {
      questData: questData,
    },
  };
  // } catch (e) {
  //   return {
  //     props: {
  //       error: "An error occurred",
  //     },
  //   };
  // }
};
