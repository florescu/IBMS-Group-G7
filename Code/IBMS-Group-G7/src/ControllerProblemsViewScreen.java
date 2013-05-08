
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class ControllerProblemsViewScreen extends JFrame
        implements ActionListener {

    //Declare the components
    JLabel jLabelTimetable, jLabelSelectedView, jLabelSent, jLabelProblems, jLabelResetCancel, jLabelResetDelay, jLabelError;
    JPanel contentPanel, mainContentPanel, requestPanel, JComponentsPanel, submenuPanel;
    JScrollPane jScrollPanel;
    JMenuBar mainMenuBar, secondaryMenuBar;
    JMenu jMenuFile, jMenuView;
    JMenuItem jMItemSave, jMItemPrint, jMItemExit;
    JMenuItem jMItemTimetables, jMItemHolidays, jMItemDrivers, jMItemReport, jMItemProblems;
    JTextArea JTextAreaTimetable;
    JButton jBtnResetDelay, jBtnSetDelay, jBtnResetCancel, jBtnCancel, jBtnReset;
    JTextField jTxtFServiceCancel, jTxtFServiceDelay;
    //Declate the colors
    Color layoutBgClr = new Color(255, 255, 255);
    Color lblFgClr = new Color(150, 150, 150);
    Color reqBgClr = new Color(140, 140, 140);
    Color lblErrorFgClr = new Color(255, 51, 51);
    Color btnBgClr = new Color(245, 245, 245);
    Color btnFgClr = new Color(130, 130, 130);
    Color borderClr = new Color(225, 225, 225);
    Color lblBgClr = new Color(200, 200, 200);
    Color lblSentFgClr = new Color(26, 127, 26);

    public ControllerProblemsViewScreen(String paramString) {

        this.setTitle(paramString);

        //Create the main menu bar
        mainMenuBar = new JMenuBar();
        mainMenuBar.setBackground(this.layoutBgClr);

        //Create the two menus
        jMenuFile = new JMenu("File");
        jMenuFile.setMnemonic(KeyEvent.VK_F);
        jMenuView = new JMenu("View");
        jMenuView.setMnemonic(KeyEvent.VK_V);

        jLabelSelectedView = new JLabel("Selected View: Problems");
        jLabelSelectedView.setForeground(lblFgClr);

        //Problems label
        jLabelProblems = new JLabel("Problems layout");
        jLabelProblems.setForeground(lblFgClr);
        jLabelProblems.setBackground(layoutBgClr);
        jLabelProblems.setHorizontalAlignment(SwingConstants.LEFT);

        //Add the two menus to the menu bar
        mainMenuBar.add(jMenuFile);
        mainMenuBar.add(jMenuView);
        mainMenuBar.add(Box.createHorizontalGlue());
        mainMenuBar.add(jLabelSelectedView);

        //Create the menu items
        jMItemSave = new JMenuItem("Save");
        jMItemPrint = new JMenuItem("Print");
        jMItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
        jMItemExit.setActionCommand("exit");
        jMItemExit.addActionListener(this);

        jMItemTimetables = new JMenuItem("Timetables", KeyEvent.VK_T);
        jMItemTimetables.setActionCommand("timetables");
        jMItemTimetables.addActionListener(this);

        jMItemReport = new JMenuItem("Report", KeyEvent.VK_R);
        jMItemReport.setActionCommand("report");
        jMItemReport.addActionListener(this);

        jMItemHolidays = new JMenuItem("Requests", KeyEvent.VK_H);
        jMItemHolidays.setActionCommand("holidays");
        jMItemHolidays.addActionListener(this);

        jMItemDrivers = new JMenuItem("Drivers", KeyEvent.VK_D);
        jMItemDrivers.setActionCommand("drivers");
        jMItemDrivers.addActionListener(this);

        jMItemProblems = new JMenuItem("Problems", KeyEvent.VK_P);
        jMItemProblems.setActionCommand("problems");
        jMItemProblems.addActionListener(this);

        //Add the items to the menus
        jMenuFile.add(this.jMItemSave);
        jMenuFile.add(this.jMItemPrint);
        jMenuFile.add(this.jMItemExit);

        jMenuView.add(this.jMItemTimetables);
        jMenuView.add(this.jMItemReport);
        jMenuView.add(this.jMItemHolidays);
        jMenuView.add(this.jMItemDrivers);
        jMenuView.add(this.jMItemProblems);

        //Create the buttons
        jBtnReset = new JButton("Reset");
        jBtnReset.setOpaque(true);
        jBtnReset.setForeground(btnFgClr);
        jBtnReset.setBackground(lblBgClr);
        jBtnReset.setBorderPainted(false);
        jBtnReset.setHorizontalAlignment(SwingConstants.CENTER);
        jBtnReset.setActionCommand("resetView");
        jBtnReset.addActionListener(this);

        jBtnSetDelay = new JButton("Set Delay");
        jBtnSetDelay.setOpaque(true);
        jBtnSetDelay.setForeground(btnFgClr);
        jBtnSetDelay.setBackground(layoutBgClr);
        jBtnSetDelay.setBorderPainted(false);
        jBtnSetDelay.setHorizontalAlignment(SwingConstants.CENTER);
        jBtnSetDelay.setActionCommand("setDelayView");
        jBtnSetDelay.addActionListener(this);


        jBtnCancel = new JButton("Cancel Service");
        jBtnCancel.setOpaque(true);
        jBtnCancel.setForeground(btnFgClr);
        jBtnCancel.setBackground(layoutBgClr);
        jBtnCancel.setBorderPainted(false);
        jBtnCancel.setHorizontalAlignment(SwingConstants.CENTER);
        jBtnCancel.setActionCommand("cancelServiceView");
        jBtnCancel.addActionListener(this);


        //Create the mainContent panel
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayout(0, 3, 20, 20));
        mainContentPanel.setBackground(layoutBgClr);

        //Create the content panel
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(600, 300));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(layoutBgClr);

        //Create the text fields
        jTxtFServiceCancel = new JTextField("service id", 7);
        jTxtFServiceCancel.setPreferredSize(new Dimension(30, 30));

        jTxtFServiceDelay = new JTextField("service id", 7);
        jTxtFServiceDelay.setPreferredSize(new Dimension(30, 30));

        //Create the buttons
        jBtnResetDelay = new JButton("Reset Delay");
        jBtnResetDelay.setActionCommand("resetDelay");
        jBtnResetDelay.addActionListener(this);
        jBtnResetDelay.setBackground(this.btnBgClr);
        jBtnResetDelay.setForeground(this.btnFgClr);

        jBtnResetCancel = new JButton("Reset Cancel");
        jBtnResetCancel.setActionCommand("resetCancel");
        jBtnResetCancel.addActionListener(this);
        jBtnResetCancel.setBackground(this.btnBgClr);
        jBtnResetCancel.setForeground(this.btnFgClr);

        //Create the labels
        jLabelError = new JLabel("Error: Please enter a valid service ID!");
        jLabelError.setForeground(this.lblErrorFgClr);
        jLabelError.setVisible(false);
        jLabelError.setHorizontalAlignment(0);
        jLabelSent = new JLabel("Request sent");
        jLabelError.setForeground(this.lblErrorFgClr);
        jLabelError.setVisible(false);
        jLabelError.setHorizontalAlignment(0);

        //Create the button panel

        JComponentsPanel = new JPanel(new GridLayout(4, 3, 10, 50));
        JComponentsPanel.setBackground(this.layoutBgClr);

        JComponentsPanel.add(new JLabel(""));
        JComponentsPanel.add(this.jBtnSetDelay);
        JComponentsPanel.add(new JLabel(""));

        jLabelResetDelay = new JLabel("Reset delay for");
        JComponentsPanel.add(this.jLabelResetDelay);
        JComponentsPanel.add(this.jTxtFServiceDelay);
        JComponentsPanel.add(this.jBtnResetDelay);

        JComponentsPanel.add(new JLabel(""));
        JComponentsPanel.add(this.jBtnCancel);
        JComponentsPanel.add(new JLabel(""));

        jLabelResetCancel = new JLabel("Reset cancel for");
        JComponentsPanel.add(this.jLabelResetCancel);
        JComponentsPanel.add(this.jTxtFServiceCancel);
        JComponentsPanel.add(this.jBtnResetCancel);

        //Create the submenuPanel
        submenuPanel = new JPanel();
        submenuPanel.setPreferredSize(new Dimension(600, 23));
        submenuPanel.setLayout(new GridLayout(1, 4));
        submenuPanel.setBackground(layoutBgClr);
        submenuPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0,
                borderClr));

        //Add the components to the submenuPanel

        submenuPanel.add(jBtnReset);
        submenuPanel.add(jBtnSetDelay);
        submenuPanel.add(jBtnCancel);


        //Add the label to the content panel
        contentPanel.add(submenuPanel, BorderLayout.PAGE_START);
        contentPanel.add(componentsPanel);

        //Resize and position the window
        Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

        int i = getSize().width;
        int j = getSize().height;
        int k = (localDimension.width - i) / 2 - 300;
        int m = (localDimension.height - j) / 2 - 150;

        this.setResizable(false);

        //Locate the window
        this.setLocation(k, m);

        this.setJMenuBar(this.mainMenuBar);
        this.setContentPane(this.contentPanel);
        this.pack();
        this.setVisible(true);

        //Define the action on closing the window
        addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent paramAnonymousWindowEvent) {

                System.exit(0);
            }//windowClosing
        });//addWindowListener

    }//constructor

    //Catch the click events
    public void actionPerformed(ActionEvent paramActionEvent) {

        String actionCmd = paramActionEvent.getActionCommand();
        String title = "G7 - IBMS System | Controller";


        if ("exit".equals(actionCmd)) {
            System.exit(0);
        } else if ("timetables".equals(actionCmd)) {
            this.dispose();
            new ControllerTimetableViewScreen(title);
        } else if ("report".equals(actionCmd)) {
            this.dispose();
            new ControllerReportViewScreen(title);
        } else if ("holidays".equals(actionCmd)) {
            this.dispose();
            new ControllerRequestViewScreen(title);
        } else if ("drivers".equals(actionCmd)) {
            this.dispose();
            new ControllerDriversViewScreen(title);
        } else if ("problems".equals(actionCmd)) {
            this.dispose();
            new ControllerProblemsViewScreen(title);
        } else if ("resetDelay".equals(actionCmd)) {
            database.openBusDatabase();
            int serviceID = Integer.parseInt(jTxtFServiceDelay.getText());

            //Check if the fields are empty or with proper values

            if (jTxtFServiceDelay.getText() == null
                    || !Validator.isNumeric(jTxtFServiceDelay.getText())) {

                jLabelError.setText("Error: provide valid service ID!");
                jLabelError.setVisible(true);

            } else if (!Service.isInDatabase(serviceID)) {

                jLabelError.setText("Error: No such service in the database!");
                jLabelError.setVisible(true);
            } else {
                jLabelSent.setText("Request sent.");
                jLabelSent.setVisible(true);
                int delayedTime = Service.getDelayedTime(serviceID);
                delayedTime = 0 - delayedTime;
                Service.setDelayedTime(serviceID, 0);
                Service.setMessage(serviceID, "");
                TimetableInfo.setTimeForService(serviceID, delayedTime);
                this.dispose();
                new ControllerAckScreen("");
            }//else
        }//else if reset Delay
        else if ("resetCancel".equals(actionCmd)) {
            database.openBusDatabase();
            int serviceID = Integer.parseInt(jTxtFServiceCancel.getText());

            //Check if the fields are empty or with proper values

            if (jTxtFServiceCancel.getText() == null
                    || !Validator.isNumeric(jTxtFServiceCancel.getText())) {

                jLabelError.setText("Error: provide valid service ID!");
                jLabelError.setVisible(true);

            } else if (!Service.isInDatabase(serviceID)) {

                jLabelError.setText("Error: No such service in the database!");
                jLabelError.setVisible(true);
            } else {
                jLabelSent.setText("Request sent.");
                jLabelSent.setVisible(true);
                Service.setCancelled(serviceID, false);
                Service.setMessage(serviceID, "");
                this.dispose();
                new ControllerAckScreen("");
            }//else
        }//else if reset cancel
        else if ("setDelayView".equals(actionCmd)) {
            this.dispose();
            new ControllerSetDelayViewScreen(title);
        } else if ("cancelServiceView".equals(actionCmd)) {
            this.dispose();
            new ControllerCancelServiceViewScreen(title);
        } else if ("resetView".equals(actionCmd)) {
            this.dispose();
            new ControllerProblemsViewScreen(title);
        }
    }//actionPerformed
}//class

