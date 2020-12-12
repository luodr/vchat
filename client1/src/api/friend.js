import { post, get } from "./request";
export const getFriends =  data => {
  return get("friend/list", data);
};
export const deleteFriends =  data => {
  return  post("friend/deleteFriend", data);
};
