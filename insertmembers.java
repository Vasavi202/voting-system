package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class insertmembers extends Frame
{
	Button insertmembersButton;
	TextField member_id,first_name,last_name,email,voter_id;
	TextArea errorText;
	Connection connection;
	Statement statement;
	public insertmembers()
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
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");

			statement = con.createStatement();
			System.out.println("connected");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	public void buildGUI()
	{

		insertmembersButton = new Button("Insert");
		insertmembersButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{

							String query= "INSERT INTO members VALUES("+"'" +member_id.getText() + "','" + first_name.getText() + "','" + last_name.getText() + "','" +email.getText() + "','" +voter_id.getText()+ "'" +")";
							int i = statement.executeUpdate(query);
							errorText.append("\nInserted " + i + " rows successfully");
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		member_id = new TextField(15);
		first_name = new TextField(15);
		last_name = new TextField(15);
		email = new TextField(15);
		voter_id = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(5, 2));
		first.add(new Label("member id : "));
		first.add(member_id);
		first.add(new Label("first name : "));
		first.add(first_name);
		first.add(new Label("last name : "));
		first.add(last_name);
		first.add(new Label("email : "));
		first.add(email);
		first.add(new Label("voter id : "));
		first.add(voter_id);
		first.setBounds(125,90,200,100);
		Panel second = new Panel(new GridLayout(5, 1));
		second.add(insertmembersButton);
		second.setBounds(125,220,150,100);
		Panel third = new Panel();
		third.add(errorText);
		third.setBounds(125,320,300,200);
		setLayout(null);
		add(first);
		add(second);
		add(third);
		setTitle("Insert Gets");
		setSize(500, 600);
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
		insertmembers i = new insertmembers();
		i.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		i.buildGUI();
	}*/
}


