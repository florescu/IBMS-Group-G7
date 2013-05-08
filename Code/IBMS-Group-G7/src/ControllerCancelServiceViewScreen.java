
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class ControllerCancelServiceViewScreen extends JFrame
        implements ActionListener {

    //Declare the components
    JLabel jLabelTimetable, jLabelMessage, jLabelSelectedView, jLabelSent, jLabelProblems, jLabelCancel, jLabelResetDelay, jLabelError;
    JPanel contentPanel, mainContentPanel, requestPanel, jButtonsPanel, jMessagePanel, submenuPanel;
    JScrollPane jScrollPanel;
    JMenuBar mainMenuBar;
    JMenu jMenuFile, jMenuView;
    JMenuItem jMItemSave, jMItemPrint, jMItemExit;
    JMenuItem jMItemTimetables, jMItemHolidays, jMItemDrivers, jMItemReport, jMItemProblems;
    JTextArea JTextAreaTimetable;
    JButton jBtnResetDelay, jBtnSetDelay, jBtnResetCancel, jBtnCancel, jBtnCancelView, jBtnSubmit, jBtnReset;
    JTextField jTxtFServiceCancel, jTxtFServiceDelay, jTxtFServiceID, JTxtFReason;
    //Declate the colors
    Color layoutBgClr = new Color(255, 255, 255);
    Color lblFgClr = new Color(150, 150, 150);
    Color reqBgClr = new Color(140, 140, 140);
    Color lblErrorFgClr = new Color(255, 51, 51);
    Color btnBgClr = new Color(245, 245, 245);
    Color btnFgClr = new Color(130, 130, 130);
    Color borderClr = new Color(225, 225, 225);
    Color lblBgClr = new Color(200, 200, 200);
    public static String cancelMessage;

    public ControllerCancelServiceViewScreen(String paramString) {

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
        
        //Create the mainContent panel
        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridLayout(0, 3, 20, 20));
        mainContentPanel.setBackground(layoutBgClr);

        //Create the scrollpanel
        jScrollPanel = new JScrollPane(mainContentPanel);
        jScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPanel.setBackground(layoutBgClr);

        //Create the content panel
        contentPanel = new JPanel();
        contentPanel.setPreferredSize(new Dimension(600, 300));
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(layoutBgClr);

        //Create the text fields
        jTxtFServiceCancel = new JTextField("service id", 7);
        jTxtFServiceCancel.setPreferredSize(new Dimension(30, 30));


        //Create the buttons
        jBtnReset = new JButton("Reset");
        jBtnReset.setOpaque(true);
        jBtnReset.setForeground(btnFgClr);
        jBtnReset.setBackground(layoutBgClr);
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

        jBtnCancelView = new JButton("Cancel Service");
        jBtnCancelView.setOpaque(true);
        jBtnCancelView.setForeground(btnFgClr);
        jBtnCancelView.setBackground(lblBgClr);
        jBtnCancelView.setBorderPainted(false);
        jBtnCancelView.setHorizontalAlignment(SwingConstants.CENTER);
        jBtnCancelView.setActionCommand("cancelServiceView");
        jBtnCancelView.addActionListener(this);


        jBtnCancel = new JButton("Cancel");
        jBtnCancel.setActionCommand("cancelService");
        jBtnCancel.addActionListener(this);
        jBtnCancel.setBackground(this.btnBgClr);
        jBtnCancel.setForeground(this.btnFgClr);

        jBtnSubmit = new JButton("Submit");
        jBtnSubmit.setActionCommand("submitMessage");
        jBtnSubmit.addActionListener(this);
        jBtnSubmit.setBackground(this.btnBgClr);
        jBtnSubmit.setForeground(this.btnFgClr);

        //Create the labels
        jLabelError = new JLabel("Error: Please enter a valid service ID!");
        jLabelError.setForeground(this.lblErrorFgClr);
        jLabelError.setVisible(false);
        jLabelError.setHorizontalAlignment(0);
        jLabelSent = new JLabel("Request sent");
        jLabelSent.setForeground(this.lblErrorFgClr);
        jLabelSent.setVisible(false);
        jLabelSent.setHorizontalAlignment(0);

        //Create the button panel

        jButtonsPanel = new JPanel(new FlowLayout());
        jButtonsPanel.setBackground(this.layoutBgClr);

        jLabelCancel = new JLabel("Reset cancel for");
        jButtonsPanel.add(this.jLabelCancel);
        jButtonsPanel.add(this.jTxtFServiceCancel);
        jButtonsPanel.add(this.jBtnCancel);

        jMessagePanel = new JPanel(new FlowLayout());
        jMessagePanel.setBackground(this.layoutBgClr);

        //The 358 service is delayed by approximately 20 minutes due to a shortage of water for the windscreen wipers1, and will arrive at New Mills bus station at approximately 13.15. We apologize for the delay to your journey.
        jLabelMessage = new JLabel("The service ");
        jMessagePanel.add(this.jLabelMessage);
        jTxtFServiceID = new JTextField("service id", 7);
        jTxtFServiceID.setPreferredSize(new Dimension(30, 30));
        jMessagePanel.add(this.jTxtFServiceID);
        jLabelMessage = new JLabel(" is cancelled due to ");
        jMessagePanel.add(this.jLabelMessage);
        JTxtFReason = new JTextField("message", 20);
        jTxtFServiceID.setPreferredSize(new Dimension(30, 30));
        jMessagePanel.add(this.JTxtFReason);
        jLabelMessage = new JLabel("We apologize for the consequences this may have on your journey.");
        jMessagePanel.add(this.jLabelMessage);
        jMessagePanel.add(this.jBtnSubmit);

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
        submenuPanel.add(jBtnCancelView);


        //Add the label to the content panel
        contentPanel.add(submenuPanel, BorderLayout.PAGE_START);

        //Add the label to the content panel
        //contentPanel.add(jScrollPanel, BorderLayout.CENTER);
        //contentPanel.add(this.jButtonsPanel,BorderLayout.PAGE_START);
        contentPanel.add(this.jMessagePanel);

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
        } else if ("submitMessage".equals(actionCmd)) {
            database.openBusDatabase();
            int serviceID = Integer.parseInt(jTxtFServiceID.getText());

            //Check if the fields are empty or with proper values

            if (jTxtFServiceID.getText() == null
                    || !Validator.isNumeric(jTxtFServiceID.getText())) {

                jLabelError.setText("Error: provide valid service ID!");
                jLabelError.setVisible(true);

            } else if (!Service.isInDatabase(serviceID)) {

                jLabelError.setText("Error: No such service in the database!");
                jLabelError.setVisible(true);
            } else {
                jLabelSent.setText("Request sent.");
                jLabelSent.setVisible(true);
                Service.setCancelled(serviceID, true);
                cancelMessage = "The service " + serviceID + " is cancelled " + " due to " + JTxtFReason.getText() + ". We apologize for the delay to your journey.";
                System.out.println(cancelMessage);
                Service.setMessage(serviceID, cancelMessage);
                this.dispose();
                new ControllerAckScreen("");
            }
        }//else submit message
        else if ("cancelService".equals(actionCmd)) {
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
                Service.setCancelled(serviceID, true);
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

