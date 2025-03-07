package kr.co.dysnt.framework.common.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.egovframe.rte.psl.dataaccess.EgovAbstractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import javax.annotation.PostConstruct;

@Repository("commonDao")
public class CommonDao extends EgovAbstractMapper {

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @PostConstruct
    public void init() {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public <T> T selectOne(String method) {
        return this.getSqlSession().selectOne(method);
    }

    public <T> T selectOne(String method, Object params) {
        System.out.println("Executing method: " + method);
        System.out.println("Executing params: " + params);

        T result = null;
        try {
            result = this.getSqlSession().selectOne(method, params);
            System.out.println("Query Result: " + result); // ✅ 반드시 찍히도록 보장
        } catch (Exception e) {
            System.out.println("SQL Execution Error: " + e.getMessage());
            e.printStackTrace(); // ❗ SQL 에러 로그 출력
        }

        return result;
    }

    public <T> List<T> selectList(String method) {
        return this.getSqlSession().selectList(method);
    }

    public <T> List<T> selectList(String method, Object params) {
        return this.getSqlSession().selectList(method, params);
    }

    public int insert(String method, Object params) {
        return this.getSqlSession().insert(method, params);
    }

    public int update(String method, Object params) {
        return this.getSqlSession().update(method, params);
    }

    public int delete(String method, Object params) {
        return this.getSqlSession().delete(method, params);
    }

}
