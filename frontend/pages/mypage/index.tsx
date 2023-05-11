// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MyPage from "@/components/page/MyPage/MyPage";
import { GetServerSideProps } from "next";
import { getAvatarInfo } from "../api/main";

// MyPage
const MyFunc = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <MyPage />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyFunc;

export const getServerSideProps: GetServerSideProps =
  async (context) => {
    const userId = "9302629d-ae6a-43b6-a965-996d5429783c";

    const res = await getAvatarInfo(userId);

    // if (res.status === 200) {
    const myLevel = res.data.content.level;
    const cntCostumes = res.data.content.costumes.length;
    const cntQuests = res.data.content.quests.length;

    return {
      props: {
        myLevel: { myLevel },
        cntCostumes: { cntCostumes },
        cntQuests: { cntQuests },
      },
    };
    // }
  };
