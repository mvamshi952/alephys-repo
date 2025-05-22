package com.alephys.program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class TransactionManager {
	private List<Transaction> transactions = new ArrayList<>();

	public void addTransaction(Transaction tx) {
		transactions.add(tx);
	}

	public void loadFromFile(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line;
		while ((line = reader.readLine()) != null) {
			Transaction tx = Transaction.fromString(line);
			transactions.add(tx);
		}
		reader.close();
	}

	public void saveToFile(String filePath) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		for (Transaction tx : transactions) {
			writer.write(tx.toString());
			writer.newLine();
		}
		writer.close();
	}

	public void showMonthlySummary(int year, int month) {
		double income = 0;
		double expense = 0;
		System.out.println("\n--- Monthly Summary for " + Month.of(month) + " " + year + " ---");
		for (Transaction tx : transactions) {
			if (tx.getDate().getYear() == year && tx.getDate().getMonthValue() == month) {
				if (tx.getType().equalsIgnoreCase("Income")) {
					income += tx.getAmount();
				} else {
					expense += tx.getAmount();
				}
				System.out.println(tx);
			}
		}
		System.out.println("Total Income: " + income);
		System.out.println("Total Expense: " + expense);
		System.out.println("Net Savings: " + (income - expense));
	}
}
