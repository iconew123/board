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

	private final int CREATE_POST = 1;
	private final int DELETE_POST = 2;
	private final int INQUIRY_POST = 3;
	private final int MODIFY_POST = 4;

	private final int TYPE_IN = 1;
	private final int TYPE_OUT = 2;

	private String type;
	private int sel;
	private int totalCount;
	private int curPage;

	private User curUser;

	public Board(String type) {
		this.type = type;
		this.sel = -1;
		this.totalCount = 0;
		this.curPage = 1;
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
		if (userManager.delete(curUser.getId(), pw))
			System.out.println("회원 탈퇴 완료");
		else
			System.err.println("회원 탈퇴 실패");
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

	private final int changePw = 1;
	private final int myWriting = 2;

	private void myPage() {
		myPageMenu();

		int mySel = inputNumber(">> ");

		if (mySel == changePw)
			changePw();
		else if (mySel == myWriting)
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
	}

	private void myWriting() {

	}

	private void enterBoardMenu() {
		while (true) {
			showBoard();
			System.out.println("[1] 게시글 작성");
			System.out.println("[2] 게시글 삭제");
			System.out.println("[4] 게시글 조회");
			System.out.println("[4] 게시글 수정");
			System.out.println("[0] 뒤로가기");

			int boardSel = inputNumber(">> ");
			if (!boardChoice(boardSel))
				break;
		}
	}

	private void showBoard() {
		// 여기서 게시글들이 5개단위로 보임
	}

	private boolean boardChoice(int boardSel) {
		if (boardSel == CREATE_POST && checkLog(TYPE_IN))
			creatWriting();
		else if (boardSel == DELETE_POST && checkLog(TYPE_IN))
			deleteWriting();
		else if (boardSel == INQUIRY_POST)
			inquiry();
		else if (boardSel == MODIFY_POST && checkLog(TYPE_IN))
			modifyMenu();
		else if (boardSel == END) {
			System.out.println("초기메뉴로 이동합니다.");
			return false;
		}

		return true;

	}

	private void creatWriting() {

	}

	private void deleteWriting() {

	}

	private void inquiry() {

	}

	private void modifyMenu() {

	}

	public void run() {
		while (isRun()) {
			printMenu();
			this.sel = inputNumber("메뉴 선택 : ");
			choice(this.sel);
		}
	}
}
