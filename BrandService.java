package service;

import mapper.BrandMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import proj.Brand;
import tools.SqlSessionFactoryTools;

import java.util.List;

public class BrandService {
    SqlSessionFactory factory = SqlSessionFactoryTools.getSqlSessionFactory();
    /*
    * 查询所有
    * */
    public List<Brand> selectAll() {
        //
        SqlSession sqlSession = factory.openSession();
        //
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //
        List<Brand> brands = brandMapper.selectAll();
        //
        sqlSession.close();
        //
        return brands;
    }

    /*
    * 添加信息
    * */
    public void add(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.add(brand);

        sqlSession.commit();

        sqlSession.close();
    }

    public Brand selectById(int id) {
        //
        SqlSession sqlSession = factory.openSession();
        //
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //
        Brand brand = brandMapper.selectById(id);
        //
        sqlSession.close();
        //
        return brand;
    }

    public void update(Brand brand) {
        SqlSession sqlSession = factory.openSession();
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);

        brandMapper.update(brand);

        sqlSession.commit();

        sqlSession.close();
    }

}
