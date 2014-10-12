package ch.hsr.uint1.whitespace.library.client.swing.dl;

import java.util.List;

import ch.hsr.uint1.whitespace.library.client.swing.bl.Customer;
import ch.hsr.uint1.whitespace.library.client.swing.bl.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.bl.Loan;
import ch.hsr.uint1.whitespace.library.client.swing.bl.Reservation;

public abstract interface LibraryData {
	List<Customer> getCustomers();
	List<Gadget> getGadgets();
	List<Loan> getLoans();
	List<Reservation> getReservations();
	
	void addGadget(Gadget gadget);	
	void updateGadget(Gadget gadget);
	
	void addLoan(Loan loan);
	void updateLoan(Loan loan);
	
	void addReservation(Reservation reservation);
	void updateReservation(Reservation reservation);
	
	void registerReservationListener(CrudListener<Reservation> listener);
	void registerCustomerListener(CrudListener<Customer> listener);
	void registerGadgetListener(CrudListener<Gadget> listener);
	void registerLoanListener(CrudListener<Loan> listener);
}
