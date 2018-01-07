package fr.unice.miage.projetpa;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private Repository repository;
	private JFrame frame;
	private JPanel contentPane;
	
	public App(Repository repository) {
		this.repository = repository;
	}
	
	void showFrame() {
		if (frame == null) {
			frame = new JFrame("Robot Plugins War");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(100, 100, 800, 800);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(new BorderLayout(0, 0));
			frame.setContentPane(contentPane);
		}
		frame.setVisible(true);
	}
	
}
