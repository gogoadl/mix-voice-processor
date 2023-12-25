// action - state management
import * as actionTypes from "./actions";

export const initialState = {
  userInfo: {},
};

const userReducer = (state = initialState, action: any) => {
  switch (action.type) {
    case actionTypes.LOGIN_USER:
      console.log(action.data);
      return {
        ...state,
        userInfo: { ...action.data },
      };
    case actionTypes.LOGOUT_USER:
      localStorage.removeItem("token");
      localStorage.removeItem("refreshToken");
      return {
        ...state,
        userInfo: {},
      };
    default:
      return state;
  }
};

export default userReducer;
