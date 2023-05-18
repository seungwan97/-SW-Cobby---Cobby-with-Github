// 로그인 정보 있으면 메인페이지로 리다이렉트 해줘야함
// 로그인 정보 어떻게 저장할 것인지
// 모바일 환경 => ?
// 브라우조 환경 => 쿠키
// ?
import { Fragment } from "react";
import * as style from "@/components/page/LoginPage/LoginComponents/style/LoginPage";

import TextBox from "@/components/common/TextBox/TextBox";
import Cobby from "@/components/common/Cobby/Cobby";
import GithubLoginButton from "@/components/page/LoginPage/LoginComponents/GithubLoginButton";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";

const LoginPage = () => {
  const outfits = {
    head: {
      costumeId: 0,
      name: "empty",
      category: "head",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
    body: {
      costumeId: 0,
      name: "empty",
      category: "body",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
    effect: {
      costumeId: 0,
      name: "empty",
      category: "effect",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
  };

  return (
    <style.LoginPageWrapper>
      <style.LoginPageContent>
        <TextBox size={50} content={"Cobby"} />
        <style.LogoMargin />
        <Cobby outfits={outfits} />
        <style.LogoMargin />
        <TextBox size={30} content={"Github with Cobby"} />
      </style.LoginPageContent>
      <style.LoginBtnMargin />
      <GithubLoginButton />
    </style.LoginPageWrapper>
  );
};
export default LoginPage;
