package mapper;

import org.apache.ibatis.annotations.*;
import proj.Brand;

import java.util.List;

public interface BrandMapper {
    // 查询所有
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    // 插入信息
    @Insert("insert into tb_brand values(null, #{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    void add(Brand brand);
//
    // 通过id查询
    @Select("select * from tb_brand where id = #{id}")
    @ResultMap("brandResultMap")
    Brand selectById(int id);
//
//    // 修改数据
    @Update("update tb_brand set brand_name = #{brand.brandName}, company_name = #{brand.companyName}, ordered = #{brand.ordered}, description = #{brand.description}, status = #{brand.status} where id = #{id}")
    void update(@Param("id") int id, @Param("brand") Brand brand);

    // 删除
    @Delete("delete from tb_brand where brand_name = #{brandName} and company_name = #{companyName} and ordered = #{ordered} and description = #{description} and status = #{status}")
    @ResultMap("brandResultMap")
    void delete(Brand brand);

    // 批量删除
    void deleteByIds(@Param("ids")int[] ids);

    // 分页查询
    @Select("select * from tb_brand limit #{begin}, #{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size") int size);

    // 查询总记录数
    @Select("select count(*) from tb_brand")
    int totalCount();

    // 根据条件
    List<Brand> selectByPageAndCondition(@Param("begin") int begin, @Param("size") int size, @Param("brand")Brand brand);

    // 查询总记录数根据条件
    int totalCountByCondition(Brand brand);
}
