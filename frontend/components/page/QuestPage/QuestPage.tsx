import { Fragment } from "react";
import QuestList from "./QuestComponents/QuestList";

const QuestPage = (props: any) => {
  return (
    <Fragment>
      <QuestList QuestItemList={props.QuestItemList} />
    </Fragment>
  );
};

export default QuestPage;
