import java.util.Map;


public class LoanDecisionMaker {
	
	private static Loan[] loans = new Loan[] {
//	    new Loan(0,1),
//	    new Loan(0.9d,63),
			
			// interest
			// down payment
			// origination fees
			// # of months
			// amount saved per month
			// principal
			// car_reserve = money you have now
//	    new Loan(4.0d,320,20000, 5000),
//	    new Loan(3.75d,160,20000, 5000),
//	    new Loan(4.0d,320,40000, 5000),
//	    new Loan(3.75d,160,40000, 5000),
//	            new Loan(3.125d,240),
//	            new Loan(3.25d,360),
//	            new Loan(2.5d,180),
			new Loan(0,240,166900,902),
			new Loan(0,240,83450,1599),
			new Loan(0,240,0,2296)

//	    new Loan(3.375d,240),
//	    new Loan(3.0d, 163),
//	    new Loan(2.99d, 60, 0, 0, 750),
//	    new Loan(3.99d, 72, 0, 0, 750
//	        )
	    };
	
	private static final int MONTHLY_SAVINGS = 2_000;
	private static final double PRINCIPAL = 0;
	
	private static final double CAR_RESERVE = 500_000;
	
	public static void main(String[] args) {
		int maxMonths = 0;
		for(Loan loan : loans) {
			loan.setPrincipal(PRINCIPAL - loan.getDealerDiscount() + loan.getOriginationFees() - loan.getDownPayment());
			if(loan.getNumberOfMonths() > maxMonths) {
				maxMonths = loan.getNumberOfMonths();
			}
			loan.prettyPrintLoan();
		}
		System.out.println("\n");
		
		StringBuilder header = new StringBuilder();
		for(int i=-5;i<15;i++) {
			String bestOption ="";
			double bestReserve = Double.MIN_VALUE;
			for(Loan loan : loans) {
				double reserve = CAR_RESERVE - loan.getDownPayment();
				for(int j=0; j<maxMonths; j++) {
					if(j<loan.getNumberOfMonths()) {
						reserve -= loan.getPayment();
					}
					double interest = 0;
					if(reserve > 0) {
						interest = (reserve* ((double) i/1200));
					}
					reserve = reserve + interest + MONTHLY_SAVINGS;
					if(reserve < 0) {
//						loan.addReserve(i, reserve);
						break;
					}
		
				}
				if(reserve > bestReserve) {
					bestReserve= reserve;
					bestOption = loan.getName();
				}
				loan.addReserve(i, reserve);
			}
			System.out.println(String.format("%15s", "Return Rate: " + i + "%") + " " + bestOption + " with a final reserve value of " + String.format("$%,.2f", bestReserve));
			header.append("\t"+i+"%\t");
		}
		System.out.format("%40s", "");
		System.out.println(header.toString() + "\n");
		for(Loan loan : loans) {
			System.out.format("%40s",loan.getName());
			for(Map.Entry<Integer, Double> reserveEntry : loan.getReserveMap().entrySet()) {
				if(reserveEntry.getValue() < 0) {
					System.err.format(String.format("\t$%,.2f", reserveEntry.getValue()));
				} else {
					System.out.format("\t$%,.2f", reserveEntry.getValue());
				}
			}
			System.out.println("");
		}
	}
	

}
