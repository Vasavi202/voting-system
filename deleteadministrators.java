package Evoting.system;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
public class deleteadministrators extends Frame
{
Button deleteadministratorsButton;
List admin_idList;
TextField admin_idText,first_nameText,last_nameText,emailText;
TextArea errorText;
Connection connection;
Statement statement;
ResultSet rs;
public deleteadministrators()
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
private void loadadministrators()
{
try
{
rs = statement.executeQuery("SELECT * FROM administrators");
while (rs.next())
{
admin_idList.add(rs.getString("admin_id"));
}
}
catch (SQLException e)
{
displaySQLErrors(e);
}
}
public void buildGUI()
{
admin_idList = new List(10);
loadadministrators();
add(admin_idList);
admin_idList.addItemListener(new ItemListener()
{
public void itemStateChanged(ItemEvent e)
{
try
{
rs = statement.executeQuery("SELECT * FROM administrators where admin_id ='"+admin_idList.getSelectedItem()+"'");
while (rs.next())
{
if
(rs.getString("admin_id").equals(admin_idList.getSelectedItem()))
break;
}
if (!rs.isAfterLast())
{
admin_idText.setText(rs.getString("admin_id"));
first_nameText.setText(rs.getString("first_name"));
last_nameText.setText(rs.getString("last_name"));
emailText.setText(rs.getString("email"));
}
}
catch (SQLException selectException)
{
displaySQLErrors(selectException);
}
}
});
deleteadministratorsButton = new Button("Delete Team");
deleteadministratorsButton.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
Connection con= DriverManager.getConnection("jdbc:oracle:thin:@218.248.0.7:1521:rdbms","it19737020","vasavi");
Statement statement1 = con.createStatement();
int i = statement1.executeUpdate("DELETE FROM administrators WHERE admin_id = '"+admin_idList.getSelectedItem()+"' and first_name='"+first_nameText.getText()+"' and  last_name='"+last_nameText.getText()+"' and email ='"+emailText.getText()+"'");
errorText.append("\nDeleted " + i + " rows successfully");
admin_idText.setText(null);
first_nameText.setText(null);
last_nameText.setText(null);
emailText.setText(null);
admin_idList.removeAll();
loadadministrators();
}
catch (SQLException insertException)
{
displaySQLErrors(insertException);
}
}
});
admin_idText = new TextField(15);
first_nameText = new TextField(15);
last_nameText = new TextField(15);
emailText = new TextField(15);
errorText = new TextArea(10, 40);
errorText.setEditable(false);
Panel first = new Panel();
first.setLayout(new GridLayout(4, 2));
first.add(new Label("admin id : "));
first.add(admin_idText);
first.add(new Label("first Name : "));
first.add(first_nameText);
first.add(new Label("last name : "));
first.add(last_nameText);
first.add(new Label("email : "));
first.add(emailText);
Panel second = new Panel(new GridLayout(4, 1));
second.add(deleteadministratorsButton);
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
		deleteadministrators del = new deleteadministrators();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
		del.buildGUI();
	}*/
}


