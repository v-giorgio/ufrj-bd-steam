import React from 'react';
import { useParams } from 'react-router-dom';
import styles from './UserGamesPage.module.css';
import UserGameCard from '../UserPage/components/UserGameCard';

interface Game {
  id: number;
  gameName: string;
  headerImage: string;
  playedTime: number;
  totalAchievementsNumber: number;
  obtainedAchievementsNumber: number;
}

const UserGamesPage: React.FC = () => {
  const { userId } = useParams<{ userId: string }>();
  const [username, setUsername] = React.useState<string | null>(null);
  const [games, setGames] = React.useState<Game[]>([]);
  const [loading, setLoading] = React.useState(true);
  const [error, setError] = React.useState<string | null>(null);

  React.useEffect(() => {
    const fetchGames = async () => {
      try {
        const response = await fetch(`http://localhost:8080/api/v1/users/${userId}/games`);
        if (!response.ok) throw new Error('Failed to fetch games');
        const data = await response.json();
        setGames(data.games);
        
        const userResponse = await fetch(`http://localhost:8080/api/v1/users/${userId}`);
        if (!userResponse.ok) {
          throw new Error('Failed to fetch user details');
        }

        const userData = await userResponse.json();
        setUsername(userData.userNickname || userData.userName);

      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchGames();
  }, [userId]);

  if (loading) return <p className={styles.loading}>Carregando...</p>;
  if (error) return <p className={styles.error}>{error}</p>;

  return (
    <div className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}>Jogos de {username}</p>
        <div className={styles.gamesGrid}>
          {games.map((game) => (
            <UserGameCard
              key={game.id}
              userId={String(userId)}
              gameId={game.id}
              gameName={game.gameName}
              gameImage={game.headerImage}
              hoursPlayed={(game.playedTime / 1000).toLocaleString('pt-BR', { minimumFractionDigits: 3, maximumFractionDigits: 3 })}
              totalAchievements={game.totalAchievementsNumber}
              obtainedAchievements={game.obtainedAchievementsNumber}
            />
          ))}
        </div>
      </div>
    </div>
  );
};

export default UserGamesPage;
