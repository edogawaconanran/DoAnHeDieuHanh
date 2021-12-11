import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
public class DetailFiles extends JFrame implements ActionListener {
	JPanel pn,pn1,pn2,pn3;
	JButton bt1;
	JScrollPane js;
	static String a;
	DefaultListModel<String> dlm = new DefaultListModel<String>();
	JList<String> ls;
	public void GUI() {
		bt1 = new JButton("Back");
		bt1.addActionListener(this);
		
		pn = new JPanel(new GridLayout(3,1));
		pn1 = new JPanel(new GridLayout(1,1));
		pn2 = new JPanel(new FlowLayout());
		
		ls = new JList<String>(dlm);
		
		js = new JScrollPane(ls);
		
		pn1.add(js);
		pn2.add(bt1);
		pn.add(pn1);
		pn.add(pn2);
		add(pn); //add vao frame
		setSize(400,300);//thiet lap kich thuwoc
		setVisible(true);//hien thi
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == bt1) {
			this.dispose();
		}
		}
	public void showDetail(int index, File file) throws IOException {
		String s = "";
	    for (int i = 0; i < index; i++)
//	      System.out.print('-');
	    	s += '-';
//	    System.out.println(file.getName());
	    s += file.getName();
	    dlm.addElement(s);
	    if (file.isDirectory()) {
	      File[] files = file.listFiles();
	      for (int i = 0; i < files.length; i++)
	        showDetail(index + 4, files[i]);
	    }
	  }
	public DetailFiles(String st) {
//		super(st);
		GUI();
		dlm.clear();
		File file = new File(st);
		try {
			showDetail(1,file);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			dlm.addElement(e1.getStackTrace().toString());
		}
	}
	public static void main(String[] args ){
		new DetailFiles("Detail files");
	}

}
