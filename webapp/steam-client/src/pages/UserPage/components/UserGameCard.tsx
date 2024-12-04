import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './UserGameCard.module.css';

interface UserGameCardProps {
  userId: number;
  gameId: number;
  gameName: string;
  gameImage: string;
  hoursPlayed: number;
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
    const id = userId.toString()
    navigate(`/user/${id}/game/${gameId}/achievements`);
  };

  return (
    <div className={styles.activityCard} onClick={handleClick}>
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
