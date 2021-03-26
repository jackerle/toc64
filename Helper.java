import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

public class Helper {



    class Backup{
        RuleSet r_backup;
        int id_gen_rules;

        public Backup(RuleSet rules){
            this.r_backup = rules;
            this.id_gen_rules = rules.getCount();
        }
    }





    public void save(RuleSet rules) throws Exception{

        String path;
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.showSaveDialog(null);
        path = f.getSelectedFile().toString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter(path);

        Backup backup = new Backup(rules);
        writer.write(gson.toJson(backup));
        writer.close();

    }

    public void open() throws Exception{



        String path;
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.showSaveDialog(null);
        path = f.getSelectedFile().toString();

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Backup backup = gson.fromJson(bufferedReader, Backup.class);
//        System.out.println(Main.gui.rules.getCount());


        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        model.addColumn("Index");
        model.addColumn("Terminal");
        model.addColumn("Definition");
        Main.gui.rulePanel.jTable.setModel(model);
        Main.gui.rulePanel.jTable.setColumnSelectionAllowed(true);
        Main.gui.rulePanel.jTable.setRowSelectionAllowed(true);

        RuleSet dummy = new RuleSet();

        for (int i = 0 ; i < backup.r_backup.size() ; i++){
            dummy.addRule(backup.r_backup.get(i));
            ((DefaultTableModel)Main.gui.rulePanel.jTable.getModel()).addRow(new Object[]{ i,  backup.r_backup.get(i).getTerminal()  , backup.r_backup.get(i).getDefinition() });
        }

        Main.gui.setRules(dummy);
        Main.gui.rulePanel.rules = dummy;
        Main.gui.table.rules = dummy;
        Main.gui.revalidate();
        Main.gui.repaint();
        Main.gui.rulePanel.scrollPane.revalidate();
        Main.gui.rulePanel.scrollPane.repaint();
        System.out.println("asd");

    }

}
