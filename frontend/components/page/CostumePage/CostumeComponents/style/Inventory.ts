import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const Inventory = styled.div`
  display: flex;
  flex-direction: column;
  width: 100%;
<<<<<<< HEAD
  height: 60%;
=======
  height: 120%;
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  align-items: center;
`;

export const InventoryBar = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 70%;
`;

export const InventoryType: any = styled.div`
  flex: 1; /* 각각의 칸이 동일한 너비를 가지도록 설정 */
  text-align: center;
  margin: 10px 5px 0 5px;
  background-color: ${({ selected }: any) =>
<<<<<<< HEAD
    selected
      ? colors.NavBarCreamColor
      : colors.ItemBoxColor};
=======
    selected ? colors.NavBarCreamColor : colors.ItemBoxColor};
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  border-radius: 10px 10px 0px 0px;
  cursor: pointer;

  :hover {
    background-color: ${colors.NavBarCreamColor};
  }
`;

export const InventoryTypeImg = styled.img`
  max-width: 40%;
  max-height: 40%;
  margin: 5px 10px 0 10px;
`;

export const InventoryBox = styled.div`
  display: grid;
  grid-template-columns: repeat(3, auto);
  gap: 15px;
  width: 70%;
<<<<<<< HEAD
  height: 100%;
=======
  height: 410px;
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  background-color: ${colors.NavBarCreamColor};
  overflow-y: scroll;
  padding: 15px;
  justify-items: center;
<<<<<<< HEAD

  & > * {
    width: 100%;
=======
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  border-radius: 10px;
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  }
`;
