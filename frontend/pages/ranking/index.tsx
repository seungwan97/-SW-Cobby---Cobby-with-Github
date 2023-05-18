// 유저별 도전과제 페이지
import { useRouter } from "next/router";
import { Fragment } from "react";

//RankingPage
const RankingFunc = () => {
  const router = useRouter();
  return (
    <Fragment>
      <div>RankingPage 입니다.</div>
      <div>user명 : {router.query.userId}</div>
    </Fragment>
  );
};

export default RankingFunc;
