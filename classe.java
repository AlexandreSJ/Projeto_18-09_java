import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class classe extends JFrame{
	
	int dimx = 1200;
	int dimy = 650;
	
	private JLabel lb1, lb2;
	private JButton btSave, btLoad, btClear;
	private JTextField tf1;
	private JTextArea ta1;
	private FileDialog fdSave, fdLoad;
	private String filename;

 	public static void main (String Args[]) {
		JFrame frame = new classe();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public classe() {
		inicializarComponentes();
		definirEventos();
	}
	public void inicializarComponentes() {
		
		setLayout(null);
		setBounds((1366-dimx)/2,(768-dimy)/2,dimx,dimy);
		setResizable(false);		
		
		lb1 = new JLabel("Digite o texto aqui");
		lb1.setBounds(10,10,130,20);
		
		ta1 = new JTextArea();
		ta1.setBounds(10,30,1170,500);
		ta1.setBackground(Color.DARK_GRAY);
		ta1.setForeground(Color.WHITE);
		
		
		
		add(lb1);
		add(ta1);
	}
	public void definirEventos() {
		
	}
	
}
