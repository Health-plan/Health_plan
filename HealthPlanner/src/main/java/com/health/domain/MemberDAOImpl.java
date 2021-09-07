package com.health.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.health.mapper.MemberMapper;


@Repository
public class MemberDAOImpl implements MemberDAO{


		@Autowired
		MemberMapper memberMapper;

		@Override
		public Account findById(String mbr_id) {
			return memberMapper.readAccount(mbr_id);
		}
	
}
