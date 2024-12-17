import axios from "axios";

const axiosClient = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL,
})

axiosClient.interceptors.request.use(function (request) {
    if (request.url !== '/accounts/sign-in' || request.url !== '/accounts') {
        request.headers['Authorization'] = `Bearer ${sessionStorage.getItem('token')}`
    }
    return request;
  }, function (error) {
    return Promise.reject(error);
  });

export default axiosClient;