// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  const [imagesLoaded, setImagesLoaded] = useState(false);

  const cobby = "/Character/Cobby2.gif";
  const cobbyHead = props.outfits.head?.gifUrl;
  const cobbyBody = props.outfits.body?.gifUrl;
  const cobbyEffect = props.outfits.effect?.gifUrl;

  // 제발 동시에 좀 불러와바라 Tlqkf
  // 택도 없네 이거 tlqkf 근데 새로고침하면 되긴하는거 같노 아닌거 같기도 하고
  useEffect(() => {
    const loadImages = () => {
      const img1 = new Image();
      img1.onload = handleImageLoad;
      img1.onerror = handleImageError;
      img1.src = cobby;

      const img2 = new Image();
      img2.onload = handleImageLoad;
      img2.onerror = handleImageError;
      img2.src = cobbyHead;

      const img3 = new Image();
      img3.onload = handleImageLoad;
      img3.onerror = handleImageError;
      img3.src = cobbyBody;

      const img4 = new Image();
      img4.onload = handleImageLoad;
      img4.onerror = handleImageError;
      img4.src = cobbyEffect;
    };

    loadImages();
  }, [cobby, cobbyHead, cobbyBody, cobbyEffect]);

  const handleImageLoad = () => {
    setImagesLoaded(true);
  };

  // 새로고침하면 실행된다 tq...? 이유가 뭘까...?
  const handleImageError = () => {
    console.error("Failed to load image.");
  };

  // //머리 장착
  // useEffect(() => {
  //   if (outfits.head !== "") {
  //     setCobbyHead(outfits.head);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [outfits.head]); // 이방법말고는 도저히 불가능한가?

  // //바디 장착
  // useEffect(() => {
  //   if (outfits.body !== "") {
  //     setCobbyBody(outfits.body);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [outfits.body]); // 이방법말고는 도저히 불가능한가?

  // //효과 장착
  // useEffect(() => {
  //   if (outfits.effect !== "") {
  //     setCobbyEffect(outfits.effect);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [outfits.effect]); // 이방법말고는 도저히 불가능한가?

  return (
    <style.CobbyWrapper>
      {imagesLoaded && (
        <>
          <style.Cobby src={cobby} alt={cobby} />
          {cobbyHead && (
            <style.CobbyHeadItem src={cobbyHead} />
          )}
          {cobbyBody && (
            <style.CobbyBodyItem src={cobbyBody} />
          )}
          {cobbyEffect && (
            <style.CobbyEffectItem src={cobbyEffect} />
          )}
        </>
      )}
    </style.CobbyWrapper>
  );
};

Cobby.defaultProps = {
  outfits: {
    head: {},
    body: {},
    effect: {},
  },
};

export default Cobby;
