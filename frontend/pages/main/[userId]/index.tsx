import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";

//main page
const MainPage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <div>메인페이지입니다. 테스트id명 : test</div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainPage;
