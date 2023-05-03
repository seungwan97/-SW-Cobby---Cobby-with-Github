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
  const [costumeGifSrc, setCostumeGifSrc] = useState("");

  const handleInventoryItem = (gifSrc: string) => {
    setCostumeGifSrc(gifSrc);
  };

  return (
    <Fragment>
      <page.PageWrapper>
        <style.CostumePageTextWrapper>
          <TextBox size={50} content={"COSTUME"} />
        </style.CostumePageTextWrapper>
        <style.CostumedCobby>
          <Cobby gifSrc={costumeGifSrc} />
        </style.CostumedCobby>
        <Inventory
          itemList={props.itemList}
          onItemClick={handleInventoryItem}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default CostumePage;
