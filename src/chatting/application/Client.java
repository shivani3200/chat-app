package chatting.application;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.Calendar;
import java.util.Date;
import java.net.*;
import java.io.*;

public class Client  implements ActionListener {

    static JPanel chatPanel;
    private JTextArea chatArea;
    private JTextField writeMessage;
    private JButton send;
    static DataOutputStream dout;

    static Box vertical = Box.createVerticalBox();
    
         static JFrame f = new JFrame();


    Client() {
        f.setLayout(null);

        // Panel at the top
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 400, 70);
        p1.setLayout(null);
        f.add(p1);

        // Back button
        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));// Update the path to your back icon image
        Image backImage = backIcon.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon backButtonIcon = new ImageIcon(backImage);
        JLabel back = new JLabel(backButtonIcon);
        back.setBounds(5, 20, 25, 25);
        p1.add(back);
        
        // ADDING EXIT ACTION
        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });


        // Profile picture, name, and status
        ImageIcon profileIcon = new ImageIcon(ClassLoader.getSystemResource("icons/2.png")); // Update the path to your profile icon image
        Image profileImage = profileIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon profilePicIcon = new ImageIcon(profileImage);
        JLabel profile = new JLabel(profilePicIcon);
        profile.setBounds(40, 10, 50, 50);
        p1.add(profile);
//ADDING NAME OF SENDER
        JLabel name = new JLabel("amit");
        name.setBounds(110, 18, 100, 20);
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Segoe UI", Font.BOLD, 18));
        p1.add(name);
//ADDING ACTIVE NOW STATUS
        JLabel status = new JLabel("Active now");
        status.setBounds(110, 45, 100, 10);
        status.setForeground(Color.WHITE);
        status.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        p1.add(status);
// ADDING VIDEO ICON
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i8 = i7.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(275, 20, 25, 25);
        p1.add(video);
// ADDING CALL ICON
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i11 = i10.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(325, 20, 25, 25);
        p1.add(phone);
 // ADDING 3dot ICON
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel dot = new JLabel(i15);
        dot.setBounds(375, 20, 10, 25);
        p1.add(dot);

        // Chat area
        chatPanel = new JPanel();
        chatPanel.setBounds(5, 75, 390, 450);
        chatPanel.setLayout(new BorderLayout());

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(new Color(235, 255, 255));
        chatArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

//        // Add the chat area to a scroll pane to make it scrollable
//        JScrollPane scrollPane = new JScrollPane(chatArea);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//        chatPanel.add(scrollPane, BorderLayout.CENTER);

        f.add(chatPanel);

        // Input field with rounded border
        writeMessage = new JTextField();
        writeMessage.setBounds(5, 535, 280, 40);
        writeMessage.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        writeMessage.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(7, 94, 84), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        f.add(writeMessage);

        // Send button
        send = new JButton("Send");
        send.setBounds(290, 535, 105, 40);
        send.setOpaque(true);
        send.setBorderPainted(false);
        send.setBackground(new Color(7, 94, 84));
        send.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);
        f.add(send);

        // Set the frame properties
        f.setSize(400, 650);
        f.setLocation(800, 70);
        f.getContentPane().setBackground(new Color(240, 240, 240)); // Set a solid color background
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    



public void actionPerformed(ActionEvent ae) {
    try{
        String out = writeMessage.getText();

        JPanel p2 = formatLabel( out);//output is panel

        JPanel LeftSide = new JPanel(new BorderLayout());
        LeftSide.add(p2, BorderLayout.LINE_END);//end of line se shuru hoga
        //ek k baad ek add hoga phir
        vertical.add(LeftSide);
        vertical.add(Box.createVerticalStrut(15));

        chatPanel.add(vertical, BorderLayout.PAGE_START);

        dout.writeUTF(out);
        //empty the write area
        writeMessage.setText("");

        //we have to repaint the panel to append messages
        f.repaint();
        f.invalidate();
        f.validate();
}catch(Exception e){
    e.printStackTrace();
    }
      
}

//FORMATTING THE MESSAGE BOX
    public static JPanel formatLabel(String out){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel output = new JLabel("<html><p style=\"width: 150px\">"+out+"</p></html");
        output.setFont(new Font("tahoma", Font.PLAIN,14));
        output.setBackground(new Color(	159,197,232));
        output.setForeground(Color.BLACK);
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15,15,15,20));
        
        panel.add(output);
         
// Get the current date and time
    Calendar cal = Calendar.getInstance();
    Date date = cal.getTime();

    // Format the date to display the timestamp in "hh:mm aa" format (hours:minutes AM/PM)
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
    String timestamp = sdf.format(date);

    // Create a label to display the timestamp
    JLabel timeLabel = new JLabel(timestamp);
    timeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
    timeLabel.setForeground(Color.GRAY);
    panel.add(timeLabel);
        
        return panel;
        
    }

    public static void main(String[] args) {
        new Client();
        
        try{
            Socket s = new Socket("127.0.0.1", 6001);
            
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
                     
            while(true){
                
                    chatPanel.setLayout(new BorderLayout());
                     String message = din.readUTF();
                     JPanel panel = formatLabel(message) ;
                     
                     JPanel leftSide = new JPanel(new BorderLayout());
                     leftSide.add(panel,BorderLayout.LINE_START);
                     
                     vertical.add(leftSide);
                     vertical.add(Box.createVerticalStrut(15));
                     f.validate();
                     
                    }
            
            
        
        }catch(Exception e){
        e.printStackTrace();
        }
    }
}
