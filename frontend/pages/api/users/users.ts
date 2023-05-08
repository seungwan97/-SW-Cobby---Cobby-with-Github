import client from "../client";

// 메인페이지 닉네임 정보 불러오기
export const getNickname = async (accessToken: string, userId: string) => {
  const response = await client.get(`/api/users/${userId}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getStatus = async (accessToken: string, userId: string) => {
  const response = await client.get(`/api/stat/${userId}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return response;
};
