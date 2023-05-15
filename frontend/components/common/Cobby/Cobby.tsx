// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  const [cobby, setCobby] = useState(
    "/Character/Cobby1.gif"
  );
  const [cobbyHead, setCobbyHead] = useState(
    props.outfits.head.gifUrl
  );
  const [cobbyBody, setCobbyBody] = useState(
    props.outfits.body.gifUrl
  );
  const [cobbyEffect, setCobbyEffect] = useState(
    props.outfits.effect.gifUrl
  );

  // 제발 동시에 좀 불러와바라 Tlqkf
  // 머리 장착
  // useEffect(() => {
  //   if (props.outfits.head.gifUrl !== "") {
  //     setCobbyHead(props.outfits.head.gifUrl);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [props.outfits.head.gifUrl]); // 이방법말고는 도저히 불가능한가?

  // // 바디 장착
  // useEffect(() => {
  //   if (props.outfits.body.gifUrl !== "") {
  //     setCobbyBody(props.outfits.body.gifUrl);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [props.outfits.body.gifUrl]); // 이방법말고는 도저히 불가능한가?

  // // 효과 장착
  // useEffect(() => {
  //   if (props.outfits.effect.gifUrl !== "") {
  //     setCobbyEffect(props.outfits.effect.gifUrl);
  //     setCobby("/Character/Cobby1.gif");

  //     if (cobby === "/Character/Cobby1.gif") {
  //       setCobby("/Character/Cobby2.gif");
  //     } else setCobby("/Character/Cobby1.gif");
  //   }
  // }, [props.outfits.effect.gifUrl]); // 이방법말고는 도저히 불가능한가?
  useEffect(() => {
    setCobbyHead(props.outfits.head.gifUrl);
  }, [props.outfits.head.gifUrl]);

  useEffect(() => {
    setCobbyBody(props.outfits.body.gifUrl);
  }, [props.outfits.body.gifUrl]);

  useEffect(() => {
    setCobbyEffect(props.outfits.effect.gifUrl);
  }, [props.outfits.effect.gifUrl]);

  useEffect(() => {
    if (
      props.outfits.head.gifUrl ||
      props.outfits.body.gifUrl ||
      props.outfits.effect.gifUrl
    ) {
      if (cobby === "/Character/Cobby1.gif") {
        setCobby("/Character/Cobby2.gif");
      } else {
        setCobby("/Character/Cobby1.gif");
      }
    }
  }, [props.outfits]);

  return (
    <style.CobbyWrapper>
      <style.Cobby src={cobby} alt="Cobby" />
      {cobbyHead && (
        <style.CobbyHeadItem
          key={cobbyHead}
          src={cobbyHead}
        />
      )}
      {cobbyBody && (
        <style.CobbyBodyItem
          key={cobbyBody}
          src={cobbyBody}
        />
      )}
      {cobbyEffect && (
        <style.CobbyEffectItem
          key={cobbyEffect}
          src={cobbyEffect}
        />
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
