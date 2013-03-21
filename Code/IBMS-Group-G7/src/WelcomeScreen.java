import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
class WelcomeScreen extends JFrame implements ActionListener{

  //Declare the components 
  JLabel jLabelWelcome, jLabelChoose;
  JButton jBtnDriver, jBtnPassanger, jBtnController;
  JPanel contentPanel, jButtonsPanel;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public WelcomeScreen(String paramString){

    this.setTitle(paramString);

    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(550, 200));
    contentPanel.setLayout(new GridLayout(3, 1));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the labels
    jLabelWelcome = new JLabel("Welcome to G7 - IBMS System");
    jLabelChoose = new JLabel("Choose your role from the options below:");
    jLabelWelcome.setForeground(this.lblFgClr);
    jLabelChoose.setForeground(this.lblFgClr);
 
    //Align the labels
    jLabelWelcome.setHorizontalAlignment(0);
    jLabelChoose.setHorizontalAlignment(0);
 
    //Create the buttons
    jBtnDriver = new JButton("Driver");
    jBtnDriver.setActionCommand("driver");
    jBtnDriver.addActionListener(this);
    jBtnDriver.setBackground(this.btnBgClr);
    jBtnDriver.setForeground(this.btnFgClr);

    jBtnPassanger = new JButton("Passenger");
    jBtnPassanger.setActionCommand("passanger");
    jBtnPassanger.addActionListener(this);
    jBtnPassanger.setBackground(this.btnBgClr);
    jBtnPassanger.setForeground(this.btnFgClr);
 
    jBtnController = new JButton("Controller");
    jBtnController.setActionCommand("controller");
    jBtnController.addActionListener(this);
    jBtnController.setBackground(this.btnBgClr);
    jBtnController.setForeground(this.btnFgClr);
 
    //Create the buttons panel
    jButtonsPanel = new JPanel(new FlowLayout(10, 65, 20));
    jButtonsPanel.setBackground(this.layoutBgClr);

    //Add the buttons to the buttons panel
    jButtonsPanel.add(this.jBtnPassanger);
    jButtonsPanel.add(this.jBtnDriver);
    jButtonsPanel.add(this.jBtnController);

    //Add the components to the content panel
    contentPanel.add(this.jLabelWelcome);
    contentPanel.add(this.jLabelChoose);
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

    if("driver".equals(paramActionEvent.getActionCommand())){
      dispose();
      new DriverIdScreen("G7 - IBMS System | Driver - Identification");
    }else if("passanger".equals(paramActionEvent.getActionCommand())){
       System.out.println("Passanger button pressed");
    }else{
       dispose();
       new ControllerTimetableViewScreen("G7 - IBMS System | Controller - Identification");
    }//else
  }//actionPerformed

}//class
