// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";
import { GetServerSideProps } from "next";
import { getAllItemList, getAvatarInfo } from "../api/main";
import { InferGetServerSidePropsType } from "next";

// Costumepage
const CostumeFunc = ({
  HEAD_ITEMS,
  BODY_ITEMS,
  EFFECT_ITEMS,
  cobbyOutfits,
  error,
}: InferGetServerSidePropsType<
  typeof getServerSideProps
>) => {
  const router = useRouter();

  if (error) {
    // 오류 처리 로직
    alert(
      "페이지에 접근할 수 없습니다. 다시 로그인해주세요"
    );
    router.push("/");
  }

  return (
    <Fragment>
      <page.PageWrapper>
        <CostumePage
          headItemList={HEAD_ITEMS}
          bodyItemList={BODY_ITEMS}
          effectItemList={EFFECT_ITEMS}
          cobbyOutfits={cobbyOutfits}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export const getServerSideProps: GetServerSideProps =
  async (context) => {
    try {
      const token = context.req.headers.cookie?.replace(
        "Authorization=",
        ""
      );

      // HEAD 코스튬 목록 불러오기
      const resHEAD = await getAllItemList(
        "HEAD",
        `${token}`
      );
      // BODY 코스튬 목록 불러오기
      const resBODY = await getAllItemList(
        "BODY",
        `${token}`
      );
      // // EFFECT 코스튬 목록 불러오기
      const resEFFECT = await getAllItemList(
        "EFFECT",
        `${token}`
      );

      const HEAD_ITEMS = resHEAD.data.content;
      const BODY_ITEMS = resBODY.data.content;
      const EFFECT_ITEMS = resEFFECT.data.content;

      // 아바타 정보 가져오기
      const avatarRes = await getAvatarInfo(`${token}`);
      const avatarData = avatarRes.data;
      console.log(avatarData);

      return {
        props: {
          HEAD_ITEMS,
          BODY_ITEMS,
          EFFECT_ITEMS,
          cobbyOutfits: avatarData.content.outfits,
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
