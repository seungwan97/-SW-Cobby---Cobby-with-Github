// 로그인 정보 있으면 메인페이지로 리다이렉트 해줘야함
// 로그인 정보 어떻게 저장할 것인지
// 모바일 환경 => ?
// 브라우조 환경 => 쿠키
// ?
import TextBox from "@/components/box/TextBox";
import Cobby from "@/components/character/Cobby";
import { Fragment } from "react";
import GithubLoginButton from "@/components/buttons/GithubLoginButton";
import * as style from "./style/LoginPage/LoginPage";
import * as page from "./style/Page";
import BottomNavBar from "@/components/layout/BottomNavBar";

export default function Home() {
  return (
    <Fragment>
      <page.PageWrapper>
        <style.LoginPageContent>
          <TextBox size={50} content={"Cobby"} />
          <style.LogoMargin />
          <Cobby />
          <style.LogoMargin />
          <TextBox size={30} content={"Github with Cobby"} />
        </style.LoginPageContent>
        <style.LoginBtnMargin />
        <GithubLoginButton />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
}
