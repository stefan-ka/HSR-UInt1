package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.domain.validation.GadgetValidator;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.MessageResolver;

@Component
public class GadgetDetail extends JFrame {

	private static final long serialVersionUID = -8347490944438461491L;

	private JPanel contentPane;
	private JPanel detailPanel;
	private JTextField nameTextField;
	private JTextField herstellerTextField;
	private JTextField preisTextField;
	private JLabel idLbl;
	private JLabel idNummerLbl;
	private JLabel nameLbl;
	private JLabel herstellerLbl;
	private JLabel preisLbl;
	private JLabel zustandLbl;
	private JComboBox<Gadget.Condition> zustandComboBox;
	private JButton abbruchBtn;
	private JButton saveBtn;

	@Autowired
	private Library library;

	@Autowired
	private GadgetValidator gadgetValidator;

	private MessageResolver messageResolver;

	private Gadget gadget;
	private boolean isNewGadget;

	@Autowired
	public GadgetDetail(MessageResolver messageResolver) {
		this.messageResolver = messageResolver;
		initializeGUI();
	}
	
	public void startGUI(Gadget gadget, boolean isNewGadget) {
		this.gadget = gadget;
		this.isNewGadget = isNewGadget;
		initializeGUI();
		updateView(gadget);
		setVisible(true);
	}

	private void initializeGUI() {
		if (isNewGadget) {
			setTitle(messageResolver.getText("gadgetDetail.newTitle"));
		} else {
			setTitle(messageResolver.getText("gadgetDetail.changeTitle"));
		}
		setResizable(false);
		setMinimumSize(new Dimension(445, 238));
		setSize(new Dimension(445, 238));
		setBounds(100, 100, 445, 238);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		final GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		detailPanel = new JPanel();
		final GridBagConstraints gbc_detailPanel = new GridBagConstraints();
		gbc_detailPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_detailPanel.gridx = 0;
		gbc_detailPanel.gridy = 0;
		contentPane.add(detailPanel, gbc_detailPanel);
		final GridBagLayout gbl_detailPanel = new GridBagLayout();
		gbl_detailPanel.columnWidths = new int[] { 0, 32, 0, 0, 0 };
		gbl_detailPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_detailPanel.columnWeights = new double[] { 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_detailPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		detailPanel.setLayout(gbl_detailPanel);

		idLbl = new JLabel(messageResolver.getText("gadgetDetail.idLabel"));
		final GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(5, 5, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 0;
		detailPanel.add(idLbl, gbc_lblId);

		idNummerLbl = new JLabel("");
		idNummerLbl.setMinimumSize(new Dimension(14, 28));
		idNummerLbl.setSize(new Dimension(15, 28));
		final GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel.insets = new Insets(5, 3, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		detailPanel.add(idNummerLbl, gbc_lblNewLabel);

		nameLbl = new JLabel(messageResolver.getText("gadgetDetail.nameLabel"));
		final GridBagConstraints gbc_nameLbl = new GridBagConstraints();
		gbc_nameLbl.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameLbl.insets = new Insets(5, 5, 5, 5);
		gbc_nameLbl.gridx = 0;
		gbc_nameLbl.gridy = 1;
		detailPanel.add(nameLbl, gbc_nameLbl);

		nameTextField = new JTextField();
		final GridBagConstraints gbc_nameTextField = new GridBagConstraints();
		gbc_nameTextField.gridwidth = 3;
		gbc_nameTextField.insets = new Insets(5, 0, 5, 5);
		gbc_nameTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameTextField.gridx = 1;
		gbc_nameTextField.gridy = 1;
		detailPanel.add(nameTextField, gbc_nameTextField);
		nameTextField.setColumns(10);

		herstellerLbl = new JLabel(messageResolver.getText("gadgetDetail.producerNameLabel"));
		final GridBagConstraints gbc_herstellerLbl = new GridBagConstraints();
		gbc_herstellerLbl.anchor = GridBagConstraints.WEST;
		gbc_herstellerLbl.insets = new Insets(5, 5, 5, 5);
		gbc_herstellerLbl.gridx = 0;
		gbc_herstellerLbl.gridy = 2;
		detailPanel.add(herstellerLbl, gbc_herstellerLbl);

		herstellerTextField = new JTextField();
		final GridBagConstraints gbc_herstellerTextField = new GridBagConstraints();
		gbc_herstellerTextField.gridwidth = 3;
		gbc_herstellerTextField.insets = new Insets(5, 0, 5, 5);
		gbc_herstellerTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_herstellerTextField.gridx = 1;
		gbc_herstellerTextField.gridy = 2;
		detailPanel.add(herstellerTextField, gbc_herstellerTextField);
		herstellerTextField.setColumns(10);

		preisLbl = new JLabel(messageResolver.getText("gadgetDetail.priceLabel"));
		final GridBagConstraints gbc_preisLbl = new GridBagConstraints();
		gbc_preisLbl.anchor = GridBagConstraints.WEST;
		gbc_preisLbl.insets = new Insets(5, 5, 5, 5);
		gbc_preisLbl.gridx = 0;
		gbc_preisLbl.gridy = 3;
		detailPanel.add(preisLbl, gbc_preisLbl);

		preisTextField = new JTextField();
		final GridBagConstraints gbc_preisTextField = new GridBagConstraints();
		gbc_preisTextField.gridwidth = 3;
		gbc_preisTextField.insets = new Insets(5, 0, 5, 5);
		gbc_preisTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_preisTextField.gridx = 1;
		gbc_preisTextField.gridy = 3;
		detailPanel.add(preisTextField, gbc_preisTextField);
		preisTextField.setColumns(10);

		zustandLbl = new JLabel(messageResolver.getText("gadgetDetail.conditionLabel"));
		final GridBagConstraints gbc_zustandLbl = new GridBagConstraints();
		gbc_zustandLbl.anchor = GridBagConstraints.WEST;
		gbc_zustandLbl.insets = new Insets(5, 5, 5, 5);
		gbc_zustandLbl.gridx = 0;
		gbc_zustandLbl.gridy = 4;
		detailPanel.add(zustandLbl, gbc_zustandLbl);

		zustandComboBox = new JComboBox<Gadget.Condition>();
		zustandComboBox.setModel(new DefaultComboBoxModel<Gadget.Condition>(Gadget.Condition.values()));
		final GridBagConstraints gbc_zustandComboBox = new GridBagConstraints();
		gbc_zustandComboBox.gridwidth = 3;
		gbc_zustandComboBox.insets = new Insets(5, 0, 5, 5);
		gbc_zustandComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_zustandComboBox.gridx = 1;
		gbc_zustandComboBox.gridy = 4;
		detailPanel.add(zustandComboBox, gbc_zustandComboBox);

		abbruchBtn = new JButton(messageResolver.getText("gadgetDetail.cancelButton"));
		abbruchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		final GridBagConstraints gbc_abbruchBtn = new GridBagConstraints();
		gbc_abbruchBtn.anchor = GridBagConstraints.NORTH;
		gbc_abbruchBtn.gridx = 2;
		gbc_abbruchBtn.gridy = 5;
		detailPanel.add(abbruchBtn, gbc_abbruchBtn);

		saveBtn = new JButton(messageResolver.getText("gadgetDetail.createNewButton"));
		if (!isNewGadget) {
			saveBtn.setText(messageResolver.getText("gadgetDetail.saveChangeButton"));
		}
		saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveGadget();
			}
		});
		final GridBagConstraints gbc_erfassenBtn = new GridBagConstraints();
		gbc_erfassenBtn.insets = new Insets(0, 0, 0, 5);
		gbc_erfassenBtn.anchor = GridBagConstraints.NORTH;
		gbc_erfassenBtn.gridx = 3;
		gbc_erfassenBtn.gridy = 5;
		detailPanel.add(saveBtn, gbc_erfassenBtn);
	}

	private void updateView(Gadget gadget) {
		idNummerLbl.setText(gadget.getInventoryNumber());
		nameTextField.setText(gadget.getName());
		herstellerTextField.setText(gadget.getManufacturer());
		preisTextField.setText(gadget.getPrice() + "");
		zustandComboBox.setSelectedItem(gadget.getCondition());
	}

	private void updateModel() {
		Gadget gadget = new Gadget("");
		gadget.setName(nameTextField.getText());
		gadget.setManufacturer(herstellerTextField.getText());
		gadget.setPrice(new Double(preisTextField.getText()));
		gadget.setCondition(zustandComboBox.getItemAt(zustandComboBox.getSelectedIndex()));
		this.gadget.setData(gadget);
	}

	private void saveGadget() {
		updateModel();
		if (!isGadgetValid())
			return;

		if (isNewGadget) {
			library.addGadget(gadget);
		} else {
			library.updateGadget(gadget);
		}
		closeWindow();
	}

	// TODO: show error's on GUI ...
	private boolean isGadgetValid() {
		BeanPropertyBindingResult result = new BeanPropertyBindingResult(gadget, "gadget");
		ValidationUtils.invokeValidator(gadgetValidator, gadget, result);

		for (FieldError error : result.getFieldErrors()) {
			System.out.println(messageResolver.getErrorMessage(error));
		}
		return !result.hasErrors();
	}

	private void closeWindow() {
		setVisible(false);
		dispose();
	}
}
