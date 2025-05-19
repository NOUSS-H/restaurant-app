import React, { useEffect, useState } from 'react';
import DishCard from '../components/DishCard';
import { getAllDishes } from '../services/api';
import '../pages/Menu.css';

const Menu = () => {
  const [dishes, setDishes] = useState([]);

  useEffect(() => {
    getAllDishes().then(res => setDishes(res.data));
  }, []);

  const handleAdd = (dish) => {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];
    cart.push(dish);
    localStorage.setItem('cart', JSON.stringify(cart));
  };

  return (
    <div className="menu-page">
      

      <div className="container">
        {dishes.map(dish => (
          <DishCard key={dish.id} dish={dish} onAdd={handleAdd} />
        ))}
      </div>
    </div>
  );
};

export default Menu;
