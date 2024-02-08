import {
  getInterfaceInfoByIdUsingGet,
  invokeInterfaceInfoUsingPost
} from '@/services/kongapi-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import {Button, Card, Descriptions, Divider, Form, message} from 'antd';
import React, { useEffect } from 'react';
import { useParams } from 'react-router';
import TextArea from "antd/es/input/TextArea";

const Index: React.FC = () => {
  const [loading, setLoading] = React.useState(false);
  const [invokeLoading, setInvokeLoading] = React.useState(false);
  const [data, setData] = React.useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = React.useState<any>();
  const params = useParams();
  const loadData = async () => {
    if (!params.id) {
      message.error('缺少参数');
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGet({
        id: Number(params.id),
      });
      setData(res.data);
    } catch (e: any) {
      message.error('加载数据失败: ' + e.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, []);

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error('缺少参数');
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPost({
        id: Number(params.id),
        ...values
      });
      setInvokeRes(res.data);
      message.success('请求成功');
    } catch (e: any) {
      message.error('请求失败: ' + e.message);
    }
    setInvokeLoading(false);
  };

  return (
    <PageContainer title="接口详情">
      <Card loading={loading}>
        {data ? (
            <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="ID">{data.id}</Descriptions.Item>
            <Descriptions.Item label="接口状态">{data.status === 1 ? '正常' : '下线'}</Descriptions.Item>
            <Descriptions.Item label="接口描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="接口地址">{data.url}</Descriptions.Item>
            <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="创建时间">{data.createTime}</Descriptions.Item>
            <Descriptions.Item label="更新时间">{data.updateTime}</Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )}
      </Card>
      <Divider/>
      <Card title="在线调用">
        <Form name="invoke" onFinish={onFinish} layout='vertical'>
          <Form.Item label="请求参数" name="userRequestParams">
            <TextArea />
          </Form.Item>
          <Form.Item>
            <Button type="primary" htmlType="submit" block>
              发送请求
            </Button>
          </Form.Item>
        </Form>
      </Card>
      <Divider/>
      <Card title="响应结果" loading={invokeLoading}>
        <pre>{JSON.stringify(invokeRes, null, 2)}</pre>
      </Card>
    </PageContainer>
  );
};

export default Index;
