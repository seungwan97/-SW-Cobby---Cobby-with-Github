// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

type PropsType = {
  gifSrc: string;
};
const COBBY_BASE = "/Character/Cobby.gif";

const Cobby = (props: any) => {
  const [cobbyCostume, setCobbyCostume] = useState("");

  useEffect(() => {
    if (props.gifSrc) {
      setCobbyCostume(props.gifSrc);
    }
  }, [props.gifSrc]);

  return (
    <style.CobbyWrapper>
      <style.Cobby src={COBBY_BASE} />
      {cobbyCostume && (
        <style.CobbyCostumedItem src={props.gifSrc}></style.CobbyCostumedItem>
      )}
    </style.CobbyWrapper>
  );
};

export default Cobby;
