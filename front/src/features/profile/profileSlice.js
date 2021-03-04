import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { client } from '../../client/api';

export const fetchProfile = createAsyncThunk('profile/fetchProfile', async () => {
  const response = await client.get('/users/currentUser');

  return response.data;
});

export const updateProfile = createAsyncThunk("/profile/updateProfile", async (user) => {
  const response = await client.put("/users/updateProfile", user);

  return response.data;
});

export const profileSlice = createSlice({
  name: 'profile',
  initialState: {
    profile: null,
    status: 'idle',
    error: null,
    updateStatus: 'idle'
  },
  reducers: {},
  extraReducers: {
    [fetchProfile.pending]: (state) => {
      state.status = 'loading';
    },
    [fetchProfile.fulfilled]: (state, action) => {
      state.status = 'succeeded';
      state.profile = action.payload;
    },
    [fetchProfile.rejected]: (state, action) => {
      state.status = 'failed';
      state.error = action.error.message;
    },
    [updateProfile.pending]: (state) => {
      state.updateStatus = 'loading';
    },
    [updateProfile.fulfilled]: (state, action) => {
      state.updateStatus = 'succeeded';
      state.profile = action.payload;
    },
    [updateProfile.rejected]: (state, action) => {
      state.updateStatus = 'failed';
      state.error = action.error.message;
    }
  }
});

export const selectProfile = state => state.profile.profile;
export const selectProfileStatus = state => state.profile.status;
export const selectProfileUpdateStatus = state => state.profile.updateStatus;

export default profileSlice.reducer;