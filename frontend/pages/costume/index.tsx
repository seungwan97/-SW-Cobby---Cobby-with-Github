// 유저별 코스튬 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import CostumePage from "@/components/page/CostumePage/CostumePage";

const DUMMY_DATA = [
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
  {
    item: "/Character/Cobby.png",
  },
];
// Costumepage
const CostumeFunc = (props: any) => {
  const router = useRouter();
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
