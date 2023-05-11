import { Fragment, useState } from "react";
// import Modal from "@/components/common/Modal/Modal";
import TextBox from "@/components/common/TextBox/TextBox";
import * as style from "./style/LogoutBtn";

const LogoutBtn = () => {
  const [flag, setFlag] = useState(false);
  const Logout = () => {
    setFlag(true);
    console.log("로그아웃!");
  };
  return (
    <Fragment>
      <style.LogoutWrapper>
        <style.LogoutBtnWrapper onClick={Logout}>
          <style.LogoutIcon src="/logouticon.png" />
          <style.LogoutTxt>
            <TextBox size={30} content={"LOGOUT"} />
          </style.LogoutTxt>
        </style.LogoutBtnWrapper>
      </style.LogoutWrapper>
    </Fragment>
  );
};
export default LogoutBtn;
