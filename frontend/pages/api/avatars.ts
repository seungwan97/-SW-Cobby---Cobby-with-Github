import client from "./client";

const req = "/api/avatars";

// 아바타 조회
export const showCobbyInfo = async (
  accessToken: string,
  userId: string
) => {
  const response = await client.get(`${req}`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
