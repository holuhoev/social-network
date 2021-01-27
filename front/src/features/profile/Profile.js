import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  fetchProfile,
  selectProfile,
  selectProfileStatus
} from './profileSlice';


/**
 * @return {null}
 */
export function Profile() {

  const profileStatus = useSelector(selectProfileStatus);

  const dispatch = useDispatch();
  useEffect(() => {
    if (profileStatus === 'idle') {
      dispatch(fetchProfile())
    }
  }, [profileStatus]);

  const profile = useSelector(selectProfile);

  if (!profile) {
    return null;
  }

  return (
    <div>
      First name: {profile.firstName}
      <br/>
      Last name: {profile.firstName}
      <br/>
      Age: {profile.age}
      <br/>
      Interests: {profile.interests}
      <br/>
      City: {profile.city}
    </div>
  );
};
