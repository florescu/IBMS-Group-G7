import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

class DriverReportWeek extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable, jLabelSelectedView, jLabelContent;
  JPanel contentPanel, mainContentPanel, requestPanel;
  JScrollPane jScrollPanel;
  JTextArea JTextAreaTimetable;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color reqBgClr = new Color(140, 140, 140);

  int driverID, week;
  String driverName;
 
  public DriverReportWeek(String paramString, int driverID, String driverName, int week){

    this.setTitle(paramString);
    this.driverID = driverID;
    this.driverName = driverName;
    this.week = week;
 
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

    
    JTextAreaTimetable = new JTextArea();
    
    String date="";

    switch(week){
    
    	case 1:date = "18_3_2013";
    			break;
    	case 2:date = "11_3_2013";
    			break;
    	case 3:date = "4_3_2013";
    			break;
    
    }
    
    
    
    try{
    	JTextAreaTimetable.read(new FileReader("reports/" + date +"/drivers/"+driverID), null);
    }catch(IOException ioe)
    {
    	
    } 
    
    JTextAreaTimetable.setEditable(false);

    //Add the label to the Scroll panel
    mainContentPanel.add(JTextAreaTimetable); 

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

    if("exit".equals(actionCmd))
      System.exit(0);

  }//actionPerformed
}//class

