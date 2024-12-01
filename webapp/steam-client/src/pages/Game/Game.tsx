import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import styles from './Game.module.css';

interface MediaItem {
  url: string;
  type: 'video' | 'image';
}

interface Media {
  videos: { name: string; url: string }[];
  images: { url: string; isHeader: boolean }[];
}

interface Details {
  id: number;
  name: string;
  genre: string;
  developerCompany: string;
  editorCompany: string;
  description: string;
  minimumSpec: string;
  recommendedSpec: string;
  categories: string[];
}

const Game: React.FC = () => {
  const { gameId } = useParams<{ gameId: string }>();
  const [mediaItems, setMediaItems] = useState<MediaItem[]>([]);
  const [details, setDetails] = useState<Details | null>(null);
  const [currentMediaIndex, setCurrentMediaIndex] = useState(0);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchGameData = async () => {
      try {
        const mediaResponse = await fetch(`http://localhost:8080/api/v1/games/${gameId}/media`);
        const detailsResponse = await fetch(`http://localhost:8080/api/v1/games/${gameId}/details`);
  
        if (!mediaResponse.ok || !detailsResponse.ok) {
          throw new Error('Failed to fetch game data');
        }
  
        const mediaData: Media = await mediaResponse.json();
        const detailsData: Details[] = await detailsResponse.json();
  
        const combinedMedia: MediaItem[] = [
          ...mediaData.videos.map((video) => ({ url: video.url, type: 'video' as const })),
          ...mediaData.images.map((image) => ({ url: image.url, type: 'image' as const })),
        ];
        setMediaItems(combinedMedia);
        setDetails(detailsData[0]);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };
  
    fetchGameData();
  }, [gameId]);

  if (loading) {
    return <p className={styles.loading}>Carregando...</p>;
  }

  if (error) {
    return <p className={styles.error}>{error}</p>;
  }

  if (!details || mediaItems.length === 0) {
    return <p className={styles.error}>Dados do jogo não encontrados.</p>;
  }

  const handleMediaClick = (index: number) => {
    setCurrentMediaIndex(index);
  };

  return (
    <div className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}>{details.name}</p>
        <div className={styles.firstRow}>
          <div className={styles.previewContainer}>
            <div className={styles.currentMedia}>
              {mediaItems[currentMediaIndex].type === 'video' ? (
                <video
                  className={styles.mediaLarge}
                  src={mediaItems[currentMediaIndex].url}
                  controls
                  autoPlay
                />
              ) : (
                <img
                  className={styles.mediaLarge}
                  src={mediaItems[currentMediaIndex].url}
                  alt={`Media ${currentMediaIndex + 1}`}
                />
              )}
            </div>
            <div className={styles.carousel}>
              {mediaItems.map((item, index) => (
                <div
                  key={index}
                  className={styles.carouselItem}
                  onClick={() => handleMediaClick(index)}
                >
                  {item.type === 'video' ? (
                    <video
                      className={styles.media}
                      src={item.url}
                      muted
                    />
                  ) : (
                    <img
                      className={styles.media}
                      src={item.url}
                      alt={`Media ${index + 1}`}
                    />
                  )}
                </div>
              ))}
            </div>
          </div>
          <div className={styles.infoContainer}>
            <p><b>Gênero:</b> {details.genre}</p>
            <p><b>Desenvolvedor:</b> {details.developerCompany}</p>
            <p><b>Distribuidor:</b> {details.editorCompany}</p>
            <p><b>Categorias:</b> {details.categories.join(', ')}</p>
            <p><b>Descrição:</b> {details.description}</p>
          </div>
        </div>

        <div className={styles.requirementsContainer}>
          <p className={styles.title}>Requisitos do Sistema</p>
          <div className={styles.requirementsText}>
            <p><b>Mínimo:</b> {details.minimumSpec}</p>
            <p><b>Recomendado:</b> {details.recommendedSpec}</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Game;
