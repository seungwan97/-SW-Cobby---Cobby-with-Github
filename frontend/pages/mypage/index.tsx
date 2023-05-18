// 유저별 마이페이지
import { useRouter } from "next/router";
import { Fragment } from "react";
import BottomNavBar from "@/components/layout/BottomNavBar/BottomNavBar";
import * as page from "@/components/layout/PageWrapper/style/PageWrapper";
import MyPage from "@/components/page/MyPage/MyPage";
import { GetServerSideProps } from "next";
import { getAvatarInfo } from "../api/main";
import { getNicknameAndGithubURL } from "../api/user";
<<<<<<< HEAD

interface MyFuncProps {
  nickname: string;
  githubUrl: string;
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
  // myNickName: string;
}
=======
import { InferGetServerSidePropsType } from "next";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

const MyFunc = ({
  nickname,
  githubUrl,
  myLevel,
  cntCostumes,
  cntQuests,
<<<<<<< HEAD
}: // myNickName,
MyFuncProps) => {
  const router = useRouter();

=======
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

>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  return (
    <Fragment>
      <page.PageWrapper>
        <MyPage
          nickname={nickname}
          githubUrl={githubUrl}
          myLevel={myLevel}
          cntCostumes={cntCostumes}
          cntQuests={cntQuests}
<<<<<<< HEAD
          // myNickName={myNickName}
=======
          cobbyOutfits={cobbyOutfits}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        />
      </page.PageWrapper>
      <BottomNavBar />
    </Fragment>
  );
};

export default MyFunc;

<<<<<<< HEAD
export const getServerSideProps: GetServerSideProps<MyFuncProps> = async (
  context
) => {
  const token = context.req.headers.cookie?.replace("Authorization=", "");
  // 닉네임, 깃허브url
  const res = await getNicknameAndGithubURL(`${token}`);

  // 코비 정보 : 레벨, 갖고있는 코스튬 수, 달성한 퀘스트 수
  const cobbyInfo = await getAvatarInfo(`${token}`);

  let nickname = "";
  let githubUrl = "";
  let myLevel = 0;
  let cntCostumes = 0;
  let cntQuests = 0;

  if (cobbyInfo.status === 200) {
    nickname = res.data.content.nickname;
    githubUrl = res.data.content.githubUrl;
    myLevel = cobbyInfo.data.content.level;
    cntCostumes = cobbyInfo.data.content.costumes.length;
    cntQuests = cobbyInfo.data.content.quests.length;
  }

  return {
    props: { nickname, githubUrl, myLevel, cntCostumes, cntQuests },
  };
=======
export const getServerSideProps: GetServerSideProps = async (context) => {
  try {

    const cookieString: any = context.req.headers.cookie?.split("; ");
    let result: any = {};

    for (var i = 0; i < cookieString.length; i++) {
      var cur = cookieString[i].split("=");
      result[cur[0]] = cur[1];
    }
    const token = result["Authorization"];

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
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
};
