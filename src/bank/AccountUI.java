package bank;

public class AccountUI {
	
	// 메뉴 표시, 사용자와 상호작용
	AccountService service = new AccountService();
	Scanner scanner = new Scanner(System.in);
	
	public void menu() {
		System.out.println("---------------------------------------");
		System.out.println("1. 전체조회 2. 상세조회 3.신규 4. 입금 5. 출금");
		System.out.println("6. 비밀번호 7. 해지 0. 종료");
		System.out.println("---------------------------------------");
		
		System.out.print("### 메뉴선택: ");
		int menuNo = scanner.nextInt();
		
		if (menuNo == 1) {
			전체조회();
		} else if (menuNo == 2) {
			상세조회();
		} else if (menuNo == 3) {
			신규();
		} else if (menuNo == 4) {
			입금();
		} else if (menuNo == 5) {
			출금();
		} else if (menuNo == 6) {
			비밀번호변경();
		} else if (menuNo == 7) {
			해지();
		} else if (menuNo == 0) {
			System.out.println("### 프로그램을 종료합니다.");
			System.exit(0);
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		menu();
	}
	
	/*
	 * 전체 계좌정보를 출력한다.
	 */
	public void 전체조회() {
		System.out.println("<<전체 계좌 정보 조회>>");
		
		Account[] accounts = service.getAllAccounts();
		System.out.println("---------------------------------------");
		System.out.println("계좌번호\t예금주\t현재 잔액");
		System.out.println("---------------------------------------");
		for (Account acc : accounts) {
			acc.display();
		}
		System.out.println("---------------------------------------");
	}
	
	/*
	 * 계좌번호를 입력받아서 해당 계좌번호에 대한 상세정보를 출력한다.
	 */
	public void 상세조회() {
		System.out.println("<<계좌 상세 정보 조회>>");
		
		System.out.print("### 조회할 계좌번호: ");
		String accNo = scanner.nextString();
		
		Account account = service.getAccount(accNo);
		if (account != null) {
			account.displayDetail();
		} else { 
			System.out.println("### 계좌정보가 존재하지 않습니다.");
		}
	}
	
	/*
	 * 계좌번호, 예금주명, 비밀번호, 최초 입금액을 입력받아서 신규 계좌를 개설한다.
	 */
	public void 신규() {
		System.out.println("<<신규 계좌 개설>>");
		
		System.out.println("### 신규 계좌 개설에 필요한 정보를 입력하세요.");
		
		System.out.print("### 계좌번호 입력: ");
		String accNo = scanner.nextString();
		System.out.print("### 예금주명 입력: ");
		String owner = scanner.nextString();
		System.out.print("### 비밀번호 입력: ");
		int password = scanner.nextInt();
		System.out.print("### 최초금액 입력: ");
		long amount = scanner.nextLong();
		
		Account account = new Account(accNo, owner,password, amount);
		boolean succeed = service.creatAccount(account);
		if (succeed) {
			System.out.println("### 신규 계좌개설이 완료되었습니다.");
		} else {
			System.out.println("#### 신규 계좌개설이 실패하였습니다.");
		}
	}
	
	/*
	 * 계좌번호, 입금액을 입력받아서 해당 계좌에 입금한다.
	 */
	public void 입금() {
		System.out.println("<<입금 하기>>");
		
		System.out.println("### 계좌번호와 입금액을 입력하세요.");
		
		System.out.print("### 계좌번호 입력: ");
		String accNo = scanner.nextString();
		
		System.out.print("### 입금액 입력: ");
		long amount = scanner.nextLong();
		
		boolean succeed = service.deposit(accNo, amount);
		
		if (succeed) {
			System.out.println("### 입금이 완료되었습니다.");
		} else {
			System.out.println("### 입금처리 중 오류가 발생하였습니다");
		}
			
	}
	
	/*
	 * 계좌번호, 비밀번호, 출금액을 입력받아서 해당 계좌에서 출금액만큼 출금한다.
	 */
	public void 출금() {
		
	}
	
	/*
	 * 계좌번호, 이전 비밀번호, 새 비밀번호를 입력받아서 해당 계좌의 비밀번호를 변경한다.
	 */
	public void 비밀번호변경() {
		System.out.println("<<계좌비밀번호 변경>>");
		
		System.out.println("### 비밀번호 변경에 필요한 계좌번호. 이전비밀번호, 새비밀번호를 입력하세요.");
		
		System.out.print("### 계좌번호 입력: ");
		String accNo = scanner.nextString();
		System.out.print("### 이전 비밀번호 입력: ");
		int oldpwd = scanner.nextInt();
		System.out.print("### 새 비밀번호  입력: ");
		int newpwd = scanner.nextInt();
		
		boolean succeed = service.changePassword(accNo, oldpwd, newpwd);
		if (succeed) {
			System.out.println("### 비밀번호 변경이 완료되었습니다.");
		} else {
			 System.out.println("### 비밀번호 변경 중 오류가 발생하였습니다.");
		}
	}
	
	/*
	 * 계좌번호, 비밀번호를 입력받아서 해당 계좌를 해지시킨다.
	 */
	public void 해지() {
		System.out.println("<< 계좌 해지 >>");
		
		System.out.println("###계좌번호, 비밀번호를 입력하세요.");
		
		System.out.print("###계좌번호 입력: ");
		String accNo = scanner.nextString();
		System.out.print("###비밀번호 입력: ");
		int password = scanner.nextInt();
		
		boolean succeed = service.expire(accNo,password);
		if (succeed) {
			System.out.println("### 해지처리 되었습니다.");
		} else {
			System.out.println("### 해지처리 중 오류가 발생하였습니다.");
		}
	}

}
