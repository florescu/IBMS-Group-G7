import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
 
class PassengerScreen extends JFrame implements ActionListener{

  //Declare the components 
  JLabel jLabelWelcome, jLabelChoose;
  JButton jBtnDriver, jBtnPassanger, jBtnController;
  JPanel contentPanel, jButtonsPanel;
  JComboBox JComboBoxBusList, JComboBoxRoutesList;
  String[] busStops;
  int[] routes;

  //Declare the colors
  Color layoutBgClr = new Color(255, 255, 255);
  Color lblFgClr = new Color(150, 150, 150);
  Color btnBgClr = new Color(245, 245, 245);
  Color btnFgClr = new Color(130, 130, 130);
 
  public PassengerScreen(String paramString){

    this.setTitle(paramString);
    
    database.openBusDatabase();
    busStops = BusStopInfo.getBusStops();
    
    routes = BusStopInfo.getRoutes();
    String[] finalRoutes = new String[routes.length];
    for (int i = 0; i<routes.length; i++)
    	finalRoutes[i] = Integer.toString(routes[i]);
    
    JComboBoxRoutesList = new JComboBox(finalRoutes);
    JComboBoxRoutesList.setEditable(false);
    JComboBoxRoutesList.setActionCommand("route");
    JComboBoxRoutesList.addActionListener(this);  
    
    JComboBoxBusList = new JComboBox();
    JComboBoxBusList.setEditable(false);
    JComboBoxBusList.setActionCommand("selectStop");
    JComboBoxBusList.addActionListener(this);    	
    
    //Create the content panel
    contentPanel = new JPanel();
    contentPanel.setPreferredSize(new Dimension(550, 200));
    contentPanel.setLayout(new GridLayout(11, 0));
    contentPanel.setBackground(this.layoutBgClr);
 
    //Create the labels
    jLabelWelcome = new JLabel("Welcome to G7 - IBMS System");
    jLabelWelcome.setForeground(this.lblFgClr);
    jLabelChoose = new JLabel("Choose the bus stop for which you want to display next 5 buses arriving: ");
    jLabelChoose.setForeground(this.lblFgClr);
    
    //Align the labels
    jLabelWelcome.setHorizontalAlignment(0);
    jLabelChoose.setHorizontalAlignment(0);
    
    //Create the buttons panel
    jButtonsPanel = new JPanel(new FlowLayout(10, 65, 20));
    jButtonsPanel.setBackground(this.layoutBgClr);

    //Add the components to the content panel
    contentPanel.add(this.jLabelWelcome);
    contentPanel.add(this.jLabelChoose);
    contentPanel.add(this.JComboBoxRoutesList);
    contentPanel.add(this.JComboBoxBusList);
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

  	if ("selectStop".equals(paramActionEvent.getActionCommand()))
  	{
	  	JComboBox cb = (JComboBox)paramActionEvent.getSource();
	    String stopName = (String)cb.getSelectedItem();
	    String[] tokens = stopName.split(" ");
	    int stopID = Integer.parseInt(tokens[0]);
	    String[] result = BusStopInfo.display5buses(stopID);

      System.out.println("Selected bus stop: " + stopID + " " + stopName);

      JLabel titleLabel = new JLabel("Service						Time");
      titleLabel.setForeground(this.lblFgClr);
      contentPanel.add(titleLabel);

      System.out.println("Buses' time:");
      for(int i = 0; i < result.length; i++){
    
        System.out.println("Bus " + (i + 1) + " " + result[i]);
        
        JLabel timeLbl = new JLabel(result[i]);

        timeLbl.setForeground(this.lblFgClr);
        contentPanel.add(timeLbl);
      
      }//for

    }else if ("route".equals(paramActionEvent.getActionCommand())){

	  	JComboBox cb = (JComboBox)paramActionEvent.getSource();
	    String route = (String)cb.getSelectedItem();
	    int routeID = Integer.parseInt(route);
	    int[] finalBusStops = BusStopInfo.getBusStops(routeID);

	    for (int i = 0; i<finalBusStops.length; i++)
	      busStops[i] = Integer.toString(finalBusStops[i]);

	    for (int i = 0; i<finalBusStops.length; i++)
	    	System.out.println(busStops[i]);

	    String[] busStopNames = new String[busStops.length]; 
	    String[] allBusStops = BusStopInfo.getBusStops();
	    
      System.out.println("Selected route: " + routeID + " " + route);

	    //This should compare the array of all bus stops which contains both id and name with the one which
	    //only contains ids. if the small array element is contained in the bigger array, the busStopNames should be
	    //the same as the big array element.
	    for (int j = 0; j<allBusStops.length; j++){
	    
        for (int i = 0; i<busStops.length; i++){
	    	  
          if (allBusStops[j].contains(busStops[i])){ // this should work but doesn't!!!!!!!!!!!!!!!! WORKS
 	    	
            //System.out.println("BUS: " + allBusStops[j] + " contains: " + busStops[i]);
            busStopNames[i] = allBusStops[j];  // same about this!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! WORKS
	    
          }//if
        }//for
      }//for

	    for (int j = 0; j<busStopNames.length;j++)
	    	System.out.println(busStopNames[j]);
	    

	    int combosize = JComboBoxBusList.getItemCount();
	    System.out.println(combosize);
	    

      //To be fixed - remove items(labels)
	    if (combosize > 0){
	    	for (int i = 0; i<combosize-1; i++){
	    		if (JComboBoxBusList.getItemAt(i) != null)
	    		{
        		JComboBoxBusList.removeItemAt(i);
        		JComboBoxBusList.removeAll();
	    		}//if	
        }//for
      }//if
  		
	    
	    for (int i = 0; i<finalBusStops.length; i++)
	    {
	      JComboBoxBusList.addItem(busStopNames[i]);
	      System.out.println("added");
	    }
	    System.out.println("---------------");
  	}
    else
    {
       dispose();
       new PassengerScreen("G7 - IBMS System | Passenger - Information");
    }
  }//actionPerformed

}//class
