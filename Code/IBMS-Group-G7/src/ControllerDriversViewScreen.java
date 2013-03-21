import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ControllerDriversViewScreen extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable, jLabelSelectedView;
  JPanel contentPanel, mainContentPanel, requestPanel;
  JScrollPane jScrollPanel;
  JMenuBar mainMenuBar;
  JMenu jMenuFile, jMenuView;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetables, jMItemReport, jMItemDrivers;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color reqBgClr = new Color(140, 140, 140);
 
  public ControllerDriversViewScreen(String paramString){

    this.setTitle(paramString);
 
    //Create the main menu bar
    mainMenuBar = new JMenuBar();
    mainMenuBar.setBackground(this.layoutBgClr);

    //Create the two menus
    jMenuFile = new JMenu("File");
    jMenuFile.setMnemonic(KeyEvent.VK_F);
    jMenuView = new JMenu("View");
    jMenuView.setMnemonic(KeyEvent.VK_V);

    jLabelSelectedView = new JLabel("Selected View: Drivers");
    jLabelSelectedView.setForeground(lblFgClr);

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
 
    //Add the items to the menus
    jMenuFile.add(this.jMItemSave);
    jMenuFile.add(this.jMItemPrint);
    jMenuFile.add(this.jMItemExit);
 
    jMenuView.add(this.jMItemTimetables);
    jMenuView.add(this.jMItemReport);
    jMenuView.add(this.jMItemDrivers);

    //Create the mainContent panel
    mainContentPanel = new JPanel();
    mainContentPanel.setLayout(new GridLayout(0, 3, 20, 20));
    mainContentPanel.setBackground(layoutBgClr);
 
    //Create the scrollpanel
    jScrollPanel = new JScrollPane(mainContentPanel);
    jScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    jScrollPanel.setBackground(layoutBgClr);

    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(600, 300));
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBackground(layoutBgClr);

    
    //Create the layouts for each driver

    //Get all the ids
    database.openBusDatabase();
    int[] driversIDs = DriverInfo.getDrivers();

    for(int i = 0; i< driversIDs.length; i++){
      System.out.println("Driver: " + driversIDs[i]);
    }

    
    //Get all the names
    String[] driversNames = new String[driversIDs.length];

    for(int i = 0; i < driversNames.length; i++){
      driversNames[i]=DriverInfo.getName(driversIDs[i]);
    }//for


    JPanel[] driverDataPanels = new JPanel[driversIDs.length];
    JButton[] vReportBtns = new JButton[driversIDs.length];

    //Create the View Report Buttons
    for(int i = 0; i < driversIDs.length; i++){
      vReportBtns[i] = new JButton("View Report");
      vReportBtns[i].setForeground(lblFgClr);
      vReportBtns[i].setBackground(layoutBgClr);
      vReportBtns[i].setActionCommand("vreport" + i);
      vReportBtns[i].addActionListener(this);
    }//for

    //Create the drivers dataPanel
    for(int i = 0; i < driversIDs.length; i++){
      driverDataPanels[i] = new JPanel();
      driverDataPanels[i].setLayout(new GridLayout(3, 1));
      driverDataPanels[i].setBackground(layoutBgClr);

      driverDataPanels[i].add(new JLabel("Name: " + driversNames[i]));
      driverDataPanels[i].add(new JLabel("Id: " + driversIDs[i]));
      driverDataPanels[i].add(vReportBtns[i]);
    }//for


    // Add the driver panels to the main content panel
    for(int i = 0; i < driversIDs.length; i++){
      mainContentPanel.add(driverDataPanels[i]);
    }//for


    //Add the label to the content panel
    contentPanel.add(jScrollPanel, BorderLayout.CENTER);

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
    addWindowListener(new WindowAdapter(){

      public void windowClosing(WindowEvent paramAnonymousWindowEvent){

        System.exit(0);
      }//windowClosing
    });//addWindowListener

  }//constructor

  //Catch the click events
  public void actionPerformed(ActionEvent paramActionEvent){

    String actionCmd = paramActionEvent.getActionCommand();
    String title = "G7 - IBMS System | Controller";

    if("exit".equals(actionCmd))
      System.exit(0);
    else if ("timetables".equals(actionCmd)){
      this.dispose();
      new ControllerTimetableViewScreen(title);
    }
    else if ("report".equals(actionCmd)){
      this.dispose();
      new ControllerReportViewScreen(title);
    }
    else if ("drivers".equals(actionCmd)){
      this.dispose();
      new ControllerDriversViewScreen(title);
    }

  }//actionPerformed
}//class

