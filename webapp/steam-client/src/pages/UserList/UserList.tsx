import React from 'react';
import styles from './UserList.module.css';

import UserListItem from './components/UserListItem/UserListItem';

const UserList: React.FC = () => {
  return (
    <body className={styles.body}>
        <div className={styles.mainContainer}>
            <p className={styles.title}><b>Usuários</b></p>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
            <UserListItem/>
        </div>
    </body>
  );
};

export default UserList;