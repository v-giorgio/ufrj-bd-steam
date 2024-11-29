import React from 'react';
import styles from './Main.module.css';

const Main: React.FC = () => {
  return (
    <body className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}><b>Em destaque</b></p>
        <div className={styles.imageContainer}>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/230410/header.jpg?t=1729784957"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1250/header.jpg?t=1726777771"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/295550/header.jpg?t=1722363744"/>
        </div>
        <div className={styles.imageContainer}>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/20920/header.jpg?t=1729586898"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/225260/header.jpg?t=1727388481"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/444090/header.jpg?t=1713560419"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/39150/header.jpg?t=1699010413"/>
        </div>
        <div className={styles.imageContainer}>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/230410/header.jpg?t=1729784957"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/1250/header.jpg?t=1726777771"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/295550/header.jpg?t=1722363744"/>
        </div>
        <div className={styles.imageContainer}>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/20920/header.jpg?t=1729586898"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/225260/header.jpg?t=1727388481"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/444090/header.jpg?t=1713560419"/>
          <img src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/39150/header.jpg?t=1699010413"/>
        </div>
      </div>
    </body>
  );
};

export default Main;