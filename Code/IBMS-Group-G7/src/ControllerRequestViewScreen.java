import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ControllerRequestViewScreen extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable, jLabelSelectedView;
  JPanel contentPanel, mainContentPanel, requestPanel;
  JScrollPane jScrollPanel;
  JMenuBar mainMenuBar;
  JMenu jMenuFile, jMenuView;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetables, jMItemHolidays, jMItemDrivers, jMItemReport, jMItemProblems;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color reqBgClr = new Color(140, 140, 140);
 
  public ControllerRequestViewScreen(String paramString){

    this.setTitle(paramString);
 
    //Create the main menu bar
    mainMenuBar = new JMenuBar();
    mainMenuBar.setBackground(this.layoutBgClr);

    //Create the two menus
    jMenuFile = new JMenu("File");
    jMenuFile.setMnemonic(KeyEvent.VK_F);
    jMenuView = new JMenu("View");
    jMenuView.setMnemonic(KeyEvent.VK_V);

    jLabelSelectedView = new JLabel("Selected View: Requests");
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
    jScrollPanel.setBackground(layoutBgClr);

    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(600, 300));
    contentPanel.setLayout(new BorderLayout());
    contentPanel.setBackground(layoutBgClr);

    JPanel[] reqPanels = new JPanel[10];
    JButton[] aBtns = new JButton[10];
    JButton[] dBtns = new JButton[10];

    //Create the approve buttons 
    for(int i = 0; i < 10; i++){
      aBtns[i] = new JButton("Approve");
      aBtns[i].setForeground(lblFgClr);
      aBtns[i].setBackground(layoutBgClr);
      aBtns[i].setActionCommand("approve" + i);
      aBtns[i].addActionListener(this);
    }
    
    //Create the decline buttons
    for(int i = 0; i < 10; i++){
      dBtns[i] = new JButton("Decline");
      dBtns[i].setForeground(lblFgClr);
      dBtns[i].setBackground(layoutBgClr);
      dBtns[i].setActionCommand("decline" + i);
      dBtns[i].addActionListener(this);
    }

    //Create the requestPanels
    for(int i = 0; i < 10; i++){
      reqPanels[i] = new JPanel();
      reqPanels[i].setLayout(new GridLayout(6, 1));
      reqPanels[i].setBackground(layoutBgClr);

      reqPanels[i].add(new JLabel("Start: 14/7/2013 "));
      reqPanels[i].add(new JLabel("End: 13/8/2013"));
      reqPanels[i].add(new JLabel("DriverID: #213123123"));
      reqPanels[i].add(new JLabel("Status: pending"));
      reqPanels[i].add(aBtns[i]);
      reqPanels[i].add(dBtns[i]);
    }

    //Add the requestPanels to the mainContentPanel
    for(int i = 1; i < 10; i++){
      mainContentPanel.add(reqPanels[i]);
    }

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
      }

  }//actionPerformed
}//class

