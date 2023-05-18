import * as style from "./style/MyCobby";
// import Cobby from "@/components/common/Cobby/Cobby";
import { useState, useEffect } from "react";
import { getAvatarInfo } from "@/pages/api/main";
import cookie from "react-cookies";

const MyCobby = () => {
  const [outfits, setOutfits]: any = useState({
    head: {
      costumeId: 0,
      name: "empty",
      category: "head",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
    body: {
      costumeId: 0,
      name: "empty",
      category: "body",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
    effect: {
      costumeId: 0,
      name: "empty",
      category: "effect",
      questId: null,
      imgUrl: "/empty.png",
      gifUrl: "/CostumeItems_GIF/empty.gif",
    },
  });

  useEffect(() => {
    const getCobbyOutfits = async () => {
      const userId = cookie.load("Authorization");

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
      {outfits.effect.costumeId === 0 && (
        <style.Background src="/Character/background.png" />
      )}
      <style.MyCobby outfits={outfits} />
    </style.MyCobbyWrapper>
  );
};
export default MyCobby;
