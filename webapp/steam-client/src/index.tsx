import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';



import Layout from './layouts/Layout'; // Import the Layout component
import Main from './pages/Main/Main';
import UserList from './pages/UserList/UserList';
import Game from './pages/Game/Game';
import UserPage from './pages/UserPage/UserPage';
import UserGames from './pages/UserGames/UserGames';




const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <Router>
      <Routes>
        {/* O componente de Layout encapsula todas as rotas */}
        <Route path="/" element={<Layout />}>
          <Route index element={<Main />} />
          <Route path="/userList" element={<UserList />} /> 
          <Route path="/games" element={<Game />} /> 
          <Route path="/userPage" element={<UserPage />} /> 
          <Route path="/userGames" element={<UserGames />} />
          {/*<Route path="/userPage" element={<UserAchievements />} />*/}
          {/* ADICIONAR ROTAS DAS OUTRAS PÁGINAS AQUI */}
        </Route>
      </Routes>
    </Router>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
