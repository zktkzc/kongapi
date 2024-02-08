import { getInterfaceInfoByIdUsingGet } from '@/services/kongapi-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import {Card, Descriptions, message} from 'antd';
import React, { useEffect } from 'react';
import { useParams } from 'react-router';

const Index: React.FC = () => {
  const [loading, setLoading] = React.useState(false);
  const [data, setData] = React.useState<API.InterfaceInfo>();
  const params = useParams();
  const loadData = async () => {
    if (!params.id) message.error('缺少参数');
    setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGet({
        id: params.id,
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

  return (
    <PageContainer title="接口详情">
      <Card>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="ID">{data.id}</Descriptions.Item>
            <Descriptions.Item label="接口状态">{data.status === 1 ? '正常' : '下线'}</Descriptions.Item>
            <Descriptions.Item label="接口描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="接口地址">{data.url}</Descriptions.Item>
            <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="创建时间">{data.createTime}</Descriptions.Item>
            <Descriptions.Item label="更新时间">{data.updateTime}</Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )}
      </Card>
    </PageContainer>
  );
};

export default Index;
