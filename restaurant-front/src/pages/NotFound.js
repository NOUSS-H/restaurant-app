import React from 'react';
import Navbar from '../components/Navbar';
import '../styles/App.css';

const NotFound = () => {
  return (
    <>
      <Navbar />
      <div className="card not-found">
        <img
          src="https://cdn-icons-png.flaticon.com/512/2748/2748558.png"
          alt="404 Not Found"
          style={{ width: '150px', marginBottom: '20px' }}
        />
        <h2>404 - Page non trouvée</h2>
        <p>Oups ! La page que vous cherchez n'existe pas.</p>
        <a href="/" className="btn-return">Retour à l'accueil</a>
      </div>
    </>
  );
};

export default NotFound;
