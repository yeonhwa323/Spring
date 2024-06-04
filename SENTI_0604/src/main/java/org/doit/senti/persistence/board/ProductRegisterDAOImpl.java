package org.doit.senti.persistence.board;

import java.sql.SQLException;

import org.doit.senti.domain.board.ProductImageDTO;
import org.doit.senti.domain.board.ProductRegisterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

public class ProductRegisterDAOImpl implements ProductRegisterDAO{
	
	@Autowired
	private NamedParameterJdbcTemplate npJdbcTemplate;
	
	@Override
	public int insertProduct(ProductRegisterDTO pdDTO) throws ClassNotFoundException, SQLException {
		
		String sql = "INSERT INTO product(pd_id, pd_name, pd_info, brand_id, pd_price, pd_discount_rate, main_ctgr_id, large_ctgr_id, medium_ctgr_id, small_ctgr_id) "
				+ "VALUES ((SELECT NVL(MAX(TO_NUMBER(pd_id)),0)+1 FROM product), :pd_name, :pd_info, :brand_id, :pd_price, :pd_discount_rate, :main_ctgr_id, :large_ctgr_id, :medium_ctgr_id, :small_ctgr_id) ";
		
		SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(pdDTO);
		
		return this.npJdbcTemplate.update(sql,  parameterSource);
	}

	@Override
	public int insertProductImg(ProductImageDTO pdImgDTO) throws ClassNotFoundException, SQLException {
		
		String sql =  " INSERT INTO product_image(pd_image_id, pd_image_url, pd_id, pd_image_uuid) "
				+ " values ((SELECT NVL(MAX(TO_NUMBER(pd_image_id)),0)+1 FROM product_image), :pd_image_url, (SELECT NVL(MAX(TO_NUMBER(pd_id)),0) FROM product), :pd_image_uuid) ";
		
		SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(pdImgDTO);
		
		return this.npJdbcTemplate.update(sql,  parameterSource);
	}
	
	@Override
	public int insertProductImgInfo(ProductImageDTO pdImgDTO) throws ClassNotFoundException, SQLException {
		
		String sql = " UPDATE product_image "
				+ "SET pd_info_image_url = :pd_info_image_url , pd_image_info_uuid = :pd_image_info_uuid "
				+ "WHERE pd_image_id = (SELECT MAX(pd_image_id) FROM product_image) ";
		
		SqlParameterSource parameterSource= new BeanPropertySqlParameterSource(pdImgDTO);
		
		return this.npJdbcTemplate.update(sql,  parameterSource);
	
	}
	
}
