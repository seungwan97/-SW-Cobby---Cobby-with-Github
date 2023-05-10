import client from "../client";

// 메인페이지 닉네임 정보 불러오기
export const getNickname = async (accessToken: string, userId: string) => {
  const response = await client.get(`/api/users`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getStatus = async (accessToken: string, userId: string) => {
  const response = await client.get(`/api/stat`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
