
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class GuiGenString extends JFrame {
    
    static JFrame f; 
    
    JButton btn;
    static JScrollPane sp,sp2;
    static JTextArea area,resultArea,kArea;
    JLabel labelInput,labelAccept;
    static JLabel output;
    static JList<String> list,list2; 
    static DefaultListModel<String> L1,L2;
    ArrayList<String> listsOfString,listsOfAcceptString;
    
    private static class ListAction implements ListSelectionListener{
        public void valueChanged(ListSelectionEvent e) {
            //output.setText(list.getSelectedValue().toString());
        }
    }

    public void render() {
        L1 = new DefaultListModel<>();

        //String result = "";
        for (int i = 0; i < listsOfString.size(); i++) {
            L1.addElement(listsOfString.get(i));
        }

        list = new JList<>(L1);
        list.addListSelectionListener(new ListAction());
        sp = new JScrollPane(list);
        sp.setBounds(10, 70, 600, 200);
        
        
        L2 = new DefaultListModel<>();

        //String result = "";
        for (int i = 0; i < listsOfAcceptString.size(); i++) {
            L2.addElement(listsOfAcceptString.get(i));
        }

        list2 = new JList<>(L2);
        list2.addListSelectionListener(new ListAction());
        sp2 = new JScrollPane(list2);
        sp2.setBounds(10, 350, 600, 200);
        
        
        f.getContentPane().add(sp);
        f.getContentPane().add(sp2);
        
        
        labelInput.setText("Generated all posible string : "+listsOfString.size() +" string(s)");
        labelAccept.setText("Generated accept string : "+listsOfAcceptString.size()+" string(s)");
        f.setVisible(true);
        
    }
    
    

    GuiGenString() {
        f = this;
        
        
        
        
        labelInput = new JLabel("");
        labelInput.setBounds(10, 30, 250, 20);
        
        labelAccept = new JLabel("Accept string");
        labelAccept.setBounds(10, 300, 200, 20);
        
        
        output = new JLabel("no result");
        Font font = new Font ("Serif", Font.BOLD, 50);
        output.setFont(font);
        output.setBounds(30,700 , 500, 100);
        //list = new JList<>();
        //sp = new JScrollPane(list);
        //sp.setBounds(10, 300, 200, 200);
        
        
        
        add(labelInput);
        add(labelAccept);
        add(output);
        
        //getContentPane().add(sp);
        
        setSize(800, 600);
        setLayout(null);
        setVisible(true);
        
    }

//    public static void main(String args[]) {
//        new Home();
//    }
}
