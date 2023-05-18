import { Cookies } from "react-cookie";

const cookies = new Cookies();

export const setCookie = (name: string, value: string, option?: any) => {
  return cookies.set(name, value, { ...option });
};

export const getCookie = (name: string) => {
  return cookies.get(name);
};
<<<<<<< HEAD
=======

>>>>>>> b0bd697a84067e765ab6e03479a065209faf7f34
