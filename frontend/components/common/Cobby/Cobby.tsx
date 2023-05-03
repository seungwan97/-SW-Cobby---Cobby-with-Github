// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

type PropsType = {
  gifSrc: string;
};

const Cobby = (props: PropsType) => {
  const [cobbyCostume, setCobbyCostume] = useState("");

  useEffect(() => {
    if (props.gifSrc) {
      setCobbyCostume(props.gifSrc);
      console.log("아 ㅅㅄㅂ");

      console.log(props.gifSrc);
    }
  }, [props.gifSrc]);

  return (
    <style.CobbyWrapper>
      <style.Cobby src="/Character/Cobby.gif" />
      {cobbyCostume && (
        <style.CobbyCostumedItem
          src={props.gifSrc}
        ></style.CobbyCostumedItem>
      )}
    </style.CobbyWrapper>
  );
};

export default Cobby;
