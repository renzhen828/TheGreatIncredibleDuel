package ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame
{
    private static final long serialVersionUID = -8808883923263763897L;

    JPanel mainPanel;
    
    public JPanel getMainPanel()
    {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel)
    {
        this.mainPanel = mainPanel;
    }

    public MainFrame(){
        this.getContentPane().add(BorderLayout.CENTER, 
                getMainPanel());
    }
    
    
}
