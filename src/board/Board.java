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

	private String type;
	private String log;
	private int sel;
	private int totalCount;
	private int curPage;

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
		System.out.println("[1] 회원가입");
		System.out.println("[2] 회원탈퇴");
		System.out.println("[3] 회원탈퇴");
		System.out.println("[4] 회원탈퇴");
		System.out.println("[5] 게시글 메뉴");
		System.out.println("[6] 마이페이지");
		System.out.println("[0] 종료");
	}

	private boolean checkLog() {
		if (this.log.equals(""))
			return false;
		else
			return true;
	}

	private void choice(int sel) {
		if (sel == JOIN && !checkLog())
			join();
		else if (sel == UNREGISTER && checkLog())
			unRegister();
		else if (sel == LOG_IN && !checkLog())
			logIn();
		else if (sel == LOG_OUT)
			logOut();
		else if (sel == BOARD_MENU)
			enterBoardMenu();
		else if (sel == MY_PAGE)
			myPage();
		else if (sel == END)
			System.out.println("시스템 종료");
		else
			System.err.println("없는 기능입니다.");
	}

	private void join() {

	}

	private void unRegister() {

	}

	private void logIn() {

	}

	private void logOut() {

	}

	private void myPage() {

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
		if (boardSel == CREATE_POST && checkLog())
			creatWriting();
		else if (boardSel == DELETE_POST && checkLog())
			deleteWriting();
		else if (boardSel == INQUIRY_POST)
			inquiry();
		else if (boardSel == MODIFY_POST && checkLog())
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
