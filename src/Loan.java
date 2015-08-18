import java.util.LinkedHashMap;
import java.util.Map;


public class Loan {

	private String name;
	
	private float interest;
	
	private float dealerDiscount;
	
	private float originationFees;
	
	private int numberOfMonths;

	private float principal;
	
	private Map<Integer, Float> reserveMap = new LinkedHashMap<Integer, Float>();
	
	public Loan(float interest, int numberOfMonths) {
		this(interest, numberOfMonths, 0);
	}
	
	public Loan(float interest, int numberOfMonths, int originationFees) {
		this(interest, numberOfMonths, originationFees, 0);
	}

	public Loan(float interest, int numberOfMonths, int originationFees,
			float dealerDiscount) {
		this.interest = interest;
		this.numberOfMonths = numberOfMonths;
		this.originationFees = originationFees;
		this.dealerDiscount = dealerDiscount;
		if(numberOfMonths == 1) {
			name = "CASH";
		} else {
			name = String.valueOf(interest)
					+ "% "
					+ String.valueOf(numberOfMonths)
					+ " months "
					+ (dealerDiscount == 0 ? "" : " Discount ("
							+ dealerDiscount + ")");
		}
	}

	public float getInterest() {
		return interest;
	}

	public void setInterest(float interest) {
		this.interest = interest;
	}

	public float getDealerDiscount() {
		return dealerDiscount;
	}

	public void setDealerDiscount(float dealerDiscount) {
		this.dealerDiscount = dealerDiscount;
	}

	public float getOriginationFees() {
		return originationFees;
	}

	public void setOriginationFees(float originationFees) {
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

	public void setPrincipal(float principal) {
		this.principal = principal;
	}

	public double getPayment() {
		if(getRate() == 0) {
			return getPrincipal()/getNumberOfMonths();
		}
		return getPrincipal()/((1-(1/Math.pow(1+getRate(),getNumberOfMonths())))/getRate()) ;
	}

	private float getRate() {
		return getInterest()/1200;
	}

	private float getPrincipal() {
		// TODO Auto-generated method stub
		return principal;
	}

	public void prettyPrintLoan() {
		double totalpayment = getPayment() * getNumberOfMonths();
		double totalInterest = totalpayment - getPrincipal();
		System.out.format("\n%s",getName());
		System.out.format("\n\tMonthly Payment: $%.2f", getPayment());
		System.out.format("\n\tTotal Interest $%.2f",totalInterest);
		System.out.format("\n\tTotal Amount: $%.2f",totalpayment);
	}

	public void addReserve(int i, float reserve) {
		reserveMap.put(i,  reserve);
	}

	public Map<Integer, Float> getReserveMap() {
		return reserveMap;
	}
	
}
