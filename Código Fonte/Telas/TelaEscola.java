package Telas;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.proteanit.sql.DbUtils;

public class TelaEscola extends JInternalFrame implements MouseListener,MouseMotionListener,ActionListener {
	

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	

	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
public void pesquisar_escola() {
	String sql = "select * from tbescola where cod = 1";

	try {
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		tabela.setModel(DbUtils.resultSetToTableModel(rs));
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}
	
}
 


/*''''''''''''''''''''''''''''''''' Metodo para preencher os campos ao clicar na tabela '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

private void limparcampos() {
	
	t_nome.setText(null);
	t_nuit.setText(null);
	t_location.setText(null);
	t_email.setText(null);
	c_naturalidade.select("");
	t_contacto.setText(null);
	t_contacto1.setText(null);
	t_foto.setText(null);
	 l_logo.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
	 
	 enablebtn();

}


	
/*''''''''''''''''''''''''''''''''' Metodo para preencher os campos ao clicar na tabela '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void sett_campos() {
		
		
			
		t_nome.setText(tabela.getModel().getValueAt(0, 1).toString());
		t_nuit.setText(tabela.getModel().getValueAt(0, 2).toString());
		t_location.setText(tabela.getModel().getValueAt(0, 3).toString());
		t_url.setText(tabela.getModel().getValueAt(0, 4).toString());
		t_email.setText(tabela.getModel().getValueAt(0, 5).toString());
		c_naturalidade.select(tabela.getModel().getValueAt(0, 6).toString());
		t_contacto.setText(tabela.getModel().getValueAt(0, 7).toString());
		t_contacto1.setText(tabela.getModel().getValueAt(0, 8).toString());
	//	if (t_foto.getText().isEmpty()) {
			
		//}else {
		t_foto.setText(tabela.getModel().getValueAt(0, 9).toString());
		if (t_foto.getText().isEmpty()) {
			 l_logo.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
		}else {
			l_logo.setIcon(new ImageIcon(t_foto.getText()));
		}
		
		//}
	    
		enablebtn(); 
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo btn '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void enablebtn() {
		
		if (t_nome.getText().isEmpty()||t_contacto.getText().isEmpty()) {
			btn_atualizar.setEnabled(false);
			btn_limpar.setEnabled(true);
			btn_import.setEnabled(true);
		}else {
			btn_atualizar.setEnabled(true);
			btn_limpar.setEnabled(true);
			btn_import.setEnabled(false);
		}
		
		
		
	}
	
/*''''''''''''''''''''''''''''''''''''''''''''''' Metodo Previsualizacao ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/	

	public void previsualizacao() {
		
		
		
		
	    
	    
	    
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Adicionar foto '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

	
	private void addfoto() {
		
		JFileChooser arquivo = new JFileChooser();
		arquivo.setDialogTitle("Selecione Uma Imagem");
		arquivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		int op = arquivo.showOpenDialog(this);
		if (op == JFileChooser.APPROVE_OPTION) {
			File file = new File("");
			file = arquivo.getSelectedFile();
			
		String fname = file.getAbsolutePath();
		t_foto.setText(fname);
		ImageIcon img = new ImageIcon(file.getPath());
		l_logo.setIcon(new ImageIcon(img.getImage().getScaledInstance(l_logo.getWidth() , l_logo.getHeight(), Image.SCALE_AREA_AVERAGING)));
		
		}
		
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void alterar() {
		String sql = "Update tbescola set Nome = ?, NUIT = ?, Localização = ?, URL = ?, Email = ?, Cidade = ?,  Telefone = ?, Telefone1 = ?, Logo = ? where cod = 1";
		enablebtn();
		try {
			
			
			
			if (t_nome.getText().isEmpty() || t_nuit.getText().isEmpty() ||t_location.getText().isEmpty() ||c_naturalidade.getSelectedItem().equals("") || t_contacto.getText().isEmpty() ){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos");	
			}else {
				
				pst = conexao.prepareStatement(sql);
				
				pst.setString(1, t_nome.getText());
				pst.setString(2, t_nuit.getText());
				pst.setString(3, t_location.getText());
				pst.setString(4, t_url.getText());
				pst.setString(5, t_email.getText());
				pst.setString(6, c_naturalidade.getSelectedItem());
				pst.setString(7, t_contacto.getText());
				pst.setString(8, t_contacto1.getText());
				pst.setString(9, t_foto.getText().toString());
				
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados da escola alterados!!");
				t_nome.setText(null);
				t_nuit.setText(null);
				t_location.setText(null);
				t_email.setText(null);
				c_naturalidade.select("");
				t_contacto.setText(null);
				t_contacto1.setText(null);
				t_foto.setText(null);
				 l_logo.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
				 enablebtn();
//				 conexao.close();
			}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar tipo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	/**/
	private void adicionartipo() {
		String sql = "insert into tbtipop(nome) values (?)";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_salvart.getText());
			
			if (t_salvart.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Preencha o campo!!"); }
			else { 
			
			int adicionar = pst.executeUpdate();
			System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Tipo de Pagamento adicionado!!");
				t_salvart.setText(null);
				t_salvart.setVisible(false);
				btn_st.setVisible(false);
				c_pagamento.removeAll();
				addcomboboxtipop();
//				conexao.close();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar Meio '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	/**/
	private void adicionarmeio() {
		String sql = "insert into tbmeiop(nome) values (?)";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_salvart.getText());
			
			if (t_salvart.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Preencha o campo!!"); }
			else {
			
			int adicionar = pst.executeUpdate();
			System.out.println(adicionar); 
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Meio de Pagamento adicionado!!");
				t_salvart.setText(null);
				t_salvart.setVisible(false);
				btn_st.setVisible(false);
				c_meiop.removeAll();
				addcomboboxmeio();
//				conexao.close();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar Classe '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	/**/
	private void adicionarclasse() {
		String sql = "insert into tbclassec(nome) values (?)";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_salvart.getText());
			
			if (t_salvart.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Preencha o campo!!"); }
			else {
			
			int adicionar = pst.executeUpdate();
			System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Classe de carta adicionada com sucesso!!");
				t_salvart.setText(null);
				t_salvart.setVisible(false);
				btn_st.setVisible(false);
				c_carta.removeAll();
				addcomboboxclasse();
//				conexao.close();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar Cargo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	/**/
	private void adicionarcargo() {
		String sql = "insert into tbcargos(nome) values (?)";
		
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_salvart.getText());
			
			if (t_salvart.getText().isEmpty()){ JOptionPane.showMessageDialog(null, "Preencha o campo!!"); }
			else {
			
			int adicionar = pst.executeUpdate();
			System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Cargo adicionado com sucesso!!");
				t_salvart.setText(null);
				t_salvart.setVisible(false);
				btn_st.setVisible(false);
				c_cargo.removeAll();
				addcomboboxcargo();
//				conexao.close();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover tipo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void removertipo() {
		
	int confirmar = JOptionPane.showConfirmDialog(null, "Remover tipo de pagamento?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
	   if (confirmar == JOptionPane.YES_OPTION) {
		   
		String sql = "delete from tbtipop where nome = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, c_pagamento.getSelectedItem());
			
			if (c_pagamento.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione o tipo que predende remover!");	
			}else {
				
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Tipo de pagamento removido!!");
				c_pagamento.remove(c_pagamento.getSelectedIndex());;
				c_pagamento.select(null);
//				conexao.close();
				}
			}
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}	
	
	}
	
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover tipo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void removermeio() {
		
	int confirmar = JOptionPane.showConfirmDialog(null, "Remover meio de pagamento?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
	   if (confirmar == JOptionPane.YES_OPTION) {
		   
		String sql = "delete from tbmeiop where nome = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, c_meiop.getSelectedItem());
			
			if (c_meiop.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione o meio que predende remover!");	
			}else {
				
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Meio de pagamento removido!!");
				c_meiop.remove(c_meiop.getSelectedIndex());;
				c_meiop.select(null);
//				conexao.close();
				}
			}
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}	
	
	}
	
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover tipo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void removerclasse() {
		
	int confirmar = JOptionPane.showConfirmDialog(null, "Remover Classe de carta?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
	   if (confirmar == JOptionPane.YES_OPTION) {
		   
		String sql = "delete from tbclassec where nome = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, c_carta.getSelectedItem());
			
			if (c_carta.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione a classe de carta a remover!");	
			}else {
				
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Tipo de pagamento removido!!");
				c_carta.remove(c_carta.getSelectedIndex());;
				c_carta.select(null);
//				conexao.close();
				}
			}
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}	
	
	}
	
	
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover tipo '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void removercargo() {
		
	int confirmar = JOptionPane.showConfirmDialog(null, "Remover cargo ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
	   if (confirmar == JOptionPane.YES_OPTION) {
		   
		String sql = "delete from tbcargos where nome = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, c_cargo.getSelectedItem());
			
			if (c_cargo.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione o cargo que predende remover!");	
			}else {
				
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Cargo a ser removido!!");
				c_cargo.remove(c_cargo.getSelectedIndex());;
				c_cargo.select(null);
//				conexao.close();
				}
			}
			} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}	
	
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox tipo'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
public void addcomboboxtipop() {
		
		String sql = "select nome from tbtipop";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();	
			
			c_pagamento.removeAll();
			c_pagamento.addItem("");
			
			while (rs.next()) {
			// c_pagamento.removeAll();	
			 c_pagamento.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}

/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox meio'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void addcomboboxmeio() {
		
		String sql = "select nome from tbmeiop";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();	
			
			c_meiop.removeAll();
			c_meiop.addItem("");
			
			while (rs.next()) {
			// c_pagamento.removeAll();	
			 c_meiop.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}


/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox tipo'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void addcomboboxclasse() {
		
		String sql = "select nome from tbclassec";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();	
			

			c_carta.removeAll();
			c_carta.addItem("");
			
			while (rs.next()) {
			// c_pagamento.removeAll();	
			 c_carta.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}

/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox tipo'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void addcomboboxcargo() {
		
		String sql = "select nome from tbcargos";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			c_cargo.removeAll();
			c_cargo.addItem("");
			
			while (rs.next()) {
			// c_pagamento.removeAll();	
			 c_cargo.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}
	


	




	

	
	

	JPanel painel,painel1,painel2,painel01,painel02,painel03,painel04;
	   Font f1,f2,f3,f31,f4,f5,f6;
	   JButton btn_sair,btn_confirmar,btn_atualizar,btn_registar,btn_import,btn_limpar,l_pagamento,l_mpagamento,l_carta,l_cargo,btn_st;
	   JLabel l_img,l_hora,l_nome,l_tel,l_email,l_url,l_nuit,l_location,l_logo,l_logot,l_ou,l_cidade,l_dados,imgdel1,imgdel2,imgdel3,imgdel4,imgadd1,imgadd2,imgadd3,imgadd4,l_data;
	   JTextField t_nome,t_tel,t_tel1,t_email,t_url,t_nuit,t_location, t_contacto,t_contacto1,t_salvart,t_foto;
	   Choice c_pagamento,c_meiop,c_carta,c_cargo,c_naturalidade;
	   JTable tabela;
	  JScrollPane js;

	public TelaEscola() {
	

	   
		
		setVisible(true);
	    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setSize(769,441);
		setLocation(0, -25);
		setClosable(true);
		setMaximizable(true);
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, getBackground()));
		setFrameIcon(new ImageIcon(TelaAluno.class.getResource("/imgs/add.png")));
		setIconifiable(true);
		
	
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.CENTER_BASELINE, 14);
		f31 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 14);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		
		
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 870, 530);
	    painel.setLayout(null);
	    
	    painel1 = new JPanel();
	    painel1.setBackground(new Color(249,249,249));
	    painel1.setOpaque(true);
	    painel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel1.setBounds(10, 10, 580, 165);
	    painel1.setLayout(null);
	    
	    
  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
  //''''''''''''''''''''''''''''''''''''''''''''' Parte Especial ''''''''''''''''''''''''''''''''''''''''''''''''''''
  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	    painel2 = new JPanel();
	    painel2.setBackground(new Color(249,249,249));
	    painel2.setOpaque(true);
	    painel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel2.setBounds(10, 185, 745, 235);
	    painel2.setLayout(null);
	    
	    painel01 = new JPanel();
	    painel01.setBackground(new Color(249,249,249));
	    painel01.setOpaque(true);
	    painel01.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel01.setBounds(20, 195, 355, 100);
	    painel01.setLayout(null);
	    
	    painel02 = new JPanel();
	    painel02.setBackground(new Color(249,249,249));
	    painel02.setOpaque(true);
	    painel02.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel02.setBounds(385, 195, 360, 100);
	    painel02.setLayout(null);
	    
	    painel03 = new JPanel();
	    painel03.setBackground(new Color(249,249,249));
	    painel03.setOpaque(true);
	    painel03.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel03.setBounds(20, 305, 355, 105);
	    painel03.setLayout(null);
	    
	    painel04 = new JPanel();
	    painel04.setBackground(new Color(249,249,249));
	    painel04.setOpaque(true);
	    painel04.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel04.setBounds(385, 305, 360, 105);
	    painel04.setLayout(null);
	    
	    l_pagamento = new JButton("Tipo de Pagamento");
	    l_pagamento.setBounds(20,195,355,25);
	    l_pagamento.setFont(f5);
	    l_pagamento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_pagamento.setBackground(new Color(240,240,240));
	    l_pagamento.setForeground(new Color(0,0,0));
	    l_pagamento.setEnabled(false);
	    painel.add(l_pagamento);
	    
	    l_mpagamento = new JButton("Meio de Pagamento");
	    l_mpagamento.setBounds(385,195,360,25);
	    l_mpagamento.setFont(f5);
	    l_mpagamento.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_mpagamento.setBackground(new Color(240,240,240));
	    l_mpagamento.setForeground(new Color(0,0,0));
	    l_mpagamento.setEnabled(false);
	    painel.add(l_mpagamento);
	    
	    l_carta = new JButton("Classe de Carta");
	    l_carta.setBounds(20,305,355,25);
	    l_carta.setFont(f5);
	    l_carta.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_carta.setBackground(new Color(240,240,240));
	    l_carta.setForeground(new Color(0,0,0));
	    l_carta.setEnabled(false);
	    painel.add(l_carta);
	    
	    l_cargo = new JButton("Cargos da Escola");
	    l_cargo.setBounds(385,305,360,25);
	    l_cargo.setFont(f5);
	    l_cargo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_cargo.setBackground(new Color(240,240,240));
	    l_cargo.setForeground(new Color(0,0,0));
	    l_cargo.setEnabled(false);
	    painel.add(l_cargo);
	    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    btn_st = new JButton("Regidtar");
	    btn_st.setBounds(272,262,90,21);
	    //  btn_st.setBounds(640,262,90,21);
	    //btn_st.setBounds(272,372,90,21);
	    // btn_st.setBounds(640,372,90,21);
	    btn_st.setFont(f5);
	    btn_st.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(200,200,200)));
	    btn_st.setBackground(new Color(240,240,240));
	    btn_st.setForeground(new Color(0,0,0));
	    btn_st.setVisible(false);
	    btn_st.addActionListener(this);
	   // btn_st.setEnabled(false);
	    painel.add(btn_st);
	    

	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    c_pagamento = new Choice();
	    c_pagamento.setBounds(30,230,230,18);
	    c_pagamento.setFont(f6);
	    c_pagamento.setBackground(new Color(255,255,255));
	    c_pagamento.setForeground(new Color(100,100,100));
	    c_pagamento.addItem("");
	    painel.add(c_pagamento);
	    
	    c_meiop = new Choice();
	    c_meiop.setBounds(395,230,230,18);
	    c_meiop.setFont(f6);
	    c_meiop.setBackground(new Color(255,255,255));
	    c_meiop.setForeground(new Color(100,100,100));
	    c_meiop.addItem("");
	    painel.add(c_meiop);
	    
	    c_carta = new Choice();
	    c_carta.setBounds(30,340,230,18);
	    c_carta.setFont(f6);
	    c_carta.setBackground(new Color(255,255,255));
	    c_carta.setForeground(new Color(100,100,100));
	    c_carta.addItem("");
	    painel.add(c_carta);
	    
	    c_cargo = new Choice();
	    c_cargo.setBounds(395,340,230,18);
	    c_cargo.setFont(f6);
	    c_cargo.setBackground(new Color(255,255,255));
	    c_cargo.setForeground(new Color(100,100,100));
	    c_cargo.addItem("");
	    painel.add(c_cargo);
	   
	   
	    
	    /**'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	    t_salvart = new JTextField();
	    //t_salvart.setBounds(30,262,231,21);
	   // t_salvart.setBounds(395,262,231,21);
	    //t_salvart.setBounds(30,372,231,21);  
	    t_salvart.setBounds(395,372,231,21);
	    t_salvart.setFont(f6);
	    t_salvart.setBackground(new Color(255,255,255));
	    t_salvart.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_salvart.setForeground(new Color(100,100,100));
	    t_salvart.setVisible(false);
	    painel.add(t_salvart);
	   
	    
	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    imgdel1 = new JLabel("");
	    imgdel1.setBounds(280,229,50,25);
	    imgdel1.setFont(f5);
	    imgdel1.addMouseListener(this);
	   // imgdel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgdel1.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/dell.png")));
	    imgdel1.setForeground(new Color(0,0,0));
	    painel.add(imgdel1);
	    
	    imgdel2 = new JLabel("");
	    imgdel2.setBounds(650,229,50,25);
	    imgdel2.setFont(f5);
	    imgdel2.addMouseListener(this);
	   // imgdel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgdel2.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/dell.png")));
	    imgdel2.setForeground(new Color(0,0,0));
	    painel.add(imgdel2);
	    
	    imgdel3 = new JLabel("");
	    imgdel3.setBounds(280,339,50,25);
	    imgdel3.setFont(f5);
	    imgdel3.addMouseListener(this);
	   // imgdel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgdel3.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/dell.png")));
	    imgdel3.setForeground(new Color(0,0,0));
	    painel.add(imgdel3);
	    
	    imgdel4 = new JLabel("");
	    imgdel4.setBounds(650,339,50,25);
	    imgdel4.setFont(f5);
	    imgdel4.addMouseListener(this);
	   // imgdel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgdel4.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/dell.png")));
	    imgdel4.setForeground(new Color(0,0,0));
	    painel.add(imgdel4);
	    
	   
	    imgadd1 = new JLabel("");
	    imgadd1.setBounds(325,229,50,25);
	    imgadd1.setFont(f5);
	    imgadd1.addMouseListener(this);
	   // imgadd1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgadd1.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addd1.png")));
	    imgadd1.setForeground(new Color(0,0,0));
	    painel.add(imgadd1);
	    
	    imgadd2 = new JLabel("");
	    imgadd2.setBounds(695,229,50,25);
	    imgadd2.setFont(f5);
	    imgadd2.addMouseListener(this);
	   // imgadd2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgadd2.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addd1.png")));
	    imgadd2.setForeground(new Color(0,0,0));
	    painel.add(imgadd2);
	    
	    imgadd3 = new JLabel("");
	    imgadd3.setBounds(325,339,50,25);
	    imgadd3.setFont(f5);
	    imgadd3.addMouseListener(this);
	   // imgadd3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgadd3.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addd1.png")));
	    imgadd3.setForeground(new Color(0,0,0));
	    painel.add(imgadd3);
	    
	    imgadd4 = new JLabel("");
	    imgadd4.setBounds(695,339,50,25);
	    imgadd4.setFont(f5);
	    imgadd4.addMouseListener(this);
	   // imgadd4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    imgadd4.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addd1.png")));
	    imgadd4.setForeground(new Color(0,0,0));
	    painel.add(imgadd4);
	    
	    
 //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
 //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
 //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  	  
	    
	    l_nome = new JLabel("Nome da Escola");
	    l_nome.setBounds(25,15,120,30);
	    l_nome.setFont(f5);
	    l_nome.setForeground(new Color(0,0,0));
	    painel.add(l_nome);
	    
	    l_nuit = new JLabel("NUIT");
	    l_nuit.setBounds(360,10,100,20);
	    l_nuit.setFont(f5);
	    l_nuit.setForeground(new Color(0,0,0));
	    painel1.add(l_nuit);
	   
	     l_location = new JLabel("Localização ");
	    l_location.setBounds(25,45,100,30);
	    l_location.setFont(f5);
	    l_location.setForeground(new Color(0,0,0));
	    painel.add(l_location);
	    
	    l_url = new JLabel("URL");
	    l_url.setBounds(310,45,100,30);
	    l_url.setFont(f5);
	    l_url.setForeground(new Color(0,0,0));
	    painel.add(l_url);
	    
	    l_email = new JLabel("Email");
	    l_email.setBounds(25,75,100,30);
	    l_email.setFont(f5);
	    l_email.setForeground(new Color(0,0,0));
	    painel.add(l_email);
	    
	    l_cidade = new JLabel("Cidade");
	    l_cidade.setBounds(360,75,45,30);
	    l_cidade.setFont(f5);
	    l_cidade.setForeground(new Color(0,0,0));
	    painel.add(l_cidade);

	    
	     l_tel = new JLabel("Telefone");
	    l_tel.setBounds(200,105,100,30);
	    l_tel.setFont(f5);
	    l_tel.setForeground(new Color(0,0,0));
	    painel.add(l_tel);
	    
	    l_ou = new JLabel("ou");
	    l_ou.setBounds(415,105,20,30);
	    l_ou.setFont(f5);
	    l_ou.setForeground(new Color(100,100,100));
	    painel.add(l_ou);
	    
	    
	    l_logo = new JLabel("");
	    l_logo.setBounds(600,10,155,130);
	    l_logo.setFont(f5);
	    l_logo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_logo.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
	    l_logo.setForeground(new Color(0,0,0));
	    l_logo.addMouseListener(this);
	    painel.add(l_logo);
	    
	    l_logot = new JLabel("");
	    l_logot.setBounds(600,10,155,130);
	    l_logot.setFont(f5);
	    l_logot.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_logot.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
	    l_logot.setForeground(new Color(0,0,0));
	    l_logot.addMouseListener(this);
	  //  painel.add(l_logot);
	    
	    
	    l_dados = new JLabel("@Dados da Escola!!");
	    l_dados.setBounds(15,145,155,30);
	    l_dados.setFont(f5);
	   // l_logo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_dados.setForeground(new Color(174,61,41));
	    painel.add(l_dados);
	    
	    
	    l_data = new JLabel();
	    l_data.setBounds(12,190,300,25);
	    l_data.setFont(f5);
	  //  l_msg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_msg.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_data.setForeground(new Color(174,61,41));
	    //painel.add(l_data);
	    
	    Date data = new Date();
		DateFormat formato =  DateFormat.getDateInstance(DateFormat.SHORT);
		
	    class hora implements ActionListener { 
            public void actionPerformed(ActionEvent e) {
            	Calendar now = Calendar.getInstance();
            //	l_data.setText("" +now.getInstance().getTime().getDate()+" / "+ now.getTime().getMonth() +" / "+ now.getInstance().getWeekYear());
            	l_data.setText("" + now.getTime());
            	
            }
        }
	    Timer timer = new Timer(1000, new hora());
	     timer.start();
	    
	    
	    
	    t_nome = new JTextField();
	    t_nome.setBounds(125,20,200,20);
	    t_nome.setFont(f5);
	    t_nome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nome.setForeground(new Color(100,100,100));
	    painel.add(t_nome);
	    
	     try {
			t_nuit = new JFormattedTextField(new MaskFormatter("### ### ###"));
		} catch (ParseException e2) {
			e2.printStackTrace();
		}
	    t_nuit.setBounds(410,20,170,20);
	    t_nuit.setFont(f5);
	    t_nuit.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nuit.setForeground(new Color(100,100,100));
	    painel.add(t_nuit);
	    
	    
	    t_location = new JTextField();
	    t_location.setBounds(100,50,180,20);
	    t_location.setFont(f5);
	    t_location.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_location.setForeground(new Color(100,100,100));
	    painel.add(t_location);
	    
	    t_url = new JTextField();
	    t_url.setBounds(350,50,230,20);
	    t_url.setFont(f5);
	    t_url.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_url.setForeground(new Color(100,100,100));
	    painel.add(t_url);
	    
	    t_email = new JTextField();
	    t_email.setBounds(70,80,255,20);
	    t_email.setFont(f5);
	    t_email.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_email.setForeground(new Color(100,100,100));
	    painel.add(t_email);
	    
	   
	    
	    c_naturalidade = new Choice();
	    c_naturalidade.setBounds(410,80,170,20);
	    c_naturalidade.setFont(f6);
	    c_naturalidade.setForeground(new Color(100,100,100));
	    c_naturalidade.addItem("");
	    c_naturalidade.addItem("Maputo");
	    c_naturalidade.addItem("Tete");
	    c_naturalidade.addItem("Manica");
	    c_naturalidade.addItem("Inhambane");
	    c_naturalidade.addItem("Sofala");
	    painel.add(c_naturalidade);
	    
	   
	    try {
			t_contacto = new JFormattedTextField(new MaskFormatter(" (258) ## #### ###"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    t_contacto.setBounds(260,110,140,20);
	    t_contacto.setFont(f5);
	    t_contacto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_contacto.setForeground(new Color(100,100,100));
	    painel.add(t_contacto);
	    
	    try {
			t_contacto1 = new JFormattedTextField(new MaskFormatter(" (258) ## #### ###"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    t_contacto1.setBounds(440,110,140,20);
	    t_contacto1.setFont(f5);
	    t_contacto1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_contacto1.setForeground(new Color(100,100,100));
	    painel.add(t_contacto1);
	    
	   
	    
	    t_foto = new JTextField();
	    t_foto.setBounds(240,100,140,20);
	    t_foto.setFont(f6);
	    t_foto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_foto.setForeground(new Color(100,100,100));
	    //painel.add(t_foto);
	    
	    
	    btn_atualizar = new JButton("Actualizar");
	    btn_atualizar.setBounds(470,140,110,25);
	    btn_atualizar.setFont(f5);
	   // btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_atualizar.setBackground(new Color(239,239,239));
	    btn_atualizar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/lapis1.png")));
	    btn_atualizar.setForeground(new Color(0,0,0));
	    btn_atualizar.addActionListener(this);
	    painel.add(btn_atualizar);
	    
	    btn_registar = new JButton("Registar");
	    btn_registar.setBounds(370,140,100,25);
	    btn_registar.setFont(f5);
	    //btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_registar.setBackground(new Color(239,239,239));
	    btn_registar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/adic.png")));
	    btn_registar.setForeground(new Color(0,0,0));
	    btn_registar.addActionListener(this);
	   // painel.add(btn_registar);
	    
	    
	    btn_limpar = new JButton("Limpar");
	    btn_limpar.setBounds(340,140,110,25);
	    btn_limpar.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_limpar.setBackground(new Color(239,239,239));
	    btn_limpar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/clean.png")));
	    btn_limpar.setForeground(new Color(0,0,0));
	    btn_limpar.addActionListener(this);
	    painel.add(btn_limpar);
	    
	    btn_import = new JButton("Importar");
	    btn_import.setBounds(600,149,155,25);
	    btn_import.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_import.setBackground(new Color(239,239,239));
	    btn_import.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/import.png")));
	    btn_import.setForeground(new Color(0,0,0));
	    btn_import.addActionListener(this);
	    painel.add(btn_import);

	    
	    
	    String [] nome = {"Cod","Nome", "NUIT","Localização","URL","Email","Cidade","Telefone"," Telefone1 ","Logo"};
	    tabela = new JTable();
		tabela.setFont(f5);
		DefaultTableModel dtml = new DefaultTableModel(nome, 0);
		tabela.setModel(dtml);
		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		tabela.setBackground(new Color(200,200,200));
		tabela.getTableHeader().setFont(f5);
		tabela.setOpaque(false);
		tabela.addMouseListener(this);
		tabela.getTableHeader().setBackground(new Color(239,239,239));
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
	    js = new JScrollPane(tabela);
		js.setBackground(Color.WHITE);
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
			///js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js.setBounds(10, 240, 749, 175);
		//painel.add(js);
		
	   
	    btn_sair = new JButton(" X ");
		btn_sair.setBounds(615, 3, 30, 25);
		btn_sair.setForeground(new Color(0,0,0));
		btn_sair.setBackground(Color.white);
		btn_sair.setContentAreaFilled(false);
		btn_sair.setToolTipText("Sair");
		btn_sair.setFont(f31);
	    btn_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		btn_sair.setOpaque(false);
		btn_sair.addMouseListener(this);
//		painel.add(btn_sair);
		
		 
	    btn_confirmar = new JButton("Confirmar");
	    btn_confirmar.setBounds(8, 5, 114, 23);
	    btn_confirmar.setForeground(new Color(255,255,255));
	    btn_confirmar.setBackground(Color.white);
	    btn_confirmar.setContentAreaFilled(false);
	    btn_confirmar.setToolTipText("Salvar");
	    btn_confirmar.setFont(f3);
    //  btn_confirmar.setBorder(new RoundedBorder(20));
	    btn_confirmar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(255, 255, 255)));
	    btn_confirmar.setOpaque(false);
	    btn_confirmar.addMouseListener(this);
	 //	painel1.add(btn_confirmar);

	    
		
		 l_img = new JLabel("");
	     l_img.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/cadescola.png")));
 	     l_img.setBounds(0, 0, 650, 400);
 	     l_img.addMouseListener(this);
         l_img.setBorder(new LineBorder(Color.lightGray));
		 l_img.setLayout(null);
	    // painel.add(l_img);
		
	    
		
		 painel.add(painel04);
		 painel.add(painel03);
		 painel.add(painel02);
		 painel.add(painel01);
		 painel.add(painel2);
	    painel.add(painel1);
	 		getContentPane().add(painel);
	 		
	 		
	 		conexao = modulo.ModuloConexao.conector();

	 		
	 	}
	 	
	 	
	 	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	public static void main(String[] args) {
	 		EventQueue.invokeLater(new Runnable() {
	 			public void run() {
	 				try {
	 				TelaEscola	te = new TelaEscola();
	 				te.addcomboboxtipop();
	 				te.addcomboboxmeio();
	 				te.addcomboboxclasse();
	 				te.addcomboboxcargo();
	 				te.pesquisar_escola();
	 				} catch (Exception e) {
	 					e.printStackTrace();
	 				}
	 			}
	 		});	
	 	}

	 	
	 	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	public void mouseClicked(MouseEvent e) {
	 		
	 		if (e.getSource() == btn_sair) {
	 			int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar saida?","   Mensagem", JOptionPane.YES_NO_OPTION);
	 			   if (confirmar == JOptionPane.YES_OPTION) {
	 				System.exit(0);
	 			}
	 		}
	 		
	 		//  btn_st.setBounds(272,262,90,21);
		    //  btn_st.setBounds(640,262,90,21);
	 	    //btn_st.setBounds(272,372,90,21);
	 	    // btn_st.setBounds(640,372,90,21);
	 		
	 	    //t_salvart.setBounds(30,262,231,21);
	 	    // t_salvart.setBounds(395,262,231,21);
	 	    //t_salvart.setBounds(30,372,231,21);  
	 	    // t_salvart.setBounds(395,372,231,21);
	 	
	 		if (e.getSource() == imgadd1) {
				
	 			btn_st.setBounds(272,262,90,21);
	 			btn_st.setVisible(true);
	 			
	 			t_salvart.setBounds(30,262,231,21);
	 			t_salvart.setVisible(true);
			}
	 		
	 		if (e.getSource() == imgadd2) {
			
	 			 btn_st.setBounds(640,262,90,21);
	 			btn_st.setVisible(true);
	 			
	 			t_salvart.setBounds(395,262,231,21);
	 			t_salvart.setVisible(true);
			}
	 		
	 		if (e.getSource() == imgadd3) {
	 			
	 			btn_st.setBounds(272,372,90,21);
		     	btn_st.setVisible(true);
		 			
		     	t_salvart.setBounds(30,372,231,21);
				t_salvart.setVisible(true);
			}
	 		
	 		if (e.getSource() == imgadd4) {
	 			 btn_st.setBounds(640,372,90,21);
		     	btn_st.setVisible(true);
		 			
		     	t_salvart.setBounds(395,372,231,21);
				t_salvart.setVisible(true);
			}
	 		
	 		
	 	if (e.getSource() == l_logo) {
			addfoto();
		}
	 		
	 		
	 	
	 	
	 	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 	
	 	if (e.getSource() == imgdel1) {
			removertipo();
		}
 		
 		if (e.getSource() == imgdel2) {
		    removermeio();
		}
 		
 		if (e.getSource() == imgdel3) {
 		    removerclasse();
		}
 		
 		if (e.getSource() == imgdel4) {
 		   removercargo();	
		}
 		
 		
 
 		
	 }
 
	 	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 	
	 	public void mousePressed(MouseEvent e) {

	 		
	 	}

	 	
	 	public void mouseReleased(MouseEvent e) {
	 		
	 		
	 	}

	 	
	 	public void mouseEntered(MouseEvent e) {
	  
	 		if (e.getSource() == btn_atualizar) {
	 		    btn_atualizar.setBackground(new Color(249,249,249));
	 		    btn_atualizar.setForeground(new Color(0,0,0));
	 		    btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 		if (e.getSource() == btn_registar) {
	 			btn_registar.setBackground(new Color(249,249,249));
	 			btn_registar.setForeground(new Color(0,0,0));
	 			btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 		if (e.getSource() == btn_limpar) {
	 			//btn_limpar.setBackground(new Color(249,249,249));
	 			btn_limpar.setForeground(new Color(0,0,0));
	 			btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 	}

	 	
	 	public void mouseExited(MouseEvent e) {

	 		if (e.getSource() == btn_atualizar) {
	 		    btn_atualizar.setBackground(new Color(88,88,88));
	 		    btn_atualizar.setForeground(new Color(255,255,255));
	 		    btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 		if (e.getSource() == btn_registar) {
	 			btn_registar.setBackground(new Color(88,88,88));
	 			btn_registar.setForeground(new Color(255,255,255));
	 			btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 		if (e.getSource() == btn_limpar) {
	 			btn_limpar.setBackground(new Color(88,88,88));
	 			btn_limpar.setForeground(new Color(255,255,255));
	 			btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	 		}
	 		
	 	}


		
		public void mouseDragged(MouseEvent e) {
		
			
		}


		
		public void mouseMoved(MouseEvent e) {
			
			
		}


		
		public void actionPerformed(ActionEvent e) {
			//  btn_st.setBounds(272,262,90,21);
 		    //  btn_st.setBounds(640,262,90,21);
 		    //btn_st.setBounds(272,372,90,21);
 		    // btn_st.setBounds(640,372,90,21);
 
			if (e.getSource() == btn_st) {
				if (btn_st.getBounds().getLocation().x == 272 && btn_st.getBounds().getLocation().y == 262) {
					adicionartipo();
				}else if (btn_st.getBounds().getLocation().x == 640 && btn_st.getBounds().getLocation().y == 262) {
					adicionarmeio();
				}else if (btn_st.getBounds().getLocation().x == 272 && btn_st.getBounds().getLocation().y == 372) {
					adicionarclasse();
				} else if (btn_st.getBounds().getLocation().x == 640 && btn_st.getBounds().getLocation().y == 372) {
					adicionarcargo();
				}
			}
			
			
			if (e.getSource() == btn_registar) {
			
			}
			
			if (e.getSource() == btn_atualizar) {
				alterar();
			}
			
			if (e.getSource() == btn_import) {
				pesquisar_escola();
				sett_campos();
				System.out.println( t_location.getText().length());
			}
			
			if (e.getSource() == btn_limpar) {
				limparcampos();
			}
		}

}
