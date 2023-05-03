import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/LogoutBtn";

const LogoutBtn = () => {
  return (
    <style.LogoutBtnWrapper>
      <style.LogoutIcon src="/logouticon.png" />
      <style.LogoutTxt>
        <TextBox size={30} content={"LOGOUT"} />
      </style.LogoutTxt>
    </style.LogoutBtnWrapper>
  );
};
export default LogoutBtn;
