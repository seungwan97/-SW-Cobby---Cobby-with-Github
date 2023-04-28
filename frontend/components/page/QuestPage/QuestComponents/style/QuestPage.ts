import styled from "styled-components";
import { colors } from "@/styles/colors-style";
import ProgressBar from "@ramonak/react-progress-bar";
import TextBox from "@/components/common/TextBox/TextBox";

export const QuestPageWrapper = styled.div`
  text-align: center;
`;

export const QuestPageTextWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 10vh;
`;

export const QuestListWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 30px;
  padding-top: 0;
`;

export const QuestItemWrapper = styled.div`
  text-align: center;
  width: 80%;
  padding: 5px;
  background-color: ${colors.NavBarCreamColor};
  margin: 10px;
`;

export const QuestInfoWrapper = styled.div`
  margin-top: 15px;
  display: flex;
`;

export const ColumContentWrapper = styled.div`
  display: flex;
  justify-content: space-around;
  flex-direction: column;
  align-items: center;
  flex-grow: 2;
`;

export const ProgressWrapper = styled.div`
  display: flex;
  align-items: center;
  width: 70%;
`;

export const ImageWrapper = styled.div`
  border: 2px solid ${colors.MainBlackColor};
  border-radius: 10px;
  display: flex;
  background-color: #ededdb;
  width: 70px;
  height: 70px;
  padding: 7px;
  margin: auto 0;
  justify-content: center;
  align-items: center;
`;

export const ConfirmButtonWrapper = styled.div`
  width: 100%;
`;

export const ConfirmButton = styled.button`
  display: inline-block;
  width: 70%;
  height: 35px;
  border: none;
  background-color: ${colors.NotQuestBtnColor};
`;

export const CustomProgressBar = styled(ProgressBar)`
  flex-grow: 2;
  margin-right: 20px;
`;
