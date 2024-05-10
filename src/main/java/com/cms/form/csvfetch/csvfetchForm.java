package com.cms.form.csvfetch;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.cms.form.BaseForm;

import lombok.Getter;
import lombok.Setter;

/**
 * 读取CSV
 */
@Getter
@Setter
public class csvfetchForm extends BaseForm{
	
	@NotNull
	private MultipartFile fileInput;
	
	@NotEmpty
	int itemCount;
}
