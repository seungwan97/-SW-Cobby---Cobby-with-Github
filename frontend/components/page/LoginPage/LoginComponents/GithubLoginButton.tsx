import * as style from "./style/GithubLoginButton";
import { useEffect } from "react";
import client from "@/pages/api/client";
import { useRouter } from "next/router";
import { setCookie } from "@/util/cookie";

const GithubLoginButton = () => {
  const router = useRouter();
  const token = router.query.Authorization;

  if (token !== undefined) {
    setCookie("Authorization", `${token}`, {
      path: "/",
      secure: true,
      sameSite: "none",
    });

    router.push("/main");
    // router.push({ pathname: "/main", query: { name: "song" } });
  }

  const Login = () => {
    window.location.href = `https://cobby-play.com/oauth2/authorization/github`;
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
