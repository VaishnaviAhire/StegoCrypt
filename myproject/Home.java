package myproject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Home extends JFrame implements ActionListener{
    private JButton encrypt , decrypt;



    Home(){
        super("Steganography");
        Container con=getContentPane();
        con.setLayout(null);
        encrypt=new JButton("Encrypt ");
        encrypt.addActionListener(this);
        encrypt.setBounds(300,350,150,50);
        decrypt=new JButton("Decrypt ");
        decrypt.addActionListener(this);
        decrypt.setBounds(550,350,150,50);
        con.add(encrypt);
        con.add(decrypt);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==encrypt){
            this.dispose();
       	EncryptPage cp=new EncryptPage ();
			cp.setSize(1035,790);
			cp.setVisible(true);

        }
        if(ae.getSource()==decrypt)
		{
			this.dispose();
			DecryptPage bp=new DecryptPage();
			bp.setSize(1035,790);
			bp.setVisible(true);
		}


    }


    public static void main(String[] args) {
        Home h = new Home();
        h.setSize(1035,950);
        h.setVisible(true);
    }
}