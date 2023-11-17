package bank;

public class AccountService {
	
	AccountRepository repo = new AccountRepository();
	
	/**
	 * 모든 계좌 정보를 반환한다.
	 * @return 계좌정보 배열
	 */
	public Account[] getAllAccounts() {
		Account[] accounts = repo.findAll();
		return accounts;
	}
	
	 /**
	  * 지정된 계좌번호에 해당하는 계좌정보를 반환한다
	  * @param accNo 조회할 계좌번호 
	  * @return 계좌번호, null 값인 경우도 있다.
	  */
	public Account getAccount(String accNo) {
		Account account = repo.findAccountByAccNo(accNo);
		return account;	
	}
	
	/**
	 * 신규 계좌정보를 전달받아서 계좌를 개설한다. 단, 동일한 계좌번호로 계좌를 개설할 수 없다.
	 * @param account 신규 계좌정보
	 * @return 계좌개설이 완료되면 true 를 반환한다.
	 */
	public boolean creatAccount(Account account) {
		Account savedAccount = repo.findAccountByAccNo(account.accNo);
		if (savedAccount != null) {
			System.out.println("### 이미 사용중인 계좌번호 입니다.");
			return false;
		}
		
		repo.save(account);
		return true;
	}
	/**
	 * 지정된 계좌번호에 입금액만큼 입금시킨다. 단, 계좌번호에 해당하는 계좌정보가 없으면 입금할 수 없다.
	 * @param accNo 계좌번호
	 * @param amount 입금액
	 * @return 입금완료서 true 를 반환한다.
	 */
	public boolean deposit(String accNo, long amount) {
		Account savedAccount = repo.findAccountByAccNo(accNo);
		if (savedAccount == null) {
			System.out.println("### 입금 계좌정보가 존재하지 않습니다.");
			return false;
		}
		savedAccount.balance += amount;
		return true;

		}

	/**
	 * 지정된 계좌의 비밀번호를 변경한다.
	 * 단, 이전비밀번호와 새 비밀번호가 일치하면 실패다.
	 * 단, 계좌번호에 해당하는 계좌정보가 존재하지 않으면 실패다.
	 * 단, 계좌비밀번호와 이전 비밀번호가 일치하지 않으면 실패다.
	 * @param accNo 계좌번호 
	 * @param oldpwd 이전 비밀번호 
	 * @param newpwd 새 비밀번호 
	 * @return 비밀번호 변경이 완료되면 true를 반환한다.
	 */
	public boolean changePassword(String accNo, int oldpwd, int newpwd) {
		if (oldpwd == newpwd) {
			System.out.println("### 이전 비밀번호와 같은 비밀번호로 변경할 수 없습니다.");
			return false;
		}
		
		Account savedAccount = repo.findAccountByAccNo(accNo);
		if (savedAccount == null) {
			System.out.println("### 계좌번호가 올바르지 않습니다.");
			return false;
		}
		if (savedAccount.password != oldpwd) {
			System.out.println("### 비밀번호가 올바르지 않습니다.");
			return false;
		}
		savedAccount.password = newpwd;
		return true;
	}

	/**
	 * 지정된 계좌정보를 폐지한다.
	 * 단, 계좌번호에 해당하는 계좌정보가 존재하지 않으면 실패다.
	 * 단, 비밀번호가 일치하지 않으면 실패다.
	 * @param accNo 계좌번호
	 * @param password 비밀번호
	 * @return 계좌정보 폐기가 완료되면 true를 반환한다.
	 */
	public boolean expire(String accNo, int password) {
		Account savedAccount = repo.findAccountByAccNo(accNo);
		if (savedAccount == null) {
			System.out.println("### 계좌정보가 올바르지 않습니다.");
			return false;
		}
		
		if (savedAccount.password != password) {
		System.out.println("### 비밀번호가 일치하지  않습니다.");
		return false;
		}
		
		repo.remove(accNo);
		return true;
	}
}

