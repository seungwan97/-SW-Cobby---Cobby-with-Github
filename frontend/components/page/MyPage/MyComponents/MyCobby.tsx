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
      const userId = "9302629d-ae6a-43b6-a965-996d5429783c";

      try {
        const res = await getAvatarInfo(userId);
        const cobbyOutfits = res.data.content.outfits;

        setOutfits(cobbyOutfits);
      } catch (error) {
        console.error(
          "Failed to fetch avatar info:",
          error
        );
      }
    };

    getCobbyOutfits();
  }, []);

  return (
    <style.MyCobbyWrapper>
      <Cobby outfits={outfits} />
    </style.MyCobbyWrapper>
  );
};
export default MyCobby;
