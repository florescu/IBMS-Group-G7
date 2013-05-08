
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
 
class JourneyPlannerScreen extends JFrame implements ActionListener{

  //Declare the components 
  JLabel jLabelWelcome, titleLabel, timeLbl1, timeLbl2, timeLbl3, timeLbl4, timeLbl5;
  JLabel[] timeLbls;
  JButton jBtnDriver, jBtnPassanger, refreshBtn;
  JPanel contentPanel, jButtonsPanel;
  JComboBox JComboBoxFromList, JComboBoxToList, JComboBoxHourList, JComboBoxMinuteList;
  String[] busStops;
  String[] hour;
  String[] min;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public JourneyPlannerScreen(String paramString){

    this.setTitle(paramString);
    
    database.openBusDatabase();
    busStops = new String[]{"Stockport, Bus Station","Stockport, Dialstone Lane/Hillcrest Road","Stockport, Lower Bents Lane/Stockport Road",
    												"Stockport, Asda/Sainsbury's","Marple, Offerton Fold","Marple, Navigation Hotel","Marple, Norfolk Arms","Strines, Royal Oak",
    												"New Mills, Bus Station","Low Leighton, Ollerset View Hospital","Birch Vale, Grouse Hotel","Hayfield, Bus Station","Romiley, Corcoran Drive",
    												"Romiley, Train Station"};
    hour = new String[]{"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
    min = new String[]{"0","15","30","45"};
    
    JComboBoxFromList = new JComboBox(busStops);
    JComboBoxFromList.setEditable(false);
    JComboBoxFromList.setActionCommand("from");
    JComboBoxFromList.addActionListener(this);  
    
    JComboBoxToList = new JComboBox(busStops);
    JComboBoxToList.setEditable(false);
    JComboBoxToList.setActionCommand("to");
    JComboBoxToList.addActionListener(this); 
    
    JComboBoxHourList = new JComboBox(hour);
    JComboBoxHourList.setEditable(false);
    JComboBoxHourList.setActionCommand("hour");
    JComboBoxHourList.addActionListener(this);  
    
    JComboBoxMinuteList = new JComboBox(min);
    JComboBoxMinuteList.setEditable(false);
    JComboBoxMinuteList.setActionCommand("minutes");
    JComboBoxMinuteList.addActionListener(this); 
    
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(550, 200));
    contentPanel.setLayout(new GridLayout(11, 0));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the labels
    jLabelWelcome = new JLabel("Welcome to G7 - IBMS System");
    jLabelWelcome.setForeground(this.lblFgClr);
    
    //Align the labels
    jLabelWelcome.setHorizontalAlignment(0);
    
    //Create the buttons panel
    jButtonsPanel = new JPanel(new FlowLayout(10, 65, 20));
    jButtonsPanel.setBackground(this.layoutBgClr);

    refreshBtn = new JButton("Refresh");
    refreshBtn.setForeground(btnFgClr);
    refreshBtn.setBackground(btnBgClr);
    refreshBtn.setActionCommand("refresh");
    refreshBtn.addActionListener(this);

    titleLabel = new JLabel();
    timeLbl1 = new JLabel();
    timeLbl2 = new JLabel();
    timeLbl3 = new JLabel();
    timeLbl4 = new JLabel();
    timeLbl5 = new JLabel();

    timeLbls = new JLabel[5];
    
    timeLbls[0] = timeLbl1;
    timeLbls[1] = timeLbl2;
    timeLbls[2] = timeLbl3;
    timeLbls[3] = timeLbl4;
    timeLbls[4] = timeLbl5;

    //Add the components to the content panel
    contentPanel.add(this.jLabelWelcome);
    contentPanel.add(this.JComboBoxFromList);
    contentPanel.add(this.JComboBoxToList);
    contentPanel.add(this.JComboBoxHourList);
    contentPanel.add(this.JComboBoxMinuteList);
    contentPanel.add(this.jButtonsPanel);
    contentPanel.add(titleLabel);
    
    for(int i = 0; i < 5; i++)
      contentPanel.add(timeLbls[i]);
 
    
    contentPanel.add(refreshBtn);


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
  public void actionPerformed(ActionEvent paramActionEvent)
  {

  }//actionPerformed

      

}
