import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class classe extends JFrame{
	
	int dimx = 1200;
	int dimy = 650;
	
	private JLabel lb1;
	private JTextArea ta1;
	private FileDialog fdSave, fdOpen;
	private String filename;
	private JPanel pn1;
	private JMenuBar mbBar;
	private JMenu mnFile;
	private JMenuItem miOpen, miSave, miProj;
	
 	public static void main (String Args[]) {
	
 		classe frame = new classe();
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
		
		mbBar = new JMenuBar();
		
		mnFile = new JMenu("Arquivo");
		
		miOpen = new JMenuItem("Abrir");
		miSave = new JMenuItem("Salvar");
		miProj = new JMenuItem("Criar Projeto HTML+CSS");
		
		mnFile.add(miOpen);
		mnFile.add(miSave);
		mnFile.add(miProj);
		
		mbBar.add(mnFile);
		
		setJMenuBar(mbBar);
		
		pn1 = new JPanel(null);
		pn1.setBackground(Color.LIGHT_GRAY);
		pn1.setBounds(0,0,dimx,dimy);
		
		lb1 = new JLabel("Digite o texto abaixo");
		lb1.setForeground(Color.BLACK);
		lb1.setBounds(10,5,130,20);
		pn1.add(lb1);
		
		ta1 = new JTextArea();
		ta1.setBounds(0,30,1200,620);
		ta1.setBackground(Color.DARK_GRAY);
		ta1.setForeground(Color.WHITE);
		pn1.add(ta1);
		
		fdOpen = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSave = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		
		add(pn1);
		
	}
	public void definirEventos() {
		miProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaProjeto();
			}
		});
		miSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salva();
			}
		});
		miOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				abre();
			}
		});
	}
	public void criaProjeto() {
		
		File dir = new File("c:/"+JOptionPane.showInputDialog(null,"Digite o nome do projeto"));
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			
			FileWriter out = new FileWriter(dir+"/index.html");
			out.write(html);
			out.close();
			
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao criar o projeto "+erro);
		}
		
		dir = new File(dir+"/CSS");
		if (!dir.exists()){
			dir.mkdir();	
		}
		try {
			
			FileWriter out = new FileWriter(dir+"/styles.css");
			out.write("//code here... \n");
			out.close();
			
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao criar o projeto "+erro);
		}
		
	}
	public void salva() {
		try {
			fdSave.setVisible(true);
			if (fdSave.getFile() == null) {
				return;
			}
			filename = fdSave.getDirectory()+fdSave.getFile();
			FileWriter out = new FileWriter(filename);
			out.write(ta1.getText());
			out.close();
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao salvar arquivo "+erro.toString());
		}
		
	}
	public void abre() {
		try {
			fdOpen.setVisible(true);
			if (fdOpen.getFile() == null) {
				return;
			}
			filename = fdOpen.getDirectory()+fdOpen.getFile();
			FileReader in = new FileReader(filename);
			String s = "";
			int i = in.read();
			while (i != -1) {
				s += (char) i;
				i = in.read();
			}
			ta1.setText(s);
			in.close();
			
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao abrir arquivo "+erro.toString());
		}
	}
	
	String html = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "<head> \n"
			+ "<link rel='stylesheet' href='CSS/styles.css' \n"
			+ "<title> \n"
			+ "</title> \n"
			+ "</head> \n"
			+ "<body> \n"
			+ "</body> \n"
			+ "</html> \n";
}
