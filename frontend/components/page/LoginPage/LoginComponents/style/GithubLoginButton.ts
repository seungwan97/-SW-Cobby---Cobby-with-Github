import styled from "styled-components";

export const LoginButton = styled.button`
  padding: 0.3em 1em;
  border-radius: 0.25em;
  font-size: 1.2rem;
  display: flex;
  align-items: center;
  font-weight: 400;
  box-shadow: var(--shadow-1);
  background-color: #1b1f23;
  color: white;
  margin: 0 auto;
  :hover {
    cursor: pointer;
    font-size: 1.3rem;
    transition: 0.7s;
  }
`;

export const GithubLogo = styled.img`
  height: 2.5rem;
  margin-right: 0.5em;
  margin-left: -0.3em;
`;
