import React from 'react';
import styles from './UserPage.module.css';


import UserGameCard from './components/UserGameCard';


const UserPage: React.FC = () => {
  return (
    <div className={styles.body}>
      <div className={styles.profileContainer}>
      <div className={styles.header}>
        <img
          className={styles.avatar}
          src="https://avatars.cloudflare.steamstatic.com/38051e069984c8a56be46d46e5c7808b2376857f_full.jpg"
          alt="Avatar"
        />
        <div className={styles.userInfo}>
          <p className={styles.username}>john-miller</p>
          <p className={styles.fullName}>João Vitor</p>
          <p className={styles.location}>Brasil</p>
        </div>
        <div className={styles.badgesSection}>
          <p className={styles.badgesTitle}>Badges <b>14</b></p>
          <div className={styles.badgesContainer}>
            <img
              className={styles.badgeIcon}
              src="https://placehold.co/50x50"
              alt="Badge 1"
            />
            <img
              className={styles.badgeIcon}
              src="https://placehold.co/50x50"
              alt="Badge 2"
            />
            <img
              className={styles.badgeIcon}
              src="https://placehold.co/50x50"
              alt="Badge 3"
            />
            <img
              className={styles.badgeIcon}
              src="https://placehold.co/50x50"
              alt="Badge 4"
            />
          </div>
        </div>
      </div>

        <div className={styles.content}>
          <div className={styles.recentActivity}>
            <p className={styles.sectionTitle}>Atividade recente</p>
            {/*<div className={styles.activityCard}>
              <img
                className={styles.gameImage}
                src="https://shared.akamai.steamstatic.com/store_item_assets/steam/apps/10/header.jpg?t=1729702322"
                alt="Counter-Strike"
              />
              <div className={styles.gameInfo}>
                <p className={styles.gameTitle}>Counter-Strike</p>
                <p className={styles.hoursPlayed}>23.1 horas registradas</p>
                <div className={styles.achievement}>
                  <p>Progresso em Conquistas: 1 de 15</p>
                  <img
                    className={styles.trophyIcon}
                    src=  {trophy}
                    alt="Trophy"
                  />
                </div>
              </div>
            </div>*/}
            <UserGameCard/>
            <UserGameCard/>
            <UserGameCard/>
            {/* Adicione mais cards se necessário */}
          </div>

          <div className={styles.onlineStatus}>
            <p className={styles.sectionTitle}>On-line</p>
            <p>Jogos <b>37</b></p>
            <p>Conquistas <b>37</b></p>
            <p>Amigos <b>37</b></p>

            <div className={styles.friendList}>
              <div className={styles.friendCard}>
                <img
                  className={styles.friendAvatar}
                  src="https://placehold.co/50x50"
                  alt="Friend"
                />
                <p>Juninho666</p>
                <p>Brazil</p>
              </div>
              {/* Adicione mais amigos conforme necessário */}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserPage;