import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DriverHolidayViewRScreen extends JFrame 
                                implements ActionListener{

  int availableDays;
  int driverID;

  // Declare the components
  JLabel jLabelTimetable, jLabelSelectedView, jLabelAvailableDays;
  JButton jBtnNRequest, jBtnRequests, jBtnNotifications;
  JPanel contentPanel, submenuPanel, componentsPanel;
  JMenuBar mainMenuBar, secondaryMenuBar;
  JMenu jMenuFile, jMenuView, jMenuNRequest;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetable, jMItemHolidays;

  // Declare the colours
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color btnFgClr = new Color(100, 100, 100);
  Color borderClr = new Color(225,225,225);
  Color lblBgClr = new Color(200,200,200);
 
  public DriverHolidayViewRScreen(String paramString, int requiredDriverID){

    setTitle(paramString);
    
    //Setting values for driverId and availableDays
    driverID = requiredDriverID;
    availableDays = 25 - DriverInfo.getHolidaysTaken(driverID);
 
    //Create the menu bar
    mainMenuBar = new JMenuBar();
    mainMenuBar.setBackground(layoutBgClr);

    //Create the menus
    jMenuFile = new JMenu("File");
    jMenuFile.setMnemonic(70);
 
    jMenuView = new JMenu("View");
    jMenuView.setMnemonic(86);

    jLabelSelectedView = new JLabel("Selected View: Holidays");
    jLabelSelectedView.setForeground(lblFgClr);
    
    //Add the menus to the menu bar
    mainMenuBar.add(jMenuFile);
    mainMenuBar.add(jMenuView);
    mainMenuBar.add(Box.createHorizontalGlue());
    mainMenuBar.add(jLabelSelectedView);
 
    //Create the menu items
    jMItemSave = new JMenuItem("Save");
    jMItemPrint = new JMenuItem("Print");
    jMItemExit = new JMenuItem("Exit", 88);
    jMItemExit.setActionCommand("exit");
    jMItemExit.addActionListener(this);
 
    jMItemTimetable = new JMenuItem("Timetable", 84);
    jMItemTimetable.setActionCommand("timetable");
    jMItemTimetable.addActionListener(this);
 
    jMItemHolidays = new JMenuItem("Holidays", 72);
    jMItemHolidays.setActionCommand("holidays");
    jMItemHolidays.addActionListener(this);
 
    //Add the menu items to the menus
    jMenuFile.add(jMItemSave);
    jMenuFile.add(jMItemPrint);
    jMenuFile.add(jMItemExit);
    jMenuView.add(jMItemTimetable);
    jMenuView.add(jMItemHolidays);
 
    //Create the buttons
    jBtnNRequest = new JButton("New Request");
    jBtnNRequest.setOpaque(true);
    jBtnNRequest.setForeground(btnFgClr);
    jBtnNRequest.setBackground(layoutBgClr);
    jBtnNRequest.setBorderPainted(false);
    jBtnNRequest.setHorizontalAlignment(SwingConstants.CENTER);
    jBtnNRequest.setActionCommand("newRequest");
    jBtnNRequest.addActionListener(this);

    jBtnRequests = new JButton("Requests");
    jBtnRequests.setForeground(btnFgClr);
    jBtnRequests.setBackground(lblBgClr);
    jBtnRequests.setBorderPainted(false);
    jBtnRequests.setHorizontalAlignment(SwingConstants.CENTER);
    jBtnRequests.setActionCommand("requests");
    jBtnRequests.addActionListener(this);

    jBtnNotifications = new JButton("Notifications");
    jBtnNotifications.setForeground(btnFgClr);
    jBtnNotifications.setBackground(layoutBgClr);
    jBtnNotifications.setBorderPainted(false);
    jBtnNotifications.setHorizontalAlignment(SwingConstants.CENTER);
    jBtnNotifications.setActionCommand("notifications");
    jBtnNotifications.addActionListener(this);
    
    jLabelAvailableDays = new JLabel("Available Days: " + 
                                      availableDays);
    jLabelAvailableDays.setForeground(lblFgClr);
    jLabelAvailableDays.setHorizontalAlignment(SwingConstants.CENTER);


    jLabelTimetable = new JLabel("Requests");
    jLabelTimetable.setForeground(lblFgClr);
 
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(600, 300));
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBackground(layoutBgClr);

    //Create the submenuPanel
    submenuPanel = new JPanel();
    submenuPanel.setPreferredSize(new Dimension(600, 23));
    submenuPanel.setLayout(new GridLayout(1,4));
    submenuPanel.setBackground(layoutBgClr);
    submenuPanel.setBorder(BorderFactory.createMatteBorder(0,0,2,0, 
                                         borderClr));

    //Create the components panel
    componentsPanel = new JPanel();
    componentsPanel.setLayout(new GridLayout(6,1));
    componentsPanel.setBackground(layoutBgClr);

    //Add the components to the submenuPanel
    submenuPanel.add(jBtnNRequest);
    submenuPanel.add(jBtnRequests);
    submenuPanel.add(jBtnNotifications);
    submenuPanel.add(jLabelAvailableDays);

    //Add the components to the componentsPanel
    componentsPanel.add(jLabelTimetable);

    //Add the panels to the content panel
    contentPanel.add(submenuPanel, BorderLayout.PAGE_START);
    contentPanel.add(componentsPanel, BorderLayout.CENTER);
 
    //Resize and position the window
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

    int i = getSize().width;
    int j = getSize().height;
    int k = (localDimension.width - i) / 2 - 300;
    int m = (localDimension.height - j) / 2 - 150;
 
    this.setResizable(false);
 
    //Locate the window
    this.setLocation(k, m);
 
    this.setJMenuBar(mainMenuBar);
    this.setContentPane(contentPanel);
    this.pack();
    this.setVisible(true);
 
    //Define the action on closing the window
    addWindowListener(new WindowAdapter(){

      public void windowClosing(WindowEvent paramAnonymousWindowEvent){

         System.exit(0);
      }//windowClosing
    });//addWindowListener
  }//constructor
 
  //Catch the click events
  public void actionPerformed(ActionEvent paramActionEvent){

    String actionCmd = paramActionEvent.getActionCommand();
    String title = "G7 - IBMS System | Driver";

    if ("exit".equals(actionCmd)){
      System.exit(0);
    }
    else if ("timetable".equals(actionCmd)){
      this.dispose();
      new DriverTimetableViewScreen(title, driverID);
    }
    else if ("holidays".equals(actionCmd)){
      this.dispose();
      new DriverHolidayViewNRScreen(title, driverID);
    }
    else if ("newRequest".equals(actionCmd)){
      this.dispose();
      new DriverHolidayViewNRScreen(title, driverID);
    }
    else if ("requests".equals(actionCmd)){
      this.dispose();
      new DriverHolidayViewRScreen(title, driverID);
    }
    else if ("notifications".equals(actionCmd)){
      this.dispose();
      new DriverHolidayViewNScreen(title, driverID);
    }//else if

  }//actionPerformed

}//class

