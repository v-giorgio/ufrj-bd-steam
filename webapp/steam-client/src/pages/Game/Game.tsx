import React, { useState, useEffect } from 'react';
import styles from './Game.module.css';

const Game: React.FC = () => {
  const [currentImageIndex, setCurrentImageIndex] = useState(0);
  const images = [
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002543.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002542.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000000133.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002540.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002543.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002542.1920x1080.jpg?t=1729702322",
    "https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002542.1920x1080.jpg?t=1729702322",
  ];

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImageIndex((prevIndex) => (prevIndex + 1) % images.length);
    }, 5000);
    return () => clearInterval(interval);
  }, [images.length]);

  const handleImageClick = (index: number) => {
    setCurrentImageIndex(index);
  };

  return (
    <div className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}>Counter Strike</p>
        <div className={styles.firstRow}>
          <div className={styles.previewContainer}>
          <div className={styles.currentMedia}>
            <img
                className={styles.mediaLarge}
                src={images[currentImageIndex]}
                alt="Current Media"
            />
            </div>
            <div className={styles.carousel}>
                {images.map((image, index) => (
                    <img
                    key={index}
                    className={styles.media}
                    src={image}
                    alt={`Media ${index + 1}`}
                    onClick={() => handleImageClick(index)}
                    />
                ))}
            </div>
          </div>
          <div className={styles.infoContainer}>
            <img
              className={styles.cover}
              src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/header.jpg?t=1729702322"
              alt="Game Cover"
            />
            <div className={styles.infoText}>
              <p><b>Gênero:</b> Ação</p>
              <p><b>Desenvolvedor:</b> Valve</p>
              <p><b>Distribuidor:</b> Valve</p>
              <p><b>Categorias:</b> Multiplayer, FPS, Competitivo</p>
            </div>
          </div>
        </div>

        <div className={styles.requirementsContainer}>
            <p className={styles.title}>Requisitos do Sistema</p>
            <div className={styles.requirementsText}>
              <p><b>Mínimo::</b> 500 mhz processor, 96mb ram, 16mb video card, Windows XP, Mouse, Keyboard, Internet Connection</p>
              <p><b>Recomendado:</b> 800 mhz processor, 128mb ram, 32mb+ video card, Windows XP, Mouse, Keyboard, Internet Connection</p>
            </div>
        </div>
      </div>

      <div className={styles.buyContainer}>
        <div className={styles.buyText}>
          <p className={styles.test}>Compre Counter Strike</p>
        </div>
        <div className={styles.price}>
          <p className={styles.test}><b>R$ 99,90</b></p>
        </div>
        <div className={styles.addToCart}>
          <p className={styles.test}><b>+ Carrinho </b></p>
        </div>
      </div>
    </div>
  );
};

export default Game;
