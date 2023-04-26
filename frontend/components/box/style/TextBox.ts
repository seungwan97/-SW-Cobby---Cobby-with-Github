import styled from "styled-components";

export const Text = styled.div<{ size: number }>`
  font-size: ${(props) => props.size}px;
  color: #333333;
`;
