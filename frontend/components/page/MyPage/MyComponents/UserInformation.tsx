import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/UserInformation";

interface MyFuncProps {
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
  // myNickName: string;
}

const UserInformation = ({
  myLevel,
  cntCostumes,
  cntQuests,
}: // myNickName,
MyFuncProps) => {
  return (
    <style.UserInfoWrapper>
      <style.InformationTxt>
        <TextBox size={30} content={"INFORMATION"} />
      </style.InformationTxt>
      <style.InfoContent>
        <TextBox
          size={20}
          content={"Github : https://github.com/baefrica"}
        />
        <TextBox
          size={20}
          content={"Nickname : baefrica"}
        />
        <TextBox size={20} content={`Level : ${myLevel}`} />
        <TextBox
          size={20}
          content={`Number of Costumes : ${cntCostumes}`}
        />
        <TextBox
          size={20}
          content={`Number of Quests Completed : ${cntQuests}`}
        />
      </style.InfoContent>
    </style.UserInfoWrapper>
  );
};
export default UserInformation;
