// pages/AdminDashboard.js
import React, { useContext, useEffect, useState } from 'react';
import '../styles/AdminDashboard.css';
import { UserContext } from '../context/UserContext';
import { useNavigate } from 'react-router-dom';
import { getAllOrders } from '../services/api';

function AdminDashboard() {
  const { user } = useContext(UserContext);
  const [orders, setOrders] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!user || user.role !== 'ADMIN') {
      navigate('/login');
      return;
    }

    getAllOrders()
      .then(res => setOrders(res.data))
      .catch(err => console.error('Erreur chargement commandes :', err));
  }, [user, navigate]);

  return (
    <div className="admin-dashboard">
      <h2>Tableau de Bord Admin</h2>
      <div className="section">
        <h3>Toutes les Commandes</h3>
        {orders.length === 0 ? (
          <p>Aucune commande à afficher.</p>
        ) : (
          orders.map((order) => (
            <div key={order.id} className="order-card">
              <p><strong>Commande n°:</strong> {order.id}</p>
              <p><strong>Client:</strong> {order.clientUsername}</p>
              <p><strong>Cuisinier:</strong> {order.cookUsername || 'Non assigné'}</p>
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

export default AdminDashboard;
