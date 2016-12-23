package projecttt;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;
import org.jdatepicker.impl.UtilDateModel;

import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

public class testconnection extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	DBConnect BookIT = new DBConnect();
	private JTextField ID;
	
	private User normalUser=new User(BookIT);
	private PremiumUser premiuumUser =new PremiumUser(BookIT);
	private Admin adminUser=new Admin(BookIT);
	private JPasswordField pass;
	private JTextField ID2;
	private JTextField mail;
	private JTextField phone;
	private JPasswordField pass2;
	private JPanel Welcome ;
	private JPanel reg ;
	private JPanel PremiumUser ;
	private JPanel viewRooms;
	private JTextField textField;
	private JPanel reserve;
	private Reservation myreservation;
	private JPanel reserve2;
	private ArrayList<Integer> avrooms2;
	private ArrayList<Reservation> myress ;
	//private JTable table;
	private JPanel viewres ;
	
	public int bolen2num(boolean a){
		if(a){
			return 1;
		}
		else {
			return 0;
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					testconnection frame = new testconnection();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public testconnection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
	    
	     reserve2 = new JPanel();
	     reserve2.setVisible(false);
	    contentPane.add(reserve2, "name_90806465694339");
	    reserve2.setLayout(null);
	    
	    JLabel label_18 = new JLabel("Room Reservation PostSearch");
	    label_18.setFont(new Font("Tahoma", Font.PLAIN, 22));
	    label_18.setBounds(144, 11, 320, 37);
	    reserve2.add(label_18);
	    
	    JLabel label_19 = new JLabel("Add email list");
	    label_19.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    label_19.setBounds(50, 74, 136, 31);
	    reserve2.add(label_19);
	    
	    JTextArea textArea = new JTextArea();
	    textArea.setBounds(29, 116, 157, 265);
	    reserve2.add(textArea);
	    
	    JList list_1 = new JList();
	    list_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    list_1.setBounds(358, 113, 194, 268);
	    reserve2.add(list_1);
	    
	    JLabel label_20 = new JLabel("Suitible Class Room");
	    label_20.setFont(new Font("Tahoma", Font.PLAIN, 18));
	    label_20.setBounds(358, 74, 200, 31);
	    reserve2.add(label_20);
	    
	    JButton button_6 = new JButton("Emergency Reservation Request");
	    button_6.setEnabled(false);
	    button_6.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		/////////////////////
	    		Classroom classx = new Classroom();
	    		classx.setClassroomID(null);
	    		myreservation.setClassroom(classx);
	    		myreservation.setStatus("waiting");
	    		 if(premiuumUser.getUserID() !=0) {
	    			
				    //////////////
				    myreservation.setUserID(premiuumUser.getUserID());
				    premiuumUser.reserveClass(myreservation);
					myreservation.setReservationID( BookIT.getnextresID());	   

					   EmergencyRequest req =premiuumUser.requestEmergencyRequest(myreservation);
					 //  System.out.println(req.getMyReservation().getReservationID()+" "+req.getReplacementReservation().getReservationID());
					   if(req==null){
						   JOptionPane.showMessageDialog (null, 
			                        "Couldn't request an emergency request");
						   
					   }
					   else{
						   JOptionPane.showMessageDialog (null, 
					   
		                        "Emergency Request is sent to admin, wait for an answer mail  ");
                        BookIT.addEmergency(req);
				    ////////////
				
					   }
	    		 }
				else {
					 myreservation.setUserID(adminUser.getUserID());
					   adminUser.reserveClass(myreservation);
					   EmergencyRequest req =adminUser.requestEmergencyRequest(myreservation);
					   if(req==null){
						   JOptionPane.showMessageDialog (null, 
			                        "Couldn't request an emergency request");
						   
					   }
					   else{
						   JOptionPane.showMessageDialog (null, 
					   
		                        "Emergency Request is sent to admin, wait for an approval mail  ");
                           BookIT.addEmergency(req);					   
					   }
					 }
	    		//////////////////////
	    		
	    		 button_6.setEnabled(false);
	    		
	    	}
	    });
	    button_6.setForeground(Color.RED);
	    button_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    button_6.setBounds(29, 392, 258, 37);
	    reserve2.add(button_6);
	    
	    JButton button_7 = new JButton("Reserve The Selected Room");
	    button_7.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	 
	    	
	    	//
	    		myreservation.setClassroom(BookIT.getClassRoom(avrooms2.get(list_1.getSelectedIndex())));
	    	//	System.out.println(myreservation.getClassroom().getClassroomID());
	    		myreservation.setStatus("reserved");
	    		 if(premiuumUser.getUserID() !=0) {
	    			 myreservation.setUserID(premiuumUser.getUserID());
				    premiuumUser.reserveClass(myreservation);
				    JOptionPane.showMessageDialog (null, 
	                        "Reservation Complete  ");
				}
				else {
					 myreservation.setUserID(adminUser.getUserID());
					   adminUser.reserveClass(myreservation);
					   JOptionPane.showMessageDialog (null, 
		                        "Reservation Complete  ");
				}
					
				
	    		
	    	}
	    });
	    button_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    button_7.setBounds(349, 392, 219, 37);
	    reserve2.add(button_7);
	    
	    JButton btnwait = new JButton("Join waiting list");
	    btnwait.setEnabled(false);
	    btnwait.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		Classroom classx = new Classroom();
	    		classx.setClassroomID(null);
	    		myreservation.setClassroom(classx);
	    		myreservation.setStatus("waiting");
	    		 if(premiuumUser.getUserID() !=0) {
	    			 myreservation.setUserID(premiuumUser.getUserID());
				    premiuumUser.reserveClass(myreservation);
				    JOptionPane.showMessageDialog (null, 
	                        "You are now on the waiting list  ");
				}
				else {
					 myreservation.setUserID(adminUser.getUserID());
					   adminUser.reserveClass(myreservation);
					   JOptionPane.showMessageDialog (null, 
		                        "You are now on the waiting list  ");
				}
	    		 btnwait.setEnabled(false);
	    	}
	    });
	    btnwait.setBounds(196, 175, 148, 48);
	    reserve2.add(btnwait);
	    
	    JButton btnBack = new JButton("Return to home");
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		reserve2.setVisible(false);
	    		PremiumUser.setVisible(true);
	    	}
	    });
	    btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnBack.setBounds(196, 266, 148, 37);
	    reserve2.add(btnBack);
	   
	    reserve = new JPanel();
	    reserve.setVisible(false);
	   contentPane.add(reserve, "name_90500405119108");
	   reserve.setLayout(null);
	   
	   JLabel label_12 = new JLabel("Room Reservation Page");
	   label_12.setFont(new Font("Tahoma", Font.PLAIN, 22));
	   label_12.setBounds(117, 24, 255, 50);
	   reserve.add(label_12);
	   
	   JLabel label_13 = new JLabel("Date");
	   label_13.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   label_13.setBounds(27, 133, 108, 22);
	   reserve.add(label_13);
	   
	   JLabel label_14 = new JLabel("Time");
	   label_14.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   label_14.setBounds(27, 174, 108, 22);
	   reserve.add(label_14);
	   
	   JLabel label_15 = new JLabel("Purpose");
	   label_15.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   label_15.setBounds(27, 220, 108, 22);
	   reserve.add(label_15);
	   
	   JLabel label_16 = new JLabel("Equipment");
	   label_16.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   label_16.setBounds(27, 266, 108, 22);
	   reserve.add(label_16);
	   
	   JLabel label_17 = new JLabel("Number of chairs");
	   label_17.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   label_17.setBounds(27, 317, 145, 22);
	   reserve.add(label_17);
	   String tiime [] = {"08:20:00" , "09:30:00" ,"10:40:00" ,"11:50:00" ,"13:00:00" ,"14:10:00" ,"15:20:00" ,"16:30:00" ,"17:40:00" ,"18:50:00" ,"19:00:00" ,"20:10:00" ,"21:20:00"};
		////////////////////////////
	   SqlDateModel model5 = new SqlDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p2 = new Properties();
		p2.put("text.today", "Today");
		p2.put("text.month", "Month");
		p2.put("text.year", "Year");
		JDatePanelImpl datePanel2 = new JDatePanelImpl(model5, p2);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2, new DateLabelFormatter());	
		SpringLayout springLayout = (SpringLayout) datePicker2.getLayout();
		springLayout.putConstraint(SpringLayout.WEST, datePicker2.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker2);
		datePicker2.setBounds(297, 137, 151, 20);
		reserve.add(datePicker2);
	   
	   JComboBox comboBox_3 = new JComboBox(tiime);
	   comboBox_3.setBounds(297, 178, 151, 20);
	   reserve.add(comboBox_3);
	   String  purpose [] = {"Lecture" , "Tutorial" ,"Exam" , "Seminar" , "StudentActivity" , "Meeting"}; 
	   JComboBox comboBox_4 = new JComboBox(purpose);
	   comboBox_4.setBounds(297, 224, 151, 20);
	   reserve.add(comboBox_4);
	   
	   textField = new JTextField();
	   textField.setColumns(10);
	   textField.setBounds(297, 321, 151, 20);
	   reserve.add(textField);
	   
	   JCheckBox checkBox = new JCheckBox("projector");
	   checkBox.setBounds(162, 269, 97, 23);
	   reserve.add(checkBox);
	   
	   JCheckBox checkBox_1 = new JCheckBox("computer");
	   checkBox_1.setBounds(262, 269, 97, 23);
	   reserve.add(checkBox_1);
	   
	   JCheckBox checkBox_2 = new JCheckBox("microphone ");
	   checkBox_2.setBounds(363, 269, 97, 23);
	   reserve.add(checkBox_2);
	   
	   JCheckBox checkBox_3 = new JCheckBox("smart board");
	   checkBox_3.setBounds(475, 269, 97, 23);
	   reserve.add(checkBox_3);
	   
	   
	   JButton button_5 = new JButton("Begin Search");
	   button_5.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		myreservation = new Reservation();
	   		myreservation.setDate(LocalDate.parse(datePicker2.getModel().getValue().toString()));
	   		myreservation.setTimeslot(comboBox_3.getSelectedItem().toString());
	   		myreservation.setPurpose(comboBox_4.getSelectedItem().toString());
	   		myreservation.setNumberofStudents(Integer.parseInt(textField.getText()));
	   		myreservation.setMic(bolen2num(checkBox_2.isSelected()));
	   		myreservation.setProj(bolen2num(checkBox.isSelected()));
	   		myreservation.setComp(bolen2num(checkBox_1.isSelected()));
	   		myreservation.setSmart(bolen2num(checkBox_3.isSelected()));
	   		if (premiuumUser.getUserID() !=0){
	   		myreservation.calPriority(premiuumUser.UniversityPosition);
	   		}
	   		else {
	   			myreservation.calPriority(adminUser.UniversityPosition);
	   		}
	   		///////////////////
	   		avrooms2 = new ArrayList();
	   		avrooms2 = BookIT.SearchAvaliableClasses(myreservation.getDate().toString(), myreservation.getTimeslot(), myreservation.getNumberofStudents(), myreservation.getMic(), myreservation.getComp(), myreservation.getProj(), myreservation.getSmart()) ;
  		DefaultListModel model4 = new DefaultListModel();
			for (Integer dum :avrooms2){
				model4.addElement(dum);
			}
			list_1.setModel(model4);
  		if (avrooms2.size() == 0) {
  			btnwait.setEnabled(true);
  			button_6.setEnabled(true);
  			button_7.setEnabled(false);
  			
  		}
  		
	   		///////////////////
	   		reserve.setVisible(false);
	   		reserve2.setVisible(true);
	   	}
	   });
	   button_5.setBounds(106, 373, 166, 37);
	   reserve.add(button_5);
	   
	   JButton bttnBack = new JButton("Return to home");
	   bttnBack.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		reserve.setVisible(false);
	   		PremiumUser.setVisible(true);
	   		
	   	}
	   });
	   bttnBack.setBounds(349, 373, 166, 37);
	   reserve.add(bttnBack);
	   
	
	    PremiumUser = new JPanel();
	    PremiumUser.setVisible(false);
	   contentPane.add(PremiumUser, "name_90064267017846");
	   PremiumUser.setLayout(null);
	   
	   JLabel label_11 = new JLabel("Premium User Page");
	   label_11.setFont(new Font("Tahoma", Font.PLAIN, 22));
	   label_11.setBounds(146, 57, 268, 27);
	   PremiumUser.add(label_11);
	   
	   JButton button_2 = new JButton("View Rooms");
	   button_2.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		PremiumUser.setVisible(false);
	   		viewRooms.setVisible(true);
	   	}
	   });
	   button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   button_2.setBounds(146, 123, 249, 39);
	   PremiumUser.add(button_2);
	   
	   JButton button_3 = new JButton("Reserve a room");
	   button_3.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		PremiumUser.setVisible(false);
	   		reserve.setVisible(true);
	   	}
	   });
	   button_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   button_3.setBounds(146, 192, 249, 39);
	   PremiumUser.add(button_3);
	   String col[] = {"Classroom","Date","Time"};

		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		                            

		JTable table = new JTable(tableModel);
		table.setBounds(152, 112, 421, 263);
	
	   
	   JButton button_4 = new JButton("View/Edit my reservations");
	   JList list_2 = new JList();
		list_2.setBounds(41, 111, 74, 263);
		
	   button_4.addActionListener(new ActionListener() {
	   	public void actionPerformed(ActionEvent e) {
	   		PremiumUser.setVisible(false);
	   		viewres.setVisible(true);
	   		 myress = premiuumUser.viewmyReservation("2016-01-12", "2018-01-25");
	   		
	   		 
	   		DefaultListModel model5 = new DefaultListModel();
	   		for(Reservation dum:myress){
	   			//System.out.println(dum.getReservationID());
		   		Object[] objs = {dum.getClassroom().getClassroomID(), dum.getDate().toString(),dum.getTimeslot()};
		   		tableModel.addRow(objs);
		   	
		   		model5.addElement(dum.getClassroom().getClassroomID());
	   		}

	   		/////////////
	   		
		
			list_2.setModel(model5);
	   		///////////////
	   	}
	   });
	   button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
	   button_4.setBounds(146, 262, 249, 39);
	   PremiumUser.add(button_4);
		
	   Welcome = new JPanel();
		Welcome.setLayout(null);
		contentPane.add(Welcome, "name_69286768871248");
		Welcome.setVisible(true);
		JLabel label = new JLabel("User ID");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(77, 124, 91, 20);
		Welcome.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_1.setBounds(77, 189, 91, 30);
		Welcome.add(label_1);
		
		ID = new JTextField();
		ID.setColumns(10);
		ID.setBounds(205, 121, 260, 30);
		Welcome.add(ID);
		
		JButton button = new JButton("Register");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			Welcome.setVisible(false);
			reg.setVisible(true);
				
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button.setBounds(114, 278, 150, 38);
		Welcome.add(button);
		
		JButton button_1 = new JButton("Reset Password");
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		button_1.setBounds(235, 361, 150, 38);
		Welcome.add(button_1);
		
		JLabel label_2 = new JLabel("Welcome to BookIt");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_2.setBounds(206, 35, 259, 38);
		Welcome.add(label_2);
		
		JButton btnLogIn = new JButton("Log in");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String passText = new String(pass.getPassword());
			
			normalUser=BookIT.Login(Integer.parseInt(ID.getText()),passText);
			
		//	System.out.println(normalUser.getUniversityPosition());
			if (normalUser==null){
				System.out.println("User not found");
			}
			else {
			switch (normalUser.getUniversityPosition()) {
			case "Student" : 
				Welcome.setVisible(false);
				viewRooms.setVisible(true);
				
				break;
			case "TA" : premiuumUser.setUserID(normalUser.getUserID());
			premiuumUser.setEmail(normalUser.getEmail());
			premiuumUser.setUniversityPosition(normalUser.getUniversityPosition());
			premiuumUser.setPassword(normalUser.getPassword());
			normalUser = new User(BookIT);
			Welcome.setVisible(false);
			PremiumUser.setVisible(true);
			
			break;
			
			case "Professor" :
				premiuumUser.setUserID(normalUser.getUserID());
				premiuumUser.setEmail(normalUser.getEmail());
				premiuumUser.setUniversityPosition(normalUser.getUniversityPosition());
				premiuumUser.setPassword(normalUser.getPassword());
				normalUser = new User(BookIT);
				Welcome.setVisible(false);
				PremiumUser.setVisible(true);
				break;
			case "MajorRepresentative" : premiuumUser.setUserID(normalUser.getUserID());
			premiuumUser.setEmail(normalUser.getEmail());
			premiuumUser.setUniversityPosition(normalUser.getUniversityPosition());
			premiuumUser.setPassword(normalUser.getPassword());
			normalUser = new User(BookIT);
			Welcome.setVisible(false);
			PremiumUser.setVisible(true);
			break;
			case "Admin" :
				adminUser.setUserID(normalUser.getUserID());
				adminUser.setEmail(normalUser.getEmail());
				adminUser.setUniversityPosition(normalUser.getUniversityPosition());
				adminUser.setPassword(normalUser.getPassword());
				normalUser = new User(BookIT);
				Welcome.setVisible(false);
				PremiumUser.setVisible(true);
				break;
				
			}
			}
			
				
			
			}
		});
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLogIn.setBounds(325, 278, 150, 38);
		Welcome.add(btnLogIn);
		
		pass = new JPasswordField();
		pass.setBounds(205, 189, 260, 27);
		Welcome.add(pass);
		
		 reg = new JPanel();
		 reg.setVisible(false);
		
		 viewres = new JPanel();
	

		
		 viewres.add(list_2);
			viewres.add(table);
		contentPane.add(viewres, "name_101154418251997");
		viewres.setLayout(null);
		
		////////////////

		
		JLabel label_21 = new JLabel("View/Cancel Reservations Page");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_21.setBounds(112, 29, 374, 51);
		viewres.add(label_21);
		
		
		
		JButton button_8 = new JButton("Delete the selected reservation");
		button_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				premiuumUser.cancelReservation(myress.get(list_2.getSelectedIndex()));
				JOptionPane.showMessageDialog (null, 
                        "Cancellation Complete  ");
				
				
			}
		});
		button_8.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_8.setBounds(71, 386, 304, 42);
		viewres.add(button_8);
		
		JButton btnReservationId = new JButton("Reservation ID");
		btnReservationId.setEnabled(false);
		btnReservationId.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnReservationId.setBounds(28, 69, 105, 31);
		viewres.add(btnReservationId);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewres.setVisible(false);
				PremiumUser.setVisible(true);
				
			}
		});
		btnBack_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack_1.setBounds(404, 392, 105, 31);
		viewres.add(btnBack_1);
		contentPane.add(reg, "name_72537851150483");
		reg.setLayout(null);
		reg.setVisible(false);
		JLabel label_3 = new JLabel("ID");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(91, 86, 79, 24);
		reg.add(label_3);
		
		JLabel label_4 = new JLabel("Email");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(91, 218, 79, 24);
		reg.add(label_4);
		
		JLabel label_5 = new JLabel("University position");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(91, 279, 137, 24);
		reg.add(label_5);
		
		JLabel label_6 = new JLabel("Phone Number");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(91, 347, 137, 24);
		reg.add(label_6);
		
		JLabel label_7 = new JLabel("Password");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_7.setBounds(91, 155, 79, 24);
		reg.add(label_7);
		
		ID2 = new JTextField();
		ID2.setColumns(10);
		ID2.setBounds(241, 88, 241, 24);
		reg.add(ID2);
		
		mail = new JTextField();
		mail.setColumns(10);
		mail.setBounds(241, 220, 241, 24);
		reg.add(mail);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(241, 349, 241, 24);
		reg.add(phone);
		
		pass2 = new JPasswordField();
		pass2.setBounds(241, 155, 241, 24);
		reg.add(pass2);
		
		String[] universityPosition = { "Student", "Professor", "TA", "MajorRepresentative", "Admin" };
		JComboBox comboBox = new JComboBox(universityPosition);
		
		
		comboBox.setBounds(238, 283, 241, 20);
		reg.add(comboBox);
		
		JLabel label_8 = new JLabel("Registration Page");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_8.setBounds(243, 23, 239, 33);
		reg.add(label_8);
		
		JButton btnreg = new JButton("Register");
		btnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			Boolean flg= 	 BookIT.Register(Integer.parseInt(ID2.getText()), comboBox.getSelectedItem().toString(),new String(pass2.getPassword()) , mail.getText(), phone.getText());
			if ( flg == false){
				JOptionPane.showMessageDialog(null, "ID or Email is already registered", "alert", JOptionPane.ERROR_MESSAGE);
			}
			else {
				reg.setVisible(false);
				Welcome.setVisible(true);
				JOptionPane.showMessageDialog (null, 
                        "Registration Complete  ");

			}
			
			
			}
		});
		btnreg.setBounds(205, 404, 166, 24);
		reg.add(btnreg);
		
		 viewRooms = new JPanel();
		 viewRooms.setVisible(false);
		contentPane.add(viewRooms, "name_76890553484023");
		viewRooms.setLayout(null);
		
		JLabel label_9 = new JLabel("Date");
		label_9.setBounds(33, 124, 86, 22);
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 18));
		viewRooms.add(label_9);
		/////////////////////////////////////
		SqlDateModel model = new SqlDateModel();
		//model.setDate(20,04,2014);
		// Need this...
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		// Don't know about the formatter, but there it is...
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());	
		SpringLayout springLayout2 = (SpringLayout) datePicker.getLayout();
		springLayout2.putConstraint(SpringLayout.WEST, datePicker.getJFormattedTextField(), 0, SpringLayout.WEST, datePicker);
		datePicker.setBounds(142, 128, 224, 20);
		viewRooms.add(datePicker);
		
		///////////////////////////////////////////////
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTime.setBounds(33, 235, 86, 22);
		viewRooms.add(lblTime);
		String tiime2 [] = {"08:20:00" , "09:30:00" ,"10:40:00" ,"11:50:00" ,"13:00:00" ,"14:10:00" ,"15:20:00" ,"16:30:00" ,"17:40:00" ,"18:50:00" ,"19:00:00" ,"20:10:00" ,"21:20:00"};
		JComboBox comboBox_2 = new JComboBox(tiime2);
		comboBox_2.setBounds(142, 239, 224, 20);
		viewRooms.add(comboBox_2);
		
		JLabel label_10 = new JLabel("View Rooms Page");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 22));
		label_10.setBounds(142, 29, 218, 45);
		viewRooms.add(label_10);
	
		
		
	
		JList list = new JList();
		
		list.setForeground(Color.BLACK);
		list.setBackground(Color.WHITE);
		list.setBounds(407, 107, 148, 241);
		
		viewRooms.add(list);
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Integer> avrooms ;
				
				if ( normalUser.getUserID() !=0 ){
					System.out.println(comboBox_2.getSelectedItem().toString() + " " + datePicker.getModel().getValue().toString());
					avrooms=normalUser.viewAvailableRooms(comboBox_2.getSelectedItem().toString(), datePicker.getModel().getValue().toString());
					System.out.println(avrooms);
					DefaultListModel model2 = new DefaultListModel();
					for (Integer dum :avrooms){
						model2.addElement(dum);
					}
					list.setModel(model2);
					viewRooms.setVisible(false);
					viewRooms.setVisible(true);
			
				}
				else if(premiuumUser.getUserID() !=0) {
					avrooms=premiuumUser.viewAvailableRooms(comboBox_2.getSelectedItem().toString(), datePicker.getModel().getValue().toString());
				
				}
				else {
					avrooms=adminUser.viewAvailableRooms(comboBox_2.getSelectedItem().toString(), datePicker.getModel().getValue().toString());
				
					
				}
				
	
				
				
			}
		});
		btnSearch.setBounds(142, 325, 89, 23);
		viewRooms.add(btnSearch);
	}
}
