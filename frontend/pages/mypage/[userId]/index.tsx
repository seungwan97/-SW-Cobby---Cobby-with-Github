// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar";
import * as page from "@/pages/style/Page";

//MyPage
const MyPage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <div>MyPage 입니다.</div>
        <div>user명 : {router.query.userId}</div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyPage;
