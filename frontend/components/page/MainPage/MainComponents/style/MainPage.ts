import styled from "styled-components";

export const MainPageContent = styled.div`
  /* 임시  CSS임 => 밑에 NavBar 표시하느라 잠시 넣었음 */

  @media (max-height: 1200px) {
    /* margin-top: 15%; */
    @media (max-height: 1024px) {
      /* margin-top: 8%; */
      @media (max-width: 767px) and (max-height: 930px) {
        // 모바일
        /* margin-top: 15%; */
        padding-top: 10%;
        @media (max-height: 667px) {
          padding-top: 5%;
        }
      }
    }
  }

  @media (min-width: 711px) and (max-width: 713px) {
    padding-top: 10%;
  }

  @media (min-width: 992px) and (max-width: 1199px) {
    // 테블릿 가로
  }

  @media (min-width: 1200px) {
    // 데스크탑 일반
  }
`;

export const MainPageMargin = styled.div`
  /* display: inline-block;
  height: 10%; */
`;
