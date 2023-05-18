import cookie from "react-cookies";

const noTokenCheck = () => {
  const token = cookie.load("Authorization");

  console.log(token);
};

export default noTokenCheck;
