// Cobby Character
import * as style from "./style/Cobby";
import { useState, useEffect } from "react";

const Cobby = (props: any) => {
  if (!props.isLoading) {
    return (
      <style.CobbyWrapper>
        <style.Cobby
          src={props.cobby.baseCobby}
          alt="Cobby"
        />
        {props.cobby.head && (
          <style.CobbyHeadItem src={props.cobby.head} />
        )}
        {props.cobby.body && (
          <style.CobbyBodyItem src={props.cobby.body} />
        )}
        {props.cobby.effect && (
          <style.CobbyEffectItem src={props.cobby.effect} />
        )}
      </style.CobbyWrapper>
    );
  } else {
    return <p>Loading</p>;
  }
};

Cobby.defaultProps = {
  outfits: {
    head: {},
    body: {},
    effect: {},
  },
};

export default Cobby;
