package chatting.application;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Client implements ActionListener {
    JTextField text1;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    JButton send;

    Client() {
        f.setLayout(null);
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94, 84));
        p1.setBounds(0, 0, 450, 70);
        f.add(p1);
        ImageIcon i1 = new ImageIcon(Server.class.getResource("3.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(3, 20, 30, 25);
        p1.add(back);
        back.addMouseListener((new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        }));
        ImageIcon i4 = new ImageIcon(Server.class.getResource("profile2.jpg"));
        Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(25, 7, 60, 55);
        p1.add(profile);

        ImageIcon i7 = new ImageIcon(Server.class.getResource("video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(335, 20, 30, 35);
        p1.add(video);

        ImageIcon i10 = new ImageIcon(Server.class.getResource("phone.png"));
        Image i11 = i10.getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12);
        phone.setBounds(380, 20, 30, 35);
        p1.add(phone);

        ImageIcon i13 = new ImageIcon(Server.class.getResource("3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel more = new JLabel(i15);
        more.setBounds(425, 22, 10, 25);
        p1.add(more);

        JLabel name = new JLabel("Vaishnavi");
        name.setBounds(81, 14, 200, 25);
        name.setForeground(Color.white);
        name.setFont(new Font("ARIAL", Font.BOLD, 18));
        p1.add(name);

        JLabel status = new JLabel("active now");
        status.setBounds(81, 38, 200, 20);
        status.setForeground(Color.white);
        status.setFont(new Font("ARIAL", Font.BOLD, 13));
        p1.add(status);

        a1 = new JPanel();
        a1.setBounds(5, 75, 445, 570);
        f.add(a1);

        text1 = new JTextField();
        text1.setBounds(5, 610, 310, 40);
        text1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text1);

        send = new JButton("send");
        send.setBounds(320, 610, 123, 40);
        send.setBackground(new Color(7, 93, 82));
        send.setForeground(Color.white);
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        send.setEnabled(false);
        send.addActionListener(this);
        f.add(send);

        f.setSize(450, 650);
        f.setLocation(800, 60);
        f.setUndecorated(true);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(dout==null){
                JOptionPane.showMessageDialog(f,"Not connectd to the server!");
                return;
            } 
            String out = text1.getText();
            JPanel p2 = formatlabel(out);
            a1.setLayout(new BorderLayout());
            JPanel right = new JPanel(new BorderLayout());
            right.add(p2, BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));// distance bet two msgs
            a1.add(vertical, BorderLayout.PAGE_START);
            text1.setText(" ");
            dout.writeUTF(out);
            f.validate();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static JPanel formatlabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width:150px\">" + out + "</p></html>");
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));
        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);
        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel("");
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;
    }

    public static void main(String args[]) {
        Client c = new Client();
        try {           
            Socket skt = new Socket("127.0.0.1",3000);
            DataInputStream din = new DataInputStream(skt.getInputStream());
            dout = new DataOutputStream(skt.getOutputStream());
            c.send.setEnabled(true);

            while (true) {
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatlabel(msg);
                JPanel left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));// distance bet two msgs
                a1.add(vertical, BorderLayout.PAGE_START);
                f.validate();
                }
            }
         catch (Exception e) {
            e.printStackTrace();
        }
    }
}


