import styled from "styled-components";
import { colors } from "@/styles/colors-style";

export const LogoutBtnWrapper = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: ${colors.NavBarCreamColor};
  margin: 20px 120px;
  padding: 10px 30px;
  cursor: pointer;
`;

export const LogoutIcon = styled.img`
  padding: 0 4%;
  width: 8%;
`;

export const LogoutTxt = styled.div`
  display: flex;
  align-items: center;
`;
