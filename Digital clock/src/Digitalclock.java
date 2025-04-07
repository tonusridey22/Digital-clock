import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.*;

public class Digitalclock extends JFrame {
    JLabel timeLabel, dateLabel;
    JButton bangladesh12Button, bangladesh24Button, canadaButton, usaButton, ukButton, australiaButton;

    boolean is24HourFormat = false;
    String currentTimeZone = "Asia/Dhaka";
    Timer timer;

    public Digitalclock() {
        setTitle("Digital Clock");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        getContentPane().setBackground(new Color(50, 50, 50));

        timeLabel = new JLabel("Click a button to show time", JLabel.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 25));
        timeLabel.setForeground(Color.GREEN);

        dateLabel = new JLabel("", JLabel.CENTER);
        dateLabel.setFont(new Font("Arial", Font.BOLD, 24));
        dateLabel.setForeground(Color.RED);

        JPanel centerPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(timeLabel);
        centerPanel.add(dateLabel);
        centerPanel.setBackground(new Color(50, 50, 50));
        add(centerPanel, BorderLayout.CENTER);

        
        bangladesh12Button = new JButton("Bangladesh 12 Hour");
        bangladesh24Button = new JButton("Bangladesh 24 Hour");
        canadaButton = new JButton("Canada");
        usaButton = new JButton("USA");
        ukButton = new JButton("UK");
        australiaButton = new JButton("Australia");

        
        JButton[] buttons = { bangladesh12Button, bangladesh24Button, canadaButton, usaButton, ukButton, australiaButton };
        for (JButton btn : buttons) {
            btn.setBackground(new Color(80,80,80));
            btn.setForeground(Color.WHITE);
            btn.setFont(new Font("Arial", Font.BOLD, 15));
        }

        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3));
        buttonPanel.setBackground(new Color(70, 70,70));
        buttonPanel.add(bangladesh12Button);
        buttonPanel.add(bangladesh24Button);
        buttonPanel.add(canadaButton);
        buttonPanel.add(usaButton);
        buttonPanel.add(ukButton);
        buttonPanel.add(australiaButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // ActionListeners
        bangladesh12Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "Asia/Dhaka";
                is24HourFormat = false;
                updateClock();
            }
        });

        bangladesh24Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "Asia/Dhaka";
                is24HourFormat = true;
                updateClock();
            }
        });

        canadaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "America/Toronto";
                is24HourFormat = false;
                updateClock();
            }
        });

        usaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "America/New_York";
                is24HourFormat = false;
                updateClock();
            }
        });

        ukButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "Europe/London";
                is24HourFormat = false;
                updateClock();
            }
        });

        australiaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeZone = "Australia/Sydney";
                is24HourFormat = false;
                updateClock();
            }
        });

      
    }

    private void updateClock() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(currentTimeZone));

        String timePattern = is24HourFormat ? "HH:mm:ss" : "hh:mm:ss a";
        String datePattern = "dd MMM yyyy, EEE";

        SimpleDateFormat timeFormatter = new SimpleDateFormat(timePattern);
        timeFormatter.setTimeZone(calendar.getTimeZone());

        SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);
        dateFormatter.setTimeZone(calendar.getTimeZone());

        timeLabel.setText("Time: " + timeFormatter.format(calendar.getTime()));
        dateLabel.setText("Date: " + dateFormatter.format(calendar.getTime()));
        // Timer to update every second
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateClock();
            }
        });
        timer.start();
    }

    public static void main(String[] args) {
        Digitalclock clock = new Digitalclock();
        clock.setVisible(true);
    }
}
