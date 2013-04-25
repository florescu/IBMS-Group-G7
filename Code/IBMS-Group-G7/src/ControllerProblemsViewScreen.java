import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class ControllerProblemsViewScreen extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable, jLabelSelectedView, jLabelSent, jLabelProblems, jLabelResetCancel, jLabelResetDelay , jLabelError;
  JPanel contentPanel, mainContentPanel, requestPanel, jButtonsPanel;
  JScrollPane jScrollPanel;
  JMenuBar mainMenuBar;
  JMenu jMenuFile, jMenuView;
  JMenuItem jMItemSave, jMItemPrint, jMItemExit;
  JMenuItem jMItemReport, jMItemDrivers, jMItemProblems;
  JTextArea JTextAreaTimetable;
  JButton jBtnResetDelay, jBtnSetDelay, jBtnResetCancel, jBtnCancel;
  JTextField jTxtFServiceCancel,  jTxtFServiceDelay;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color reqBgClr = new Color(140, 140, 140);
  Color lblErrorFgClr = new Color(255, 51, 51);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public ControllerProblemsViewScreen(String paramString){

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
 
    jMenuView.add(this.jMItemReport);
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
 
    jTxtFServiceDelay = new JTextField("service id", 7);
    jTxtFServiceDelay.setPreferredSize(new Dimension(30, 30));
    
    //Create the buttons
    jBtnResetDelay = new JButton("Reset Delay");
    jBtnResetDelay.setActionCommand("resetDelay");
    jBtnResetDelay.addActionListener(this);
    jBtnResetDelay.setBackground(this.btnBgClr);
    jBtnResetDelay.setForeground(this.btnFgClr);
    
    jBtnSetDelay = new JButton("Set Delay");
    jBtnSetDelay.setActionCommand("setDelay");
    jBtnSetDelay.addActionListener(this);
    jBtnSetDelay.setBackground(this.btnBgClr);
    jBtnSetDelay.setForeground(this.btnFgClr);
    
    jBtnResetCancel = new JButton("Reset Cancel");
    jBtnResetCancel.setActionCommand("resetCancel");
    jBtnResetCancel.addActionListener(this);
    jBtnResetCancel.setBackground(this.btnBgClr);
    jBtnResetCancel.setForeground(this.btnFgClr);
    
    jBtnCancel = new JButton("Cancel");
    jBtnCancel.setActionCommand("cancelService");
    jBtnCancel.addActionListener(this);
    jBtnCancel.setBackground(this.btnBgClr);
    jBtnCancel.setForeground(this.btnFgClr);
    
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
    
    jButtonsPanel = new JPanel(new GridLayout(4, 3, 10, 50));
    jButtonsPanel.setBackground(this.layoutBgClr); 
    
    jButtonsPanel.add(new JLabel(""));
    jButtonsPanel.add(this.jBtnSetDelay);
    jButtonsPanel.add(new JLabel(""));
    
    jLabelResetDelay = new JLabel("Reset delay for");
    jButtonsPanel.add(this.jLabelResetDelay);
    jButtonsPanel.add(this.jTxtFServiceDelay);
    jButtonsPanel.add(this.jBtnResetDelay);
    
    jButtonsPanel.add(new JLabel(""));
    jButtonsPanel.add(this.jBtnCancel);
    jButtonsPanel.add(new JLabel(""));
    
    jLabelResetCancel = new JLabel("Reset cancel for");
    jButtonsPanel.add(this.jLabelResetCancel);
    jButtonsPanel.add(this.jTxtFServiceCancel);
    jButtonsPanel.add(this.jBtnResetCancel);
    
    
    //Add the label to the content panel
    contentPanel.add(jScrollPanel, BorderLayout.CENTER);
    contentPanel.add(this.jButtonsPanel);

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
    else if ("report".equals(actionCmd)){
      this.dispose();
      new ControllerReportViewScreen(title);
    }
    else if ("drivers".equals(actionCmd)){
      this.dispose();
      new ControllerDriversViewScreen(title);
    }
    else if ("problems".equals(actionCmd)){
      this.dispose();
      new ControllerProblemsViewScreen(title);
    }
    else if ("resetDelay".equals(actionCmd)){
      database.openBusDatabase();   
      int serviceID = Integer.parseInt(jTxtFServiceDelay.getText());

      //Check if the fields are empty or with proper values
      
      if(jTxtFServiceDelay.getText() == null || 
         !Validator.isNumeric(jTxtFServiceDelay.getText())){
         
        jLabelError.setText("Error: provide valid service ID!");
        jLabelError.setVisible(true);

      }else if(!Service.isInDatabase(serviceID)){
       
        jLabelError.setText("Error: No such service in the database!");
        jLabelError.setVisible(true);
      }
      else{
      	jLabelSent.setText("Request sent.");
      	jLabelSent.setVisible(true);
      	Service.setDelayedTime(serviceID, 0);
      	this.dispose();
        new ControllerAckScreen();
      }//else
    }//else if reset Delay
    else if ("resetCancel".equals(actionCmd)){
      database.openBusDatabase();   
      int serviceID = Integer.parseInt(jTxtFServiceCancel.getText());

      //Check if the fields are empty or with proper values
      
      if(jTxtFServiceCancel.getText() == null || 
         !Validator.isNumeric(jTxtFServiceCancel.getText())){
         
        jLabelError.setText("Error: provide valid service ID!");
        jLabelError.setVisible(true);

      }else if(!Service.isInDatabase(serviceID)){
       
        jLabelError.setText("Error: No such service in the database!");
        jLabelError.setVisible(true);
      }
      else{
      	jLabelSent.setText("Request sent.");
      	jLabelSent.setVisible(true);
      	Service.setCancelled(serviceID, false);
      	this.dispose();
        new ControllerAckScreen();
      }//else
    }//else if reset cancel
    else if ("setDelay".equals(actionCmd)){
      this.dispose();
      new ControllerSetDelayViewScreen(title);
    }
    else if ("cancelService".equals(actionCmd)){
      this.dispose();
      new ControllerCancelServiceViewScreen(title);
    }
  }//actionPerformed
}//class

