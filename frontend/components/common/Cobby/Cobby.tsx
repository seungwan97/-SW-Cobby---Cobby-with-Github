// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  console.log(props);

  if (props.outfits.head.costumeId === 0) {
    props.outfits.head.gifUrl =
      "/CostumeItems_GIF/empty.gif";
  }
  if (props.outfits.body.costumeId === 0) {
    props.outfits.body.gifUrl =
      "/CostumeItems_GIF/empty.gif";
  }
  if (props.outfits.effect.costumeId === 0) {
    props.outfits.effect.gifUrl =
      "/CostumeItems_GIF/empty.gif";
  }

  if (!props.isLoading) {
    return (
      <style.CobbyWrapper>
        {props.cobby.effect ? (
          <style.CobbyEffectItem src={props.cobby.effect} />
        ) : null}
        {props.cobby.effect === null && (
          <style.CobbyEffectItem
            src={props.outfits.effect.gifUrl}
          />
        )}
        {props.cobby.effect === "" && (
          <style.CobbyEffectItem
            src={props.outfits.effect.gifUrl}
          />
        )}
        {/* <style.Cobby
          src="https://cobby-bucket.s3.ap-northeast-2.amazonaws.com/character/cobby.gif"
          alt="Cobby"
        /> */}
        <style.Cobby
          src={props.cobby.baseCobby}
          alt="Cobby"
        />
        {props.cobby.head && (
          <style.CobbyHeadItem src={props.cobby.head} />
        )}
        {props.cobby.head === null && (
          <style.CobbyHeadItem
            src={props.outfits.head.gifUrl}
          />
        )}
        {props.cobby.head === "" && (
          <style.CobbyHeadItem
            src={props.outfits.head.gifUrl}
          />
        )}
        {props.cobby.body && (
          <style.CobbyBodyItem src={props.cobby.body} />
        )}
        {props.cobby.body === null && (
          <style.CobbyBodyItem
            src={props.outfits.body.gifUrl}
          />
        )}
        {props.cobby.body === "" && (
          <style.CobbyBodyItem
            src={props.outfits.body.gifUrl}
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
