package com.xupt.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import com.xupt.bean.Employee;
import com.xupt.dao.EmployeeMapper;

class MyBatisTest {
	private SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		return sqlSessionFactory;
	}
	@Test
	public void test() throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory =new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession openSession = sqlSessionFactory.openSession();
		Employee employee=openSession.selectOne("com.xupt.EmployeeMapper.selectEmp", 1);
		System.out.println(employee);
		openSession.close();
	}
	@Test
	public void test01() throws IOException {
		SqlSessionFactory sqlSessionFactory=getSqlSessionFactory();
		SqlSession openSession=sqlSessionFactory.openSession();
		try {
			EmployeeMapper mapper=openSession.getMapper(EmployeeMapper.class);
			Employee employee=mapper.getEmployee(1);
			System.out.println(employee);
		}finally {
			openSession.close();
		}
	}

}
