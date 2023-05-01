import * as style from "@/components/page/MainPage/MainComponents/style/CurrentCommit";
import TextBox from "@/components/common/TextBox/TextBox";

const CurrentCommit = () => {
  return (
    <style.CommitContainer>
      <TextBox size={23} content={"Seungwan97 made 5 commits today."} />
    </style.CommitContainer>
  );
};
export default CurrentCommit;
