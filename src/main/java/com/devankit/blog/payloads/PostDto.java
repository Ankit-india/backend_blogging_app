package com.devankit.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDto {

	private Integer PostId;
	
	private String title;
	
	private String content;
	
	private Date addedDate;
	
	private String imageName;
	
	private CategoryDto category;
	
	private UserDto user;
}
