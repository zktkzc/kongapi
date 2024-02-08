import { listInterfaceInfoByPageUsingGet } from '@/services/kongapi-backend/interfaceInfoController';
import { PageContainer } from '@ant-design/pro-components';
import { List, message } from 'antd';
import React, { useEffect } from 'react';

const Index: React.FC = () => {
  const [loading, setLoading] = React.useState(false);
  const [list, setList] = React.useState<API.InterfaceInfo[]>([]);
  const [total, setTotal] = React.useState<number>(0);

  const loadData = async (current: number = 1, pageSize: number = 10) => {
    setLoading(true);
    try {
      const res = await listInterfaceInfoByPageUsingGet({
        current,
        pageSize,
      });
      setList(res?.data?.records ?? []);
      setTotal(res?.data?.total ?? 0);
    } catch (e: any) {
      message.error('加载数据失败: ' + e.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, []);

  return (
    <PageContainer title="在线接口开放平台">
      <List
        className="my-list"
        loading={loading}
        itemLayout="horizontal"
        dataSource={list}
        renderItem={(item) => (
          <List.Item actions={[<a href={`/interface_info/${item.id}`} key={item.id}>查看</a>]}>
            <List.Item.Meta
              title={<a href={`/interface_info/${item.id}`}>{item.name}</a>}
              description={item.description}
            />
          </List.Item>
        )}
        pagination={{
          pageSize: 10,
          total: total,
          onChange: (page, pageSize) => {
            loadData(page, pageSize);
          },
          showTotal: (total, range) => `共 ${total} 条`,
        }}
      />
    </PageContainer>
  );
};

export default Index;
