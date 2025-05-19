import React, { useState, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Auth.css';
import { UserContext } from '../context/UserContext'; // ⬅️ Import du contexte

function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const { login } = useContext(UserContext); // ⬅️ Utilisation du contexte

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/users/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password }),
      });

      if (!response.ok) throw new Error('Échec de la connexion');

      const user = await response.json();

// ⬇️ Mise à jour du contexte utilisateur
login(user);

// ⬇️ Sauvegarde dans le localStorage pour persistance
localStorage.setItem('user', JSON.stringify(user));

// Redirection selon le rôle
switch (user.role) {
  case 'ADMIN':
    navigate('/admin');
    break;
  case 'CUISINIER':
    navigate('/cook');
    break;
  case 'CLIENT':
    navigate('/menu');
    break;
  default:
    setError('Rôle non reconnu');
}

    } catch (err) {
      console.error(err);
      setError('Identifiants invalides ou erreur serveur');
    }
  };

  return (
    <div className="main">
      <div className="login">
        <label className='label-register'>Connexion</label>
        <form onSubmit={handleSubmit}>
          <input
            className='register'
            type="text"
            name="username"        // ✅ Ajouté
            placeholder="Nom d'utilisateur"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            className='register'
            type="password"
            name="password" 
            placeholder="Mot de passe"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <button className='register-button' type="submit">Se connecter</button>
          {error && <p className="error">{error}</p>}
        </form>
        <span className="switch-link" onClick={() => navigate('/register')}>
          Pas encore de compte ? S'inscrire
        </span>
      </div>
    </div>
  );
}

export default Login;
