import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styles from './AchievementsPage.module.css';

import Trophy from '../../assets/trophy.png'

interface Achievement {
  name: string;
  obtained: boolean;
  obtainedPercentage: number;
}

const AchievementsPage: React.FC = () => {
  const { userId, gameId } = useParams<{ userId: string; gameId: string }>();
  const [achievements, setAchievements] = useState<Achievement[]>([]);
  const [nickname, setNickname] = useState<string>('');
  const [avatar, setAvatar] = useState<string>('');
  const [gameName, setGameName] = useState<string>('');
  const [gameImage, setGameImage] = useState<string>('');
  const [progress, setProgress] = useState<string>('0 / 0');
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAchievementsAndUser = async () => {
      try {
        const achievementsResponse = await fetch(
          `http://localhost:8080/api/v1/achievements/user/${userId}/game/${gameId}`
        );

        if (!achievementsResponse.ok) {
          throw new Error('Failed to fetch achievements');
        }

        const achievementsData: Achievement[] = await achievementsResponse.json();
        setAchievements(achievementsData);

        const obtainedCount = achievementsData.filter((ach) => ach.obtained).length;
        setProgress(`${obtainedCount} / ${achievementsData.length}`);

        const userResponse = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
        if (!userResponse.ok) {
          throw new Error('Failed to fetch user details');
        }

        const userData = await userResponse.json();
        setNickname(userData.userNickname || userData.userName);
        setAvatar(userData.userAvatar);

        const gameResponse = await fetch(`http://localhost:8080/api/v1/games/${gameId}/details`);
        if (!gameResponse.ok) {
          throw new Error('Failed to fetch game details');
        }

        const mediaResponse: any = await fetch(`http://localhost:8080/api/v1/games/${gameId}/media`);
        if (!mediaResponse.ok) {
          throw new Error('Failed to fetch game details');
        }

        const gameData = await gameResponse.json();
        const mediaData = await mediaResponse.json();
        setGameName(gameData[0].name);
        setGameImage(mediaData.images[0].url);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchAchievementsAndUser();
  }, [userId, gameId]);

  if (loading) {
    return (
      <div className={styles.body}>
        <p className={styles.loading}>Carregando...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className={styles.body}>
        <p className={styles.error}>{error}</p>
      </div>
    )
  }

  if (achievements.length === 0) {
    return (
      <div className={styles.body}>
        <div className={styles.noAchievements}>
          <p>Esse jogo não possui conquistas associadas.</p>
          <button className={styles.returnButton} onClick={() => navigate(-1)}>
            Voltar
          </button>
        </div>
      </div>
      
    );
  }

  const obtainedAchievements = achievements.filter((ach) => ach.obtained);
  const notObtainedAchievements = achievements.filter((ach) => !ach.obtained);

  return (
    <div className={styles.body}>
      <div className={styles.header}>
        <img className={styles.avatar} src={avatar} alt="Avatar" onClick={() => navigate(`/user/${userId}`)} />
        <div className={styles.info}>
          <h1 className={styles.titleStats}>Conquistas em <b>{gameName}</b></h1>
          <p className={styles.username}>
            {nickname}
          </p>
        </div>
        <img className={styles.gameImage} src={gameImage} alt={gameName} onClick={() => navigate(`/game/${gameId}`)} />
      </div>
      <div className={styles.progressContainer}>
        <p>{progress} conquistas</p>
        <div className={styles.progressBar}>
          <div
            className={styles.progressFill}
            style={{
              width: `${(obtainedAchievements.length / achievements.length) * 100}%`,
            }}
          ></div>
        </div>
      </div>
      <div className={styles.achievementsContainer}>
        <div className={styles.section}>
          <h2 className={styles.sectionTitle}>Obtidas</h2>
          {obtainedAchievements.map((ach) => (
            <div key={ach.name} className={styles.achievementCard}>
              <img
                className={styles.achievementTrophy}
                src={Trophy}
                alt="Trophy"
              />
              <div className={styles.achievementInfo}>
                <p className={styles.achievementName}>{ach.name}</p>
                <p className={styles.achievementPercentage}>
                  Obtida por apenas {ach.obtainedPercentage}% do total de jogadores
                </p>
              </div>
            </div>
          ))}
        </div>
        <div className={styles.section}>
          <h2 className={styles.sectionTitle}>Não Obtidas</h2>
          {notObtainedAchievements.map((ach) => (
            <div key={ach.name} className={styles.achievementCard}>
              <img
                className={`${styles.achievementTrophy} ${styles.notObtained}`}
                src={Trophy}
                alt="Trophy"
              />
              <div className={styles.achievementInfo}>
                <p className={styles.achievementName}>{ach.name}</p>
                <p className={styles.achievementPercentage}>
                  Obtida por apenas {ach.obtainedPercentage}% do total de jogadores
                </p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default AchievementsPage;
