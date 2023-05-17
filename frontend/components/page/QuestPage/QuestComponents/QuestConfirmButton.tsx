import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";
import { getQuestItem } from "@/pages/api/main";
import cookie from "react-cookies";
interface Props {
  progress: number;
  questId: number;
}
const QuestConfirmButton = (props: Props) => {
  const { progress } = props;
  const { questId } = props;
  const getItem = async () => {
    const token = cookie.load("Authorization");
    const res = await getQuestItem(token, questId);
    console.log(res);
  };
  return (
    <style.ConfirmButtonWrapper>
      {progress !== 100 && (
        <style.ConfirmButton
          color="#F2F2F2"
          cursor={"default"}
          width={70}
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
          width={70}
          height={35}
          transition={"0.3"}
          x={3}
          onClick={getItem}
        >
          <TextBox size={15} content={"Get an Item"} />
        </style.ConfirmButton>
      )}
    </style.ConfirmButtonWrapper>
  );
};

export default QuestConfirmButton;
