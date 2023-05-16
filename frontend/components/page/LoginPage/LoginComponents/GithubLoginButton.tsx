import * as style from "./style/GithubLoginButton";

const GithubLoginButton = () => {
  const Login = () => {
    console.log("깃허브로그인!");
  };
  return (
    <style.LoginButton>
      <style.GithubLogo
        src="/logo/GithubLogo.png"
        alt="github"
        onClick={Login}
      />
      Login with Github
    </style.LoginButton>
  );
};
export default GithubLoginButton;
