import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './Main.module.css';

interface Game {
  id: number;
  name: string;
  headerImageUrl: string;
}

const Main: React.FC = () => {
  const [games, setGames] = useState<Game[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchGames = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/v1/games');
        if (!response.ok) {
          throw new Error('Failed to fetch games');
        }
        const data = await response.json();
        setGames(data.games);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchGames();
  }, []);

  if (loading) {
    return <p className={styles.loading}>Carregando...</p>;
  }

  if (error) {
    return <p className={styles.error}>{error}</p>;
  }

  const handleGameClick = (id: number) => {
    navigate(`/game/${id}`);
  };

  return (
    <div className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}><b>Em destaque</b></p>
        <div className={styles.imageGrid}>
          {games.map((game) => (
            <div
              key={game.id}
              className={styles.imageContainer}
              onClick={() => handleGameClick(game.id)}
            >
              <img src={game.headerImageUrl} alt={game.name} />
              <p className={styles.gameTitle}>{game.name}</p>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Main;
