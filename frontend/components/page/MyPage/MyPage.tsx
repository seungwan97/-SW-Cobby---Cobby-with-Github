// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment, useState } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./MyComponents/style/MyPage";
import MyCobby from "./MyComponents/MyCobby";
import GithubBadge from "./MyComponents/GithubBadge";
import UserInformation from "./MyComponents/UserInformation";
import LogoutBtn from "./MyComponents/LogoutBtn";
import Modal from "@/components/common/Modal/Modal";
import CopyAlarm from "@/components/common/CopyAlarm/CopyAlarm";

interface MyFuncProps {
  nickname: string;
  githubUrl: string;
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
}

// MyPage
const MyPage = ({
  nickname,
  githubUrl,
  myLevel,
  cntCostumes,
  cntQuests,
}: MyFuncProps) => {
  // const router = useRouter();

  const [isLogout, setIsLogout] = useState(false);
  const [isLeave, setIsLeave] = useState(false);
  const [isCopied, setIsCopied] = useState(false);

  const setLogout = (val: boolean) => {
    setIsLogout(val);
  };

  const setLeave = () => {
    setIsLeave(true);
  };

  return (
    <Fragment>
      {isLogout && (
        <Modal
          name={""}
          name2={"로그아웃 하시겠습니까?"}
          yes={true}
          no={true}
          setStatus={setIsLogout}
        />
      )}
      {isLeave && (
        <Modal
          name={""}
          name2={"회원탈퇴 하시겠습니까?"}
          yes={true}
          no={true}
          setStatus={setIsLeave}
        />
      )}
      <page.PageWrapper>
        <style.MyPageTxt>
          <TextBox size={50} content={"MY PAGE"} />
        </style.MyPageTxt>
        <style.Cobby />
        <GithubBadge setIsCopied={setIsCopied} />
        <CopyAlarm isVisible={isCopied} setIsVisible={setIsCopied} />
        <UserInformation
          nickname={nickname}
          githubUrl={githubUrl}
          myLevel={myLevel}
          cntCostumes={cntCostumes}
          cntQuests={cntQuests}
        />
        <LogoutBtn setLogout={setLogout} />
        <style.LeaveButton onClick={setLeave}>
          <TextBox size={20} content={"Leave Our App"} />
          {/* 이거 LeaveButton에 대해서 반응형 처리 해줘야함. */}
        </style.LeaveButton>
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyPage;
