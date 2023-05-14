import client from "./client";

const req = "/api/auth";

// 액세스 토큰 발급
export const getAccessToken = async () => {
  const response = await client.get(`${req}/reissue`, {
    headers: {
      Authorization: `token`,
    },
  });

  return response;
};

// 리프레시 토큰 발급
export const getRefreshToken = async () => {
  const response = await client.get(`${req}/reissue`, {
    headers: {
      Cookie: `token`,
    },
  });

  return response;
};
