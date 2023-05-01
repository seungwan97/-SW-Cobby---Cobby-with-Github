import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const ItemWrapper = styled.div`
  border: 2px solid ${colors.ItemBoxColor};
  background-color: ${colors.ItemBoxColor};
  text-align: center;
  width: 100px;
  height: 100px;
  border-radius: 10px;
`;

export const ItemImage = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 10px;
`;

export const ImageWrapper = styled.div`
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

  &:hover {
    cursor: pointer;
    background-color: gray;
  }
`;
