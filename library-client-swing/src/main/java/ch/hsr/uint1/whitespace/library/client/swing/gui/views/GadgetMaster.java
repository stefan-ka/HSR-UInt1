package ch.hsr.uint1.whitespace.library.client.swing.gui.views;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ch.hsr.uint1.whitespace.library.client.swing.domain.Library;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.ApplicationMessages;
import ch.hsr.uint1.whitespace.library.client.swing.gui.i18n.LocaleChangedListener;

@Component
public class GadgetMaster extends JFrame implements LocaleChangedListener {

	private static final long serialVersionUID = -6748954870301059200L;

	private JPanel biblioContentPane;
	private JTabbedPane biblioTabbedPane;
	private GadgetTab gadgetTab;
	private AusleihenTab ausleihenTab;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem deutschMenuItem;
	private JMenuItem spanischMenuItem;
	private JMenuItem englischMenuItem;
	@Autowired
	private Library library;
	private SpringObjectFactory prototypeFactory;

	@Autowired
	public GadgetMaster(SpringObjectFactory prototypeFactory) {
		this.prototypeFactory = prototypeFactory;
		buildGUI();
	}

	private void buildGUI() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setName(ApplicationMessages.getText("master.title"));
		setSize(new Dimension(972, 577));
		setMinimumSize(new Dimension(750, 510));
		setTitle(ApplicationMessages.getText("master.title"));
		setBounds(100, 100, 972, 577);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.getAccessibleContext().setAccessibleDescription(ApplicationMessages.getText("master.menu.languages.description"));
		menu = new JMenu(ApplicationMessages.getText("master.menu.languages"));
		menu.setToolTipText(ApplicationMessages.getText("master.menu.languages.description"));
		menu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(menu);
		deutschMenuItem = new JMenuItem(ApplicationMessages.getText("master.menu.languages.deutsch"), new ImageIcon((new ImageIcon("images/germany_flag.gif")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		deutschMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationMessages.setCurrentLocale(Locale.GERMAN);
			}
		});
		deutschMenuItem.setMnemonic(KeyEvent.VK_D);
		deutschMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		menu.add(deutschMenuItem);
		spanischMenuItem = new JMenuItem(ApplicationMessages.getText("master.menu.languages.spanisch"), new ImageIcon((new ImageIcon("images/spain_flag.gif")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		spanischMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationMessages.setCurrentLocale(new Locale("es", "ES"));
			}
		});
		spanischMenuItem.setMnemonic(KeyEvent.VK_E);
		spanischMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
		menu.add(spanischMenuItem);
		englischMenuItem = new JMenuItem(ApplicationMessages.getText("master.menu.languages.englisch"), new ImageIcon((new ImageIcon("images/united_kingdom_flag.gif")).getImage()
				.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH)));
		englischMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationMessages.setCurrentLocale(Locale.ENGLISH);
			}
		});
		englischMenuItem.setMnemonic(KeyEvent.VK_I);
		englischMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menu.add(englischMenuItem);

		biblioContentPane = new JPanel();
		biblioContentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(biblioContentPane);
		GridBagLayout gbl_biblioContentPane = new GridBagLayout();
		gbl_biblioContentPane.columnWidths = new int[] { 0, 0 };
		gbl_biblioContentPane.rowHeights = new int[] { 0, 0 };
		gbl_biblioContentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
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

		gadgetTab = (GadgetTab) SpringObjectFactory.createObject(GadgetTab.class, prototypeFactory);
		ausleihenTab = (AusleihenTab) SpringObjectFactory.createObject(AusleihenTab.class, prototypeFactory);

		ApplicationMessages.addLocaleChangedListener(ausleihenTab);
		ApplicationMessages.addLocaleChangedListener(gadgetTab);
		
		biblioTabbedPane.addTab(ApplicationMessages.getText("master.tab.gadgets"), null, gadgetTab, ApplicationMessages.getText("master.tab.gadgets.tip"));
		biblioTabbedPane.addTab(ApplicationMessages.getText("master.tab.loans"), null, ausleihenTab, ApplicationMessages.getText("master.tab.loans.tip"));
		biblioTabbedPane.setMnemonicAt(0, KeyEvent.VK_G);
		biblioTabbedPane.setMnemonicAt(1, KeyEvent.VK_A);
	}

	public void showGUI() {
		this.setVisible(true);
	}

	@Override
	public void localeChanged() {
		menuBar.getAccessibleContext().setAccessibleDescription(ApplicationMessages.getText("master.menu.languages.description"));
		setName(ApplicationMessages.getText("master.title"));
		setTitle(ApplicationMessages.getText("master.title"));
		menu.setText(ApplicationMessages.getText("master.menu.languages"));
		menu.setToolTipText(ApplicationMessages.getText("master.menu.languages.description"));
		deutschMenuItem.setText(ApplicationMessages.getText("master.menu.languages.deutsch"));
		spanischMenuItem.setText(ApplicationMessages.getText("master.menu.languages.spanisch"));
		englischMenuItem.setText(ApplicationMessages.getText("master.menu.languages.englisch"));
		biblioTabbedPane.setTitleAt(0, ApplicationMessages.getText("master.tab.gadgets"));
		biblioTabbedPane.setTitleAt(1, ApplicationMessages.getText("master.tab.loans"));
		biblioTabbedPane.setToolTipTextAt(0, ApplicationMessages.getText("master.tab.gadgets.tip"));
		biblioTabbedPane.setToolTipTextAt(1, ApplicationMessages.getText("master.tab.loans.tip"));
	}

}
