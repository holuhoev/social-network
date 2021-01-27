import { createSlice, createAsyncThunk } from '@reduxjs/toolkit'
import { get, post } from '../../client/api';


export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
  const response = await get('/users');
  return response.data;

});

export const addFriend = createAsyncThunk("users/addFriend", async (userId, thunkAPI) => {
  const response = await post('users/addFriend/' + userId);
  return response.data;
});

export const usersSlice = createSlice({
  name: 'users',
  initialState: {
    users: [],
    status: 'idle',
    addFriendStatus: 'idle',
    addFriendResult: null,
    addFriendError: null,
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
    },
    [addFriend.pending]: (state, action) => {
      state.addFriendStatus = 'loading';
    },
    [addFriend.fulfilled]: (state, action) => {
      state.addFriendStatus = 'succeeded';
      state.addFriendResult = action.payload;
    },
    [addFriend.rejected]: (state, action) => {
      state.addFriendStatus = 'failed';
      state.addFriendError = action.error.message;
    }
  }
});

export const selectAllUsers = state => state.users.users;
export const selectUserById = userId => state => state.users.users.find(user => user.userId === userId);
export const selectAddFriendStatus = state => state.users.addFriendStatus;

export default usersSlice.reducer;