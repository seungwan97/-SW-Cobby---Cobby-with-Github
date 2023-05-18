import Cobby from "@/components/common/Cobby/Cobby";
import styled, { keyframes } from "styled-components";

export const MyCobbyWrapper = styled.div`
  position: relative;
  display: flex;
  justify-content: center;
`;

export const Background = styled.img`
  position: absolute;
  width: 100%;
  height: 100%;
  z-index: -5;
`;

export const MyCobby = styled(Cobby)``;
