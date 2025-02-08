package myproject;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class EncryptPage extends JFrame implements ActionListener{
    

    private JLabel codelabel , secretlabel , picturelabel;
    private JTextField codetext , secrettext,picturetext;
    private JButton picturebutton , hidebutton,homebutton;

    String filepath="",secretCode="",secretInfo="",userKey="";
    Container con=null;
    JLabel jl;
    byte imgByte[]=new byte[6000];
    FileDialog fd;


    //variables for creating image

    Image img ;
    Dimension d;
    int iw,ih;
    int w=10,h=10;
    int pix[];
    int hist[]=new int[256];
    int t[];
    int max_hist=0;
    boolean ok;
	static Image newimg;
	int key,k;

    public EncryptPage() {
        super("Encrypt");
        con=getContentPane();
        con.setLayout(null);
        codelabel=new JLabel("Enter Security Code");
        codelabel.setBounds(230,100,150,50);
        
        codetext=new JTextField(200);
		codetext.setBounds(400,100,250,40);
		secretlabel=new JLabel("Secret Information");
		secretlabel.setBounds(230,200,150,50);
		secrettext=new JTextField(200);
		secrettext.setBounds(400,200,250,40);

		picturelabel=new JLabel("Picture");
		picturelabel.setBounds(230,300,250,40);
		picturetext=new JTextField(200);
		picturetext.setBounds(400,300,250,50);
		picturebutton=new JButton("Load");	
		picturebutton.setBounds(700,300,150,30);
		picturebutton.addActionListener(this);
	
		hidebutton=new JButton("Hide");
		hidebutton.setBounds(400,400,150,30);
		hidebutton.addActionListener(this);
		homebutton=new JButton("Home");
		homebutton.setBounds(700,400,150,30);
		homebutton.addActionListener(this);

		jl=new JLabel();
		jl.setBounds(700,500,150,30);

		fd=new FileDialog(new JFrame());

		con.add(codelabel);
		con.add(codetext);
		con.add(secretlabel);
		con.add(secrettext);
		con.add(picturelabel);
		con.add(picturetext);
		con.add(picturebutton);
		con.add(hidebutton);
		con.add(homebutton);
		//con.add(jl);
	}


    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==picturebutton){
            fd.setVisible(true);
            filepath=fd.getDirectory()+fd.getFile();
            picturetext.setText(filepath);

        } else if(ae.getSource()==hidebutton){
            int flag=0;
            secretCode=codetext.getText();
            for(int i=0;i<secretCode.length();i++){
                if(secretCode.charAt(i)=='*'){
                    flag=1;

                }
            }
            if(flag==0){
                secretInfo=secrettext.getText();
                userKey=secretCode+"*"+new String("" +secretInfo.length());
                System.out.println("user key  :"+userKey);
                String secretCodeInfo =  userKey+"*"+secretInfo+"*";
                byte secretByteArray[]=secretCodeInfo.getBytes();
            int secretIntArray[]=new int[secretByteArray.length];
            try {
                if(filepath.equals("" )&& (secrettext.getText().equals(""))){
                    JOptionPane.showMessageDialog(null,"Invalid Entry!!! Enter Again");

                }else if(secretInfo.length()==0 && filepath.length()>0){
                    JOptionPane.showMessageDialog(null,"Enter Secret Info");
                }
                else if(filepath.length()==0 && (secrettext.getText()).length()>0){
                    JOptionPane.showMessageDialog(null,"load an image");
                }
                else{
                    ImageIcon ic = new ImageIcon(filepath);
                    img=ic.getImage();
                    iw=img.getWidth(null);
                    ih=img.getHeight(null);
                    pix=new int[iw*ih];
                    t=new int[iw*ih];
                    PixelGrabber pg =new PixelGrabber(img, 0, 0, iw, ih, pix,0,iw);
                    ColorModel cm=pg.getColorModel();
                    int ww=pg.getWidth();
                    int hh=pg.getHeight();
                    pg.grabPixels();

                    key=secretByteArray.length;
                    int k=key;
                    int j=0;
                    for(int i=0;i<pix.length;i++){
                        if((i%20)==0 && k>0){
                            secretIntArray[j]=(int)secretByteArray[j];
                            System.out.println("User Key : "+secretIntArray[j]);
                            pix[i]=secretIntArray[j];
                            j++;
                            k--;
                        }
                    }
                    newimg=con.createImage(new MemoryImageSource(ww,hh,cm,pix,0,ww));
                    jl.setIcon(new ImageIcon(newimg));
                    JOptionPane.showMessageDialog(null, "your secret code:"+userKey);

                    MediaTracker  mt=new MediaTracker(new Container());
                    mt.addImage(newimg,0);
                    mt.waitForID(0);
                    int thumbwidth=400;
                    int thumbheight=400;
                    double thumbratio=(double)thumbwidth / (double)thumbheight;
                    int imageWidth = newimg.getWidth(null);
						int imageHeight = newimg.getHeight(null);
						double imageRatio = (double)imageWidth / (double)imageHeight;
		
						if (thumbratio < imageRatio)
						{
      							thumbheight = (int)(thumbwidth / imageRatio);
						}
    						else 
						{
      							thumbwidth = (int)(thumbheight * imageRatio);
	    					}
                            BufferedImage thumbImage=new BufferedImage(newimg.getWidth(null), newimg.getHeight(null), BufferedImage.TYPE_INT_RGB);
                            Graphics2D g=thumbImage.createGraphics();
                            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                            g.drawImage(newimg,0,0,newimg.getWidth(null),newimg.getHeight(null),null);
                            File f=new File("secpic.jpg");
                            	File outputFile = new File("secpic.jpg");
						ImageIO.write(thumbImage, "jpg", outputFile);

    						System.out.println("Done.");	
				
						test t=new test(newimg);
						t.setSize(1035,790);
						t.setVisible(true);
                            



                }
            } catch (Exception e) {
                System.out.println(e);			
            }
        }else
        JOptionPane.showMessageDialog(null,"Do not enter '*' in secrect code");	
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
		EncryptPage cp=new EncryptPage();
		cp.setSize(1035,740);
		cp.setVisible(true);
	}
}
