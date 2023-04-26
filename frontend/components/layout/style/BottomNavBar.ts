import styled from "styled-components";

export const NavWrapper = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  background-color: #fafae7;
`;

export const NavItemWrapper = styled.div`
  width: 25%;
  height: 50%;
  text-align: center;

  &:hover {
    cursor: pointer;
    background-color: gray;
  }
`;

export const NavItemIcon = styled.img<{ imgSrc: string }>`
  height: 70px;
  content: url(${(props) => props.imgSrc});
`;
