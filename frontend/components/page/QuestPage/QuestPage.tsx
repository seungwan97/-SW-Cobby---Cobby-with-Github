import { Fragment } from "react";
import QuestList from "./QuestComponents/QuestList";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./QuestComponents/style/QuestPage";

const QuestPage = (props: any) => {
  return (
    <style.QuestPageWrapper>
      <style.QuestPageTextWrapper>
        <TextBox size={50} content={"Quest"} />
      </style.QuestPageTextWrapper>
      <QuestList QuestItemList={props.QuestItemList} />
    </style.QuestPageWrapper>
  );
};

export default QuestPage;
