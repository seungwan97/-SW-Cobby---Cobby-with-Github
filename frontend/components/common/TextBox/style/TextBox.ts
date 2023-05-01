import styled from "styled-components";

export const Text = styled.div<{ size: number }>`
  font-size: ${(props) => props.size}px;
  font-family: "DungGeunMo";
  color: ${(props) => props.color};
  font-weight: normal;
  @media (max-width: 767px) {
    font-size: ${(props) => props.size * 0.7}px;
  }

  @media (min-width: 768px) and (max-width: 991px) {
    // 테블릿 세로
  }

  @media (min-width: 992px) and (max-width: 1199px) {
    // 테블릿 가로
  }
`;
