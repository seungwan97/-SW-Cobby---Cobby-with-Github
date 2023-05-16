import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MainPage from "@/components/page/MainPage/MainPage";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

//main page
const MainFunc = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <MainPage />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MainFunc;
