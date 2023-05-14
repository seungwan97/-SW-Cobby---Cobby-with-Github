import styled, { css } from "styled-components";
import { colors } from "@/styles/colors-style";

export const ImageWrapper = styled.div`
  position: relative;
  border: 2px solid ${colors.MainBlackColor};
  border-radius: 10px;
  display: flex;
  background-color: #ededdb;
  width: 70px;
  height: 70px;
  padding: 7px;
  margin: auto 0;
  justify-content: center;
  align-items: center;
  z-index: 2;

  &:hover {
    cursor: pointer;
    background-color: gray;
  }

  ${(props) =>
    !props.selected &&
    css`
      position: relative;
      border: 2px solid ${colors.MainBlackColor};
      background-color: ${colors.MainBlackColor};
      pointer-events: none;
      background-image: url("/lock.png");
      background-position: center;
      background-repeat: no-repeat;
      background-size: 100% auto;
      z-index: 10;
    `}
`;
