import * as style from "./style/QuestPage";
import TextBox from "@/components/common/TextBox/TextBox";

const QuestConfirmButton = (props: any) => {
  return (
    <style.ConfirmButtonWrapper>
      <style.ConfirmButton>
        <TextBox size={15} content={"Get an Item"} />
      </style.ConfirmButton>
    </style.ConfirmButtonWrapper>
  );
};

export default QuestConfirmButton;
