package Evoting.system;
import java.awt.*;
import javax.swing.*; 
import java.awt.event.*;
public class MainFrame extends JFrame{ public static void main(String[] args) {
new MainFrame().setVisible(true);
}

public MainFrame() {
super("E VOTING");
initialize();
}

private void initialize() {

setForeground(Color.CYAN); setLayout(null);

JLabel Evoting = new JLabel("WELCOME!"); Evoting.setForeground(Color.BLUE);
Evoting.setFont(new Font("Tahoma", Font.PLAIN, 40)); Evoting.setBounds(240, 150, 1000, 55); add(Evoting);


JMenuBar menuBar = new JMenuBar(); setJMenuBar(menuBar);

JMenu administrators = new JMenu("ADMINISTRATOR"); administrators.setForeground(Color.BLUE);
menuBar.add(administrators);

JMenuItem insertadministrators = new JMenuItem("Insert"); administrators.add(insertadministrators);

JMenuItem updateadministrators = new JMenuItem("Update"); administrators.add(updateadministrators);

JMenuItem deleteadministrators = new JMenuItem("Delete"); administrators.add(deleteadministrators);

JMenu candidates = new JMenu("CANDIDATES_DETAILS "); candidates.setForeground(Color.BLUE);
menuBar.add(candidates);
 
JMenuItem insertcandidates = new JMenuItem("Insert");  candidates.add(insertcandidates);

JMenuItem updatecandidates = new JMenuItem("Update"); candidates.add(updatecandidates);

JMenuItem deletecandidates = new JMenuItem("Delete"); candidates.add(deletecandidates);

JMenu members = new JMenu("MEMBERS_DETAILS "); members.setForeground(Color.BLUE);
menuBar.add(members);

JMenuItem insertmembers = new JMenuItem("Insert"); members.add(insertmembers);

JMenuItem updatemembers = new JMenuItem("Update"); members.add(updatemembers);

JMenuItem deletemembers = new JMenuItem("Delete"); members.add(deletemembers);

JMenu positions= new JMenu("POSITIONS_DETAILS"); positions.setForeground(Color.BLUE);
menuBar.add(positions);

JMenuItem insertpositions = new JMenuItem("Insert");  positions.add(insertpositions);

JMenuItem updatepositions = new JMenuItem("Update");positions.add(updatepositions);

JMenuItem deletepositions = new JMenuItem("Delete"); positions.add(deletepositions);

insertadministrators.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	insertadministrators i = new insertadministrators();
	i.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) {
 
e.printStackTrace();
}
}
});

updateadministrators.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	updateadministrators ups = new updateadministrators();
	ups.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});

deleteadministrators.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	deleteadministrators del = new deleteadministrators();
	del.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});
insertcandidates.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	insertcandidates i = new insertcandidates();
	i.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});

updatecandidates.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	updatecandidates ups = new updatecandidates();
	ups.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});

deletecandidates.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	deletecandidates del = new deletecandidates();
	del.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
 
});

insertmembers.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	insertmembers i = new insertmembers();
	i.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});

updatemembers.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
	updatemembers ups = new updatemembers();
	ups.addWindowListener(new WindowAdapter(){
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	});
} catch (Exception e) { e.printStackTrace();
}
}
});

deletemembers.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
 try {
	 deletemembers del = new deletemembers();
		del.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
} catch (Exception e) { e.printStackTrace();	 
}
}
});

insertpositions.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
new insertpositions();
} catch (Exception e) { e.printStackTrace();
}
}
});

updatepositions.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
try {
new updatepositions();
} catch (Exception e) { e.printStackTrace();
}
 
}
});

deletepositions.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent ae){
new deletepositions();
}
});

setSize(700,500); setLocation(285,100);
setVisible(true);
}
}
