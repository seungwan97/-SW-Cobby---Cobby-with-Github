import styled from "styled-components";

export const CommitContainer = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  height: 10vh;
  width: 70%;
  margin: 0 auto;
  margin-top: 3%;
  background-color: #fafae7;

  @media (max-width: 767px) {
    // 모바일
    width: 80%;
  }

  @media (min-width: 768px) and (max-width: 991px) {
    // 테블릿 세로
  }

  @media (min-width: 992px) and (max-width: 1199px) {
    // 테블릿 가로
  }

  @media (min-width: 1200px) {
    // 데스크탑 일반
  }
`;
