import java.util.Comparator;

/**
 * CustomerMeanValueComparator
 * This comparator compares customers based on their mean account value. That is the sum of all accounts / the number of accounts.
 *
 * @author ejbosia
 */
public class CustomerMeanValueComparator implements Comparator<Customer> {

    @Override
    public int compare(Customer c1, Customer c2) {

        double valueOne = c1.getNetWorth() / c1.getAccounts().size();
        double valueTwo = c1.getNetWorth() / c2.getAccounts().size();

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
