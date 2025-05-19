import React, { useContext, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './Navbar.css';
import logo from '../assets/logo-1.png';
import { UserContext } from '../context/UserContext';

function Navbar() {
  const [isOpen, setIsOpen] = useState(false);
  const { user, logout } = useContext(UserContext);
  const navigate = useNavigate();

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  const handleLogout = () => {
    logout();
    navigate('/');
  };

  return (
    <nav className="navbar">
      <div className="navbar-content">
        <img src={logo} alt="Logo" className="navbar-logo" />
        <div className="menu-icon" onClick={toggleMenu}>☰</div>
        <div className={`navbar-links ${isOpen ? 'active' : ''}`}>
          <Link to="/" onClick={toggleMenu}>Accueil</Link>
          <Link to="/menu" onClick={toggleMenu}>Menu</Link>

         
  <>
    <Link to="/cart" onClick={toggleMenu}>
      Panier
      <span id="cart-count">
        ({JSON.parse(localStorage.getItem('cart'))?.length || 0})
      </span>
    </Link>
    <Link to="/client" onClick={toggleMenu}>Mon compte</Link>
  </>

             

          {user && user.role === 'CUISINIER' && (
            <Link to="/cook" onClick={toggleMenu}>Commandes à préparer</Link>
          )}

          {user && user.role === 'ADMIN' && (
            <Link to="/admin" onClick={toggleMenu}>Dashboard Admin</Link>
          )}

          {user ? (
            <span onClick={handleLogout} className="logout-link">Déconnexion</span>
          ) : (
            <Link to="/login" onClick={toggleMenu}>Connexion</Link>
          )}

          {/* ✅ Ajout de l'indicateur de rôle */}
          {user && (
            <div className="user-role-indicator">
              Connecté en tant que : <strong>{user.role}</strong>
            </div>
          )}
        </div>
      </div>
    </nav>
  );
}

export default Navbar;
