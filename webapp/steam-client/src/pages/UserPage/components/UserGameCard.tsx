import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './UserGameCard.module.css';

interface UserGameCardProps {
  userId: string;
  gameId: number;
  gameName: string;
  gameImage: string;
  hoursPlayed: string;
  totalAchievements: number;
  obtainedAchievements: number;
}

const UserGameCard: React.FC<UserGameCardProps> = ({
  userId,
  gameId,
  gameName,
  gameImage,
  hoursPlayed,
  totalAchievements,
  obtainedAchievements,
}) => {
  const navigate = useNavigate();

  const handleClick = () => {
    navigate(`/user/${userId}/game/${gameId}/achievements`);
  };

  return (
    <div className={styles.activityCard} onClick={handleClick}>
      <img className={styles.gameImage} src={gameImage} alt={`${gameName}`} />
      <div className={styles.gameInfo}>
        <p className={styles.gameTitle}>{gameName}</p>
        <p className={styles.hoursPlayed}>{hoursPlayed} horas registradas</p>
        <div className={styles.achievement}>
          <p className={styles.progress}>
            Progresso em Conquistas: {obtainedAchievements} de {totalAchievements}
          </p>
          <div className={styles.progressBar}>
            <div
              className={styles.progressFill}
              style={{
                width: `${(obtainedAchievements / totalAchievements) * 100}%`,
              }}
            ></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserGameCard;
