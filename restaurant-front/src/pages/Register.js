import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Auth.css';

function Register() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [role, setRole] = useState('CLIENT'); // Valeur par défaut
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch('http://localhost:8080/api/users/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, email, password, role }),
      });

      if (!response.ok) throw new Error('Erreur lors de l\'inscription');

      navigate('/login');
    } catch (err) {
      console.error(err);
      setError('Erreur lors de l\'inscription');
    }
  };

  return (
    <div className="main">
      <div className="register">
        <label className='label-register'>Inscription</label>
        <form onSubmit={handleSubmit}>
          <input
            className='register-a'
            type="text"
            placeholder="Nom d'utilisateur"
            value={username}
            onChange={(e) => setUsername(e.target.value)}
            required
          />
          <input
            className='register'
            type="text"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
          <input
            className='register'
            type="password"
            placeholder="Mot de passe"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
          <select
            className='register'
            value={role}
            onChange={(e) => setRole(e.target.value)}
          >
            <option value="CLIENT">Client</option>
            <option value="CUISINIER">Cuisinier</option>
            <option value="ADMIN">Admin</option>
          </select>
          <button className='register-button' type="submit">S'inscrire</button>
          {error && <p className="error">{error}</p>}
        </form>
        <span className="switch-link" onClick={() => navigate('/login')}>
          Déjà un compte ? Se connecter
        </span>
      </div>
    </div>
  );
}

export default Register;
