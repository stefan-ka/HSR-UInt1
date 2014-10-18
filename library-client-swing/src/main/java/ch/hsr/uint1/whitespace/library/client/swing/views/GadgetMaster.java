package ch.hsr.uint1.whitespace.library.client.swing.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import ch.hsr.uint1.whitespace.library.client.swing.bl.Gadget;
import ch.hsr.uint1.whitespace.library.client.swing.bl.Library;

public class GadgetMaster extends JFrame {

    private static final long serialVersionUID = -2060969695124152513L;

    private JPanel biblioContentPane;
    private JTextField suchenTxtEditGadgetTab;
    private JTabbedPane biblioTabbedPane;
    private JPanel gadgetTab;
    private JPanel ausleihenTab;
    private JButton gadgetErfassenBtn;
    private JButton gadgetEditBtn;
    private JList<Gadget> gadgetsList;
    private final Library library;
    private JTextField suchenTxtEditAusleiheTab;
    private JTable ausleiheTabTable;
    private JScrollPane ausleiheTableScrollPane;

    /**
     * Create the frame.
     */
    public GadgetMaster(final Library library) {
        this.library = library;
        initializeGUI();
    }

    private void editGadget(final Gadget gadget, final boolean isNewGadget) {
        final GadgetDetail detailFrame = new GadgetDetail(library, gadget,
                isNewGadget);
        detailFrame.setVisible(true);
    }

    private void initializeGUI() {
        setName("Gadget Bibliothek");
        setMinimumSize(new Dimension(500, 230));
        setSize(new Dimension(730, 515));
        setTitle("Gadget Bibliothek");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 730, 513);
        biblioContentPane = new JPanel();
        biblioContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        setContentPane(biblioContentPane);
        final GridBagLayout gbl_biblioContentPane = new GridBagLayout();
        gbl_biblioContentPane.columnWidths = new int[] { 0, 0 };
        gbl_biblioContentPane.rowHeights = new int[] { 0, 0 };
        gbl_biblioContentPane.columnWeights = new double[] { 1.0,
                Double.MIN_VALUE };
        gbl_biblioContentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
        biblioContentPane.setLayout(gbl_biblioContentPane);

        biblioTabbedPane = new JTabbedPane(SwingConstants.TOP);
        biblioTabbedPane.setBorder(null);
        biblioTabbedPane.setPreferredSize(new Dimension(0, 0));
        final GridBagConstraints gbc_biblioTabbedPane = new GridBagConstraints();
        gbc_biblioTabbedPane.fill = GridBagConstraints.BOTH;
        gbc_biblioTabbedPane.gridx = 0;
        gbc_biblioTabbedPane.gridy = 0;
        biblioContentPane.add(biblioTabbedPane, gbc_biblioTabbedPane);

        gadgetTab = new JPanel();
        gadgetTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        biblioTabbedPane.addTab("Gadgets", null, gadgetTab, "Gadgets Liste");
        biblioTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
        final GridBagLayout gbl_gadgetTab = new GridBagLayout();
        gbl_gadgetTab.columnWidths = new int[] { 0, 0, 0 };
        gbl_gadgetTab.rowHeights = new int[] { 0, 0, 0 };
        gbl_gadgetTab.columnWeights = new double[] { 1.0, 1.0, 0.0 };
        gbl_gadgetTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        gadgetTab.setLayout(gbl_gadgetTab);

        ausleihenTab = new JPanel();
        ausleihenTab.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null,
                null));
        biblioTabbedPane.addTab("Ausleihen & Rückgabe", null, ausleihenTab,
                "Hier können Sie Ausleihen und Rückgaben erfassen");
        final GridBagLayout gbl_ausleihenTab = new GridBagLayout();
        gbl_ausleihenTab.columnWidths = new int[] { 355, 355, 0 };
        gbl_ausleihenTab.rowHeights = new int[] { 0, 0, 0 };
        gbl_ausleihenTab.columnWeights = new double[] { 1.0, 1.0,
                Double.MIN_VALUE };
        gbl_ausleihenTab.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
        ausleihenTab.setLayout(gbl_ausleihenTab);

        suchenTxtEditAusleiheTab = new JTextField();
        suchenTxtEditAusleiheTab.setToolTipText("Suchen");
        suchenTxtEditAusleiheTab.setText("Suchen...");
        suchenTxtEditAusleiheTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                suchenTxtEditGadgetTab.setText("");
            }
        });

        final GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.insets = new Insets(2, 2, 5, 5);
        gbc_textField.gridx = 0;
        gbc_textField.gridy = 0;
        ausleihenTab.add(suchenTxtEditAusleiheTab, gbc_textField);
        suchenTxtEditAusleiheTab.setColumns(10);
        ausleiheTabTable = new JTable();
        ausleiheTabTable.setBorder(null);
        ausleiheTabTable.setFillsViewportHeight(true);
        final GridBagConstraints gbc_table = new GridBagConstraints();
        gbc_table.insets = new Insets(0, 5, 2, 5);
        gbc_table.fill = GridBagConstraints.BOTH;
        gbc_table.gridx = 0;
        gbc_table.gridy = 1;
        ausleihenTab.add(ausleiheTabTable, gbc_table);

        ausleiheTableScrollPane = new JScrollPane(ausleiheTabTable);
        ausleiheTableScrollPane.setViewportBorder(null);
        final GridBagConstraints gbc_ausleiheTableScrollPane = new GridBagConstraints();
        gbc_ausleiheTableScrollPane.insets = new Insets(0, 5, 2, 5);
        gbc_ausleiheTableScrollPane.fill = GridBagConstraints.BOTH;
        gbc_ausleiheTableScrollPane.gridx = 0;
        gbc_ausleiheTableScrollPane.gridy = 1;
        ausleihenTab.add(ausleiheTableScrollPane, gbc_ausleiheTableScrollPane);
        biblioTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);

        suchenTxtEditGadgetTab = new JTextField();
        suchenTxtEditGadgetTab.setToolTipText("Suchen");
        suchenTxtEditGadgetTab.setText("Suchen...");
        suchenTxtEditGadgetTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(final FocusEvent e) {
                suchenTxtEditGadgetTab.setText("");
            }
        });
        final GridBagConstraints gbc_suchenTxtEditGadgetTab = new GridBagConstraints();
        gbc_suchenTxtEditGadgetTab.fill = GridBagConstraints.HORIZONTAL;
        gbc_suchenTxtEditGadgetTab.gridwidth = 13;
        gbc_suchenTxtEditGadgetTab.insets = new Insets(2, 2, 5, 5);
        gbc_suchenTxtEditGadgetTab.gridx = 0;
        gbc_suchenTxtEditGadgetTab.gridy = 0;
        gadgetTab.add(suchenTxtEditGadgetTab, gbc_suchenTxtEditGadgetTab);
        suchenTxtEditGadgetTab.setColumns(10);

        gadgetErfassenBtn = new JButton("Gadget erfassen");
        gadgetErfassenBtn
                .setToolTipText("Clicken Sie hier, um einen Gadget zu erfassen");
        gadgetErfassenBtn.addActionListener(e -> editGadget(new Gadget(""),
                true));
        final GridBagConstraints gbc_gadgetErfassenBtn = new GridBagConstraints();
        gbc_gadgetErfassenBtn.fill = GridBagConstraints.HORIZONTAL;
        gbc_gadgetErfassenBtn.insets = new Insets(2, 0, 5, 0);
        gbc_gadgetErfassenBtn.gridx = 13;
        gbc_gadgetErfassenBtn.gridy = 0;
        gadgetTab.add(gadgetErfassenBtn, gbc_gadgetErfassenBtn);

        gadgetEditBtn = new JButton("Gadget editieren");
        gadgetEditBtn
                .setToolTipText("Clicken Sie hier, um einen Gadget zu editieren");
        gadgetEditBtn.setMinimumSize(new Dimension(145, 29));
        gadgetEditBtn.setMaximumSize(new Dimension(145, 29));
        gadgetEditBtn.setName("Gadget editieren");
        gadgetEditBtn.addActionListener(e -> {
            final Gadget gadgetSelected = gadgetsList.getSelectedValue();
            if (gadgetSelected != null) {
                editGadget(gadgetSelected, false);
            }
        });
        final GridBagConstraints gbc_gadgetEditBtn = new GridBagConstraints();
        gbc_gadgetEditBtn.insets = new Insets(2, 0, 5, 0);
        gbc_gadgetEditBtn.fill = GridBagConstraints.BOTH;
        gbc_gadgetEditBtn.gridx = 14;
        gbc_gadgetEditBtn.gridy = 0;
        gadgetTab.add(gadgetEditBtn, gbc_gadgetEditBtn);

        gadgetsList = new JList<Gadget>();
        gadgetsList.setModel(new GadgetListModel(library));
        final GridBagConstraints gbc_list = new GridBagConstraints();
        gbc_list.gridwidth = 15;
        gbc_list.insets = new Insets(0, 5, 2, 5);
        gbc_list.fill = GridBagConstraints.BOTH;
        gbc_list.gridx = 0;
        gbc_list.gridy = 1;
        gadgetTab.add(gadgetsList, gbc_list);
    }

}
