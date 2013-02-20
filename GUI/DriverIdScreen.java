import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class DriverIdScreen extends JFrame implements ActionListener{

  //Declare the components
  JLabel jLabelEnter, jLabelError;
  JTextField jTxtFEnterId;
  JButton jBtnIdentify;
  JPanel contentPanel, jTextFieldPanel, jButtonsPanel;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color lblErrorFgClr = new Color(255, 51, 51);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public DriverIdScreen(String paramString){

    this.setTitle(paramString);
 
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(360, 200));
    contentPanel.setLayout(new GridLayout(4, 1));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the labels
    jLabelEnter = new JLabel("Enter your ID number below:");
    jLabelError = new JLabel("Error: Please enter a valid ID");
    jLabelEnter.setForeground(this.lblFgClr);
    jLabelError.setForeground(this.lblErrorFgClr);
    jLabelError.setVisible(false);

    //Align the labels
    jLabelEnter.setHorizontalAlignment(0);
    jLabelError.setHorizontalAlignment(0);
 
    //Create the text field
    jTxtFEnterId = new JTextField("Type in ID number", 18);
    jTxtFEnterId.setPreferredSize(new Dimension(80, 30));
 
    //Create the button
    jBtnIdentify = new JButton("Identify");
    jBtnIdentify.setActionCommand("identify");
    jBtnIdentify.addActionListener(this);
    jBtnIdentify.setBackground(this.btnBgClr);
    jBtnIdentify.setForeground(this.btnFgClr);
 
    //Create the text field panel
    jTextFieldPanel = new JPanel(new FlowLayout(0, 80, 10));
    jTextFieldPanel.setBackground(this.layoutBgClr);
    jTextFieldPanel.add(this.jTxtFEnterId);
 
    //Create the button panel
    jButtonsPanel = new JPanel(new FlowLayout(10, 135, 0));
    jButtonsPanel.setBackground(this.layoutBgClr); 
    jButtonsPanel.add(this.jBtnIdentify);
 
    //Add the components to the content panel
    contentPanel.add(this.jLabelEnter);
    contentPanel.add(this.jTextFieldPanel);
    contentPanel.add(this.jLabelError);
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
    String title = "G7 - IBMS System | Driver";

    if ("identify".equals(actionCmd)){
       dispose();
       new DriverTimetableViewScreen(title);
    }

  }//actionPerformed
}//class

