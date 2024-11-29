import React from 'react';
import styles from './Footer.module.css';

import steamLogo from '../../assets/steam-footer-logo.png';
import valveLogo from '../../assets/valve.png';

const Footer: React.FC = () => {
  return (
    <footer className={styles.footer}>
        <div className={styles.container}>
        <a href="/">
            <img className={styles.logo} src={valveLogo} />
        </a>
        <a href="https://www.valvesoftware.com/en/">
            <img className={styles.logo} src={steamLogo} />
        </a>
        <div className={styles.copyrightsText}>
        © 2024.2 Bacharelado em Ciência da Computação UFRJ - Grupo 13 de Banco de dados. Todos os direitos reservados. Todas as marcas comerciais são propriedade dos respetivos proprietários nos E.U.A. e outros países. IVA incluído em todos os preços onde aplicável.
        <br/><br/>
        Política de privacidade     &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
        Termos legais               &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
        Acordo de Subscrição Steam  &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
        Reembolsos                  &nbsp;&nbsp;&nbsp; | &nbsp;&nbsp;&nbsp;
        Cookies  
        </div>
      </div>
    </footer>
  );
};

export default Footer;