import React from 'react';
import { useNavigate } from 'react-router-dom';
import styles from './UserListItem.module.css';

import brazilFlag from '../../../../assets/brazil.png';

interface UserListItemProps {
  id: number;
  nickname: string;
  originCountry: string;
  avatar: string;
}

const UserListItem: React.FC<UserListItemProps> = ({ id, nickname, originCountry, avatar }) => {
  const navigate = useNavigate();

  const handleUserClick = () => {
    navigate(`/user/${id}`);
  };

  return (
    <div className={styles.mainContainer} onClick={handleUserClick}>
      <img className={styles.avatar} src={avatar} alt={`${nickname}'s Avatar`} />
      <div className={styles.infoContainer}>
        <div className={styles.userName}>{nickname}</div>
        <div className={styles.userCountry}>
          <img src={brazilFlag} alt="Country Flag" />
          {originCountry}
        </div>
      </div>
    </div>
  );
};

export default UserListItem;
