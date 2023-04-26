import styled from "styled-components";

export const NavWrapper = styled.div`
  width: 30%;
  height: auto;
  display: flex;
  background-color: #fafae7;
  position: fixed;
  bottom: 0;
`;
export const TextBox = styled.div<{ size: number }>`
  font-size: ${(props) => props.size}px;
  font-family: "DungGeunMo";
  color: #333333;
`;

export const NavItemWrapper = styled.div`
  width: 25%;
  height: 50%;
  text-align: center;

  &:hover {
    cursor: pointer;
    background-color: gray;
  }

  &:hover ${TextBox} {
    color: white;
  }
`;

export const NavItemIcon = styled.img<{ imgSrc: string }>`
  padding-top: 10px;
  height: 50px;
  content: url(${(props) => props.imgSrc});
`;
