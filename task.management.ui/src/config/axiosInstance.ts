import axios, { AxiosInstance } from "axios";
import BASE_URL from "./APIConfig";

const instance: AxiosInstance = axios.create(
    { baseURL: BASE_URL }
);

instance.interceptors.request.use(
    (config) => {
        return config;
    },
    (error) => { return Promise.reject(error) }
);

export default instance;