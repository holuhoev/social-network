import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import {
  Button,
  Form,
  Input,
  InputNumber,
  Modal,
  Select,
  Typography
} from 'antd';
import { LockOutlined, UserOutlined } from '@ant-design/icons';
import './LoginForm.css'
import { login, register, selectLoginStatus } from './authSlice';

const { Option } = Select;
const { Title } = Typography;

const layout = {
  labelCol: { span: 8 },
  wrapperCol: { span: 16 }
};
const tailLayout = {
  wrapperCol: { offset: 8, span: 16 }
};

function registerProfileModal(visible, loading, handleCancel, form, handleOk) {
  return <Modal
    title="Register"
    visible={visible}
    footer={null}
    onCancel={handleCancel}
  >
    <Form
      {...layout}
      form={form}
      name="control-hooks"
      onFinish={handleOk}
    >
      <Form.Item name="username" label="Username"
                 rules={[{ required: true }]}>
        <Input disabled={loading}
               placeholder="Username"/>
      </Form.Item>
      <Form.Item name="password" label="Password"
                 rules={[{ required: true }]}>
        <Input
          prefix={<LockOutlined className="site-form-item-icon"/>}
          type="password"
          placeholder="Password"
        />
      </Form.Item>
      <Form.Item name="firstName" label="First name"
                 rules={[{ required: true }]}>
        <Input disabled={loading}
               placeholder="First name"/>
      </Form.Item>
      <Form.Item name="lastName" label="Last name"
                 rules={[{ required: true }]}>
        <Input disabled={loading}
               placeholder="Last name"/>
      </Form.Item>
      <Form.Item name="age" label="Age"
                 rules={[{ required: true }]}>
        <InputNumber min={8} max={120}
                     disabled={loading}
                     placeholder="23"/>
      </Form.Item>
      <Form.Item name="city" label="City"
                 rules={[{ required: true }]} placeholder="Moscow">
        <Input disabled={loading}/>
      </Form.Item>
      <Form.Item
        name="interests"
        label="Interests"
        rules={[{ required: true }]}
      >
        <Input.TextArea
          maxLength={200}
          disabled={loading}
          placeholder={'Java, High-load, Wakeboard, Football '}
        />
      </Form.Item>
      <Form.Item name="gender" label="Gender"
                 rules={[{ required: true }]}>
        <Select
          placeholder="Select a option and change input text above"
          allowClear
        >
          <Option value="MALE">male</Option>
          <Option value="FEMALE">female</Option>
        </Select>
      </Form.Item>
      <Form.Item {...tailLayout}>
        <Button loading={loading} type="primary"
                htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  </Modal>;
}

export function LoginForm() {

  const dispatch = useDispatch();
  const onFinish = values => {
    const { username, password } = values;
    dispatch(login({ username: username, password: password }));
  };

  const [visible, setVisible] = React.useState(false);
  const [confirmLoading, setConfirmLoading] = React.useState(false);
  const loginStatus = useSelector(selectLoginStatus);
  const [form] = Form.useForm();

  const showModal = () => {
    setVisible(true);
  };

  const handleOk = (values) => {
    dispatch(register(values));
    setConfirmLoading(true);
    setTimeout(() => {
      setVisible(false);
      setConfirmLoading(false)
    }, 2000);
  };

  const handleCancel = () => {
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
        {registerProfileModal(visible, loginStatus==='loading' || confirmLoading, handleCancel, form, handleOk)}
      </Form>
    </div>
  );
}