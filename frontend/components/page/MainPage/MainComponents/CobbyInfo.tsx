import * as style from "@/components/page/MainPage/MainComponents/style/CobbyInfo";

import TextBox from "@/components/common/TextBox/TextBox";
import Cobby from "@/components/common/Cobby/Cobby";
import ExpBar from "./ExpBar";
interface Props {
  nicknameData: {
    nickname: string;
  };
  avatarData: {
    level: number;
    exp: number;
    nextExp: number;
    outfits: {
      head: any;
      effect: any;
      body: any;
    };
  };
}
const CobbyInfo = (props: Props) => {
  const { nicknameData } = props;
  const { avatarData } = props;

  return (
    <style.CobbyInfoWrapper>
      <TextBox
        size={37}
        content={`${nicknameData.nickname}'s Cobby`}
      />
      <style.TextMargin />
      <TextBox
        size={25}
        content={`Lv. ${avatarData.level}`}
      />
      <style.Margin />
      <Cobby outfits={avatarData.outfits} />
      <ExpBar avatarData={avatarData} />
    </style.CobbyInfoWrapper>
  );
};

export default CobbyInfo;
