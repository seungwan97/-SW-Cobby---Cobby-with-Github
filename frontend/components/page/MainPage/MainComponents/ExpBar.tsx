import * as style from "@/components/page/MainPage/MainComponents/style/ExpBar";

import TextBox from "@/components/common/TextBox/TextBox";
import ExpProgress from "./ExpProgress";
interface Props {
  avatarData: {
    level: number;
    exp: number;
    outfits: {
      head: string;
      effect: string;
      body: string;
    };
  };
}
const ExpBar = (props: Props) => {
  const { avatarData } = props;
  return (
    <style.ExpContainer>
      <TextBox size={20} content={"EXP"} />
      <ExpProgress avatarData={avatarData} />
    </style.ExpContainer>
  );
};
export default ExpBar;
