package com.bridglabz.bookmanagementsystem.services;

import java.util.List;
import java.util.Scanner;

import com.bridglabz.bookmanagementsystem.interfaces.ICustomerService;
import com.bridglabz.bookmanagementsystem.model.Customer;



public class CustomerServiceImpl implements ICustomerService {
	Scanner scanInput = new Scanner(System.in);
	int i = 0;

	@Override
	public Customer getCustomer(List<Customer> customers) {
		int i = 0;
		Customer customer = new Customer();
		do {

			System.out.println("enter customer id");
			int getId = scanInput.nextInt();
			customer.setId(getId);

			if (customers.stream().map(data -> data.getId()).anyMatch(data -> data == getId)) {
				System.out.print("customer allready present - ");

			} else {

				System.out.print("Enter customer name- ");
				String customerName = scanInput.nextLine();
				customer.setName(customerName);

				System.out.print("Enter customer address- ");
				String customerAddress = scanInput.nextLine();
				customer.setAddress(customerAddress);

				System.out.print("Enter customer mobile number- ");
				long mobileNumber = scanInput.nextLong();
				customer.setMobileNumber(mobileNumber);

			}

			System.out.print("want to repeat - ");
			i = scanInput.nextInt();

		} while (i == 1);

		return customer;
	}
}
