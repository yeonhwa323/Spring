package org.doit.senti.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
  private int member_id;
  private String member_pwd;
  private String member_name;
  private String member_email;
}
