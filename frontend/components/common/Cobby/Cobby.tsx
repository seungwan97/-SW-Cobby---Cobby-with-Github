// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

type PropsType = {
  gifSrc: string;
};

const Cobby = (props: PropsType) => {
  const [cobby, setCobby] = useState("/Character/Cobby2.gif");
  const [cobbyCostume, setCobbyCostume] = useState("");

  useEffect(() => {
    if (props.gifSrc) {
      setCobbyCostume(props.gifSrc);
      setCobby("/Character/Cobby1.gif");

      if (cobby === "/Character/Cobby1.gif") {
        setCobby("/Character/Cobby2.gif");
      } else setCobby("/Character/Cobby1.gif");
    }
  }, [props.gifSrc]); // 이방법말고는 도저히 불가능한가?

  return (
    <style.CobbyWrapper>
      <style.Cobby src={cobby} alt={cobbyCostume} />

      {cobbyCostume && (
        <style.CobbyCostumedItem src={props.gifSrc}></style.CobbyCostumedItem>
      )}
    </style.CobbyWrapper>
  );
};

export default Cobby;
