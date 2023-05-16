// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MyPage from "@/components/page/MyPage/MyPage";

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
