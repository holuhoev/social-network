import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './Main.css';
import { Breadcrumb, Layout, Menu, Typography } from 'antd';

import { UserOutlined } from '@ant-design/icons';
import { Users } from '../users/Users';
import {
  pageSelected,
  selectCurrentPage
} from '../application/applicationSlice';
import { fetchClientInfo, selectClientInfoStatus } from '../auth/authSlice';

const { Header, Content, Footer, Sider } = Layout;
const { Title } = Typography;

function Main() {
  const dispatch = useDispatch();
  const currentPage = useSelector(selectCurrentPage);
  const clientInfoStatus = useSelector(selectClientInfoStatus);

  useEffect(() => {
    if (clientInfoStatus === 'idle') {
      dispatch(fetchClientInfo())
    }
  }, [clientInfoStatus]);

  const handleClickMenu = e => {
    dispatch(pageSelected(e.key))
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
              <Menu.Item key='Friends' icon={<UserOutlined/>}>
                Friends
              </Menu.Item>
            </Menu>
          </Sider>
          <Layout style={{ backgroundColor: 'white', height: '94vh' }}>
            <Content style={{ padding: '0 50px', maxHeight: '500' }}>
              <Breadcrumb style={{ margin: '16px 0' }}>
                <Breadcrumb.Item>{currentPage}</Breadcrumb.Item>
              </Breadcrumb>
              <Users/>
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
