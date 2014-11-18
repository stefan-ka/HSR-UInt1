package ch.hsr.uint1.whitespace.library.client.swing.domain;

import java.util.UUID;

import ch.hsr.uint1.whitespace.library.client.swing.data.Dto;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.LocaleChangedListener;

public class Gadget implements Dto<Gadget>, LocaleChangedListener {

	public enum Condition {
		// NEW("Neu"), GOOD("Gut"), DAMAGED("Beschädigt"), WASTE("Schlecht"),
		// LOST("Verloren");
		NEW(ApplicationMessages.getText("gadget.zustand.neu")), GOOD(ApplicationMessages.getText("gadget.zustand.gut")), DAMAGED(ApplicationMessages
				.getText("gadget.zustand.beschaedigt")), WASTE(ApplicationMessages.getText("gadget.zustand.schlecht")), LOST(ApplicationMessages.getText("gadget.zustand.verloren"));

		private final String text;

		private Condition(String text) {
			this.text = text;
		}

		@Override
		public String toString() {
			return text;
		}
	}

	private final String inventoryNumber;
	private Condition condition;
	private double price;
	private String manufacturer;
	private String name;

	public Gadget() {
		this("");
	}

	public Gadget(String name) {
		this.name = name;
		inventoryNumber = "" + Math.abs(UUID.randomUUID().getMostSignificantBits());
		condition = Condition.NEW;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public String getInventoryNumber() {
		return inventoryNumber;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setData(Gadget gadget) {
		this.setCondition(gadget.getCondition());
		this.setManufacturer(gadget.getManufacturer());
		this.setName(gadget.getName());
		this.setPrice(gadget.getPrice());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((inventoryNumber == null) ? 0 : inventoryNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gadget other = (Gadget) obj;
		if (inventoryNumber == null) {
			if (other.inventoryNumber != null)
				return false;
		} else if (!inventoryNumber.equals(other.inventoryNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public void localeChanged() {

	}
}
