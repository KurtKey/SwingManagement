package graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;

public class Menu extends JFrame {

	private JPanel contentPane;
	private Menu cContext = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 918, 597);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton empBtn = new JButton("EMPLOYE");
		empBtn.setFont(new Font("Cambria", Font.PLAIN, 40));
		empBtn.setBounds(167, 151, 577, 92);
		contentPane.add(empBtn);
		empBtn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		empBtn.setToolTipText("gérer les employes");
		empBtn.setBackground(new Color(192, 192, 192));
		empBtn.setForeground(Color.BLACK);
		empBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EmployeUI frame = new EmployeUI();
				cContext.setVisible(false);
				frame.setVisible(true);
			}
		});

		JButton MachineBtn = new JButton("MACHINE");
		MachineBtn.setFont(new Font("Cambria", Font.PLAIN, 40));
		MachineBtn.setBounds(167, 278, 577, 92);
		contentPane.add(MachineBtn);
		MachineBtn.setBackground(new Color(192, 192, 192));
		MachineBtn.setForeground(Color.BLACK);
		MachineBtn.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		MachineBtn.setToolTipText("gérer les machines");
		MachineBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MachineUI frame = new MachineUI();
				cContext.setVisible(false);
				frame.setVisible(true);
			}
		});
	}
}
