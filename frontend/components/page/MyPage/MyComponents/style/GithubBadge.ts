import styled from "styled-components";
import { colors } from "@/styles/colors-style";
import TextBox from "@/components/common/TextBox/TextBox";

export const GithubBadgeWrapper = styled.div`
<<<<<<< HEAD
  margin: 3% 0 3% 0;
=======
  margin: 8% 0 8% 0;
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  display: flex;
  flex-direction: column;
  align-items: center;

<<<<<<< HEAD
  @media (max-width: 767px) {
    @media (max-height: 736px) {
      margin: 1% 0 1% 0;
    }
  }
=======
  @media (max-width: 1300px) {
    margin-top: 3%;
  }
  // @media (max-width: 830px) {
  //   margin-top: 10%;
  // }
  // @media (max-width: 711px) {
  //   margin-top: 20%;
  // }
  // @media (max-width: 611px) {
  //   margin-top: 30%;
  // }
  // @media (max-width: 511px) {
  //   margin-top: 40%;
  // }
  // @media (max-width: 411px) {
  //   margin-top: 45%;
  // }
  // @media (max-width: 311px) {
  //   margin-top: 55%;
  // }
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
`;

export const BadgeTxt = styled.div`
  width: auto;
`;

export const CodeCopyBox = styled.div`
  display: flex;
  align-items: center;
  background-color: ${colors.NavBarCreamColor};
  padding: 10px;
  width: 80%;
`;

export const ReadmeCode = styled.div`
  flex-grow: 8;
  text-align: center;
  overflow: hidden; // 여기 나중에 주소 길이통해서 수정해야할 수 있음.
`;

export const CustomTextBox = styled.div<{ size: number }>`
  display: flex;
  white-space: nowrap;
  overflow-x: scroll;
<<<<<<< HEAD
  height: 30px;
  font-weight:bold;
=======
  font-weight: bold;

  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */

  &::-webkit-scrollbar {
    display: none; /* Chrome, Safari, Opera*/
  }
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

  font-size: ${(props) => props.size}px;
  font-family: "DungGeunMo";
  color: ${(props) => props.color};
  font-weight: normal;

  @media (max-width: 1200px) {
    font-size: ${(props) => props.size * 1.2}px;
    @media (max-width: 1024px) {
      font-size: ${(props) => props.size * 1.2}px;
      @media (max-width: 711px) {
        font-size: ${(props) => props.size * 0.7}px;
        @media (max-width: 667px) {
          font-size: ${(props) => props.size * 0.7}px;
        }
      }
    }
  }
`;

export const CopyBtnImg = styled.img`
  background-color: ${colors.ItemBoxColor};
  padding: 2%;
  width: 5%;

  border-radius: 5px;

  &:hover {
    background-color: ${colors.NavBarCreamColor};
    cursor: pointer;
  }
<<<<<<< HEAD
=======
  @media (max-width: 1300px) {
    padding: 1%;
    width: 3%;
  }
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
`;
