// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";

// Costumepage
const CostumeFunc = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <CostumePage></CostumePage>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
