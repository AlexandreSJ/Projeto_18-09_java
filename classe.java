import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Element;

public class classe extends JFrame{
	int dimx = 1200;
	int dimy = 650;
	private JLabel lb1;
	private JTextPane ta1, ta2, ta3, ta4;
	private FileDialog fdSave, fdOpen;
	private String filename;
	private JPanel pn1, pn2, pn3, pn4;
	private JMenuBar mbBar;
	private JMenu mnFile,mnColor,mnExamples;
	private JMenuItem miOpen, miSave, miProj,miDark,miWhite;
	private JMenuItem miSizes,miFormatting,miPara;
	private JScrollPane sppainel, sppainel2, sppainel3, sppainel4;
	private boolean aviso = true;
	private JTextArea lines;
	private JTabbedPane tp1, tp2, tp3, tp4;

	private Color color = new Color(0,0,0);
	StyleContext cont = StyleContext.getDefaultStyleContext();
    AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(173,71,172));
    AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, color);
    
	ImageIcon iconeTitulo = new ImageIcon(getClass().getResource("icone.png"));
	
	public static void main (String Args[]) {
		classe frame = new classe();
 		frame.setTitle("IDE HTML GRUPO AMARELO");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		frame.setVisible(true);
	}
	public classe() {
		inicializarComponentes();
		definirEventos();
	}
	
	public void inicializarComponentes() {
 		setIconImage(iconeTitulo.getImage());
		try {
			File dir = new File("c:/htmlidle");
			if(!dir.exists()) {
				dir.mkdir();
			}
			File dir2 = new File("c:/htmlidle/exemplos");
			if(!dir2.exists()) {
				dir2.mkdir();
			}
		}catch(Exception e) {
			System.out.print(e);
		}
		try{
			FileWriter out = new FileWriter("C:/htmlidle/exemplos/Tamanhos.html");
			out.write(Basic);
			out.close();
		}catch(Exception erro) {
			System.out.print(erro);
		}
		try{
			FileWriter out = new FileWriter("C:/htmlidle/exemplos/Formatacao.html");
			out.write(Formatting);
			out.close();
		}catch(Exception erro) {
			System.out.print(erro);
		}
		try{
			FileWriter out = new FileWriter("C:/htmlidle/exemplos/Paragrafo.html");
			out.write(Paragraphs);
			out.close();
		}catch(Exception erro) {
			System.out.print(erro);
		}
		
		setLayout(null);
		setBounds((1366-dimx)/2,(768-dimy)/2,dimx,dimy);
		setResizable(false);		
		
		
        DefaultStyledDocument doc = new DefaultStyledDocument() {
            public void insertString (int offset, String str, AttributeSet a) throws BadLocationException {
                super.insertString(offset, str, a);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offset);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offset + str.length());
                int wordL = before;
                int wordR = before;

                while (wordR <= after) {
                    if (wordR == after || String.valueOf(text.charAt(wordR)).matches("\\W")) {
                        if (text.substring(wordL, wordR).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p)"))
                            setCharacterAttributes(wordL, wordR - wordL, attr, false);
                        else
                            setCharacterAttributes(wordL, wordR - wordL, attrBlack, false);
                        wordL = wordR;
                    }
                    wordR++;
                }
            }

            public void remove (int offs, int len) throws BadLocationException {
                super.remove(offs, len);

                String text = getText(0, getLength());
                int before = findLastNonWordChar(text, offs);
                if (before < 0) before = 0;
                int after = findFirstNonWordChar(text, offs);

                if (text.substring(before, after).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p)")) {
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };

        tp1 = new JTabbedPane();
        tp2 = new JTabbedPane();
        tp3 = new JTabbedPane();
        tp4 = new JTabbedPane();
        
        tp1.addTab("Teste", new JPanel());
        tp2.addTab("Teste", new JPanel());
        tp3.addTab("Teste", new JPanel());
        tp4.addTab("Teste", new JPanel());
        
		mbBar = new JMenuBar();
		
		mnFile = new JMenu("Arquivo");
		
		miOpen = new JMenuItem("Abrir");
		miSave = new JMenuItem("Salvar");
		miProj = new JMenuItem("Criar Projeto HTML+CSS+JS");
		
		mnFile.add(miOpen);
		mnFile.add(miSave);
		mnFile.add(miProj);
		
		mnColor = new JMenu("Cores");
		
		miDark = new JMenuItem("Modo Noturno");
		miWhite = new JMenuItem("Desativar Modo Noturno");
		
		mnColor.add(miDark);
		mnColor.add(miWhite);
		
		mnExamples = new JMenu("Exemplos");
		
		miSizes = new JMenuItem("Tamanhos para títulos");
		miFormatting = new JMenuItem("Formatos");
		miPara = new JMenuItem("Parágrafos");
		
		mnExamples.add(miSizes);
		mnExamples.add(miFormatting);
		mnExamples.add(miPara);
		
		mbBar.add(mnFile);
		mbBar.add(mnColor);
		mbBar.add(mnExamples);
		setJMenuBar(mbBar);
		
		pn1 = new JPanel(null);
		pn1.setBackground(Color.LIGHT_GRAY);
		pn1.setBounds(0,0,dimx,dimy);
		pn2 = new JPanel(null);
		pn2.setBackground(Color.LIGHT_GRAY);
		pn2.setBounds(0,0,dimx,dimy);
		pn3 = new JPanel(null);
		pn3.setBackground(Color.LIGHT_GRAY);
		pn3.setBounds(0,0,dimx,dimy);
		pn4 = new JPanel(null);
		pn4.setBackground(Color.LIGHT_GRAY);
		pn4.setBounds(0,0,dimx,dimy);
		
		lb1 = new JLabel("Digite o texto abaixo");
		lb1.setForeground(Color.BLACK);
		lb1.setBounds(10,5,130,20);
		pn1.add(tp1);
		pn2.add(tp2);
		pn3.add(tp3);
		pn4.add(tp3);
			
		ta1 = new JTextPane(doc);
		ta2 = new JTextPane(doc);
		ta3 = new JTextPane(doc);
		ta4 = new JTextPane(doc);
		
		ta1.setForeground(Color.BLACK);
		ta2.setForeground(Color.BLACK);
		ta3.setForeground(Color.BLACK);
		ta4.setForeground(Color.BLACK);
		
		sppainel = new JScrollPane(ta1);
		sppainel2 = new JScrollPane(ta2);
		sppainel3 = new JScrollPane(ta3);
		sppainel4 = new JScrollPane(ta4);
		
        lines = new JTextArea("1");
        
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        
        ta1.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = ta1.getDocument().getLength();
                Element root = ta1.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });
        ta2.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = ta2.getDocument().getLength();
                Element root = ta2.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });
        ta3.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = ta3.getDocument().getLength();
                Element root = ta3.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });
        ta4.getDocument().addDocumentListener(new DocumentListener() {
            public String getText() {
                int caretPosition = ta4.getDocument().getLength();
                Element root = ta4.getDocument().getDefaultRootElement();
                String text = "1" + System.getProperty("line.separator");
                for (int i = 2; i < root.getElementIndex(caretPosition) + 2; i++) {
                    text += i + System.getProperty("line.separator");
                }
                return text;
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines.setText(getText());
            }

        });
        sppainel.getViewport().add(ta1);
        sppainel.setRowHeaderView(lines);
        sppainel2.getViewport().add(ta2);
        sppainel2.setRowHeaderView(lines);
        sppainel3.getViewport().add(ta3);
        sppainel3.setRowHeaderView(lines);
        sppainel4.getViewport().add(ta4);
        sppainel4.setRowHeaderView(lines);
        
        Font f = new Font("",Font.ITALIC,12);
        
        lines.setFont(f);
        
		sppainel.setBounds(0,30,1185,558);
		sppainel2.setBounds(0,30,1185,558);
		sppainel3.setBounds(0,30,1185,558);
		sppainel4.setBounds(0,30,1185,558);
		
		fdOpen = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSave = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		
		pn1.add(sppainel);
		pn2.add(sppainel2);
		pn3.add(sppainel3);
		pn4.add(sppainel4);

		JOptionPane.showMessageDialog(pn1, "Pasta criada em C:\n"
				+ "Nome: htmlidle");
		
		JPanel principal = new JPanel();
		
		principal.add(pn1);
		principal.add(pn2);
		principal.add(pn3);
		principal.add(pn4);
		
	}
	public void definirEventos() {
		
		miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
		
		miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
		
		miProj.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
		
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
		
		miDark.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setBackground(Color.DARK_GRAY);
				ta2.setBackground(Color.DARK_GRAY);
				ta3.setBackground(Color.DARK_GRAY);
				ta4.setBackground(Color.DARK_GRAY);
				lines.setForeground(Color.WHITE);
				color = new Color(255,255,255);
				attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, color);
				ta1.setText(ta1.getText()+"");
				ta2.setText(ta2.getText()+"");
				ta3.setText(ta3.getText()+"");
				ta4.setText(ta4.getText()+"");
			}
		});
		miWhite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setBackground(Color.WHITE);
				ta2.setBackground(Color.WHITE);
				ta3.setBackground(Color.WHITE);
				ta4.setBackground(Color.WHITE);
				lines.setForeground(Color.BLACK);
				color = new Color(0,0,0);
				attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, color);
				ta1.setText(ta1.getText()+"");
				ta2.setText(ta2.getText()+"");
				ta3.setText(ta3.getText()+"");
				ta4.setText(ta4.getText()+"");
			}
		});
		
		//Exemplos
		miSizes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setText(Basic);
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
		miFormatting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setText(Formatting);
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
		miPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ta1.setText(Paragraphs);
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
	}
	
    private int findLastNonWordChar (String text, int index) {
        while (--index >= 0) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
        }
        return index;
    }

    private int findFirstNonWordChar (String text, int index) {
        while (index < text.length()) {
            if (String.valueOf(text.charAt(index)).matches("\\W")) {
                break;
            }
            index++;
        }
        return index;
    }
	
	public void criaProjeto() {
		String name = JOptionPane.showInputDialog(pn1,"Digite o nome do projeto");
		if(name == null){
		}else {
			File dir = new File("c:/"+name);
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
			
			File dir1 = new File(dir+"/CSS");
			if (!dir1.exists()){
				dir1.mkdir();	
			}
			try {
				FileWriter out = new FileWriter(dir1+"/styles.css");
				out.write("//code here... \n");
				out.close();
				
			} catch(IOException erro) {
				JOptionPane.showMessageDialog(null,"Erro ao criar o projeto "+erro);
			}
			
			dir = new File(dir+"/JavaScript");
			if(!dir.exists()) {
				dir.mkdir();
			}
			try {
				FileWriter out = new FileWriter(dir+"/javascript.js");
				out.write("//code here... \n");
				out.close();
				JOptionPane.showMessageDialog(pn1,"Projeto salvo em c:");
			}catch(IOException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao criar o projeto "+erro);
			}
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
		int a = 0;
		if(!ta1.getText().equals("")) {
			a  = JOptionPane.showConfirmDialog(pn1,"Salvar arquivo criado?");
			if(a == 0) {
				salva();
			}
		}
		if(a != 2) {
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
	}

	private String html = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "<head> \n"
			+ "<link rel='stylesheet' href='CSS/styles.css'> \n"
			+ "<title> "
			+ "</title> \n"
			+ "</head> \n"
			+ "<body> \n"
			+ "</body> \n"
			+ "</html> \n";

	private String Basic = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "	<head> \n"
			+ "	<link rel='stylesheet' href='CSS/styles.css'> \n"
			+ "	<title>Eu sou um título</title> \n"
			+ "	</head> \n"
			+ "	<body> \n"
			+ "		<!-- Eu sou um comentário -->\n"
			+ "		<!-- \n"
			+ "		Cada 'h' tem um tamanho pré-definido \n"
			+ "		-->\n"
			+ "		<h1>Eu sou um título h1</h1> \n"
			+ "		<h2>Eu sou um título h2</h2> \n"
			+ "		<h3>Eu sou um título h3</h3> \n"
			+ "		<h4>Eu sou um título h4</h4> \n"
			+ "		<h5>Eu sou um título h5</h5> \n"
			+ "	</body> \n"
			+ "</html>";
	private String Formatting = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "	<head> \n"
			+ "	<link rel='stylesheet' href='CSS/styles.css'> \n"
			+ "	<title></title> \n"
			+ "	</head> \n"
			+ "	<body> \n"
			+ "	<!-- <br> pula linha -->\n"
			+ "		<b>Eu sou um texto em negríto</b><br> \n"
			+ "		<i>Eu sou um texto em itálico</i><br> \n"
			+ "		<sub>Eu sou um texto subescrito</sub><br> \n"
			+ "		<sup>Eu sou um texto sobrescrito</sup><br> \n"
			+ "		<ins>Eu sou um texto sublinhado</ins><br> \n"
			+ "	</body> \n"
			+ "</html>";

	private String Paragraphs = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "	<head> \n"
			+ "	<link rel='stylesheet' href='CSS/styles.css'> \n"
			+ "	<title></title> \n"
			+ "	</head> \n"
			+ "	<body> \n"
			+ "		<p>Eu sou um parágrafo</p> \n"
			+ "	</body> \n"
			+ "</html>";
}