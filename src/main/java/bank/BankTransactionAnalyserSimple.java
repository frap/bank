package bank;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class BankTransactionAnalyserSimple {

    private static final String RESOURCES = "src/test/resources/";

    public static void main(String... args) throws Exception {
        BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

        Path path = Paths.get(RESOURCES + args[0]);
        List<String> lines = Files.readAllLines(path);

        List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFromCSV(lines);


        System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
        System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));



    }

    public static double calculateTotalAmount(List<BankTransaction> bankTransactions) {
        double totalAmount = 0d;
        for(BankTransaction bankTransaction: bankTransactions) {
            totalAmount += bankTransaction.getAmount();
        }
        return totalAmount;
    }

    public static List<BankTransaction> selectInMonth(List<BankTransaction> bankTransactions, Month month) {

        List<BankTransaction> bankTransactionsInMonth = new ArrayList();
        for(BankTransaction bankTransaction: bankTransactions) {
            if(bankTransaction.getDate().getMonth() == month) {
                bankTransactionsInMonth.add(bankTransaction);
            }
        }
        return bankTransactionsInMonth;
        }

}
