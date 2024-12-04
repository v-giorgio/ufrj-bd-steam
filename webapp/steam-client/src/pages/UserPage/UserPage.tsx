import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import styles from './UserPage.module.css';

import UserGameCard from './components/UserGameCard';

interface User {
  userName: string;
  userNickname: string;
  userProfileUrl: string;
  userOriginCountry: string;
  userAvatar: string;
  userGamesCount: number;
  userAchievementsCount: number;
  topCategories: { categoryName: string; timesPlayed: number }[];
}

interface Game {
  id: number;
  gameName: string;
  playedTime: number;
  totalAchievementsNumber: number;
  obtainedAchievementsNumber: number;
  headerImage: string;
}

const UserPage: React.FC = () => {
  const { userId } = useParams<{ userId: string }>();
  const [user, setUser] = useState<User | null>(null);
  const [games, setGames] = useState<Game[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchUserData = async () => {
      try {
        const userResponse = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
        const gamesResponse = await fetch(`http://localhost:8080/api/v1/users/${userId}/games`);

        if (!userResponse.ok || !gamesResponse.ok) {
          throw new Error('Failed to fetch user data');
        }

        const userData = await userResponse.json();
        const gamesData = await gamesResponse.json();

        setUser(userData);
        setGames(gamesData.games);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchUserData();
  }, [userId]);

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

  if (!user) {
    return (
      <div className={styles.body}>
        <p className={styles.error}>Dados do usuário não encontrados.</p>
      </div>
    );
  }

  return (
    <div className={styles.body}>
      <div className={styles.profileContainer}>
        <div className={styles.header}>
          <img className={styles.avatar} src={user.userAvatar} alt={`${user.userNickname}'s Avatar`} />
          <div className={styles.userInfo}>
            <p className={styles.username}>{user.userNickname}</p>
            <p className={styles.fullName}>{user.userName}</p>
            <p className={styles.location}>{user.userOriginCountry}</p>
          </div>
          <div className={styles.badgesSection}>
            <p className={styles.badgesTitle}>
              <a href={`/user/${userId}/games`} className={styles.link}>
                Jogos <b>{user.userGamesCount}</b>
              </a>{' '}
              | Conquistas <b>{user.userAchievementsCount}</b>
            </p>
            <div className={styles.badgesContainer}>
              {user.topCategories.map((category, index) => (
                <div key={index} className={styles.badge}>
                  {category.categoryName} ({category.timesPlayed}x)
                </div>
              ))}
            </div>
          </div>
        </div>

        <div className={styles.content}>
        <div className={styles.recentActivity}>
          <p className={styles.sectionTitle}>Mais Jogados</p>
          {games
            .sort((a, b) => b.playedTime - a.playedTime) 
            .slice(0, 3) 
            .map((game) => (
              <UserGameCard
                key={game.id}
                userId={Number(userId)}
                gameId={game.id}
                gameName={game.gameName}
                gameImage={game.headerImage}
                hoursPlayed={((game.playedTime / 1000).toLocaleString('pt-BR', { minimumFractionDigits: 3, maximumFractionDigits: 3 }))}
                totalAchievements={game.totalAchievementsNumber}
                obtainedAchievements={game.obtainedAchievementsNumber}
              />
            ))}
        </div>


          <div className={styles.onlineStatus}>
            <p className={styles.sectionTitle}>Sobre</p>
            <p>País de origem: {user.userOriginCountry}</p>
            <p>Jogos: <b>{user.userGamesCount}</b></p>
            <p>Conquistas: <b>{user.userAchievementsCount}</b></p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserPage;
