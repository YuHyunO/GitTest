import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class LoginPanel extends JPanel
{	
	MainFrame mf;
	DBManager dbm;
	JLabel logoLabel, loginNeededLabel, idLabel, pwdLabel;
	JTextField idField, pwdField;
	JButton loginButton, signUpButton;
	
	LoginPanel(MainFrame mf){
		this.mf = mf;
		dbm = new DBManager();
		setPanel();
	}
	void setPanel(){
		setLayout(null);
		setSize(433, 475);
		setBackground(new Color(255, 255, 255));
		
		logoLabel = new JLabel(new ImageIcon(".\\imgs\\bigLogo.png"));
		loginNeededLabel = new JLabel(new ImageIcon(".\\imgs\\loginNeededIcon.png"));
		idLabel = new JLabel(new ImageIcon(".\\imgs\\idIcon.png"));
		pwdLabel = new JLabel(new ImageIcon(".\\imgs\\passwordIcon.png"));

		idField = new JTextField();
		idField.setBorder(BorderFactory.createEmptyBorder());
		idField.setBackground(new Color(217, 233, 255));

		pwdField = new JTextField();
		pwdField.setBorder(BorderFactory.createEmptyBorder());
		pwdField.setBackground(new Color(217, 233, 255));

		loginButton = new JButton(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
			String id,pwd;			
				if(e.getSource()==loginButton){
					id=idField.getText().trim();				
					if(id.length()==0) {
						JOptionPane.showMessageDialog(null,"ID를 입력해주세요.","ERROR!",JOptionPane.WARNING_MESSAGE);				
					}else if(id.length()!=0){
						pwd= pwdField.getText().trim();								
						if(pwd.length()==0){
							JOptionPane.showMessageDialog(null,"PWD를 입력해주세요.","ERROR!",JOptionPane.WARNING_MESSAGE);
						}else if(pwd.length()!=0){								
							if(dbm.login(idField.getText().trim(),pwdField.getText().trim())==true){
								dbm.insertL(id,pwd);
								JOptionPane.showMessageDialog(null, "로그인 되었습니다.", "어서오세요!", JOptionPane.INFORMATION_MESSAGE);
								
								System.out.println("로그인 db확인");
							}else{
								JOptionPane.showMessageDialog(null, "ID나 PWD을 다시 한번 확인해주세요", "로그인불가능", JOptionPane.WARNING_MESSAGE);
							}
						}				
					}
				}
				idField.setText("");
				pwdField.setText("");

				mf.cp.removeAll();
				mf.setDefaultPanel(); 
				mf.setMyPagePanel(); 
				mf.repaint();
				mf.revalidate();
			}
		});
		loginButton.setIcon(new ImageIcon(".\\imgs\\loginButtonIcon.png"));
		loginButton.setBackground(new Color(95, 161, 255));
		loginButton.setBorderPainted(false);

		signUpButton = new JButton(new AbstractAction(){
			public void actionPerformed(ActionEvent e){
				mf.cp.removeAll();
				mf.setDefaultPanel(); 
				mf.setSignUpPanel(); 
				mf.repaint();
				mf.revalidate();
			}
		});
		signUpButton.setIcon(new ImageIcon(".\\imgs\\signUpButtonIcon.png"));
		signUpButton.setBackground(new Color(95, 161, 255));
		signUpButton.setBorderPainted(false);
	
		add(logoLabel);
		logoLabel.setBounds(145, 46, 140, 40);
		add(loginNeededLabel);
		loginNeededLabel.setBounds(58, 88, 330, 50);
		add(idLabel);
		idLabel.setBounds(75, 186, 90, 17);
		add(pwdLabel);
		pwdLabel.setBounds(75, 230, 90, 17);

		add(idField);
		idField.setBounds(176, 182, 181, 26);
		add(pwdField);
		pwdField.setBounds(176, 226, 181, 26);

		add(loginButton);
		loginButton.setBounds(176, 300, 72, 23);
		add(signUpButton);
		signUpButton.setBounds(176, 340, 72, 23);
	}
}
