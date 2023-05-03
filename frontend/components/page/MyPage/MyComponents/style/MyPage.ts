import styled from "styled-components";
import MyCobby from "../MyCobby";
export const MyPageTxt = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 10vh;
`;

export const Cobby = styled(MyCobby)`
  text-align: center;
`;

export const LeaveButton = styled.div`
  width: auto;
  position: absolute;
  text-decoration: underline;
  right: 5%;
  bottom: 12%;

  &:hover {
    cursor: pointer;
  }

  @media (max-width: 767px) {
    bottom: 12%;
    @media (max-height: 736px) {
      margin: 1% 0 1% 0;
    }

    @media (min-height: 738px) {
      margin: 10% 0 10% 0;
    }
  }
`;
