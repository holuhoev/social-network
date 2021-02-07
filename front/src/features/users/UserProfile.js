import React from 'react';

import { Button, Drawer, Skeleton } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { addFriend, removeFriend, selectAddFriendData } from './usersSlice';
import { DeleteOutlined, UserAddOutlined } from '@ant-design/icons';

function renderButton(loading, disabled, onClick, title, icon) {
  return (<Button
    icon={icon}
    loading={loading}
    disabled={disabled}
    onClick={onClick}
  >
    {title}
  </Button>);
}


function renderProfile(user, visible, onClose, button) {
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
        {button && button()}
      </Skeleton>
    </Drawer>
  )
}

export const UserProfile = ({ userId, visible, onClose, selectUser }) => {

  const user = useSelector(selectUser(userId));
  const addFriendData = useSelector(selectAddFriendData(userId));
  const dispatch = useDispatch();
  const addFriendStatus = addFriendData ? addFriendData.addFriendStatus : 'idle';

  if (!user) {
    return null;
  }


  const onAddFriendClick = () => {
    dispatch(addFriend(userId))
  };
  const onRemoveFriendClick = () => {
    dispatch(removeFriend(userId))
  };

  const button = user.myFriend
    ? () => renderButton(addFriendStatus === 'loading', addFriendStatus === 'loading', onRemoveFriendClick, 'Remove' +
      ' friend',
      <DeleteOutlined/>)
    : () => renderButton(addFriendStatus === 'loading', addFriendStatus === 'loading', onAddFriendClick, 'Add friend',
      <UserAddOutlined/>);


  return renderProfile(
    user,
    visible,
    onClose,
    button
  );
};