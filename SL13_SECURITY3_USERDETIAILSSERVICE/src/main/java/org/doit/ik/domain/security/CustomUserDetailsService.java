package org.doit.ik.domain.security;

import java.sql.SQLException;

import org.doit.ik.domain.MemberVO;
import org.doit.ik.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component("customUserDetailsService")
@Log4j
public class CustomUserDetailsService 
implements UserDetailsService {
   
   @Autowired
   private MemberMapper memberMapper;

   // UserDetails 인증받은 사용자의 정보를 
   // read(id) -> MemberVO   ->   UserDetails 타입 변환
   //                   CustomerUser클래스
   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      log.warn("> MemberMapper.Load User By UserName : " + username);
      MemberVO vo = null ;
      try {
         vo = this.memberMapper.read(username);
      } catch (ClassNotFoundException | SQLException e) { 
         System.out.println(" > MemberMapper.Load User By UserName : " );
         e.printStackTrace();
      }
      return vo == null ? null : new CustomerUser(vo);
   }

}
