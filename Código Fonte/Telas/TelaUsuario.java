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
import java.util.HashMap;
import java.util.Iterator;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;

import Others.RoundedBorder;







public class TelaUsuario extends JInternalFrame implements MouseListener, ActionListener, KeyListener{
	
	
	Connection conexao = null;
	PreparedStatement pst = null; 
	ResultSet rs = null;
	


	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void adicionar() {
		String sql = "insert into tbusuario(nome,login,senha,perfil) values (?, ?, ?, ?)";
		enablebtn();
		try {
			
			
			if (t_nome.getText().isEmpty() || p_senha.getPassword().toString().isEmpty() ||c_perm.getSelectedItem().isEmpty() || c_nomef.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!!");	
			}else {
				
				
				int contt = 0;
				for (int i = 0; i <tabela.getRowCount(); i++) {
					if (c_nomef.getSelectedItem().equals(tabela.getModel().getValueAt(i, 1))) {
						contt = 1;
					}
				}
				
				if (contt >= 1) {
					JOptionPane.showMessageDialog(null, "Este usuario ja existe!!");
				}
				else {
				pst = conexao.prepareStatement(sql);
				
				pst.setString(1, c_nomef.getSelectedItem());
				pst.setString(2, t_nome.getText());
				//String captura = new String(p_senha.getPassword());
				pst.setString(3, p_senha.getText());
				pst.setString(4, c_perm.getSelectedItem());
				
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			//System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Usuario " + c_nomef.getSelectedItem() + " adicionado com sucesso!!");
				
				pesquisar_usuario();
				pesquisar_usuarioprivilegio();
				t_pesqusercod.setText(tabela.getModel().getValueAt(tabela.getRowCount()-1, 0).toString());
				t_pesquser.setText(tabela.getModel().getValueAt(tabela.getRowCount()-1, 1).toString());
				pesquisar_usuarioprivilegio();
				 
				adicionarprivilegios();
				
				
				t_cod.setText(null);
				t_nome.setText(null);
				p_senha.setText(null);
				c_nomef.select("");
				
				
				enablebtn();
				
				//conexao.close()
			}
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo adicionar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void adicionarprivilegios() {
		String sql = "insert into tbbloqueio(nomeuser,iduser,eadd,erem,eupd,evis,fadd,frem,fupd,fvis,tadd,trem,tupd,tvis,padd,prem,pupd,pvis) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		enablebtn();
		try {
			
			
			if (t_pesqusercod.getText().isEmpty() || t_pesquser.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione o Usuario!!");	
			}else {
				
				int cont = 0;
				//JOptionPane.showMessageDialog(null, " o Usuario!!");	
				for (int i = 0; i < tabela2.getRowCount(); i++) {
					if (t_pesqusercod.getText().equals(tabela2.getModel().getValueAt(i, 2))) {
						cont = 1;
					}
				}
				
				if (cont > 0) {
					//JOptionPane.showMessageDialog(null, "");
					alterarprivilegios();
				}else {
				pst = conexao.prepareStatement(sql);
				
				pst.setString(1, t_pesquser.getText());
				pst.setString(2, t_pesqusercod.getText());
				
				if (eadd.isSelected()) {
					pst.setBoolean(3, true);
				}else {
					pst.setBoolean(3, false);
				}
				
				if (erem.isSelected()) {
					pst.setBoolean(4, true);
				}else {
					pst.setBoolean(4, false);
				}
				
				if (eupd.isSelected()) {
					pst.setBoolean(5, true);
				}else {
					pst.setBoolean(5, false);
				}
				
				if (evis.isSelected()) {
					pst.setBoolean(6, true);
				}else {
					pst.setBoolean(6, false);
				}
				
				if (fadd.isSelected()) {
					pst.setBoolean(7, true);
				}else {
					pst.setBoolean(7, false);
				}
				
				if (frem.isSelected()) {
					pst.setBoolean(8, true);
				}else {
					pst.setBoolean(8, false);
				}
				
				if (fupd.isSelected()) {
					pst.setBoolean(9, true);
				}else {
					pst.setBoolean(9, false);
				}
				
				if (fvis.isSelected()) {
					pst.setBoolean(10, true);
				}else {
					pst.setBoolean(10, false);
				}
				
				if (tadd.isSelected()) {
					pst.setBoolean(11, true);
				}else {
					pst.setBoolean(11, false);
				}
				
				if (trem.isSelected()) {
					pst.setBoolean(12, true);
				}else {
					pst.setBoolean(12, false);
				}
				
				if (tupd.isSelected()) {
					pst.setBoolean(13, true);
				}else {
					pst.setBoolean(13, false);
				}
				
				if (tvis.isSelected()) {
					pst.setBoolean(14, true);
				}else {
					pst.setBoolean(14, false);
				}
				
				if (padd.isSelected()) {
					pst.setBoolean(15, true);
				}else {
					pst.setBoolean(15, false);
				}
				
				if (prem.isSelected()) {
					pst.setBoolean(16, true);
				}else {
					pst.setBoolean(16, false);
				}
				
				if (pupd.isSelected()) {
					pst.setBoolean(17, true);
				}else {
					pst.setBoolean(17, false);
				}
				
				if (pvis.isSelected()) {
					pst.setBoolean(18, true);
				}else {
					pst.setBoolean(18, false);
				}
				
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			//System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Configure os niveis de acesso para o usuario " + t_pesquser.getText() + " !!");
				
				t_pesquser.setText(null);
				t_pesqusercod.setText(null);
				enablebtn();
				pesquisar_usuarioprivilegio();
			}
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo sette privilegios '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/

	public void ativarrestricao(int getid) {
		
		String sql = "select * from tbbloqueio where iduser = ?";
		
		try {
			pst = conexao.prepareStatement(sql);
			
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
	
	

	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 
public void pesquisar_usuario() {
	String sql = "select Cod, nome, login, perfil from tbusuario where nome like ?";
	
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
	



/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar usuario para privilegios '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
 
public void pesquisar_usuarioprivilegio() {
String sql = "select * from tbbloqueio";

try {
	pst = conexao.prepareStatement(sql);
	//passando o conteudo da caixa de pesquisa para ?
	// atencao ao %  - continuacao da string sql
	//pst.setString(1, t_pesquser.getText() + "%");
	rs = pst.executeQuery();
	// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
	
	tabela2.setModel(DbUtils.resultSetToTableModel(rs));
	
} catch (SQLException e) {
	JOptionPane.showMessageDialog(null, e);
	//e.printStackTrace();
}

}




/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar usuario para privilegios '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
 
//public void pesquisar_nomecodigo() {
//String sql = "select Cod,Nome from tbusuario";
//
//try {
//	pst = conexao.prepareStatement(sql);
//	//passando o conteudo da caixa de pesquisa para ?
//	// atencao ao %  - continuacao da string sql
//	//pst.setString(1, t_pesquser.getText() + "%");
//	rs = pst.executeQuery();
//	// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
//	
//	tabela1.setModel(DbUtils.resultSetToTableModel(rs));
//	
//} catch (SQLException e) {
//	JOptionPane.showMessageDialog(null, e);
//	//e.printStackTrace();
//}
//
//}
/*''''''''''''''''''''''''''''''''' Metodo para preencher os campos ao clicar na tabela '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void sett_campos() {
		
		int nr = tabela.getSelectedRow();
		
		try {
			pst = conexao.prepareStatement("select senha from tbusuario where cod = ?");
    	    pst.setString(1, tabela.getModel().getValueAt(nr, 0).toString());
			rs = pst.executeQuery();	
			if (rs.next()) {
				p_senha.setText(rs.getString(1));
			}
		
		t_cod.setText(tabela.getModel().getValueAt(nr, 0).toString());
		c_nomef.select(tabela.getModel().getValueAt(nr, 1).toString());
		t_nome.setText(tabela.getModel().getValueAt(nr, 2).toString());
		
		//c_perm.setText(tabela.getModel().getValueAt(nr, 2).toString());
		c_perm.select(tabela.getModel().getValueAt(nr, 3).toString());
		//sett_camposprivilegios();
		enablebtn();
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,e);
		}
	}
	
	 

/*''''''''''''''''''''''''''''''''' Metodo para preencher os campos ao clicar na tabela '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	

	
	public void sett_camposprivilegios() {
		
		//TelaAluno ta = new TelaAluno();
		int nr1 = tabela.getSelectedRow();
		
		t_pesqusercod.setText(tabela.getModel().getValueAt(nr1, 0).toString());
		t_pesquser.setText(tabela.getModel().getValueAt(nr1, 1).toString());
//		System.out.println(t_pesqusercod.getText());
		
		int count = 0;
		int i= 0;
		
		for (int j = 0; j < tabela2.getRowCount(); j++) {
			if (t_pesqusercod.getText().equals(tabela2.getModel().getValueAt(j, 2))) {
			count = 1;
			//	System.out.println(tabela2.getModel().getValueAt(j, 2));
				//System.out.println(t_pesqusercod.getText());
				i = j; /* Integer.parseInt(tabela2.getModel().getValueAt(j, 2).toString());*/
			}
			
		}
			
			if (count >= 1) {
				
				if (tabela2.getModel().getValueAt(i, 3).equals(true)) {
					eadd.setSelected(true);
				}else { eadd.setSelected(false);
				}
				
				if (tabela2.getModel().getValueAt(i, 4).equals(true)) {
					erem.setSelected(true);
				}else { erem.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 5).equals(true)) {
					eupd.setSelected(true);
				}else { eupd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 6).equals(true)) {
					evis.setSelected(true);
				}else { evis.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 7).equals(true)) {
					fadd.setSelected(true);
				}else { fadd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 8).equals(true)) {
					frem.setSelected(true);
				}else { frem.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 9).equals(true)) {
					fupd.setSelected(true);
				}else { fupd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 10).equals(true)) {
					fvis.setSelected(true);
				}else { fvis.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 11).equals(true)) {
					tadd.setSelected(true);
				}else { tadd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 12).equals(true)) {
					trem.setSelected(true);
				}else { trem.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 13).equals(true)) {
					tupd.setSelected(true);
				}else { tupd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 14).equals(true)) {
					tvis.setSelected(true);
				}else { tvis.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 15).equals(true)) {
					padd.setSelected(true);
				}else { padd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 16).equals(true)) {
					prem.setSelected(true);
				}else { prem.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 17).equals(true)) {
					pupd.setSelected(true);
				}else { pupd.setSelected(false);}
				
				if (tabela2.getModel().getValueAt(i, 18).equals(true)) {
					pvis.setSelected(true);
				}else { pvis.setSelected(false);}
				
			}else {
				JOptionPane.showMessageDialog(null, "Usuario nao encontrado");	
			}	
			
		
	
	}
	
	
	
	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo alterar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void alterar() {
		String sql = "Update tbusuario set nome = ?, login = ?, senha = ?, perfil = ? where cod = ?";
		enablebtn();
		try {
			
			
			if (t_nome.getText().isEmpty() || p_senha.getPassword().toString().isEmpty() ||c_perm.getSelectedItem().isEmpty() || c_nomef.getSelectedItem().isEmpty()){
				JOptionPane.showMessageDialog(null, "Preencha todos os campos");	
			}else {
				
				
				pst = conexao.prepareStatement(sql);
				pst.setString(1, c_nomef.getSelectedItem());
				pst.setString(2, t_nome.getText());
				pst.setString(3, p_senha.getText());
				pst.setString(4, c_perm.getSelectedItem());
				pst.setString(5, t_cod.getText());
				
			//A linha a baixo actualiza a tabela
			pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados do usuario alterados com sucesso!!");
				t_nome.setText(null);
				p_senha.setText(null);
				c_nomef.select("");
				pesquisar_usuario();
				enablebtn();
				//conexao.close();
			}
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
			//e.printStackTrace();
		}
		
	}
	
	//''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Alterar privilegios ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	private void alterarprivilegios() {
		//String sql = "insert into tbbloqueio(nomeuser,iduser,eadd,erem,eupd,evis,fadd,frem,fupd,fvis,tadd,trem,tupd,tvis,padd,prem,pupd,pvis) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String sql = "Update tbbloqueio set eadd = ?, erem = ?, eupd = ?, evis = ?, fadd =?, frem = ?, fupd = ?, fvis = ?, tadd = ?, trem = ?, tupd = ?, tvis = ?, padd = ?, prem = ?, pupd = ?, pvis = ?  where iduser = ?";
		enablebtn();
		try {
			
			
			if (t_pesqusercod.getText().isEmpty() || t_pesquser.getText().isEmpty()){
				JOptionPane.showMessageDialog(null, "Selecione o Usuario!!");	
			}else {
				
				/*
				int cont = 0;
				
				for (int i = 0; i < tabela1.getRowCount(); i++) {
					if (t_pesquser.getText().equals(tabela1.getModel().getValueAt(i, 1))) {
						cont = 1;
					}
				}
				
				if (cont > 0) {
					JOptionPane.showMessageDialog(null, "");
				}
				*/
				
				pst = conexao.prepareStatement(sql);

				
				if (eadd.isSelected()) {
					pst.setBoolean(1, true);
				}else {
					pst.setBoolean(1, false);
				}
				
				if (erem.isSelected()) {
					pst.setBoolean(2, true);
				}else {
					pst.setBoolean(2, false);
				}
				
				if (eupd.isSelected()) {
					pst.setBoolean(3, true);
				}else {
					pst.setBoolean(3, false);
				}
				
				if (evis.isSelected()) {
					pst.setBoolean(4, true);
				}else {
					pst.setBoolean(4, false);
				}
				
				if (fadd.isSelected()) {
					pst.setBoolean(5, true);
				}else {
					pst.setBoolean(5, true);
				}
				
				if (frem.isSelected()) {
					pst.setBoolean(6, true);
				}else {
					pst.setBoolean(6, false);
				}
				
				if (fupd.isSelected()) {
					pst.setBoolean(7, true);
				}else {
					pst.setBoolean(7, false);
				}
				
				if (fvis.isSelected()) {
					pst.setBoolean(8, true);
				}else {
					pst.setBoolean(8, false);
				}
				
				if (tadd.isSelected()) {
					pst.setBoolean(9, true);
				}else {
					pst.setBoolean(9, false);
				}
				
				if (trem.isSelected()) {
					pst.setBoolean(10, true);
				}else {
					pst.setBoolean(10, false);
				}
				
				if (tupd.isSelected()) {
					pst.setBoolean(11, true);
				}else {
					pst.setBoolean(11, false);
				}
				
				if (tvis.isSelected()) {
					pst.setBoolean(12, true);
				}else {
					pst.setBoolean(12, false);
				}
				
				if (padd.isSelected()) {
					pst.setBoolean(13, true);
				}else {
					pst.setBoolean(13, false);
				}
				
				if (prem.isSelected()) {
					pst.setBoolean(14, true);
				}else {
					pst.setBoolean(14, false);
				}
				
				if (pupd.isSelected()) {
					pst.setBoolean(15, true);
				}else {
					pst.setBoolean(15, false);
				}
				
				if (pvis.isSelected()) {
					pst.setBoolean(16, true);
				}else {
					pst.setBoolean(16, false);
				}
				
				pst.setString(17, t_pesqusercod.getText());
				
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
			
			int adicionar = pst.executeUpdate();
			//System.out.println(adicionar);
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Definiu os privilegios para o usuario " + t_pesquser.getText() + " !!");
				
				t_pesquser.setText(null);
				t_pesqusercod.setText(null);
				enablebtn();
				pesquisar_usuarioprivilegio();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo remover '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void remover() {
		
	
		   
		String sql = "delete from tbusuario where cod = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_cod.getText());
			
			if (t_cod.getText().isEmpty() ){
				JOptionPane.showMessageDialog(null, "Selecione o usuario que predende remover!");	
			}else {
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
				int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario "+c_nomef.getSelectedItem()+" ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
				   if (confirmar == JOptionPane.YES_OPTION) {
			t_pesqusercod.setText(t_cod.getText());
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Usuario removido com sucesso!!");
				removerprivilegios();
				t_cod.setText(null);
				t_nome.setText(null);
				p_senha.setText(null);
				c_nomef.select("");
				pesquisar_usuario();
				//conexao.close();
				t_pesqusercod.setText(null);
				t_pesquser.setText(null);
				pesquisar_usuarioprivilegio();
				enablebtn();
				}
			}
			}
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		//	e.printStackTrace();
		}
		
		
	
	}
	
	
/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo removerprivilegios '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	private void removerprivilegios() {
		
	
		   
		String sql = "delete from tbbloqueio where iduser = ?";
		try {
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_pesqusercod.getText());
			
			if (t_pesqusercod.getText().isEmpty() ){
				JOptionPane.showMessageDialog(null, "Selecione o usuario que predende remover!");	
			}else {
			//A linha a baixo actualiza a tabela
			//pst.executeUpdate();
//				int confirmar = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuario "+c_nomef.getSelectedItem()+" ?","Atencao",JOptionPane.YES_NO_CANCEL_OPTION);
//				   if (confirmar == JOptionPane.YES_OPTION) {
//			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Usuarivvo removido com sucesso!!");
				t_pesqusercod.setText(null);
				t_pesquser.setText(null);
//				t_cod.setText(null);
//				t_nome.setText(null);
//				p_senha.setText(null);
//				c_nomef.select("");
//				pesquisar_usuario();
				//conexao.close();
				enablebtn();
				}
//			}
			}
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		//	e.printStackTrace();
		}
		
		
	
	}
	
	
	
//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''	
	
	
	
public void enablebtn() {
		
		if (t_cod.getText().isEmpty()) {
			btn_atualizar.setEnabled(false);
			btn_remover.setEnabled(false);
			btn_registar.setEnabled(true);
		}else {
			btn_atualizar.setEnabled(true);
			btn_remover.setEnabled(true);
			btn_registar.setEnabled(false);
		}
	
	}
	

//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

private void limparcampos() {
	
	t_cod.setText(null);
	t_nome.setText(null);
	p_senha.setText(null);
	c_nomef.select("");
	c_perm.select("");
	
	eadd.setSelected(false);
	erem.setSelected(false);
	eupd.setSelected(false);
	evis.setSelected(false);
	
	fadd.setSelected(false);
	frem.setSelected(false);
	fupd.setSelected(false);
	fvis.setSelected(false);
	
	tadd.setSelected(false);
	trem.setSelected(false);
	tupd.setSelected(false);
	tvis.setSelected(false);
	
	padd.setSelected(false);
	prem.setSelected(false);
	pupd.setSelected(false);
	pvis.setSelected(false);
	
	
	enablebtn();
}
	
	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Addcombobox '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	public void addcombobox() {
		
		String sql = "select nome from tbfuncionario";
		
		try {
			pst = conexao.prepareStatement(sql);
			rs = pst.executeQuery();
			
			c_nomef.removeAll();
			c_nomef.addItem("");
			
			while (rs.next()) {
			 c_nomef.addItem(rs.getString(1));	
			}
		} catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
		}
	}
	
	

	public void gerarrelatorio1() {
		
		try {
			//conexao = modulo.ModuloConexao.conector();
			HashMap parametro = new HashMap();
	 		JasperPrint jp = JasperFillManager.fillReport("C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Turmas.jasper", null,conexao);
			JasperViewer jw = new JasperViewer(jp);
			jw.setVisible(true);
			
			//JasperExportManager.exportReportToPdfFile(jp, "C:\\Users\\Prashna\\eclipse-workspace\\Projecto_School_Drive\\Código Fonte\\Relatorios\\Pagamentos.pdf");
			
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''' Metodo Pesquisar '''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	
	/*
public void pesquisar_bloqueio() {
	String sql = "select * from tbbloqueio where cod = 1";
	
	try {
		pst = conexao.prepareStatement(sql);
		//passando o conteudo da caixa de pesquisa para ?
		// atencao ao %  - continuacao da string sql
		//.setString(1, t_pesquisar.getText() + "%");
		rs = pst.executeQuery();
		// a linha abaixo usa a biblioteca rs2xml para preencher a tabela
		
		tabela1.setModel(DbUtils.resultSetToTableModel(rs));
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
		//e.printStackTrace();
	}
	
}
	*/
	
	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Administrador Junior '''''''''''''''''''''''''''''''''''''''''''''''''''''''''

/*	
public void bloquearuser() {
	//	String sql = "Update tbbloqueio set eadd = ?, erem = ?, eupd = ?, evis = ?, set fadd = ?, frem = ?, fupd = ?, fvis = ?, tadd = ?, trem = ?, tupd = ?, tvis = ?, padd = ?, prem = ?, pupd = ?, pvis = ?,  set eadd1 = ?, erem1 = ?, eupd1 = ?, evis1 = ?, set fadd1 = ?, frem1 = ?, fupd1 = ?, fvis1 = ?, tadd1 = ?, trem1 = ?, tupd1 = ?, tvis1 = ?, padd1 = ?, prem1 = ?, pupd1 = ?, pvis1 = ? where cod = 1";
		String sql = "Update tbbloqueio set eadd = ?, erem = ? where cod = 1";

		enablebtn();
		
		TelaAluno ta = new TelaAluno();
		
		try {
			pst = conexao.prepareStatement(sql);
			
			if (eadd.isSelected()) { pst.setBoolean(1, true); ta.btn_registar.setEnabled(false);	
			}else { pst.setBoolean(1, false); ta.btn_registar.setEnabled(true);}
			
			
			if (erem.isSelected()) { pst.setBoolean(2, true); ta.btn_limpar.setEnabled(false);	
			}else { pst.setBoolean(2, false); ta.btn_limpar.setEnabled(true);	}
			
			/*
			if (eupd.isSelected()) { pst.setBoolean(3, true);	
			}else { pst.setBoolean(3, false);}
			
			if (evis.isSelected()) { pst.setBoolean(4, true);	
			}else { pst.setBoolean(4, false);}
			
			if (fadd.isSelected()) { pst.setBoolean(5, true);	
			}else { pst.setBoolean(5, false);}
			
			if (frem.isSelected()) { pst.setBoolean(6, true);	
			}else { pst.setBoolean(6, false);}
			
			if (fupd.isSelected()) { pst.setBoolean(7, true);	
			}else { pst.setBoolean(7, false);}
			
			if (fvis.isSelected()) { pst.setBoolean(8, true);	
			}else { pst.setBoolean(8, false);}
			
			if (tadd.isSelected()) { pst.setBoolean(9, true);	
			}else { pst.setBoolean(9, false);}
			
			if (trem.isSelected()) { pst.setBoolean(10, true);	
			}else { pst.setBoolean(10, false);}
			
			if (tupd.isSelected()) { pst.setBoolean(11, true);	
			}else { pst.setBoolean(11, false);}
			
			if (tvis.isSelected()) { pst.setBoolean(12, true);	
			}else { pst.setBoolean(12, false);}
			
			if (padd.isSelected()) { pst.setBoolean(13, true);	
			}else { pst.setBoolean(13, false);}
			
			if (prem.isSelected()) { pst.setBoolean(14, true);	
			}else { pst.setBoolean(14, false);}
			
			if (pupd.isSelected()) { pst.setBoolean(15, true);	
			}else { pst.setBoolean(15, false);}
			
			if (pvis.isSelected()) { pst.setBoolean(16, true);	
			}else { pst.setBoolean(16, false);}
			
			
			if (eadd1.isSelected()) { pst.setBoolean(17, true);	
			}else { pst.setBoolean(17, false);}
			
			if (erem1.isSelected()) { pst.setBoolean(18, true);	
			}else { pst.setBoolean(18, false);}
			
			if (eupd1.isSelected()) { pst.setBoolean(19, true);	
			}else { pst.setBoolean(19, false);}
			
			if (evis1.isSelected()) { pst.setBoolean(20, true);	
			}else { pst.setBoolean(20, false);}
			
			if (fadd1.isSelected()) { pst.setBoolean(21, true);	
			}else { pst.setBoolean(21, false);}
			
			if (frem1.isSelected()) { pst.setBoolean(22, true);	
			}else { pst.setBoolean(22, false);}
			
			if (fupd1.isSelected()) { pst.setBoolean(23, true);	
			}else { pst.setBoolean(23, false);}
			
			if (fvis1.isSelected()) { pst.setBoolean(24, true);	
			}else { pst.setBoolean(24, false);}
			
			if (tadd1.isSelected()) { pst.setBoolean(25, true);	
			}else { pst.setBoolean(25, false);}
			
			if (trem1.isSelected()) { pst.setBoolean(26, true);	
			}else { pst.setBoolean(26, false);}
			
			if (tupd1.isSelected()) { pst.setBoolean(27, true);	
			}else { pst.setBoolean(27, false);}
			
			if (tvis1.isSelected()) { pst.setBoolean(28, true);	
			}else { pst.setBoolean(28, false);}
			
			if (padd1.isSelected()) { pst.setBoolean(29, true);	
			}else { pst.setBoolean(29, false);}
			
			if (prem1.isSelected()) { pst.setBoolean(30, true);	
			}else { pst.setBoolean(30, false);}
			
			if (pupd1.isSelected()) { pst.setBoolean(31, true);	
			}else { pst.setBoolean(31, false);}
			
			if (pvis1.isSelected()) { pst.setBoolean(32, true);	
			}else { pst.setBoolean(32, false);}
			
			
			
			
			int adicionar = pst.executeUpdate();
			if (adicionar > 0) {
				JOptionPane.showMessageDialog(null, "Dados actualizados com sucesso!!");
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		
		
		
	}*/


	//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''' Sett Checkbox '''''''''''''''''''''''''''''''''''''''''''''''''''''''''

	/*
	public void settchackbox() {
		
		if (tabela1.getModel().getValueAt(0,1).equals(true)) {
			eadd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,2).equals(true)) {
			erem.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,3).equals(true)) {
			eupd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,4).equals(true)) {
			evis.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,5).equals(true)) {
			fadd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,6).equals(true)) {
			frem.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,7).equals(true)) {
			fupd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,8).equals(true)) {
			fvis.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,9).equals(true)) {
			tadd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,10).equals(true)) {
			trem.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,11).equals(true)) {
			tupd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,12).equals(true)) {
			tvis.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,13).equals(true)) {
			padd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,14).equals(true)) {
			prem.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,15).equals(true)) {
			pupd.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,16).equals(true)) {
			pvis.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,17).equals(true)) {
			eadd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,18).equals(true)) {
			erem1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,19).equals(true)) {
			eupd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,20).equals(true)) {
			evis1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,21).equals(true)) {
			fadd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,22).equals(true)) {
			frem1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,23).equals(true)) {
			fupd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,24).equals(true)) {
			fvis1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,25).equals(true)) {
			tadd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,26).equals(true)) {
			trem1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,27).equals(true)) {
			tupd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,28).equals(true)) {
			tvis1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,29).equals(true)) {
			padd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,30).equals(true)) {
			prem1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,31).equals(true)) {
			pupd1.setSelected(true);
		}
		
		if (tabela1.getModel().getValueAt(0,32).equals(true)) {
			pvis1.setSelected(true);
		}
		
	}
	
	*/
	
	
	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * */
	
	
	
	JPanel painel,painel1,painel2,painel3,painel01,painel02,painel011,painel022,painel033,painel044,painel0111,painel0222,painel0333,painel0444;
	   Font f1,f2,f3,f31,f4,f5,f6;
	   JButton btn_salvar,btn_atualizar,btn_registar,btn_remover,btn_limpar,l_admin,l_user,l_estudante,l_funcionario,l_turma,l_pagamneto,l_estudante1,l_funcionario1,l_turma1,l_pagamneto1,l_nota1;
	   JLabel l_nomef,l_nome,l_senha,l_perm,l_pesquisar,l_pesquser;
       JTextField t_pesquisar,t_nomef,t_nome,t_senha,t_cod, t_pesquser, t_pesqusercod;
       Choice c_perm,c_nomef;
       JPasswordField p_senha;
       JTable tabela, tabela2;
       JScrollPane js,js1,js2;
       JCheckBox eadd,erem,eupd,evis,fadd,frem,fupd,fvis,tadd,trem,tupd,tvis,padd,prem,pupd,pvis,eadd1,erem1,eupd1,evis1,fadd1,frem1,fupd1,fvis1,tadd1,trem1,tupd1,tvis1,padd1,prem1,pupd1,pvis1;
       
      
       
       
       
	public TelaUsuario() {
		
		
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
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		
		
		painel = new JPanel();
	    painel.setBackground(new Color(249,249,249));
	    painel.setBounds(0, 0, 870, 530);
	    painel.setLayout(null);
	    
	    painel1 = new JPanel();
	    painel1.setBackground(new Color(249,249,249));
	  //painel1.setOpaque(true);
	    painel1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel1.setBounds(10, 10, 580, 165);
	    painel1.setLayout(null);
	    
	    painel2 = new JPanel();
	    painel2.setBackground(new Color(249,249,249));
	    painel2.setOpaque(true);
	    painel2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel2.setBounds(10, 185, 745, 235);
	    painel2.setLayout(null);
	    
	    painel3 = new JPanel();
	    painel3.setBackground(new Color(249,249,249));
	    painel3.setOpaque(true);
	    painel3.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel3.setBounds(600, 10, 155, 165);
	    painel3.setLayout(null);
	  
	    
	    painel01 = new JPanel();
	    painel01.setBackground(new Color(249,249,249));
	    painel01.setOpaque(true);
	    painel01.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel01.setBounds(20, 195, 355, 215);
	    painel01.setLayout(null);
	  
	    
	    painel02 = new JPanel();
	    painel02.setBackground(new Color(249,249,249));
	    painel02.setOpaque(true);
	    painel02.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel02.setBounds(385, 195, 360, 215);
	    painel02.setLayout(null);
	  
	    /*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * ''''''''''''''''''''''''''''''''''''''''''''''''' Especial'''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	    
	    
	    painel011 = new JPanel();
	    painel011.setBackground(new Color(249,249,249));
	    painel011.setOpaque(true);
	    painel011.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel011.setBounds(30, 230, 165, 80);
	    painel011.setLayout(null);
	    
	    painel022 = new JPanel();
	    painel022.setBackground(new Color(249,249,249));
	    painel022.setOpaque(true);
	    painel022.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel022.setBounds(205, 230, 160, 80);
	    painel022.setLayout(null);  
	    
	    painel033 = new JPanel();
	    painel033.setBackground(new Color(249,249,249));
	    painel033.setOpaque(true);
	    painel033.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel033.setBounds(30, 320, 165, 80);
	    painel033.setLayout(null);
	    
	    painel044 = new JPanel();
	    painel044.setBackground(new Color(249,249,249));
	    painel044.setOpaque(true);
	    painel044.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel044.setBounds(205, 320, 160, 80);
	    painel044.setLayout(null);
	    
	    l_estudante = new JButton("Estudante");
	    l_estudante.setBounds(30,230,165,25);
	    l_estudante.setFont(f5);
	    l_estudante.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_estudante.setBackground(new Color(240,240,240));
	    l_estudante.setForeground(new Color(0,0,0));
	    l_estudante.setEnabled(false);
	    painel.add(l_estudante);
	    
	    l_funcionario = new JButton("Funcionario");
	    l_funcionario.setBounds(205,230,160,25);
	    l_funcionario.setFont(f5);
	    l_funcionario.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_funcionario.setBackground(new Color(240,240,240));
	    l_funcionario.setForeground(new Color(0,0,0));
	    l_funcionario.setEnabled(false);
	    painel.add(l_funcionario);
	    
	    l_turma = new JButton("Turma");
	    l_turma.setBounds(30,320,165,25);
	    l_turma.setFont(f5);
	    l_turma.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_turma.setBackground(new Color(240,240,240));
	    l_turma.setForeground(new Color(0,0,0));
	    l_turma.setEnabled(false);
	    painel.add(l_turma);
	    
	    l_pagamneto = new JButton("Pagamento");
	    l_pagamneto.setBounds(205,320,160,25);
	    l_pagamneto.setFont(f5);
	    l_pagamneto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_pagamneto.setBackground(new Color(240,240,240));
	    l_pagamneto.setForeground(new Color(0,0,0));
	    l_pagamneto.setEnabled(false);
	    painel.add(l_pagamneto);
	    
	    
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * */
	    
	    
	    l_nota1 = new JButton("Atencao aos niveis de acesso!!");
	    l_nota1.setBounds(570,320,165,25);
	    l_nota1.setFont(f5);
	    l_nota1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_nota1.setBackground(new Color(240,240,240));
	    l_nota1.setForeground(new Color(0,0,0));
       l_nota1.setEnabled(false);
	    painel.add(l_nota1);
	    
	    
	    
	    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	     * */
	    
	    l_pagamneto = new JButton("Pagamento");
	    l_pagamneto.setBounds(205,320,160,25);
	    l_pagamneto.setFont(f5);
	    l_pagamneto.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_pagamneto.setBackground(new Color(240,240,240));
	    l_pagamneto.setForeground(new Color(0,0,0));
	    l_pagamneto.setEnabled(false);
	    painel.add(l_pagamneto);
	    
	    
	    /*
	    l_estudante1 = new JButton("Estudante");
	    l_estudante1.setBounds(395,230,165,25);
	    l_estudante1.setFont(f5);
	    l_estudante1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_estudante1.setBackground(new Color(240,240,240));
	    l_estudante1.setForeground(new Color(0,0,0));
	    l_estudante1.setEnabled(false);
	    painel.add(l_estudante1);
	    
	    l_funcionario1 = new JButton("Funcionario");
	    l_funcionario1.setBounds(570,230,165,25);
	    l_funcionario1.setFont(f5);
	    l_funcionario1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_funcionario1.setBackground(new Color(240,240,240));
	    l_funcionario1.setForeground(new Color(0,0,0));
	    l_funcionario1.setEnabled(false);
	    painel.add(l_funcionario1);
	    
	    l_turma1 = new JButton("Turma");
	    l_turma1.setBounds(395,320,165,25);
	    l_turma1.setFont(f5);
	    l_turma1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_turma1.setBackground(new Color(240,240,240));
	    l_turma1.setForeground(new Color(0,0,0));
	    l_turma1.setEnabled(false);
	    painel.add(l_turma1);
	    
	    l_pagamneto1 = new JButton("Pagamento");
	    l_pagamneto1.setBounds(570,320,165,25);
	    l_pagamneto1.setFont(f5);
	    l_pagamneto1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_pagamneto1.setBackground(new Color(240,240,240));
	    l_pagamneto1.setForeground(new Color(0,0,0));
	    l_pagamneto1.setEnabled(false);
	    painel.add(l_pagamneto1);
	    
	    
	    
	    //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    

	    painel0111 = new JPanel();
	    painel0111.setBackground(new Color(249,249,249));
	    painel0111.setOpaque(true);
	    painel0111.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel0111.setBounds(395, 230, 165, 80);
	    painel0111.setLayout(null);
	    
	    painel0222 = new JPanel();
	    painel0222.setBackground(new Color(249,249,249));
	    painel0222.setOpaque(true);
	    painel0222.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel0222.setBounds(570, 230, 165, 80);
	    painel0222.setLayout(null);  
	    
	    painel0333 = new JPanel();
	    painel0333.setBackground(new Color(249,249,249));
	    painel0333.setOpaque(true);
	    painel0333.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel0333.setBounds(395, 320, 165, 80);
	    painel0333.setLayout(null);
	    
	    painel0444 = new JPanel();
	    painel0444.setBackground(new Color(249,249,249));
	    painel0444.setOpaque(true);
	    painel0444.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    painel0444.setBounds(570, 320, 165, 80);
	    painel0444.setLayout(null);
	   
	    */
	    
	    /*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * ''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	     * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	    btn_salvar = new JButton("Salvar");
		btn_salvar.setBounds(665, 375, 80, 25);
		btn_salvar.setForeground(new Color(231,231,231));
		btn_salvar.setBackground(new Color(68,68,68));
	//	btn_salvar.setContentAreaFilled(false);
	//	btn_salvar.setToolTipText("Sair");
		btn_salvar.setFont(f5);
		btn_salvar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		//btn_salvar.setOpaque(false);
		btn_salvar.addActionListener(this);
		btn_salvar.addMouseListener(this);
		painel.add(btn_salvar);
	    
	    
	    l_admin = new JButton("Definir os privilegios");
	    l_admin.setBounds(20,195,355,25);
	    l_admin.setFont(f5);
	    l_admin.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_admin.setBackground(new Color(240,240,240));
	    l_admin.setForeground(new Color(0,0,0));
	    l_admin.setEnabled(false);
	    painel.add(l_admin);
	    
	    l_user = new JButton("Atencao!!");
	    l_user.setBounds(385,195,360,25);
	    l_user.setFont(f5);
	    l_user.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    l_user.setBackground(new Color(240,240,240));
	    l_user.setForeground(new Color(0,0,0));
	    l_user.setEnabled(false);
	    painel.add(l_user);
	    
	    l_nomef = new JLabel("Nome");
	    l_nomef.setBounds(25,15,40,30);
	    l_nomef.setFont(f5);
	    l_nomef.setForeground(new Color(0,0,0));
	    painel.add(l_nomef);

	    
	    l_pesquser = new JLabel("    Selecionado   ");
	    l_pesquser.setBounds(395,230,90,20);
	    l_pesquser.setFont(f5);
	    l_pesquser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(150,150,150)));
	    l_pesquser.setForeground(new Color(0,0,0));
	    painel.add(l_pesquser);
	    
	    t_pesquser = new JTextField();
	    t_pesquser.setBounds(490,230,245,20);
	    t_pesquser.setFont(f5);
	    t_pesquser.setEditable(false);
	    t_pesquser.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_pesquser.setForeground(new Color(150,150,150));
	    //t_pesquser.setEnabled(false);
	    painel.add(t_pesquser);
	    
	    t_pesqusercod = new JTextField();
	    t_pesqusercod.setBounds(550,230,50,20);
	    t_pesqusercod.setFont(f5);
	    t_pesqusercod.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_pesqusercod.setForeground(new Color(0,0,0));
	    t_pesqusercod.setEnabled(false);
	    t_pesqusercod.setVisible(false);
	  //  painel.add(t_pesqusercod);
	    
	    l_nome = new JLabel("login");
	    l_nome.setBounds(235,5,60,30);
	    l_nome.setFont(f5);
	    l_nome.setForeground(new Color(0,0,0));
	    
	    painel1.add(l_nome);
	    
	    
	    l_senha = new JLabel("Senha ");
	    l_senha.setBounds(425,20,100,20);
	    l_senha.setFont(f5);
	    l_senha.setForeground(new Color(0,0,0));
	    painel.add(l_senha);
	    
	   
	    l_perm = new JLabel("Permissão ");
	    l_perm.setBounds(25,50,60,20);
	    l_perm.setFont(f5);
	    l_perm.setForeground(new Color(0,0,0));
	    painel.add(l_perm);
	    
	    l_pesquisar = new JLabel("    Pesquisar");
	    l_pesquisar.setBounds(280,50,80,20);
	    l_pesquisar.setFont(f5);
	    l_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    //l_pesquisar.setIcon(new  ImageIcon(TelaUsuario.class.getResource("/imgs/pesq.png")));
	    l_pesquisar.setForeground(new Color(174,61,41));
	    painel.add(l_pesquisar);
	    
	    
	    
	    t_cod = new JTextField();
	    t_cod.setBounds(285,65,120,20);
	    t_cod.setFont(f6);
	    t_cod.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_cod.setForeground(new Color(100,100,100));
	    t_cod.setVisible(false);
	    painel.add(t_cod);
	   
	    
	    t_nome = new JTextField();
	    t_nome.setBounds(285,20,120,20);
	    t_nome.setFont(f6);
	    t_nome.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nome.setForeground(new Color(100,100,100));
	    painel.add(t_nome);
	    
	    p_senha = new JPasswordField();
	    p_senha.setBounds(470,20,110,20);
	    p_senha.setFont(f6);
	    p_senha.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    p_senha.setForeground(new Color(100,100,100));
	    painel.add(p_senha);
	   
	    c_perm = new Choice();
	    c_perm.setBounds(95,49,150,20);
	    c_perm.setFont(f6);
	    c_perm.setForeground(new Color(88,88,88));
	   // c_perm.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(88,88,88)));
	    c_perm.add("");
	    c_perm.addItem("Admin");
	    c_perm.addItem("Usuario");
	    painel.add(c_perm);
	     
	    t_nomef = new JTextField();
	    t_nomef.setBounds(70,20,140,20);
	    t_nomef.setFont(f6);
	    t_nomef.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    t_nomef.setForeground(new Color(100,100,100));
	  //  painel.add(t_nomef);
	    
	    c_nomef = new Choice();
	    c_nomef.setBounds(70,20,140,20);
	    c_nomef.setFont(f6);
	    c_nomef.setForeground(new Color(100,100,100));
	    //addcombobox();
	    painel.add(c_nomef);
	    
	    
	    
	     eadd = new JCheckBox("");
	    eadd.setBounds(45, 25, 21, 23);
	    eadd.setBackground(new Color(200,200,200));
	    eadd.setOpaque(false);
	    painel011.add(eadd);
	    
	     erem = new JCheckBox("");
	    erem.setBounds(120, 25, 21, 23);
	    erem.setOpaque(false);
	    painel011.add(erem);
	    
	     eupd = new JCheckBox("");
	    eupd.setBounds(45, 55, 21, 23);
	    eupd.setOpaque(false);
	    painel011.add(eupd);
	    
	     evis = new JCheckBox("");
	    evis.setBounds(120, 55, 21, 23);
	    evis.setOpaque(false);
	    painel011.add(evis);
	    
	    JLabel lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel.setBounds(83, 25, 21, 23);
	    painel011.add(lblNewLabel);
	    
	    JLabel lblNewLabel_1 = new JLabel("");
	    lblNewLabel_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1.setBounds(10, 25, 21, 23);
	    painel011.add(lblNewLabel_1);
	    
	    JLabel lblNewLabel_2 = new JLabel("");
	    lblNewLabel_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2.setBounds(12, 55, 21, 23);
	    painel011.add(lblNewLabel_2);
	    
	    JLabel lblNewLabel_3 = new JLabel("");
	    lblNewLabel_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/vis.png")));
	    lblNewLabel_3.setBounds(83, 55, 23, 23);
	    painel011.add(lblNewLabel_3);
	    painel.add(painel022);
	    
	    
	  //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	   
	     fadd = new JCheckBox("");
	    fadd.setBounds(45, 25, 21, 23);
	    fadd.setOpaque(false);
	    painel022.add(fadd);
	    
	     frem = new JCheckBox("");
	    frem.setBounds(120, 25, 21, 23);
	    frem.setOpaque(false);
	    painel022.add(frem);
	    
	     fupd = new JCheckBox("");
	    fupd.setBounds(45, 55, 21, 23);
	    fupd.setOpaque(false);
	    painel022.add(fupd);
	    
	     fvis = new JCheckBox("");
	    fvis.setBounds(120, 55, 21, 23);
	    fvis.setOpaque(false);
	    painel022.add(fvis);
	    
	    JLabel lblNewLabel_3_1 = new JLabel("");
	    lblNewLabel_3_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/vis.png")));
	    lblNewLabel_3_1.setBounds(85, 50, 23, 23);
	    painel022.add(lblNewLabel_3_1);
	    
	    JLabel lblNewLabel_4 = new JLabel("");
	    lblNewLabel_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_4.setBounds(85, 25, 21, 23);
	    painel022.add(lblNewLabel_4);
	    
	    JLabel lblNewLabel_1_1 = new JLabel("");
	    lblNewLabel_1_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_1.setBounds(12, 25, 21, 23);
	    painel022.add(lblNewLabel_1_1);
	    
	    JLabel lblNewLabel_2_1 = new JLabel("");
	    lblNewLabel_2_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_1.setBounds(14, 50, 21, 23);
	    painel022.add(lblNewLabel_2_1);
	    painel.add(painel033);

   //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	     tadd = new JCheckBox("");
	    tadd.setBounds(45, 25, 21, 23);
	    tadd.setOpaque(false);
	    painel033.add(tadd);
	    
	     trem = new JCheckBox("");
	    trem.setBounds(120, 25, 21, 23);
	    trem.setOpaque(false);
	    painel033.add(trem);
	    
	     tupd = new JCheckBox("");
	    tupd.setBounds(45, 55, 21, 23);
	    tupd.setOpaque(false);
	    painel033.add(tupd);
	    
	     tvis = new JCheckBox("");
	    tvis.setBounds(120, 55, 21, 23);
	    tvis.setOpaque(false);
	    painel033.add(tvis);
	    
	    JLabel lblNewLabel_3_2 = new JLabel("");
	    lblNewLabel_3_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/vis.png")));
	    lblNewLabel_3_2.setBounds(83,55, 21, 23);
	    painel033.add(lblNewLabel_3_2);
	    
	    JLabel lblNewLabel_5 = new JLabel("");
	    lblNewLabel_5.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_5.setBounds(83, 25, 21, 23);
	    painel033.add(lblNewLabel_5);
	    
	    JLabel lblNewLabel_1_2 = new JLabel("");
	    lblNewLabel_1_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_2.setBounds(10,25, 21, 23);
	    painel033.add(lblNewLabel_1_2);
	    
	    JLabel lblNewLabel_2_2 = new JLabel("");
	    lblNewLabel_2_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_2.setBounds(12,55, 21, 23);
	   painel033.add(lblNewLabel_2_2);
	    painel.add(painel044);
	    

  //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	     padd = new JCheckBox("");
	    padd.setBounds(45, 25, 21, 23);
	    padd.setOpaque(false);
	    painel044.add(padd);
	    
	     prem = new JCheckBox("");
	    prem.setBounds(120, 25, 21, 23);
	    prem.setOpaque(false);
	    painel044.add(prem);
	    
	     pupd = new JCheckBox("");
	    pupd.setBounds(45, 55, 21, 23);
	    pupd.setOpaque(false);
	    painel044.add(pupd);
	    
	     pvis = new JCheckBox("");
	    pvis.setBounds(120, 55, 21, 23);
	    pvis.setOpaque(false);
	    painel044.add(pvis);
	    
	    JLabel lblNewLabel_3_3 = new JLabel("");
	    lblNewLabel_3_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/vis.png")));
	    lblNewLabel_3_3.setBounds(94, 55, 21, 23);
	    painel044.add(lblNewLabel_3_3);
	    
	    JLabel lblNewLabel_6 = new JLabel("");
	    lblNewLabel_6.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_6.setBounds(94,25, 21, 23);
	    painel044.add(lblNewLabel_6);
	    
	    JLabel lblNewLabel_1_3 = new JLabel("");
	    lblNewLabel_1_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_3.setBounds(21,25, 21, 23);
	    painel044.add(lblNewLabel_1_3);
	    
	    JLabel lblNewLabel_2_3 = new JLabel("");
	    lblNewLabel_2_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_3.setBounds(23, 55, 21, 23);
	    painel044.add(lblNewLabel_2_3);
	   
	   
	    
 //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	  /*  
	     padd1 = new JCheckBox("");
	    padd1.setBounds(45, 25, 21, 23);
	    padd1.setOpaque(false);
	 //   painel0444.add(padd1);
	    
	     prem1 = new JCheckBox("");
	    prem1.setBounds(120, 25, 21, 23);
	    prem1.setOpaque(false);
	 //   painel0444.add(prem1);
	    
	     pupd1 = new JCheckBox("");
	    pupd1.setBounds(45, 55, 21, 23);
	    pupd1.setOpaque(false);
	//    painel0444.add(pupd1);
	    
	     pvis1 = new JCheckBox("");
	    pvis1.setBounds(120, 55, 21, 23);
	    pvis1.setOpaque(false);
	  //  painel0444.add(pvis1);
	    
	    JLabel lblNewLabel_1_1_4 = new JLabel("");
	    lblNewLabel_1_1_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_1_4.setBounds(22,25, 21, 23);
	//    painel0444.add(lblNewLabel_1_1_4);
	    
	    JLabel lblNewLabel_2_1_4 = new JLabel("");
	    lblNewLabel_2_1_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_1_4.setBounds(24,55, 21, 23);
	  //  painel0444.add(lblNewLabel_2_1_4);
	    
	    JLabel lblNewLabel_3_1_4 = new JLabel("");
	    lblNewLabel_3_1_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/pesq.png")));
	    lblNewLabel_3_1_4.setBounds(95,55, 21, 23);
	  //  painel0444.add(lblNewLabel_3_1_4);
	    
	    JLabel lblNewLabel_4_4 = new JLabel("");
	    lblNewLabel_4_4.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_4_4.setBounds(95,25, 21, 23);
	//    painel0444.add(lblNewLabel_4_4);
	   // painel.add(painel0333);

 //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	     tadd1 = new JCheckBox("");
	    tadd1.setBounds(45, 25, 21, 23);
	    tadd1.setOpaque(false);
	  //  painel0333.add(tadd1);
	    
	     trem1 = new JCheckBox("");
	    trem1.setBounds(120, 25, 21, 23);
	    trem1.setOpaque(false);
	   // painel0333.add(trem1);
	    
	     tupd1 = new JCheckBox("");
	    tupd1.setBounds(45, 55, 21, 23);
	    tupd1.setOpaque(false);
	   // painel0333.add(tupd1);
	    
	     tvis1 = new JCheckBox("");
	    tvis1.setBounds(120, 55, 21, 23);
	   // painel0333.add(tvis1);
	    
	    JLabel lblNewLabel_1_1_2 = new JLabel("");
	    lblNewLabel_1_1_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_1_2.setBounds(22,25, 21, 23);
	  //  painel0333.add(lblNewLabel_1_1_2);
	    
	    JLabel lblNewLabel_2_1_2 = new JLabel("");
	    lblNewLabel_2_1_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_1_2.setBounds(19, 55, 21, 23);
	   // painel0333.add(lblNewLabel_2_1_2);
	    
	    JLabel lblNewLabel_3_1_2 = new JLabel("");
	    lblNewLabel_3_1_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/pesq.png")));
	    lblNewLabel_3_1_2.setBounds(95, 55, 21, 23);
	  //  painel0333.add(lblNewLabel_3_1_2);
	    
	    JLabel lblNewLabel_4_2 = new JLabel("");
	    lblNewLabel_4_2.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_4_2.setBounds(95, 25, 21, 23);
	  //  painel0333.add(lblNewLabel_4_2);
	   // painel.add(painel0222);

  //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

	     fadd1 = new JCheckBox("");
	    fadd1.setBounds(45, 25, 21, 23);
	    fadd1.setOpaque(false);
	  //  painel0222.add(fadd1);
	    
	     frem1 = new JCheckBox("");
	    frem1.setBounds(120, 25, 21, 23);
	    frem1.setOpaque(false);
	 //   painel0222.add(frem1);
	//    
	     fupd1 = new JCheckBox("");
	    fupd1.setBounds(45, 55, 21, 23);
	    fupd1.setOpaque(false);
	   // painel0222.add(fupd1); 
	    
	     fvis1 = new JCheckBox("");
	    fvis1.setBounds(120, 55, 21, 23);
	    fvis1.setOpaque(false);
	   // painel0222.add(fvis1);
	    
	    JLabel lblNewLabel_1_1_3 = new JLabel("");
	    lblNewLabel_1_1_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_1_3.setBounds(25, 25, 21, 23);
	   // painel0222.add(lblNewLabel_1_1_3);
	    
	    JLabel lblNewLabel_2_1_3 = new JLabel("");
	    lblNewLabel_2_1_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_1_3.setBounds(27, 55, 21, 23);
	  //  painel0222.add(lblNewLabel_2_1_3);
	    
	    JLabel lblNewLabel_3_1_3 = new JLabel("");
	    lblNewLabel_3_1_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/pesq.png")));
	    lblNewLabel_3_1_3.setBounds(98,55, 21, 23);
	  //  painel0222.add(lblNewLabel_3_1_3);
	    
	    JLabel lblNewLabel_4_3 = new JLabel("");
	    lblNewLabel_4_3.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_4_3.setBounds(98, 25, 21, 23);
	   // painel0222.add(lblNewLabel_4_3);
	   // painel.add(painel0111);

 //''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	    
	    
	     eadd1 = new JCheckBox("");
	    eadd1.setBounds(45, 25, 21, 23);
	    eadd1.setOpaque(false);
	  //  painel0111.add(eadd1);
	    
	     erem1 = new JCheckBox("");
	    erem1.setBounds(120, 25, 21, 23);
	    erem1.setOpaque(false);
	  //.add(erem1);
	    
	     evis1 = new JCheckBox("");
	    evis1.setBounds(45, 55, 21, 23);
	    evis1.setOpaque(false);
	  //  painel0111.add(evis1);
	    
	     eupd1 = new JCheckBox("");
	    eupd1.setBounds(120, 55, 21, 23);
	    eupd1.setOpaque(false);
	   // painel0111.add(eupd1);
	    
	    
	    JLabel lblNewLabel_1_1_1 = new JLabel("");
	    lblNewLabel_1_1_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/adic.png")));
	    lblNewLabel_1_1_1.setBounds(20, 25, 21, 23);
	   //painel0111.add(lblNewLabel_1_1_1);
	    
	    JLabel lblNewLabel_2_1_1 = new JLabel("");
	    lblNewLabel_2_1_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/lapis1.png")));
	    lblNewLabel_2_1_1.setBounds(22,55, 21, 23);
	   // painel0111.add(lblNewLabel_2_1_1);
	    
	    JLabel lblNewLabel_3_1_1 = new JLabel("");
	    lblNewLabel_3_1_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/pesq.png")));
	    lblNewLabel_3_1_1.setBounds(93, 55, 21, 23);
	   // painel0111.add(lblNewLabel_3_1_1);
	    
	    JLabel lblNewLabel_4_1 = new JLabel("");
	    lblNewLabel_4_1.setIcon(new ImageIcon(TelaUsuario.class.getResource("/imgs/remov.png")));
	    lblNewLabel_4_1.setBounds(93, 25, 21, 23);
	  //  painel0111.add(lblNewLabel_4_1);
	    
	    */
	   
	    
	    t_pesquisar = new JTextField();
	    t_pesquisar.setBounds(380,50,200,20);
	    t_pesquisar.setFont(f6);
	    t_pesquisar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    //t_pesquisar.setIcon(new  ImageIcon(TelaCadAluno.class.getResource("/imgs/addp1.png")));
	    t_pesquisar.setForeground(new Color(0,0,0));
	    t_pesquisar.addKeyListener(this);
	    t_pesquisar.addMouseListener(this);
	    painel.add(t_pesquisar);
	    
	    
	    btn_atualizar = new JButton("Actualizar");
	    btn_atualizar.setBounds(610,20,135,30);
	    btn_atualizar.setFont(f5);
	   // btn_atualizar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_atualizar.setBackground(new Color(239,239,239));
	    btn_atualizar.addActionListener(this);
	    btn_atualizar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/lapis1.png")));
	    btn_atualizar.setForeground(new Color(0,0,0));
	    painel.add(btn_atualizar);
	    
	    btn_registar = new JButton("Registar");
	    btn_registar.setBounds(610,60,135,30);
	    btn_registar.setFont(f5);
	    btn_registar.addActionListener(this);
	    //btn_registar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_registar.setBackground(new Color(239,239,239));
	    btn_registar.setForeground(new Color(0,0,0));
	    btn_registar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/adic.png")));
	    painel.add(btn_registar);
	    
	    btn_remover = new JButton("Remover");
	    btn_remover.setBounds(610,100,135,30);
	    btn_remover.setFont(f5);
	    //btn_remover.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_remover.setBackground(new Color(239,239,239));
	    btn_remover.setForeground(new Color(0,0,0));
	    btn_remover.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/remov.png")));
	    btn_remover.addActionListener(this);
	    painel.add(btn_remover);
	    
	    
	    btn_limpar = new JButton("Limpar");
	    btn_limpar.setBounds(610,140,135,30);
	    btn_limpar.setFont(f5);
	    //btn_limpar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
	    btn_limpar.setBackground(new Color(239,239,239));
	    btn_limpar.setIcon(new ImageIcon(TelaTurma.class.getResource("/imgs/clean.png")));
	    btn_limpar.setForeground(new Color(0,0,0));
	    btn_limpar.addActionListener(this);
	    painel.add(btn_limpar);
	    

	  
	    String [] nome = {"cod","nome","login", "senha","perfil"};
	    tabela = new JTable();
		tabela.setFont(f5);
		DefaultTableModel dtml = new DefaultTableModel(nome, 0);
		
		tabela.setModel(dtml);
		tabela.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(249,249,249)));
		tabela.setBackground(new Color(245,245,245));
		tabela.getTableHeader().setFont(f5);
		tabela.setOpaque(false);
		tabela.addMouseListener(this);
		tabela.getTableHeader().setBackground(new Color(239,239,239));
		tabela.getTableHeader().setReorderingAllowed(false);
		tabela.getTableHeader().setResizingAllowed(false);
		//tabela.getTableHeader().setFocusable(true);
		//tabela.editCellAt(WAIT_CURSOR, CROSSHAIR_CURSOR);
	    js = new JScrollPane(tabela);
		js.setBackground(new Color(249,249,249));
		js.setOpaque(false);
		js.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		js.setBounds(20, 79, 560, 88); 
		painel.add(js);
		
		
//		String [] nome1 = {"cod","eadd","erem", "eupd","evis","fadd","frem","fupd","fvis","tadd","trem","tupd","tvis","padd","prem","pupd","pvis","eadd1","erem1", "eupd1","evis1","fadd1","frem1","fupd1","fvis1","tadd1","trem1","tupd1","tvis1","padd1","prem1","pupd1","pvis1"};
//		String [] nome1 = {"cod","Nome"};
//
//		tabela1 = new JTable();
//		tabela1.setFont(f5);
//		DefaultTableModel dtml1 = new DefaultTableModel(nome1, 0);
//		
//		tabela1.setModel(dtml1);
//		tabela1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(249,249,249)));
//		tabela1.setBackground(new Color(245,245,245));
//		tabela1.getTableHeader().setFont(f5);
//		tabela1.setOpaque(false);
//		tabela1.addMouseListener(this);
//		tabela1.getTableHeader().setBackground(new Color(239,239,239));
//		//tabela1.getTableHeader().setReorderingAllowed(false);
//		//tabela1.getTableHeader().setResizingAllowed(false);
//		//tabela.getTableHeader().setFocusable(true);
//		//tabela.editCellAt(WAIT_CURSOR, CROSSHAIR_CURSOR);
//	    js1 = new JScrollPane(tabela1);
//		js1.setBackground(new Color(249,249,249));
//		js1.setOpaque(false);
//		js1.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
//		js1.setBounds(395, 260, 340, 88);
//		painel.add(js1);
		
		
	    	
		String [] nome2 = {"cod","nome","iduser","eadd","erem", "eupd","evis","fadd","frem","fupd","fvis","tadd","trem","tupd","tvis","padd","prem","pupd","pvis"};

		tabela2 = new JTable();
		tabela2.setFont(f5);
		DefaultTableModel dtml2 = new DefaultTableModel(nome2, 0);
		
		tabela2.setModel(dtml2);
		tabela2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(249,249,249)));
		tabela2.setBackground(new Color(245,245,245));
		tabela2.getTableHeader().setFont(f5);
		tabela2.setOpaque(false);
		tabela2.addMouseListener(this);
		tabela2.getTableHeader().setBackground(new Color(239,239,239));
		//tabela1.getTableHeader().setReorderingAllowed(false);
		//tabela1.getTableHeader().setResizingAllowed(false);
		//tabela.getTableHeader().setFocusable(true);
		//tabela.editCellAt(WAIT_CURSOR, CROSSHAIR_CURSOR);
	    js2 = new JScrollPane(tabela2);
		js2.setBackground(new Color(249,249,249));
		js2.setOpaque(false);
		js2.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(232,229,229)));
		js2.setBounds(395, 160, 340, 88);
	//	painel.add(js2);
	
		
		
		 //painel.add(painel0111);
         //painel.add(painel0222);
         //painel.add(painel0333);
         //painel.add(painel0444);
         painel.add(painel011);
         painel.add(painel022);
         painel.add(painel033);
         painel.add(painel044);
    	 painel.add(painel01);
		 painel.add(painel02);
		 painel.add(painel3);
		 painel.add(painel2);
	     painel.add(painel1);
	 	getContentPane().add(painel);
	 
	
	 		
	 		conexao = modulo.ModuloConexao.conector();
	 	
	
	}
	
	
	 	
	 	
	 	/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	public static void main(String[] args) {
	 		EventQueue.invokeLater(new Runnable() {
	 			public void run() {
	 				try {
	 				TelaUsuario t1 = 	new TelaUsuario();
	 					t1.addcombobox();
	 				} catch (Exception e) {
	 					e.printStackTrace();
	 				}
	 			}
	 		});	
	 	}

	 	
	 	/*''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
	 	
	 	
	 	public void mouseClicked(MouseEvent e) {
	 		
	 		
	 		
	 	if (e.getSource() == tabela) {
			sett_campos();	
			sett_camposprivilegios();
		}
	 	
		
	 	
	 	
	 	if (e.getSource() == t_pesquisar) {
			pesquisar_usuario();
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


		/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == btn_registar) {
			    adicionar();
			   //	user.adicionar(c_nomef.getSelectedItem().toString(), t_nome.getText().toString(), p_senha.getText().toString(), c_perm.getSelectedItem().toString());
			    //adicionarprivilegios();
			}
			
			if (e.getSource() == btn_atualizar) {
				alterar();
			}
			
			if (e.getSource() == btn_remover) {
				remover();
			}
			
			if (e.getSource() == btn_limpar) {
				limparcampos();
				//pesquisar_bloqueio();
				//settchackbox();
			}
			
			if (e.getSource() == btn_salvar) {
				adicionarprivilegios();
				//gerarrelatorio1();
			}
			
		}





		
		public void keyTyped(KeyEvent e) {
			
			
		}





		
		public void keyPressed(KeyEvent e) {
			
			
		}





		
		public void keyReleased(KeyEvent e) {
			
			if (e.getSource() == t_pesquisar) {
					pesquisar_usuario();
			}
			
			if (e.getSource() == t_pesquser) {
				pesquisar_usuarioprivilegio();
		}
	    	
		}


}
