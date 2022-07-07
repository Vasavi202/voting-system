package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class insertcandidates extends Frame
{
	Button insertcandidatesButton;
	TextField candidate_id,candidate_name,candidate_position,candidate_cvotes;
	TextArea errorText;
	Connection connection;
	Statement statement;
	public insertcandidates()
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

		insertcandidatesButton = new Button("Insert");
		insertcandidatesButton.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						try
						{

							String query= "INSERT INTO candidates VALUES("+"'" +candidate_id.getText() + "','" + candidate_name.getText() + "','" + candidate_position.getText() + "','" +candidate_cvotes.getText() + "'" +")";
							int i = statement.executeUpdate(query);
							errorText.append("\nInserted " + i + " rows successfully");
						}
						catch (SQLException insertException)
						{
							displaySQLErrors(insertException);
						}
					}
				});
		candidate_id = new TextField(15);
		candidate_name = new TextField(15);
		candidate_position = new TextField(15);
		candidate_cvotes = new TextField(15);
		errorText = new TextArea(10, 40);
		errorText.setEditable(false);
		Panel first = new Panel();
		first.setLayout(new GridLayout(4, 2));
		first.add(new Label("candidate id : "));
		first.add(candidate_id);
		first.add(new Label("candidate name : "));
		first.add(candidate_name);
		first.add(new Label("candidate position : "));
		first.add(candidate_position);
		first.add(new Label("candidate cvotes : "));
		first.add(candidate_cvotes);
		first.setBounds(125,90,200,100);
		Panel second = new Panel(new GridLayout(4, 1));
		second.add(insertcandidatesButton);
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
		insertcandidates i = new insertcandidates();
		i.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		i.buildGUI();
	}*/
}


