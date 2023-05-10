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
    item: "/CostumeItems_IMG/Head/andae.png",
    gifSrc: "/CostumeItems_GIF/Head/andae.gif",
  },
  {
    item: "/CostumeItems_IMG/Head/rudolf.png",
    gifSrc: "/CostumeItems_GIF/Head/rudolf.gif",
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

export const getServerSideProps: GetServerSideProps =
  async (context) => {
    const userId = "9302629d-ae6a-43b6-a965-996d5429783c";
    const token = "token";

    return {
      props: {
        nicknameData: nicknameData.content,
        statusData: statusData.content,
      },
    };
  };
