// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment, useState } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import TextBox from "@/components/common/TextBox/TextBox";
import Inventory from "./CostumeComponents/Inventory";
import * as style from "./CostumeComponents/style/CostumePage";
import Cobby from "@/components/common/Cobby/Cobby";

// CostumePage
const CostumePage = (props: any) => {
  // const router = useRouter();

  const [outfits, setOutfits] = useState(
    props.myCobbyOutfits
  );

  const handleInventoryItem = (itemInfo: any) => {
    // console.log(itemInfo);

    setOutfits((prevOutfits: any) => {
      let updatedOutfits = { ...prevOutfits };

      if (itemInfo.category === "HEAD") {
        updatedOutfits.head = itemInfo.gifUrl;
      } else if (itemInfo.category === "BODY") {
        updatedOutfits.body = itemInfo.gifUrl;
      } else if (itemInfo.category === "EFFECT") {
        updatedOutfits.effect = itemInfo.gifUrl;
      }

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
          onItemClick={handleInventoryItem}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
