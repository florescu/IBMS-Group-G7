import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class ControllerAckScreen extends JFrame implements ActionListener{

	int driverID;


  // Declare the components
  JLabel jLabelSelectedView, jLabelAvailableDays;
  JLabel jLabelError, jLabelSent; 
  JPanel  submenuPanel, componentsPanel, mainContentPanel;
  JMenuBar mainMenuBar, secondaryMenuBar;
  JMenu jMenuFile, jMenuView, jMenuNRequest;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemTimetable, jMItemHolidays;
  JLabel jLabel;
  JButton jBtnContinue;
  JPanel contentPanel, jTextFieldPanelId,jTextFieldPanelName, jButtonsPanel;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color lblErrorFgClr = new Color(255, 51, 51);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public ControllerAckScreen(){

    this.setTitle("Acknowledge");
    
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
 
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(300, 150));
    contentPanel.setLayout(new GridLayout(2, 1));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the label
    jLabel = new JLabel("Your request has been processed");
    jLabel.setForeground(this.lblFgClr);

    //Align the label
    jLabel.setHorizontalAlignment(0);
    
    //Create the button
    jBtnContinue = new JButton("Continue");
    jBtnContinue.setActionCommand("continue");
    jBtnContinue.addActionListener(this);
    jBtnContinue.setBackground(this.btnBgClr);
    jBtnContinue.setForeground(this.btnFgClr);
    
    //Create the button panel
    jButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    jButtonsPanel.setBackground(this.layoutBgClr); 
    jButtonsPanel.add(this.jBtnContinue);
 
    //Add the components to the content panel
    contentPanel.add(this.jLabel);
    contentPanel.add(this.jButtonsPanel);
 
    //Resize and position the window
    Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();
 
    int i = getSize().width;
    int j = getSize().height;
    int k = (localDimension.width - i) / 2 - 180;
    int m = (localDimension.height - j) / 2 - 100;

    setResizable(false);
 
    //Locate the window
    setLocation(k, m);
 
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
    if ("continue".equals(actionCmd)){
    dispose();
    new ControllerProblemsViewScreen(title);
    }//if
  }//actionPerformed
}//class

