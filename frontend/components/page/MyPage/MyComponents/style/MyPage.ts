import styled from "styled-components";
import MyCobby from "../MyCobby";
export const MyPageTxt = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 10vh;
`;

export const Background = styled.img`
  width: 100%;
  height: 20%;
`;

export const Div = styled.div`
  position: absolute;
  top: 20%;
  left: 3%;
`;

export const Cobby = styled(MyCobby)``;

export const LeaveButton = styled.div`
  width: auto;
  float: right;
  margin-top: 0.5%;
  margin-right: 7%;
  text-decoration: underline;

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
