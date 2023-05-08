import styled from "styled-components";

export const PageWrapper = styled.div`
  height: 100%;
  width: 30vw;

  @media (max-width: 1300px) {
    // 모바일
    width: 100%;
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
