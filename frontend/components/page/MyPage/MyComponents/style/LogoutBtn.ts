import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const LogoutBtnWrapper = styled.div`
  margin-top: 10%;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: ${colors.NavBarCreamColor};
  padding: 10px;
  width: 85%;
  cursor: pointer;

  @media (max-width: 1300px) {
    margin-top: 5%;
  }
`;

export const LogoutIcon = styled.img`
  padding: 0 4%;
  width: 30px;
`;

export const LogoutTxt = styled.div`
  display: flex;
  align-items: center;
`;

export const LogoutWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
`;
