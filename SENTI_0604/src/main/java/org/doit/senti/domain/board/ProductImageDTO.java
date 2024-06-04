package org.doit.senti.domain.board;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductImageDTO {
	
	private int pd_image_id;
	private String pd_image_url;
	private String pd_info_image_url;
	private int pd_id;
	private String pd_image_uuid;
	private String pd_image_info_uuid;
	
	private CommonsMultipartFile file;
	
}
