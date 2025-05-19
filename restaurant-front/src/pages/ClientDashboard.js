// pages/ClientDashboard.js
import React, { useEffect, useState, useContext } from 'react';
import '../styles/ClientDashboard.css';
import { UserContext } from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import { getClientOrders, getCartItems } from '../services/api';

function ClientDashboard() {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();

  const [cartItems, setCartItems] = useState([]);
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    if (!user || user.role !== 'CLIENT') {
      navigate('/client');
      return;
    }

    getClientOrders(user.username)
      .then(res => setOrders(res.data))
      .catch(err => console.error("Erreur chargement commandes :", err));

    getCartItems(user.username)
      .then(res => setCartItems(res.data))
      .catch(err => console.error("Erreur chargement panier :", err));
  }, [user, navigate]);

  if (!user) return <p>Chargement...</p>;

  return (
    <div className="client-dashboard">
      <h2>Bienvenue {user.username}</h2>

      <div className="section">
        <h3>Mon Panier</h3>
        {cartItems.length === 0 ? (
          <p>Votre panier est vide.</p>
        ) : (
          <ul>
            {cartItems.map((item, idx) => (
              <li key={idx}>
                {item.dishName} × {item.quantity}
              </li>
            ))}
          </ul>
        )}
      </div>

      <div className="section">
        <h3>Mes Commandes</h3>
        {orders.length === 0 ? (
          <p>Aucune commande effectuée.</p>
        ) : (
          orders.map((order) => (
            <div key={order.id} className="order-card">
              <p><strong>Commande n°:</strong> {order.id}</p>
              <p><strong>État:</strong> {order.status}</p>
              <ul>
                {order.items.map((item, i) => (
                  <li key={i}>{item.dishName} × {item.quantity}</li>
                ))}
              </ul>
            </div>
          ))
        )}
      </div>
    </div>
  );
}

export default ClientDashboard;
