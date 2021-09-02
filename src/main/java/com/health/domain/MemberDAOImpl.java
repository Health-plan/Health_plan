package com.health.domain;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAOImpl implements MemberDAO{
	
	@Autowired
	SqlSession sql;
	
	@Override 
    public void insertMember(MemberDTO memberDto) {    
       sql.insert("memberRegister",memberDto);
    }
    
}
