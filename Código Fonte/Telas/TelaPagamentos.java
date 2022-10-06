package Telas;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.exceptions.ExceptionFactory;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;

public class TelaPagamentos extends JInternalFrame implements MouseListener, KeyListener, ActionListener{

	
	
	

/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * */	

	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	
	private void adicionar() {
			ativarrestricao(t_idusuario.getText());
		String sql = "insert into tbpagamentos(TipoPagamento,Aluno,IdAluno,MeioPagamento,Valor,Funcionario,DataPagamento) values (?, ?, ?, ?, ?, ?, ?)";
		
	//	int i = tabela.getSelectedRow();
		 
		try {
			
			
			if (t_alunop.getText().isEmpty() /*|| t_funcionariop.getText().isEmpty()|| */||t_valor.getText().isEmpty()  || c_meiop.getSelectedItem().isEmpty() || c_meiop.getSelectedItem().equals("") ||  c_tipo.getSelectedItem().equals("") || t_datap.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos campos obrigatorios");	
			}else {
			
				pst = conexao.prepareStatement(sql);
				
				if (Double.parseDouble(t_valor.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "O valor nao pode ser negativo!!");
				}else {
				try {
				
				pst.setString(1, c_tipo.getSelectedItem().toString());
				pst.setString(2, t_alunop.getText());
				pst.setString(3, t_codigoa.getText());
				pst.setString(4, c_meiop.getSelectedItem().toString()); 
				pst.setDouble(5, Double.parseDouble(t_valor.getText()));
				pst.setString(6, t_funcionariop.getText());
				pst.setString(7, t_datap.getText().toString());
				
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, "Valor Invalido!!");
					t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(106,12,12)));
					//t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
				}
				
				
			int adicionar = pst.executeUpdate();
			System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Pagamento registado com sucesso!!");
				
				t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
				limparcampos();
				pesquisar_pagamento();
				
			
				
//	       			tabela1.getSelectedRow();
//	       			int i;
//	       			for ( i = 0; i <tabela1.getRowCount(); i++) {
//				if (i == tabela1.getRowCount()) {
//					tabela1.getSelectedRow();
//				}
//			}
	       			
			}
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
public void pesquisar_pagamento() {
	//String sql = "select * from tbpagamentos where Aluno like ?";
	//String sql = "select Cod,TipoPagamento,Aluno,MeioPagamento,Valor,DataPagamento from tbpagamentos where TipoPagamento like ? and Aluno like ?";
	String sql = "select Cod, TipoPagamento, Aluno, IdAluno, MeioPagamento, Valor, Funcionario, DataPagamento from tbpagamentos where TipoPagamento like ? and Aluno like ? and Activo = 1";

	try {
		pst = conexao.prepareStatement(sql);
		//passando o conteudo da caixa de pesquisa para ?
		// atencao ao %  - continuacao da string sql
		pst.setString(1, c_filtrarp.getSelectedItem() + "%");
		pst.setString(2, t_pesquisar1.getText() + "%");
		rs = pst.executeQuery();
		// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
		
		tabela1.setModel(DbUtils.resultSetToTableModel(rs));
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}
	
}

/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Aluno '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

public void pesquisar_aluno() {
String sql = "select Cod, Nome from tbaluno where nome like ?";

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
		
		int nr = tabela1.getSelectedRow();
		
		t_codigo.setText(tabela1.getModel().getValueAt(nr, 0).toString());
		c_tipo.select(tabela1.getModel().getValueAt(nr, 1).toString());
		t_alunop.setText(tabela1.getModel().getValueAt(nr, 2).toString());
		t_codigoa.setText(tabela1.getModel().getValueAt(nr, 3).toString());
		c_meiop.select(tabela1.getModel().getValueAt(nr, 4).toString());
		t_valor.setText(tabela1.getModel().getValueAt(nr, 5).toString());
//		t_funcionariop.setText(tabela.getModel().getValueAt(nr, 6).toString());
		
		ativarrestricao(t_idusuario.getText());
		
		
	    
	}
	
	
public void sett_aluno() {
		
		int nr = tabela.getSelectedRow();
		
		t_alunop.setText(tabela.getModel().getValueAt(nr, 1).toString());
		t_codigoa.setText(tabela.getModel().getValueAt(nr, 0).toString());
		ativarrestricao(t_idusuario.getText());
	}
	
	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void alterar() {
		String sql = "Update tbpagamentos set TipoPagamento = ?, Aluno = ?, IdAluno = ?, MeioPagamento = ?, Valor = ?  where Cod = ?";
	
		ativarrestricao(t_idusuario.getText());
		try {

			if (t_alunop.getText().isEmpty() || t_funcionariop.getText().isEmpty()|| t_valor.getText().isEmpty()  || c_meiop.getSelectedItem().isEmpty() || c_meiop.getSelectedItem().equals("") || c_tipo.getSelectedItem().isEmpty() || c_tipo.getSelectedItem().equals("") || t_datap.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha  os campos obrigatorios!!");	
			}else {
				pst = conexao.prepareStatement(sql);
				
				if (Double.parseDouble(t_valor.getText()) < 0) {
					JOptionPane.showMessageDialog(null, "O valor nao pode ser negativo!!");
				}else {
				try {
					
					pst.setString(1, c_tipo.getSelectedItem().toString());
					pst.setString(2, t_alunop.getText());
					pst.setString(3, t_codigoa.getText());
					pst.setString(4, c_meiop.getSelectedItem().toString()); 
					//pst.setString(5, t_valor.getText());
					pst.setDouble(5, Double.parseDouble(t_valor.getText()));
				//	pst.setString(7, t_funcionariop.getText());
					
					} catch (Exception e) {
						
						JOptionPane.showMessageDialog(null, "Valor Invalido!!");
						t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(106,12,12)));
						//t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
						
					}
				
				
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados do Pagamento alterados com sucesso!!");
				
				limparcampos();
				t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
			   pesquisar_pagamento();
				
			}
			}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo PreVisualizacao '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void previsualizacao() {
		
	String sql =	"select  * from tbescola where cod = 1";
	int nr = tabela1.getSelectedRow();
	
	try {
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		
		if (rs.next()) {
			
			total = 0;
			String indice = tabela1.getModel().getValueAt(nr, 3).toString();
			String tipopa = tabela1.getModel().getValueAt(nr, 1).toString();
			System.out.println(indice+" | "+tipopa);

			
			for (int i = 0; i < tabela1.getRowCount(); i++) {
				if ((indice.equals(tabela1.getModel().getValueAt(i, 3).toString())) & (tipopa.equals(tabela1.getModel().getValueAt(i, 1).toString()))) {
					total += Double.parseDouble(tabela1.getModel().getValueAt(i, 5).toString());
				//	System.out.println(tabela1.getModel().getValueAt(i, 3).toString()+" | "+tabela1.getModel().getValueAt(i, 1).toString());
				
				}
			}
			

		
		tipopagamento.setText(tabela1.getModel().getValueAt(nr, 1).toString());
		datapagamento.setText(tabela1.getModel().getValueAt(nr, 7).toString());
	    nomeestudante.setText("Nome: "+tabela1.getModel().getValueAt(nr, 2).toString());
	    valorpago.setText("Valor: "+tabela1.getModel().getValueAt(nr, 5).toString() + "MT");
	    nomefuncionario.setText("Emitido por: "+tabela1.getModel().getValueAt(nr, 6).toString());
	    meiopago.setText("MeioPagamento: "+tabela1.getModel().getValueAt(nr, 4).toString());
	    totalpago.setText("Total: "+total);
	    fundopa.setVisible(true);
			l_logotipo.setIcon(new ImageIcon(rs.getString(10)));
			
		ativarrestricao(t_idusuario.getText());
		
		
			
			
			
		}
		
		
	
	} catch (SQLException e) {
        JOptionPane.showMessageDialog(nomeestudante, e);
	}
	
	
	//try {
	//	pst = conexao.prepareStatement(sql);
		
//		pst.setString(1, tabela1.getModel().getValueAt(nr, 3).toString());
//		pst.setString(2, tabela1.getModel().getValueAt(nr, 1).toString());
		
//		rs = pst.executeQuery();
//		
		
		
//	} catch (SQLException e) {
//		e.printStackTrace();
//	}
		
		
		
		 
		
		
	}
	
	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
//	
//	private void remover() {
//		ativarrestricao(t_idusuario.getText());
//			
//			if (t_codigo.getText().isEmpty() ){
//				JOptionPane.showMessageDialog(null, "Selecione o Funcionario que predende remover!");	
//			}else {
//			
//			int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuario?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
//			if (confirmar == JOptionPane.YES_OPTION) {
//				
//				
//				String sql = "delete from tbpagamentos where Cod = ?";
//				try {
//					pst = conexao.prepareStatement(sql);
//					pst.setString(1, t_codigo.getText());
//				
//				int adicionar = pst.executeUpdate();
//			    if (adicionar > 0) {
//				JOptionPane.showMessageDialog(null, "Operacao efectuada com sucesso!!");
//				
//				limparcampos();
//				pesquisar_pagamento();
//
//				}
//			    
//			   } catch (SQLException e) {
//			       JOptionPane.showMessageDialog(null, e);
//	     	  }	
//		} else {
//	    	JOptionPane.showMessageDialog(null, "Operação cancelada!!");
//	            }		
//		 	}
//		}
//	
	
	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	
	public void remover1() {
		
		ativarrestricao(t_idusuario.getText());
		
		if (t_codigo.getText().isEmpty() ){
			JOptionPane.showMessageDialog(null, "Selecione o Pagamento que predende remover!");	
		}else {
		
		int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este pagamento?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
		if (confirmar == JOptionPane.YES_OPTION) {
			
			
			String sql = "update tbpagamentos set Activo = 0 where Cod = ?";
			try {
				pst = conexao.prepareStatement(sql);
				pst.setString(1, t_codigo.getText());
			
			int adicionar = pst.executeUpdate();
		    if (adicionar > 0) {
			JOptionPane.showMessageDialog(null, "Operacao efectuada com sucesso!!");
			
			limparcampos();
			pesquisar_pagamento();

			}
		    
		   } catch (SQLException e) {
		       JOptionPane.showMessageDialog(null, e);
     	  }	
	} else {
    	JOptionPane.showMessageDialog(null, "Operação cancelada!!");
            }		
	 	}
	
	}
	
	

	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo btn '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
//	
//	public void enablebtn() {
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
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Limpar Campos '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void limparcampos() {
		
		t_codigo.setText(null);
		t_alunop.setText(null);
		t_codigoa.setText(null);
		t_valor.setText(null);
		c_tipo.select("");
		c_meiop.select("");

		ativarrestricao(t_idusuario.getText());
	}
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''' Add Combobox meiopagamento'''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
public void addcomboboxmeio() {
		
		String sql = "select nome from tbmeiop";
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
				c_meiop.removeAll();
				c_meiop.addItem("");
			while (rs.next()) {			
			 c_meiop.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}




/*''''''''''''''''''''''''''''''''''''''''''''''' Add Combobox tipopagamento'''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
public void addcomboboxtipo() {
	
	String sql = "select nome from tbtipop";
	try {
		pst = conexao.prepareStatement(sql);
		rs = pst.executeQuery();
		
		c_tipo.removeAll();
		c_filtrarp.removeAll();
		
		c_tipo.addItem("");
		c_filtrarp.addItem("");
		while (rs.next()) {
			
		 c_tipo.addItem(rs.getString(1));
		 c_filtrarp.addItem(rs.getString(1));
		}
	} catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e);
	}
}



/*''''''''''''''''''''''''''''''''''''''''''''''' Gerar Relatorio '''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
public void gerarrelatorio() {
	
	int nr = tabela1.getSelectedColumn();
	
	
	try {
		conexao = modulo.ModuloConexao.conector();
		HashMap<String,Object> parametro = new HashMap<String,Object>();
		parametro.put("IdAluno", t_codigoa.getText());
		parametro.put("tipopa", c_tipo.getSelectedItem());
		
 		JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\recibo.jasper", parametro,conexao);
		JasperViewer jw = new JasperViewer(jp,false);
		jw.setVisible(true);
		
		//JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Pagamentos.pdf");
		
	} catch (JRException e) {
		e.printStackTrace();
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
			
			if (rs.getBoolean(16) == false) {
			
				btn_registar.setEnabled(false);
			}else {

				if (t_codigo.getText().isEmpty()) {
					btn_registar.setEnabled(true);
				}else {
					btn_registar.setEnabled(false);
				}
				
			}
			
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
			
			if (rs.getBoolean(17) == false) {
				
				btn_remover.setEnabled(false);
			}else {

				if (t_codigo.getText().isEmpty()) {
					btn_remover.setEnabled(false);
				}else {
					btn_remover.setEnabled(true);
				} 
				
			}
			
			//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

			if (rs.getBoolean(18) == false) {
				
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




/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 *::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
 * */	


	
	JPanel painel,painel1,painel2,painel3;
	   Font f1,f2,f3,f31,f4,f5,f6;
	   JButton btn_imprimir,btn_registar,btn_atualizar,btn_remover,btn_limpar;
	   JLabel l_nomep,l_nomeA,l_meiop,l_valor,l_pesquisar,l_codigo,l_pesquisar1,l_pre,l_mt,l_pesquisarl;
	   JLabel l_logotipo,tipopagamento,datapagamento,valorpago,nomeestudante,nomefuncionario, totalpago,meiopago,fundopa;
       JTextField t_pesquisar,t_tipop,t_datap,t_alunop,t_funcionariop,t_valor,t_codigo,t_codigoa,t_pesquisar1,t_idusuario;
       Choice c_tipo,c_meiop,c_filtrarp;
       JTable tabela,tabela1;
       JScrollPane js,js1;
       JDesktopPane desk1; 
       double total = 0;
       
       
     //  TelaPreVisualizacao tpv = new TelaPreVisualizacao();   
      TelaEscola te = new TelaEscola();
       
       
       
	public TelaPagamentos() {
		
		//setUndecorated(true);
				setVisible(true);
			    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setResizable(false);
				setLayout(null);
				setSize(769,441);
				setLocation(0, -25);
				//setClosable(true);
				setMaximizable(true);
				setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, getBackground()));
				setFrameIcon(new ImageIcon(TelaAluno.class.getResource("/imgs/add.png")));
				//setIconifiable(true);
				
				
				
				BasicInternalFrameUI basic = (javax.swing.plaf.basic.BasicInternalFrameUI)this.getUI();
				for (MouseListener listener:basic.getNorthPane().getMouseListeners()) {
					basic.getNorthPane().removeMouseListener(listener);
				}
				
				
			
	
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 14);
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		
		Date data = new Date();
		DateFormat formato =  DateFormat.getDateInstance(DateFormat.SHORT);
		
	    class hora implements ActionListener { 
            public void actionPerformed(ActionEvent e) {
            	Calendar now = Calendar.getInstance();
            // l_hora.setText(String.format("%1$tH : %1$tM : %1$tS", now));
            
            	
            }
        }
	    
	    Timer timer = new Timer(1000, new hora());
	     timer.start();
	     
	     Calendar now = Calendar.getInstance();
		
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 870, 530);
	    painel.addMouseListener(this);
	    painel.setLayout(null);
	    
	    painel1 = new JPanel();
	    painel1.setBackground(new Color(249,249,249));
	    painel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel1.setBounds(10, 10, 440, 245);
	    painel1.setLayout(null);
	    
	    painel2 = new JPanel();
	    painel2.setBackground(new Color(249,249,249));
	    painel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel2.setBounds(460, 10, 300, 395);
	    painel2.setLayout(null);
	    
	    desk1 = new JDesktopPane();
	    desk1.setBackground(new Color(249,249,249));
	    desk1.setBounds(462, 40, 296, 364); 
	    desk1.setBackground(new Color(231,231,231));
	    desk1.setLayout(null);
	    //desk1.add(tpv);
	    
	    painel3 = new JPanel();
	    painel3.setBackground(new Color(249,249,249));
	    painel3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel3.setBackground(new Color(100,100,100));
	    painel3.setBounds(460, 10, 300, 30);
	    painel3.setLayout(null);
	    
	   // tpv.setVisible(true);
	    //painel3.add(tpv);
	    
	    
	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	   
	    l_logotipo = new JLabel();
	    l_logotipo.setBounds(445,30,130,120);
	    l_logotipo.setFont(f5);
	  //  l_logotipo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_logotipo.setForeground(new Color(255,255,255));
	    //l_logotipo.setIcon(te.tabela.getModel().getValueAt(0, 9).toString());
	    painel.add(l_logotipo);
	    

	    tipopagamento = new JLabel();
		tipopagamento.setBounds(480, 150, 500, 40);
		tipopagamento.setForeground(new Color(66,66,66));
		tipopagamento.setFont(f5);
		painel.add(tipopagamento);
		
		
		datapagamento = new JLabel();
		datapagamento.setBounds(640, 365, 500, 40);
		datapagamento.setForeground(new Color(66,66,66));
		datapagamento.setFont(f5);
		painel.add(datapagamento);
		
		nomeestudante = new JLabel();
		nomeestudante.setBounds(480, 190, 500, 40);
		nomeestudante.setForeground(new Color(66,66,66));
		nomeestudante.setFont(f5);
		painel.add(nomeestudante);
		
		valorpago = new JLabel();
		valorpago.setBounds(650, 150, 500, 40);
		valorpago.setForeground(new Color(66,66,66));
		valorpago.setFont(f5);
		painel.add(valorpago);
		
		totalpago = new JLabel();
		totalpago.setBounds(480, 320, 300, 40);
		totalpago.setForeground(new Color(66,66,66));
		totalpago.setFont(f3);
		painel.add(totalpago);
		
		meiopago = new JLabel();
		meiopago.setBounds(480, 170, 500, 40);
		meiopago.setForeground(new Color(66,66,66));
		meiopago.setFont(f5);
		painel.add(meiopago);
		
		nomefuncionario = new JLabel();
		nomefuncionario.setBounds(500, 290, 240, 40);
		nomefuncionario.setForeground(new Color(66,66,66));
		nomefuncionario.setFont(f5);
		painel.add(nomefuncionario);
		
		fundopa = new JLabel();
		fundopa.setBounds(480, 230, 240, 300);
		fundopa.setForeground(new Color(66,66,66));
		//fundopa.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/shop.png")));
		fundopa.setFont(f5);
		fundopa.setVisible(false);
		painel.add(fundopa);
	    
	    
	    
	  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    l_pre = new JLabel("Pre-Visualização ");
	    l_pre.setBounds(470,10,150,30);
	    l_pre.setFont(f5);
	    l_pre.setForeground(new Color(255,255,255));
	   // painel.add(l_pre);
	    
	    
	    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	   
	    //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	    l_mt = new JLabel("MT");
	    l_mt.setBounds(420,80,70,20);
	    l_mt.setFont(f5);
	    l_mt.setForeground(new Color(66,66,66));
	    painel.add(l_mt);
	    
	    l_codigo = new JLabel("Cod");
	    l_codigo.setBounds(25,15,120,30);
	    l_codigo.setFont(f5);
	    l_codigo.setForeground(new Color(0,0,0));
	    painel.add(l_codigo);

	    
	    l_nomep = new JLabel("Tipo de Pagamento");
	    l_nomep.setBounds(140,15,110,30);
	    l_nomep.setFont(f5);
	    l_nomep.setForeground(new Color(0,0,0));
	    painel.add(l_nomep);

	   
	    
	    l_nomeA = new JLabel("Nome do Aluno");
	    l_nomeA.setBounds(15,40,100,20);
	    l_nomeA.setFont(f5);
	    l_nomeA.setForeground(new Color(0,0,0));
	    painel1.add(l_nomeA);
	    
	    
	    l_meiop = new JLabel("Meio de Pagamento");
	    l_meiop.setBounds(25,75,120,30);
	    l_meiop.setFont(f5);
	    l_meiop.setForeground(new Color(0,0,0));
	    painel.add(l_meiop);
	    
	    l_valor = new JLabel("Valor");
	    l_valor.setBounds(310,75,120,30);
	    l_valor.setFont(f5);
	    l_valor.setForeground(new Color(0,0,0));
	    painel.add(l_valor);
	       
	    
	    /*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * */
	    
	    /*
	    ptipop = new JLabel("Valor");
	    ptipop.setBounds(400,100,120,30);
	    ptipop.setFont(f5);
	    ptipop.setForeground(new Color(0,0,0));
	    painel.add(ptipop);
	    
	    
	    pNome = new JLabel("Valor");
	    ptipop.setBounds(400,100,120,30);
	    ptipop.setFont(f5);
	    ptipop.setForeground(new Color(0,0,0));
	    painel.add(ptipop);
	    
	    
*/
	    
	    
	    /*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * */

	    
	    l_pesquisar = new JLabel("");
	    l_pesquisar.setBounds(20,115,30,20);
	    l_pesquisar.setFont(f5);
	    l_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
	    l_pesquisar.setForeground(new Color(0,0,0));
	    l_pesquisar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/pesq.png")));
	    painel.add(l_pesquisar);
	    
	    
	    l_pesquisar1 = new JLabel("");
	    l_pesquisar1.setBounds(200,260,30,20);
	    l_pesquisar1.setFont(f5);
	    l_pesquisar1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231,231,231)));
	    l_pesquisar1.setForeground(new Color(0,0,0));
	    l_pesquisar1.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/pesq.png")));
	    painel.add(l_pesquisar1);
	    
	    
	    
	    t_codigo = new JTextField();
	    t_codigo.setBounds(60,20,60,20);
	    t_codigo.setFont(f6);
	    t_codigo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_codigo.setForeground(new Color(100,100,100));
	    t_codigo.setEnabled(false);
	    painel.add(t_codigo);
	   
	    c_tipo = new Choice();
	    c_tipo.setBounds(250,20,190,20);
	    c_tipo.setFont(f6);
	    // c_tipo.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    c_tipo.setForeground(new Color(100,100,100));
	    c_tipo.addItem("");
	    painel.add(c_tipo);  
	    
	    
	    t_datap = new JTextField();
	    t_datap.setBounds(460,20,120,20);
	    t_datap.setFont(f6);
	    t_datap.setText("" + now.getInstance().getTime().getDate()+" de "+ now.getInstance().getTime().getMonth() +" de "+ now.getInstance().getWeekYear());
	    t_datap.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_datap.setForeground(new Color(100,100,100));
	    t_datap.setVisible(false);
	   // painel.add(t_datap);
	    
	    t_alunop = new JTextField();
	    t_alunop.setBounds(120,50,230,20);
	    t_alunop.setFont(f6);
	    t_alunop.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_alunop.setForeground(new Color(0,0,0));
	    t_alunop.setEnabled(false);
	    painel.add(t_alunop);
	    
	     t_codigoa = new JTextField();
	     t_codigoa.setBounds(390,50,50,20);
	     t_codigoa.setFont(f6);
	     t_codigoa.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	     t_codigoa.setForeground(new Color(0,0,0));
	     t_codigoa.setEnabled(false);
	     painel.add(t_codigoa);
	    
	    
	    t_funcionariop = new JTextField();
	    t_funcionariop.setBounds(415,50,165,20);
	    t_funcionariop.setFont(f6);
	    t_funcionariop.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_funcionariop.setForeground(new Color(100,100,100));
	    t_funcionariop.setVisible(false);
	    //painel.add(t_funcionariop);
	    
	    c_meiop = new Choice();
	    c_meiop.setBounds(145,80,140,20);
	    c_meiop.setFont(f6);
	   // c_meiop.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    c_meiop.setForeground(new Color(100,100,100));
	    c_meiop.addItem("");
	    painel.add(c_meiop);
	    
	    
	    t_valor = new JTextField();
	    t_valor.setBounds(350,80,65,20);
	    t_valor.setFont(f5);
	    t_valor.setForeground(new Color(100,100,100));
	    t_valor.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel.add(t_valor);
	    
	    
	    t_idusuario = new JTextField();
	    t_idusuario.setBounds(70,50,180,20);
	    t_idusuario.setFont(f6);
	    t_idusuario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_idusuario.setForeground(new Color(100,100,100));
	    
	    c_filtrarp = new Choice();
	    c_filtrarp.setBounds(20,259,150,16);
	    c_filtrarp.setFont(f6);
	    c_filtrarp.setForeground(new Color(100,100,100));
	    c_filtrarp.addItem("Todos");
	    c_filtrarp.addMouseListener(this);
	    painel.add(c_filtrarp);
	    
	    
	    t_pesquisar = new JTextField();
	    t_pesquisar.setBounds(60,115,200,20);
	    t_pesquisar.setFont(f5);
	    t_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar.setForeground(new Color(0,0,0));
	    t_pesquisar.addKeyListener(this);
	    t_pesquisar.addMouseListener(this);
	    painel.add(t_pesquisar);
	    
	    
	    t_pesquisar1 = new JTextField();
	    t_pesquisar1.setBounds(240,260,210,20);
	    t_pesquisar1.setFont(f5);
	    t_pesquisar1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar1.setForeground(new Color(0,0,0));
	    t_pesquisar1.addKeyListener(this);
	    t_pesquisar1.addMouseListener(this);
	    painel.add(t_pesquisar1);
	    
	     String [] nome = {"Cod","Nome"};
	    tabela = new JTable();
		tabela.setFont(f5);
		DefaultTableModel dtml = new DefaultTableModel(nome, 0);	
		tabela.setModel(dtml);
		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		tabela.setBackground(Color.white);
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(0).setResizable(false);
		tabela.addMouseListener(this);
		js = new JScrollPane(tabela);
		js.setBackground(Color.WHITE);
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		//js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js.setBounds(20, 145, 240, 100);
		painel.add(js);
		
		
	    String [] nome1 = {"Cod","TipoPagamento", "Aluno ","IdAluno","MeioPagamento","Valor","Funcionario","DataPagamento"};
	    tabela1 = new JTable();
		tabela1.setFont(f5);
		
		DefaultTableModel dtml1 = new DefaultTableModel(nome1, 0);
		
		tabela1.setModel(dtml1);
		tabela1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		tabela1.setBackground(Color.white);
		tabela1.addMouseListener(this);
	    js1 = new JScrollPane(tabela1);
		js1.setBackground(Color.WHITE);
		js1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		///js.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		js1.setBounds(10, 285, 440, 120);
		painel.add(js1); 
	    
	    
	    btn_registar = new JButton("Registar");
	    btn_registar.setBounds(280,115,160,25);
	    btn_registar.setFont(f5);
	    btn_registar.addActionListener(this);
	    //btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_registar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/adic.png")));
	    btn_registar.setBackground(new Color(239,239,239));
	    btn_registar.setForeground(new Color(0,0,0));
	    painel.add(btn_registar);
	    
	    
		btn_atualizar = new JButton("Atualizar");
	    btn_atualizar.setBounds(280,150,160,25);
	    btn_atualizar.setFont(f5);
	    // btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_atualizar.setBackground(new Color(239,239,239));
	    btn_atualizar.addActionListener(this);
	    btn_atualizar.setForeground(new Color(0,0,0));
	    btn_atualizar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/lapis1.png")));
	    painel.add(btn_atualizar);
	    
	   
	    btn_remover = new JButton("Remover");
	    btn_remover.setBounds(280,185,160,25);
	    btn_remover.setFont(f5);
	    //btn_remover.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_remover.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/remov.png")));
	    btn_remover.setBackground(new Color(239,239,239));
	    btn_remover.setForeground(new Color(0,0,0));
	    btn_remover.addActionListener(this);
	    painel.add(btn_remover);
	    
	    
	    btn_limpar = new JButton("Limpar");
	    btn_limpar.setBounds(280,220,160,25);
	    btn_limpar.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_limpar.setBackground(new Color(239,239,239));
	    btn_limpar.setForeground(new Color(0,0,0));
	    btn_limpar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/clean.png")));
	    btn_limpar.addActionListener(this);
	    painel.add(btn_limpar);
		
	    
		
	    btn_imprimir = new JButton(" Imprimir ");
	    btn_imprimir.setBounds(640, 15, 110, 20);
	    btn_imprimir.setForeground(new Color(239,239,239));
	    btn_imprimir.setBackground(new Color(239,239,239));
	    btn_imprimir.setContentAreaFilled(false);
	    btn_imprimir.setFont(f5); 
	    btn_imprimir.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/impr.png")));
	    btn_imprimir.addMouseListener(this);
	    btn_imprimir.addActionListener(this);
		painel.add(btn_imprimir);
		
		
	    
		    
            painel.add(desk1);		
 		    painel.add(painel3);
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
	 			TelaPagamentos	tp =	new TelaPagamentos();
	 			tp.addcomboboxmeio();
	 				} catch (Exception e) {
	 					e.printStackTrace();
	 				}
	 			}
	 		});	
	 	}

	 	
	 	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	public void mouseClicked(MouseEvent e) {
	 		
	 		if (e.getSource() == tabela1) {
				sett_campos();
				previsualizacao();
			}
	 		
	 		if (e.getSource() == tabela) {
				sett_aluno();
			}
	 		
	 		
	 		if (e.getSource() == t_pesquisar) {
				pesquisar_aluno();
		}
			
			if (e.getSource() == t_pesquisar1) {
				pesquisar_pagamento();
		}
			
	
	 		
	 		if (e.getSource() == c_filtrarp) {
	 			if (c_filtrarp.getSelectedItem().equals("")) {
	 				pesquisar_pagamento();
				}else {
				pesquisar_pagamento();
			       }
	 			}
	 		
			
	 	}

	 	
	 	public void mousePressed(MouseEvent e) {

	 		
	 	}

	 	
	 	public void mouseReleased(MouseEvent e) {
	 		
	 		
	 	}

	 	
	 	public void mouseEntered(MouseEvent e) {
	  
	 		if (e.getSource() == btn_imprimir) {
	 			btn_imprimir.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(231, 231, 231)));
			}
	 		
	 		if (e.getSource() == tabela1) {
				pesquisar_pagamento();
			}

	 		if (e.getSource() == painel) {
				pesquisar_pagamento();
			}
	 	
	 		
	 	}

	 	
	 	public void mouseExited(MouseEvent e) {

	 		if (e.getSource() == btn_imprimir) {
	 			btn_imprimir.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(231, 231, 231)));
			}
	 		
	 		
	 		
	 	}



		public void actionPerformed(ActionEvent e) {
			
			if (e.getSource() == btn_registar) {
				adicionar();
			}
			
			if (e.getSource() == btn_atualizar) {
				alterar();
			}
			
			if (e.getSource() == btn_remover) {
				remover1();
			}
			
			if (e.getSource() == btn_limpar) {
				limparcampos();
			}
			
			if (e.getSource() == c_filtrarp) {
				if (c_filtrarp.getSelectedItem().equalsIgnoreCase("")) {
					
				}else {
				pesquisar_pagamento();
				}
			}
			

			if (e.getSource() == btn_imprimir) {
			gerarrelatorio();	
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
			
			if (e.getSource() == t_pesquisar1) {
				pesquisar_pagamento();
		}
			
			
		}

}
