import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";
interface Props {
  progress: number;
}
const QuestConfirmButton = (props: Props) => {
  const { progress } = props;
  return (
    <style.ConfirmButtonWrapper>
      {progress !== 100 && (
        <style.ConfirmButton color="#F2F2F2">
          <TextBox size={15} content={"Get an Item"} />
        </style.ConfirmButton>
      )}
      {progress === 100 && (
        <style.ConfirmButton color="#A0FF72">
          <TextBox size={15} content={"Get an Item"} />
        </style.ConfirmButton>
      )}
    </style.ConfirmButtonWrapper>
  );
};

export default QuestConfirmButton;
