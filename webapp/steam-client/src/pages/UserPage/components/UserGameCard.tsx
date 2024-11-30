
import React from 'react';
import styles from './UserGameCard.module.css';
import trophy from '../../../assets/trophy.png'

const UserGameCard: React.FC = () => {

  return (
    <a href="/games" className={styles.activityCardLink}>
        <div className={styles.activityCard}>
            <img
            className={styles.gameImage}
            src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/header.jpg?t=1729702322"
            alt="Counter-Strike"
            />
            <div className={styles.gameInfo}>
                <p className={styles.gameTitle}>Counter-Strike</p>
                <p className={styles.hoursPlayed}>23.1 horas registradas</p>
                <div className={styles.achievement}>
                    <p className={styles.progress}>Progresso em Conquistas: 1 de 15</p>
                        <img
                        className={styles.trophyIcon}
                        src=  {trophy}
                        alt="Trophy"
                        />
                </div>
            </div>
        </div>
    </a>
);
};

export default UserGameCard;
            