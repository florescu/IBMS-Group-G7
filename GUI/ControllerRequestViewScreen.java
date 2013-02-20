import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ControllerRequestViewScreen extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable;
  JPanel contentPanel;
  JMenuBar mainMenuBar;
  JMenu jMenuFile, jMenuView;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetables, jMItemHolidays, jMItemDrivers;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
 
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

    //Add the two menus to the menu bar
    mainMenuBar.add(jMenuFile);
    mainMenuBar.add(jMenuView);

    //Create the menu items
    jMItemSave = new JMenuItem("Save");
    jMItemPrint = new JMenuItem("Print");
    jMItemExit = new JMenuItem("Exit", KeyEvent.VK_X);
    jMItemExit.setActionCommand("exit");
    jMItemExit.addActionListener(this);
 
    jMItemTimetables = new JMenuItem("Timetables", KeyEvent.VK_T);
    jMItemTimetables.setActionCommand("timetables");
    jMItemTimetables.addActionListener(this);
 
    jMItemHolidays = new JMenuItem("Holidays", KeyEvent.VK_H);
    jMItemHolidays.setActionCommand("holidays");
    jMItemHolidays.addActionListener(this);
 
    jMItemDrivers = new JMenuItem("Drivers", KeyEvent.VK_D);
    jMItemDrivers.setActionCommand("drivers");
    jMItemDrivers.addActionListener(this);
 
    //Add the items to the menus
    jMenuFile.add(this.jMItemSave);
    jMenuFile.add(this.jMItemPrint);
    jMenuFile.add(this.jMItemExit);
 
    jMenuView.add(this.jMItemTimetables);
    jMenuView.add(this.jMItemHolidays);
    jMenuView.add(this.jMItemDrivers);

    //Create the label
    jLabelTimetable = new JLabel("Controller Request label");
    jLabelTimetable.setForeground(this.lblFgClr);
 
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(600, 300));
    contentPanel.setLayout(new GridLayout(1, 1));
    contentPanel.setBackground(this.layoutBgClr);

    //Add the label to the content panel
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
    String title = "G7 - IBMS System | Controller";

    if("exit".equals(actionCmd))
      System.exit(0);
    else if ("timetables".equals(actionCmd)){
      System.out.println("Timetables menu selected");
    }
    else if ("holidays".equals(actionCmd)){
      this.dispose();
      new ControllerRequestViewScreen(title);
    }
    else if ("drivers".equals(actionCmd)){
      System.out.println("Drivers menu selected");
    }
  }//actionPerformed
}//class

