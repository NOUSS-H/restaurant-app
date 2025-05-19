// services/api.js
import axios from 'axios';

const API_BASE = 'http://localhost:8080/api';

// Authentification
export const registerUser = (data) => axios.post(`${API_BASE}/users/register`, data);
export const loginUser = (data) => axios.post(`${API_BASE}/users/login`, data);

// Plats & catégories
export const getAllDishes = () => axios.get(`${API_BASE}/dishes`);
export const getAllCategories = () => axios.get(`${API_BASE}/categories`);

// Commandes
export const createOrder = (data) => axios.post(`${API_BASE}/orders`, data);
export const getAllOrders = () => axios.get(`${API_BASE}/orders`);
export const getClientOrders = (username) => axios.get(`${API_BASE}/orders/client/${username}`);
export const getCookOrders = (username) => axios.get(`${API_BASE}/orders/cook/${username}`);
export const updateOrderStatus = (id, status, cookUsername) =>
  axios.put(`${API_BASE}/orders/${id}/status?status=${status}&cookUsername=${cookUsername}`);

// Panier
export const getCartItems = (username) => axios.get(`${API_BASE}/cart/${username}`);
