package Telas;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

public class TelaPreVisualizacao extends JInternalFrame implements MouseListener, KeyListener{

	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

	
	
	public void pesquisar_turma() {
		String sql = "select * from tbturma where nome like ?";
		
		try {
			pst = conexao.prepareStatement(sql);
			//passando o conteudo da caixa de pesquisa para ?
			// atencao ao %  - continuacao da string sql
			pst.setString(1, t_pesquisar.getText() + "%");
			rs = pst.executeQuery();
			// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
			
			tabela.setModel(DbUtils.resultSetToTableModel(rs));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
	}
	
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	
	public void sett_campos() {
		TelaAluno ta = new TelaAluno();
		int nr = tabela.getSelectedRow();
		//t_numero.setText(tabela.getModel().getValueAt(nr, 1).toString());
		System.out.println(tabela.getModel().getValueAt(nr, 1).toString());
	//	ta.t_turma.setText(tabela.getModel().getValueAt(nr, 1).toString());

		
	}
	
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

	private JPanel contentPane;
   JPanel painel,painel1;
   JLabel l_titulo,l_pesquisar, l_nralunos;
   JTextField t_pesquisar,t_numero;
   JTable tabela;
   JScrollPane js;
   Choice c_filtrar;
   Font f1,f2,f3,f31,f4,f5,f6;
	
	
	public TelaPreVisualizacao() {
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 450, 300);
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//contentPane.setLayout(new BorderLayout(0, 0));
		//setContentPane(contentPane);
		
	    //setUndecorated(true);
        setSize(749,220);
		setVisible(false);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	   
		setResizable(false);
		setLayout(null);
		
		//setLocationRelativeTo(null);
		setLocation(10, 180);
		setTitle("Lista das Turmas");
		setFont(new Font("Nunito", Font.CENTER_BASELINE, 14));
		//setLocation(0, -20);
		setClosable(true);
		//setMaximizable(true);
		setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(66,66,66)));
		setFrameIcon(new ImageIcon(TelaAluno.class.getResource("/imgs/add.png")));
		//setIconifiable(true);
	
		BasicInternalFrameUI basic = (javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI();
		for (MouseListener listener:basic.getNorthPane().getMouseListeners()) {
			basic.getNorthPane().removeMouseListener(listener);
		}
		
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.CENTER_BASELINE, 14);
		f31 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 16);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 748, 220);
	    painel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    painel.setLayout(null);
	    
	    c_filtrar = new Choice();
	    c_filtrar.setBounds(10,5,150,25);
	    c_filtrar.setFont(f6);
	    c_filtrar.setForeground(new Color(100,100,100));
	    c_filtrar.addItem("");
	    c_filtrar.addMouseListener(this);
	    painel.add(c_filtrar);
	    
	    l_pesquisar = new JLabel("        pesquisar");
	    l_pesquisar.setBounds(340,5,100,20);
	    l_pesquisar.setFont(f5);
	    l_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_pesquisar.setForeground(new Color(0,0,0));
	    painel.add(l_pesquisar);
	    
	    l_nralunos = new JLabel("       Nr de Alunos  ");
	    l_nralunos.setBounds(530,120,120,20);
	    l_nralunos.setFont(f5);
	    l_nralunos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(150,150,150)));
	    //l_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_nralunos.setForeground(new Color(0,0,0));
	   // painel.add(l_nralunos);
	    
	    
	    t_pesquisar = new JTextField();
	    t_pesquisar.setBounds(450,5,271,20);
	    t_pesquisar.setFont(f5);
	    t_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar.setForeground(new Color(0,0,0));
	    t_pesquisar.addKeyListener(this);
	    t_pesquisar.addMouseListener(this);
	    painel.add(t_pesquisar);
	    
	    t_numero = new JTextField();
	    t_numero.setBounds(660,120,60,20);
	    t_numero.setFont(f5);
	    t_numero.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_numero.setForeground(new Color(0,0,0));
	    t_numero.addKeyListener(this);
	    t_numero.addMouseListener(this);
	    //painel.add(t_numero);
	    
	    
		
	    String [] nome = {"Cod","Nome ", "Horario","DataRegisto","NrAlunos","Instrutor"};
	    tabela = new JTable();
		tabela.setFont(f5);
		DefaultTableModel dtml = new DefaultTableModel(nome, 0);	
		tabela.setModel(dtml);
		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		tabela.setBackground(Color.white);
		tabela.getTableHeader().setFont(f5);
		tabela.setOpaque(false);
		tabela.getTableHeader().setBackground(new Color(239,239,239));
		tabela.addMouseListener(this);
	    js = new JScrollPane(tabela);
		js.setBackground(Color.WHITE);
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		///js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js.setBounds(11, 30, 730, 130);
		painel.add(js);
	    
	    
		getContentPane().add(painel);
		
		
		conexao = modulo.ModuloConexao.conector();
		
	}

	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPreVisualizacao frame = new TelaPreVisualizacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


//TelaAluno ta = new TelaAluno();
	
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == tabela) {
		//	ta.sett_camposturma();
			
		}
		
	}



	
	public void mousePressed(MouseEvent e) {
		
		
	}



	
	public void mouseReleased(MouseEvent e) {
	
		
	}



	
	public void mouseEntered(MouseEvent e) {
		
		
	}



	
	public void mouseExited(MouseEvent e) {
		
		
	}




	public void keyTyped(KeyEvent e) {
	
		
	}



	
	public void keyPressed(KeyEvent e) {
	
		
	}



	
	public void keyReleased(KeyEvent e) {
	
		if (e.getSource() == t_pesquisar) {
			pesquisar_turma();
		}
		
	}
	
	

}
