import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const LogoutBtnWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: ${colors.NavBarCreamColor};
  padding: 5px;
  width: 45%;
  cursor: pointer;
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
