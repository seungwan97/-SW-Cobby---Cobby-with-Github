import client from "./client";

const req = "/api/main";

// 코비 관련 정보 전체 조회 (코스튬 포함)
export const getAvatarInfo = async (userId: string) => {
  const response = await client.get(`${req}/avatars`, {
    headers: {
      Authorization: `${userId}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 코비 관련 정보 유형별 조회 (코스튬, 칭호, 도전과제)
export const getInventoryItem = async (userId: string, itemType: string) => {
  const response = await client.get(`${req}/avatars/inventories/${itemType}`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 사용자가 가진 코스튬 카테고리별 조회 (HEAD, BODY, EFFECT)
export const getMyCostumes = async (userId: string, itemType: string) => {
  const response = await client.get(
    `${req}/avatars/inventories/costumes/${itemType}`,
    {
      headers: {
        //   Authorization: `Bearer ${accessToken}`,
        userId: `${userId}`,
      },
    }
  );

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
export const getTitles = async () => {
  const response = await client.get(`${req}/titles`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
      //   userId: `${userId}`,
    },
  });

  return response;
};

// 카테고리별 코스튬 아이템 전체 목록 조회
export const getAllItemList = async (category: string) => {
  const response = await client.get(`${req}/costumes/${category}`, {
    headers: {
      //   Authorization: `Bearer ${accessToken}`,
    },
  });

  return response;
};
