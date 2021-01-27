import { createAsyncThunk, createSlice } from '@reduxjs/toolkit';
import { get } from '../../client/api';

export const fetchProfile = createAsyncThunk('profile/fetchProfile', async () => {
  const response = await get('/users/currentUser');

  return response.data;
});

export const profileSlice = createSlice({
  name: 'profile',
  initialState: {
    profile: null,
    status: 'idle',
    error: null
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
    }
  }
});

export const selectProfile = state => state.profile.profile;
export const selectProfileStatus = state => state.profile.status;

export default profileSlice.reducer;