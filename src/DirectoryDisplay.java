import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;

import java.io.File;
import java.io.IOException;
public class DirectoryDisplay extends JFrame implements ActionListener {
	JLabel lb1;
	JTextField txt1,txt2,txt3;
	JButton bt1,bt2,bt3,bt4;
	TextArea ta1,ta2,ta3;
	JPanel pn1,pn2,pn3,pn4,pn;
	JList<String> ls1,ls2,ls3;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	DefaultListModel<String> dlm1 = new DefaultListModel<String>();
	DefaultListModel<String> dlm2 = new DefaultListModel<String>();
	JScrollPane js1,js2,js3;
		
	public void GUI() {
		lb1 = new JLabel("ĐỒ ÁN HỆ ĐIỀU HÀNH - CÂY THƯ MỤC");
		
		bt1 = new JButton("Scan all hard drive");
		bt2 = new JButton("Show files in chose drive");
		bt3 = new JButton("Reset");
		bt4 = new JButton("Exit");
		
		bt1.addActionListener(this);
		bt2.addActionListener(this);
		bt3.addActionListener(this);
		bt4.addActionListener(this);
		

		txt1 = new JTextField();
		txt2 = new JTextField();
		txt3 = new JTextField();
		
		ta1 = new TextArea();
		ta2 = new TextArea();
		ta3 = new TextArea();
		
		ls1 = new JList<String>(dlm);
		ls2 = new JList<String>(dlm1);
		ls3 = new JList<String>(dlm2);
		
		js1 = new JScrollPane(ls1);
		js2 = new JScrollPane(ls2);
		js3 = new JScrollPane(ls3);

		pn1 = new JPanel(new FlowLayout());
		pn2 = new JPanel(new GridLayout(2,2));
		pn3 = new JPanel(new FlowLayout());
		pn = new JPanel(new GridLayout(3,1));

		pn1.add(lb1);
		
		pn2.add(bt1);
		pn2.add(js1);
		
		pn2.add(bt2);
		pn2.add(js2);
		
		pn3.add(bt3);
		pn3.add(bt4);
		
		pn.add(pn1);
		pn.add(pn2);
		pn.add(pn3);;
		add(pn); //add vao frame
		setSize(700,700);//thiet lap kich thuwoc
		setVisible(true);//hien thi
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == bt1) {
			dlm.clear();
			showDisk();
			}
		else if(e.getSource() == bt2) {
			dlm1.clear();
			String s = ls1.getSelectedValue() + "\\";
			File file = new File(s);
			showFile(file);
			}
		else if(e.getSource() == bt3) {
			dlm.clear();
			dlm1.clear();
		}
		else if(e.getSource() == bt4) {
			System.exit(0);
		}
//			else if(e.getSource() == bt3) {
//				dlm2.clear();
//				String s = ls1.getSelectedValue() + "\\" + ls2.getSelectedValue();
//				File file = new File(s);
//				try {
//					showDetail(1,file);
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					dlm2.addElement(e1.getStackTrace().toString());
//				}
//			}
		}
	MouseListener mouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent mouseEvent) {
//	        JList<String> theList = (JList) mouseEvent.getSource();
	        if (mouseEvent.getClickCount() == 2) {
					new DetailFiles(ls1.getSelectedValue() + "\\" + ls2.getSelectedValue());
	          }
	        }
	    };
	public void showDisk(){
	    File[] drives = File.listRoots();
	    if (drives != null && drives.length > 0)
	        for (File aDrive : drives) 
	            dlm.addElement(aDrive.toString());
	    }
	public void showFile(File file){
        File[] fileList = file.listFiles();
        if(fileList == null)
            dlm1.addElement("Empty!");
        else{
//            System.out.println(file.getPath());
            dlm1.addElement(file.getPath());
            for(File files : fileList){
//                System.out.print("----");
//                System.out.println(files.getName());
            	dlm1.addElement(files.getName());
            }
//            ls2.addMouseListener(mouseListener);
        }
    }
	public void showDetail(int index, File file) throws IOException {
		String s = "";
	    for (int i = 0; i < index; i++)
//	      System.out.print('-');
	    	s += '-';
//	    System.out.println(file.getName());
	    s += file.getName();
	    dlm2.addElement(s);
	    if (file.isDirectory()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++)
	        showDetail(index + 4, files[i]);
	    }
	  }
	public DirectoryDisplay(String st) {
		super(st);
		GUI();
		ls2.addMouseListener(mouseListener);
	}
	public static void main(String[] args) {
		new DirectoryDisplay("Directory Display Ver 1");
	}

}