import client from "./client";

const req = "/api/stat";

// 메인페이지 github status 정보 불러오기
export const getStatus = async (accessToken: string, userId: string) => {
  const response = await client.get(`${req}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
