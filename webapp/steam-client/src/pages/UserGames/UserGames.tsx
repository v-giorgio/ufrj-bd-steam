
import React, { useState, useEffect } from 'react';
import styles from './UserGames.module.css';

import UserGameCard from '../UserPage/components/UserGameCard'


const UserGames: React.FC = () => {

  return (
    <body className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}>Lista de jogos de john-miller</p>
        

      </div>
    </body>
);
};

export default UserGames;