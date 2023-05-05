package com.KoreaIT.java.AM.dto;

public class Member {
		public int num;
		public String LogDate;
		public String LogID;
		public String LogPw;
		public String name;
		
		public Member(int num, String LogDate, String LogID, String LogPw, String name) {
			this.num = num;
			this.LogDate = LogDate;
			this.LogID = LogID;
			this.LogPw = LogPw;
			this.name = name;
		
	}
		
//		public Member(String title, String body, int num, String regDate) {
//			this(title, body, num, regDate, 0); 
//		}
}
