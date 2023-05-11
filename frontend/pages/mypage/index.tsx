// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MyPage from "@/components/page/MyPage/MyPage";
import { GetServerSideProps } from "next";
import { getAvatarInfo } from "../api/main";
import { getNickname } from "../api/user";

interface MyFuncProps {
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
  // myNickName: string;
}

const MyFunc = ({
  myLevel,
  cntCostumes,
  cntQuests,
}: // myNickName,
MyFuncProps) => {
  const router = useRouter();

  return (
    <Fragment>
      <page.PageWrapper>
        <MyPage
          myLevel={myLevel}
          cntCostumes={cntCostumes}
          cntQuests={cntQuests}
          // myNickName={myNickName}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyFunc;

export const getServerSideProps: GetServerSideProps<
  MyFuncProps
> = async (context) => {
  const userId = "9302629d-ae6a-43b6-a965-996d5429783c";

  // 코비 정보 : 레벨, 갖고있는 코스튬 수, 달성한 퀘스트 수
  const cobbyInfo = await getAvatarInfo(userId);

  // 깃허브 주소
  // const githubUrl = await getNickname(userId);
  // console.log(githubUrl);

  // const nickNameRes = await getNickname(userId);
  // console.log(nickNameRes);

  let myLevel = 0;
  let cntCostumes = 0;
  let cntQuests = 0;
  // let myNickName = "닉네임 없음";

  if (cobbyInfo.status === 200) {
    myLevel = cobbyInfo.data.content.level;
    cntCostumes = cobbyInfo.data.content.costumes.length;
    cntQuests = cobbyInfo.data.content.quests.length;
  }

  // if (nickNameRes.status === 200) {
  //   myNickName = nickNameRes.data.content.nickname;
  // }

  return {
    props: {
      myLevel,
      cntCostumes,
      cntQuests,
      // myNickName,
    },
  };
};
