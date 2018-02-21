import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InvestmentDecisionMaker {

	private static Loan[] accounts = new Loan[] {
			// new Loan(0,1),
			// new Loan(0.9d,63),

			// interest
			// down payment
			// origination fees
			// # of months
			// amount saved per month
			// principal
			// car_reserve = money you have now
			// new Loan(4.0d,320,20000, 5000),
			// new Loan(3.75d,160,20000, 5000),
			// new Loan(4.0d,320,40000, 5000),
			// new Loan(3.75d,160,40000, 5000),
			// new Loan(3.125d,240),
			// new Loan(3.25d,360),
			// new Loan(2.5d,180),
			new Loan(0, 360)

			// new Loan(3.375d,240),
			// new Loan(3.0d, 163),
			// new Loan(2.99d, 60, 0, 0, 750),
			// new Loan(3.99d, 72, 0, 0, 750
			// )
	};

	private static final double STARTING_VALUE = 50_000;

	private static final int STARTING_AGE = 66;

	public static void main(String[] args) {
		int maxMonths = 0;
		for (Loan loan : accounts) {
			loan.setPrincipal(0);
			if (loan.getNumberOfMonths() > maxMonths) {
				maxMonths = loan.getNumberOfMonths();
			}
			// loan.prettyPrintLoan();
		}
		DecimalFormat format = new DecimalFormat("#,###.00");
		System.out.println("\n");

		StringBuilder header = new StringBuilder();

		List<Integer> interestRates = new ArrayList<Integer>();
		interestRates.add(0);
		interestRates.add(1);
		interestRates.add(3);
		interestRates.add(5);
		interestRates.add(7);
		interestRates.add(9);
		header.append(String.format("%1$8s", ""));
		for (Integer interestRate : interestRates) {

			// System.out.println(String.format("%15s", "Return Rate: " +
			// interestRate + "%") + " " + bestOption + " with a final reserve
			// value of " + String.format("$%,.2f", bestReserve));
			header.append(String.format("%1$15s", interestRate + "%"));
		}
		System.out.println(header.toString() + "\n");

		double bestReserve = Double.MIN_VALUE;
		Map<Integer, StringBuilder> ageToValue = new TreeMap<Integer, StringBuilder>();
		for (Loan loan : accounts) {
			for (Integer interestRate : interestRates) {
				int age = STARTING_AGE;

				double reserve = STARTING_VALUE - loan.getDownPayment();
				for (int j = 0; j < maxMonths / 12; j++) {
					if (j < loan.getNumberOfMonths()) {
						reserve -= loan.getPayment();
						age++;
					}
					double interest = 0;
					if (reserve > 0) {
						interest = (reserve * ((double) interestRate / 100));
					}
					reserve = reserve + interest + 0;
					if (reserve < 0) {
						// loan.addReserve(i, reserve);
						break;
					}
					StringBuilder builder = null;
					if(ageToValue.containsKey(age)) {
						builder = ageToValue.get(age);
					} else {
						builder = new StringBuilder();
						builder.append(String.format("%1$8s",age));
						ageToValue.put(age, builder);
					}
					builder.append(String.format("%1$15s", "$" + format.format(reserve)));
				}
				if (reserve > bestReserve) {
					bestReserve = reserve;
				}
				
				loan.addReserve(interestRate, reserve);
			}
		}
		
		for(StringBuilder builder : ageToValue.values()) {
			System.out.println(builder.toString());
		}
	}

}
