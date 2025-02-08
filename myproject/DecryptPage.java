package myproject;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class DecryptPage extends JFrame implements ActionListener {
    private JLabel codelabel,picturelabel;
	private JTextField codetext,picturetext;
	private JButton picturebutton,breakbutton,homebutton;
	String filepath="",secretcode="",secretinfo="";
	Container con=null;
	JLabel jl;
	byte imgbyte[]=new byte[6000];
	FileDialog fd;

    Image img;
	Dimension d;
	int iw,ih;
	int w=10,h=10;
	int pix[];
	int hist[]=new int[256];
	int t[];
	int maxhist=0;
	boolean ok;
	Image newimg;
	int key,k;
	String userkey="";

    
    DecryptPage(){
        super("Break");
        con=getContentPane();
		con.setLayout(null);

		codelabel=new JLabel("Security Code");
		codelabel.setBounds(230,200,150,50);
		codetext=new JTextField(200);
		codetext.setBounds(400,200,250,40);
		
		picturelabel=new JLabel("Picture");
		picturelabel.setBounds(230,300,250,40);
		picturetext=new JTextField(200);
		picturetext.setBounds(400,300,250,50);
		picturebutton=new JButton("Load");	
		picturebutton.setBounds(700,300,150,30);
		picturebutton.addActionListener(this);
	
		breakbutton=new JButton("Break");
		breakbutton.setBounds(400,400,150,30);
		breakbutton.addActionListener(this);
		homebutton=new JButton("Home");
		homebutton.setBounds(700,400,150,30);
		homebutton.addActionListener(this);
		
		jl=new JLabel();
		jl.setBounds(700,500,150,30);

		fd=new FileDialog(new JFrame());

		con.add(codelabel);
		con.add(codetext);
		con.add(picturelabel);
		con.add(picturetext);
		con.add(picturebutton);
		con.add(breakbutton);
		con.add(homebutton);
		con.add(jl);
    }

    public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==picturebutton)
		{
			fd.setVisible(true);
			filepath=fd.getDirectory()+fd.getFile();
			picturetext.setText(filepath);
		}else if(ae.getSource()==breakbutton)
		{
			String sc=codetext.getText();
			int star_flag=0;
			String star_value="";
			for(int i=0;i<sc.length();i++)
			{
				if(sc.charAt(i)=='*')
					star_flag=1;
				if(star_flag==1&& star_flag!=2)
				{
					i=++i;
					star_value=sc.substring(i);
					star_flag=2;					
				}
			}
			System.out.println("star value er:"+Integer.parseInt(star_value));
			k=sc.length()+1+Integer.parseInt(star_value);
			try{
			img=EncryptPage.newimg;
			key=k;
			System.out.println("key ckeck in temp:"+key);
			userkey=sc;

			Container con=getContentPane();

			iw=img.getWidth(null);
			ih=img.getHeight(null);
			pix=new int[iw*ih];
			t=new int[iw*ih];

			PixelGrabber pg=new PixelGrabber(img,0,0,iw,ih,pix,0,iw);
			ColorModel cm=pg.getColorModel();
			int ww=pg.getWidth();
			int hh=pg.getHeight();
			pg.grabPixels();

			int secret_check[]=new int[sc.length()];
			byte sc_byte[]=sc.getBytes();
		
			for(int i=0;i<sc.length();i++)
				secret_check[i]=sc_byte[i];

			int secret_info[]=new int[key];		
			byte b[]=new byte[key];
			int j=0,loop=0,flag=0,star2_flag=0;

			System.out.println("hi welcome");

			for(int i=0;i<pix.length;i++)
			{
				if((i%20)==0 && k>0 && flag==0) 
				{
					
					System.out.println("one");

					if(loop<userkey.length() && secret_check[loop]==pix[i] && star2_flag<2)
					{
						System.out.println("two");
						if((char)secret_check[loop]=='*')						
						{
							star2_flag++;
						}

						k--;
						loop++;
					}else if(star2_flag>=1)
					{
						System.out.println("else if");
						secret_info[j]=pix[i];
						b[j]=(byte)pix[i];
						System.out.println("secrect pix :"+new String(""+(char)b[j])+"");
						j++;
						k--;	
					}
					else 
					{
						System.out.println("star flag  :"+star2_flag);
						System.out.println("else");
						flag=1;
					}
				}
			}
			if(flag==0)	
			{
				String s=new String(b);
				s=new String(s.substring(1));
				
				System.out.println("secret information :"+s);
				System.out.println("key  :"+key);			
				JOptionPane.showMessageDialog(null,"Secret Information is : "+s);
			}
			else
				JOptionPane.showMessageDialog(null,"code you entered is not valid");		
			newimg =con.createImage(new MemoryImageSource(ww,hh,cm,pix, 0, ww));
		}catch(Exception e)
		{
			System.out.println(e);
		}
		}else 
		{
			this.dispose();
			Home h=new Home();
			h.setSize(1035,790);
			h.setVisible(true);
		}
	}
	public static void main(String args[])
	{
		DecryptPage bp=new DecryptPage();
		bp.setSize(1035,740);
		bp.setVisible(true);
	}
}
