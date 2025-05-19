import React from 'react';
import '../pages/Menu.css'; // Importe le bon fichier CSS

const DishCard = ({ dish, onAdd }) => {
  return (
    <div className="card">
      <div className="card-image">
        <img src={dish.imageUrl} alt={dish.name} />
      </div>

      <div className="card-text">
        <p className="card-meal-type">Plat principal</p>
        <h2 className="card-title">{dish.name}</h2>
        <p className="card-body">{dish.description}</p>
      </div>

      <div style={{ display: 'flex', alignItems: 'center', marginTop: '10px' }}>
        <div className="card-price">{dish.price.toFixed(2)} €</div>
        <button
        className='add-button'
          onClick={() => onAdd(dish)}
          type="text"
          style={{
            backgroundColor: '#F2901D',
            border: 'none',
            color: 'white',
            fontWeight: 'bold',
            padding: '10px 20px',
            marginLeft: 'auto',
            marginRight: '20px',
            borderRadius: '5px',
            cursor: 'pointer'
          }}
        >
          Ajouter
        </button>
      </div>
    </div>
  );
};

export default DishCard;
