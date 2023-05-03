import styled from "styled-components";

export const CobbyWrapper = styled.div`
  position: relative;
`;

export const Cobby = styled.img`
  width: 180px;
  height: 180px;
  zindex: 1;

  @media (max-height: 667px) {
    width: 150px;
    height: 150px;
  }
`;

export const CobbyCostumedItem = styled.img`
  position: absolute;
  width: 180px;
  height: 180px;
  zindex: 2;
`;
