import * as style from "@/components/page/MainPage/MainComponents/style/CobbyInfo";

import TextBox from "@/components/common/TextBox/TextBox";
import Cobby from "@/components/common/Cobby/Cobby";
import ExpBar from "./ExpBar";

const CobbyInfo = () => {
  return (
    <style.CobbyInfoWrapper>
      <TextBox size={37} content={"Seungwan97's Cobby"} />
      <style.TextMargin />
      <TextBox size={25} content={"Lv. 10"} />
      <style.Margin />
      <Cobby />
      <ExpBar />
    </style.CobbyInfoWrapper>
  );
};
export default CobbyInfo;
