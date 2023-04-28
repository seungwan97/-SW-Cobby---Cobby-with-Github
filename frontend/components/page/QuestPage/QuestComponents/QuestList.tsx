import QuestItem from "./QuestItem";

const QuestList = (props: any) => {
  return (
    <div>
      {props.QuestItemList.map((item: object, index: number) => (
        <QuestItem key={index} item={item} />
      ))}
    </div>
  );
};

export default QuestList;
