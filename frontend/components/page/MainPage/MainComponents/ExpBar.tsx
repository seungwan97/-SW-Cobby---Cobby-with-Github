import * as style from "@/components/page/MainPage/MainComponents/style/ExpBar";

import TextBox from "@/components/common/TextBox/TextBox";
import ExpProgress from "./ExpProgress";

const ExpBar = () => {
  return (
    <style.ExpContainer>
      <TextBox size={20} content={"EXP"} />
      <ExpProgress />
    </style.ExpContainer>
  );
};
export default ExpBar;
