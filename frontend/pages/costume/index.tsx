// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";
import { GetServerSideProps } from "next";
<<<<<<< HEAD
import { getAllItemList } from "../api/main";

// Costumepage
const CostumeFunc = (props: any) => {
  // const router = useRouter();
=======
import { getAllItemList, getAvatarInfo } from "../api/main";
import { InferGetServerSidePropsType } from "next";

// Costumepage
const CostumeFunc = ({
  HEAD_ITEMS,
  BODY_ITEMS,
  EFFECT_ITEMS,
  cobbyOutfits,
  error,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const router = useRouter();

  if (error) {
    // 오류 처리 로직
    alert("페이지에 접근할 수 없습니다. 다시 로그인해주세요");
    router.push("/");
    return;
  }
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

  return (
    <Fragment>
      <page.PageWrapper>
        <CostumePage
<<<<<<< HEAD
          headItemList={props.HEAD_ITEMS}
          bodyItemList={props.BODY_ITEMS}
          effectItemList={props.EFFECT_ITEMS}
=======
          HEAD_ITEMS={HEAD_ITEMS}
          BODY_ITEMS={BODY_ITEMS}
          EFFECT_ITEMS={EFFECT_ITEMS}
          cobbyOutfits={cobbyOutfits}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export const getServerSideProps: GetServerSideProps = async (context) => {
<<<<<<< HEAD
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
=======
  try {
    const cookieString: any = context.req.headers.cookie?.split("; ");
    let result: any = {};

    for (var i = 0; i < cookieString.length; i++) {
      var cur = cookieString[i].split("=");
      result[cur[0]] = cur[1];
    }
    const token = result["Authorization"];

    // HEAD 코스튬 목록 불러오기
    const resHEAD = await getAllItemList("HEAD", `${token}`);
    // BODY 코스튬 목록 불러오기
    const resBODY = await getAllItemList("BODY", `${token}`);
    // // EFFECT 코스튬 목록 불러오기
    const resEFFECT = await getAllItemList("EFFECT", `${token}`);

    const HEAD_ITEMS = resHEAD.data.content;
    const BODY_ITEMS = resBODY.data.content;
    const EFFECT_ITEMS = resEFFECT.data.content;

    // 아바타 정보 가져오기
    const avatarRes = await getAvatarInfo(`${token}`);
    const avatarData = avatarRes.data;

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
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
};

export default CostumePage;
