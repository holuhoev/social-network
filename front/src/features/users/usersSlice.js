import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import { get } from '../../client/api';


export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
  const response = await get('/users');
  return response.data;

});

export const usersSlice = createSlice({
  name: 'users',
  initialState: {
    users: [],
    status: 'idle',
    error: null
  },
  reducers: {},
  extraReducers: {
    [fetchUsers.pending]: (state, action) => {
      state.status = 'loading';
    },
    [fetchUsers.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.users = action.payload;
    },
    [fetchUsers.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
    }
  }
});

export const selectAllUsers = state => state.users.users;
export const selectUserById = userId => state => state.users.users.find(user => user.userId === userId);

export default usersSlice.reducer;