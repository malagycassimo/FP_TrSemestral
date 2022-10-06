package Telas;

import java.awt.Choice;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import net.proteanit.sql.DbUtils;

public class TelaTurma extends JInternalFrame implements MouseListener, ActionListener, KeyListener{
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	
	
	private void adicionar() {
		String sql = "insert into tbturma(Nome,Horario,DataRegisto,NrAlunos,Professor) values (?, ?, ?, ?, ?)";
		 ativarrestricao(t_idusuario.getText());
		try {
			
			int contn = 0;
			int cont = 0;
			if (t_nome.getText().isEmpty() || t_turno.getText().isEmpty() || c_professor.getSelectedItem().equals("") || t_nestudantes.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!!");	
			}else {
				
				
				for (int i = 0; i < tabela.getRowCount(); i++) {
					if (tabela.getModel().getValueAt(i, 1).equals(t_nome.getText())) {
								contn += 1;
					}
				}
				
				for (int i = 0; i < tabela.getRowCount(); i++) {
					if (tabela.getModel().getValueAt(i, 5).equals(c_professor.getSelectedItem().toString())) {
						if (tabela.getModel().getValueAt(i, 2).equals(t_turno.getText())) {
								cont += 1;
						}
					}
				}
				
				//System.out.println(cont);
				if (contn > 0) {
					JOptionPane.showMessageDialog(null, "Ja Existe uma turma com o nome" + t_nome.getText());
				} else {
				if (cont > 0) {
					JOptionPane.showMessageDialog(null, "Este Instrutor(a) ja se encontra ocupado(a) a essa hora");
				}else {
				
				pst = conexao.prepareStatement(sql);
			
			try {
				
				pst.setString(1, t_nome.getText());
				pst.setString(2, t_turno.getText());
				pst.setString(3, t_data.getText());
				pst.setInt(4, Integer.parseInt(t_nestudantes.getText()));
				pst.setString(5, c_professor.getSelectedItem());
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nr de estudantes invalido!!");
				t_nestudantes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(106,12,12)));
			}
			}
			}
			
			
			int adicionar = pst.executeUpdate();
						if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Turma " + t_nome.getText() + " adicionada com sucesso!!");

				 ativarrestricao(t_idusuario.getText());
				t_nome.setText(null);
				//t_data.setText(null);
				t_nestudantes.setText(null);
				t_turno.setText(null);
				c_professor.select("");
				t_nestudantes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
				pesquisar_turma();
				
			}
			}
			
		} catch (SQLException e) {
			//JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
	
	

	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void alterar() {
		String sql = "Update tbturma set Nome = ?, Horario = ?, NrAlunos = ?, Professor = ? where cod = ?";
		 ativarrestricao(t_idusuario.getText());
		try {
			pst = conexao.prepareStatement(sql);
	
			try {
				
				pst.setString(1, t_nome.getText());
				pst.setString(2, t_turno.getText());
				//pst.setString(3, t_data.getText());
				pst.setInt(3, Integer.parseInt(t_nestudantes.getText()));
				pst.setString(4, c_professor.getSelectedItem());
				pst.setString(5, t_codigo.getText());
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Nr de estudantes invalido!!");
				t_nestudantes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(106,12,12)));
			}
			
			if (t_codigo.getText().isEmpty() || t_nome.getText().isEmpty() || t_turno.getText().isEmpty() || c_professor.getSelectedItem().equals("") || t_nestudantes.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos");	
			}else {
			//A linha a baixo actualiza a tabela
			pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados do usuario alterados com sucesso!!");
				
				t_nome.setText(null);
				//t_data.setText(null);
				t_nestudantes.setText(null);
				t_turno.setText(null);
				c_professor.select("");
				t_nestudantes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
				 ativarrestricao(t_idusuario.getText());
				pesquisar_turma();
			
			}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
	}




	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

private void remover() {
	

	String sql = "delete from tbturma where cod = ?";
	try {
		pst = conexao.prepareStatement(sql);
		pst.setString(1, t_codigo.getText());
		
		if (t_codigo.getText().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Selecione o usuario que predende remover!");	
		}else {
		//A linha a baixo actualiza a tabela
		//pst.executeUpdate();
			
			int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario "+t_nome.getText()+" ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
			   if (confirmar == JOptionPane.YES_OPTION) {
				   
		
		int adicionar = pst.executeUpdate();
		if (adicionar > 0) {
			JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!!");
			t_codigo.setText(null);
			limparcampos();
			pesquisar_turma();
		
			}
		}
		}
	
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	//	e.printStackTrace();
	}
	


}






	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

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


/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void filtrar_turma() {
String sql = "select * from tbturma where horario like ?";

try {
	pst = conexao.prepareStatement(sql);
	//passando o conteudo da caixa de pesquisa para ?
	// atencao ao %  - continuacao da string sql
	pst.setString(1,t_filtrado.getText() + "%");
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
		t_turno.setText(tabela.getModel().getValueAt(nr, 2).toString());
		//t_data.setText(tabela.getModel().getValueAt(nr, 3).toString());
		c_professor.select(tabela.getModel().getValueAt(nr, 5).toString());
		t_nestudantes.setText(tabela.getModel().getValueAt(nr, 4).toString());
		 ativarrestricao(t_idusuario.getText());
		
	}
	
	 
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void limparcampos() {
		
		t_codigo.setText(null);
		t_nome.setText(null);
		//t_data.setText(null);
		t_nestudantes.setText(null);
		t_turno.setText(null); 
		c_professor.select("");
		 ativarrestricao(t_idusuario.getText());
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void addcombobox() {
		
		String sql = "select Nome from tbfuncionario where Cargo like 'Instrutor(a)%'";
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			c_professor.removeAll();
			c_professor.addItem("");
			
			while (rs.next()) {
			 c_professor.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo enable'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
//
//public void enablebtn() {
//		
//		if (t_codigo.getText().isEmpty()) {
//			btn_atualizar.setEnabled(false);
//			btn_remover.setEnabled(false);
//			btn_registar.setEnabled(true);
//		}else {
//			btn_atualizar.setEnabled(true);
//			btn_remover.setEnabled(true);
//			btn_registar.setEnabled(false);
//		}
//		
//		
//		
//	}
//	
	
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox filtrar'''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void addcomboboxfiltrar() {
		
		String sql = "select Horario from tbturma";
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			c_filtrar.removeAll();
			c_filtrar.addItem("Tudo");
			while (rs.next()) {
			 c_filtrar.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}
	
	

	// ''''''''''''''''''''''''''''''''''''''''''''''''''''' verificaralunosnaturma '''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	public void verificarnralunosnat() {
		
		int nr = tabela.getSelectedRow();
		int count = 0;
		String sql = "select count(*) from tbaluno where Turma = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, tabela.getModel().getValueAt(nr, 1).toString());
		  	rs = pst.executeQuery();
		  	
		  	while (rs.next()) {
			 count = rs.getInt(1);
			}
		  	
		    t_nralunos.setText("           " + count);
		    t_nralunos1.setText("          " + (Integer.parseInt(tabela.getModel().getValueAt(nr, 4).toString()) - count));
		  	
		 	//System.out.println(count);
		
	} catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
	}
	
	}
	
	
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo ativarprivilegios '''''''''''''''''''''''''''''''''''''''''''''''''''


	public void ativarrestricao(String getid) {
		
		String sql = "select * from tbbloqueio where iduser = ?";

		
		try {
			
			pst = conexao.prepareStatement(sql);
			
		   
			pst.setString(1, getid);
			
			rs = pst.executeQuery();
			
			if (rs.next()) {
				
				if (rs.getBoolean(12) == false) {
				
					btn_registar.setEnabled(false);
				}else {

					if (t_codigo.getText().isEmpty()) {
						btn_registar.setEnabled(true);
					}else {
						btn_registar.setEnabled(false);
					}
					
				}
				
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
				
				if (rs.getBoolean(13) == false) {
					
					btn_remover.setEnabled(false);
				}else {

					if (t_codigo.getText().isEmpty()) {
						btn_remover.setEnabled(false);
					}else {
						btn_remover.setEnabled(true);
					} 
					
				}
				
				//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

				if (rs.getBoolean(14) == false) {
					
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


	
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * */
	
	
	
	JPanel painel,painel1,painel2;
	   Font f1,f2,f3,f31,f4,f5,f6;
	   JButton btn_sair,btn_atualizar,btn_registar,btn_remover,btn_limpar;
	   JLabel l_nomep,l_nomet,l_data,l_funcionario,l_meiop,l_meiop1,l_valor,l_pesquisar,l_nralunos;
       JTextField t_pesquisar,t_codigo,t_nome,t_turno,t_nestudantes,t_data,t_nralunos,t_nralunos1, t_filtrado,t_idusuario;
       Choice c_professor,c_filtrar;
       JTable tabela;
       JScrollPane js;
       JTextArea ta_texto;
      
       
       
       
	public TelaTurma() { 
	
		
		//setUndecorated(true);
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
		
		
		//BasicInternalFrameUI basic = (javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI();
		//for (MouseListener listener:basic.getNorthPane().getMouseListeners()) {
		//	basic.getNorthPane().removeMouseListener(listener);
		///}
		
	
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.CENTER_BASELINE, 14);
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		Date data = new Date();
		DateFormat formato =  DateFormat.getDateInstance(DateFormat.SHORT);Calendar now = Calendar.getInstance();
		
	    class hora implements ActionListener { 
            public void actionPerformed(ActionEvent e) {
            	
           
            	
            	
            }
        }
		
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 870, 530);
	    painel.addMouseListener(this);
	    painel.setLayout(null);
	    
	    painel1 = new JPanel();
	    painel1.setBackground(new Color(249,249,249));
	  //  painel1.setOpaque(true);
	    painel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel1.setBounds(10, 10, 580, 165);
	    painel1.setLayout(null);
	    
	    painel2 = new JPanel();
	    painel2.setBackground(new Color(249,249,249));
	  //  painel1.setOpaque(true);
	    painel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel2.setBounds(20, 80, 385, 85);
	    painel2.setLayout(null);
	    
	    
	  
	    ta_texto = new JTextArea();
	    ta_texto.setText("  Para melhor organizacao recomendamos que criem \n  um padrao de nomes a seguir e que considerem  os \n  espacos das salas!!");
	    ta_texto.setBounds(30,90,365,65);
	    ta_texto.setFont(f3);
	    ta_texto.setEditable(false);
	    //ta_texto.setEnabled(false);
        ta_texto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    ta_texto.setForeground(new Color(150,150,150,80));
	    painel.add(ta_texto);
	    
	    
	    
	     
	    l_nomep = new JLabel("Codigo");
	    l_nomep.setBounds(25,15,120,30);
	    l_nomep.setFont(f5);
	    l_nomep.setForeground(new Color(0,0,0));
	    painel.add(l_nomep);

	    
	    l_nomet = new JLabel("Nome da Turma");
	    l_nomet.setBounds(155,10,100,20);
	    l_nomet.setFont(f5);
	    l_nomet.setForeground(new Color(0,0,0));
	    painel1.add(l_nomet);
	    
	    
	    l_data = new JLabel("Data ");
	    l_data.setBounds(430,15,100,30);
	    l_data.setFont(f5);
	    l_data.setForeground(new Color(0,0,0));
	    painel.add(l_data);
	
	    
	    l_funcionario = new JLabel("Instrutor(a)");
	    l_funcionario.setBounds(30,50,60,20);
	    l_funcionario.setFont(f5);
	    l_funcionario.setForeground(new Color(0,0,0));
	    painel.add(l_funcionario);
	    
	    l_meiop = new JLabel("Horario");
	    l_meiop.setBounds(260,45,120,30);
	    l_meiop.setFont(f5);
	    l_meiop.setForeground(new Color(0,0,0));
	    painel.add(l_meiop);
	     
	    l_meiop1 = new JLabel("N°.Estudantes");
	    l_meiop1.setBounds(415,50,100,20);
	    l_meiop1.setFont(f5);
	    l_meiop1.setForeground(new Color(0,0,0));
	    painel.add(l_meiop1);
	    
	    l_nralunos = new JLabel(" Inseridos");
	    l_nralunos.setBounds(415,115,100,20);
	    l_nralunos.setFont(f5);
	    l_nralunos.setForeground(new Color(125,12,12));
	    painel.add(l_nralunos);
	    
	    l_nralunos = new JLabel(" Restantes");
	    l_nralunos.setBounds(505,115,100,20);
	    l_nralunos.setFont(f5);
	    l_nralunos.setForeground(new Color(0,221,22));
	    painel.add(l_nralunos);
	    
	   
	    
	    l_pesquisar = new JLabel("        pesquisar");
	    l_pesquisar.setBounds(340,185,100,25);
	    l_pesquisar.setFont(f5);
	    l_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    l_pesquisar.setForeground(new Color(0,0,0));
	    painel.add(l_pesquisar);
	
	    t_codigo = new JTextField();
	    t_codigo.setBounds(70,20,80,20);
	    t_codigo.setFont(f6);
	    t_codigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_codigo.setForeground(new Color(100,100,100));
	    t_codigo.setEnabled(false);
	    painel.add(t_codigo);
	    


	    t_nome = new JTextField();
	    t_nome.setBounds(260,20,150,20);
	    t_nome.setFont(f6);
	    t_nome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nome.setForeground(new Color(100,100,100));
	    t_nome.addMouseListener(this);
	   // t_nome.addActionListener(this);
	    painel.add(t_nome);
	    
	    t_turno = new JTextField();
	    t_turno.setBounds(300,50,80,20);
	    t_turno.setFont(f6);
	    t_turno.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_turno.setForeground(new Color(100,100,100));
	    painel.add(t_turno);
	   
	    c_professor = new Choice();
	    c_professor.setBounds(95,50,140,20);
	    c_professor.setFont(f6);
	    //c_professor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    c_professor.setForeground(new Color(100,100,100));
	    painel.add(c_professor);
	    
	    t_nestudantes = new JTextField();
	    t_nestudantes.setBounds(500,50,80,20);
	    t_nestudantes.setFont(f6);
	    t_nestudantes.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nestudantes.setForeground(new Color(100,100,100));
	    painel.add(t_nestudantes);
	    
	    
	    t_nralunos = new JTextField();
	    t_nralunos.setBounds(415,135,80,30);
	    t_nralunos.setFont(f6);
	    t_nralunos.setEnabled(false);
	    t_nralunos.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nralunos.setForeground(new Color(100,100,100));
	    painel.add(t_nralunos);
	    
	    t_nralunos1 = new JTextField();
	    t_nralunos1.setBounds(500,135,80,30);
	    t_nralunos1.setFont(f6);
	    t_nralunos1.setEnabled(false);
	    t_nralunos1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nralunos1.setForeground(new Color(0,221,22));
	    painel.add(t_nralunos1);
	    
	  
	
	    t_data = new JTextField();
	    t_data.setText("      "+now.getInstance().getTime().getDate()+" - "+  now.getTime().getMonth() +" - "+ now.getInstance().getWeekYear());
	    t_data.setBounds(465,20,115,20);
	    t_data.setFont(f6);
	    t_data.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_data.setForeground(new Color(125,12,12));
	    t_data.setEnabled(false);
	    painel.add(t_data);
	    
	    t_idusuario = new JTextField();
	    t_idusuario.setBounds(70,50,180,20);
	    t_idusuario.setFont(f6);
	    t_idusuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_idusuario.setForeground(new Color(100,100,100));
	  //  painel.add(t_foto);
	    
	    
	    
	    c_filtrar = new Choice();
	    c_filtrar.setBounds(20,185,150,25);
	    c_filtrar.setFont(f6);
	    c_filtrar.setForeground(new Color(100,100,100));
	    c_filtrar.addItem("Todas");
	    c_filtrar.addMouseListener(this);
	    painel.add(c_filtrar);
	  
	    
	    t_filtrado = new JTextField();
	    t_filtrado.setBounds(450,185,309,25);
	    t_filtrado.setFont(f5);
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_filtrado.addMouseListener(this);
	    
	    t_pesquisar = new JTextField();
	    t_pesquisar.setBounds(450,185,309,25);
	    t_pesquisar.setFont(f5);
	    t_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar.setForeground(new Color(0,0,0));
	    t_pesquisar.addKeyListener(this);
	    t_pesquisar.addMouseListener(this);
	    painel.add(t_pesquisar);
	    
	    

	    
	    btn_atualizar = new JButton("Atualizar");
	    btn_atualizar.setBounds(600,10,160,30);
	    btn_atualizar.setFont(f5);
	   // btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_atualizar.setBackground(new Color(239,239,239));
	    btn_atualizar.addActionListener(this);
	    btn_atualizar.setForeground(new Color(0,0,0));
	    btn_atualizar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/lapis1.png")));
	    painel.add(btn_atualizar);
	    
	    btn_registar = new JButton("Registar");
	    btn_registar.setBounds(600,55,160,30);
	    btn_registar.setFont(f5);
	    btn_registar.addActionListener(this);
	    //btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_registar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/adic.png")));
	    btn_registar.setBackground(new Color(239,239,239));
	    btn_registar.setForeground(new Color(0,0,0));
	    painel.add(btn_registar);
	    
	    btn_remover = new JButton("Remover");
	    btn_remover.setBounds(600,100,160,30);
	    btn_remover.setFont(f5);
	    //btn_remover.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_remover.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/remov.png")));
	    btn_remover.setBackground(new Color(239,239,239));
	    btn_remover.setForeground(new Color(0,0,0));
	    btn_remover.addActionListener(this);
	    painel.add(btn_remover);
	    
	    
	    btn_limpar = new JButton("Limpar");
	    btn_limpar.setBounds(600,145,160,30);
	    btn_limpar.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_limpar.setBackground(new Color(239,239,239));
	    btn_limpar.setForeground(new Color(0,0,0));
	    btn_limpar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/clean.png")));
	    btn_limpar.addActionListener(this);
	    painel.add(btn_limpar);
	     

	    
	    
	    String [] nome = {"Cod","Nome ", "Horario","DataRegisto","NrAlunos","Instrutor(a)"};
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
		//tabela.getTableHeader().getColumnModel().getColumn(0).setPreferredWidth(0);
	    js = new JScrollPane(tabela);
		js.setBackground(Color.WHITE);
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		///js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js.setBounds(10, 222, 749, 175);
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
	 					TelaTurma tt = new TelaTurma();
	 					
	 				} catch (Exception e) {
	 					e.printStackTrace();
	 				}
	 			}
	 		});	
	 	}

	 	
	 	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	TelaPreVisualizacao tpv = new TelaPreVisualizacao();
	 	
	 	public void mouseClicked(MouseEvent e) {
	 		
	 		if (e.getSource() == btn_sair) {
	 			int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar saida?","   Mensagem", JOptionPane.YES_NO_OPTION);
	 			   if (confirmar == JOptionPane.YES_OPTION) {
	 				System.exit(0);
	 			}
	 		}
	 	
	 		
	 		if (e.getSource() == t_pesquisar) {
				pesquisar_turma();
			}
	 		
	 		
	 		if (e.getSource() == tabela) {
				sett_campos();
				verificarnralunosnat();
			}
	 		
//	 		if (e.getSource() == c_filtrar) {
//	 			if (c_filtrar.getSelectedItem().equals("")) {
//				}else if (c_filtrar.getSelectedItem().equals("Tudo")) {
//					pesquisar_turma();
//				} else {
//				t_filtrado.setText(c_filtrar.getSelectedItem());
//			    }
//	 			filtrar_turma();
//	 		}
//	 		
	 		
	 		//if (e.getSource() == t_nome) {
	 			//sett_campos();
		//	}
			
	 		
	 	}

	 	
	 	public void mousePressed(MouseEvent e) {

	 		
	 	}

	 	
	 	public void mouseReleased(MouseEvent e) {
	 		
	 		
	 	}

	 	
	 	public void mouseEntered(MouseEvent e) {
	  

	 		if (e.getSource() == painel) {
			   if (tpv.isVisible()) {
				tpv.setVisible(false);
			    }
			}
	 		
	 		if (e.getSource() == tabela) {
				filtrar_turma();
			}
	 		
	 	}

	 	
	 	public void mouseExited(MouseEvent e) {

	 		
	 	}


		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == btn_registar) {
				pesquisar_turma();
				adicionar();
			}
			

			if (e.getSource() == btn_atualizar) {
				alterar();
			}
			
			if (e.getSource() == btn_remover) {
				remover();
			}
			
			if (e.getSource() == btn_limpar) {
				limparcampos();
			}
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


