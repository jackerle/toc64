import javafx.scene.control.Tab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class State extends ArrayList<JPanel> {

    static String result;

    public void addState(GUI gui , RuleSet rules   , int y , int x){

        JButton[][] jButton = new JButton[rules.getWordSize()][rules.getWordSize()];
        JLabel[] jLabel = new JLabel[rules.getWordSize()];
        JPanel jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel.setSize(jPanel.getPreferredSize());





        int count = 0 ;

        for(int i = 0 ; i < rules.getWordSize() ; i++){

            for(int j = 0 ; j < rules.getWordSize()-count ; j++){

                if(i==0){
                    jLabel[j] = new JLabel(rules.getWord(j),SwingConstants.CENTER);
                    jLabel[j].setBounds(15+j*60,10,60,60);
                    jLabel[j].setFont(gui.jFont);
                    jPanel.add(jLabel[j]);
                }

                String display = "";
                if(Table.isCheck[i][j]&&Table.state_store[i][j].size()>0){
                    for(int k = 0 ;k < Table.state_store[i][j].size();k++){
                        display+= Table.state_store[i][j].get(k)+",";
                    }
                }
                else{
                    display = "-";
                }


//                System.out.println(display);
                if (i == rules.getWordSize() - 1 && j == rules.getWordSize() - count - 1) {

                    result = new String(display);
                }

                jButton[i][j] = new JButton((i<y )? display :
                        (i==y && j<=x)? display : "-");

                final int _i = i;
                final int _j = j;

                jButton[i][j].setBounds(10+j*60,60+i*60,60,60);
                jButton[i][j].setEnabled(false);
                jButton[i][j].setVisible(true);
                if(i<y || (i==y && j <=x)){
                    jButton[i][j].setEnabled(true);
                    jButton[i][j].addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            if(Table.state_store[_i][_j].hasNext)
                                RulePanel.summary.setText("From "+Table.state_store[_i][_j].str_left+Table.state_store[_i][_j].str_right);
                            else{
                                RulePanel.summary.setText("From "+Table.state_store[_i][_j].str);
                            }
                        }
                    });
                }

//                table.jButton[0][0].setBounds(0,120,60,60);
                jButton[i][j].setPreferredSize(new Dimension(60,60));

                if(i==y && j ==x){
                    jButton[i][j].setBackground(Color.orange);
                }

                jPanel.add(jButton[i][j]);

            }
            count++;
        }



        this.add(jPanel);

    }

}
