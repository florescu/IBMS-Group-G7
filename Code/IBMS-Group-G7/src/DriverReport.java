import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class DriverReport extends JFrame 
                                  implements ActionListener{

  //Declare the components
  JLabel jLabelTimetable, jLabelSelectedView, jLabelContent;
  JPanel contentPanel, mainContentPanel, requestPanel;
  JScrollPane jScrollPanel;

  //Declate the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color reqBgClr = new Color(140, 140, 140);

  int driverID;
  String driverName;
 
  public DriverReport(String paramString, int driverID, String driverName){

    this.setTitle(paramString);
    this.driverID = driverID;
    this.driverName = driverName;
 
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

      //Create the label for the content panel
    jLabelContent = new JLabel("Name: " + driverName + "| Id: " + driverID);
    jLabelContent.setForeground(lblFgClr);
    jLabelContent.setBackground(layoutBgClr);
    jLabelContent.setHorizontalAlignment(SwingConstants.LEFT);

    //Add the label to the Scroll panel
    mainContentPanel.add(jLabelContent); 

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

