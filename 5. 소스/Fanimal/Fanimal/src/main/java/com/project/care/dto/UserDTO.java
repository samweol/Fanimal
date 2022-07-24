package com.project.care.dto;

import lombok.Data;

@Data
public class UserDTO {

	private String id;
	private String name;
	private String nickname;
	private String password;
	private String tel;
	private String address;
	private String joindate;
	private Double xcoor;
	private Double ycoor;
	private String birth;
	private String picture;
	
	private String useq;
}
