package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatecandidates extends Frame
{
	Button updatecandidatesButton;
	List candidate_idList;
	TextField candidate_idText,candidate_nameText,candidate_positionText,candidate_cvotesText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatecandidates()
	{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}
		catch (Exception e)
		{
			System.err.println("Unable to find and load driver");
			System.exit(1);
		}
		connectToDB();
		buildGUI();
	}
	public void connectToDB()
	{
		try
		{    
			Connection con =
				DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
			statement = con.createStatement();
			System.out.println("connected");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	private void loadcandidates()
	{
		try
		{
			rs = statement.executeQuery("SELECT candidate_id FROM candidates");
			while (rs.next())
			{
				candidate_idList.add(rs.getString("candidate_id"));
			}
		}
		catch (SQLException e)
		{
			displaySQLErrors(e);
		}
	}
	public void buildGUI()
	{
		candidate_idList = new List(10);
		loadcandidates();
		add(candidate_idList);
		candidate_idList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM candidates where candidate_id ='"+candidate_idList.getSelectedItem()+"'");
							rs.next();
							candidate_idText.setText(rs.getString("candidate_id"));
							candidate_nameText.setText(rs.getString("candidate_name"));
							candidate_positionText.setText(rs.getString("candidate_position"));
							candidate_cvotesText.setText(rs.getString("candidate_cvotes"));
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatecandidatesButton = new Button("Update");
		updatecandidatesButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE candidates "+ "SET candidate_name='" + candidate_nameText.getText() + "', "+ "candidate_position=" + candidate_positionText.getText() + ", "+ "candidate_cvotes ="+ candidate_cvotesText.getText() + " WHERE candidate_id= "+ candidate_idList.getSelectedItem());
							errorText.append("\nUpdated " + i + " rows successfully");
							candidate_idList.removeAll();
							loadcandidates();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		candidate_idText = new TextField(15);
		candidate_idText.setEditable(false);
		candidate_nameText = new TextField(15);
		candidate_nameText = new TextField(15);
		candidate_cvotesText = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("admin id : "));
		first.add(candidate_idText);
		first.add(new Label("first name : "));
		first.add(candidate_nameText);
		first.add(new Label("last name : "));
		first.add(candidate_positionText);
		first.add(new Label("email : "));
		first.add(candidate_cvotesText);
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(updatecandidatesButton);
		Panel third = new Panel();
		third.add(errorText);
		add(first);
		add(second);
		add(third);
		setTitle("Update Element ");
		setSize(500, 600);
		setLayout(new FlowLayout());
		setVisible(true);
	}
	private void displaySQLErrors(SQLException e)
	{
		errorText.append("\nSQLException: " + e.getMessage() + "\n");
		errorText.append("SQLState: " + e.getSQLState() + "\n");
		errorText.append("VendorError: " + e.getErrorCode() + "\n");
	}
	/*public static void main(String[] args)
	{
		updatecandidates ups = new updatecandidates();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}*/
}
