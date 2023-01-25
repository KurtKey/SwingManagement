package graphics;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import beans.Employe;
import service.EmployeService;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class EmployeUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtSalaire;
	private JTextField txtIdFind;
	private JTable table;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1_1;
	private JLabel lblNewLabel_1_2;
	private JLabel lblNewLabel_1_3;
	private JButton createBtn;
	private JButton findBtn;
	private JButton refreshBtn;
	private EmployeService es = new EmployeService();
	private final String[] colomns = { "id", "nom", "prenom", "salaire" };
	private EmployeUI cContext = this;
	private JButton btnUpdate;
	private JButton returnBnt;
	private int row = -1, idOfSelectedRow;
	private List<Employe> lst = new ArrayList<>();
	private JComboBox<String> colomnChooser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeUI frame = new EmployeUI();
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
	public EmployeUI() {
		EmployeUI mContext = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 920, 583);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Labels :
		lblNewLabel = new JLabel("GESTION DES EMPLOYES:");
		lblNewLabel.setBounds(145, 11, 446, 53);
		lblNewLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		contentPane.add(lblNewLabel);

		lblNewLabel_1_1 = new JLabel("Nom : ");
		lblNewLabel_1_1.setBounds(10, 136, 125, 42);
		lblNewLabel_1_1.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_1);

		lblNewLabel_1_2 = new JLabel("Prenom : ");
		lblNewLabel_1_2.setBounds(10, 180, 125, 42);
		lblNewLabel_1_2.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_2);

		lblNewLabel_1_3 = new JLabel("Salaire : ");
		lblNewLabel_1_3.setBounds(10, 224, 125, 42);
		lblNewLabel_1_3.setFont(new Font("Arial Black", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1_3);

		txtNom = new JTextField();
		txtNom.setBounds(145, 149, 171, 20);
		txtNom.setToolTipText("nom de l'employe");
		txtNom.setColumns(10);
		contentPane.add(txtNom);

		txtPrenom = new JTextField();
		txtPrenom.setBounds(145, 193, 171, 20);
		txtPrenom.setToolTipText("prenom de l'employe");
		txtPrenom.setColumns(10);
		contentPane.add(txtPrenom);

		txtSalaire = new JTextField();
		txtSalaire.setBounds(145, 237, 171, 20);
		txtSalaire.setToolTipText("salaire de l'employe");
		txtSalaire.setColumns(10);
		contentPane.add(txtSalaire);

		txtIdFind = new JTextField();
		txtIdFind.setBounds(446, 96, 145, 21);
		txtIdFind.setToolTipText("inserer un element à rechercher");
		txtIdFind.setColumns(10);
		contentPane.add(txtIdFind);

		// buttons :
		createBtn = new JButton("Ajouter");
		createBtn.setToolTipText("ajouter un employe");
		createBtn.setBackground(new Color(192, 192, 192));
		createBtn.setFont(new Font("Arial", Font.PLAIN, 20));
		createBtn.setBounds(145, 293, 171, 33);
		contentPane.add(createBtn);
		createBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nom = txtNom.getText();
				String prenom = txtPrenom.getText();
				String salaire = txtSalaire.getText();
				if (!nom.isEmpty() && !prenom.isEmpty() && !salaire.isEmpty())
					try {
						Employe emp = new Employe(nom, prenom, Double.parseDouble(salaire));
						es.create(emp);
						mContext.loadEmployes();
						contentPane.updateUI();
					} catch (Exception em) {
						JOptionPane.showMessageDialog(null, "Error :" + "\n" + em.getMessage());
					}
			}
		});

		findBtn = new JButton("Rechercher");
		findBtn.setBackground(new Color(192, 192, 192));
		findBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		findBtn.setBounds(608, 86, 125, 38);
		contentPane.add(findBtn);
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (colomnChooser.getSelectedItem().equals("id")) {
					List<Employe> l = new ArrayList<>();
					try {
						int id = Integer.parseInt(txtIdFind.getText());
						l.add(es.findById(id));
						lst=l;
						loadSpecifiedEmployes();
						contentPane.updateUI();
					} catch (Exception fe) {
						JOptionPane.showMessageDialog(cContext, "Element not found", "Not found",
								JOptionPane.OK_OPTION);
					}
				} else {
					lst = es.findElementsByKeyWord(colomnChooser.getSelectedItem().toString(), txtIdFind.getText());
					loadSpecifiedEmployes();
					contentPane.updateUI();
				}
			}
		});

		refreshBtn = new JButton("Rafraichir");
		refreshBtn.setBackground(new Color(192, 192, 192));
		refreshBtn.setFont(new Font("Arial", Font.PLAIN, 15));
		refreshBtn.setToolTipText("Refresh");
		refreshBtn.setBounds(743, 86, 125, 38);
		contentPane.add(refreshBtn);
		refreshBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadEmployes();
				contentPane.updateUI();
			}
		});

		JButton btnNewButton = new JButton("Supprimer");
		btnNewButton.setToolTipText("supprimer un employe");
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 20));
		btnNewButton.setBounds(145, 399, 171, 33);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				List<Employe> l = new ArrayList<>();
				l = es.findAll();
				Employe emp = l.get(rowIndex);
				es.delete(emp);
				loadEmployes();
				contentPane.updateUI();
			}
		});

		btnUpdate = new JButton("Modifier");
		btnUpdate.setToolTipText("modifier un employe");
		btnUpdate.setBackground(new Color(192, 192, 192));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 20));
		btnUpdate.setBounds(145, 347, 171, 33);
		contentPane.add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (row == -1) {
					JOptionPane.showMessageDialog(cContext, "Select a row first!!");
				} else {
					String nom = txtNom.getText();
					String prenom = txtPrenom.getText();
					String salaire = txtSalaire.getText();
					try {
						if (!nom.isEmpty() && !prenom.isEmpty()) {
							Employe emp = new Employe(idOfSelectedRow, nom, prenom, Double.parseDouble(salaire));
							es.update(emp);
							loadEmployes();
							contentPane.updateUI();
						} else
							JOptionPane.showMessageDialog(cContext, "complete all fields than press modify!!");
					} catch (Exception em) {
						JOptionPane.showMessageDialog(cContext, "Error : " + em.getMessage());
					}
				}
			}
		});

		returnBnt = new JButton("<");
		returnBnt.setToolTipText("page precedente");
		returnBnt.setForeground(new Color(0, 0, 0));
		returnBnt.setBackground(new Color(0, 128, 192));
		returnBnt.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		returnBnt.setFont(new Font("Arial Black", Font.BOLD, 30));
		returnBnt.setBounds(10, 13, 125, 48);
		contentPane.add(returnBnt);
		returnBnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cContext.setVisible(false);
				Menu frame = new Menu();
				frame.setVisible(true);
			}
		});

		// combo box

		colomnChooser = new JComboBox<String>();
		colomnChooser.setBounds(326, 95, 100, 22);
		contentPane.add(colomnChooser);
		colomnChooser.addItem("id");
		colomnChooser.addItem("nom");
		colomnChooser.addItem("prenom");

		loadEmployes();

		// adding to contentPane
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(38, 118, 20, 1);
		contentPane.setLayout(null);
		contentPane.add(horizontalStrut);
		contentPane.add(table);

	}

	public void loadEmployes() {
		List<Employe> lst = new ArrayList<>();
		lst = es.findAll();
		Object[][] datTab = new String[lst.size()][4];
		int i = 0;
		for (Employe emp : lst) {
			datTab[i++] = new String[] { String.valueOf(emp.getId()), emp.getNom(), emp.getPrenom(),
					String.valueOf(emp.getSalaire()) };
		}
		if (cContext.table != null)
			contentPane.remove(table);
		newTable(datTab, colomns);
	}

	public void loadSpecifiedEmployes() {
		Object[][] datTab = new Object[lst.size()][4];
		int i = 0;
		for (Employe e : lst) {
			datTab[i++] = new Object[] { e.getId(), e.getNom(), e.getPrenom(), e.getSalaire() };
		}
		if (cContext.table != null)
			contentPane.remove(table);
		newTable(datTab, colomns);
	}

	public void newTable(Object[][] datTab, String[] colomns) {
		table = new JTable(datTab, colomns);
		table.setToolTipText("table des employes");
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				if (row != -1) {
					List<Employe> l = new ArrayList<>();
					l = es.findAll();
					Employe tmp = l.get(row);
					idOfSelectedRow = tmp.getId();
					txtNom.setText((es.findById(idOfSelectedRow).getNom()));
					txtPrenom.setText((es.findById(idOfSelectedRow).getPrenom()));
					txtSalaire.setText(String.valueOf(es.findById(idOfSelectedRow).getSalaire()));
				}
			}
		});
		table.setBackground(new Color(255, 255, 255));
		table.setBounds(326, 147, 542, 376);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		contentPane.add(table);
	}
}
