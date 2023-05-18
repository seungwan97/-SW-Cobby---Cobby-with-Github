import styled from "styled-components";
import { colors } from "@/styles/colors-style";
import ProgressBar from "@ramonak/react-progress-bar";
import Image from "next/image";

export const QuestPageWrapper = styled.div`
  text-align: center;

  @media (max-height: 1180px) {
    height: 600px;
  }

  @media (max-height: 1024px) {
    height: 350px;
  }

  @media (max-height: 915px) {
    height: 430px;
  }

  @media (max-height: 844px) {
    height: 330px;
  }

  @media (max-height: 812px) {
    height: 330px;
  }

  @media (max-height: 736px) {
    height: 250px;
  }

  @media (max-height: 667px) {
    height: 250px;
  }
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
  overflow-y: scroll;
  height: 75vh;
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
`;

export const QuestItemWrapper = styled.div`
  text-align: center;
  width: 85%;
  padding: 5px;
  background-color: ${colors.NavBarCreamColor};
  margin: 5px;
  padding-top: 10px;

  @media (min-width: 1300px) {
    width: 100%;
  }
`;

export const QuestInfoWrapper = styled.div`
  margin-top: 10px;
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
  width: 80%;
`;

export const ImageWrapper = styled.div`
  border: 2px solid ${colors.MainBlackColor};
  border-radius: 10px;
  display: flex;
  background-color: #ededdb;
  width: auto;
  height: auto;
  padding: 7px;
  float: left;
  margin-right: 3%;
  margin-bottom: 3%;
  justify-content: center;
  align-items: center;
`;

export const ConfirmButtonWrapper = styled.div`
  width: 100%;
`;

export const ConfirmButton = styled.button<{
  color: string;
  cursor: string;
  width: number;
  height: number;
  transition: string;
  x: number;
}>`
  display: inline-block;
  width: 80%;
  height: 35px;
  border: none;
  margin-bottom: 3%;
  background-color: ${(props) => props.color};
  :hover {
    cursor: ${(props) => props.cursor};
    width: ${(props) => props.width + props.x}%;
    height: ${(props) => props.height + props.x}px;
    transition: ${(props) => props.transition}s;
  }
  :active {
    background-color: #f2f2f2;
  }
`;

export const CustomProgressBar = styled(ProgressBar)`
  flex-grow: 2;
  margin-right: 3%;
`;

export const CustomImage = styled(Image)<{ width: number; height: number }>`
  @media (max-width: 767px) {
    width: ${(props) => props.width * 0.75}px;
    height: ${(props) => props.height * 0.75}px;

    @media (max-height: 667px) {
      width: ${(props) => props.width * 0.5}px;
      height: ${(props) => props.height * 0.5}px;
    }
  }
`;

export const Margin = styled.div`
  margin-top: 60%;
`;
