package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatemembers extends Frame
{
	Button updatemembersButton;
	List member_idList;
	TextField member_idText,first_nameText,last_nameText,emailText,voter_idText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatemembers()
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
	private void loadmembers()
	{
		try
		{
			rs = statement.executeQuery("SELECT member_id FROM members");
			while (rs.next())
			{
				member_idList.add(rs.getString("member_id"));
			}
		}
		catch (SQLException e)
		{
			displaySQLErrors(e);
		}
	}
	public void buildGUI()
	{
		member_idList = new List(10);
		loadmembers();
		add(member_idList);
		member_idList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM members where member_id ='"+member_idList.getSelectedItem()+"'");
							rs.next();
							member_idText.setText(rs.getString("member_id"));
							first_nameText.setText(rs.getString("first_name"));
							last_nameText.setText(rs.getString("last_name"));
							emailText.setText(rs.getString("email"));
							voter_id.setText(rs.getString("voter_id"));
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatemembersButton = new Button("Update");
		updatemembersButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE members "+ "SET first_name='" + first_nameText.getText() + "', "+ "last_name=" + last_nameText.getText() + "', "+ "email=" + emailText.getText() + ", "+ "voter_id ="+ voter_idText.getText() + " WHERE member_id= "+ member_idList.getSelectedItem());
							errorText.append("\nUpdated " + i + " rows successfully");
							member_idList.removeAll();
							loadmembers();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		member_idText = new TextField(15);
		member_idText.setEditable(false);
		first_nameText = new TextField(15);
		last_nameText = new TextField(15);
		emailText = new TextField(15);
		voter_idText = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("member id : "));
		first.add(member_idText);
		first.add(new Label("first name : "));
		first.add(first_nameText);
		first.add(new Label("last name : "));
		first.add(last_nameText);
		first.add(new Label("email : "));
		first.add(emailText);
		first.add(new Label("voter id : "));
		first.add(voter_idText);
		Panel second = new Panel(new GridLayout(5, 1));
		second.add(updatemembersButton);
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
		updatemembers ups = new updatemembers();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}*/
}
