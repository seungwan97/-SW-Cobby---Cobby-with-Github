import cookie from "react-cookies";

const noTokenCheck = () => {
  const token = cookie.load("Authorization");

};

export default noTokenCheck;
