package service.impl;

import mapper.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import proj.Brand;
import proj.PageBean;
import service.BrandService;
import tools.SqlSessionFactoryTools;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    SqlSessionFactory factory = SqlSessionFactoryTools.getSqlSessionFactory();
    @Override
    public List<Brand> selectAll() {
        // 获取sqlsession对象
        SqlSession sqlSession = factory.openSession();
        // 获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 获取数据
        List<Brand> brands = mapper.selectAll();
        // 释放资源
        sqlSession.close();
        return brands;
    }

    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        // 获取brandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 调用方法
        mapper.add(brand);
        // 提交事务
        sqlSession.commit();
        //
        sqlSession.close();
    }

    // 单个删除
    public void delete(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        // 获取
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        mapper.delete(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    // 进行修改
    public void update(int id, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.update(id, brand);
        sqlSession.commit();
        sqlSession.close();
    }

    // 批量删除
    public void deleteByIds(int[] ids) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        mapper.deleteByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    // 分页查询
    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        List<Brand> brands = mapper.selectByPage(begin, size);

        int totalCount = mapper.totalCount();

        // 创建pagebean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(brands);

        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        // 计算开始索引
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;

        // 处理查询条件
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0)
        {
            brand.setBrandName("%" + brandName + "%");
        }
        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0)
        {
            brand.setCompanyName("%" + companyName + "%");
        }
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);

        int totalCount = mapper.totalCountByCondition(brand);

        // 创建pagebean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setTotalCount(totalCount);
        pageBean.setRows(rows);

        sqlSession.close();

        return pageBean;
    }
}
