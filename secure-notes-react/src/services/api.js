import axios from "axios";

console.log("API URL:", process.env.REACT_APP_API_URL);

// Create Axios instance
const api = axios.create({
  baseURL: `${ process.env.REACT_APP_API_URL }/api`,
headers: {
  "Content-Type": "application/json",
    Accept: "application/json",
  },
});

// Request interceptor to attach JWT
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("JWT_TOKEN");

    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

// Optional: Response interceptor (for handling 401 globally)
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      console.error("Unauthorized - redirecting to login");

      // Optional: clear token & redirect
      localStorage.removeItem("JWT_TOKEN");
      // window.location.href = "/login";  // enable if needed
    }

    return Promise.reject(error);
  }
);

export default api;

