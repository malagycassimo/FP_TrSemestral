package Telas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Others.RoundedBorder;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaPrincipal extends JFrame implements MouseListener, MouseMotionListener, ActionListener{
	
	Connection conexao = null;
	PreparedStatement pst = null; 
	ResultSet rs = null;
	
//::::::::::::::::::::::::::::::::::::::::::::::::::::::: relatorio estudante :::::::::::::::::::::::::::::::::::::::::::::::::::::		
	public void gerarrelatorio() {
		
		int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório ?","  Atenção ", JOptionPane.YES_NO_OPTION);
	 if (confirmar == JOptionPane.YES_OPTION) {
		try {
			//conexao = modulo.ModuloConexao.conector();
			HashMap parametro = new HashMap();
	 		JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Alunos.jasper", null,conexao);
			JasperViewer jw = new JasperViewer(jp,false);
			jw.setVisible(true);
			
			//JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Pagamentos.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	 }
		
	}
//::::::::::::::::::::::::::::::::::::::::::::::::::::::: relatorio funcionario :::::::::::::::::::::::::::::::::::::::::::::::::::	
	public void gerarrelatorio1() {
			int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório ?","  Atenção ", JOptionPane.YES_NO_OPTION);
			 if (confirmar == JOptionPane.YES_OPTION) {
			
			try {
				//conexao = modulo.ModuloConexao.conector();
				HashMap parametro = new HashMap();
		 		JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Turmas.jasper", null,conexao);
				JasperViewer jw = new JasperViewer(jp,false);
				jw.setVisible(true);
				
				//JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Pagamentos.pdf");
				
			} catch (JRException e) {
				e.printStackTrace();
			}
			 }
		}
//::::::::::::::::::::::::::::::::::::::::::::::::::::::: relatorio turma :::::::::::::::::::::::::::::::::::::::::::::::::::::::::	
	public void gerarrelatorio2() {
			
			int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a emissão deste relatório ?","  Atenção ", JOptionPane.YES_NO_OPTION);
			 if (confirmar == JOptionPane.YES_OPTION) {
			
			try {
				//conexao = modulo.ModuloConexao.conector();
				HashMap parametro = new HashMap();
		 		JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Funcionarios.jasper", null,conexao);
				JasperViewer jw = new JasperViewer(jp,false);
				jw.setVisible(true);
				
				//JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Pagamentos.pdf");
				
			} catch (JRException e) {
				e.printStackTrace();
			}
			 }
		}			
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
//:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		
	
	   JPanel painel,painel1,painel2,painel3;
	   Font f1,f2,f3,f31,f4,f5,f6,f7,f8;
	   JButton btn_user, btn_escola;
	   public  JLabel l_img,l_hora,l_texto,l_texto1,l_texto2,l_texto3,l_texto4,l_data,test,l_imga,l_imgma,l_imgh,l_imgpa,l_imgfu,btn_sair,btn_min,l_outros;
	   JDesktopPane desk;
	   JMenuBar menubar,menubar1;
	   JMenu menu,menu1;
	   JMenuItem menuitem1,menuitem2,menuitem3,menuitem11,menuitem12,menuitem13;
	   JTable tabela;
	   JScrollPane js;
	   

	   
	   /*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	public TelaPrincipal() {

		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		setLayout(null);
		setSize(870,470);
		setLocationRelativeTo(null);	
		
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.PLAIN, 12);
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Segoe UI Semibold", Font.PLAIN, 10);
		f8 = new Font("Segoe UI Semibold", Font.PLAIN, 12);
		f6 = new Font("Segoe UI Semibold", Font.PLAIN, 14);
		f7 = new Font("Bahnschrift", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 870, 470);
	    painel.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(62,164,195)));
	    painel.setLayout(null);
	    
	    painel1 = new JPanel();
	    //painel1.setBackground(new Color(65,65,66));
	    painel1.setBackground(new Color(245,245,245));
	    painel1.setBounds(1, 20, 100, 430);
	    //painel1.setBorder(new RoundedBorder(20));
	    painel1.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(224,224,224)));
	    painel1.setLayout(null);
	    
	    painel2 = new JPanel();
	    painel2.setBackground(new Color(232,229,229));
	    painel2.setBounds(1, 443, 868,26);
	    //painel2.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(48,200,234)));
	    painel2.setLayout(null); 
	
	    painel3 = new JPanel();
	    painel3.setBackground(new Color(62,62,62));
	    painel3.setBounds(1, 1, 869,26);
	   // painel3.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, new Color(232,229,229)));
	    painel3.setLayout(null);
	    
	    desk = new JDesktopPane();
	    desk.setBackground(new Color(0,250,250));
	    desk.setBounds(101, 27, 768,429);
	    desk.setLayout(null); 
	    
	    
	    menubar = new JMenuBar();
	    menubar.setBounds(767,1,102,25);
	    menubar.setBackground(new Color(62,62,62));
	    menubar.setBorder(null);
	    menubar.setOpaque(true);


	    menu = new JMenu("    Opções            ");
	    menu.setForeground(new Color(220,220,220));
	    menu.setBackground(new Color(255,255,255));
	    menu.setFont(f8);
	    menu.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/menu1.png")));
	    
	    menuitem1 = new JMenuItem("  Usuario   ");
	    menuitem1.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/user.png")));
	    menuitem1.addActionListener(this);
	    menuitem1.setFont(f3);
	    
	    menuitem2 = new JMenuItem("  Escola   ");
	    menuitem2.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/esc.png")));
	    menuitem2.addActionListener(this);
	    menuitem2.setFont(f3);
	    
	    menuitem3 = new JMenuItem("  Encerrar   ");
	    menuitem3.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/exit.png")));
	    menuitem3.addActionListener(this);
	    menuitem3.setFont(f3);
	    
	    menu.add(menuitem1);
	    menu.add(menuitem2);
	    menu.add(menuitem3); 
	    
	    menubar.add(menu);
	    painel.add(menubar);
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    menubar1 = new JMenuBar();
	    menubar1.setBounds(652,1,120,25);
	    menubar1.setBackground(new Color(62,62,62));
	    menubar1.setBorder(null);
	    menubar1.setOpaque(true);
	
	    
	    menu1 = new JMenu("   Relatorios      ");
	    menu1.setForeground(new Color(220,220,220));
	    menu1.setBackground(new Color(255,255,255));
	    menu1.setFont(f8);
	    menu1.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/report.png")));  
	   // menu1.setVisible(false);
	       
	    menuitem11 = new JMenuItem(" Alunos   ");
	    menuitem11.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/alunorel.png")));
	    menuitem11.addActionListener(this);
	    menuitem11.setFont(f3);
	    
	    menuitem12 = new JMenuItem(" Turmas  ");
	    menuitem12.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/turmarel.png")));
	    menuitem12.addActionListener(this);
	    menuitem12.setFont(f3);
	    
	    menuitem13 = new JMenuItem(" Funcionarios");
	    menuitem13.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/funcionariorel.png")));
	    menuitem13.addActionListener(this);
	    menuitem13.setFont(f3);

	    menu1.add(menuitem11);
	    menu1.add(menuitem12);
	    menu1.add(menuitem13);
	   
	    menubar1.add(menu1);
        painel.add(menubar1);
        
        
        
        
		
	    btn_sair = new JLabel("   X ");
		btn_sair.setBounds(838, 3, 33, 20);
		btn_sair.setForeground(new Color(255,255,255));
		btn_sair.setBackground(Color.white);
		//btn_sair.setContentAreaFilled(false);
		btn_sair.setToolTipText("Sair");
		btn_sair.setFont(f3);
	    btn_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		btn_sair.setOpaque(false);
		btn_sair.addMouseListener(this);
		btn_sair.addMouseMotionListener(this);
		//painel.add(btn_sair);
		
		
		btn_user = new JButton("Usuarios");
		btn_sair.setBounds(838, 3, 33, 20);
		btn_sair.setForeground(new Color(255,255,255));
		btn_sair.setBackground(Color.white);
		//btn_sair.setContentAreaFilled(false);
		btn_sair.setToolTipText("Sair");
		btn_sair.setFont(f3);
	    btn_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		btn_sair.setOpaque(false);
		btn_sair.addMouseListener(this);
		btn_sair.addMouseMotionListener(this);
		//painel.add(btn_sair);
		//painel.add(painel2);
		
		
		
		l_outros = new JLabel();
		l_outros.setBounds(845, 4, 60, 20);
		l_outros.setForeground(new Color(255,255,255));
		l_outros.setBackground(Color.white);
		//btn_sair.setContentAreaFilled(false);
		l_outros.setToolTipText("Sair");
		l_outros.setFont(f31);
		l_outros.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		l_outros.setOpaque(false);
		l_outros.addMouseListener(this);
		l_outros.addMouseMotionListener(this);
		l_outros.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/imgs/menu.png")));
		//painel.add(l_outros);
		//painel.add(btn_min);
		//painel.add(painel2);
		

	    l_hora = new JLabel("");
		l_hora.setBounds(10,3,100,20);
		l_hora.setFont(f3);
		l_hora.setForeground(new Color(240,240,240));
		painel3.add(l_hora);
		
		Date data = new Date();
		DateFormat formato =  DateFormat.getDateInstance(DateFormat.SHORT);
		
	    class hora implements ActionListener { 
            public void actionPerformed(ActionEvent e) {
            	Calendar now = Calendar.getInstance();
             l_hora.setText(String.format("%1$tH : %1$tM : %1$tS", now));
            
            	
            }
        }
	    
	    
	    Timer timer = new Timer(1000, new hora());
	     timer.start();
	     
	     Calendar now = Calendar.getInstance();
	    
	     
	     l_texto = new JLabel();
	     l_texto.setText("@"+ now.getWeekYear()+" * Maputo-Moçambique ");
	     l_texto.setBounds(2,441,250,30);
	     l_texto.setFont(f5);
	     l_texto.setForeground(new Color(157,157,157));
	     painel.add(l_texto);
	     
	   
	 
	    //System.out.println(tl.perfil);
	     l_texto1 = new JLabel();
	  //   l_texto1.setText("" + tl.perfil);
	     l_texto1.setBounds(270,436,250,40);
	     l_texto1.setFont(f5);
	     l_texto1.setForeground(new Color(157,157,157));
	     painel.add(l_texto1);
	     
	     l_texto2 = new JLabel();
	    // l_texto2.setText("Perfil: ");
	     l_texto2.setBounds(550,436,250,40);
	     l_texto2.setFont(f5);
	     l_texto2.setForeground(new Color(157,157,157)); 
	     painel.add(l_texto2);
	     
	     l_texto3 = new JLabel("");
	     //l_texto2.setText("Perfil: ");
	     l_texto3.setBounds(100,3,1000,20);
	     l_texto3.setFont(f31);
	     l_texto3.setForeground(new Color(200,200,200));
	    // painel.add(l_texto3);
	     
	     l_texto4 = new JLabel(""); 
	     //l_texto2.setText("Perfil: ");
	     l_texto4.setBounds(100,3,1000,20);
	     l_texto4.setFont(f31);
	     l_texto4.setForeground(new Color(200,200,200));
	    // painel.add(l_texto3);
	    
	     
	     
	     l_data = new JLabel();
	     l_data.setText(now.getInstance().getTime().getDate()+" de "+ 5 +" de "+ now.getInstance().getWeekYear());
	     l_data.setBounds(780,436,250,40);
	     l_data.setFont(f5);
	     l_data.setForeground(new Color(157,157,157));
	     painel.add(l_data);
	     
	     
	        l_imgh = new JLabel();
		    l_imgh.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/Homep.png")));
		    l_imgh.setBounds(7, 35, 88, 75);
		    l_imgh.setBackground(Color.white);
		    l_imgh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imgh.addMouseListener(this); 
		    l_imgh.setToolTipText("Home");
		    l_imgh.setLayout(null);
		    painel.add(l_imgh);
        
		    l_imga = new JLabel();
		    l_imga.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/alunop.png")));
		    l_imga.setBounds(7, 115, 88, 75);
		    l_imga.setBackground(Color.white);
		    l_imga.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imga.addMouseListener(this);
		    l_imga.setLayout(null);
		    l_imga.setToolTipText("Estudantes");
		    painel.add(l_imga);
		    
		    l_imgma = new JLabel();
		    l_imgma.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/turmapp.png")));
		    l_imgma.setBounds(7, 195, 88, 75);
		    l_imgma.setBackground(Color.white);
		    l_imgma.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imgma.addMouseListener(this);
		    l_imgma.setToolTipText("Turmas");
		    l_imgma.setLayout(null);
		    painel.add(l_imgma);
		    
		    l_imgpa = new JLabel();
		    l_imgpa.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/payp.png")));
		    l_imgpa.setBounds(7, 275, 88, 75);
		    l_imgpa.setBackground(Color.white);
		    l_imgpa.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imgpa.addMouseListener(this);
		    l_imgpa.setLayout(null);
		    l_imgpa.setToolTipText("Pagamentos");
		    painel.add(l_imgpa);
		    
		    l_imgfu = new JLabel();
		    l_imgfu.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/funcionariop.png")));
		    l_imgfu.setBounds(7, 355, 88, 75);
		    l_imgfu.setBackground(Color.white);
		    l_imgfu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imgfu.addMouseListener(this);
		    l_imgfu.setToolTipText("Funcionarios");
		    l_imgfu.setLayout(null);
		    painel.add(l_imgfu);
	     
		 l_img = new JLabel("");
	     l_img.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/deskb1.png")));
 	     l_img.setBounds(0, 0, 768,448);
 	     l_img.setLayout(null);
         //l_img.setBorder(new LineBorder(Color.lightGray));
 	     desk.add(l_img);
	     
// 	  	
// 		String [] nome2 = {"cod","nome","iduser","eadd","erem", "eupd","evis","fadd","frem","fupd","fvis","tadd","trem","tupd","tvis","padd","prem","pupd","pvis"};
//
// 		tabela = new JTable();
// 		tabela.setFont(f5);
// 		DefaultTableModel dtml2 = new DefaultTableModel(nome2, 0);
// 		
// 		tabela.setModel(dtml2);
// 		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(249,249,249)));
// 		tabela.setBackground(new Color(245,245,245));
// 		tabela.getTableHeader().setFont(f5);
// 		tabela.setOpaque(false);
// 		tabela.addMouseListener(this);
// 		tabela.getTableHeader().setBackground(new Color(239,239,239));
// 	
// 	    js = new JScrollPane(tabela);
// 		js.setBackground(new Color(249,249,249));
// 		js.setOpaque(false);
// 		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
// 		js.setBounds(110, 160, 600, 88);
	//	painel.add(js);
 		
 		
		 
		 
		 painel.add(painel3); 
		 painel.add(painel2);
		 painel.add(painel1);   
 	     painel.add(desk);
	     
	  //  desk.add(l_img);	
	    getContentPane().add(painel);
	    
	    conexao = modulo.ModuloConexao.conector();
		
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	TelaAluno ta = new TelaAluno();
	TelaFuncionario tf = new TelaFuncionario();
	TelaTurma tt = new TelaTurma();
	TelaPagamentos tp = new TelaPagamentos();
	TelaUsuario tu = new TelaUsuario();
	TelaEscola te = new TelaEscola();
	TelaPreVisualizacao tpv = new TelaPreVisualizacao(); 
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal tp1 = new TelaPrincipal();
					//tp1.pesquisar_usuariop();
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});	
		
	}

	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void mouseClicked(MouseEvent e) {
		
		if (e.getSource() == l_imgh) {
		
			desk.remove(l_img);
			desk.remove(ta);
			desk.remove(tt);
			desk.remove(te);
			desk.remove(tp);
			desk.remove(tu);
			desk.remove(tf);
			desk.add(l_img);
			
		} else
		
		if (e.getSource() == l_imga) {
			
			if (l_imga.isEnabled()) {
				
			desk.remove(ta);
			desk.remove(tt);
			desk.remove(te);
			desk.remove(tp);
			desk.remove(tu);
			desk.remove(tf);
			desk.remove(l_img);
			
			
			ta.setVisible(true);
			ta.addcomboboxcarta();
			ta.pesquisar_aluno();
			//ta.enablebtn();
			ta.addcomboboxhorariot();
			ta.addcomboboxnomet();
			ta.t_funcionario.setText(l_texto1.getText());
			ta.t_idusuario.setText(l_texto3.getText());
			ta.ativarrestricao(l_texto3.getText());
			desk.add(ta);
			
			}else {
				
				JOptionPane.showMessageDialog(null, "Acesso negado !!");
			}
			
		
		} else
		
		if (e.getSource() == l_imgma) {
		
			if (l_imgma.isEnabled()) {
				
			
			desk.remove(tt);
			desk.remove(ta);
			desk.remove(te);
			desk.remove(tp);
			desk.remove(tu);
			desk.remove(tf);
			desk.remove(l_img);
			
			tt.setVisible(true);
			tt.addcombobox();
			tt.addcomboboxfiltrar();
			tt.pesquisar_turma();
			//tt.enablebtn();
			tt.t_idusuario.setText(l_texto3.getText());
			tt.ativarrestricao(l_texto3.getText());
			tpv.pesquisar_turma();
			desk.add(tt);
			
			}else {
				JOptionPane.showMessageDialog(null, "Acesso negado !!");
			}
		} else
		
		if (e.getSource() == l_imgpa) {
			
		
			if (l_imgpa.isEnabled()) {
			
			
			desk.remove(tp);
			desk.remove(tt);
			desk.remove(te);
			desk.remove(ta);
			desk.remove(tu);
			desk.remove(tf);
			desk.remove(l_img);
			
			tp.setVisible(true);
			tp.addcomboboxmeio();
			tp.addcomboboxtipo();
			///tp.enablebtn();
			tp.pesquisar_aluno();
	        tp.pesquisar_pagamento();
	        tp.t_funcionariop.setText(l_texto1.getText());
	        tp.t_idusuario.setText(l_texto3.getText());
	        tp.ativarrestricao(l_texto3.getText());
			desk.add(tp);
			
			} else {
			   JOptionPane.showMessageDialog(null, "Acesso negado !!");	
			}
			
		} else
		 
		if (e.getSource() == l_imgfu) {
			
			if (l_imgfu.isEnabled()) {
			
			desk.remove(tf);
			desk.remove(tt);
			desk.remove(te);
			desk.remove(tp);
			desk.remove(tu);
			desk.remove(ta);
			desk.remove(l_img);
			
			
			tf.setVisible(true);
			tf.pesquisar_funcionario();
			tf.addcombobox();
			tf.t_idusuario.setText(l_texto3.getText());
			tf.ativarrestricao(l_texto3.getText()); 
			//tf.enablebtn();
			
			desk.add(tf);
			
			}else {
				JOptionPane.showMessageDialog(null, "Acesso negado !!");
			}
		} 
		
		
	 }  
		
	


	
	public void mousePressed(MouseEvent e) {
		
		
	}


	
	public void mouseReleased(MouseEvent e) {
		
		
		
	}


	
	public void mouseEntered(MouseEvent e) {
	
		if (e.getSource() == l_imgh) {
			l_imgh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 136, 176)));
			  l_imgh.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/homepa.png")));
		}
		
		if (e.getSource() == l_imga) {
			l_imga.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 136, 176)));
		    l_imga.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/alunopa.png")));
		}
		
		if (e.getSource() == l_imgma) {
			l_imgma.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 136, 176)));
			 l_imgma.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/turmappa.png")));
		}
		
		if (e.getSource() == l_imgpa) {
			l_imgpa.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 136, 176)));
			 l_imgpa.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/paypa.png")));
		}
		
		if (e.getSource() == l_imgfu) {
			l_imgfu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(36, 136, 176)));
			  l_imgfu.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/funcionariopa.png")));
		}
		
		
	}


	
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == l_imgh) {
			l_imgh.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
			l_imgh.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/homep.png")));
		}
		
		if (e.getSource() == l_imga) {
			l_imga.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
		    l_imga.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/alunop.png")));
		}
		
		if (e.getSource() == l_imgma) {
			l_imgma.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
			   l_imgma.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/turmapp.png")));
		}
		
		if (e.getSource() == l_imgpa) {
			l_imgpa.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
			 l_imgpa.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/payp.png")));
		}
		
		if (e.getSource() == l_imgfu) {
			l_imgfu.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200, 200, 200)));
			  l_imgfu.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/funcionariop.png")));
		}
	}

	
	public void mouseDragged(MouseEvent e) {
		
		
	}

	
	public void mouseMoved(MouseEvent e) {
		
		
	}

	
	public void actionPerformed(ActionEvent e) {

       
      
      if (e.getSource() == menuitem3) {
			int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar saida?","   Mensagem", JOptionPane.YES_NO_OPTION);
			   if (confirmar == JOptionPane.YES_OPTION) {
			//	System.exit(0);
				   this.dispose();
				  Tela_Login tl = new Tela_Login();
				  tl.setVisible(true);
			}
		}
	

      if (e.getSource() == menuitem1) {
			
    	    desk.remove(tu);
    	    desk.remove(l_img);
			desk.remove(tt);
			desk.remove(te);
			desk.remove(tp);
			desk.remove(ta);
			desk.remove(tf);
			
			tu.setVisible(true);
			tu.addcombobox();
			tu.enablebtn();
			//tu.bloquearuser();
			tu.pesquisar_usuario();
			tu.pesquisar_usuarioprivilegio();
		//	tu.pesquisar_nomecodigo();
			//tu.pesquisar_bloqueio();
			//tu.settchackbox();
			desk.add(tu);
			
			if (tu.eadd.isSelected()) {
				ta.btn_atualizar.setEnabled(false);
				ta.btn_registar.setEnabled(false);
			    l_imgfu.setEnabled(false);
			}
			
		} else
		
		
      if (e.getSource() == menuitem2) {
			
			desk.remove(te);
  	        desk.remove(l_img);
			desk.remove(tt);
			desk.remove(ta);
			desk.remove(tp);
			desk.remove(tu);
			desk.remove(tf);
			
			te.setVisible(true);
			te.pesquisar_escola();
			te.enablebtn();
			te.addcomboboxcargo();
			te.addcomboboxclasse();
			te.addcomboboxmeio();
			te.addcomboboxtipop();
			desk.add(te);
		} 
		 
      if (e.getSource() == menuitem11) {
			gerarrelatorio();
		} 
      if (e.getSource() == menuitem12) {
			gerarrelatorio1();
		} 
      if (e.getSource() == menuitem13) {
			gerarrelatorio2();
		} 
      
//         if (e.getSource() == menuitem13) {
//			   
//      		try {
//				JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\funcionarios.jasper", null,conexao);
//                JasperViewer.viewReport(jp,false);			
//      		} catch (JRException e1) {
//				e1.printStackTrace();
//			}
//
//        	 
//		}
      
      
	}
	
	
	
}
