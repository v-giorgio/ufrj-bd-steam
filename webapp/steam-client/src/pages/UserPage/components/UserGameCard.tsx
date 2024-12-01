import React from 'react';
import styles from './UserGameCard.module.css';

interface UserGameCardProps {
  gameName: string;
  gameImage: string;
  hoursPlayed: number;
  totalAchievements: number;
  obtainedAchievements: number;
}

const UserGameCard: React.FC<UserGameCardProps> = ({
  gameName,
  gameImage,
  hoursPlayed,
  totalAchievements,
  obtainedAchievements,
}) => {
  return (
    <div className={styles.activityCard}>
      <img className={styles.gameImage} src={gameImage} alt={`${gameName}`} />
      <div className={styles.gameInfo}>
        <p className={styles.gameTitle}>{gameName}</p>
        <p className={styles.hoursPlayed}>{hoursPlayed.toFixed(1)} horas registradas</p>
        <div className={styles.achievement}>
          <p className={styles.progress}>
            Progresso em Conquistas: {obtainedAchievements} de {totalAchievements}
          </p>
        </div>
      </div>
    </div>
  );
};

export default UserGameCard;
