package bank;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser
{
    private static final DateTimeFormatter DATE_PATTERN
        = DateTimeFormatter.ofPattern("dd-MM-yyy");

    private BankTransaction parseFromCSV(String line) {
        String[] columns = line.split(",");

        LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        double amount = Double.parseDouble(columns[1]);
        String description = columns[2];

        return new BankTransaction(date, amount, description);
    }

    public List<BankTransaction> parseLinesFromCSV(List<String> lines) {
        List<bank.BankTransaction> bankTransactions = new ArrayList<>();
        for(String line: lines) {
            bankTransactions.add(parseFromCSV(line));
        }
        return bankTransactions;
    }
}
