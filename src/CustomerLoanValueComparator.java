import java.util.Comparator;

/**
 * CustomerLoanValueComparator
 * This comparator compares customers based on their total current loans
 *
 * @author ejbosia
 */
public class CustomerLoanValueComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer c1, Customer c2) {

        double valueOne = c1.getTotalLoans();
        double valueTwo = c1.getTotalLoans();

        if(valueOne > valueTwo){
            return 1;
        }
        else if(valueOne < valueTwo){
            return -1;
        }
        else{
            return 0;
        }
    }
}
