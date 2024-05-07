import React from 'react'
import style from './LandingPage.module.scss'
import Logo from '../../assets/placeholder.png'

const LandingPage = () => {
  return (
      <div className={style.container}>
          <h1 className={style.heading}>Welcome to Antisocial Media!</h1>
          <img className={style.img} src={Logo} alt="logo" />
      </div>
  );
};

export default LandingPage;