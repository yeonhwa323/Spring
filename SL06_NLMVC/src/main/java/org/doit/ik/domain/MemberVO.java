package org.doit.ik.domain;

import java.util.Date;

public class MemberVO {
	
	// member 테이블의 컬러명과 필드명 동일...
	private String id; // 1. 수정 uid -> id
	private String pwd;
	private String name;
	private String gender;
	private String birth;
	private String is_lunar; // 2. 수정 -> is_lunar
	private String cphone; // 3. 수정 -> cphone
	private String email;
	private String habit;
	private Date   regdate; // 4. 수정 -> regdate	
	
}
