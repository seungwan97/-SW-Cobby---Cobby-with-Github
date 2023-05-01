import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/UserInformation";

const UserInformation = () => {
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
        <TextBox size={20} content={"Level : 89274"} />
        <TextBox
          size={20}
          content={"Number of Costumes : 500"}
        />
        <TextBox
          size={20}
          content={"Number of Quests Completed : 500"}
        />
      </style.InfoContent>
    </style.UserInfoWrapper>
  );
};
export default UserInformation;
