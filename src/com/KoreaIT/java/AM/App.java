package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.dto.Member;
import com.KoreaIT.java.AM.util.Util;

public class App{
	List<Article> articles;
	List<Member> members;
	
	public App() {
		articles = new ArrayList<>();
		members = new ArrayList<>();
}	

	public void run() {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		makeTestData();
		
		int num = 3; 
		
		
		while(true) {

			System.out.println("명령어를 입력해 주세요");
			String cmd = sc.nextLine().trim(); 
		
			System.out.println("입력된 명령어) "+ cmd);

		if(cmd.length()==0) {
			System.out.println("명령어가 입력되지 않았습니다.");
		continue;
		}
		
		
		if(cmd.equals("exit")) {
				break;
			}

		
		if(cmd.equals("member join")) {
			String LogDate = Util.getNowDateStr();
			
			String LogID = null;

			while(true) {
			System.out.println("로그인 아이디 ) ");
			LogID = sc.nextLine();

//			내가 짠 아이디 중복 코드. 중복 구분까지는 하는데 반복을 안함. 아래의 Private 메소드도 그렇고 어딘가 잘못 된듯.
//원인 찾음. private 메소드에서 ==로 비교해서 문제임. 문자열인데. equals를 안쓰고 == 써서.  
			Member foundMember = getByMemberID(LogID); 
			
			if(foundMember != null) {
				System.out.println("아이디는 존재 합니다."); 

				continue; 
			}
			break;
		}
			
			String LogPw = null;
			
			while(true) {
					System.out.println("로그인 비밀번호 ) ");
					LogPw = sc.nextLine();
					System.out.println("로그인 비밀번호 확인) ");
					String LogPwCheck = sc.nextLine();
					
					if(LogPw.equals(LogPwCheck) == false) {
						System.out.println("비밀번호가 맞지 않습니다.");
						continue;
					}
				 break;
				}	
				
				System.out.println("이름을 입력해 주세요)");
				String name = sc.nextLine();
			
				Member member = new Member(num, LogDate, LogID, LogPw, name);	

				members.add(member);
				
				System.out.printf("%s 회원님 환영합니다\n", name);
			
			
			
			
			
			}	 else if(cmd.equals("article write")) {
				num++;
				String regDate = Util.getNowDateStr();
				System.out.println("제목 ) ");
				String title = sc.nextLine();
				System.out.println("내용 ) ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, num, regDate);
				
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", num); 

				
			} else if (cmd.equals("article list")) {

				if (articles.size() == 0) {
					System.out.println("게시글이 없습니다");
					continue;
				}

				String searchKeyword = cmd.substring("article list".length()).trim();
				//substring이 뭐하는 애인지 잘 서치해서 이해하면 뒤가 다 이해됨. 
				List<Article> forPrintArticles = articles;
				
				if (searchKeyword.length() > 0) {
					forPrintArticles = new ArrayList<>();
					
					for (Article article : articles) {
						if (article.title.contains(searchKeyword)) {
							forPrintArticles.add(article);
						}
					}
					
					if (forPrintArticles.size() == 0) {
						System.out.println("검색결과가 없습니다");
						continue;
					}
				}
				
				System.out.println("번호	|	제목		|		날짜		|	조회수	");

				for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
					Article article = forPrintArticles.get(i);
					System.out.printf("%d	|	%s	|	%s	|	%d	\n", article.num, article.title, article.regDate,
							article.check);
				}	

			} else if(cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");//split이 String[](배열)이라서 그냥 String 넣으면 Type mismatch로 난리남.
				//?? ?? = cmdbits.get(2); 이게 틀린이유 : 일단 쟤는 배열이지 list가 아니야. 지금 객체해서 막 연결하고 저장하고 그거 불러오는거 아니라고.
				int id = Integer.parseInt(cmdBits[2]);//String 배열이었음 : 타입미스매치니까 수동 형변환 해야함. 이때 Integer.parseInt();를 쓰는 것임.

				Article foundArticle = circuit(id);
				
				//아래 if문이 없으면 null이 발생할수 있음. 왜냐면 null이어도 위로 올리는 행위를 안하니까.
				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue; 
				}
				foundArticle.check++;
				
				System.out.println("==게시글 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.num);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
				System.out.printf("조회수 : %d\n", foundArticle.check);

			}else if(cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = circuit(id);
				

				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue; 
				}
				
				articles.remove(foundArticle);
				
				} else if(cmd.startsWith("article modify ")) {
					
					String[] cmdBits = cmd.split(" ");
					int id = Integer.parseInt(cmdBits[2]);
	
					Article foundArticle = circuit(id);
					
					if(foundArticle == null) {
						System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
						continue; 
					}
					
					String regDate = Util.getNowDateStr();
					System.out.println("수정할 제목 ) ");
					String title = sc.nextLine();
					System.out.println("수정할 내용 ) ");
					String body = sc.nextLine();
					
					
					foundArticle.title = title;
					foundArticle.body = body;
					
					
					
					System.out.printf("%d번 게시글이 수정되었습니다.\n", id);
					
				
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
			
			
		}
		

		sc.close();
		
		System.out.println("==프로그램 종료==");
		}
	
	//메소드 만들면 기본형이 void지만 이거 수정 안하면 리턴이 없어서 위에서 0이되. 주의!! 매번 수정을 안하네.
	private Article circuit(int id) {
//		Article foundArticle = null;
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if(article.num == id) {
				return article; //id랑 article의 번호가 같으면 아티클 번호 리턴
			}
		}
		return null; //안 같으면 null을 해라고 그럼 메소드 불렀을때 null로 if문으로 판단할꺼고. if문이랑 적합하면 if문 실행할꺼고 아니면 밑에 내용 실행하겠지.
	}

	private Member getByMemberID(String LogID) {
		for(int i = 0; i < members.size(); i++) {
			Member member = members.get(i);
//			if(member.LogID==LogID) { 문제의 그 코드
//			if(LogID.equals(member.LogID)){ 아래랑 같은거 
			if(member.LogID.equals(LogID)) {
				return member; 
				}
		}
		return null; 
	}
	
	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		
		articles.add(new Article("test1", "test1",1, Util.getNowDateStr(),10));
		articles.add(new Article("test2", "test2",2, Util.getNowDateStr(),15));
		articles.add(new Article("test3", "test3",3, Util.getNowDateStr(),28));

		
	}	
	
}


