import React from 'react';
import styles from './UserListItem.module.css';

import brazilFlag from '../../../../assets/brazil.png'

const UserListItem: React.FC = () => {
  return (
    <a href={'/userPage'} >
      <div className={styles.mainContainer}>
        <img className={styles.avatar} src="https://avatars.steamstatic.com/9308db1f667688237ceabacc69cde296c28a6533.jpg" />
        <div className={styles.infoContainer}>
          <div className={styles.userName}>Juninho666</div>
          <div className={styles.userCountry}>
            <img src={brazilFlag} alt="" />
              BR
          </div>
        </div>
      </div>
    </a>
  );
};

export default UserListItem;