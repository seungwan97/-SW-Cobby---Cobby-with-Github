// 곧 메인페이지로 쓰일 예정
// 로그인 정보 없으면 로그인 페이지로 리다이렉트
// import { Inter } from "next/font/google";

import TextBox from "@/components/box/TextBox";
import ItemBox from "@/components/box/ItemBox";
import BottomNavBar from "@/components/layout/BottomNavBar";
import { Fragment } from "react";

// const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  return (
    <Fragment>
      <TextBox size={50} content={"커피 먹고 싶드아"} />
      <ItemBox />
      <BottomNavBar />
    </Fragment>
  );
}
