import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { extractToken, get, post } from '../../client/api';

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

export const fetchClientInfo = createAsyncThunk('auth/clientInfo', async () => {
  const response = await get('/users/currentUser');

  return response.data;
});

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    clientInfo: null,
    clientInfoStatus: 'idle',
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
    },
    [fetchClientInfo.pending]: (state) => {
      state.clientInfoStatus = 'loading';
    },
    [fetchClientInfo.fulfilled]: (state, action) => {
      state.clientInfoStatus = 'succeeded';
      state.clientInfo = action.payload;
    },
    [fetchClientInfo.rejected]: (state, action) => {
      state.clientInfoStatus = 'failed';
      state.error = action.error.message;
    }
  }
});

export const selectIsLoggedIn = state => state.auth.isLoggedIn;
export const selectClientInfoStatus = state => state.auth.clientInfoStatus;

export default authSlice.reducer;