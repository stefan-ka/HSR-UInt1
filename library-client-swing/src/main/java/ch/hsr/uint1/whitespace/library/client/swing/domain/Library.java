package ch.hsr.uint1.whitespace.library.client.swing.domain;

import java.util.List;
import java.util.Observable;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.data.CrudListener;
import ch.hsr.uint1.whitespace.library.client.swing.data.LibraryData;
import ch.hsr.uint1.whitespace.library.client.swing.data.MessageData;

@Component
public class Library extends Observable {

	private LibraryData libraryData;

	@Autowired
	public Library(LibraryData libraryData) {
		this.libraryData = libraryData;
		registerListeners();
	}

	public boolean canLent(Gadget gadget, Customer customer) {
		List<Reservation> reservations = getReservationFor(gadget, true);
		return getLoansFor(gadget, true).size() == 0 && (reservations.size() == 0 || reservations.get(0).getCustomerId().equals(customer.getStudentNumber()));
	}

	public void addLoan(Gadget gadget, Customer customer) {
		if (canLent(gadget, customer)) {
			libraryData.addLoan(new Loan(customer, gadget));
			Reservation reservation = getReservationFor(gadget, customer, true);
			if (reservation != null) {
				reservation.setFinished(true);
				libraryData.updateReservation(reservation);
			}
		}
	}

	public List<Loan> getLoansFor(Gadget gadget, boolean onlyLent) {
		if (!onlyLent) {
			return libraryData.getLoans().stream().filter(p -> p.getGadgetId().equals(gadget.getInventoryNumber())).collect(Collectors.toList());
		} else {
			return libraryData.getLoans().stream().filter(p -> p.isLent() && p.getGadgetId().equals(gadget.getInventoryNumber())).collect(Collectors.toList());
		}
	}

	public List<Loan> getLoansFor(Customer customer, boolean onlyLent) {
		if (!onlyLent) {
			return libraryData.getLoans().stream().filter(p -> p.getCustomerId().equals(customer.getStudentNumber())).collect(Collectors.toList());
		} else {
			return libraryData.getLoans().stream().filter(p -> p.isLent() && p.getCustomerId().equals(customer.getStudentNumber())).collect(Collectors.toList());
		}
	}

	public boolean hasOverdue(Customer customer) {
		return getLoansFor(customer, true).stream().anyMatch(p -> p.isOverdue());
	}

	public List<Customer> getCustomers() {
		return libraryData.getCustomers();
	}

	public List<Gadget> getGadgets() {
		return libraryData.getGadgets();
	}

	public Gadget getGadget(String id) {
		return libraryData.getGadgets().stream().filter(p -> p.getInventoryNumber().equals(id)).findAny().orElse(null);
	}

	public Gadget getGadgetByName(String name) {
		return libraryData.getGadgets().stream().filter(p -> p.getName().equals(name)).findAny().orElse(null);
	}

	public Customer getCustomer(String id) {
		return libraryData.getCustomers().stream().filter(p -> p.getStudentNumber().equals(id)).findAny().orElse(null);
	}

	public void addGadget(Gadget gadget) {
		libraryData.addGadget(gadget);
	}

	public void updateGadget(Gadget gadget) {
		libraryData.updateGadget(gadget);
	}

	public void updateLoan(Loan loan) {
		libraryData.updateLoan(loan);
	}

	public void updateReservation(Reservation reservation) {
		libraryData.updateReservation(reservation);
	}

	public Reservation getReservationFor(Gadget gadget, Customer customer, boolean onlyOpen) {

		if (gadget == null || customer == null) {
			return null;
		}
		if (!onlyOpen) {
			return libraryData.getReservations().stream().filter(p -> p.getGadgetId().equals(gadget.getInventoryNumber()) && p.getCustomerId().equals(customer.getStudentNumber()))
					.sorted((e1, e2) -> e1.getReservationDate().compareTo(e2.getReservationDate())).findAny().orElse(null);
		} else {
			return libraryData.getReservations().stream()
					.filter(p -> !p.getFinished() && (p.getGadgetId().equals(gadget.getInventoryNumber()) && p.getCustomerId().equals(customer.getStudentNumber())))
					.sorted((e1, e2) -> e1.getReservationDate().compareTo(e2.getReservationDate())).findAny().orElse(null);
		}

	}

	public List<Reservation> getReservationFor(Gadget gadget, boolean onlyOpen) {

		if (!onlyOpen) {
			return libraryData.getReservations().stream().filter(p -> p.getGadgetId().equals(gadget.getInventoryNumber()))
					.sorted((e1, e2) -> e1.getReservationDate().compareTo(e2.getReservationDate())).collect(Collectors.toList());
		} else {
			return libraryData.getReservations().stream().filter(p -> !p.getFinished() && p.getGadgetId().equals(gadget.getInventoryNumber()))
					.sorted((e1, e2) -> e1.getReservationDate().compareTo(e2.getReservationDate())).collect(Collectors.toList());
		}

	}

	public List<Reservation> getReservationFor(Customer customer, boolean onlyOpen) {

		if (!onlyOpen) {
			return libraryData.getReservations().stream().filter(p -> p.getCustomerId().equals(customer.getStudentNumber())).collect(Collectors.toList());
		} else {
			return libraryData.getReservations().stream().filter(p -> !p.getFinished() && p.getCustomerId().equals(customer.getStudentNumber())).collect(Collectors.toList());
		}

	}

	public void addReservation(Gadget gadget, Customer customer) {
		if (canReservation(gadget, customer)) {
			libraryData.addReservation(new Reservation(customer, gadget));
		}
	}

	public boolean canReservation(Gadget gadget, Customer customer) {
		return getReservationFor(gadget, customer, true) == null;
	}

	private void dataChanged(MessageData message) {
		setChanged();
		notifyObservers(message);
	}

	private void registerListeners() {
		libraryData.registerGadgetListener(new CrudListener<Gadget>() {
			@Override
			public void changed(MessageData message) {
				dataChanged(message);
			}
		});
		libraryData.registerReservationListener(new CrudListener<Reservation>() {
			@Override
			public void changed(MessageData message) {
				dataChanged(message);
			}
		});
		libraryData.registerLoanListener(new CrudListener<Loan>() {
			@Override
			public void changed(MessageData message) {
				dataChanged(message);
			}
		});
	}
}
