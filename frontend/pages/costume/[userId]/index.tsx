// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/pages/style/Page";
import BottomNavBar from "@/components/layout/BottomNavBar";
//Costumepage
const CostumePage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <div>CostumePage 입니다.</div>
        <div>user명 : {router.query.userId}</div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
