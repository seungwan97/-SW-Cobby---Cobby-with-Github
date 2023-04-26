// global-style.ts
import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`
    @font-face {
      font-family: "DungGeunMo";
      src: url("@/fonts/DungGeunMo.ttf");
    }
    body {
      margin: 0;
      /* 스크롤바 없에기 */
      -ms-overflow-style: none;
      position: absolute;
      height: 8%;
      width: 50%;
      left: 25%;
      z-index: 10;
      background-color:#fffff8;
    }
`;

export const colors = createGlobalStyle`
  
`;
