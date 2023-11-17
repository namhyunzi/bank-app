package bank;

/**
 * 은행계좌정보를 표현하는 클래스
 */
public class Account {
	
	String accNo;			// 계좌정보
	String owner;			// 예금주명
	int password;			// 비밀번호
	long balance;			// 잔액
	double interestRate;	// 이자율
	boolean expired;		// 폐기여부
	
	public Account(String accNo, String owner, int password, long amount) {
		this.accNo = accNo;
		this.owner = owner;
		this.password = password;
		this.balance = amount;
		this.interestRate = 0.05;
	}
	
	public void display() {
		System.out.println(accNo +"\t" + owner + "\t" +balance);
		
	}
	
	public void displayDetail() {
		System.out.println("###계좌상세 정보###");
		System.out.println("계좌번호: " + accNo);
		System.out.println("예금주명: " + owner);
		System.out.println("현재잔액: " + balance);
		System.out.println("적립이율: " + interestRate);
		System.out.println("폐기여부: " + expired);
		System.out.println("#################");
		System.out.println();
	}
}
