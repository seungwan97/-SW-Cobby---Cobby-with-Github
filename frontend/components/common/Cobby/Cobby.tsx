// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  const [imagesLoaded, setImagesLoaded] = useState(false);

  const cobby = "/Character/Cobby.gif";
  const cobbyHead = props.outfits.head.gifUrl || cobby;
  const cobbyBody = props.outfits.body.gifUrl || cobby;
  const cobbyEffect = props.outfits.effect.gifUrl || cobby;

  // 제발 동시에 좀 불러와바라
  useEffect(() => {
    const preloadImages = async () => {
      const img1 = loadImage(cobby);
      const img2 = loadImage(cobbyHead);
      const img3 = loadImage(cobbyBody);
      const img4 = loadImage(cobbyEffect);

      await Promise.all([img1, img2, img3, img4]);

      setImagesLoaded(true);
    };

    preloadImages();
  }, [cobby, cobbyHead, cobbyBody, cobbyEffect]);

  const loadImage = (src: string) => {
    return new Promise((resolve, reject) => {
      const image = new Image();
      image.onload = resolve;
      image.onerror = reject;
      image.src = src;
    });
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
          <style.CobbyHeadItem
            src={cobbyHead}
          ></style.CobbyHeadItem>
          <style.CobbyBodyItem
            src={cobbyBody}
          ></style.CobbyBodyItem>
          <style.CobbyEffectItem
            src={cobbyEffect}
          ></style.CobbyEffectItem>
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
