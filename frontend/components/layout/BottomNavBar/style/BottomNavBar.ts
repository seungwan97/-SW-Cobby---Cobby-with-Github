import styled from "styled-components";
import Link from "next/link";

export const NavWrapper = styled.div`
  width: 100%;
  height: 9%;
  display: flex;
  justify-content: space-around;
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
  height: auto;
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
  height: 30px;
  content: url(${(props) => props.imgSrc});
`;

export const CustomLink = styled(Link)``;
