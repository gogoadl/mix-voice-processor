import axios from "axios";

const instance = axios.create({
  baseURL: "localhost:9001/",
  timeout: 1000,
});
// 요청 인터셉터 추가하기
instance.interceptors.request.use(
  (config) => {
    // 요청이 전달되기 전에 작업 수행
    console.log(localStorage.getItem("token"));
    const token = localStorage.getItem("token");
    config.headers.Authorization = token;
    return config;
  },
  (error) => {
    // 요청 오류가 있는 작업 수행
    return Promise.reject(error);
  }
);

// 응답 인터셉터 추가하기
instance.interceptors.response.use(
  (response) => {
    // 2xx 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
    // 응답 데이터가 있는 작업 수행
    return response;
  },
  async (error) => {
    const { response, config } = error;

    if (response.status == 401) {
      const { data } = await axios.get("", {
        baseURL: "localhost:9001/",
        params: {
          token: localStorage.getItem("refreshToken"),
        },
      });

      const { token } = data;
      localStorage.setItem("token", token);
      config.headers["Authorization"] = token;

      return await axios(config);
    }
    // 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
    // 응답 오류가 있는 작업 수행
    return Promise.reject(error);
  }
);

export default instance;
