import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const Inventory = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const InventoryBar = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

export const InventoryType = styled.div`
  flex: 1; /* 각각의 칸이 동일한 너비를 가지도록 설정 */
  text-align: center;
  margin: 0 5px;
  background-color: ${colors.ItemBoxColor};
  border-radius: 10px 10px 0px 0px;

  :hover {
    background-color: ${colors.NavBarCreamColor};
  }
`;

export const InventoryTypeImg = styled.img`
  max-width: 50%;
  max-height: 50%;
  margin: 5px 10px;
`;

export const InventoryBox = styled.div`
  display: grid;
  grid-template-columns: repeat(
    3,
    1fr
  ); /* 3개의 열을 가지는 그리드 */
  gap: 15px;
  padding: 20px;
  margin-bottom: 20px;
  height: calc(100% - 50px);
  background-color: ${colors.NavBarCreamColor};
`;
