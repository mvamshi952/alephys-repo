package com.alephys.program;

import java.time.LocalDate;
import java.util.Scanner;

public class ExpenseTracker {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TransactionManager manager = new TransactionManager();

		while (true) {
			System.out.println("\nExpense Tracker Menu:");
			System.out.println("1. Add Income");
			System.out.println("2. Add Expense");
			System.out.println("3. View Monthly Summary");
			System.out.println("4. Load from file");
			System.out.println("5. Save to file");
			System.out.println("6. Exit");

			System.out.print("Choose an option: ");
			int option = scanner.nextInt();
			scanner.nextLine(); // consume newline

			switch (option) {
			case 1:
			case 2:
				String type = option == 1 ? "Income" : "Expense";
				System.out.print("Enter date (YYYY-MM-DD): ");
				LocalDate date = LocalDate.parse(scanner.nextLine());
				System.out.print("Enter category (e.g., Salary, Rent, Travel): ");
				String category = scanner.nextLine();
				System.out.print("Enter amount: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				manager.addTransaction(new Transaction(date, type, category, amount));
				System.out.println(type + " added successfully.");
				break;
			case 3:
				System.out.print("Enter year (e.g., 2025): ");
				int year = scanner.nextInt();
				System.out.print("Enter month (1-12): ");
				int month = scanner.nextInt();
				scanner.nextLine();
				manager.showMonthlySummary(year, month);
				break;
			case 4:
				System.out.print("Enter file path to load: ");
				String loadPath = scanner.nextLine();
				try {
					manager.loadFromFile(loadPath);
					System.out.println("Transactions loaded successfully.");
				} catch (Exception e) {
					System.out.println("Error loading file: " + e.getMessage());
				}
				break;
			case 5:
				System.out.print("Enter file path to save: ");
				String savePath = scanner.nextLine();
				try {
					manager.saveToFile(savePath);
					System.out.println("Transactions saved successfully.");
				} catch (Exception e) {
					System.out.println("Error saving file: " + e.getMessage());
				}
				break;
			case 6:
				System.out.println("Exiting... Thank you!");
				System.exit(0);
				break;
			default:
				System.out.println("Invalid option.");
			}
		}
	}
}
