import javafx.scene.control.Tab;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RulePanel {

    GUI gui;
    JLabel rule_input_title;
    JScrollPane scrollPane;
    JTable jTable;
    JButton create_button;
    JButton input_word;
    private DefaultTableModel model;
    RuleSet rules;
    JLabel word_title;
    JButton calc_button;
    JButton page_select;
    static JLabel summary;

    JLabel stepLebel;
    JSlider slider;


     public RulePanel(GUI gui , RuleSet rules){

         this.gui = gui;
         this.rules = rules;


         rule_input_title = new JLabel("Rule : ",SwingConstants.LEFT);
         rule_input_title.setBounds(10,0,100,30);
         rule_input_title.setFont(gui.jFont);



        //Config Table
         jTable = new JTable();
         jTable.setFont(this.gui.jFont);
//         model = (DefaultTableModel)jTable.getModel();
         model = new DefaultTableModel(){
             @Override
             public boolean isCellEditable(int row, int column) {
                 //all cells false
                 return false;
             }
         };
         model.addColumn("Index");
         model.addColumn("Terminal");
         model.addColumn("Definition");
         jTable.setModel(model);
         jTable.setColumnSelectionAllowed(true);
         jTable.setRowSelectionAllowed(true);
         JTableHeader header = jTable.getTableHeader();
         jTable.getColumnModel().getColumn(0).setPreferredWidth(80);
         jTable.getColumnModel().getColumn(1).setPreferredWidth(80);
         jTable.getColumnModel().getColumn(2).setPreferredWidth(340);
         header.setFont(this.gui.jFont);
//         model.addRow(new String[]{"asd","asd"});

         scrollPane = new JScrollPane(jTable);
         scrollPane.setBounds(10,30,500,150);


         //Create Rule Button
         create_button = new JButton("Create New Rule");
         create_button.setBounds(10,190,200,40);
         create_button.setFont(this.gui.jFont);
         create_button.setBackground(Color.green);
         create_button.setForeground(Color.black);
         create_button.addActionListener(create_button_onClick);


        //Input word
         input_word = new JButton("Input word");
         input_word.setBounds(300,190,200,40);
         input_word.setFont(this.gui.jFont);
         input_word.addActionListener(input_word_onClick);

         word_title = new JLabel("Word input dont exit");
         word_title.setHorizontalAlignment(JLabel.CENTER);
         word_title.setForeground(Color.GRAY);
         word_title.setBounds(10,240,500,40);
         word_title.setFont(this.gui.jFont);
         word_title.setBorder(BorderFactory.createEtchedBorder());

         calc_button = new JButton("Calc");
         calc_button.setBounds(10,290,500,40);
         calc_button.setFont(this.gui.jFont);
         calc_button.addActionListener(calc_listener);

         stepLebel = new JLabel("Step : ");
         stepLebel.setFont(this.gui.jFont);
         stepLebel.setBounds(10, 450, 400, 40);

         slider = new JSlider();
         slider.setValue(0);
         slider.setMinimum(0);
         slider.setMaximum(0);
         slider.setBounds(10, 500, 400, 40);
         slider.addChangeListener(change_listener);


         gui.add(rule_input_title);
         gui.add(scrollPane);
         gui.add(create_button);
         gui.add(input_word);
         gui.add(word_title);
         gui.add(calc_button);
         gui.add(stepLebel);
         gui.add(slider);

    }








    ActionListener create_button_onClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            String terminal = (String)JOptionPane.showInputDialog("Input Terminal \n- Terminal must be Capital Letter \n- Terminal must be one character ex. \"A\"");
            if(terminal.length()>0 && isCapitalLetter(terminal)&&terminal.length()<2){
                String definition = (String)JOptionPane.showInputDialog("Input New Definition");
                if(definition.length()>0){

                    Main.gui.rules.addRule(new Rule(terminal,definition.replaceAll(" ","")));
                    ((DefaultTableModel)jTable.getModel()).addRow(new Object[]{ rules.getCount(),  terminal  , definition });

                }
                else{
                    gui.alert("Please type your definition");
                }
            }
            else{
                gui.alert("Terminal must be not empty and Capital letter");
            }

        }
    };

     ActionListener input_word_onClick = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {

             String input_word_text = (String)JOptionPane.showInputDialog("This word mustn't be empty and terminal symbol");

             if(input_word_text.length()>0 && input_word_text.indexOf(" ")==-1 && isLowerLetter(input_word_text)){

                 Main.gui.rules.setWord(input_word_text);
                 word_title.setText(Main.gui.rules.getWord());


             }else{
                 gui.alert("Word must be not empty or musnt have space");
             }

         }
     };

     ActionListener calc_listener = new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent actionEvent) {
             if(Main.gui.rules.getWordSize()>0){
                 gui.calc_cyk();

                 summary = new JLabel("");

                 summary.setBounds(10,400,500,40);
                 summary.setFont(gui.jFont);
                 summary.setBorder(BorderFactory.createEtchedBorder());
                 if(Table.state_store[Main.gui.rules.getWordSize()-1][0].size()==0){
                     summary.setText("Grammar not acceept this word");
                 }
                 else{
                     summary.setText(gui.summ());
                 }
                 gui.add(summary);

             }

         }
     };

     ChangeListener change_listener = new ChangeListener() {
         @Override
         public void stateChanged(ChangeEvent changeEvent) {

             String _page = String.valueOf(slider.getValue());
             stepLebel.setText("Step : " + String.valueOf(slider.getValue() + 1));
             gui.set_page(Integer.parseInt(_page)); //To change body of generated methods, choose Tools | Templates.
         }
     };





    Boolean isCapitalLetter(String str){
        for(int i=str.length()-1; i>=0; i--) {
            if(!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    Boolean isLowerLetter(String str){
        for(int i=str.length()-1; i>=0; i--) {
            if(Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}


