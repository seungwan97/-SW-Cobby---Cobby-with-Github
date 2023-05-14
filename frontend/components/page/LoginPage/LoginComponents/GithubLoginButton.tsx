import * as style from "./style/GithubLoginButton";
import { useEffect } from "react";
import client from "@/pages/api/client";

const GithubLoginButton = () => {
  const Login = () => {
    window.location.href = `http://k8b201.p.ssafy.io:15010/oauth2/authorization/github`;
    // window.location.href = `http://cobby-play.com/oauth2/authorization/github`;
  };

  useEffect(() => {
    const urlSearch = new URLSearchParams(window.location.search);
    const initial = urlSearch.get("Initial");
    if (initial === "true") {
      window.location.href =
        "https://github.com/apps/cobby-with-github/installations/new";
    } else {
      //axios 통신
    }
  }, []);
  return (
    <style.LoginButton onClick={Login}>
      <style.GithubLogo src="/logo/GithubLogo.png" alt="github" />
      Login with Github
    </style.LoginButton>
  );
};
export default GithubLoginButton;
