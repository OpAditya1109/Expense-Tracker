import java.io.*;
import java.util.*;

class Expense implements Serializable {
    private Date date;
    private String category;
    private double amount;

    public Expense(Date date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    // Getters and setters

    public Date getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Date: " + date + ", Category: " + category + ", Amount: " + amount;
    }
}

class ExpenseTracker {
    private List<Expense> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void saveExpensesToFile(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(expenses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadExpensesFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            expenses = (List<Expense>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class ExpenseTrackerApp {
    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();

        // Sample expense data
        expenseTracker.addExpense(new Expense(new Date(), "Food", 50.0));
        expenseTracker.addExpense(new Expense(new Date(), "Transportation", 30.0));
        expenseTracker.addExpense(new Expense(new Date(), "Entertainment", 20.0));

        // Save expenses to file
        expenseTracker.saveExpensesToFile("expenses.dat");

        // Clear expenses list
        expenseTracker = new ExpenseTracker();

        // Load expenses from file
        expenseTracker.loadExpensesFromFile("expenses.dat");

        // Display expenses
        List<Expense> allExpenses = expenseTracker.getExpenses();
        for (Expense expense : allExpenses) {
            System.out.println(expense);
        }
    }
}
