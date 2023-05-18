<<<<<<< HEAD
import styled, { css } from "styled-components";
import { colors } from "@/styles/colors-style";
import { keyframes } from "@emotion/react";
import Image from "next/image";

export const ImageWrapper = styled.div`
=======
import styled, { css, keyframes } from "styled-components";
import { colors } from "@/styles/colors-style";
import Image from "next/image";

export const ImageWrapper = styled.div<{
  select: boolean;
  getto: boolean;
}>`
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
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
<<<<<<< HEAD
    !props.selected &&
=======
    props.select &&
    css`
      &:before {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        background-position: center;
        background-repeat: no-repeat;
        background-size: 70% auto;
      }

      border: 6px solid ${colors.QuestBtnColor};
      background-color: #ededdb;
      pointer-events: none;
      box-shadow: 2px 2px 2px gray;
    `}
  ${(props) =>
    !props.getto &&
    css`
      pointer-events: none;
    `}

  @media (max-width: 767px) {
    width: 55px;
    height: 55px;

    @media (max-height: 667px) {
      width: 55px;
      height: 55px;
    }
  }
`;

export const Filter = styled.div<{ select: boolean }>`
  position: absolute;
  border-radius: 10px;
  width: 100%;
  height: 100%;

  justify-content: center;
  align-items: center;
  z-index: 3;

  ${(props) =>
    !props.select &&
    css`
      &:before {
        content: "";
        position: absolute;
        width: 100%;
        height: 100%;
        background-position: center;
        background-repeat: no-repeat;
        background-size: 50% auto;
      }
      opacity: 0.5;
      background-color: black;
      pointer-events: none;
    `}

  @media (max-width: 767px) {
    width: 55px;
    height: 55px;

    @media (max-height: 667px) {
      width: 55px;
      height: 55px;
    }
  }
`;

export const LockFilter = styled.div<{ select: boolean }>`
  position: absolute;
  border-radius: 10px;
  width: 100%;
  height: 100%;

  justify-content: center;
  align-items: center;
  z-index: 4;

  ${(props) =>
    !props.select &&
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
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
<<<<<<< HEAD

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

    @media (max-width: 767px) {
=======
      pointer-events: none;
    `}

  @media (max-width: 767px) {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    width: 55px;
    height: 55px;

    @media (max-height: 667px) {
      width: 55px;
      height: 55px;
    }
  }
`;

<<<<<<< HEAD
export const ItemImage = styled(Image)<{
  width: number;
  height: number;
}>`
=======
export const ItemImage = styled(Image)<{ width: number; height: number }>`
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  @media (max-width: 767px) {
    width: ${(props) => props.width * 0.75}px;
    height: ${(props) => props.height * 0.75}px;

    @media (max-height: 667px) {
      width: ${(props) => props.width * 0.75}px;
      height: ${(props) => props.height * 0.75}px;
    }
  }
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

<<<<<<< HEAD
export const NewTag = styled.div`
  color: black;
  border-radius: 10px 10px 0 0;
=======
export const isNew = styled.div`
  width: 100%;
  height: 20%;
  text-align: center;
  line-height: 50%;
  position: absolute;
  font-family: "DungGeunMo";
  top: 0;
  border-radius: 10px 10px 0 0;

>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  animation-name: ${backgroundColor};
  animation-duration: 1s;
  animation-timing-function: linear;
  animation-iteration-count: infinite;
`;
