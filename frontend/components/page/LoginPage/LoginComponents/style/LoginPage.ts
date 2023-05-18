import styled from "styled-components";

export const LoginPageWrapper = styled.div`
  text-align: center;
  @media (max-height: 1180px) {
    height: 600px;
  }

  @media (max-height: 1024px) {
    height: 350px;
  }

  @media (max-height: 915px) {
    height: 430px;
  }

  @media (max-height: 844px) {
    height: 330px;
  }

  @media (max-height: 812px) {
    height: 330px;
  }

  @media (max-height: 736px) {
    height: 250px;
  }

  @media (max-height: 667px) {
    height: 250px;
  }
`;
export const LoginPageContent = styled.div`
  /* 임시  CSS임 => 밑에 NavBar 표시하느라 잠시 넣었음 */
  margin-top: 40%;
  padding: 15px;
  @media (max-width: 1300px) {
    margin-top: 20%;
  }
  @media (max-width: 830px) {
    margin-top: 30%;
  }
  @media (max-width: 600px) {
    margin-top: 45%;
  }
  @media (max-width: 500px) {
    margin-top: 55%;
  }
  @media (max-width: 400px) {
    margin-top: 65%;
  }
  @media (max-width: 300px) {
    margin-top: 75%;
  }
  @media (max-width: 200px) {
    margin-top: 85%;
  }
`;

export const LogoMargin = styled.div`
  display: inline-block;
  height: 10%;
`;

export const LoginBtnMargin = styled.div`
  display: inline-block;
  height: 0%;
`;
