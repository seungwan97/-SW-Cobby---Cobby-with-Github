import client from "./client";

const req = "/api/user";

// 메인페이지 닉네임 정보 불러오기
export const getNickname = async (userId: string) => {
  const response = await client.get(`${req}/users`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
      "Content-Type": "application/json",
    },
  });

  return response;
};

// 메인페이지 총 커밋 정보 불러오기
export const getStatus = async (userId: string) => {
  const response = await client.get(`${req}/stat`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
      "Content-Type": "application/json",
    },
  });

  return response;
};

// 메인페이지 연속 커밋, 오늘자 커밋 정보 불러오기
export const getCommitInfo = async (userId: string) => {
  const response = await client.get(`${req}/activityLog/commit`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
      "Content-Type": "application/json",
    },
  });

  return response;
};

// 메인페이지 연속 출석일수 정보 불러오기
export const getAttendanceInfo = async (userId: string) => {
  const response = await client.get(`${req}/activityLog/attendance`, {
    headers: {
      // Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
      "Content-Type": "application/json",
    },
  });

  return response;
};
