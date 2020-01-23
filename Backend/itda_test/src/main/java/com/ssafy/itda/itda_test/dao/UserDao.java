package com.ssafy.itda.itda_test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.itda.itda_test.model.User;

@Repository
public class UserDao {
	String ns = "ssafy.user.";

	@Autowired
	private SqlSession sqlSession;

	public void signUp(User u) {
		sqlSession.insert(ns + "signUp", u);
	}
}
