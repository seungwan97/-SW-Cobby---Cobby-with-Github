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
<<<<<<< HEAD
=======
import cookie from "react-cookies";
import { doSignOut } from "@/pages/api/user";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

interface MyFuncProps {
  nickname: string;
  githubUrl: string;
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
<<<<<<< HEAD
=======
  cobbyOutfits: {
    head: any;
    effect: any;
    body: any;
  };
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
}

// MyPage
const MyPage = ({
  nickname,
  githubUrl,
  myLevel,
  cntCostumes,
  cntQuests,
<<<<<<< HEAD
}: MyFuncProps) => {
  // const router = useRouter();

=======
  cobbyOutfits,
}: MyFuncProps) => {
  // const router = useRouter();
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  const [isLogout, setIsLogout] = useState(false);
  const [isLeave, setIsLeave] = useState(false);
  const [isCopied, setIsCopied] = useState(false);

<<<<<<< HEAD
=======
  const router = useRouter();

>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  const setLogout = (val: boolean) => {
    setIsLogout(val);
  };

  const setLeave = () => {
    setIsLeave(true);
  };

<<<<<<< HEAD
=======
  const doLogout = () => {
    cookie.remove("Authorization");
    router.push("/");
  };

  const doLeave = () => {
    doSignOut(cookie.load("Authorization"));
    doLogout();
  };

>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  return (
    <Fragment>
      {isLogout && (
        <Modal
          name={""}
          name2={"로그아웃 하시겠습니까?"}
          yes={true}
          no={true}
          setStatus={setIsLogout}
<<<<<<< HEAD
=======
          confirmMethod={doLogout}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        />
      )}
      {isLeave && (
        <Modal
          name={""}
          name2={"회원탈퇴 하시겠습니까?"}
          yes={true}
          no={true}
          setStatus={setIsLeave}
<<<<<<< HEAD
=======
          confirmMethod={doLeave}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        />
      )}
      <page.PageWrapper>
        <style.MyPageTxt>
          <TextBox size={50} content={"MY PAGE"} />
        </style.MyPageTxt>
        <style.Cobby />
<<<<<<< HEAD
        <GithubBadge setIsCopied={setIsCopied} />
=======
        <GithubBadge nickname={nickname} setIsCopied={setIsCopied} />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        <CopyAlarm isVisible={isCopied} setIsVisible={setIsCopied} />
        <UserInformation
          nickname={nickname}
          githubUrl={githubUrl}
          myLevel={myLevel}
          cntCostumes={cntCostumes}
          cntQuests={cntQuests}
        />
        <LogoutBtn setLogout={setLogout} />
<<<<<<< HEAD
        <style.LeaveButton onClick={setLeave}>
          <TextBox size={20} content={"Leave Our App"} />
          {/* 이거 LeaveButton에 대해서 반응형 처리 해줘야함. */}
        </style.LeaveButton>
=======
        {/* <style.LeaveButton onClick={setLeave}>
          <TextBox size={20} content={"Leave Our App"} />
        </style.LeaveButton> */}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyPage;
