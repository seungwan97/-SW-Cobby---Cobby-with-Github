// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";
import * as style from "./CostumeComponents/style/CostumePage";
import Cobby from "@/components/common/Cobby/Cobby";

// CostumePage
const CostumePage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <style.CustomeTxt>
          <TextBox size={50} content={"COSTUME"} />
        </style.CustomeTxt>
        <style.CustomedCobby>
          <Cobby />
          <div>user명 : {router.query.userId}</div>
        </style.CustomedCobby>
        <Inventory />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
