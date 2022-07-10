package service;

import proj.Brand;
import proj.PageBean;

import java.util.List;

public interface BrandService {

//   查询所有
    List<Brand> selectAll();

    // 添加
    void add(Brand brand);

    // 删除
    void delete(Brand brand);

    // 更新
    void update(int id, Brand brand);

    // 查询，和删除一起使用
    void deleteByIds(int[] ids);

    // 分页查询
    PageBean<Brand> selectByPage(int currentPage, int pageSize);  // 当前页码，每页条数

    // 分页条件查询
    PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand);  // 当前页码，每页条数
}
