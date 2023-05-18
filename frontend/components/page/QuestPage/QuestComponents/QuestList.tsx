<<<<<<< HEAD
import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";
=======
import { useEffect, useState } from "react";
import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";
import cookie from "react-cookies";
import { getQuestItem, getQuests } from "@/pages/api/main";
import TextBox from "@/components/common/TextBox/TextBox";
import { log } from "console";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
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
<<<<<<< HEAD
  const arr = [];
  for (let i = 0; i < questData.length; i++) {
    arr.push(questData[i]);
  }
  return (
    <style.QuestListWrapper>
      {arr.map((item, index) => (
        <QuestItem key={index} questData={item} />
      ))}
=======
  const [arr, setArr] = useState(questData);
  const [data, setData] = useState(false);

  useEffect(() => {
    renderData();
  }, []);

  const renderData = async () => {
    const token = cookie.load("Authorization");
    const questRes = await getQuests(token);
    const questData = questRes.data.content;
    let cnt = 0;
    let idx = 0;
    for (let i = 0; i < 4; i++) {
      if (questData[idx].questId === -1) {
        questData.splice(idx, 1);
        cnt += 1;
        continue;
      }
      idx++;
    }
    setArr(questData);
    if (cnt === 4) {
      setData(true);
    }
  };

  const modifyData = async (qId: number) => {
    setData(false);
    const token = cookie.load("Authorization");
    //아이템수령api쏴주고
    const res = await getQuestItem(token, qId);
    const data = res.data.content;
    //새로운 퀘스트로 갱신됐을 것이니 전체리스트api 다시 쏴주고
    const questRes = await getQuests(token);
    const questData = questRes.data.content;
    let cnt = 0;
    let idx = 0;
    for (let i = 0; i < 4; i++) {
      if (questData[idx].questId === -1) {
        questData.splice(idx, 1);
        cnt += 1;
        continue;
      }
      idx++;
    }

    //useState 배열에 저장
    setArr(questData);
    if (cnt === 4) {
      setData(true);
    }
  };

  return (
    <style.QuestListWrapper>
      {arr.map((item, index) => (
        <QuestItem key={index} questData={item} modifyData={modifyData} />
      ))}
      {data && (
        <div>
          <style.Margin />
          <TextBox size={30} content={"모든 퀘스트를 완료했습니다!"} />
        </div>
      )}
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    </style.QuestListWrapper>
  );
};

export default QuestList;
