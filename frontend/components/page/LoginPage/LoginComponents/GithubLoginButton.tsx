import * as style from "./style/GithubLoginButton";
import { useEffect } from "react";
import client from "@/pages/api/client";

const GithubLoginButton = () => {
  const Login = () => {
    window.location.href = `${client.defaults.baseURL}/oauth2/authorization/github`;
    // window.location.href = `http://cobby-play.com/oauth2/authorization/github`;
  };
  useEffect(() => {}, []);
  return (
    <style.LoginButton onClick={Login}>
      <style.GithubLogo src="/logo/GithubLogo.png" alt="github" />
      Login with Github
    </style.LoginButton>
  );
};
export default GithubLoginButton;
