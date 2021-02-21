import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Button, Form, Input, Modal, Typography } from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import './LoginForm.css'
import { login, selectLoginStatus } from './authSlice';

const { Title } = Typography;

export function LoginForm() {

  const dispatch = useDispatch();
  const onFinish = values => {
    const { username, password } = values;
    dispatch(login({ username: username, password: password }));
  };

  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const [modalText, setModalText] = React.useState('Content of the modal');
  const loginStatus = useSelector(selectLoginStatus);

  const showModal = () => {
    setVisible(true);
  };

  const handleOk = () => {
    setModalText('The modal will be closed after two seconds');
    setConfirmLoading(true);
    setTimeout(() => {
      setVisible(false);
      setConfirmLoading(false);
    }, 2000);
  };

  const handleCancel = () => {
    console.log('Clicked cancel button');
    setVisible(false);
  };

  return (
    <div className="login-container">
      <Title className="login-title" level={1}>Social network for OTUS
        Homework</Title>
      <Form
        className="login-form"
        name="normal_login"
        initialValues={{
          remember: true
        }}
        onFinish={onFinish}
      >
        <Form.Item
          name="username"
          rules={[
            {
              required: true,
              message: 'Please input your Username!'
            }
          ]}
        >
          <Input
            prefix={<UserOutlined className="site-form-item-icon"/>}
            placeholder="Username"
          />
        </Form.Item>
        <Form.Item
          name="password"
          rules={[
            {
              required: true,
              message: 'Please input your Password!'
            }
          ]}
        >
          <Input
            prefix={<LockOutlined className="site-form-item-icon"/>}
            type="password"
            placeholder="Password"
          />
        </Form.Item>
        <Form.Item>
          <Button
            type="primary"
            htmlType="submit"
            className="login-form-button"
            loading={loginStatus === 'loading'}
          >
            Log in
          </Button>
          Or <Button style={{ paddingLeft: 0 }} type="link" onClick={showModal}>register
          now!</Button>
        </Form.Item>
        <Modal
          title="Title"
          visible={visible}
          onOk={handleOk}
          confirmLoading={confirmLoading}
          onCancel={handleCancel}
        >
          <p>{modalText}</p>
        </Modal>
      </Form>
    </div>
  );
};