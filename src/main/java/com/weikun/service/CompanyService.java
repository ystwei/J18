package com.weikun.service;

import com.weikun.model.Company;
import com.weikun.model.Emp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;

/**
 * Created by Administrator on 2016/12/12.
 */
public class CompanyService {
    private SqlSessionFactory ssf=null;
    public CompanyService(){
        InputStream is=CompanyService.class.getClassLoader().getResourceAsStream("mybatis.cfg.xml");
        ssf=new SqlSessionFactoryBuilder().build(is);

    }

    @Test
    public void update(){
        SqlSession session=ssf.openSession();
        try {

            Company com=new Company();

            com.setCid(4);
            com.setName("阿里巴巴");

            session.update("com.weikun.mapper.CompanyMapper.updateByPrimaryKeySelective",com);


            System.out.println("ok");
            session.commit();
            session.close();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
        }

    }
    @Test
    public void del(){

        SqlSession session=ssf.openSession();
        try {

            Company com=new Company();

            com.setCid(9);

            session.delete("com.weikun.mapper.CompanyMapper.deleteByPrimaryKey",com);


            System.out.println("ok");
            session.commit();
            session.close();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
        }
    }
    @Test
    public void add(){
        SqlSession session=ssf.openSession();
        try {
            Company com=new Company();
            com.setCid(9);
            com.setName("阿里巴巴");
            com.setAddress("哈尔滨");
            com.setCity("黑龙江");
            com.setPrice(999.1f);
            com.setPro("中国");
            session.insert("com.weikun.mapper.CompanyMapper.insert",com);

            Emp emp=new Emp();
            emp.setEid(10);
            emp.setCid(9);
            emp.setName("ALICE");
            emp.setEmail("123");
            emp.setSex("F");
            emp.setSalary(199.1f);
            session.insert("com.weikun.mapper.EmpMapper.insert",emp);

            System.out.println("ok");
            session.commit();
            session.close();
        } catch (Exception e) {
            session.rollback();
            e.printStackTrace();
        } finally {
        }


    }



}
