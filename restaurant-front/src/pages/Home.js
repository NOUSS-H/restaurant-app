import React from "react";
import "./Home.css";
import { Link } from 'react-router-dom';
import backgroundImage from "../assets/background.png";



function Home() {
  return (
    <div
      className="home-container"
      style={{
        backgroundImage: `url(${backgroundImage})`,
        backgroundSize: "cover",
        backgroundPosition: "center",
        minHeight: "63vh",
      }}
    >
      <div className="home-content">
        <h1>Bienvenue au Noussa Food</h1>
        <p>Découvrez notre carte gourmande et passez commande en ligne !</p>
        <Link to="/menu" className="menu-button">Voir le Menu</Link>
      </div>
    </div>
  );
}

export default Home;
