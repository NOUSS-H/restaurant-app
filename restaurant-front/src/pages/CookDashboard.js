// pages/CookDashboard.js
import React, { useEffect, useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/CookDashboard.css';
import { UserContext } from '../context/UserContext';
import { getCookOrders } from '../services/api';

function CookDashboard() {
  const { user } = useContext(UserContext);
  const navigate = useNavigate();

  const [orders, setOrders] = useState([]);

  useEffect(() => {
    if (!user || user.role !== 'CUISINIER') {
      navigate('/login');
      return;
    }

    getCookOrders(user.username)
      .then(res => setOrders(res.data))
      .catch(err => console.error("Erreur chargement commandes :", err));
  }, [user, navigate]);

  if (!user) return <p>Chargement...</p>;

  return (
    <div className="cook-dashboard">
      <h2>Bienvenue Chef {user.username}</h2>
      <p className="role-indicator">Connecté en tant que : {user.role}</p>

      <div className="section">
        <h3>Commandes à Préparer</h3>
        {orders.length === 0 ? (
          <p>Aucune commande à préparer.</p>
        ) : (
          orders.map((order) => (
            <div key={order.id} className="order-card">
              <p><strong>Commande n°:</strong> {order.id}</p>
              <p><strong>Client:</strong> {order.clientUsername}</p>
              {order.note && <p><strong>Note:</strong> {order.note}</p>}
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

export default CookDashboard;
