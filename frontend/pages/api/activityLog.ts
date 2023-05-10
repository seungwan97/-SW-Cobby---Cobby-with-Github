import client from "./client";

const req = "/api/activityLog";

// 메인페이지 github status 정보 불러오기
export const getCommitInfo = async (accessToken: string, userId: string) => {
  const response = await client.get(`${req}/commit`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};

// 메인페이지 github status 정보 불러오기
export const getAttendanceInfo = async (
  accessToken: string,
  userId: string
) => {
  const response = await client.get(`${req}/attendance`, {
    headers: {
      Authorization: `Bearer ${accessToken}`,
      userId: `${userId}`,
    },
  });

  return response;
};
