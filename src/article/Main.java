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
				System.out.println("제목 ) ");
				String title = sc.nextLine();
				System.out.println("내용 ) ");
				String body = sc.nextLine();
				
				Article article = new Article(title, body, num);
				
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
				
				System.out.println("번호	|	제목	"); //여기 있으면 상단에 한번만 나오고 반복 안됨.
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);//-1을 여기다가 줘도 큰 상관은 없지만 그러면 i가 특정 조건을 물고 있는게 아니므로 그 조건을 문 변수를 쓸때 활용에 제약이 생김. 활용할때마다 일일이 i-1을 넣느니 이게 낫지.
				//	System.out.println("번호	|	제목	"); 여기에 있으면 글들 사이에  번호랑 제목이 계속 나옴.
					System.out.printf("%d	|	%S	\n ", article.num, article.title );
				}

			 } else if(cmd.startsWith("article detail ")) {
				
				String[] cmdBits = cmd.split(" ");//split이 String[](배열)이라서 그냥 String 넣으면 Type mismatch로 난리남.
				//?? ?? = cmdbits.get(2); 이게 틀린이유 : 일단 쟤는 배열이지 list가 아니야. 지금 객체해서 막 연결하고 저장하고 그거 불러오는거 아니라고.
				int id = Integer.parseInt(cmdBits[2]);//String 배열이었음 : 타입미스매치니까 수동 형변환 해야함. 이때 Integer.parseInt();를 쓰는 것임.

				for(int i = articles.size()-1; i >= 0; i--) { //역시 같은거 찾기만 하면 되는거라 역순회든 정순회든 상관없구만.
				
					//for(int i =0; i < articles.size(); i++) {
						Article article = articles.get(i);
					
						if(article.num == id) {
							System.out.println("==게시글 상세보기 ==");
							System.out.printf("번호 : %d\n", article.num);
							System.out.printf("제목 : %s\n", article.title);
							System.out.printf("내용 : %s\n", article.body);
						} 	
			
				}	
				
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
	
	public Article(String title, String body, int num) {
		this.num = num;
		this.title = title;
		this.body = body;
	}
	
	
}
