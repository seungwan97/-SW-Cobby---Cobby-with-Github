// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";

const DUMMY_DATA = [
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Head/andae.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
  {
    item: "/CostumeItems_IMG/Cloth/soccerplayer.png",
    gifSrc: "/CostumeItems_GIF/Cloth/soccerplayer.gif",
  },
];
// Costumepage
const CostumeFunc = (props: any) => {
  // const router = useRouter();

  return (
    <Fragment>
      <page.PageWrapper>
        <CostumePage itemList={props.itemList} />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export async function getStaticProps() {
  // fetch data for a single meetup

  return {
    props: {
      itemList: DUMMY_DATA,
    },
  };
}

export default CostumePage;
