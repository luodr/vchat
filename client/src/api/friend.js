import { post, get } from "./request";
export const getFriends = async data => {
  return get("friend/list", data);
};
export const deleteFriends = async data => {
  return await post("friend/deleteFriend", data);
};
