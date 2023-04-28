// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./MyComponents/style/MyPage";
import MyCobby from "./MyComponents/MyCobby";
import GithubBadge from "./MyComponents/GithubBadge";

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
        <div>user명 : {router.query.userId}</div>
        <GithubBadge />
        <div>
          마이페이지 info
          <div>
            인포 그룹
            <TextBox
              size={20}
              content={
                "Github : https://github.com/baefrica"
              }
            />
            <TextBox
              size={20}
              content={"Nickname : baefrica"}
            />
            <TextBox size={20} content={"Level : 89274"} />
            <TextBox
              size={20}
              content={"Number of Costumes : 500"}
            />
            <TextBox
              size={20}
              content={"Number of Quests Completed : 500"}
            />
          </div>
          <div>LOGOUT</div>
          <div>회원탈퇴</div>
        </div>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyPage;
