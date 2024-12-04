import React, { useState, useEffect } from 'react';
import styles from './UserList.module.css';
import UserListItem from './components/UserListItem/UserListItem';

interface User {
  id: number;
  nickname: string;
  name: string;
  profileUrl: string;
  originCountry: string;
  avatar: string;
}

const UserList: React.FC = () => {
  const [users, setUsers] = useState<User[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/v1/users');
        if (!response.ok) {
          throw new Error('Failed to fetch users');
        }
        const data = await response.json();
        setUsers(data.users);
      } catch (err) {
        setError(err instanceof Error ? err.message : 'An unexpected error occurred');
      } finally {
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  if (loading) {
    return (
      <div className={styles.body}>
        <p className={styles.loading}>Carregando...</p>
      </div>
    );
  }

  if (error) {
    return (
      <div className={styles.body}>
        <p className={styles.error}>{error}</p> 
      </div>
    )
  }

  return (
    <div className={styles.body}>
      <div className={styles.mainContainer}>
        <p className={styles.title}><b>Usuários</b></p>
        {users.map((user) => (
          <UserListItem
            key={user.id}
            id={user.id}
            nickname={user.nickname || user.name}
            originCountry={user.originCountry}
            avatar={user.avatar}
          />
        ))}
      </div>
    </div>
  );
};

export default UserList;
