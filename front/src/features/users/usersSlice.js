import { createAsyncThunk, createSlice } from '@reduxjs/toolkit'
import { client } from '../../client/api';
import { pageSelected } from '../application/applicationSlice';


export const fetchUsers = createAsyncThunk('users/fetchUsers', async () => {
  const response = await client.get('/users');
  return response.data;

});

export const addFriend = createAsyncThunk('users/addFriend', async (userId, thunkAPI) => {
  const response = await client.post('users/addFriend/' + userId);
  return { ...response.data, userId: userId };
});


export const removeFriend = createAsyncThunk('users/removeFriend', async (userId, thunkAPI) => {
  const response = await client.delete('/users/friends/remove/' + userId);
  return { ...response.data, userId: userId };
});


export const usersSlice = createSlice({
  name: 'users',
  initialState: {
    users: [],
    status: 'idle',
    addFriendData: {},
    error: null
  },
  reducers: {},
  extraReducers: {
    [pageSelected]: (state, action) => {
      state.status = 'idle';
    },
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
      const userId = action.meta.arg;

      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendStatus: 'loading'
        }
      };
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

      // обновляем статус
      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendStatus: 'succeeded',
          addFriendError: null
        }
      }
    },
    [addFriend.rejected]: (state, action) => {
      const userId = action.meta.arg;
      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendError: action.error.message,
          addFriendStatus: 'failed'
        }
      };
    },
    [removeFriend.pending]: (state, action) => {
      const userId = action.meta.arg;

      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendStatus: 'loading'
        }
      };
    },
    [removeFriend.fulfilled]: (state, action) => {
      const userId = action.payload.userId;

      // помечаем как друга
      state.users = state.users.map(user => {
        if (user.userId === userId) {
          return {
            ...user,
            myFriend: false
          }
        }
        return user
      });

      // обновляем статус
      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendStatus: 'succeeded-removed',
          addFriendError: null
        }
      }
    },
    [removeFriend.rejected]: (state, action) => {
      const userId = action.meta.arg;
      state.addFriendData = {
        ...state.addFriendData,
        [userId]: {
          addFriendError: action.error.message,
          addFriendStatus: 'failed'
        }
      };
    }
  }
});

export const selectAllUsers = state => state.users.users;
export const selectUserById = userId => state => state.users.users.find(user => user.userId === userId);
export const selectAddFriendData = userId => state => state.users.addFriendData[userId];

export default usersSlice.reducer;