import QuestItem from "./QuestItem";
import * as style from "./style/QuestPage";

const QuestList = (props: any) => {
  return (
    <style.QuestListWrapper>
      {props.QuestItemList.map((item: object, index: number) => (
        <QuestItem key={index} item={item} />
      ))}
    </style.QuestListWrapper>
  );
};

export default QuestList;
