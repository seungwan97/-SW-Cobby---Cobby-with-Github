import styled from "styled-components";

export const Text = styled.div<{ size: number }>`
  font-size: ${(props) => props.size}px;
  font-family: "DungGeunMo";
  color: ${(props) => props.color};
  font-weight: bolder;
`;
