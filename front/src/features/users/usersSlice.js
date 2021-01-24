import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import { get } from '../../client/api';


export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
  const response = await get('/users');
  debugger;
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
      state.users = state.users.concat(action.payload);
    },
    [fetchUsers.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
    }
  }
});

export const selectAllUsers = state => state.users.users;
export const selectUserById = (state, userId) => state.users.find(user => user.id === userId);

export default usersSlice.reducer;