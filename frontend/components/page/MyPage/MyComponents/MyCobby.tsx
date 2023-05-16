import * as style from "./style/MyCobby";
import Cobby from "@/components/common/Cobby/Cobby";
import { useState, useEffect } from "react";
import { getAvatarInfo } from "@/pages/api/main";

const MyCobby = () => {
  const [outfits, setOutfits] = useState({
    head: {},
    body: {},
    effect: {},
  });

  useEffect(() => {
    const getCobbyOutfits = async () => {
      const userId =
        "Bearer-eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmZDJkMDlmNC1lOTA0LTQyZDMtOTQwMy0wMzJkODE0ZDVhNjYiLCJyb2xlIjoiUk9MRV9VU0VSIiwiaWF0IjoxNjg0MjUxOTM0LCJleHAiOjE2ODQyNTU1MzR9.p9miuyHDFwDG3ImN31G17LfapE3Y17ZM2YpNaeq9jG0";

      try {
        const res = await getAvatarInfo(userId);
        const cobbyOutfits = res.data.content.outfits;

        setOutfits(cobbyOutfits);
      } catch (error) {
        console.error("Failed to fetch avatar info:", error);
      }
    };

    getCobbyOutfits();
  }, []);

  return (
    <style.MyCobbyWrapper>
      <style.Background src="/Character/background.png" />
      <style.MyCobby />
    </style.MyCobbyWrapper>
  );
};
export default MyCobby;
