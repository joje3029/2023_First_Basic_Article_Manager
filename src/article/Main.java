package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("==프로그램 시작==");
		
		Scanner sc = new Scanner(System.in);
		int num=0;
		List<Article> articles = new ArrayList<>();//저장할 꺼 여기 들어가야함. 리스트 사용.*리스트 사용하는거 잘 익히자!
		
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
			
		//		continue; //이거 없으면 컴터 입장에서는 내가 원망스러울 것. if가 분리 되어있으니. 밑에 해야하잖아. else로 연결되어있으면 몰라. 
				//연결 안되어있으면 continue를 해서 쓸대 없는 연산을 안하게 하는게 컴터입장에서 좋은것임.
		//	}
			//여기서부터는 else if로 묶어버리는게 낫지만 지금 나의 레벨에서는 헷갈리므로. 일단 continue로 올리고 나중에 다 만들고 나면 수정을...
		} else if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;	
				}
				
				System.out.println("번호	|	제목	|	날짜	"); //여기 있으면 상단에 한번만 나오고 반복 안됨.
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);//-1을 여기다가 줘도 큰 상관은 없지만 그러면 i가 특정 조건을 물고 있는게 아니므로 그 조건을 문 변수를 쓸때 활용에 제약이 생김. 활용할때마다 일일이 i-1을 넣느니 이게 낫지.
				//	System.out.println("번호	|	제목	"); 여기에 있으면 글들 사이에  번호랑 제목이 계속 나옴.
					System.out.printf("%d	|	%s	|	%s\n ", article.num, article.title, article.regDate );
				}

			 } else if(cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");//split이 String[](배열)이라서 그냥 String 넣으면 Type mismatch로 난리남.
				//?? ?? = cmdbits.get(2); 이게 틀린이유 : 일단 쟤는 배열이지 list가 아니야. 지금 객체해서 막 연결하고 저장하고 그거 불러오는거 아니라고.
				int id = Integer.parseInt(cmdBits[2]);//String 배열이었음 : 타입미스매치니까 수동 형변환 해야함. 이때 Integer.parseInt();를 쓰는 것임.

/*				for(int i = articles.size()-1; i >= 0; i--) { //역시 같은거 찾기만 하면 되는거라 역순회든 정순회든 상관없구만.
					//for(int i =0; i < articles.size(); i++) {
						Article article = articles.get(i);
					
						if(article.num == id) {
							System.out.println("==게시글 상세보기 ==");
							System.out.printf("번호 : %d\n", article.num);
							System.out.printf("제목 : %s\n", article.title);
							System.out.printf("내용 : %s\n", article.body);
						break; //이거 없으면 컴터가 맞는걸 찾고도 뒤에 남은 애들을 순회해야하니까. 컴터는 내가 원망스럽겠지.
						} 	
//아래의 %d번 게시글은 존재하지 않습니다는 내가 스스로 생각해서 만든 코드. 자랑스럽다! 근데 이게 좋은 방법인걸까?
						if(articles.size()-1<id) {
						System.out.printf("%d번 게시글은 존재하지 않습니다\n", id);
						//이렇게 하면 %d번 게시글은 존재하지 않습니다 라고 뜸. 문제는 똑같은 글이 두줄 나와서 그렇지. 하하
						break; //break를 만드니까 한번만 나오네. 근데 왜 없으면 두번 나오는거지? 아. 찾고나서 또 순회해서구나. 
						//여기가 continue가 아니라 break를 쓴 이유 : for문 안이니까. continue 쓰면 for문 나가는게 아니라 또 순회하는건걸.
						}
*/						
				//강사님이 사용하신 코드. 이게 내가한것보다 효율적인 이유.
				//articles를 순회해서. 명령어에 입력된 글 번호값이랑 일치하는 글이 있는지 확인하는 작업을 detail에서만 하는거 아니니까.
/* 아래의 강사님이 사용하신 코드 까지 가는 중간 과정. 이해를 위해 적어둠.
 				boolean foundArticle = false;
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.num == id) {
						foundArticle =true;
						break;
					}
				}
				if(foundArticle == false) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue;
				}
				System.out.println("==게시글 상세보기 ==");
				System.out.printf("번호 : %d\n", article.num);
				System.out.printf("제목 : %s\n", article.title);
				System.out.printf("내용 : %s\n", article.body);
				이렇게 쓰면 article을 for문안에서 만들었으므로 게시글 상세보기는 for문 밖이기 때문에 사용할수 없다.
				그래서 아래와 같은 작업을 한다.
*/				
				Article foundArticle = null;
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.num == id) {
						foundArticle = article;
						break;
					}
				}
				//아래 if문이 없으면 null이 발생할수 있음. 왜냐면 null이어도 위로 올리는 행위를 안하니까.
				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue; 
				}
				System.out.println("==게시글 상세보기 ==");
				System.out.printf("번호 : %d\n", foundArticle.num);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
				System.out.printf("날짜 : %s\n", foundArticle.regDate);
/*	수업 중 나온 발상은 좋으나 위험한 내용 기재. 내가 나중에 이 발상하는건 상관없는데 일할때 적용하면 큰일남.		
detail 할때 다른 학생이 제안한 방법
내가 입력한 번호 (spilt으로 잘라서 배열 2번에 넣고 비교하는 대상)과 articles.size를 비교해서 조건문으로 게시물이 있는지 없는지를 보여주는 것을 제안.
=>좋은 발상이나 위험이 있음
=> 위험성 : article라고 하는 애한테 list를 이용해서 데이터를 넣고 있음. 문제는 삭제기능을 만들었을때. 삭제를 하게 되면 배열 번호와 입력한 번호가 안일치하게 됨. 매우 위험해짐.
=> 프로그램이 운영되다가 에러가 나서 멈출 가능성이 발생함.	
*/			 }else if(cmd.startsWith("article delete ")) {
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);
				
				Article foundArticle = null;
				
				for(int i = 0; i < articles.size(); i++) {
					Article article = articles.get(i);
					
					if(article.num == id) {
						foundArticle = article; //이건 없애면 안되! 지금 우리 밑에서 foundArticle에 null인지 아닌지로 판단한단다. 이걸 없애면 쟈는 뭘로 판단해.
//					list.remove(id); : 우선 list가 아니야!! 없앨것(remove시킬것)이 list에 있는애가 아니잖아. 
						System.out.printf("%d번 게시글이 삭제되었습니다.\n", id);
						
						break;
					}
				}
				if(foundArticle == null) {
					System.out.printf("%d번 게시글은 존재하지 않습니다.\n", id);
					continue; 
				}
				
				articles.remove(foundArticle);
				
			} else {
				System.out.println("존재하지 않는 명령어입니다.");
			}
			
			
		}
		

		sc.close();
		
		System.out.println("==프로그램 종료==");
	}	

}


class Article{
	int num;
	String title;
	String body;
	String regDate;
	
	public Article(String title, String body, int num, String regDate) {
		this.num = num;
		this.title = title;
		this.body = body;
		this.regDate = regDate;
	}
	
	
}
