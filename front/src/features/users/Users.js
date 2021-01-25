import React, { Fragment, useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { fetchUsers, selectAllUsers } from './usersSlice';
import { Avatar, List } from 'antd';
import UserProfile from './UserProfile';

export function Users() {
  const users = useSelector(selectAllUsers);
  const usersStatus = useSelector(state => state.users.status);

  const dispatch = useDispatch();

  useEffect(() => {
    if (usersStatus === 'idle') {
      dispatch(fetchUsers())
    }
  }, [usersStatus, users]);

  const [selectedUserId, setSelectedUserId] = useState('');
  const [visible, setVisible] = useState(false);

  const onSelect = userId => {
    setSelectedUserId(userId);
    setVisible(true);
  };

  const onClose = () => setVisible(false);

  return (
    <Fragment>
      <div style={{ height: 800, overflow: 'auto' }}>
        <List
          itemLayout="vertical"
          size="medium"
          // pagination={{ pageSize: 8 }}
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
                  <a
                    onClick={() => onSelect(user.userId)}>{user.username}
                  </a>
                }
                description={user.firstName + ' ' + user.lastName}
              />
            </List.Item>
          )}
        />
      </div>
      <UserProfile
        userId={selectedUserId}
        visible={visible}
        onClose={onClose}
      />
    </Fragment>


  );
}
