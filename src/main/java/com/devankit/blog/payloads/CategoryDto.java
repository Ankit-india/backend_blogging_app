package com.devankit.blog.payloads;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {

	private Integer categoryId;
	@NotBlank
	@Size(min = 4, message = "Category title should be greater than 4")
	private String categoryTitle;
	@NotBlank
	@Size(min = 4, message = "Category title should be greater than 4")
	private String categoryDescription;
}
