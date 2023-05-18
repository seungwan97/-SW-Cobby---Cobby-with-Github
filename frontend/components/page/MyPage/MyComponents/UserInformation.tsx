import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/UserInformation";

interface MyFuncProps {
  nickname: string;
  githubUrl: string;
  myLevel: number;
  cntCostumes: number;
  cntQuests: number;
  // myNickName: string;
}

const UserInformation = ({
  nickname,
  githubUrl,
  myLevel,
  cntCostumes,
  cntQuests,
}: MyFuncProps) => {
  return (
    <style.UserInfoWrapper>
<<<<<<< HEAD
      <style.InformationTxt>
        <TextBox size={30} content={"INFORMATION"} />
      </style.InformationTxt>
      <style.InfoContent>
        <TextBox size={20} content={`Github : ${githubUrl}`} />
        <TextBox size={20} content={`Nickname : ${nickname}`} />
        <TextBox size={20} content={`Level : ${myLevel}`} />
        <TextBox size={20} content={`Number of Costumes : ${cntCostumes}`} />
=======
      <style.Margin2 />
      <style.InformationTxt>
        <TextBox size={30} content={"INFORMATION"} />
      </style.InformationTxt>
      <style.Margin />
      <style.InfoContent>
        <TextBox size={20} content={`Github : ${githubUrl}`} />
        <style.Margin />
        <TextBox size={20} content={`Nickname : ${nickname}`} />
        <style.Margin />
        <TextBox size={20} content={`Level : ${myLevel}`} />
        <style.Margin />
        <TextBox size={20} content={`Number of Costumes : ${cntCostumes}`} />
        <style.Margin />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
        <TextBox
          size={20}
          content={`Number of Quests Completed : ${cntQuests}`}
        />
      </style.InfoContent>
    </style.UserInfoWrapper>
  );
};
export default UserInformation;
