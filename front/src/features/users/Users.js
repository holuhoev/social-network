import React, { Fragment, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchUsers, selectAllUsers, selectUserById } from './usersSlice';
import { Avatar, List } from 'antd';
import {UserProfile} from './UserProfile';

export function Users() {
  const users = useSelector(selectAllUsers);
  const usersStatus = useSelector(state => state.users.status);

  const dispatch = useDispatch();

  useEffect(() => {
    if (usersStatus === 'idle') {
      dispatch(fetchUsers())
    }
  }, [usersStatus]);

  const [selectedUserId, setSelectedUserId] = useState('');
  const [visible, setVisible] = useState(false);

  const onSelect = userId => {
    setSelectedUserId(userId);
    setVisible(true);
  };

  const onClose = () => {
    setVisible(false)
  };

  return (
    <Fragment>
      {renderUserList(usersStatus ==='loading', users, onSelect)}
      <UserProfile
        userId={selectedUserId}
        visible={visible}
        onClose={onClose}
        selectUser={selectUserById}
      />
    </Fragment>


  );
}


export function renderUserList(loading, users, onSelect) {
  return (<div style={{ height: 750, overflow: 'auto' }}>
    <List
      loading={loading}
      itemLayout="vertical"
      size="medium"
      dataSource={users}
      renderItem={user => (
        <List.Item key={user.userId}>
          <List.Item.Meta
            avatar={
              <Avatar
                src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png"
              />
            }
            title={
              <a onClick={() => onSelect(user.userId)}>
                {user.firstName + ' ' + user.lastName}
              </a>
            }
            description={user.username}
          />
        </List.Item>
      )}
    />
  </div>);
}
