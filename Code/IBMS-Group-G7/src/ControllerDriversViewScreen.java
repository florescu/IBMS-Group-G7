import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ControllerDriversViewScreen extends JFrame implements ActionListener
{

	// Declare the components
	JLabel jLabelTimetable, jLabelSelectedView;
	JPanel contentPanel, mainContentPanel, requestPanel;
	JScrollPane jScrollPanel;
	JMenuBar mainMenuBar;
	JMenu jMenuFile, jMenuView;
	JMenuItem jMItemSave, jMItemPrint, jMItemExit;
	JMenuItem jMItemReport, jMItemDrivers;

	// Declate the colors
	Color layoutBgClr = new Color(255, 255, 255);
	Color lblFgClr = new Color(150, 150, 150);
	Color reqBgClr = new Color(140, 140, 140);

	int driversNum;
	int driversIDs[];
	String driversNames[];

	public ControllerDriversViewScreen(String paramString)
	{

		this.setTitle(paramString);

		// Create the main menu bar
		mainMenuBar = new JMenuBar();
		mainMenuBar.setBackground(this.layoutBgClr);

		// Create the two menus
		jMenuFile = new JMenu("File");
		jMenuFile.setMnemonic(KeyEvent.VK_F);
		jMenuView = new JMenu("View");
		jMenuView.setMnemonic(KeyEvent.VK_V);

		jLabelSelectedView = new JLabel("Selected View: Drivers");
		jLabelSelectedView.setForeground(lblFgClr);

		// Add the two menus to the menu bar
		mainMenuBar.add(jMenuFile);
		mainMenuBar.add(jMenuView);
		mainMenuBar.add(Box.createHorizontalGlue());
		mainMenuBar.add(jLabelSelectedView);

		// Create the menu items
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

		// Add the items to the menus
		jMenuFile.add(this.jMItemSave);
		jMenuFile.add(this.jMItemPrint);
		jMenuFile.add(this.jMItemExit);

		jMenuView.add(this.jMItemReport);
		jMenuView.add(this.jMItemDrivers);

		// Create the mainContent panel
		mainContentPanel = new JPanel();
		mainContentPanel.setLayout(new GridLayout(0, 3, 20, 20));
		mainContentPanel.setBackground(layoutBgClr);

		// Create the scrollpanel
		jScrollPanel = new JScrollPane(mainContentPanel);
		jScrollPanel
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPanel
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		jScrollPanel.setBackground(layoutBgClr);

		// Create the content panel
		contentPanel = new JPanel();
		contentPanel.setPreferredSize(new Dimension(600, 300));
		contentPanel.setLayout(new BorderLayout());
		contentPanel.setBackground(layoutBgClr);

		// Create the layouts for each driver

		// Get all the ids
		database.openBusDatabase();
		driversIDs = DriverInfo.getDrivers();

		driversNum = driversIDs.length;

		// Get all the names
		driversNames = new String[driversIDs.length];

		for (int i = 0; i < driversNames.length; i++)
		{
			driversNames[i] = DriverInfo.getName(driversIDs[i]);
		}// for

		JPanel[] driverDataPanels = new JPanel[driversIDs.length];
		JButton[] vReportBtns = new JButton[driversIDs.length];
		JButton[] v1waBtns = new JButton[driversIDs.length];
		JButton[] v2waBtns = new JButton[driversIDs.length];
		JButton[] v3waBtns = new JButton[driversIDs.length];

		// Create the View Report Buttons
		for (int i = 0; i < driversIDs.length; i++)
		{
			vReportBtns[i] = new JButton("View Report");
			vReportBtns[i].setForeground(lblFgClr);
			vReportBtns[i].setBackground(layoutBgClr);
			vReportBtns[i].setActionCommand("vreport" + i);
			vReportBtns[i].addActionListener(this);

			v1waBtns[i] = new JButton("1 week ago");
			v1waBtns[i].setForeground(lblFgClr);
			v1waBtns[i].setBackground(layoutBgClr);
			v1waBtns[i].setActionCommand("v1wa" + i);
			v1waBtns[i].addActionListener(this);

			v2waBtns[i] = new JButton("2 weeks ago");
			v2waBtns[i].setForeground(lblFgClr);
			v2waBtns[i].setBackground(layoutBgClr);
			v2waBtns[i].setActionCommand("v2wa" + i);
			v2waBtns[i].addActionListener(this);

			v3waBtns[i] = new JButton("3 weeks ago");
			v3waBtns[i].setForeground(lblFgClr);
			v3waBtns[i].setBackground(layoutBgClr);
			v3waBtns[i].setActionCommand("v3wa" + i);
			v3waBtns[i].addActionListener(this);
		}// for

		// Create the drivers dataPanel
		for (int i = 0; i < driversIDs.length; i++)
		{
			driverDataPanels[i] = new JPanel();
			driverDataPanels[i].setLayout(new GridLayout(6, 1));
			driverDataPanels[i].setBackground(layoutBgClr);

			driverDataPanels[i].add(new JLabel("Name: " + driversNames[i]));
			driverDataPanels[i].add(new JLabel("Id: " + driversIDs[i]));
			driverDataPanels[i].add(vReportBtns[i]);
			driverDataPanels[i].add(v1waBtns[i]);
			driverDataPanels[i].add(v2waBtns[i]);
			driverDataPanels[i].add(v3waBtns[i]);
		}// for

		// Add the driver panels to the main content panel
		for (int i = 0; i < driversIDs.length; i++)
		{
			mainContentPanel.add(driverDataPanels[i]);
		}// for

		// Add the label to the content panel
		contentPanel.add(jScrollPanel, BorderLayout.CENTER);

		// Resize and position the window
		Dimension localDimension = Toolkit.getDefaultToolkit().getScreenSize();

		int i = getSize().width;
		int j = getSize().height;
		int k = (localDimension.width - i) / 2 - 300;
		int m = (localDimension.height - j) / 2 - 150;

		this.setResizable(false);

		// Locate the window
		this.setLocation(k, m);

		this.setJMenuBar(this.mainMenuBar);
		this.setContentPane(this.contentPanel);
		this.pack();
		this.setVisible(true);

		// Define the action on closing the window
		addWindowListener(new WindowAdapter()
		{

			public void windowClosing(WindowEvent paramAnonymousWindowEvent)
			{

				System.exit(0);
			}// windowClosing
		});// addWindowListener

	}// constructor

	// Catch the click events
	public void actionPerformed(ActionEvent paramActionEvent)
	{

		String actionCmd = paramActionEvent.getActionCommand();
		String title = "G7 - IBMS System | Controller";

		if ("exit".equals(actionCmd))
			System.exit(0);
		else if ("report".equals(actionCmd))
		{
			this.dispose();
			new ControllerReportViewScreen(title);
		} else if ("drivers".equals(actionCmd))
		{
			this.dispose();
			new ControllerDriversViewScreen(title);
		}

		// catch the view report click events
		for (int i = 0; i < driversNum; i++)
		{

			String rBtnAction = "vreport" + i;
			String rBtn1wa = "v1wa" + i;
			String rBtn2wa = "v2wa" + i;
			String rBtn3wa = "v3wa" + i;

			if (rBtnAction.equals(actionCmd))
			{
				// this.dispose();
				new DriverReport("G7 - IBMS System | Driver Report", driversIDs[i],
						driversNames[i]);
			} else if (rBtn1wa.equals(actionCmd))
			{
				// this.dispose();
				new DriverReportWeek("G7 - IBMS System | Driver Report", driversIDs[i],
						driversNames[i], 1);
			} else if (rBtn2wa.equals(actionCmd))
			{
				// this.dispose();
				new DriverReportWeek("G7 - IBMS System | Driver Report", driversIDs[i],
						driversNames[i], 2);
			} else if (rBtn3wa.equals(actionCmd))
			{
				// this.dispose();
				new DriverReportWeek("G7 - IBMS System | Driver Report", driversIDs[i],
						driversNames[i], 3);
			}

		}// for

	}// actionPerformed
}// class

