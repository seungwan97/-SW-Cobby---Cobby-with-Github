// 로그인 정보 있으면 메인페이지로 리다이렉트
import TextBox from "@/components/box/TextBox";
import Cobby from "@/components/character/Cobby";
import { Fragment } from "react";
import GithubLoginButton from "@/components/buttons/GithubLoginButton";
import * as style from "./style/LoginPage/LoginPage";

export default function Home() {
  return (
    <Fragment>
      <style.LoginPageWrapper>
        <style.LoginPageContent>
          <TextBox size={50} content={"COBBY"} />
          <style.LogoMargin />
          <Cobby />
          <style.LogoMargin />
          <TextBox size={30} content={"Github with Cobby"} />
        </style.LoginPageContent>
        <GithubLoginButton />
      </style.LoginPageWrapper>
    </Fragment>
  );
}
