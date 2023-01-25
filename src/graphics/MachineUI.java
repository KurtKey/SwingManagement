package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import beans.Machine;
import beans.Employe;
import service.MachineService;
import service.EmployeService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MachineUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRef;
	private JTextField txtMarq;
	private JTextField txtDate;
	private JTextField txtPrix;
	private JTextField txtIdFind;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JButton createBtn;
	private JButton findBtn;
	private JButton refreshBtn;
	private JButton btnUpdate;
	private JButton deleteBtn;
	private MachineService ms = new MachineService();
	private EmployeService es = new EmployeService();
	private final String[] colomns = { "id", "reference", "marque", "date", "prix", "employe" };
	private MachineUI cContext = this;
	private JButton returnBtn;
	private JComboBox<Employe> listeEmployes;
	private int row = -1, idOfSelectedRow;
	private SimpleDateFormat smp = new SimpleDateFormat("yyyy-MM-dd");
	private JTextField txtDate1;
	private JTextField txtDate2;
	private List<Machine> lst = new ArrayList<>();
	private JComboBox<String> colomnChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MachineUI frame = new MachineUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MachineUI() {
		MachineUI mContext = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 583);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Labels :
		lblNewLabel = new JLabel("GESTION DES MACHINES:");
		lblNewLabel.setBounds(143, 11, 446, 53);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Reference : ");
		lblNewLabel_1.setBounds(10, 161, 125, 42);
		lblNewLabel_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);

		lblNewLabel_1_1 = new JLabel("Marque : ");
		lblNewLabel_1_1.setBounds(10, 192, 125, 42);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_1);

		lblNewLabel_1_2 = new JLabel("Date d'achat : ");
		lblNewLabel_1_2.setBounds(10, 223, 125, 42);
		lblNewLabel_1_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_2);

		lblNewLabel_1_3 = new JLabel("Prix : ");
		lblNewLabel_1_3.setBounds(10, 254, 125, 42);
		lblNewLabel_1_3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_3);

		JLabel titreEmploye = new JLabel("Employe : ");
		titreEmploye.setFont(new Font("Arial Black", Font.PLAIN, 15));
		titreEmploye.setBounds(10, 293, 125, 42);
		contentPane.add(titreEmploye);

		JLabel lblNewLabel_2_1 = new JLabel("Date entre : ");
		lblNewLabel_2_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(236, 122, 131, 22);
		contentPane.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_2 = new JLabel("et :");
		lblNewLabel_2_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		lblNewLabel_2_2.setBounds(466, 122, 33, 22);
		contentPane.add(lblNewLabel_2_2);

		// Text Fields :
		txtRef = new JTextField();
		txtRef.setBounds(143, 174, 171, 20);
		txtRef.setToolTipText("Reference");
		txtRef.setColumns(10);
		contentPane.add(txtRef);

		txtMarq = new JTextField();
		txtMarq.setBounds(143, 205, 171, 20);
		txtMarq.setToolTipText("Marque");
		txtMarq.setColumns(10);
		contentPane.add(txtMarq);

		txtDate = new JTextField();
		txtDate.setBounds(143, 236, 171, 20);
		txtDate.setToolTipText("Date");
		txtDate.setColumns(10);
		contentPane.add(txtDate);

		txtPrix = new JTextField();
		txtPrix.setBounds(143, 267, 171, 20);
		txtPrix.setToolTipText("Prix");
		txtPrix.setColumns(10);
		contentPane.add(txtPrix);

		txtIdFind = new JTextField();
		txtIdFind.setBounds(365, 91, 142, 20);
		txtIdFind.setToolTipText("inserer un element à rechercher");
		txtIdFind.setColumns(10);
		contentPane.add(txtIdFind);

		txtDate1 = new JTextField();
		txtDate1.setToolTipText("date de debut");
		txtDate1.setColumns(10);
		txtDate1.setBounds(365, 124, 96, 20);
		contentPane.add(txtDate1);

		txtDate2 = new JTextField();
		txtDate2.setToolTipText("date de fin");
		txtDate2.setColumns(10);
		txtDate2.setBounds(509, 124, 96, 20);
		contentPane.add(txtDate2);

		// buttons :
		createBtn = new JButton("Ajouter");
		createBtn.setToolTipText("ajouter une machine");
		createBtn.setBackground(new Color(192, 192, 192));
		createBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		createBtn.setBounds(143, 366, 171, 33);
		contentPane.add(createBtn);
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// date = smp.format(smp.parse(date));
				// if (!reference.isEmpty() && !marque.isEmpty() && !date.isEmpty())
				try {
					ms.create(new Machine(txtRef.getText(), txtMarq.getText(), smp.parse(txtDate.getText()),
							Double.parseDouble(txtPrix.getText()), (Employe) listeEmployes.getSelectedItem()));
					mContext.loadMachines();
					contentPane.updateUI();
				} catch (Exception em) {
					JOptionPane.showMessageDialog(null, "Error :" + "\n" + em.getMessage());
				}
			}
		});

		findBtn = new JButton("Rechercher");
		findBtn.setBackground(new Color(192, 192, 192));
		findBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		findBtn.setBounds(617, 91, 125, 53);
		contentPane.add(findBtn);
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtIdFind.getText().isEmpty() && txtDate1.getText().isEmpty() && txtDate2.getText().isEmpty()) {
					if (colomnChooser.getSelectedItem().equals("id")) {
						List<Machine> l = new ArrayList<>();
						try {
							int id = Integer.parseInt(txtIdFind.getText());
							l.add(ms.findById(id));
							lst=l;
							loadSpecifiedMachines();
							contentPane.updateUI();
						} catch (Exception fe) {
							JOptionPane.showMessageDialog(cContext, "Element not found", "Not found",
									JOptionPane.OK_OPTION);
						}
					} else if (colomnChooser.getSelectedItem().equals("employe")) {
						List<Machine> l = new ArrayList<>();
						for (Employe emp : es.findAll()) {
							if (emp.getNom().equals(txtIdFind.getText()) || emp.getPrenom().equals(txtIdFind.getText())
									|| (emp.getNom() + " " + emp.getPrenom()).equals(txtIdFind.getText())
									|| (emp.getPrenom() + " " + emp.getNom()).equals(txtIdFind.getText()))
								l = ms.getMachinesByEmploye(emp);
						}
						lst = l;
						loadSpecifiedMachines();
						contentPane.updateUI();
					} else if (colomnChooser.getSelectedItem().equals("reference")) {
						lst = ms.findElementsByKeyWord("reference", txtIdFind.getText());
						loadSpecifiedMachines();
						contentPane.updateUI();
					} else if (colomnChooser.getSelectedItem().equals("marque")) {
						lst = ms.findElementsByKeyWord("marque", txtIdFind.getText());
						loadSpecifiedMachines();
						contentPane.updateUI();
					}
				} else if (txtIdFind.getText().isEmpty() && !txtDate1.getText().isEmpty()
						&& !txtDate2.getText().isEmpty()) {
					lst = ms.getMachinesByDates(txtDate1.getText(), txtDate2.getText());
					loadSpecifiedMachines();
					contentPane.updateUI();

				}
			}
		});

		refreshBtn = new JButton("rafraichir");
		refreshBtn.setBackground(new Color(192, 192, 192));
		refreshBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		refreshBtn.setToolTipText("Refresh");
		refreshBtn.setBounds(744, 91, 125, 53);
		contentPane.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadMachines();
				contentPane.updateUI();
			}
		});

		deleteBtn = new JButton("Supprimer");
		deleteBtn.setToolTipText("supprimer une machine");
		deleteBtn.setBackground(new Color(192, 192, 192));
		deleteBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		deleteBtn.setBounds(143, 454, 171, 33);
		contentPane.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				List<Machine> l = new ArrayList<>();
				l = ms.findAll();
				Machine m = l.get(rowIndex);
				ms.delete(m);
				loadMachines();
				contentPane.updateUI();
			}
		});

		btnUpdate = new JButton("Modifier");
		btnUpdate.setToolTipText("modifier une machine");
		btnUpdate.setBackground(new Color(192, 192, 192));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpdate.setBounds(143, 410, 171, 33);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (row == -1) {
					JOptionPane.showMessageDialog(cContext, "Select a row first!!");
				} else {
					String reference = txtRef.getText();
					String marque = txtMarq.getText();
					String Date = txtDate.getText();
					String prix = txtPrix.getText();
					Employe employe = (Employe) listeEmployes.getSelectedItem();
					try {
						if (!reference.isEmpty() && !marque.isEmpty()) {
							Machine mach = new Machine(idOfSelectedRow, reference, marque, smp.parse(Date),
									Double.parseDouble(prix), employe);
							ms.update(mach);
							loadMachines();
							contentPane.updateUI();
						} else
							JOptionPane.showMessageDialog(cContext, "complete all fields than press modify!!");
					} catch (Exception em) {
						JOptionPane.showMessageDialog(cContext, "Error : " + em.getMessage());
					}
				}
			}
		});

		returnBtn = new JButton("<");
		returnBtn.setToolTipText("page precedente");
		returnBtn.setBackground(new Color(0, 128, 192));
		returnBtn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		returnBtn.setFont(new Font("Arial Black", Font.BOLD, 30));
		returnBtn.setBounds(10, 11, 123, 48);
		contentPane.add(returnBtn);
		returnBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cContext.setVisible(false);
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});

		// combo boxes :

		listeEmployes = new JComboBox<Employe>();
		listeEmployes.setToolTipText("nom d'employe");
		listeEmployes.setBounds(143, 305, 171, 23);
		contentPane.add(listeEmployes);

		colomnChooser = new JComboBox<String>();
		colomnChooser.setBounds(236, 89, 82, 22);
		contentPane.add(colomnChooser);
		colomnChooser.addItem("id");
		colomnChooser.addItem("employe");
		colomnChooser.addItem("reference");
		colomnChooser.addItem("marque");

		loadMachines();
		loadEmployes();

		// adding to contentPane
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(38, 118, 20, 1);
		contentPane.setLayout(null);
		contentPane.add(horizontalStrut);
		contentPane.add(table);

	}

	public void loadMachines() {
		Object[][] datTab = new Object[(ms.findAll()).size()][6];
		int i = 0;
		for (Machine m : ms.findAll()) {
			datTab[i++] = new Object[] { m.getId(), m.getReference(), m.getMarque(), m.getDateAchat(), m.getPrix(),
					m.getEmploye().getId() };
		}
		if (cContext.table != null)
			contentPane.remove(table);
		newTable(datTab, colomns);
	}

	public void loadEmployes() {
		for (Employe e : es.findAll()) {
			listeEmployes.addItem(e);
		}
	}

	public void loadSpecifiedMachines() {
		Object[][] datTab = new Object[lst.size()][6];
		int i = 0;
		for (Machine m : lst) {
			datTab[i++] = new Object[] { m.getId(), m.getReference(), m.getMarque(), m.getDateAchat(), m.getPrix(),
					m.getEmploye().getId() };
		}
		if (cContext.table != null)
			contentPane.remove(table);
		newTable(datTab, colomns);
	}

	public void newTable(Object[][] datTab, String[] colomns) {
		table = new JTable(datTab, colomns);
		table.setToolTipText("table des machines");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				if (row != -1) {
					List<Machine> l = new ArrayList<>();
					l = ms.findAll();
					Machine tmp = l.get(row);
					idOfSelectedRow = tmp.getId();
					txtRef.setText((ms.findById(idOfSelectedRow).getReference()));
					txtMarq.setText((ms.findById(idOfSelectedRow).getMarque()));
					txtDate.setText(smp.format(ms.findById(idOfSelectedRow).getDateAchat()));
					txtPrix.setText(String.valueOf(ms.findById(idOfSelectedRow).getPrix()));
					listeEmployes.setSelectedItem((ms.findById(idOfSelectedRow).getEmploye()));
				}
			}
		});
		table.setBackground(new Color(255, 255, 255));
		table.setBounds(324, 171, 545, 353);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(table);
	}
}
