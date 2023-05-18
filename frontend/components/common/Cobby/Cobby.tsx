// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  if (props.outfits.head.costumeId === 0) {
    props.outfits.head.gifUrl = "/CostumeItems_GIF/empty.gif";
  }
  if (props.outfits.body.costumeId === 0) {
    props.outfits.body.gifUrl = "/CostumeItems_GIF/empty.gif";
  }
  if (props.outfits.effect.costumeId === 0) {
    props.outfits.effect.gifUrl = "/CostumeItems_GIF/empty.gif";
  }

  if (!props.isLoading) {
    return (
      <style.CobbyWrapper>
        {props.cobby.effect && (
          <style.CobbyEffectItem src={props.cobby.effect + `?${Date.now()}`} />
        )}
        {props.cobby.effect === null && (
          <style.CobbyEffectItem
            src={props.outfits.effect.gifUrl + `?${Date.now()}`}
          />
        )}
        {props.cobby.effect === "" && (
          <style.CobbyEffectItem
            src={props.outfits.effect.gifUrl + `?${Date.now()}`}
          />
        )}
        {/* <style.Cobby
          src="https://cobby-bucket.s3.ap-northeast-2.amazonaws.com/character/cobby.gif"
          alt="Cobby"
        /> */}
        <style.Cobby
          src={props.cobby.baseCobby + `?${Date.now()}`}
          alt="Cobby"
        />
        {props.cobby.head && (
          <style.CobbyHeadItem src={props.cobby.head + `?${Date.now()}`} />
        )}
        {props.cobby.head === null && (
          <style.CobbyHeadItem
            src={props.outfits.head.gifUrl + `?${Date.now()}`}
          />
        )}
        {props.cobby.head === "" && (
          <style.CobbyHeadItem
            src={props.outfits.head.gifUrl + `?${Date.now()}`}
          />
        )}
        {props.cobby.body && (
          <style.CobbyBodyItem src={props.cobby.body + `?${Date.now()}`} />
        )}
        {props.cobby.body === null && (
          <style.CobbyBodyItem
            src={props.outfits.body.gifUrl + `?${Date.now()}`}
          />
        )}
        {props.cobby.body === "" && (
          <style.CobbyBodyItem
            src={props.outfits.body.gifUrl + `?${Date.now()}`}
          />
        )}
      </style.CobbyWrapper>
    );
  } else {
    return <p>Loading...</p>;
  }
};

Cobby.defaultProps = {
  cobby: {
    baseCobby: "/Character/Cobby.gif",
    head: "",
    body: "",
    effect: "",
  },
};

export default Cobby;
