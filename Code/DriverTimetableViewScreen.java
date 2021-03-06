import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DriverTimetableViewScreen extends JFrame 
                                implements ActionListener{
                                
  //Declare the driverID
  int driverID;

  //Declare the components 
  JLabel jLabelTimetable, jLabelSelectedView;
  JPanel contentPanel;
  JMenuBar mainMenuBar;
  JMenu jMenuFile, jMenuView;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetable, jMItemHolidays;

  //Declare the colours
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
 
  public DriverTimetableViewScreen(String paramString, int requiredDriverID){

    setTitle(paramString);
    
    driverID = requiredDriverID;
 
    //Create the menu bar
    mainMenuBar = new JMenuBar();
    mainMenuBar.setBackground(this.layoutBgClr);

    //Create the menus
    jMenuFile = new JMenu("File");
    jMenuFile.setMnemonic(70);
 
    jMenuView = new JMenu("View");
    jMenuView.setMnemonic(86);

    jLabelSelectedView = new JLabel("Selected View: Timetable");
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
 
    //Add the menus to the menu bar
    jMenuFile.add(this.jMItemSave);
    jMenuFile.add(this.jMItemPrint);
    jMenuFile.add(this.jMItemExit);
    jMenuView.add(this.jMItemTimetable);
    jMenuView.add(this.jMItemHolidays);
 
    jLabelTimetable = new JLabel("Timetable");
    jLabelTimetable.setForeground(this.lblFgClr);
 
    //Create the contentPanel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(600, 300));
    contentPanel.setLayout(new GridLayout(1, 1));
    contentPanel.setBackground(this.layoutBgClr);

    contentPanel.add(this.jLabelTimetable);
 
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
    String title = "G7 - IBMS System | Driver";

    if ("exit".equals(actionCmd))
      System.exit(0);
    else if ("timetable".equals(actionCmd)){
      this.dispose();
      new DriverTimetableViewScreen(title, driverID);
    }
    else if ("holidays".equals(actionCmd)){
      this.dispose();
      new DriverHolidayViewNRScreen(title, driverID);
    }

  }//actionPerformed

}//class

