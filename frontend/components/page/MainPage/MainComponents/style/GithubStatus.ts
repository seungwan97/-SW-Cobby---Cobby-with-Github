import styled from "styled-components";

export const StatusContainer = styled.div`
  width: 335px;
  height: 200px;
  border: 2px solid #333333;
  margin: 0 auto;
  margin-top: 5%;
  text-align: center;
  @media (max-width: 767px) {
    width: 280px;
    height: 160px;
    border: 1px solid #333333;
    margin-top: 8%;
    @media (max-width: 667px) {
      width: 243px;
      height: 160px;
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

export const StatusBar = styled.div`
  width: 323px;
  height: 20px;
  background-color: #333333;
  margin: 0 auto;
  margin-top: 2.5%;
  display: flex;
  justify-content: flex-end;
  align-items: center;
  @media (max-width: 767px) {
    width: 273px;
    height: 10px;
    margin-top: 1.5%;
    @media (max-width: 667px) {
      width: 237px;
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

export const StatusBox1 = styled.div`
  width: 12px;
  height: 12px;
  background-color: #fffff8;
  margin-right: 1.5%;
  display: flex;
  justify-content: center;
  align-items: flex-end;
  @media (max-width: 767px) {
    width: 8px;
    height: 8px;
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

export const StatusBox2 = styled.div`
  width: 12px;
  height: 12px;
  background-color: #fffff8;
  margin-right: 1.5%;
  display: flex;
  justify-content: center;
  align-items: center;
  @media (max-width: 767px) {
    width: 8px;
    height: 8px;
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

export const StatusBox3 = styled.div`
  width: 12px;
  height: 12px;
  background-color: #fffff8;
  margin-right: 1.5%;
  display: flex;
  justify-content: center;
  align-items: flex-start;
  @media (max-width: 767px) {
    width: 8px;
    height: 8px;
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

export const MinusIcon = styled.div`
  width: 8.5px;
  height: 2px;
  background-color: #333333;
  margin-bottom: 2px;
  @media (max-width: 767px) {
    width: 6px;
    height: 1px;
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
export const SquareIcon = styled.div`
  width: 5px;
  height: 5px;
  border: 2px solid #333333;
  @media (max-width: 767px) {
    width: 4.7px;
    height: 3.5px;
    border: 1px solid #333333;
    @media (max-width: 667px) {
      width: 3.5px;
      height: 3px;
      border: 1.5px solid #333333;
      margin-right: 1px;
      margin-top: 0.5px;
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

//XIcon
export const Sect01 = styled.div`
  position: relative;
  width: 64px; /* X 사이즈 */
  height: 64px; /* X 세로 중앙 */
  margin: 0 auto; /* 가운데 정렬 */
`;

export const LineBox = styled.div`
  margin-top: 50%;
  transform: translate(-50%, -50%);
  width: 100%;
  height: 100%;
  span {
    position: absolute;
    top: 50%;
    width: 100%;
    height: 0.1rem;
    background-color: #000;
  }
  @media (max-width: 767px) {
    span {
      height: 0.05rem;
    }
    @media (max-width: 667px) {
      span {
        height: 0.1px;
      }
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

export const Line01 = styled.span`
  transform: rotate(135deg) translateX(0%);
`;

export const Line02 = styled.span`
  transform: rotate(45deg) translateX(0%);
`;

export const Margin = styled.div`
  display: inline-block;
  margin-top: 5%;
`;
