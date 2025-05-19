import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import '../styles/App.css';

const Orders = () => {
  const [orders, setOrders] = useState([]);
  const [user, setUser] = useState(null);

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));

    if (!storedUser || storedUser.role !== 'CLIENT') {
      alert('Seuls les clients peuvent voir leurs commandes.');
      window.location.href = '/login';
      return;
    }

    setUser(storedUser);

    // ✅ Appel direct au bon endpoint
    fetch(`http://localhost:8080/api/orders/client/${storedUser.username}`)
      .then(response => {
        if (!response.ok) throw new Error("Erreur lors du chargement des commandes");
        return response.json();
      })
      .then(data => setOrders(data))
      .catch(error => {
        console.error('Erreur :', error);
        alert("Impossible de récupérer les commandes.");
      });
  }, []);

  if (!user) return null;

  return (
    <>
      <Navbar />
      <div className="card">
        <h2>Mes commandes</h2>
        {orders.length === 0 ? (
          <p>Aucune commande passée.</p>
        ) : (
          orders.map(order => (
            <div key={order.id} className="order-card">
              <p><strong>Commande #{order.id}</strong></p>
              <p><strong>État :</strong> {order.status}</p>
              <p><strong>Pour :</strong> {order.numberOfPeople} personne(s)</p>
              <p><strong>Note :</strong> {order.note || 'Aucune'}</p>
              <ul>
                {order.items.map((item, index) => (
                  <li key={index}>{item.dishName} × {item.quantity}</li>
                ))}
              </ul>
              <hr />
            </div>
          ))
        )}
      </div>
    </>
  );
};

export default Orders;
