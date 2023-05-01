import styled from "styled-components";

export const MainPageContent = styled.div`
  /* 임시  CSS임 => 밑에 NavBar 표시하느라 잠시 넣었음 */
  margin-top: 10%;
  margin-bottom: 10%;
  padding: 15px;

  @media (max-width: 767px) {
    // 모바일
    margin-top: 15%;

    @media (max-height: 736px) {
      /* margin: 0; */
    }
  }

  @media (min-width: 768px) and (max-width: 991px) {
    // 테블릿 세로
  }

  @media (min-width: 992px) and (max-width: 1199px) {
    // 테블릿 가로
  }

  @media (min-width: 1200px) {
    // 데스크탑 일반
  }스크탑 일반
`;

export const MainPageMargin = styled.div`
  display: inline-block;
  height: 10%;
`;
