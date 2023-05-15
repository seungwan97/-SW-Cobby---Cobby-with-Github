// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment, useState, useEffect } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";
import * as style from "./CostumeComponents/style/CostumePage";
import Cobby from "@/components/common/Cobby/Cobby";
import {
  getAvatarInfo,
  patchAvatarInfo,
} from "@/pages/api/main";

// CostumePage
const CostumePage = (props: any) => {
  const [outfits, setOutfits] = useState({
    head: {},
    body: {},
    effect: {},
  });

  useEffect(() => {
    const getCobbyOutfits = async () => {
      const userId = "9302629d-ae6a-43b6-a965-996d5429783c";

      try {
        const res = await getAvatarInfo(userId);
        const cobbyOutfits = res.data.content.outfits;

        setOutfits(cobbyOutfits);
      } catch (error) {
        console.error(
          "Failed to fetch avatar info:",
          error
        );
      }
    };

    getCobbyOutfits();
  }, []);

  const handleInventoryItem = (itemInfo: any) => {
    setOutfits((prevOutfits: any) => {
      let updatedOutfits = { ...prevOutfits };

      if (itemInfo.category === "HEAD") {
        updatedOutfits.head = itemInfo;
      } else if (itemInfo.category === "BODY") {
        updatedOutfits.body = itemInfo;
      } else if (itemInfo.category === "EFFECT") {
        updatedOutfits.effect = itemInfo;
      }

      // 코비 정보 수정
      const userId = "9302629d-ae6a-43b6-a965-996d5429783c";
      const data = {
        head: updatedOutfits.head.costumeId,
        body: updatedOutfits.body.costumeId,
        effect: updatedOutfits.effect.costumeId,
      };

      patchAvatarInfo(userId, data).then((res) => {
        console.log(res.data.content);
      });

      return updatedOutfits;
    });
  };

  return (
    <Fragment>
      <page.PageWrapper>
        <style.CostumePageTextWrapper>
          <TextBox size={50} content={"COSTUME"} />
        </style.CostumePageTextWrapper>
        <style.CostumedCobby>
          <Cobby outfits={outfits} />
        </style.CostumedCobby>
        <Inventory
          headItemList={props.HEAD_ITEMS}
          bodyItemList={props.BODY_ITEMS}
          effectItemList={props.EFFECT_ITEMS}
          outfits={outfits}
          onItemClick={handleInventoryItem}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
