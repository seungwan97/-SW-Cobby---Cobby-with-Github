// global-style.ts
import { createGlobalStyle } from "styled-components";

export const GlobalStyle = createGlobalStyle`
    @font-face {
      font-family: "DungGeunMo";
      src: url("/fonts/DungGeunMo.ttf");
    }
    html{
      margin: 0;
      padding: 0;
      background-color: #333333;
    }
    body {
       /* 스크롤바 없에기 */
    -ms-overflow-style: none;
    height: 100vh;
    background-color: #fffff8;
    text-align: center;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%,-50%);
    }
`;

export const colors = createGlobalStyle`
  
`;
