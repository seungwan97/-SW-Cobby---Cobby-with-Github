import { useEffect, useState } from "react";
import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";
import cookie from "react-cookies";
import { getQuestItem, getQuests } from "@/pages/api/main";
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
  // const [data, setData] = useState(questData[0].questId);
  const modifyData = async (qId: number) => {
    const token = cookie.load("Authorization");
    //아이템수령api쏴주고
    const res = await getQuestItem(token, qId);
    const data = res.data.content;
    console.log(data);
    //새로운 퀘스트로 갱신됐을 것이니 전체리스트api 다시 쏴주고
    const questRes = await getQuests(token);
    const questData = questRes.data.content;
    console.log(questData);
    for (let i = 0; i < questData.length; i++) {
      if (questData[i].questId === -1) {
        questData.splice(i, 1);
      }
    }
    console.log(questData);

    //useState 배열에 저장
    setArr(questData);
  };

  return (
    <style.QuestListWrapper>
      {arr.map((item, index) => (
        <QuestItem key={index} questData={item} modifyData={modifyData} />
      ))}
    </style.QuestListWrapper>
  );
};

export default QuestList;
