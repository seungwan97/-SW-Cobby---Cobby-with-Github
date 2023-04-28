// 유저별 도전과제 페이지
// 유저별 마이페이지
// import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import QuestPage from "@/components/page/QuestPage/QuestPage";

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
];

//QuestPage
const QuestFunc = (props: any) => {
  // const router = useRouter(); // router.query.userId
  return (
    <Fragment>
      <page.PageWrapper>
        <QuestPage QuestItemList={props.QuestItemList} />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export async function getStaticProps() {
  // fetch data for a single meetup
  return {
    props: {
      QuestItemList: DUMMY_DATA,
    },
  };
}

export default QuestFunc;
