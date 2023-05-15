import styled from "styled-components";

export const CobbyWrapper = styled.div`
  position: relative;
`;

export const Cobby = styled.img`
  // position: absolute;
  width: 180px;
  height: 180px;
  z-index: 2;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;

export const CobbyHeadItem = styled.img`
  position: absolute;
  width: 180px;
  height: 180px;
  left: 50%;
  transform: translate(-50%);
  z-index: 4;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;

export const CobbyBodyItem = styled.img`
  position: absolute;
  width: 180px;
  height: 180px;
  left: 50%;
  transform: translate(-50%);
  z-index: 3;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;

export const CobbyEffectItem = styled.img`
  position: absolute;
  width: 180px;
  height: 180px;
  left: 50%;
  transform: translate(-50%);
  z-index: 1;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;
