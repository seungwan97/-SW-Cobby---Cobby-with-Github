// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MyPage from "@/components/page/MyPage/MyPage";
import { GetServerSideProps } from "next";
import { getAvatarInfo } from "../api/main";
import { getNicknameAndGithubURL } from "../api/user";
import { InferGetServerSidePropsType } from "next";

const MyFunc = ({
  nickname,
  githubUrl,
  myLevel,
  cntCostumes,
  cntQuests,
  cobbyOutfits,
  error,
}: InferGetServerSidePropsType<typeof getServerSideProps>) => {
  const router = useRouter();

  if (error) {
    // 오류 처리 로직
    alert("페이지에 접근할 수 없습니다. 다시 로그인해주세요");
    router.push("/");
    return;
  }

  return (
    <Fragment>
      <page.PageWrapper>
        <MyPage
          nickname={nickname}
          githubUrl={githubUrl}
          myLevel={myLevel}
          cntCostumes={cntCostumes}
          cntQuests={cntQuests}
          cobbyOutfits={cobbyOutfits}
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyFunc;

export const getServerSideProps: GetServerSideProps = async (context) => {
  try {
    const token = context.req.headers.cookie?.replace("Authorization=", "");
    // 닉네임, 깃허브url
    const res = await getNicknameAndGithubURL(`${token}`);

    // 코비 정보 : 레벨, 갖고있는 코스튬 수, 달성한 퀘스트 수
    const cobbyInfo = await getAvatarInfo(`${token}`);

    const nickname = res.data.content.nickname;
    const githubUrl = res.data.content.githubUrl;
    const myLevel = cobbyInfo.data.content.level;
    const cntCostumes = cobbyInfo.data.content.costumes.length;
    const cntQuests = cobbyInfo.data.content.quests.length;
    const cobbyOutfits = cobbyInfo.data.content.outfits;

    return {
      props: {
        nickname,
        githubUrl,
        myLevel,
        cntCostumes,
        cntQuests,
        cobbyOutfits,
      },
    };
  } catch (e) {
    return {
      props: {
        error: "An error occurred",
      },
    };
  }
};
