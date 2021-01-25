import React from 'react';

import { Drawer, Skeleton } from 'antd';
import { useSelector } from 'react-redux';
import { selectUserById } from './usersSlice';

const UserProfile = ({ userId, visible, onClose }) => {

  const user = useSelector(selectUserById(userId));

  if(!user) {
    return null;
  }

  return (
    <Drawer
      destroyOnClose
      title={user.firstName + ' ' + user.lastName}
      visible={visible}
      width={640}
      onClose={onClose}
    >
      <Skeleton active loading={false} paragraph={{ rows: 4 }}>
        <div style={{ padding: 10 }}>
          <p>age - {user.age}</p>
          <p>city - {user.city}</p>
          <p>interests - {user.interests}</p>
        </div>
      </Skeleton>
    </Drawer>
  )
};

export default UserProfile;