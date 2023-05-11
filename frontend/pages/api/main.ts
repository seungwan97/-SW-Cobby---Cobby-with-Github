import client from "./client";

const req = "/api/main";

// 코비 관련 정보 조회 (코스튬 포함)
export const getAvatarInfo = async (userId: string) => {
  const response = await client.get(`${req}/avatars`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 인벤토리 유형 별 코스튬 조회 (머리, 몸통, 효과)
export const getInventoryItem = async (userId: string, itemType: string) => {
  const response = await client.get(`${req}/avatars/inventories/${itemType}`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 코비 관련 정보 수정
export const patchAvatarInfo = async (userId: string, data: {}) => {
  const response = await client.patch(`${req}/avatars`, data, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
      "Content-Type": "application/json",
    },
  });

  return response;
};

// 도전과제 목록 조회
export const getQuests = async (userId: string) => {
  const response = await client.get(`${req}/quests/current`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 칭호 목록 전체조회
export const getTitles = async (userId: string) => {
  const response = await client.get(`${req}/quests/current`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
