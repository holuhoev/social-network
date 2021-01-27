import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { extractToken, post } from '../../client/api';

export const login = createAsyncThunk('auth/login', async ({ username, password }) => {
  const response = await post('/login', {
    username: username,
    password: password
  }, true);

  if (!response.success) {
    return Promise.reject('');
  }

  localStorage.setItem('accessToken', response.data.accessToken);

  return response.data.accessToken;
});

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    status: 'idle',
    error: null,
    isLoggedIn: !!extractToken()
  },
  reducers: {},
  extraReducers: {
    [login.pending]: (state) => {
      state.status = 'loading';
    },
    [login.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.isLoggedIn = true;
    },
    [login.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
      state.isLoggedIn = false;
    }
  }
});

export const selectIsLoggedIn = state => state.auth.isLoggedIn;

export default authSlice.reducer;