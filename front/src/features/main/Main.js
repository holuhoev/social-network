import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './Main.css';
import { Breadcrumb, Layout, Menu, Typography, Button } from 'antd';

import {
  UserOutlined,
  UsergroupAddOutlined,
  LogoutOutlined,
  SmileFilled
} from '@ant-design/icons';
import { Users } from '../users/Users';
import {
  pageSelected,
  selectCurrentPage
} from '../application/applicationSlice';
import { Profile } from '../profile/Profile';
import { Friends } from '../friends/Friends';
import { logout } from '../auth/authSlice';

const { Header, Content, Footer, Sider } = Layout;
const { Title } = Typography;

function renderCurrentPage(currentPage) {
  switch (currentPage) {
    case 'Profile':
      return <Profile/>;
    case 'Friends':
      return <Friends/>;
    case 'Users':
    default:
      return <Users/>;
  }
}

function Main() {
  const dispatch = useDispatch();
  const currentPage = useSelector(selectCurrentPage);

  const handleClickMenu = e => {
    if (e.key === 'Logout') {
      return;
    }
    dispatch(pageSelected(e.key))
  };

  const handleLogoutClick = e => {
    localStorage.clear();
    dispatch(logout())
  };

  return (
    <div className="App">
      <Layout>
        <Header style={{ padding: 10 }}>
          <Title style={{ color: 'white' }} level={3}>Social Network</Title>
        </Header>
        <Layout>
          <Sider>
            <Menu
              selectedKeys={[currentPage]}
              mode="inline"
              onClick={handleClickMenu}
            >
              <Menu.Item key='Users' icon={<UserOutlined/>}>
                Users
              </Menu.Item>
              <Menu.Item key='Friends' icon={<UsergroupAddOutlined/>}>
                Friends
              </Menu.Item>
              <Menu.Item key='Profile' icon={<SmileFilled/>}>
                Profile
              </Menu.Item>
              <Menu.Item
                key='Logout'
                icon={<LogoutOutlined/>}
                onClick={handleLogoutClick}
              >
                Logout
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout style={{ backgroundColor: 'white', height: '94vh' }}>
            <Content style={{ padding: '0 50px', maxHeight: '500' }}>
              <Breadcrumb style={{ margin: '16px 0' }}>
                <Breadcrumb.Item>{currentPage}</Breadcrumb.Item>
              </Breadcrumb>
              {renderCurrentPage(currentPage)}
            </Content>
            <Footer style={{ textAlign: 'center' }}>
              Social Network for OTUS homework
            </Footer>
          </Layout>
        </Layout>
      </Layout>
    </div>
  );
}

export default Main;
