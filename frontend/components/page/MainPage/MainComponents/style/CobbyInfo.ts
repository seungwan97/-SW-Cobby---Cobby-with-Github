import styled from "styled-components";

export const Margin = styled.div`
  display: inline-block;
  margin-top: 5%;
  @media (max-width: 767px) {
    margin-top: 10%;
    @media (max-height: 736px) {
      margin-top: 5%;
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
  }
`;

export const TextMargin = styled.div`
  display: inline-block;
  margin-top: 3%;
  @media (max-width: 767px) {
    margin-top: 7%;
    @media (max-height: 736px) {
      margin-top: 3%;
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
  }
`;

export const CobbyInfoWrapper = styled.div`
  text-align: center;
`;
