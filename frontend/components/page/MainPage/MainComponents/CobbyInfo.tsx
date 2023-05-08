import * as style from "@/components/page/MainPage/MainComponents/style/CobbyInfo";

import TextBox from "@/components/common/TextBox/TextBox";
import Cobby from "@/components/common/Cobby/Cobby";
import ExpBar from "./ExpBar";
interface Props {
  nicknameData: {
    nickname: string;
  };
}
const CobbyInfo = (props: Props) => {
  const { nicknameData } = props;
  return (
    <style.CobbyInfoWrapper>
      <TextBox size={37} content={`${nicknameData.nickname}'s Cobby`} />
      <style.TextMargin />
      <TextBox size={25} content={"Lv. 10"} />
      <style.Margin />
      <Cobby />
      <ExpBar />
    </style.CobbyInfoWrapper>
  );
};
export default CobbyInfo;
