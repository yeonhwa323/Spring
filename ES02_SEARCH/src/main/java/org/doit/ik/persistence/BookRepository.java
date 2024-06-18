package org.doit.ik.persistence;

import java.util.List;
import java.util.Map;

import org.doit.ik.domain.BookDTO;
 
// ES - book 인덱스 + 추가/목록보기
public interface BookRepository {

   // 검색   
   List<Map<String, Object>> search(String word);

   // 저장
   void add(BookDTO dto);

}
