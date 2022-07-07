package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class updatepositions extends Frame
{
	Button updatepositionsButton;
	List position_idList;
	TextField position_idText,position_nameText;
	TextArea errorText;
	Connection connection;
	Statement statement;
	ResultSet rs;
	public updatepositions()
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
	private void loadpositions()
	{
		try
		{
			rs = statement.executeQuery("SELECT position_id FROM positions");
			while (rs.next())
			{
				position_idList.add(rs.getString("position_id"));
			}
		}
		catch (SQLException e)
		{
			displaySQLErrors(e);
		}
	}
	public void buildGUI()
	{
		position_idList = new List(10);
		loadpositions();
		add(position_idList);
		position_idList.addItemListener(new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						try
						{
							rs = statement.executeQuery("SELECT * FROM positions where position_id ='"+position_idList.getSelectedItem()+"'");
							rs.next();
							position_idText.setText(rs.getString("position_id"));
							position_nameText.setText(rs.getString("position_name"));
						}
						catch (SQLException selectException)
						{
							displaySQLErrors(selectException);
						}
					}
				});
		updatepositionsButton = new Button("Update");
		updatepositionsButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{
							 
							Connection con =
								DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
							Statement statement1 = con.createStatement();
							int i = statement1.executeUpdate("UPDATE positions "+ "SET position_name='" + position_nameText.getText() + "', " + " WHERE position_id= "+ position_idList.getSelectedItem());
							errorText.append("\nUpdated " + i + " rows successfully");
							position_idList.removeAll();
							loadpositions();
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		position_idText = new TextField(15);
		position_idText.setEditable(false);
		position_nameText = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(2, 2));
		first.add(new Label("position id : "));
		first.add(position_idText);
		first.add(new Label("position name : "));
		first.add(position_nameText);
		Panel second = new Panel(new GridLayout(2, 1));
		second.add(updatepositionsButton);
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
		updatepositions ups = new updatepositions();
		ups.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		ups.buildGUI();
	}*/
}
