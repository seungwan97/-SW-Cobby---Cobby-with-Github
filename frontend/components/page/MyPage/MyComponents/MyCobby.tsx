import * as style from "./style/MyCobby";
<<<<<<< HEAD
import Cobby from "@/components/common/Cobby/Cobby";
=======
// import Cobby from "@/components/common/Cobby/Cobby";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
import { useState, useEffect } from "react";
import { getAvatarInfo } from "@/pages/api/main";
import cookie from "react-cookies";

const MyCobby = () => {
<<<<<<< HEAD
  const [outfits, setOutfits] = useState({
    head: {},
    body: {},
    effect: {},
=======
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
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  });

  useEffect(() => {
    const getCobbyOutfits = async () => {
      const userId = cookie.load("Authorization");

      try {
        const res = await getAvatarInfo(userId);
        const cobbyOutfits = res.data.content.outfits;

        setOutfits(cobbyOutfits);
      } catch (error) {
<<<<<<< HEAD
        console.error("Failed to fetch avatar info:", error);
=======
        console.error(
          "Failed to fetch avatar info:",
          error
        );
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      }
    };

    getCobbyOutfits();
  }, []);

  return (
    <style.MyCobbyWrapper>
<<<<<<< HEAD
      <style.Background src="/Character/background.png" />
      <style.MyCobby />
=======
      {outfits.effect.costumeId === 0 && (
        <style.Background src="/Character/background.png" />
      )}
      <style.MyCobby outfits={outfits} />
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    </style.MyCobbyWrapper>
  );
};
export default MyCobby;
