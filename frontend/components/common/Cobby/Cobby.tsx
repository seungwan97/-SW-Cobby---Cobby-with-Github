// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

type PropsType = {
  outfits: {
    head: string;
    body: string;
    effect: string;
  };
};

const Cobby = (props: PropsType) => {
  const { outfits } = props;
  const [cobby, setCobby] = useState("/Character/Cobby2.gif");
  const [cobbyHead, setCobbyHead] = useState(outfits.head);
  const [cobbyBody, setCobbyBody] = useState(outfits.body);
  const [cobbyEffect, setCobbyEffect] = useState(outfits.effect);

  //머리 장착
  useEffect(() => {
    if (outfits.head !== "") {
      setCobbyHead(outfits.head);
      setCobby("/Character/Cobby1.gif");

      if (cobby === "/Character/Cobby1.gif") {
        setCobby("/Character/Cobby2.gif");
      } else setCobby("/Character/Cobby1.gif");
    }
  }, [outfits.head]); // 이방법말고는 도저히 불가능한가?

  //바디 장착
  useEffect(() => {
    if (outfits.body !== "") {
      setCobbyBody(outfits.body);
      setCobby("/Character/Cobby1.gif");

      if (cobby === "/Character/Cobby1.gif") {
        setCobby("/Character/Cobby2.gif");
      } else setCobby("/Character/Cobby1.gif");
    }
  }, [outfits.body]); // 이방법말고는 도저히 불가능한가?

  //효과 장착
  useEffect(() => {
    if (outfits.effect !== "") {
      setCobbyEffect(outfits.effect);
      setCobby("/Character/Cobby1.gif");

      if (cobby === "/Character/Cobby1.gif") {
        setCobby("/Character/Cobby2.gif");
      } else setCobby("/Character/Cobby1.gif");
    }
  }, [outfits.effect]); // 이방법말고는 도저히 불가능한가?

  return (
    <style.CobbyWrapper>
      <style.Cobby src={cobby} alt={cobby} />

      {cobbyHead !== "" && (
        <style.CobbyHeadItem src={outfits.head}></style.CobbyHeadItem>
      )}
      {cobbyBody !== "" && (
        <style.CobbyBodyItem src={outfits.body}></style.CobbyBodyItem>
      )}
      {cobbyEffect !== "" && (
        <style.CobbyEffectItem src={outfits.effect}></style.CobbyEffectItem>
      )}
    </style.CobbyWrapper>
  );
};

Cobby.defaultProps = {
  outfits: {
    head: "",
    body: "",
    effect: "",
  },
};

export default Cobby;
