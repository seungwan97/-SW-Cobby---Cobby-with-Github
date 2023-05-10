import client from "./client";

// 아바타 조회
export const showCobbyInfo = async (accessToken: string, userId: string) => {
  const response = await client.get(`/api/avatars`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
