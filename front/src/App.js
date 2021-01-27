import React from 'react';
import Main from './features/main/Main';
import { useSelector } from 'react-redux';

import { LoginForm } from './features/auth/LoginForm';
import { selectIsLoggedIn } from './features/auth/authSlice';

function App() {

  const isLoggedIn = useSelector(selectIsLoggedIn);

  return (
    isLoggedIn ? <Main/> : <LoginForm/>
  );
}

export default App;
