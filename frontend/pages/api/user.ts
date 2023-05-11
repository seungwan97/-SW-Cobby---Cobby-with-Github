import client from "./client";

const req = "/api/user";

// 메인페이지 닉네임 정보 불러오기
export const getNickname = async (userId: string) => {
  const response = await client.get(`${req}/users`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getStatus = async (userId: string) => {
  const response = await client.get(`${req}/stat`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getCommitInfo = async (userId: string) => {
  const response = await client.get(`${req}/activityLog/commit`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getAttendanceInfo = async (userId: string) => {
  const response = await client.get(`${req}/activityLog/attendance`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
