import * as style from "@/components/page/MainPage/MainComponents/style/CobbyInfo";

import TextBox from "@/components/common/TextBox/TextBox";
import Cobby from "@/components/common/Cobby/Cobby";

const CobbyInfo = () => {
  return (
    <>
      <TextBox size={30} content={"Seungwan97's Cobby"} />
      <style.TextMargin />
      <TextBox size={20} content={"Lv. 10"} />
      <style.Margin />
      <Cobby />
      <style.Margin />
    </>
  );
};
export default CobbyInfo;
