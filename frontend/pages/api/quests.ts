import client from "./client";

const req = "/api/quests";

// 도전과제 목록 조회
export const getCurrentQuest = async (accessToken: string, userId: string) => {
  const response = await client.get(`${req}/current`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 도전과제 생성 (도전과제 완료 후 새로운 도전과제 받기)
export const postUpdateQuest = async (accessToken: string, data: {}) => {
  const response = await client.post(`${req}/new`, data, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
    },
  });

  return response;
};
