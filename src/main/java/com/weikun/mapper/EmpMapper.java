package com.weikun.mapper;

import com.weikun.model.Emp;
import com.weikun.model.EmpExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

public interface EmpMapper {

    @Select({
            "select",
            "eid, name, sex, email, salary, cid",
            "from emp",
            "where cid = #{cid,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="eid", property="eid", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
            @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
            @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
            @Result(column="salary", property="salary", jdbcType=JdbcType.REAL),
            @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER)
    })
    List<Emp> selectEmpsByCid(Integer cid);//通过cid键找到所有符合的emp记录

    @SelectProvider(type=EmpSqlProvider.class, method="countByExample")
    long countByExample(EmpExample example);

    @DeleteProvider(type=EmpSqlProvider.class, method="deleteByExample")
    int deleteByExample(EmpExample example);

    @Delete({
        "delete from emp",
        "where eid = #{eid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer eid);

    @Insert({
        "insert into emp (eid, name, ",
        "sex, email, salary, ",
        "cid)",
        "values (#{eid,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{salary,jdbcType=REAL}, ",
        "#{cid,jdbcType=INTEGER})"
    })
    int insert(Emp record);

    @InsertProvider(type=EmpSqlProvider.class, method="insertSelective")
    int insertSelective(Emp record);

    @SelectProvider(type=EmpSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="eid", property="eid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary", property="salary", jdbcType=JdbcType.REAL),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER)
    })
    List<Emp> selectByExample(EmpExample example);

    @Select({
        "select",
        "eid, name, sex, email, salary, cid",
        "from emp",
        "where eid = #{eid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="eid", property="eid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="sex", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="salary", property="salary", jdbcType=JdbcType.REAL),
        @Result(column="cid", property="cid", jdbcType=JdbcType.INTEGER),
        @Result(column="cid", property="com",
                one = @One(select="com.weikun.mapper.CompanyMapper.selectByPrimaryKey"))
    })
    Emp selectByPrimaryKey(Integer eid);

    @UpdateProvider(type=EmpSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Emp record, @Param("example") EmpExample example);

    @UpdateProvider(type=EmpSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Emp record, @Param("example") EmpExample example);

    @UpdateProvider(type=EmpSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Emp record);

    @Update({
        "update emp",
        "set name = #{name,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "salary = #{salary,jdbcType=REAL},",
          "cid = #{cid,jdbcType=INTEGER}",
        "where eid = #{eid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Emp record);
}