import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";
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
  const arr = [];
  for (let i = 0; i < questData.length; i++) {
    arr.push(questData[i]);
  }
  return (
    <style.QuestListWrapper>
      {arr.map((item, index) => (
        <QuestItem key={index} questData={item} />
      ))}
    </style.QuestListWrapper>
  );
};

export default QuestList;
