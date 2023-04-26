import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/pages/style/Page";
import BottomNavBar from "@/components/layout/BottomNavBar";
//main page
const MainPage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <div>MainPage 입니다.</div>
        <div>user명 : {router.query.userId}</div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainPage;
