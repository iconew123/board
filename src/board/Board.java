package board;

import java.util.Scanner;

public class Board {
	private Scanner scan = new Scanner(System.in);

	private UserManager userManager = UserManager.getInstance();
	private WritingManager writingManager = WritingManager.getInstance();

	private final int JOIN = 1;
	private final int UNREGISTER = 2;
	private final int LOG_IN = 3;
	private final int LOG_OUT = 4;
	private final int BOARD_MENU = 5;
	private final int MY_PAGE = 6;
	private final int END = 0;

	private final int NEXT = 1;
	private final int PREVIOUS = 2;
	private final int CREATE_POST = 3;
	private final int DELETE_POST = 4;
	private final int INQUIRY_POST = 5;
	private final int MODIFY_POST = 6;

	private final int TYPE_IN = 1;
	private final int TYPE_OUT = 2;

	private final int CHANGE_PASSWORD = 1;
	private final int MY_WRITING = 2;

	private final int FIX_TITLE = 1;
	private final int FIX_CONTENT = 2;

	private final int PAGE_SIZE = 5;
	private String type;
	private int sel;
	private int totalCount;
	private int pageCount;
	private int curPage;
	private int startNum;
	private int endNum;

	private User curUser;

	public Board(String type) {
		this.type = type;
		this.sel = -1;
		this.totalCount = 1;
		this.curPage = 1;
		calEndPage();

	}

	private boolean isRun() {
		return this.sel == 0 ? false : true;
	}

	private int inputNumber(String text) {
		int number = -1;
		System.out.print(text);

		try {
			String input = scan.next();
			number = Integer.parseInt(input);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("숫자만 입력가능");
		}

		return number;
	}

	private String inputString(String text) {
		System.out.print(text);
		return scan.next();
	}

	private void printMenu() {
		System.out.println(curUser == null ? "로그아웃상태" : String.format(curUser.getName() + "님 로그인 중..."));
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 로그인");
		System.out.println("[4] 로그아웃");
		System.out.println("[5] 게시글 메뉴");
		System.out.println("[6] 마이페이지");
		System.out.println("[0] 종료");
	}

	private boolean checkLog(int logState) {
		if (!(curUser == null) && logState == TYPE_OUT) {
			System.err.println("로그아웃 후 이용해주세요");
			return false;
		} else if (curUser == null && logState == TYPE_IN) {
			System.err.println("로그인 후 이용해주세요");
			return false;
		}

		return true;
	}

	private void choice(int sel) {
		if (sel == JOIN && checkLog(TYPE_OUT))
			join();
		else if (sel == UNREGISTER && checkLog(TYPE_IN))
			unRegister();
		else if (sel == LOG_IN && checkLog(TYPE_OUT))
			logIn();
		else if (sel == LOG_OUT)
			logOut();
		else if (sel == BOARD_MENU)
			enterBoardMenu();
		else if (sel == MY_PAGE && checkLog(TYPE_IN))
			myPage();
		else if (sel == END)
			System.out.println("시스템 종료");

	}

	private void join() {
		String name = inputString("회원 이름 입력 : ");
		String id = inputString("회원 아이디 입력 : ");
		String pw = inputString("회원 비밀번호 입력 입력 : ");

		User tmpUser = new User(id, pw, name);

		if (userManager.create(tmpUser))
			System.out.println("회원가입 성공");
		else
			System.err.println("회원가입 실패");
	}

	private void unRegister() {
		String pw = inputString("탈퇴 할 아이디 비밀번호 입력 : ");

		if (curUser.getPassword().equals(pw)) {
			userManager.delete(curUser.getId());
			logOut();
			System.out.println("회원 탈퇴 완료");
		} else
			System.err.println("비밀번호가 일치하지 않습니다.");

	}

	private void logIn() {
		String id = inputString("ID : ");
		String pw = inputString("PW : ");

		if (userManager.checkLogIn(id, pw)) {
			curUser = showUser(id);
			System.out.println("로그인 성공");
		} else
			System.err.println("로그인 실패");

	}

	private void logOut() {
		curUser = null;
	}

	private User showUser(String id) {
		User tmpUser = userManager.read(id);
		return tmpUser;
	}

	private void myPage() {
		myPageMenu();

		int mySel = inputNumber(">> ");

		if (mySel == CHANGE_PASSWORD)
			changePw();
		else if (mySel == MY_WRITING)
			myWriting();
		else
			System.err.println("없는 기능입니다.");
	}

	private void myPageMenu() {
		System.out.println("[1] 비밀번호 변경");
		System.out.println("[2] 내가 쓴 글");
	}

	private void changePw() {
		String pw = inputString("변경 할 비밀번호 입력");
		curUser.setPassWord(pw);
		userManager.update(curUser);
//		글에대한 비밀번호도 변경
		for (int i = 1; i < totalCount; i++) {
			Writing tmp = writingManager.read(i + "");
			if (tmp.getId().equals(curUser.getId())) {
				tmp.setPassWord(pw);
				writingManager.update(tmp);
			}
		}

		System.out.println("비밀번호 변경완료");
	}

	private void myWriting() {
		System.out.printf("%s님의 글 목록 + 내용\n", curUser.getName());
		for (int i = 1; i <= totalCount; i++) {
			Writing tmp = writingManager.read(i + "");
			if (tmp.getId().equals(curUser.getId())) {
				System.out.println("====================================");
				if (tmp.getIsdeleted())
					System.err.println(tmp);
				else {
					System.out.println(tmp);
					System.out.println(tmp.getContent());
				}
				System.out.println("====================================");

			}

		}
	}

	private void enterBoardMenu() {
		while (true) {
			showBoard();
			System.out.println("[1] 다음 페이지");
			System.out.println("[2] 이전 페이지");
			System.out.println("[3] 게시글 작성");
			System.out.println("[4] 게시글 삭제");
			System.out.println("[5] 게시글 조회");
			System.out.println("[6] 게시글 수정");
			System.out.println("[0] 뒤로가기");

			int boardSel = inputNumber(">> ");

			if (boardSel == NEXT)
				curPage++;
			else if (boardSel == PREVIOUS)
				curPage--;
			else if (boardSel == CREATE_POST && checkLog(TYPE_IN))
				creatWriting();
			else if (boardSel == DELETE_POST && checkLog(TYPE_IN))
				deleteWriting();
			else if (boardSel == INQUIRY_POST)
				inquiry();
			else if (boardSel == MODIFY_POST && checkLog(TYPE_IN))
				modifyMenu();
			else if (boardSel == END) {
				System.out.println("초기메뉴로 이동합니다.");
				break;
			}
		}

	}

	private void showBoard() {
		writingManager.sort();
		calEndPage();
		System.out.printf("===================%s===================\n", this.type);
		for (int i = startNum; i <= endNum; i++) {
			Writing curWriting = writingManager.read(i + "");
			if (curWriting.getIsdeleted())
				System.err.println(curWriting);
			else
				System.out.println(curWriting);
		}
		System.out.println("==============================================");
		System.out.printf("[%d / %d]\n", curPage, pageCount + 1);
	}

	private void calEndPage() {
		pageCount = totalCount / PAGE_SIZE;
		if (totalCount % PAGE_SIZE == 0)
			pageCount--;
		if (totalCount == 0)
			pageCount++;

		if (curPage >= pageCount + 1)
			curPage = pageCount + 1;
		else if (curPage < 1)
			curPage = 1;

		startNum = (curPage - 1) * PAGE_SIZE + 1;
		endNum = startNum + PAGE_SIZE - 1;
		if (endNum > totalCount) {
			endNum = totalCount;
		}

	}

	private void creatWriting() {
		if (curUser.getCount() < 3) {
			System.err.println("글 조회수가 3이상인 경우에만 글을 작성할 수 있습니다.");
			System.out.println(curUser);
			return;
		}

		System.out.print("글 제목입력 : ");
		scan.nextLine();
		String title = scan.nextLine();
		String content = "";
		content += addContent(content);
		Writing tmpWrite = new Writing(curUser.getId(), curUser.getPassword(), title, content, ++totalCount);

		if (writingManager.create(tmpWrite))
			System.out.println("글 작성 완료");
		else
			System.err.println("글 작성 실패");
	}

	private String addContent(String content) {
		int n = 0;
		while (true) {
			String tmp = inputString(++n + "번째 내용 입력 (종료 : . 입력) : ");
			if (tmp.equals("."))
				break;
			content += tmp + "\n";
		}
		return content;
	}

	private void deleteWriting() {
		String delNumber = inputString("삭제할 게시글 번호 입력 : ");
		Writing curWriting = writingManager.read(delNumber);

		if (findError(curWriting))
			return;

		if (writingManager.delete(delNumber))
			System.out.println("글 삭제 완료");
		else
			System.err.println("글 삭제 실패");
	}

	private boolean findError(Writing curWriting) {

		if (!curWriting.getId().equals(curUser.getId())) {
			System.out.printf("해당 게시글은 %s사용자만 수정,삭제가 가능합니다.\n", curWriting.getId());
			return false;
		}

		String pw = inputString("Pw : ");
		if (!curWriting.getPassword().equals(pw)) {
			System.err.println("비밀번호가 일치하지 않습니다.");
			return false;
		}

		return true;
	}

	private void inquiry() {
		String inquiryNumber = inputString("조회 글 번호 입력 : ");
		Writing curWriting = writingManager.read(inquiryNumber);

		if (curWriting == null)
			return;

		if (curWriting.getIsdeleted()) {
			System.err.println("삭제된 글은 읽을 수 없습니다.");
			return;
		}

		int writingCount = curWriting.getCount() + 1;
		if (curUser != null) {
			int userCount = curUser.getCount() + 1;
			curUser.setCount(userCount);
			userManager.update(curUser);
		}
		curWriting.setCount(writingCount);
		writingManager.update(curWriting);
		System.out.println(curWriting.getContent());
	}

	private void modifyMenu() {
		String modifyNumber = inputString("수정할 게시글 번호 입력 : ");
		Writing curWriting = writingManager.read(modifyNumber);

		if (curWriting.getIsdeleted()) {
			System.err.println("삭제된 글은 읽을 수 없습니다.");
			return;
		}

		if (!findError(curWriting))
			return;

		showModifyMenu();
		int modifySel = inputNumber(">> ");

		if (modifySel == FIX_TITLE)
			modifyTitle(curWriting);
		else if (modifySel == FIX_CONTENT)
			modifyContent(curWriting);
		else
			System.err.println("없는 기능입니다.");
	}

	private void showModifyMenu() {
		System.out.println("[1] 글 제목 수정");
		System.out.println("[2] 글 내용 수정");
	}

	private void modifyTitle(Writing curWriting) {
		System.out.print("글 제목입력 : ");
		scan.nextLine();
		String changeTitle = scan.nextLine();
		curWriting.setTitle(changeTitle);
		writingManager.update(curWriting);
		System.out.println("수정완료");
	}

	private void modifyContent(Writing curWriting) {
		System.out.printf("%s의 내용 수정\n", curWriting.getTitle());
		String content = "";
		content += addContent(content);
		curWriting.setContent(content);
		writingManager.update(curWriting);
		System.out.println("수정완료");
	}

	public void run() {
		while (isRun()) {
			printMenu();
			this.sel = inputNumber("메뉴 선택 : ");
			choice(this.sel);
		}
	}
}
