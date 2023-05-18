// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";
import { GetServerSideProps } from "next";
import { getAllItemList } from "../api/main";

// Costumepage
const CostumeFunc = (props: any) => {
  const router = useRouter();

  if (props.error) {
    // 오류 처리 로직
    alert("페이지에 접근할 수 없습니다. 다시 로그인해주세요");
    router.push("/");
  }

  return (
    <Fragment>
      <page.PageWrapper>
        <CostumePage
          headItemList={props.HEAD_ITEMS}
          bodyItemList={props.BODY_ITEMS}
          effectItemList={props.EFFECT_ITEMS}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export const getServerSideProps: GetServerSideProps = async (context) => {
  try {
    const token = context.req.headers.cookie?.replace("Authorization=", "");

    // HEAD 코스튬 목록 불러오기
    const resHEAD = await getAllItemList("HEAD", `${token}`);
    // BODY 코스튬 목록 불러오기
    const resBODY = await getAllItemList("BODY", `${token}`);
    // // EFFECT 코스튬 목록 불러오기
    const resEFFECT = await getAllItemList("EFFECT", `${token}`);

    const HEAD_ITEMS = resHEAD.data.content;
    const BODY_ITEMS = resBODY.data.content;
    const EFFECT_ITEMS = resEFFECT.data.content;

    return {
      props: {
        HEAD_ITEMS,
        BODY_ITEMS,
        EFFECT_ITEMS,
      },
    };
  } catch (e) {
    return {
      props: {
        error: "An error occurred",
      },
    };
  }
};

export default CostumePage;
