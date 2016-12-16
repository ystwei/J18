package com.weikun.mapper;

import com.weikun.model.Company;
import com.weikun.model.CompanyExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface CompanyMapper {
    @SelectProvider(type=CompanySqlProvider.class, method="countByExample")
    long countByExample(CompanyExample example);

    @DeleteProvider(type=CompanySqlProvider.class, method="deleteByExample")
    int deleteByExample(CompanyExample example);

    @Delete({
        "delete from company",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer cid);

    @Insert({
        "insert into company (cid, name, ",
        "address, pro, city, ",
        "price)",
        "values (#{cid,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{address,jdbcType=VARCHAR}, #{pro,jdbcType=VARCHAR}, #{city,jdbcType=VARCHAR}, ",
        "#{price,jdbcType=REAL})"
    })
    int insert(Company record);

    @InsertProvider(type=CompanySqlProvider.class, method="insertSelective")
    int insertSelective(Company record);

    @SelectProvider(type=CompanySqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="pro", property="pro", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL)
    })
    List<Company> selectByExample(CompanyExample example);

    @Select({
        "select",
        "cid, name, address, pro, city, price",
        "from company",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="address", property="address", jdbcType=JdbcType.VARCHAR),
        @Result(column="pro", property="pro", jdbcType=JdbcType.VARCHAR),
        @Result(column="city", property="city", jdbcType=JdbcType.VARCHAR),
        @Result(column="price", property="price", jdbcType=JdbcType.REAL),
        @Result(property = "elist",javaType = List.class,column="cid"
        ,many = @Many(select="com.weikun.mapper.EmpMapper.selectEmpsByCid"))
    })
    Company selectByPrimaryKey(Integer cid);

    @UpdateProvider(type=CompanySqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Company record, @Param("example") CompanyExample example);

    @UpdateProvider(type=CompanySqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Company record, @Param("example") CompanyExample example);

    @UpdateProvider(type=CompanySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Company record);

    @Update({
        "update company",
        "set name = #{name,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "pro = #{pro,jdbcType=VARCHAR},",
          "city = #{city,jdbcType=VARCHAR},",
          "price = #{price,jdbcType=REAL}",
        "where cid = #{cid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Company record);
}