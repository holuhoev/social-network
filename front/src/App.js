import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import './App.css';
import { Breadcrumb, Layout, Menu, Typography } from 'antd';

import { UserOutlined } from '@ant-design/icons';
import { Users } from './features/users/Users';
import {
  selectCurrentPage,
  pageSelected
} from './features/application/applicationSlice';

const { Header, Content, Footer, Sider } = Layout;
const { Title } = Typography;

function App() {
  const dispatch = useDispatch();
  const currentPage = useSelector(selectCurrentPage);

  const handleClickMenu = e => {
    console.log(e.key);
    dispatch(pageSelected(e.key))
  };

  console.log('currentPage:' + currentPage);

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

export default App;
