package org.doit.senti.persistence.board;

import java.sql.SQLException;

import org.doit.senti.domain.board.ProductImageDTO;
import org.doit.senti.domain.board.ProductRegisterDTO;

public interface ProductRegisterDAO {
	
	int insertProduct(ProductRegisterDTO pdDTO) throws ClassNotFoundException, SQLException;
	
	int insertProductImg(ProductImageDTO pdImgDTO) throws ClassNotFoundException, SQLException;
	
	int insertProductImgInfo(ProductImageDTO pdImgDTO) throws ClassNotFoundException, SQLException;
	
}
