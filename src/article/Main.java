package article;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	static List<Article> articles;
	
	static {
		articles = new ArrayList<>();
	} // 테스트용 데이터는 ArrayList에 미리 들어가서 프로그램 생성될 때 만들어져 있어야하는데 그걸 위해서 밑에 있는 ArrayList를 데리고 오는건 안좋은 방식.
	//그리고 여기에 static 안붙이고 만들면 main 메소드는 static이므로 main에서 활용을 하지 않음. 그러니 일반생성자에서 static필드를 초기화 하는 방법인 static 블럭을 쓰는 것. 
	
	
	
	public static void main(String[] args) {
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
			
		//		continue; //이거 없으면 컴터 입장에서는 내가 원망스러울 것. if가 분리 되어있으니. 밑에 해야하잖아. else로 연결되어있으면 몰라. 
				//연결 안되어있으면 continue를 해서 쓸대 없는 연산을 안하게 하는게 컴터입장에서 좋은것임.
		//	}
			//여기서부터는 else if로 묶어버리는게 낫지만 지금 나의 레벨에서는 헷갈리므로. 일단 continue로 올리고 나중에 다 만들고 나면 수정을...
		} else if(cmd.equals("article list")) {
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;	
				}
				
				System.out.println("번호	|	제목	|	날짜	|	조회수"); //여기 있으면 상단에 한번만 나오고 반복 안됨.
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);//-1을 여기다가 줘도 큰 상관은 없지만 그러면 i가 특정 조건을 물고 있는게 아니므로 그 조건을 문 변수를 쓸때 활용에 제약이 생김. 활용할때마다 일일이 i-1을 넣느니 이게 낫지.
				//	System.out.println("번호	|	제목	"); 여기에 있으면 글들 사이에  번호랑 제목이 계속 나옴.
					System.out.printf("%d	|	%s	|	%s	|	%d\n ", article.num, article.title, article.regDate, article.check );
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
	private static Article circuit(int id) {
//		Article foundArticle = null;
		for(int i = 0; i < articles.size(); i++) {
			Article article = articles.get(i);
			if(article.num == id) {
				return article; //id랑 article의 번호가 같으면 아티클 번호 리턴
			}
		}
		return null; //안 같으면 null을 해라고 그럼 메소드 불렀을때 null로 if문으로 판단할꺼고. if문이랑 적합하면 if문 실행할꺼고 아니면 밑에 내용 실행하겠지.
	}

	private static void makeTestData() {
		System.out.println("테스트를 위한 데이터를 생성합니다");
		
		articles.add(new Article("test1", "test1",1, Util.getNowDateStr(),10));
		articles.add(new Article("test2", "test2",2, Util.getNowDateStr(),15));
		articles.add(new Article("test3", "test3",3, Util.getNowDateStr(),28));

		
	}	

	
	
	
}


class Article{
	int num;
	String title;
	String body;
	String regDate;
	int check;
	
	
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
