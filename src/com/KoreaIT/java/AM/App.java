package com.KoreaIT.java.AM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.KoreaIT.java.AM.dto.Article;
import com.KoreaIT.java.AM.util.Util;

public class App {
	List<Article> articles;
	
	public App() {
		articles = new ArrayList<>();
	} // 테스트용 데이터는 ArrayList에 미리 들어가서 프로그램 생성될 때 만들어져 있어야하는데 그걸 위해서 밑에 있는 ArrayList를 데리고 오는건 안좋은 방식.
	//그리고 여기에 static 안붙이고 만들면 main 메소드는 static이므로 main에서 활용을 하지 않음. 그러니 일반생성자에서 static필드를 초기화 하는 방법인 static 블럭을 쓰는 것. 
	
	
	
	public void run() {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		
		//강사님이 test용 데이터 메소드 만든 위치
		makeTestData();
		
		int num = 3; //테스트 데이터 때매 3인것임. 원래 이친구는 숫자가 증가되야해서 초기값 0이 들어감. 간단하게 확인하려면 이게 젤 속편함. 
//		List<Article> articles = new ArrayList<>();//저장할 꺼 여기 들어가야함. 리스트 사용.*리스트 사용하는거 잘 익히자!
// test용 데이터가 생기면서는 여기에 이게 있으면 안됨. 왜냐. 테스트용 데이터는 main메소드 밖에 있는데 이게 여기 있으면 테스트용 데이터가 저장이 안되잖아. 
// test용 데이터랑 main메소드 안이랑 둘다 저장하려고 위에보면 main메소드 밖에 뺀거고.
// 위와 같은 이유로 test용 데이터 만들고도 여기에 list가 있으면 main 메소드 실행하면서 초기화 되서 테스트 데이터 없어짐 날라감. article list 아무것도 없어!!		
		
		//내가 궁금한 위치. 여다가 만들면 컴터의 입장이 궁금. 컴터... 니입장이 여자보다 어려워
//		makeTestData();
		
		while(true) {

			System.out.println("명령어를 입력해 주세요");
			String cmd = sc.nextLine().trim();// trim = 공백 제거. 
		
			System.out.println("입력된 명령어) "+ cmd);

		if(cmd.length()==0) {
			System.out.println("명령어가 입력되지 않았습니다.");
		continue;
		}
		
		
		if(cmd.equals("exit")) {
				break;
			}
			
			if(cmd.equals("article write")) {
				num++;
				String regDate = Util.getNowDateStr();
				System.out.println("제목 ) ");
				String title = sc.nextLine();
				System.out.println("내용 ) ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, num, regDate);
				
				articles.add(article);
				
				System.out.printf("%d번 글이 생성되었습니다.\n", num); 

				
/* 여기서부터는 리스트 검색어 내 생각.
  				} else if(cmd.startsWith("article list ")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;	
				}
				
				if(articles.size() != 0) {
					String[] cmdBits = cmd.split(" ");
					int id = Integer.parseInt(cmdBits[2]);
					
					if(id != articles.num) {
				System.out.println("번호	|	제목	|	날짜	|	조회수"); 
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	|	%s	|	%s	|	%d\n ", article.num, article.title, article.regDate, article.check );
				}
				if(id == articles.num) {
					System.out.println("번호	|	제목	|	날짜	|	조회수"); 
					for(int i = articles.size()-1; i >= 0; i--) {
						Article article = articles.get(i);
						System.out.printf("%d	|	%s	|	%s	|	%d\n ", article.num, article.title, article.regDate, article.check );
					}
				}
					}
				}
<내 생각이 잘못된 이유>
1. article list 뒤에 띄어쓰기를 했는데 명령어를 받을때 trim(공백제거)을 했기 때문에 article list만 쳤을때 존재하지 않는 명령어입니다로 넘어감. 
=> 기존의 list도 안나온다는 뜻. 저 상태에서 기존의 list기능이 실행 되려면 article list 뒤에 공백하고 뭔가를 결국에는 적어야 기존 list 기능을 함.
여기서 부터 오류가 나니 split을 써서 2번째 방(배열)에 있는걸 불러와서 비교하는게 다 불가하게 됨.
*/				

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
/*	수업 중 나온 발상은 좋으나 위험한 내용 기재. 내가 나중에 이 발상하는건 상관없는데 일할때 적용하면 큰일남.		
detail 할때 다른 학생이 제안한 방법
내가 입력한 번호 (spilt으로 잘라서 배열 2번에 넣고 비교하는 대상)과 articles.size를 비교해서 조건문으로 게시물이 있는지 없는지를 보여주는 것을 제안.
=>좋은 발상이나 위험이 있음
=> 위험성 : article라고 하는 애한테 list를 이용해서 데이터를 넣고 있음. 문제는 삭제기능을 만들었을때. 삭제를 하게 되면 배열 번호와 입력한 번호가 안일치하게 됨. 매우 위험해짐.
=> 프로그램이 운영되다가 에러가 나서 멈출 가능성이 발생함.	
*/			 }else if(cmd.startsWith("article delete ")) {
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
					
//					Article article1 = new Article(title, body, num, regDate);
//					articles.add(article1);
//					이거 아니야. 이거 하면 수정 안되고 삽입되. 것도 희안하게. EX)2번글을 수정하겠다고 하고 수정내용을 쳤는데 수정은 안되고 번호 3번이 하나 더생기고 제목 내용이 있는 경우를 볼께다.
					
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

	private void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		
		articles.add(new Article("test1", "test1",1, Util.getNowDateStr(),10));
		articles.add(new Article("test2", "test2",2, Util.getNowDateStr(),15));
		articles.add(new Article("test3", "test3",3, Util.getNowDateStr(),28));

		
	}	
	
}


