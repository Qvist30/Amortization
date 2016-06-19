import java.util.LinkedHashMap;
import java.util.Map;

public class Loan {

	private String name;

	private double interest;

	private double dealerDiscount;

	private double originationFees;

	private int numberOfMonths;

	private double principal;

	private double downPayment;

	private Map<Integer, Double> reserveMap = new LinkedHashMap<Integer, Double>();

	public Loan(double interest, int numberOfMonths) {
		this(interest, numberOfMonths, 0);
	}

	public Loan(double interest, int numberOfMonths, double downPayment) {
		this(interest, numberOfMonths, downPayment, 0);
	}

	public Loan(double interest, int numberOfMonths, double downPayment,
			int originationFees) {
		this(interest, numberOfMonths, downPayment, originationFees, 0);
	}

	public Loan(double interest, int numberOfMonths, double downPayment,
			int originationFees, double dealerDiscount) {
		this.interest = interest;
		this.numberOfMonths = numberOfMonths;
		this.originationFees = originationFees;
		this.dealerDiscount = dealerDiscount;
		this.downPayment = downPayment;
		if (numberOfMonths == 0) {
			name = "CASH";
		} else {
			name = String.valueOf(interest)
					+ "% "
					+ String.valueOf(numberOfMonths)
					+ " months " + (downPayment == 0 ? "" : String.format("$%,.2f",downPayment) + " Down")
					+ (dealerDiscount == 0 ? "" : " Discount ("
							+ dealerDiscount + ")");
		}
	}

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getDealerDiscount() {
		return dealerDiscount;
	}

	public void setDealerDiscount(double dealerDiscount) {
		this.dealerDiscount = dealerDiscount;
	}

	public double getOriginationFees() {
		return originationFees;
	}

	public void setOriginationFees(double originationFees) {
		this.originationFees = originationFees;
	}

	public int getNumberOfMonths() {
		return numberOfMonths;
	}

	public void setNumberOfMonths(int numberOfMonths) {
		this.numberOfMonths = numberOfMonths;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrincipal(double principal) {
		this.principal = principal;
	}

	public double getPayment() {
		if (numberOfMonths == 0) {
			return 0;
		}
		if (getRate() == 0) {
			return getPrincipal() / getNumberOfMonths();
		}
		// return (getPrincipal() *
		// getRate())/(1-Math.pow(1+getRate(),-getNumberOfMonths()));

		return getPrincipal()
				* (getRate() * Math.pow((1 + getRate()), getNumberOfMonths()))
				/ (Math.pow((1 + getRate()), getNumberOfMonths()) - 1);
	}

	private double getRate() {
		return getInterest() / 1200;
	}

	private double getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}

	public void prettyPrintLoan() {
		double totalpayment = getPayment() * getNumberOfMonths()
				+ getDownPayment();
		double totalInterest = totalpayment - getPrincipal() - getDownPayment();
		System.out.format("\n%s", getName());
		System.out.format("\n\tMonthly Payment: $%,.2f", getPayment());
		System.out.format("\n\tTotal Interest $%,.2f", totalInterest);
		System.out.format("\n\tTotal Amount: $%,.2f", totalpayment);
	}

	public void addReserve(int i, double reserve) {
		reserveMap.put(i, reserve);
	}

	public Map<Integer, Double> getReserveMap() {
		return reserveMap;
	}

	public double getDownPayment() {
		return downPayment;
	}

}
