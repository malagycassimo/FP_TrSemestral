package Telas;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import com.toedter.calendar.JDateChooser;

import Others.RoundedBorder;
import net.proteanit.sql.DbUtils;

public class TelaAluno extends JInternalFrame implements MouseListener, ActionListener, KeyListener {
	
	
	

/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * */	

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	TelaPrincipal tp = null;

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void adicionar() {
		String sql = "insert into tbaluno(Nome,NrBI,Genero,DataNascimento,Telefone,Telefone1,Naturalidade,CategCarta,DataMatricula,Turma,Foto,Funcionario) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		//enablebtn();
		ativarrestricao(t_idusuario.getText());
		try {
			  
			
			if ( t_nome.getText().isEmpty() || t_nrbi.getText().isEmpty() || (r_sexo.getActionCommand().isEmpty() && r_sexo1.getActionCommand().isEmpty()) /*||t_idade.getDateFormatString().isEmpty() */ || t_contacto.getText().isEmpty() || c_categoria.getSelectedItem().isEmpty() || c_categoria.getSelectedItem().equals("") || c_nomet.getSelectedItem().equals("")|| l_data.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha  os campos obrigatorios(*)!!");	
			}else {
			
				  
                   
//                     
//                 	int countt = 0;
//            	  	for (int i = 0; i < tabela.getRowCount(); i++) {
//            	  		if (tabela.getModel().getValueAt(i, 10).equals(c_nomet.getSelectedItem())) {
//                           countt += 1;				
//            			}
//            		}
//                     
//                     if (countt >= 1) {
//						 JOptionPane.showMessageDialog(null, "A turma se encontra cheia");
//						 countt=0;
//					}else {

					pst = conexao.prepareStatement(sql);
					
					pst.setString(1, t_nome.getText().toString());
					pst.setString(2, t_nrbi.getText().toString());
					if (r_sexo.isSelected()) {
					pst.setString(3, r_sexo.getActionCommand().toString());
					}else if (r_sexo1.isSelected()) {
					pst.setString(3, r_sexo1.getActionCommand().toString());	
					}
					
					SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");
					String date = formatodata.format(t_idade.getDate());
					pst.setString(4, ""+date.toString());
					
					pst.setString(5, t_contacto.getText().toString());
					pst.setString(6, t_contacto1.getText().toString());
					pst.setString(7, c_naturalidade.getSelectedItem().toString());
					pst.setString(8, c_categoria.getSelectedItem().toString());
					pst.setString(9, l_data.getText().toString());
					pst.setString(10,c_nomet.getSelectedItem());
					pst.setString(11, t_foto.getText().toString());
					pst.setString(12,  t_funcionario.getText().toString());
				
			int adicionar = pst.executeUpdate();
			//System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Estudante adicionado com sucesso!!");
				
				 limparcampos();
				//t_foto.setText(null);
			   //  System.out.println(bg.getSelection().getActionCommand());
				pesquisar_aluno();	
				
			}
			
			}
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
public void pesquisar_aluno() {
	String sql = "select * from tbaluno where nome like ?";
	
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
	
/*''''''''''''''''''''''''''''''''' Metodo para preencher os campos ao clicar na tabela '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void sett_campos() {
		
		int nr = tabela.getSelectedRow();
			
		
		
		t_codigo.setText(tabela.getModel().getValueAt(nr, 0).toString());
		t_nome.setText(tabela.getModel().getValueAt(nr, 1).toString());
		t_nrbi.setText(tabela.getModel().getValueAt(nr, 2).toString());
		if (tabela.getModel().getValueAt(nr, 3).equals("    Masculino")) {
		r_sexo.setSelected(true);
		}else if (tabela.getModel().getValueAt(nr, 3).equals("    Feminino")) {
		r_sexo1.setSelected(true);	
		}
		SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");
	    Date data1;
		try {
			data1 = formatodata.parse((String) tabela.getModel().getValueAt(nr, 4));
			t_idade.setDate(data1);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		//bg.setSelected(tabela.getModel().getValueAt(nr, 3).toString(),null);
		//t_idade.setDateFormatString(tabela.getModel().getValueAt(nr, 4).toString());
		t_contacto.setText(tabela.getModel().getValueAt(nr, 5).toString());
	 	t_contacto1.setText(tabela.getModel().getValueAt(nr, 6).toString());
		c_naturalidade.select(tabela.getModel().getValueAt(nr, 7).toString());
		c_categoria.select(tabela.getModel().getValueAt(nr, 8).toString());
		
	//	c_turma.select(tabela.getModel().getValueAt(nr, 10).toString());
		c_nomet.select(tabela.getModel().getValueAt(nr, 10).toString());
//		t_turma.setText(tabela.getModel().getValueAt(nr, 10).toString());
		t_foto.setText(tabela.getModel().getValueAt(nr, 11).toString());
		if (t_foto.getText().isEmpty()) {
			 l_addp.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
		}else {
			l_addp.setIcon(new ImageIcon(t_foto.getText()));
		}
	  //  t_funcionario.setText(tabela.getModel().getValueAt(nr, 12).toString());
		//t_foto.setText(tabela.getModel().getValueAt(nr, 9).toString());
	    //enablebtn();
	    ativarrestricao(t_idusuario.getText());
	}
	 
	

	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo btn '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
//	public void enablebtn() {
//		
//		if (t_codigo.getText().isEmpty()) {
//			btn_atualizar.setEnabled(false);
//			btn_limpar.setEnabled(false);
//			btn_registar.setEnabled(true);
//		}else {
//			btn_atualizar.setEnabled(true);
//			btn_limpar.setEnabled(true);
//			btn_registar.setEnabled(false);
//		}
//		
//		ativarrestricao(t_idusuario.getText());
//		//System.out.println(t_idusuario.getText());
//		
//	}
//	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void alterar() {
		String sql = "Update tbaluno set Nome = ?, NrBI = ?, Genero = ?, DataNascimento = ?, Telefone = ?,Telefone1 = ?, Naturalidade = ?, CategCarta = ?, Turma = ?, Foto = ?, Funcionario = ? where Cod = ?";
		//enablebtn();
		ativarrestricao(t_idusuario.getText());
		    
		try {
	
			if (t_codigo.getText().isEmpty() || t_nome.getText().isEmpty() || t_nrbi.getText().isEmpty() ||t_idade.getDateFormatString().isEmpty() || t_contacto.getText().isEmpty() || c_categoria.getSelectedItem().isEmpty() || c_categoria.getSelectedItem().equals("") || c_nomet.getSelectedItem().equals("") ){
				JOptionPane.showMessageDialog(null, "Preencha  os campos obrigatorios!!");	
			}else {
				
				
			    
//             	int countt = 0;
//        	  	for (int i = 0; i < tabela.getRowCount(); i++) {
//        	  		if (tabela.getModel().getValueAt(i, 10).equals(c_nomet.getSelectedItem())) {
//                       countt += 1;				
//        			}
//        		}
//                  
//                  if (countt >= 1) {
//						 JOptionPane.showMessageDialog(null, "A turma se encontra cheia");
//						 countt=0;
//					}else {
				
				pst = conexao.prepareStatement(sql);
				
				pst.setString(1, t_nome.getText().toString()); 
				pst.setString(2, t_nrbi.getText().toString());
				if (r_sexo.isSelected()) {
					pst.setString(3, r_sexo.getActionCommand().toString());
					}else if (r_sexo1.isSelected()) {
					pst.setString(3, r_sexo1.getActionCommand().toString());	
					}
				
				SimpleDateFormat formatodata = new SimpleDateFormat("dd/MM/yyyy");
				String date = formatodata.format(t_idade.getDate());
				
				pst.setString(4, ""+date.toString());
				pst.setString(5, t_contacto.getText().toString());
				pst.setString(6, t_contacto1.getText().toString());
				pst.setString(7, c_naturalidade.getSelectedItem().toString());
				pst.setString(8, c_categoria.getSelectedItem().toString());
				
				//pst.setString(9, c_turma.getSelectedItem().toString());
				pst.setString(9,  c_nomet.getSelectedItem().toString());
				pst.setString(10, t_foto.getText().toString());
				pst.setString(11, t_funcionario.getText().toString());
				pst.setString(12, t_codigo.getText().toString());
				
				//System.out.print("skjf"+t_funcionario.getText());
				
			//A linha a baixo actualiza a tabela
			pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados do aluno alterados com sucesso!!");
				 limparcampos();
				 pesquisar_aluno();
			
			}
			}
//			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void remover() {
		//enablebtn();
		ativarrestricao(t_idusuario.getText());
		if (t_codigo.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Selecione o Aluno que predende remover!");	
		}else {
			int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuario?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
			   if (confirmar == JOptionPane.YES_OPTION) {
				   
				String sql = "delete from tbaluno where Cod = ?";
				try {
					pst = conexao.prepareStatement(sql);
					pst.setString(1, t_codigo.getText());
					
					int adicionar = pst.executeUpdate();
					if (adicionar > 0) {
						JOptionPane.showMessageDialog(null, "Aluno removido com sucesso!!");
						 limparcampos();
						//	t_foto.setText(null);
					    pesquisar_aluno();
					  
						}
					//}
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e);
				}	
			}else {
				JOptionPane.showMessageDialog(null, "Operação cancelada!!");
			}	
		} 

	}
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Limpar Campos '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void limparcampos() {
		
		t_codigo.setText(null);
		t_nome.setText(null);
		t_nrbi.setText(null);
        r_sexo.setSelected(false);
        r_sexo1.setSelected(false);
		//t_idade.setText(null);
		//t_idade.cleanup();
		t_idade.setDate(null);
		t_contacto.setText(null);
		t_contacto1.setText(null);
		//t_funcionario.setText(null);
		c_categoria.select("");
		c_naturalidade.select("");
		c_nomet.select("");
		//t_turma.setText(null);
		//c_turma.select(""); 
	    //t_foto.setText(null);
	    l_addp.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
	    //enablebtn();
	    ativarrestricao(t_idusuario.getText());
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''' Add Combobox'''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
public void addcomboboxcarta() {
		
		String sql = "select nome from tbclassec";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			c_categoria.removeAll();
			c_categoria.addItem("");
			
			while (rs.next()) {
		     c_categoria.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
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
	l_addp.setIcon(new ImageIcon(img.getImage().getScaledInstance(l_addp.getWidth(), l_addp.getHeight(), Image.SCALE_DEFAULT)));
	
	}
	
}




/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox filtrar'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
/*
public void addcomboxturma() {
	
	String sql = "select Turno from tbturma";
	
	try {
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		
	
		c_turma.removeAll();
		c_turma.addItem("");
		while (rs.next()) {
			c_turma.addItem(rs.getString(1));	
		}
	} catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
	}
}

*/


public void addcomboboxnomet() {
	
	String sql = "select nome from tbturma where horario =?";
	try {
		pst = conexao.prepareStatement(sql);
		
		pst.setString(1, t_hora1.getText());
		
		rs = pst.executeQuery();
		
		c_nomet.removeAll();
		c_nomet.addItem("");
		
		while (rs.next()) {
	     c_nomet.addItem(rs.getString(1));	
		}
		
    

	} catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
	}
	
}


public void addcomboboxhorariot() {
	
	String sql = "select Horario from tbturma";
	try {
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		
		c_horariot.removeAll();
		c_horariot.addItem("");
		
		while (rs.next()) {
	     c_horariot.addItem(rs.getString(1));	
		}
		
		
	} catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e); 
	}
	
}

//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo turma cheia '''''''''''''''''''''''''''''''''''''''''''''''''''

// '''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo ativarprivilegios '''''''''''''''''''''''''''''''''''''''''''''''''''


public void ativarrestricao(String getid) {
	
	String sql = "select * from tbbloqueio where iduser = ?";

	
	try {
		
		pst = conexao.prepareStatement(sql);
		
	   
		pst.setString(1, getid);
		
		rs = pst.executeQuery();
		
		if (rs.next()) {
			
			if (rs.getBoolean(4) == false) {
			
				btn_registar.setEnabled(false);
			}else {

				if (t_codigo.getText().isEmpty()) {
					btn_registar.setEnabled(true);
				}else {
					btn_registar.setEnabled(false);
				}
				
			}
			
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
			
			if (rs.getBoolean(5) == false) {
				
				btn_limpar.setEnabled(false);
			}else {

				if (t_codigo.getText().isEmpty()) {
					btn_limpar.setEnabled(false);
				}else {
					btn_limpar.setEnabled(true);
				}
				
			}
			
			//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

			if (rs.getBoolean(6) == false) {
				
				btn_atualizar.setEnabled(false);
			}else {

				if (t_codigo.getText().isEmpty()) {
					btn_atualizar.setEnabled(false);
				}else {
					btn_atualizar.setEnabled(true);
				}
				
			}
			
			
		}

		
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}
	
}

//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

//
//// ''''''''''''''''''''''''''''''''''''''''''''''''''''' verificaralunosnaturma '''''''''''''''''''''''''''''''''''''''''''''''''''''''''
//
//public void verificarnralunosnat() {
//	
//	int nr = tabela.getSelectedRow();
//	int count = 0;
//	String sql = "select count(*) from tum where Turma = ?";
//	try {
//		pst = conexao.prepareStatement(sql);
//		pst.setString(1, tabela.getModel().getValueAt(nr, 1).toString());
//	  	rs = pst.executeQuery();
//	  	
//	  	while (rs.next()) {
//		 count = rs.getInt(1);
//		}
//	  	
//	    t_nralunos.setText("           " + count);
//	    t_nralunos1.setText("          " + (Integer.parseInt(tabela.getModel().getValueAt(nr, 4).toString()) - count));
//	  	
//	 	//System.out.println(count);
//	
//} catch (SQLException e) {
//    JOptionPane.showMessageDialog(null, e);
//}
//
//}




/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * */	
	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	    
	   JPanel painel,painel1,painel2,painel3;
	   Font f1,f2,f3,f31,f4,f5,f6;
	   JButton btn_sair,btn_registar,btn_atualizar,btn_limpar,btn_limpar1;
	   JLabel l_nome,l_codigo,l_nrbi,l_sexo,l_datanascimento,l_naturalidade,l_contacto,l_ou,l_categoria,l_addp,l_pesquisar,l_msg,l_data,l_turma;
	   JTextField t_nome,t_codigo,t_nrbi,t_contacto,t_contacto1,t_naturalidade,t_pesquisar,t_foto,t_funcionario, t_hora1,t_idusuario;
	   JRadioButton r_sexo, r_sexo1;
	   Choice c_naturalidade,c_categoria, c_horariot,c_nomet;
	   JTable tabela;
	   JScrollPane js;
	   ButtonGroup bg;
	   JDateChooser t_idade;
	  
	  //TelaPrincipal tp;
	   
	   
	   

	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 //  TelaPreVisualizacao tpv = new TelaPreVisualizacao();
	   // TelaUsuario tu = new TelaUsuario();
	  
	   
	public TelaAluno()   {
		
		//System.out.println(tpv.tabela.getModel().getValueAt(1, 1).toString());
		//setUndecorated(true);
		setVisible(true);
		//setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(JRootPane.PLAIN_DIALOG);
		setResizable(false);
		setLayout(null);
		setSize(769,441);
		setLocation(0, -25);
		//setIconifiable(true);
		//setClosable(true);
		//setMaximizable(true);
		setActionMap(getActionMap());
		setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, getBackground()));
		setFrameIcon(new ImageIcon(TelaAluno.class.getResource("/imgs/add.png")));
		//this.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
		this.addMouseListener(this);
		
		BasicInternalFrameUI basic = (javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI();
		for (MouseListener listener:basic.getNorthPane().getMouseListeners()) {
			basic.getNorthPane().removeMouseListener(listener);
		}
		
		
		
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.CENTER_BASELINE, 14);
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		
		// dada da matricula
		
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
	   
	    
	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	    
	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	    
	    l_codigo = new JLabel("Codigo*");
	    l_codigo.setBounds(25,15,50,30);
	    l_codigo.setFont(f5);
	    l_codigo.setForeground(new Color(0,0,0));
	    painel.add(l_codigo);
	    
	    l_nome = new JLabel("Nome Completo*");
	    l_nome.setBounds(180,15,100,30);
	    l_nome.setFont(f5);
	    l_nome.setForeground(new Color(0,0,0));
	    painel.add(l_nome);
	    
	    
	    
	    l_nrbi = new JLabel("N°.BI*");
	    l_nrbi.setBounds(25,45,100,30);
	    l_nrbi.setFont(f5);
	    l_nrbi.setForeground(new Color(0,0,0));
	    painel.add(l_nrbi);
	    
	    l_sexo = new JLabel("Genero*");
	    l_sexo.setBounds(300,45,100,30);
	    l_sexo.setFont(f5);
	    l_sexo.setForeground(new Color(0,0,0));
	    painel.add(l_sexo);
	    
        l_datanascimento = new JLabel("Data_N*");
        l_datanascimento.setBounds(25,75,100,30);
        l_datanascimento.setFont(f5);
        l_datanascimento.setForeground(new Color(0,0,0));
	    painel.add(l_datanascimento);
	    
	    
	    l_contacto = new JLabel("Contacto*");
	    l_contacto.setBounds(180,75,100,30);
	    l_contacto.setFont(f5);
	    l_contacto.setForeground(new Color(0,0,0));
	    painel.add(l_contacto);
	    
	    
	    l_naturalidade = new JLabel("Naturalidade*");
	    l_naturalidade.setBounds(25,105,75,30);
	    l_naturalidade.setFont(f5);
	    l_naturalidade.setForeground(new Color(0,0,0));
	    painel.add(l_naturalidade);
	    
	    l_turma = new JLabel("Turma*");
	    l_turma.setBounds(25,135,45,30);
	    l_turma.setFont(f5);
	    l_turma.setForeground(new Color(0,0,0));
	    painel.add(l_turma);
	    
//	    l_importt = new JLabel("X");
//	    l_importt.setBounds(180,140,60,20);
//	    l_importt.setFont(f5);
//	    l_importt.setForeground(new Color(0,0,0));
//	    l_importt.addMouseListener(this);
//	    painel.add(l_importt);
	    
	    l_ou = new JLabel("ou");
	    l_ou.setBounds(400,75,20,30);
	    l_ou.setFont(f5);
	    l_ou.setForeground(new Color(100,100,100));
	    painel.add(l_ou);
	    
	    
	    l_categoria = new JLabel("Categoria da Carta*");
	    l_categoria.setBounds(280,105,105,30);
	    l_categoria.setFont(f5);
	    l_categoria.setForeground(new Color(0,0,0));
	    painel.add(l_categoria);
	    
	    
	    l_addp = new JLabel("");
	    l_addp.setBounds(600,10,159,130);
	    l_addp.setFont(f5);
	    l_addp.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_addp.setIcon(new  ImageIcon(TelaAluno.class.getResource("/imgs/addp1.png")));
	    l_addp.setForeground(new Color(0,0,0));
	    l_addp.addMouseListener(this);
	    painel.add(l_addp);
	    
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
	    
	    l_pesquisar = new JLabel("        pesquisar");
	    l_pesquisar.setBounds(340,185,100,25);
	    l_pesquisar.setFont(f5);
	    l_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_pesquisar.setForeground(new Color(0,0,0));
	    painel.add(l_pesquisar);
	    
	    l_msg = new JLabel("Registo de Estudantes!!");
	    l_msg.setBounds(12,175,300,25);
	    l_msg.setFont(f5);
	  //  l_msg.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_msg.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_msg.setForeground(new Color(174,61,41));
	    painel.add(l_msg);
	    
	    
	    
	    t_codigo = new JTextField();
	    t_codigo.setBounds(70,20,80,20);
	    t_codigo.setFont(f6);
	    t_codigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_codigo.setForeground(new Color(100,100,100));
	    t_codigo.setEnabled(false);
	    painel.add(t_codigo);
	    
	    
	    t_nome = new JTextField();
	    t_nome.setBounds(280,20,300,20);
	    t_nome.setFont(f6);
	    t_nome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nome.setForeground(new Color(100,100,100));
	    painel.add(t_nome);
	    
	   
	    
	    try {
			t_nrbi =  new JFormattedTextField(new MaskFormatter("###########" + " *"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
	    t_nrbi.setBounds(70,50,180,20);
	    t_nrbi.setFont(f6);
	    t_nrbi.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nrbi.setForeground(new Color(100,100,100));
	    painel.add(t_nrbi);
	    
	    bg = new ButtonGroup();
	    
	    r_sexo = new JRadioButton("    Masculino");
	    r_sexo.setBounds(350,50,105,20);
	    r_sexo.setFont(f6);
	    r_sexo.setForeground(new Color(0,0,0));
	    r_sexo.setBackground(new Color(239,239,239));
	    
	    r_sexo1 = new JRadioButton("    Feminino");
	    r_sexo1.setBounds(475,50,105,20);
	    r_sexo1.setFont(f6);
	    r_sexo1.setForeground(new Color(0,0,0));
	    r_sexo1.setBackground(new Color(239,239,239));
	    
	    bg.add(r_sexo);
	    bg.add(r_sexo1);
	    painel.add(r_sexo); 
	    painel.add(r_sexo1);

	    
	    
	  //  try {
		//	t_idade = new JFormattedTextField(new MaskFormatter("## " + "/" + " ## " + "/" + " ####"));
		//} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
	//	}
	    //t_idade.setBounds(70,80,90,20);
	    //t_idade.setFont(f6);
	    //t_idade.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	   // t_idade.setForeground(new Color(100,100,100));
	  //  painel.add(t_idade);
	    
	    t_idade = new JDateChooser("dd/MM/yy","##/##/####",'_');
	    t_idade.setBounds(70,80,90,20);
	    t_idade.setFont(f6);
	    t_idade.setForeground(new Color(100,100,100));
	    t_idade.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel.add(t_idade);
	    
	    
	    try {
			t_contacto = new JFormattedTextField(new MaskFormatter(" (258) ## #### ###"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    t_contacto.setBounds(240,80,140,20);
	    t_contacto.setFont(f6);
	    t_contacto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_contacto.setForeground(new Color(100,100,100));
	    painel.add(t_contacto);
	    
	    try {
			t_contacto1 = new JFormattedTextField(new MaskFormatter(" (258) ## #### ###"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    t_contacto1.setBounds(440,80,140,20);
	    t_contacto1.setFont(f6);
	    t_contacto1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_contacto1.setForeground(new Color(100,100,100));
	    painel.add(t_contacto1);
	    
	   
	    
	    c_naturalidade = new Choice();
	    c_naturalidade.setBounds(110,110,150,18);
	    c_naturalidade.setFont(f6);
	    c_naturalidade.setForeground(new Color(100,100,100));
	    c_naturalidade.addItem("");
	    c_naturalidade.addItem("Maputo");
	    c_naturalidade.addItem("Tete");
	    c_naturalidade.addItem("Manica");
	    c_naturalidade.addItem("Inhambane");
	    c_naturalidade.addItem("Sofala");
	    painel.add(c_naturalidade);
	    
	    c_categoria = new Choice();
	    c_categoria.setBounds(400,110,180,18);
	    c_categoria.setFont(f6);
	    c_categoria.setForeground(new Color(100,100,100));
	    c_categoria.addItem("");
	  //  c_categoria.addItem("Pesado");
	   
	    painel.add(c_categoria);
	    
	    
	    
	    c_nomet = new Choice();
	    c_nomet.setBounds(180,140,100,18);
	    c_nomet.setFont(f6);
	    c_nomet.setForeground(new Color(100,100,100));
	    c_nomet.addItem("");
	    c_nomet.addMouseListener(this);
	   // c_nomet.addItem("17h");
	    //c_nomet.addItem("15h"); 	   
	    painel.add(c_nomet);
	    
	    c_horariot = new Choice();
	    c_horariot.setBounds(70,140,100,18);
	    c_horariot.setFont(f6);
	    c_horariot.setForeground(new Color(100,100,100));
	    c_horariot.addItem("");
	    c_horariot.addMouseListener(this);
	   	   
	    painel.add(c_horariot);
	    
	    
	    t_hora1 = new JTextField();
	    t_hora1.setBounds(70,140,100,18);
	    t_hora1.setFont(f6);
	  //  t_hora1.setText(tpv.t_numero.getText());
	    t_hora1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_hora1.setForeground(new Color(100,100,100));
	    t_hora1.addKeyListener(this);
//	    t_turma.setEnabled(false);
//	    painel.add(t_turma);
	    
	    t_foto = new JTextField();
	    t_foto.setBounds(70,50,180,20);
	    t_foto.setFont(f6);
	    t_foto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_foto.setForeground(new Color(100,100,100));
	  //  painel.add(t_foto);
	    
	    t_idusuario = new JTextField();
	    t_idusuario.setBounds(70,50,180,20);
	    t_idusuario.setFont(f6);
	    t_idusuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_idusuario.setForeground(new Color(100,100,100));
	  //  painel.add(t_foto);
	    
	    //System.out.println(t_idusuario.getText());
	    
	    t_funcionario = new JTextField();
	    t_funcionario.setBounds(70,155,180,20);
	    t_funcionario.setFont(f6);
	    t_funcionario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_funcionario.setForeground(new Color(100,100,100));
	    t_funcionario.setVisible(false);
	    painel.add(t_funcionario);
	    
	    
	    btn_atualizar = new JButton("Actualizar");
	    btn_atualizar.setBounds(470,140,110,25);
	    btn_atualizar.setFont(f5);
	   // btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_atualizar.setBackground(new Color(239,239,239));
	    btn_atualizar.setForeground(new Color(0,0,0));
	    btn_atualizar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/lapis1.png")));
	    btn_atualizar.addActionListener(this);
	    painel.add(btn_atualizar);
	    
	    btn_registar = new JButton("Registar");
	    btn_registar.setBounds(350,140,110,25);
	    btn_registar.setFont(f5);
	    //btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_registar.setBackground(new Color(239,239,239));
	    btn_registar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/adic.png")));
	    btn_registar.setForeground(new Color(0,0,0));
	    btn_registar.addActionListener(this);
	    btn_registar.addActionListener(this);
	    painel.add(btn_registar);
	    
	    btn_limpar = new JButton("Remover");
	    btn_limpar.setBounds(600,149,159,25);
	    btn_limpar.setFont(f5);
	  //  btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0,0,0)));
	    btn_limpar.setBackground(new Color(239,239,239));
	    btn_limpar.setForeground(new Color(0,0,0));
	    btn_limpar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/remov.png")));
	    btn_limpar.addActionListener(this);
	    painel.add(btn_limpar);
	    
	    
	    btn_limpar1 = new JButton("Limpar");
	    btn_limpar1.setBounds(220,185,110,25);
	    btn_limpar1.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_limpar1.setBackground(new Color(239,239,239));
	    btn_limpar1.setForeground(new Color(0,0,0));
	    btn_limpar1.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/clean.png")));
	    btn_limpar1.addActionListener(this);
	    painel.add(btn_limpar1);
	    
	    t_pesquisar = new JTextField();
	    t_pesquisar.setBounds(450,185,309,25);
	    t_pesquisar.setFont(f5);
	    t_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar.setForeground(new Color(0,0,0));
	    t_pesquisar.addKeyListener(this);
	    t_pesquisar.addMouseListener(this);
	    painel.add(t_pesquisar);
	    
	    String [] nome = {"Cod","Nome", "NrBI","Genero","DataNascimento","Telefone","Telefone1","Naturalidade"," CategCarta ","DataMatricula","Turma","Foto"};
	    tabela = new JTable();
		tabela.setFont(f5);
		DefaultTableModel dtml = new DefaultTableModel(nome, 0);
		tabela.setModel(dtml);
		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		tabela.setBackground(Color.white);
		tabela.getTableHeader().setFont(f5);
		tabela.setOpaque(false);
		tabela.addMouseListener(this);
		tabela.getTableHeader().setBackground(new Color(239,239,239));
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
	    js = new JScrollPane(tabela);
		js.setBackground(new Color(200,200,200));
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		///js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js.setBounds(10, 222, 749, 185);
		painel.add(js);
	    
	    
	    btn_sair = new JButton(" X ");
		btn_sair.setBounds(700, 3, 30, 25);
		btn_sair.setForeground(new Color(0,0,0));
		btn_sair.setBackground(Color.white);
		btn_sair.setContentAreaFilled(false);
		btn_sair.setToolTipText("Sair");
		btn_sair.setFont(f31);
	    btn_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		btn_sair.setOpaque(false);
		btn_sair.addMouseListener(this);
		//painel.add(btn_sair);
		
		
	   
	    
	    painel.add(painel1);
	    
		getContentPane().add(painel);
		
		conexao = modulo.ModuloConexao.conector();
		
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void  location(ComponentEvent e) {
		getContentPane().setLocation(0, 0);
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			TelaAluno ta = new TelaAluno();
				ta.addcomboboxcarta();
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
				//System.exit(0);
				   
			}
		}
		
		if (e.getSource() == tabela) {
			sett_campos();
		}
		
		if (e.getSource() == l_addp) {
			addfoto();
		}
	
		if (e.getSource() == t_pesquisar) {
			pesquisar_aluno();
	    }
		
		if (e.getSource() == c_horariot) {
		    if (c_horariot.getSelectedItem().equals("")) {	
				  } else {
				    t_hora1.setText(c_horariot.getSelectedItem());
				  }
		    addcomboboxnomet();
		}
		
		
		if (e.getSource() == c_nomet) {
			addcomboboxnomet();
		}
		
		
//		if (e.getSource() == l_importt) {
//				tpv.setVisible(true);
//			    tpv.pesquisar_turma();
//			    js.setVisible(false);
//			    l_pesquisar.setVisible(false);
//			    t_pesquisar.setVisible(false);
//			    l_msg.setVisible(false);
//			    btn_limpar1.setVisible(false);
//			    painel.add(tpv);
//			    
//		}
	
		
		
	}

	
	public void mousePressed(MouseEvent e) {

		
	}

	
	public void mouseReleased(MouseEvent e) {
		
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
	
	}

	
	public void mouseExited(MouseEvent e) {

	}


	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btn_registar) {
			adicionar();
		}
		
		if (e.getSource() == btn_atualizar) {
			alterar();
		}
		
		if (e.getSource() == btn_limpar) {
			remover();
		}
		
		if (e.getSource() == btn_limpar1) {
			//tu.pesquisar_bloqueio();
			//tu.settchackbox();
			//tu.bloquearuser(); 
			limparcampos();
		}
	}



	public void keyTyped(KeyEvent e) {
		
		
	}




	public void keyPressed(KeyEvent e) {
		
		
	}



	
	public void keyReleased(KeyEvent e) {

		if (e.getSource() == t_pesquisar) {
			pesquisar_aluno();
	}
		
		if (e.getSource() == t_hora1) {
			addcomboboxnomet();
		}
		
	}

}
