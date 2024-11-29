import React from 'react';
import styles from './Game.module.css';

const Game: React.FC = () => {
  return (
    <body className={styles.body}>
        <div className={styles.mainContainer}>
            <p className={styles.title}>Counter Strike</p>
            <div className={styles.firstRow}>
                <div className={styles.previewContainer}>
                    <div className={styles.currentMedia}>

                    </div>
                    <div className={styles.carousel}>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002543.1920x1080.jpg?t=1729702322"/>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002542.1920x1080.jpg?t=1729702322"/>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000000133.1920x1080.jpg?t=1729702322"/>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002540.1920x1080.jpg?t=1729702322"/>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002543.1920x1080.jpg?t=1729702322"/>
                        <img className={styles.media} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/0000002542.1920x1080.jpg?t=1729702322"/>
                    </div>
                </div>
                <div className={styles.infoContainer}>
                    <img className={styles.cover} src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/header.jpg?t=1729702322" />
                    <div className={styles.infoText}>
                        <p><b>Gênero:</b> Ação</p>
                        <p><b>Desenvolvedor:</b> Valve</p>
                        <p><b>Distribuidor:</b> Valve</p>
                        <p><b>Categorias:</b> Multiplayer, FPS, Competitivo</p>
                    </div>
                </div>
            </div>

            <div className={styles.requirementsContainer}>

            </div>
        </div>

        <div className={styles.buyContainer}>
            <div className={styles.buyText}><p  className={styles.test}>Compre counter strike</p></div>
            <div className={styles.price}><p  className={styles.test}><b>R$ 99,90</b></p></div>
            <div className={styles.addToCart}><p  className={styles.test}><b>+ Carrinho </b></p></div>
        </div>
    </body>
  );
};

export default Game;