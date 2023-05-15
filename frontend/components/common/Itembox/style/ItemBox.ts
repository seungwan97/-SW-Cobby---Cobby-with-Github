import styled, { css } from "styled-components";
import { colors } from "@/styles/colors-style";
import { keyframes } from "@emotion/react";

export const ImageWrapper = styled.div`
  position: relative;
  border-radius: 10px;
  display: flex;
  background-color: ${colors.ItemBoxColor};
  width: 70px;
  height: 70px;
  padding: 7px;
  margin: auto 0;
  justify-content: center;
  align-items: center;
  z-index: 2;

  &:hover {
    cursor: pointer;
    border: 2px solid ${colors.ItemBoxHoveredColor};
    background-color: ${colors.ItemBoxHoveredColor};
  }

  ${(props) =>
    !props.selected &&
    css`
      &:before {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        background-image: url("/lock.png");
        background-position: center;
        background-repeat: no-repeat;
        background-size: 50% auto;
      }

      background-color: ${colors.MainBlackColor};
      pointer-events: none;
    `}

  ${(props) =>
    props.checked &&
    css`
      &:before {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        background-image: url("/checked.png");
        background-position: center;
        background-repeat: no-repeat;
        background-size: 70% auto;
      }

      border: 2px solid ${colors.MainBlackColor};
      background-color: ${colors.ItemBoxHoveredColor};
      pointer-events: none;
    `}
`;

export const backgroundColor = keyframes`
  0% {
    background-color: yellow;
  }
  50% {
    background-color: orange;
  }
  100% {
    background-color: yellow;
  }
`;

export const NewTag = styled.div`
  color: black;
  border-radius: 10px 10px 0 0;
  animation-name: ${backgroundColor};
  animation-duration: 1s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
`;
