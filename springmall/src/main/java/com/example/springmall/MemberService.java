package com.example.springmall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	public int getCountMember() {
		//return 777;
		return memberMapper.selectCountMember();
		
	}
}
