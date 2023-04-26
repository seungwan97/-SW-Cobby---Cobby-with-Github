// 곧 메인페이지로 쓰일 예정
// 로그인 정보 없으면 로그인 페이지로 리다이렉트
// import { Inter } from "next/font/google";

import TextBox from "@/components/box/TextBox";
import Cobby from "@/components/character/Cobby";
import { Fragment } from "react";
import GithubLoginButton from "@/components/buttons/GithubLoginButton";
import * as style from "./style/LoginPage/LoginPage";

// const inter = Inter({ subsets: ["latin"] });

export default function Home() {
  return (
    <Fragment>
      <style.LoginPageWrapper>
        <Cobby />
        <TextBox size={37} content={"COBBY"} />
        <TextBox size={30} content={"Github with Cobby"} />
        <GithubLoginButton />
      </style.LoginPageWrapper>
    </Fragment>
  );
}
