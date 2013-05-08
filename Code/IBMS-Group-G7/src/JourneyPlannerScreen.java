
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class JourneyPlannerScreen extends JFrame implements ActionListener{

	//Declare the components 
	JLabel jLabelWelcome, titleLabel, fromLabel, toLabel, atLabel, colonLabel;
	JButton jBtnDriver, jBtnPassanger, goBtn;
	JPanel contentPanel, selectPanel, destinationPanel, timePanel, topPanel;
	JComboBox JComboBoxFromList, JComboBoxToList, JComboBoxHourList, JComboBoxMinuteList;
	JTextArea messageTextArea;
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
		min = new String[]{"00","15","30","45"};

		JComboBoxFromList = new JComboBox(busStops);
		JComboBoxFromList.setEditable(false);
		JComboBoxFromList.addActionListener(this);  

		JComboBoxToList = new JComboBox(busStops);
		JComboBoxToList.setEditable(false);
		JComboBoxToList.addActionListener(this); 

		JComboBoxHourList = new JComboBox(hour);
		JComboBoxHourList.setEditable(false);
		JComboBoxHourList.addActionListener(this);  

		JComboBoxMinuteList = new JComboBox(min);
		JComboBoxMinuteList.setEditable(false);
		JComboBoxMinuteList.addActionListener(this); 

		//Create the message text area
		messageTextArea = new JTextArea("");
		messageTextArea.setEditable(false);

		//Create the content panel
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(775, 200));
		contentPanel.setLayout(new GridLayout(0,1));
		contentPanel.setBackground(this.layoutBgClr);

		//Create the select panel
		selectPanel = new JPanel();
		selectPanel.setLayout(new FlowLayout());
		selectPanel.setBackground(this.layoutBgClr);

		//Create the destination panel
		destinationPanel = new JPanel();
		destinationPanel.setLayout(new FlowLayout());
		destinationPanel.setBackground(this.layoutBgClr);

		//Create the time panel
		timePanel = new JPanel();
		timePanel.setLayout(new FlowLayout());
		timePanel.setBackground(this.layoutBgClr);

		//Create the top panel
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(3,1));
		topPanel.setBackground(this.layoutBgClr);


		//Create the labels
		jLabelWelcome = new JLabel("Welcome to G7 - IBMS System");
		jLabelWelcome.setForeground(this.lblFgClr);
		fromLabel = new JLabel("From:");
		fromLabel.setForeground(this.lblFgClr);
		toLabel = new JLabel("To:");
		toLabel.setForeground(this.lblFgClr);
		atLabel = new JLabel("at");
		atLabel.setForeground(this.lblFgClr);
		colonLabel = new JLabel(":");
		colonLabel.setForeground(this.lblFgClr);

		//Align the labels
		jLabelWelcome.setHorizontalAlignment(0);

		goBtn = new JButton("Go");
		goBtn.setForeground(btnFgClr);
		goBtn.setBackground(btnBgClr);
		goBtn.setActionCommand("go");
		goBtn.addActionListener(this);

		titleLabel = new JLabel();

		//Add the components to the content panel
		topPanel.add(this.jLabelWelcome);
		destinationPanel.add(this.fromLabel);
		destinationPanel.add(this.JComboBoxFromList);
		destinationPanel.add(this.toLabel);
		destinationPanel.add(this.JComboBoxToList);
		timePanel.add(this.atLabel);
		timePanel.add(this.JComboBoxHourList);
		timePanel.add(this.colonLabel);
		timePanel.add(this.JComboBoxMinuteList);
		topPanel.add(this.destinationPanel);
		selectPanel.add(this.timePanel);
		selectPanel.add(goBtn);
		topPanel.add(this.selectPanel);
		contentPanel.add(this.topPanel);
		contentPanel.add(this.messageTextArea);


		//Resize and position the window
		Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

		int i = getSize().width;
		int j = getSize().height;
		int k = (localDimension.width - i) / 2 - 300;
		int m = (localDimension.height - j) / 2 - 150;

		this.setResizable(true);

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

		String actionCmd = paramActionEvent.getActionCommand();
		if(actionCmd.equals("go"))
		{
			try
			{
				int fromStop = JComboBoxFromList.getSelectedIndex();
				int toStop = JComboBoxToList.getSelectedIndex();
				int time = Integer.parseInt(hour[JComboBoxHourList.getSelectedIndex()])*60;
				time = time + Integer.parseInt(min[JComboBoxMinuteList.getSelectedIndex()]);
				System.out.println(fromStop+" "+toStop+" "+ time);
				Journey j = new Journey(fromStop, toStop, time);
				this.messageTextArea.append(j.getMessage());
				System.out.println(j.getMessage());
				this.pack();
			}
			catch(Exception e)
			{
				this.messageTextArea.append("There is no buses for the time you picked.");
			}
		}
	}//actionPerformed



}
