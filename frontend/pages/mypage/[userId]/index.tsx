// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar";
import * as page from "@/pages/style/Page";
import TextBox from "@/components/box/TextBox";

//MyPage
const MyPage = () => {
  const router = useRouter();
  return (
    <Fragment>
      <page.PageWrapper>
        <TextBox size={50} content={"MY PAGE"} />
        <div>자기 코비가 배경뒤로 걸어가야한다잉</div>
        <div>user명 : {router.query.userId}</div>
        <div>
          뱃지 컴포넌트야
          <TextBox
            size={20}
            content={"#Github 어쩌고 저쩌꼬"}
          />
          <button>Copy Btn</button>
        </div>
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
