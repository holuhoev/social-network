import React, { Fragment, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  fetchFriends,
  selectAllFriends,
  selectFriendById
} from './friendsSlice';
import { renderUserList } from '../users/Users';
import { UserProfile } from '../users/UserProfile';

export function Friends() {
  const friends = useSelector(selectAllFriends);
  const usersStatus = useSelector(state => state.friends.status);

  const dispatch = useDispatch();

  useEffect(() => {
    if (usersStatus === 'idle') {
      dispatch(fetchFriends())
    }
  }, [usersStatus]);

  const [selectedUserId, setSelectedUserId] = useState('');
  const [visible, setVisible] = useState(false);

  const onSelect = userId => {
    setSelectedUserId(userId);
    setVisible(true);
  };

  const onClose = () => {
    setVisible(false);
    dispatch(fetchFriends());
  };

  return (
    <Fragment>
      {renderUserList(usersStatus === 'loading', friends, onSelect)}
      <UserProfile
        userId={selectedUserId}
        visible={visible}
        onClose={onClose}
        selectUser={selectFriendById}
      />
    </Fragment>
  );
}

