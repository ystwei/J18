package com.weikun.service;

import com.weikun.model.Company;
import com.weikun.model.Emp;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

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
            com.setName("°¢Àï°Í°Í");

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
            com.setName("°¢Àï°Í°Í");
            com.setAddress("¹þ¶û±õ");
            com.setCity("ºÚÁú½­");
            com.setPrice(999.1f);
            com.setPro("ÖÐ¹ú");
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
    @Test
    public void queryCompanyByid(){
        SqlSession session=ssf.openSession();
        try{
            Company c=new Company();
            c.setCid(1);
            Company com=(Company)session.selectOne("com.weikun.mapper.CompanyMapper.selectByPrimaryKey",c);
            System.out.println(com.getName());
            List<Emp> elist=(List<Emp>)com.getElist();
            for(Emp e :elist){
                System.out.println(e.getName());
            }


        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
    }


    @Test
    public void queryEmpByid(){
        SqlSession session=ssf.openSession();
        try{
            Emp e=new Emp();
            e.setEid(4);

            Emp emp=(Emp)session.selectOne("com.weikun.mapper.EmpMapper.selectByPrimaryKey",e);
            System.out.println(emp.getName());
            List<Company> list=(List)emp.getCom();

            System.out.println(list.get(0).getName());
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            session.close();
        }
    }



}
