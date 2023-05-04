package com.KoreaIT.java.AM.dto;

public class Article{
	public int num;
	public String title;
	public String body;
	public String regDate;
	public int check;
	
	
	public Article(String title, String body, int num, String regDate, int check) { 
		this.num = num;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.check = check; 
	}

	public Article(String title, String body, int num, String regDate) {
		this(title, body, num, regDate, 0); 
	}
	
}
