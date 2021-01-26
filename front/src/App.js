import React from 'react';
import Main from './features/main/Main';
import { useSelector } from 'react-redux';

import { LoginForm } from './features/auth/LoginForm';
import { selectToken } from './features/auth/authSlice';

function App() {

  const token = useSelector(selectToken)

  return (
    !token ? <LoginForm/> : <Main/>
  );
}

export default App;
