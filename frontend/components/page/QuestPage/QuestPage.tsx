import { Fragment } from "react";
import QuestList from "./QuestComponents/QuestList";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./QuestComponents/style/QuestPage";

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
const QuestPage = (props: Props) => {
  const { questData } = props;
  return (
    <style.QuestPageWrapper>
      <style.QuestPageTextWrapper>
        <TextBox size={50} content={"Quest"} />
      </style.QuestPageTextWrapper>
      <QuestList questData={questData} />
    </style.QuestPageWrapper>
  );
};

export default QuestPage;
