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
      width: 90vw;
      height: 90vh;
    }
    body {
       /* 스크롤바 없에기 */
    -ms-overflow-style: none;
    height: 100vh;
    background-color: #fffff8;
    position: absolute;
    top: 50%;
    left: 50%;
    width:30%;
    transform: translate(-50%,-50%);
    }

    @media (max-width: 1300px){
        //모바일
          html{
          margin: 0;
          padding: 0;
          background-color: white;
          width: 90vw;
          height: 90vh;
        }

        body {
          /* 스크롤바 없에기 */
          margin:0;
          padding: 0;
          -ms-overflow-style: none;
          width: 100%;
          height: 100%;
          background-color: #fffff8;
        }         
    }
`;

export const colors = createGlobalStyle`
  
`;
