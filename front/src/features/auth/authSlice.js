import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { extractToken, client } from '../../client/api';
import { fetchUsers } from '../users/usersSlice';


export async function postLogin(username, password) {
  const response = await client.post('/login', {
    username: username,
    password: password
  }, true);


  if (!response.success) {
    return Promise.reject('');
  }

  localStorage.setItem('accessToken', response.data.accessToken);

  return response.data.accessToken;
}

export const login = createAsyncThunk('auth/login', async ({ username, password }) => {
  return await postLogin(username, password);
});


export const register = createAsyncThunk("/profile/register", async (user) => {
  const response = await client.post("/users/register", user, true);
  const {username, password} = user;

  const loginResult = await postLogin(username, password);
  return response.data;
});


export const authSlice = createSlice({
  name: 'auth',
  initialState: {
    status: 'idle',
    error: null,
    isLoggedIn: !!extractToken()
  },
  reducers: {
    logout: (state, action) => {
      state.isLoggedIn = false;
    }
  },
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
    [register.pending]: (state) => {
      state.status = 'loading';
    },
    [register.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.isLoggedIn = true;
    },
    [register.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
      state.isLoggedIn = false;
    },
    [fetchUsers.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
      state.isLoggedIn = false;
    },
  }
});

export const selectIsLoggedIn = state => state.auth.isLoggedIn;
export const selectLoginStatus = state => state.auth.status;
export const logout = authSlice.actions.logout;

export default authSlice.reducer;