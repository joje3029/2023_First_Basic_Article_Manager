package com.KoreaIT.java.AM.dto;

public class Article{
	public int num;
	public String title;
	public String body;
	public String regDate;
	public int check;
	
	
// 이거는 테스트용 데이터는 만들때 조회수도 만들어져 있으니 check=check에 인자도 들어가 있는거고. 
	public Article(String title, String body, int num, String regDate, int check) { 
		this.num = num;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
		this.check = check; 
	// test용 데이터가 아닐때 this.check = check 가 아닌 이유 : 위에 애들을 왜 연결했는지 알면 보임. 위에 애 딴 애들은 write. 즉 글을 만들면서 입력받은것(=기존에 없던것)이 연결되야하고. 조회수는 기존에 값이 있어야하고. 위에서 write할때마다 조회수 입력해주세요해서 엮을꺼면 몰라 그거 아니면 연결할 이유없고 당연히 0부터고.
	//생성자는 이거 만들때 이거 한번 실행해 이므로 ()안에 check가 들어갈 이유가 없음. 
	//위에 저놈들도 받아온걸로 안해도 되는데 안하면 모두가 똑같은 객체로 만들어지니까 그럼 의미가 없으니까 하는 것임.	
	}
//기존 mian메소드 안에 있는 내용을 저장하기 위해 쓰는 생성자. 여기는 만들때 조회수 0이어야하니까 0인거고.
	public Article(String title, String body, int num, String regDate) {
		this(title, body, num, regDate, 0); //이거는 무조건 생성자의 첫번째 줄에 존재해야함. 이건 무조건 지켜야함!!
	//여기가 0인 이유는 test용 데이터가 아닐때 위의 내용이 this.check =0;인것과 동일.
	//this();는 다른생성자한테 일 넘기는 애. 여기서는 static main 메소드 안의 article과 밖의 article이 생성자가 안맞는데(인자가 안맞음) 결국 위의 article 생성자가 하는 일을 써먹어야하니 떠넘기기.
	//그래서 this();안에 위의 생성자 인자 내용이 들어간 것임.
	//	
	}
	
}
