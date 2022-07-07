package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class deletecandidates extends Frame
{
Button deletecandidatesButton;
List candidate_idList;
TextField candidate_idText,candidate_nameText,candidate_positionText,candidate_cvotesText;
TextArea errorText;
Connection connection;
Statement statement;
ResultSet rs;
public deletecandidates()
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
private void loadcandidates()
{
try
{
rs = statement.executeQuery("SELECT * FROM candidates");
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
while (rs.next())
{
if
(rs.getString("candidate_id").equals(candidate_idList.getSelectedItem()))
break;
}
if (!rs.isAfterLast())
{
	candidate_idText.setText(rs.getString("candidate_id"));
	candidate_nameText.setText(rs.getString("candidate_name"));
	candidate_positionText.setText(rs.getString("candidate_position"));
	candidate_cvotesText.setText(rs.getString("candidate_cvotes"));
}
}
catch (SQLException selectException)
{
displaySQLErrors(selectException);
}
}
});
deletecandidatesButton = new Button("Delete Team");
deletecandidatesButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
Statement statement1 = con.createStatement();
int i = statement1.executeUpdate("DELETE FROM candidates WHERE candidate_id = '"+candidate_idList.getSelectedItem()+"' and candidate_name='"+candidate_nameText.getText()+"' and  candidate_position='"+candidate_positionText.getText()+"' and candidate_cvotes ='"+candidate_cvotesText.getText()+"'");
errorText.append("\nDeleted " + i + " rows successfully");
candidate_idText.setText(null);
candidate_nameText.setText(null);
candidate_positionText.setText(null);
candidate_cvotesText.setText(null);
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
candidate_nameText = new TextField(15);
candidate_positionText = new TextField(15);
candidate_cvotesText = new TextField(15);
errorText = new TextArea(10, 40);
errorText.setEditable(false);
Panel first = new Panel();
first.setLayout(new GridLayout(4, 2));
first.add(new Label("candidate id : "));
first.add(candidate_idText);
first.add(new Label("candidate Name : "));
first.add(candidate_nameText);
first.add(new Label("candidate position : "));
first.add(candidate_positionText);
first.add(new Label("candidate cvotes : "));
first.add(candidate_cvotesText);
Panel second = new Panel(new GridLayout(4, 1));
second.add(deletecandidatesButton);
Panel third = new Panel();
third.add(errorText);
add(first);
add(second);
add(third);
setTitle("Remove  Team ");
setSize(450, 600);
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
		deletecandidates del = new deletecandidates();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		del.buildGUI();
	}*/
}


