// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";

// CostumePage
const CostumePage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <TextBox size={50} content={"COSTUME"} />
        <div>
          로그인한 사용자의 저장된 코비 모습 불러와야한다잉
        </div>
        <div>user명 : {router.query.userId}</div>
        <Inventory />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
