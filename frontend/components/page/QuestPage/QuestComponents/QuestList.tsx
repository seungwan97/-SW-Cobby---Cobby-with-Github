import { useEffect, useState } from "react";
import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";
import cookie from "react-cookies";
import { getQuestItem, getQuests } from "@/pages/api/main";
import TextBox from "@/components/common/TextBox/TextBox";
import { log } from "console";
interface Props {
  questData: [
    {
      questId: number;
      questName: string;
      questType: string;
      questGoal: number;
      progress: number;
      award: {
        costumeId: number;
        name: string;
        category: string;
        questId: number;
        imgUrl: string;
        gifUrl: string;
      };
    },
    {
      questId: number;
      questName: string;
      questType: string;
      questGoal: number;
      progress: number;
      award: {
        costumeId: number;
        name: string;
        category: string;
        questId: number;
        imgUrl: string;
        gifUrl: string;
      };
    },
    {
      questId: number;
      questName: string;
      questType: string;
      questGoal: number;
      progress: number;
      award: {
        costumeId: number;
        name: string;
        category: string;
        questId: number;
        imgUrl: string;
        gifUrl: string;
      };
    },
    {
      questId: number;
      questName: string;
      questType: string;
      questGoal: number;
      progress: number;
      award: {
        costumeId: number;
        name: string;
        category: string;
        questId: number;
        imgUrl: string;
        gifUrl: string;
      };
    }
  ];
}
const QuestList = (props: Props) => {
  const { questData } = props;
  const [arr, setArr] = useState(questData);
  const [data, setData] = useState(false);
  useEffect(() => {
    let cnt = 0;
    for (let i = 0; i < questData.length; i++) {
      console.log(questData[i].questId);
      console.log(questData.length);

      if (questData[i].questId === -1) {
        cnt += 1;
      }
    }
    if (cnt == 4) {
      setData(true);
      console.log("퀘스트없음");
    }
  }, []);
  const modifyData = async (qId: number) => {
    setData(false);
    const token = cookie.load("Authorization");
    //아이템수령api쏴주고
    const res = await getQuestItem(token, qId);
    const data = res.data.content;
    console.log(data);
    //새로운 퀘스트로 갱신됐을 것이니 전체리스트api 다시 쏴주고
    const questRes = await getQuests(token);
    const questData = questRes.data.content;
    console.log(questData);
    let cnt = 0;
    for (let i = 0; i < questData.length; i++) {
      if (questData[i].questId === -1) {
        questData.splice(i, 1);
        cnt += 1;
      }
    }
    if (cnt == 4) {
      setData(true);
    }
    console.log(questData);

    //useState 배열에 저장
    setArr(questData);
  };

  return (
    <style.QuestListWrapper>
      {!data &&
        arr.map((item, index) => (
          <QuestItem key={index} questData={item} modifyData={modifyData} />
        ))}
      {data && (
        <div>
          <style.Margin />
          <TextBox size={30} content={"모든 퀘스트를 완료하였습니다!"} />
        </div>
      )}
    </style.QuestListWrapper>
  );
};

export default QuestList;
