package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class insertpositions extends Frame
{
	Button insertpositionsButton;
	TextField position_id,position_name;
	TextArea errorText;
	Connection connection;
	Statement statement;
	public insertpositions()
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

		insertpositionsButton = new Button("Insert");
		insertpositionsButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{

							String query= "INSERT INTO positions VALUES("+"'" +position_id.getText() + "','" + position_name.getText() + "'" +")";
							int i = statement.executeUpdate(query);
							errorText.append("\nInserted " + i + " rows successfully");
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		position_id = new TextField(15);
		position_name = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(2, 2));
		first.add(new Label("position id : "));
		first.add(position_id);
		first.add(new Label("position name : "));
		first.add(position_name);
		first.setBounds(125,90,200,100);
		Panel second = new Panel(new GridLayout(2, 1));
		second.add(insertpositionsButton);
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
		insertpositions i = new insertpositions();
		i.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		i.buildGUI();
	}*/
}


