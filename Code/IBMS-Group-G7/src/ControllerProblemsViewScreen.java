
import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class ControllerProblemsViewScreen extends JFrame
        implements ActionListener {

    //Declare the components
    JLabel jLabelTimetable, jLabelSelectedView, jLabelSent, jLabelProblems, jLabelResetCancel, jLabelResetDelay, jLabelError;
    JPanel contentPanel, mainContentPanel, requestPanel, JComponentsPanel, submenuPanel, JResetCancelPanel, JResetDelayPanel;
    JScrollPane jScrollPanel;
    JMenuBar mainMenuBar, secondaryMenuBar;
    JMenu jMenuFile, jMenuView;
    JMenuItem jMItemSave, jMItemPrint, jMItemExit;
    JMenuItem jMItemTimetables, jMItemDrivers, jMItemReport, jMItemProblems;
    JTextArea JTextAreaTimetable;
    JButton jBtnResetDelay, jBtnSetDelay, jBtnResetCancel, jBtnCancel, jBtnReset;
    JComboBox jCBoxServiceCancel, jCBoxServiceDelay;
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


        database.openBusDatabase();
        int servicesInt[] = Service.getServices();
        Integer services[] = new Integer[servicesInt.length];

        for (int i = 0; i < services.length; i++) {
            services[i] = Integer.valueOf(servicesInt[i]);
        }


        //Create the text fields
        jCBoxServiceCancel = new JComboBox(services);
        jCBoxServiceCancel.setEditable(false);


        jCBoxServiceDelay = new JComboBox(services);
        jCBoxServiceDelay.setEditable(false);

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

        JComponentsPanel = new JPanel(new GridLayout(2, 1));
        JComponentsPanel.setBackground(this.layoutBgClr);

        //Create the JResetDelay panel
        JResetDelayPanel = new JPanel();
        JResetDelayPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 30));
        JResetDelayPanel.setBackground(layoutBgClr);

        //Create the JRestCancel panel
        JResetCancelPanel = new JPanel();
        JResetCancelPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 30));
        JResetCancelPanel.setBackground(layoutBgClr);

        jLabelResetDelay = new JLabel("Reset delay for");
        JResetDelayPanel.add(this.jLabelResetDelay);
        JResetDelayPanel.add(this.jCBoxServiceDelay);
        JResetDelayPanel.add(this.jBtnResetDelay);

        jLabelResetCancel = new JLabel("Reset cancel for");
        JResetCancelPanel.add(this.jLabelResetCancel);
        JResetCancelPanel.add(this.jCBoxServiceCancel);
        JResetCancelPanel.add(this.jBtnResetCancel);

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

        JComponentsPanel.add(JResetDelayPanel);
        JComponentsPanel.add(JResetCancelPanel);

        //Add the label to the content panel
        contentPanel.add(submenuPanel, BorderLayout.PAGE_START);
        contentPanel.add(JComponentsPanel);

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
        } else if ("drivers".equals(actionCmd)) {
            this.dispose();
            new ControllerDriversViewScreen(title);
        } else if ("problems".equals(actionCmd)) {
            this.dispose();
            new ControllerProblemsViewScreen(title);
        } else if ("resetDelay".equals(actionCmd)) {
            database.openBusDatabase();
            int serviceID = (Integer) jCBoxServiceDelay.getSelectedItem();

            //Check if the fields are empty or with proper values

            jLabelSent.setText("Request sent.");
            jLabelSent.setVisible(true);
            int delayedTime = Service.getDelayedTime(serviceID);
            delayedTime = 0 - delayedTime;
            Service.setDelayedTime(serviceID, 0);
            Service.setMessage(serviceID, "");
            TimetableInfo.setTimeForService(serviceID, delayedTime);
            this.dispose();
            new ControllerAckScreen("");
        }//else if reset Delay
        else if ("resetCancel".equals(actionCmd)) {
            database.openBusDatabase();
            int serviceID = (Integer) jCBoxServiceCancel.getSelectedItem();

            //Check if the fields are empty or with proper values


            jLabelSent.setText("Request sent.");
            jLabelSent.setVisible(true);
            Service.setCancelled(serviceID, false);
            Service.setMessage(serviceID, "");
            this.dispose();
            new ControllerAckScreen("");

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

