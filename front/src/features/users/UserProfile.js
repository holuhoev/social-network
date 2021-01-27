import React from 'react';

import { Button, Drawer, Skeleton } from 'antd';
import { useDispatch, useSelector } from 'react-redux';
import { addFriend, selectAddFriendStatus, selectUserById } from './usersSlice';
import { UserAddOutlined, CheckOutlined } from '@ant-design/icons';

const UserProfile = ({ userId, visible, onClose, showAddFriendButton }) => {

  // TODO: 1. Вынести onClose в redux. Чтобы по закрытию удалять содержимо
  //  редюсера "добавить в друзья"

  // TODO: 2. При открытии вкладки пользователя, делать запрос. В котором
  //  вернется чел в друзьях или нет

  const user = useSelector(selectUserById(userId));
  const addFriendStatus = useSelector(selectAddFriendStatus);

  const dispatch = useDispatch();
  const onAddFriendClick = () => {
    dispatch(addFriend(userId))
  };

  if (!user) {
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
        {
          showAddFriendButton &&
          <Button
            icon={
              addFriendStatus === 'succeeded'
                ? <CheckOutlined/>
                : <UserAddOutlined/>
            }
            loading={addFriendStatus === 'loading'}
            disabled={addFriendStatus !== 'idle'}
            onClick={onAddFriendClick}
          >
            Add friend
          </Button>
        }
      </Skeleton>
    </Drawer>
  )
};

export default UserProfile;