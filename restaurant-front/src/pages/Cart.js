
import React, { useEffect, useState } from 'react';
import '../pages/Cart.css';
import { useNavigate } from 'react-router-dom';
import { createOrder } from '../services/api';

const Cart = () => {
  const [cart, setCart] = useState([]);
  const [note, setNote] = useState('');
  const [numberOfPeople, setNumberOfPeople] = useState(1);
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = JSON.parse(localStorage.getItem('user'));
    const storedCart = JSON.parse(localStorage.getItem('cart')) || [];

    if (!storedUser || storedUser.role !== 'CLIENT') {
      alert('Seuls les clients peuvent accéder au panier.');
      navigate('/login');
      return;
    }

    setUser(storedUser);
    setCart(
      storedCart.map(item => ({
        ...item,
        quantity: item.quantity || 1
      }))
    );
  }, [navigate]);

  const updateCart = (index, quantity) => {
    const updatedCart = [...cart];
    updatedCart[index].quantity = quantity;
    setCart(updatedCart);
    localStorage.setItem('cart', JSON.stringify(updatedCart));
  };

  const removeFromCart = (index) => {
    const updatedCart = cart.filter((_, i) => i !== index);
    setCart(updatedCart);
    localStorage.setItem('cart', JSON.stringify(updatedCart));
  };

  const totalPrice = cart.reduce(
    (total, item) => total + item.price * item.quantity,
    0
  );

  const handleOrder = () => {
    if (!user || user.role !== 'CLIENT') {
      alert('Seuls les clients peuvent passer une commande.');
      return;
    }

    const orderRequest = {
      clientUsername: user.username,
      numberOfPeople,
      note,
      items: cart.map(item => ({
        dishId: item.id,
        quantity: item.quantity
      }))
    };

    createOrder(orderRequest)
      .then(() => {
        alert('Commande passée avec succès !');
        localStorage.removeItem('cart');
        setCart([]);
        setNote('');
        setNumberOfPeople(1);
        navigate('/client');
      })
      .catch(error => {
        console.error('Erreur :', error);
        alert('Échec de la commande');
      });
  };

  if (!user) return <p>Chargement...</p>;

  return (
    <div className="cart-container">
      <div className="cart-box">
        <h2>Votre panier</h2>
        {cart.length === 0 ? (
          <p>Votre panier est vide.</p>
        ) : (
          cart.map((item, index) => (
            <div key={index} className="cart-item">
              <span>{item.name} - {item.price.toFixed(2)} €</span>
              <input
                type="number"
                value={item.quantity}
                min="1"
                onChange={(e) => updateCart(index, parseInt(e.target.value))}
              />
              <button onClick={() => removeFromCart(index)}>Supprimer</button>
            </div>
          ))
        )}

        <h3>Total : {totalPrice.toFixed(2)} €</h3>

        <div className="cart-input-group">
          <label htmlFor="numberOfPeople">Nombre de personnes :</label>
          <input
            type="number"
            id="numberOfPeople"
            value={numberOfPeople}
            min="1"
            onChange={(e) => setNumberOfPeople(parseInt(e.target.value) || 1)}
          />
        </div>

        <div className="cart-input-group">
          <label htmlFor="note">Remarque :</label>
          <textarea
            id="note"
            value={note}
            onChange={(e) => setNote(e.target.value)}
            placeholder="Ex : pas d'oignons, allergie au gluten..."
          ></textarea>
        </div>

        {cart.length > 0 && (
          <button className="order-btn" type="submit" onClick={handleOrder}>Commander</button>
        )}
      </div>
    </div>
  );
};

export default Cart;
