//서버로 인증을 요청할 uri (서버의 webSecurityConfig의 base uri와 일치해야 한다)
export const API_BASE_URL = "http://localhost:9001";

//서버에서 인증을 완료한 후에 프론트엔드로 돌아올 redirect uri (app.oauth2.authorized-redirect-uri와 일치해야 한다)
export const OAUTH2_REDIRECT_URI = "http://localhost:9001/oauth2/token";

export const GOOGLE_AUTH_URL =
  API_BASE_URL + "/oauth2/authorization/google?redirect_uri=" + "http://localhost:9001/login/oauth2/code/google";
export const FACEBOOK_AUTH_URL = API_BASE_URL + "/oauth2/authorization/facebook?redirect_uri=";
export const NAVER_AUTH_URL =
  API_BASE_URL + "/oauth2/authorization/naver?redirect_uri=" + "http://localhost:9001/login/oauth2/code/naver";
export const KAKAO_AUTH_URL = API_BASE_URL + "/oauth2/authorization/kakao?redirect_uri=" + OAUTH2_REDIRECT_URI;

export const CLOUDFRONT_URL = "https://djaikjbosji2f.cloudfront.net/";
