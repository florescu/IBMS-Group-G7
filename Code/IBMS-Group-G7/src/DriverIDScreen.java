import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class DriverIDScreen extends JFrame implements ActionListener{

  //Declare the components
  JLabel jLabelId, jLabelName, jLabelError;
  JTextField jTxtFEnterId, jTxtFEnterName;
  JButton jBtnIdentify;
  JPanel contentPanel, jTextFieldPanelId,jTextFieldPanelName, jButtonsPanel;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color lblErrorFgClr = new Color(255, 51, 51);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public DriverIDScreen(String paramString){

    this.setTitle(paramString);
 
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(360, 200));
    contentPanel.setLayout(new GridLayout(6, 1));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the labels
    jLabelId = new JLabel("Enter your ID number:");
    jLabelName = new JLabel("Enter your Name: ");
    jLabelError = new JLabel("Error: Please enter a valid ID");
    jLabelId.setForeground(this.lblFgClr);
    jLabelName.setForeground(this.lblFgClr);
    jLabelError.setForeground(this.lblErrorFgClr);
    jLabelError.setVisible(false);

    //Align the labels
    jLabelId.setHorizontalAlignment(0);
    jLabelName.setHorizontalAlignment(0);
    jLabelError.setHorizontalAlignment(0);
 
    //Create the text fields
    jTxtFEnterId = new JTextField("Type in ID number", 18);
    jTxtFEnterId.setPreferredSize(new Dimension(80, 30));
 
    jTxtFEnterName = new JTextField("Type in Name", 18);
    jTxtFEnterName.setPreferredSize(new Dimension(80, 30));
    
    //Create the button
    jBtnIdentify = new JButton("Identify");
    jBtnIdentify.setActionCommand("identify");
    jBtnIdentify.addActionListener(this);
    jBtnIdentify.setBackground(this.btnBgClr);
    jBtnIdentify.setForeground(this.btnFgClr);
 
    //Create the text field panel
    jTextFieldPanelId = new JPanel(new FlowLayout(0, 80, 10));
    jTextFieldPanelId.setBackground(this.layoutBgClr);
    jTextFieldPanelId.add(this.jTxtFEnterId);
 
    jTextFieldPanelName = new JPanel(new FlowLayout(0, 80, 10));
    jTextFieldPanelName.setBackground(this.layoutBgClr);
    jTextFieldPanelName.add(this.jTxtFEnterName);
    
    //Create the button panel
    jButtonsPanel = new JPanel(new FlowLayout(10, 135, 0));
    jButtonsPanel.setBackground(this.layoutBgClr); 
    jButtonsPanel.add(this.jBtnIdentify);
 
    //Add the components to the content panel
    contentPanel.add(this.jLabelId);
    contentPanel.add(this.jTextFieldPanelId);
    contentPanel.add(this.jLabelName);
    contentPanel.add(this.jTextFieldPanelName);
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
    try{
    if ("identify".equals(actionCmd)){
      database.openBusDatabase();   
      int driverID = Integer.parseInt(jTxtFEnterId.getText());
      String name = jTxtFEnterName.getText();

      //Check if the fields are empty or with proper values
      
      if(jTxtFEnterId.getText() == null || 
         jTxtFEnterName.getText() == null || 
         !Validator.isNumeric(jTxtFEnterId.getText()) ||
         Validator.isNumeric(jTxtFEnterName.getText())){
         
        jLabelError.setText("Error: provide valid credentials!");
        jLabelError.setVisible(true);

      }else if(!DriverInfo.isInDatabase(driverID, name)){
       
        jLabelError.setText("Error: No such driver in the database!");
        jLabelError.setVisible(true);

      }
      else{
       dispose();
       new DriverTimetableViewScreen(title, driverID);
      }//else
    }
   } //try
   catch (NumberFormatException e)
   {
     jLabelError.setText("Error: provide numeric ID!");
     jLabelError.setVisible(true);
   }//catch
   catch (InvalidQueryException e)
   {
     jLabelError.setText("Error: Database access error");
     jLabelError.setVisible(true);
   }
   
  }//actionPerformed
}//class

