package Telas;

import Others.RoundedBorder;
import modulo.ModuloConexao;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.security.Principal;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.xml.crypto.Data;

public class Tela_Login extends JFrame implements  MouseListener, ActionListener, KeyListener {
	

	
	
	Connection conexao = null;
	PreparedStatement pst = null;
	ResultSet rs = null;  

	
	
public void logar() {	
		String sql = "select * from tbusuario where login = ? and senha = ?";
		try {
			
			pst = conexao.prepareStatement(sql);
			pst.setString(1, t_user.getText());
			String captura = new String(p_senha.getPassword());
			pst.setString(2, captura);
			
			// A linha abaixo executa a query
			rs = pst.executeQuery();
			
			//Se existir usuario e senha correspondente
			if(rs.next()) {
		                                                         		
			//	verificarfuncionario(rs.getString(2));
		       TelaPrincipal principal = new TelaPrincipal(); 
				principal.setVisible(true);
				
				if (rs.getString(5).equals("Usuario")) {
					principal.menuitem1.setEnabled(false);
					principal.menuitem2.setEnabled(false);
					principal.menuitem13.setVisible(false);
				}
				
			    principal.l_texto1.setText(rs.getString(2)); 
			    principal.l_texto2.setText("Perfil: " + rs.getString(5));
			    principal.l_texto3.setText(rs.getString(1));
			    
			    ativarrestricao(principal.l_imga, principal.l_imgfu, principal.l_imgma, principal.l_imgpa, rs.getString(1));
			
				this.dispose(); 
				conexao.close();
			
				
			}else {
				JOptionPane.showMessageDialog(null, "Usuario e/ou Senha invalida!!");
		}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
public void ativarrestricao(JLabel label1,JLabel label2, JLabel label3, JLabel label4 ,String getid) {
	
	String sql = "select * from tbbloqueio where iduser = ?";

	
	try {
		
		pst = conexao.prepareStatement(sql);
		pst.setString(1, getid);
		
		rs = pst.executeQuery();
		
		if (rs.next()) {
			
			if (rs.getBoolean(7) == false) {
				label1.setEnabled(false);
				//label1.removeMouseListener(this);
			}
			
          //'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
			
			if (rs.getBoolean(11) == false) {
				label2.setEnabled(false);
				//label2.removeMouseListener(this);
			}
			
			//'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''

			if (rs.getBoolean(15) == false) {
				label3.setEnabled(false);
				//label3.removeMouseListener(this);
			}
			
			//''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
			
            if (rs.getBoolean(19) == false) {
            	label4.setEnabled(false);
            	//label4.removeMouseListener(this);
			}
			
		}
	
		
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e);
	}
	
}


	
	
	 private JPanel contentPane;
     JPanel painel,p_vermelho;	
     JLabel l_sair,l_user,l_senha,l_img,l_hora,l_texto,l_texto1,lbstatus;
     Font f1,f2,f3,f4,f31,f11,f5,f6;
     JButton btn_login,btn_sair;
     private JTextField t_user;
	 private JPasswordField p_senha;
	

	 
	public static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela_Login tela = new Tela_Login();
					tela.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public Tela_Login() {
		
  		setUndecorated(true);
  	    setVisible(true);
  	    setResizable(false);
  	    setSize(600, 300);
  	    setLocationRelativeTo(null);
  	    getContentPane().setLayout(null);
  	    getContentPane().addKeyListener(this);
     
		
		
		f1 = new Font("Nexa", Font.CENTER_BASELINE, 16);
		f11 = new Font("Nexa", Font.CENTER_BASELINE, 22);
		f2 = new Font("Bahnschrift", Font.CENTER_BASELINE, 14);
		f3 = new Font("Nunito", Font.CENTER_BASELINE, 14);
		f31 = new Font("Nunito", Font.CENTER_BASELINE, 24);
		f4 = new Font("Popping", Font.BOLD, 24);
		f5 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT,12);
		f6 = new Font("Nunito", Font.LAYOUT_LEFT_TO_RIGHT, 11);
	   
		    painel = new JPanel();
		    painel.setBackground(Color.white);
		    painel.setBounds(0, 0, 600, 300);
		    painel.setLayout(null);
		
		    
		    l_user = new JLabel("Usuário");
			l_user.setFont(f3);
			l_user.setBounds(335,105,250,15);
			l_user.setForeground(new Color(180,81,81));
			//painel.add(l_user);
			
			
			l_senha = new JLabel("Senha");
			l_senha.setFont(f3);
			l_senha.setBounds(335,163,200,15);
			l_senha.setForeground(new Color(180,81,81));
			//painel.add(l_senha);
			
			t_user = new JTextField();
			t_user.setText("Didite seu nome");
			t_user.setBounds(335, 103, 180, 30);
			t_user.setForeground(new Color(88,88,88));
			t_user.setBackground(getForeground());
			t_user.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
			//t_user.setBorder(new RoundedBorder(30));
			t_user.setToolTipText("Digite sua ID");
			t_user.addMouseListener(this);
			t_user.addKeyListener(this);
			t_user.setColumns(10);
			t_user.setOpaque(false);
			painel.add(t_user);
		    
		    p_senha = new JPasswordField();
		    p_senha.setText("Digite a sua senha ");
	        p_senha.setOpaque(false);
		    p_senha.setBounds(335, 151, 180, 30);
		    p_senha.setForeground(new Color(88,88,88));
		  	p_senha.addMouseListener(this);
		  	p_senha.addKeyListener(this);
		    p_senha.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
		   // p_senha.setBorder(new RoundedBorder(30)) 
		    p_senha.setToolTipText("Digite sua senha");
		    painel.add(p_senha);
			
		    btn_login = new JButton("Entrar");
		    btn_login.setBounds(305, 210, 220, 30);
		    btn_login.setForeground(new Color(100,100,100));
			btn_login.setBackground(new Color(200,200,200));
			btn_login.setContentAreaFilled(false);
			btn_login.setFont(f1);
		    btn_login.setOpaque(false);
			btn_login.setBorder(new RoundedBorder(30));
			//btn_login.addMouseListener(this);
			btn_login.addActionListener(this);
			btn_login.addKeyListener(this);
			painel.add(btn_login);
			
			l_sair = new JLabel("X");
			l_sair.setBounds(570, 5, 50, 30);
			l_sair.setForeground(new Color(180,81,81));
			l_sair.setBackground(Color.white);
			//l_sair.setContentAreaFilled(false);
			l_sair.setFont(f11);
			l_sair.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, new Color(195, 135, 135)));
			l_sair.setOpaque(false);
			l_sair.addKeyListener(this);
			l_sair.addMouseListener(this);
			painel.add(l_sair);
			
			
			l_hora = new JLabel("");
			l_hora.setBounds(10,2,200,30);
			l_hora.setFont(f5);
			l_hora.setForeground(new Color(255,255,255));
			painel.add(l_hora);
			
			Date data = new Date();
			DateFormat formato =  DateFormat.getDateInstance(DateFormat.SHORT);
			
		    class hora implements ActionListener { 
	            public void actionPerformed(ActionEvent e) {
	            	Calendar now = Calendar.getInstance();
	            	 l_hora.setText(String.format("%1$tH:%1$tM:%1$tS", now));
	            	
	            }
	        }
		    Timer timer = new Timer(1000, new hora());
		     timer.start(); 
		
		
		    
		    
		    
		    l_img = new JLabel("");
		    l_img.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/login.png")));
		    l_img.setBounds(0, 0, 600, 300);
		    l_img.setBorder(new LineBorder(Color.lightGray));
		    l_img.setLayout(null);
		   // getContentPane().add(l_imgp);  
		    
		
		/*'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''*/
		    lbstatus = new JLabel(" ");
			lbstatus.setBackground(Color.WHITE);
			lbstatus.setForeground(new Color(200,200,200,200));
			lbstatus.setBounds(30,  200, 100, 100);
			

		   conexao = ModuloConexao.conector();
	       System.out.println(conexao);
	       
	       if (conexao != null) {
			lbstatus.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/right.png")));
			painel.add(lbstatus);
		}else {
			lbstatus.setIcon(new ImageIcon(Tela_Login.class.getResource("/imgs/erroc.png")));
			painel.add(lbstatus);
		}
			
			
	
		painel.add(l_img);
	  getContentPane().add(painel);
		
	}
	
	
	// EVENTOS DO MOUSE :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
	
	// MOUSECLIKED
	public void mouseClicked(MouseEvent e) {
		 if (e.getSource() == l_sair) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar saida?","   Mensagem", JOptionPane.YES_NO_OPTION);
				   if (confirmar == JOptionPane.YES_OPTION) {
					System.exit(0);
				   }
		 }
		
		 
		 
		  if(e.getSource() == t_user) {
	        	t_user.setText("");
	        }
		
		  
		   if(e.getSource() == p_senha) {
	        	p_senha.setText("");
	        }
	}


    //MOUSEPRESSED
	public void mousePressed(MouseEvent e) {
	
		
	}


 

	//MOUSERELEASED
	public void mouseReleased(MouseEvent e) {
	
		if(e.getSource()== p_senha) {
        	p_senha.setText("");
        }
	}


	//MOUSEENTERED
	public void mouseEntered(MouseEvent e) {
		
		
		if(e.getSource() == btn_login) {
			btn_login.setForeground(new Color(151,39,21));
			btn_login.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			
		}
		
		
		if(e.getSource() == l_sair) {
			l_sair.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				l_sair.setForeground(new Color(100,100,100));
		}
		
	}


	//MOUSEEXITED
	public void mouseExited(MouseEvent e) {
		
		if(e.getSource() == btn_login) {
			btn_login.setForeground(new Color(100,100,100));
			btn_login.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
		
		
		if(e.getSource() == l_sair) {
			l_sair.setForeground(new Color(180,81,81));
			l_sair.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
			}
		
		 
		  if(e.getSource() == t_user) {
			  if (t_user.getText().equals(null)) {
				  t_user.setText("Digite seu nome");
			}else {
				
			  }
	        }
		
		  
		   if(e.getSource() == p_senha) {
			   if (p_senha.getText().equals(null)) {
				p_senha.setText("Digite sua senha");
			}else {
				
			}
	        	
	        }
		   
		   
	}


//EVENTOS  	
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == btn_login) {
		logar();
		//tu.pesquisar_bloqueio();
		//tu.settchackbox();
	}
	
}


//KEYTYPED
public void keyTyped(KeyEvent e) {
if (e.getKeyCode()== KeyEvent.VK_TAB) {
		p_senha.setText("");
}
	
}


//KEYPRESSED
public void keyPressed(KeyEvent e) {
	
 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		 logar();
	}
 
 
 if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
		int confirmar = JOptionPane.showConfirmDialog(null, "Confirmar saida?","   Mensagem", JOptionPane.YES_NO_OPTION);
		   if (confirmar == JOptionPane.YES_OPTION) {
			System.exit(0);
		   }
}
 

if (e.getKeyCode()== KeyEvent.VK_TAB) {
	if (p_senha.getCursor().equals(DEFAULT_CURSOR)) {
		p_senha.setText("");
	}
}

}


//KEYREALEASED
public void keyReleased(KeyEvent e) {


	
}
	
	
	
	



}
