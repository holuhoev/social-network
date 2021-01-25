import { createSlice } from '@reduxjs/toolkit';


export const applicationSlice = createSlice({
  name: 'application',
  initialState: {
    currentPage: 'Users'
  },
  reducers: {
    pageSelected: (state, action) => {
      state.currentPage = action.payload
    }
  }
});

export const selectCurrentPage = state => state.application.currentPage;
export const pageSelected = applicationSlice.actions.pageSelected;
export default applicationSlice.reducer;