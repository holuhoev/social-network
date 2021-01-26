import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { post } from '../../client/api';

export const login = createAsyncThunk('auth/login', async ({ username, password }) => {
  const response = await post('/login', {
    username: username,
    password: password
  });

  if (!response.success) {
    return Promise.reject('');
  }

  return response.data.accessToken;
});

export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    clientInfo: null,
    status: 'idle',
    token: null,
    error: null
  },
  reducers: {},
  extraReducers: {
    [login.pending]: (state) => {
      state.status = 'loading'
    },
    [login.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.token = action.payload;
    },
    [login.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
    }
  }
});

export const selectToken = state => state.auth.token;
export default authSlice.reducer;