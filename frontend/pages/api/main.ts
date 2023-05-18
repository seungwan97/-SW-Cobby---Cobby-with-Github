import client from "./client";

const req = "/api/main";

// 코비 관련 정보 전체 조회 (코스튬 포함)
export const getAvatarInfo = async (userId: string) => {
  const response = await client.get(`${req}/avatars`, {
    headers: {
      Authorization: `${userId}`,
<<<<<<< HEAD
=======
      withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    },
  });

  return response;
};

// 코비 관련 정보 유형별 조회 (코스튬, 칭호, 도전과제)
<<<<<<< HEAD
export const getInventoryItem = async (userId: string, itemType: string) => {
=======
export const getInventoryItem = async (token: string, itemType: string) => {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  const response = await client.get(
    `${req}/avatars/inventories/costumes/${itemType}`,
    {
      headers: {
<<<<<<< HEAD
        Authorization: `${userId}`,
=======
        Authorization: `${token}`,
        withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      },
    }
  );

  return response;
};

// 사용자가 가진 코스튬 카테고리별 조회 (HEAD, BODY, EFFECT)
<<<<<<< HEAD
export const getMyCostumes = async (userId: string, itemType: string) => {
=======
export const getMyCostumes = async (token: string, itemType: string) => {
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
  const response = await client.get(
    `${req}/avatars/inventories/costumes/${itemType}`,
    {
      headers: {
<<<<<<< HEAD
        Authorization: `${userId}`,
=======
        Authorization: `${token}`,
        withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
      },
    }
  );

  return response;
};

// 코비 관련 정보 수정
export const patchAvatarInfo = async (token: string, data: {}) => {
  const response = await client.patch(`${req}/avatars`, data, {
    headers: {
      Authorization: token,
      "Content-Type": "application/json",
<<<<<<< HEAD
=======
      withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    },
  });

  return response;
};

// 도전과제 목록 조회
export const getQuests = async (token: string) => {
  const response = await client.get(`${req}/quests/current`, {
    headers: {
      Authorization: token,
<<<<<<< HEAD
=======
      withCredentials: true
    },
  });

  return response;
};

// 도전과제 아이템 수령
export const getQuestItem = async (token: string, questId: number) => {
  const response = await client.get(`${req}/quests/getItem/${questId}`, {
    headers: {
      Authorization: token,
      withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    },
  });

  return response;
};

// 칭호 목록 전체조회
export const getTitles = async () => {
<<<<<<< HEAD
  const response = await client.get(`${req}/titles`, {});
=======
  const response = await client.get(`${req}/titles`, { withCredentials: true });
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

  return response;
};

// 카테고리별 코스튬 아이템 전체 목록 조회
export const getAllItemList = async (category: string, token: string) => {
  const response = await client.get(`${req}/costumes/${category}`, {
    headers: {
      Authorization: token,
<<<<<<< HEAD
=======
      withCredentials: true
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
    },
  });

  return response;
};
<<<<<<< HEAD
=======

// 카테고리별 코스튬 아이템 전체 목록 조회
export const patchInventories = async (costumeID: string, token: string) => {
  const response = await client.patch(
    `${req}/avatars/inventories`,
    {
      costumeId: costumeID,
    },
    {
      headers: {
        Authorization: token,
        withCredentials: true
      },
    }
  );

  return response;
};
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
