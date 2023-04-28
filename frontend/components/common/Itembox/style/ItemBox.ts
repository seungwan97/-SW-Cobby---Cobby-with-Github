import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const ItemWrapper = styled.div`
  border: 2px solid ${colors.ItemBoxColor};
  background-color: ${colors.ItemBoxColor};
  text-align: center;
  width: 100px;
  height: 100px;
  border-radius: 10px;

  &:hover {
    cursor: pointer;
    background-color: gray;
  }
`;

export const ItemImage = styled.img`
  width: 100%;
  height: 100%;
  border-radius: 10px;
`;
