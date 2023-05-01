import styled from "styled-components";

export const FullDiv = styled.div`
  //   display: inline-block;
  width: 300px;
  height: 10px;
  background-color: #fff;
  margin-top: 0.5%;
  margin-left: 1%;
  border: 2px solid #333333;
  @media (max-width: 767px) {
    width: 257px;
    border: 1px solid #333333;
    @media (max-width: 667px) {
      width: 220px;
      border: 1px solid #333333;
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

export const RangeDiv = styled.div`
  //   display: inline-block;
  width: 70%;
  height: 100%;
  background-color: #333333;
`;
