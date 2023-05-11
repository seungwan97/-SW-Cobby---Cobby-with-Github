import client from "./client";

const req = "/api/users";

// 메인페이지 닉네임 정보 불러오기
export const getNickname = async (accessToken: string, userId: string) => {
  const response = await client.get(`${req}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
