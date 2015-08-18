import java.util.Collections;
import java.util.Map;


public class LoanDecisionMaker {
	
	private static Loan[] loans = new Loan[] {
	    new Loan(0,1),
	    new Loan(0,36),
	    new Loan(0.9f,60),
	    new Loan(1.9f, 72),
	    new Loan(2.99f, 60, 0, 750),
	    new Loan(3.99f, 72, 0, 750)};
	
	private static final float PRINCIPAL = 22988;
	
	private static final float CAR_RESERVE = 36715.34f;
	
	public static void main(String[] args) {
		int maxMonths = 0;
		for(Loan loan : loans) {
			loan.setPrincipal(PRINCIPAL - loan.getDealerDiscount() + loan.getOriginationFees());
			if(loan.getNumberOfMonths() > maxMonths) {
				maxMonths = loan.getNumberOfMonths();
			}
			loan.prettyPrintLoan();
		}
		System.out.println("\n");
		System.out.format("%40s", "");
		for(int i=-5;i<15;i++) {
			for(Loan loan : loans) {
				float reserve = CAR_RESERVE;
				for(int j=0; j<maxMonths; j++) {
					if(j<loan.getNumberOfMonths()) {
						reserve -= loan.getPayment();
					}
					
					float interest = (reserve* ((float) i/1200));
					reserve += interest;
				}
				loan.addReserve(i, reserve);
			}
			System.out.format("%s", "\t"+i+"%\t");
		}
		System.out.println("\n");
		for(Loan loan : loans) {
			System.out.format("%40s",loan.getName());
			for(Map.Entry<Integer, Float> reserveEntry : loan.getReserveMap().entrySet()) {
				System.out.format("\t$%.2f", reserveEntry.getValue());
			}
			System.out.println("");
		}
	}
	

}
