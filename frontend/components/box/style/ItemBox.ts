import styled from "styled-components";

export const ItemWrapper = styled.div`
  border: 2px solid #333333;
  background-color: #ededdb;
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

// export const ItemLabel = styled.div<{ size: number }>`
//   border: 1px solid green;
//   width: 100%;
// `;
