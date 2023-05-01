import styled from "styled-components";
import { colors } from "@/styles/colors-style";
import TextBox from "@/components/common/TextBox/TextBox";

export const GithubBadgeWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

export const BadgeTxt = styled.div`
  display: flex;
  align-items: center;
`;

export const CodeCopyBox = styled.div`
  display: flex;
  align-items: center;
  background-color: ${colors.NavBarCreamColor};
  padding: 10px 20px;
`;

export const ReadmeCode = styled.div`
  padding: 0 20px;
`;

export const CopyBtn = styled.div`
  text-align: center;
  background-color: ${colors.ItemBoxColor};
  border-radius: 5px;
  width: 40px;
  height: 40px;
  cursor: pointer;
`;

export const CopyBtnImg = styled.img`
  display: flex;
  width: 20px;
  height: 20px;
  padding: 30px;
`;
