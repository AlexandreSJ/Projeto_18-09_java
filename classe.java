import java.io.*;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class classe extends JFrame{
	int dimx = 1200;
	int dimy = 650;

	private JTextPane ta1, ta2, ta3, ta4;
	private FileDialog fdSave,fdSave2,fdSave3,fdSave4, fdOpen;
	private String filename,filename2,filename3,filename4,salvar,salvar2,salvar3,salvar4;
	private JPanel pn1, pn2, pn3, pn4;
	private JMenuBar mbBar;
	private JMenu mnFile,mnColor,mnExamples;
	private JMenuItem miOpen, miSave, miSaveAs, miProj,miDark,miWhite;
	private JMenuItem miSintaxe,miSizes,miFormatting,miPara;
	private JScrollPane sppainel, sppainel2, sppainel3, sppainel4;
	private boolean aviso = true;
	private JTextArea lines, lines2, lines3, lines4;
	private JTabbedPane tb1;
	
	private Color color = new Color(0,0,0);
	StyleContext cont = StyleContext.getDefaultStyleContext();
    AttributeSet attr = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, new Color(173,71,172));
    AttributeSet attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, color);
    
	ImageIcon iconeTitulo = new ImageIcon(getClass().getResource("icone.png"));
	ImageIcon spawn = new ImageIcon(getClass().getResource("bg.jpg"));
	
	JLabel lbspawn = new JLabel(spawn);
	
	public static void main (String Args[]) {
		classe frame = new classe();
 		frame.setTitle("UMBRA - IDE HTML");
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
				JOptionPane.showMessageDialog(pn1, "Pasta criada em C:\n"
						+ "Nome: htmlidle");
			}
			File dir3 = new File("c:/htmlidle/projetos");
			if(!dir3.exists()) {
				dir3.mkdir();
			}
		}catch(Exception e) {
			System.out.print(e);
		}
		
		try{
			FileWriter out = new FileWriter("C:/htmlidle/exemplos/Sintaxe.html");
			out.write(html);
			out.close();
		}catch(Exception erro) {
			System.out.print(erro);
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
                        if (text.substring(wordL, wordR).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p|a|br|img|style)"))
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

                if (text.substring(before, after).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p|a|br|img|style)")) {
                    setCharacterAttributes(before, after - before, attr, false);
                } else {
                    setCharacterAttributes(before, after - before, attrBlack, false);
                }
            }
        };

        DefaultStyledDocument doc1 = new DefaultStyledDocument() {
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
                        if (text.substring(wordL, wordR).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p|a|br|img|style)"))
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

        DefaultStyledDocument doc2 = new DefaultStyledDocument() {
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
                        if (text.substring(wordL, wordR).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p|a|br|img|style)"))
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

        DefaultStyledDocument doc3 = new DefaultStyledDocument() {
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
                        if (text.substring(wordL, wordR).matches("(\\W)*(body|title|html|head|b|i|h1|h2|h3|h4|h5|hl|div|link|sub|sup|ins|br|p|a|br|img|style)"))
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


		
		mbBar = new JMenuBar();
		
		mnFile = new JMenu("Arquivo");
		
		miOpen = new JMenuItem("Abrir");
		miSave = new JMenuItem("Salvar");
		miSaveAs = new JMenuItem("Salvar Como...");
		miProj = new JMenuItem("Criar Projeto HTML+CSS+JS");
		
		mnFile.add(miOpen);
		mnFile.add(miSave);
		mnFile.add(miSaveAs);
		mnFile.add(miProj);
		
		mnColor = new JMenu("Estilo");
		
		miDark = new JMenuItem("Modo Noturno");
		miWhite = new JMenuItem("Desativar Modo Noturno");
		
		mnColor.add(miDark);
		mnColor.add(miWhite);
		
		Font f2 = new Font("Sans-Serif",Font.PLAIN,12);
		
		tb1 = new JTabbedPane();

		mnExamples = new JMenu("Exemplos");
		
		miSintaxe = new JMenuItem("Sintaxe");
		miSizes = new JMenuItem("Tamanhos para títulos");
		miFormatting = new JMenuItem("Formatos");
		miPara = new JMenuItem("Parágrafos");
		
		mnExamples.add(miSintaxe);
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

		ta1 = new JTextPane(doc);
		
		ta1.setForeground(Color.BLACK);
		ta1.setForeground(Color.BLACK);
		ta1.setText(ta1.getText()+"");
		
		ta2 = new JTextPane(doc1);
		ta2.setForeground(Color.BLACK);
		ta2.setText(ta2.getText()+"");
		
		ta3 = new JTextPane(doc2);
		ta3.setForeground(Color.BLACK);
		ta3.setText(ta3.getText()+"");
		
		ta4 = new JTextPane(doc3);
		ta4.setForeground(Color.BLACK);
		ta4.setText(ta4.getText()+"");

		ta1.setFont(f2);
		ta2.setFont(f2);
		ta3.setFont(f2);
		ta4.setFont(f2);
		
		sppainel = new JScrollPane(ta1);
		sppainel2 = new JScrollPane(ta2);
		sppainel3 = new JScrollPane(ta3);
		sppainel4 = new JScrollPane(ta4);
		
        lines = new JTextArea("1");
        lines2 = new JTextArea("1");
        lines3 = new JTextArea("1");
        lines4 = new JTextArea("1");
        
        lines.setBackground(Color.LIGHT_GRAY);
        lines.setEditable(false);
        lines2.setBackground(Color.LIGHT_GRAY);
        lines2.setEditable(false);
        lines3.setBackground(Color.LIGHT_GRAY);
        lines3.setEditable(false);
        lines4.setBackground(Color.LIGHT_GRAY);
        lines4.setEditable(false);
        
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
                lines2.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines2.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines2.setText(getText());
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
                lines3.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines3.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines3.setText(getText());
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
                lines4.setText(getText());
            }

            @Override
            public void insertUpdate(DocumentEvent de) {
                lines4.setText(getText());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                lines4.setText(getText());
            }

        });
        
        sppainel.getViewport().add(ta1);
        sppainel.setRowHeaderView(lines);
        sppainel2.getViewport().add(ta2);
        sppainel2.setRowHeaderView(lines2);
        sppainel3.getViewport().add(ta3);
        sppainel3.setRowHeaderView(lines3);
        sppainel4.getViewport().add(ta4);
        sppainel4.setRowHeaderView(lines4);
        
        Font f = new Font("",Font.ITALIC,12);
        
        lines.setBounds(0,5,10,10);
        lines.setFont(f2);
        lines2.setBounds(0,5,10,10);
        lines2.setFont(f2);
        lines3.setBounds(0,5,10,10);
        lines3.setFont(f2);
        lines4.setBounds(0,5,10,10);
        lines4.setFont(f2);
        
		sppainel.setBounds(0,0,1185,558);
		sppainel2.setBounds(0,0,1185,558);
		sppainel3.setBounds(0,0,1185,558);
		sppainel4.setBounds(0,0,1185,558);
		
		fdOpen = new FileDialog(this, "Abrir arquivo", FileDialog.LOAD);
		fdSave = new FileDialog(this, "Salvar arquivo", FileDialog.SAVE);
		
		pn1.add(sppainel);
		pn2.add(sppainel2);
		pn3.add(sppainel3);
		pn4.add(sppainel4);
		
		tb1.addTab("Aba1",pn1);
		tb1.addTab("Aba2",pn2);
		tb1.addTab("Aba3",pn3);
		tb1.addTab("Aba4",pn4);
		
		tb1.setBounds(0,0,dimx,dimy);
		
		lbspawn.setBounds(0,0,dimx,dimy);

		add(lbspawn);

		add(tb1);
		
		tb1.setVisible(false);
		
		new Delay().start();
		
		ta1.setText(html);
		ta2.setText(Basic);
		ta3.setText(Formatting);
		ta4.setText(Paragraphs);
	}
	public void definirEventos() {
		
		miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
		
		miOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		
		miSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_DOWN_MASK));
		
		miProj.setAccelerator(javax.swing.KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		
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
		miSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salvaComo();
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
				color = new Color(0,0,0);
				attrBlack = cont.addAttribute(cont.getEmptySet(), StyleConstants.Foreground, color);
				ta1.setText(ta1.getText()+"");
				ta2.setText(ta2.getText()+"");
				ta3.setText(ta3.getText()+"");
				ta4.setText(ta4.getText()+"");
			}
		});
		
		//Exemplos
		miSintaxe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb1.getSelectedIndex() == 0) {
					ta1.setText(html);
				}else if (tb1.getSelectedIndex() == 1) {
					ta2.setText(html);
				}else if (tb1.getSelectedIndex() == 2) {
					ta3.setText(html);
				}else if (tb1.getSelectedIndex() == 3) {
					ta4.setText(html);
				}else {
					JOptionPane.showMessageDialog(pn1, "Selecione uma aba");
				}
				
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
		miSizes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb1.getSelectedIndex() == 0) {
					ta1.setText(Basic);
				}else if (tb1.getSelectedIndex() == 1) {
					ta2.setText(Basic);
				}else if (tb1.getSelectedIndex() == 2) {
					ta3.setText(Basic);
				}else if (tb1.getSelectedIndex() == 3) {
					ta4.setText(Basic);
				}else {
					JOptionPane.showMessageDialog(pn1, "Selecione uma aba");
				}
				
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
		miFormatting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb1.getSelectedIndex() == 0) {
					ta1.setText(Formatting);
				}else if (tb1.getSelectedIndex() == 1) {
					ta2.setText(Formatting);
				}else if (tb1.getSelectedIndex() == 2) {
					ta3.setText(Formatting);
				}else if (tb1.getSelectedIndex() == 3) {
					ta4.setText(Formatting);
				}else {
					JOptionPane.showMessageDialog(pn1, "Selecione uma aba");
				}
				
				if(aviso == true) {
					JOptionPane.showMessageDialog(pn1, "Todos os exemplos estão salvos no diretório C:/htmlidle/exemplos");
					aviso = false;
				}
			}
		});
		miPara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tb1.getSelectedIndex() == 0) {
					ta1.setText(Paragraphs);
				}else if (tb1.getSelectedIndex() == 1) {
					ta2.setText(Paragraphs);
				}else if (tb1.getSelectedIndex() == 2) {
					ta3.setText(Paragraphs);
				}else if (tb1.getSelectedIndex() == 3) {
					ta4.setText(Paragraphs);
				}else {
					JOptionPane.showMessageDialog(pn1, "Selecione uma aba");
				}
				
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
			File dir = new File("c:/htmlidle/projetos/"+name);
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
				JOptionPane.showMessageDialog(pn1,"Projeto salvo em c:/htmlidle/Projetos");
			}catch(IOException erro) {
				JOptionPane.showMessageDialog(null, "Erro ao criar o projeto "+erro);
			}
		}
		
	}

	public void salva() {
		try {
			if(salvar == null && tb1.getSelectedIndex() == 0) {
				fdSave.setVisible(true);
				if (fdSave.getFile() == null) {
					return;
				}
				filename = fdSave.getDirectory()+fdSave.getFile();
				salvar = "Texto";
				tb1.setTitleAt(0, fdSave.getFile());
				FileWriter out = new FileWriter(filename);
				out.write(ta1.getText());
				out.close();
			}
			else if(salvar2 == null && tb1.getSelectedIndex() == 1) {
				fdSave2.setVisible(true);
				if (fdSave2.getFile() == null) {
					return;
				}
				filename2 = fdSave2.getDirectory()+fdSave2.getFile();
				salvar2 = "Texto";
				tb1.setTitleAt(1, fdSave2.getFile());
				FileWriter out = new FileWriter(filename2);
				out.write(ta2.getText());
				out.close();
			}
			else if(salvar3 == null && tb1.getSelectedIndex() == 2) {
				fdSave3.setVisible(true);
				if (fdSave3.getFile() == null) {
					return;
				}
				filename3 = fdSave3.getDirectory()+fdSave3.getFile();
				salvar3 = "Texto";
				tb1.setTitleAt(2, fdSave3.getFile());
				FileWriter out = new FileWriter(filename3);
				out.write(ta3.getText());
				out.close();
			}
			else if(salvar4 == null && tb1.getSelectedIndex() == 3) {
				fdSave4.setVisible(true);
				if (fdSave.getFile() == null) {
					return;
				}
				filename4 = fdSave4.getDirectory()+fdSave4.getFile();
				salvar4 = "Texto";
				tb1.setTitleAt(3, fdSave4.getFile());
				FileWriter out = new FileWriter(filename4);
				out.write(ta4.getText());
				out.close();
			}
			else {
				try {
					if(tb1.getSelectedIndex() == 0) {
						FileWriter out = new FileWriter(filename);
						out.write(ta1.getText());
						out.close();
					}
					else if(tb1.getSelectedIndex() == 1) {
						FileWriter out = new FileWriter(filename2);
						out.write(ta2.getText());
						out.close();
					}
					else if(tb1.getSelectedIndex() == 2) {
						FileWriter out = new FileWriter(filename3);
						out.write(ta3.getText());
						out.close();
					}
					else if(tb1.getSelectedIndex() == 3) {
						FileWriter out = new FileWriter(filename4);
						out.write(ta4.getText());
						out.close();
					}else {
						JOptionPane.showMessageDialog(tb1, "Selecione uma aba");
					}
				}catch(IOException erra) {
					JOptionPane.showMessageDialog(null,"Erro ao salvar arquivo "+erra.toString());					
				}
			}
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao salvar arquivo "+erro.toString());
		}	
	}

	public void salvaComo() {
		try {
			if(tb1.getSelectedIndex() == 0) {
				fdSave.setVisible(true);
				if (fdSave.getFile() == null) {
					return;
				}
				filename = fdSave.getDirectory()+fdSave.getFile();
				salvar = "Texto";
				tb1.setTitleAt(0, fdSave.getFile());
				FileWriter out = new FileWriter(filename);
				out.write(ta1.getText());
				out.close();
			}
			else if(tb1.getSelectedIndex() == 1) {
				fdSave2.setVisible(true);
				if (fdSave2.getFile() == null) {
					return;
				}
				filename2 = fdSave2.getDirectory()+fdSave2.getFile();
				salvar2 = "Texto";
				tb1.setTitleAt(1, fdSave2.getFile());
				FileWriter out = new FileWriter(filename2);
				out.write(ta1.getText());
				out.close();
			}
			else if(tb1.getSelectedIndex() == 2) {
				fdSave3.setVisible(true);
				if (fdSave3.getFile() == null) {
					return;
				}
				filename3 = fdSave3.getDirectory()+fdSave3.getFile();
				salvar3 = "Texto";
				tb1.setTitleAt(2, fdSave3.getFile());
				FileWriter out = new FileWriter(filename3);
				out.write(ta1.getText());
				out.close();
			}
			else if(tb1.getSelectedIndex() == 3) {
				fdSave4.setVisible(true);
				if (fdSave4.getFile() == null) {
					return;
				}
				filename4 = fdSave4.getDirectory()+fdSave4.getFile();
				salvar4 = "Texto";
				tb1.setTitleAt(3, fdSave4.getFile());
				FileWriter out = new FileWriter(filename4);
				out.write(ta1.getText());
				out.close();
			}else {
				JOptionPane.showMessageDialog(tb1, "Selecione uma aba");
			}
		} catch(IOException erro) {
			JOptionPane.showMessageDialog(null,"Erro ao salvar arquivo "+erro.toString());
		}	
	}
	
	public void abre() {
		int a = 0;
		if (tb1.getSelectedIndex() == 0) {
			if(!ta1.getText().equals("")) {
				a  = JOptionPane.showConfirmDialog(pn1,"Salvar arquivo criado?");
				if(a == 0) {
					salva();
				}
			}	
		}else if (tb1.getSelectedIndex() == 1) {
			if(!ta2.getText().equals("")) {
				a  = JOptionPane.showConfirmDialog(pn1,"Salvar arquivo criado?");
				if(a == 0) {
					salva();
				}
			}	
		}else if (tb1.getSelectedIndex() == 2) {
			if(!ta3.getText().equals("")) {
				a  = JOptionPane.showConfirmDialog(pn1,"Salvar arquivo criado?");
				if(a == 0) {
					salva();
				}
			}	
		}else if (tb1.getSelectedIndex() == 3) {
			if(!ta4.getText().equals("")) {
				a  = JOptionPane.showConfirmDialog(pn1,"Salvar arquivo criado?");
				if(a == 0) {
					salva();
				}
			}	
		}else {
			JOptionPane.showMessageDialog(pn1,"Selecione uma aba");
		}
		
		if(a != 2) {
			try {
				fdOpen.setVisible(true);
				if (fdOpen.getFile() == null) {
					if(tb1.getSelectedIndex() == 0) {
						salvar = "Texto";
					}
					else if(tb1.getSelectedIndex() == 1) {
						salvar2 = "Texto";
					}
					else if(tb1.getSelectedIndex() == 2) {
						salvar3 = "Texto";
					}
					else if(tb1.getSelectedIndex() == 3) {
						salvar4 = "Texto";
					}
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
				if (tb1.getSelectedIndex() == 0) {
					tb1.setTitleAt(0, fdOpen.getFile());
					ta1.setText(s);	
					salvar = null;
				}else if (tb1.getSelectedIndex() == 1) {
					tb1.setTitleAt(1, fdOpen.getFile());
					ta2.setText(s);
					salvar2 = null;
				}else if (tb1.getSelectedIndex() == 2) {
					tb1.setTitleAt(2, fdOpen.getFile());
					ta3.setText(s);
					salvar3 = null;
				}else if (tb1.getSelectedIndex() == 3) {
					tb1.setTitleAt(3, fdOpen.getFile());
					ta4.setText(s);
					salvar4 = null;
				}else {
					JOptionPane.showMessageDialog(pn1,"Selecione uma aba");
				}
				in.close();
				
			} catch(IOException erro) {
				JOptionPane.showMessageDialog(null,"Erro ao abrir arquivo "+erro.toString());
			}
		}
	}


	private String html = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "<head> \n"
			+ "<link rel=\"stylesheet\" href=\"CSS/styles.css\"> \n"
			+ "<title> "
			+ "</title> \n"
			+ "</head> \n"
			+ "<body> \n"
			+ "</body> \n"
			+ "</html> \n";

	private String Basic = "<!DOCTYPE html> \n"
			+ "<html> \n"
			+ "	<head> \n"
			+ "	<link rel=\"stylesheet\" href=\"CSS/styles.css\"> \n"
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
			+ "	<link rel=\"stylesheet\" href=\"CSS/styles.css\"> \n"
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
			+ "	<link rel=\"stylesheet\" href=\"CSS/styles.css\"> \n"
			+ "	<title></title> \n"
			+ "	</head> \n"
			+ "	<body> \n"
			+ "		<p>Eu sou um parágrafo</p> \n"
			+ "	</body> \n"
			+ "</html>";
	
	public class Delay extends Thread{
		public void run() {
			try{
				Thread.sleep(4000);
				lbspawn.setVisible(false);
				tb1.setVisible(true);
			}catch(Exception ste) {
			}
		}
	}
}