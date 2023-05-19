import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";
import { getQuestItem } from "@/pages/api/main";
import cookie from "react-cookies";
interface Props {
  progress: number;
  questId: number;
  modifyData: (qId: number) => void;
}
const QuestConfirmButton = (props: Props) => {
  const { progress } = props;
  const { questId } = props;
  const { modifyData } = props;
  return (
    <style.ConfirmButtonWrapper>
      {progress !== 100 && (
        <style.ConfirmButton
          color="#F2F2F2"
          cursor={"default"}
          width={80}
          height={35}
          transition={"0"}
          x={0}
        >
          <TextBox size={15} content={"Get an Item"} />
        </style.ConfirmButton>
      )}
      {progress === 100 && (
        <style.ConfirmButton
          color="#A0FF72"
          cursor={"pointer"}
          width={80}
          height={35}
          transition={"0.3"}
          x={3}
          onClick={() => modifyData(questId)}
        >
          <TextBox size={15} content={"Get an Item"} />
        </style.ConfirmButton>
      )}
    </style.ConfirmButtonWrapper>
  );
};

export default QuestConfirmButton;
