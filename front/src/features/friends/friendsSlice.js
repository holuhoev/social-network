import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { addFriend, removeFriend } from '../users/usersSlice';
import { pageSelected } from '../application/applicationSlice';
import {client} from '../../client/api';


export const fetchFriends = createAsyncThunk('friends/myFriends', async () => {
  const response = await client.get('/users/myFriends');
  return response.data;
});

export const friendsSlice = createSlice({
  name: 'friends',
  initialState: {
    users: [],
    status: 'idle',
    error: null
  },
  reducers: {},
  extraReducers: {
    [pageSelected]: (state, action) => {
      state.status = 'idle';
    },
    [fetchFriends.pending]: (state, action) => {
      state.status = 'loading';
    },
    [fetchFriends.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.users = action.payload;
    },
    [fetchFriends.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
    },
    [removeFriend.fulfilled]: (state, action) => {
      const userId = action.payload.userId;

      // помечаем как не друга
      state.users = state.users.map(user => {
        if (user.userId === userId) {
          return {
            ...user,
            myFriend: false
          }
        }
        return user
      });
    },
    [addFriend.fulfilled]: (state, action) => {
      const userId = action.payload.userId;

      // помечаем как друга
      state.users = state.users.map(user => {
        if (user.userId === userId) {
          return {
            ...user,
            myFriend: true
          }
        }
        return user
      });
    }
  }
});

export const selectAllFriends = state => state.friends.users;
export const selectFriendById = userId => state => state.friends.users.find(user => user.userId === userId);
