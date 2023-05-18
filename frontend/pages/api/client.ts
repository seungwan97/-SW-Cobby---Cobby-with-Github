import axios from "axios";

const client = axios.create();

//순서대로 AUTH, USER, MAIN, STORE, RANK 서버 포트번호
const port = [15010, 15020, 15030, 15040, 15050];
// 로컬 서버 용 (배열 인덱스값만 바꿔서 사용)
// client.defaults.baseURL = `http://localhost:${port[1]}`;

// 로컬 리다이렉트 용 (location.href)
<<<<<<< HEAD
client.defaults.url = "http://localhost:3000";
=======
// client.defaults.url = "http://localhost:3000";
>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34

// 배포 서버 용
client.defaults.baseURL = `https://cobby-play.com`;
// client.defaults.baseURL = `https://k8b201.p.ssafy.io`;

// client.defaults.url = "https://cobby-play.com/";

export default client;
