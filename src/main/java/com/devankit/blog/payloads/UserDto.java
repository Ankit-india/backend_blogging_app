package com.devankit.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {

	private int id;
	
	@NotEmpty
	@Size(min = 3, message = "User name must be greater than 3")
	private String name;
	@Email(message = "Entered email is not valid")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be greater than 3 and less than 10")
	private String password;
	@NotEmpty
	private String about;
}
