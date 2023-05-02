// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./MyComponents/style/MyPage";
import MyCobby from "./MyComponents/MyCobby";
import GithubBadge from "./MyComponents/GithubBadge";
import UserInformation from "./MyComponents/UserInformation";
import LogoutBtn from "./MyComponents/LogoutBtn";

// MyPage
const MyPage = () => {
  const router = useRouter();

  return (
    <Fragment>
      <page.PageWrapper>
        <style.MyPageTxt>
          <TextBox size={50} content={"MY PAGE"} />
        </style.MyPageTxt>
        <MyCobby />
        <GithubBadge />
        <UserInformation />
        <LogoutBtn />
        <style.LeaveButton>
          <TextBox size={20} content={"Leave Our App"} />
          {/* 이거 LeaveButton에 대해서 반응형 처리 해줘야함. */}
        </style.LeaveButton>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyPage;
