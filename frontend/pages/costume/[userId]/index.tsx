// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/pages/style/Page";
import BottomNavBar from "@/components/layout/BottomNavBar";
import TextBox from "@/components/box/TextBox";

//Costumepage
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
        <div>
          여기는 인벤토리야
          <div>
            여기는 인벤토리Bar야 (타입 : head, body, effect)
          </div>
          <div>
            여기는 인벤토리 캄포넌트야
            <div>itembox들이 있어야해</div>
          </div>
        </div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
