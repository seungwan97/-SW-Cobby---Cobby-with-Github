import styled from "styled-components";

export const CobbyWrapper = styled.div`
  position: relative;
`;

export const Cobby = styled.img`
  // position: absolute;
  width: 180px;
  height: 180px;
  z-index: 1;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;

export const CobbyCostumedItem = styled.img`
  position: absolute;
  width: 180px;
  height: 180px;
  left: 50%;
  transform: translate(-50%);
  z-index: 2;
`;
