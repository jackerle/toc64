import javax.swing.*;
import javax.xml.stream.events.Characters;
import java.awt.*;
import java.util.ArrayList;

public class Table {

    public static Log[][] state_store;
    public static boolean[][] isCheck ;



    JButton jButton[][];
    ArrayList<JPanel> list_calc = new ArrayList<JPanel>();
    JPanel jPanel = null;



    JScrollPane jScrollPane;
    RuleSet rules;
    GUI gui;
    State state = new State();
    JLabel[] jLabel;

    public Table(GUI gui , RuleSet rules){

        this.rules = rules;
        this.gui = gui;


//        JButton jButton = new JButton("A");
//        jButton.setFont(gui.jFont);
//        jButton.setBounds(0,10,60,60);
//        jButton.setPreferredSize(new Dimension(60,60));


        jPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jPanel.setLayout(null);
        jPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        jPanel.setSize(jPanel.getPreferredSize());
//        jPanel.add(jButton);
//        jPanel.setVisible(true);
        jScrollPane = new JScrollPane(jPanel);
        jScrollPane.setBounds(530,30,(GUI.screen_width==GUI.screen_width_lg)?1350:700,(GUI.screen_height==GUI.screen_height_lg)?980:600);
//        jScrollPane.setPreferredSize(new Dimension(50,50));
//        jScrollPane.setVisible(true);

        gui.add(jScrollPane);


    }


    public void draw_cyk(){

        state_store= new Log[rules.getWordSize()][rules.getWordSize()];
        isCheck = new boolean[rules.getWordSize()][rules.getWordSize()];

        jPanel.removeAll();
        int count = 0;
        jButton = new JButton[rules.getWordSize()][rules.getWordSize()];
        jLabel = new JLabel[rules.getWordSize()];

        for (int i= 0; i<rules.getWordSize();i++){



            for(int j = 0 ; j<rules.getWordSize()-count ; j++){

                if(i==0){
                    jLabel[j] = new JLabel(rules.getWord(j),SwingConstants.CENTER);
                    jLabel[j].setBounds(15+j*60,10,60,60);
                    jLabel[j].setFont(gui.jFont);
                    jPanel.add(jLabel[j]);
                }


                jButton[i][j] = new JButton("-");
                jButton[i][j].setBounds(10+j*60,60+i*60,60,60);
                jButton[i][j].setEnabled(false);
                jButton[i][j].setVisible(true);
//                table.jButton[0][0].setBounds(0,120,60,60);
                jButton[i][j].setPreferredSize(new Dimension(60,60));
                jPanel.add(jButton[i][j]);

            }

            count++;
        }
        jPanel.setPreferredSize(new Dimension(count*60 +30 , count*60 + 30));
        jPanel.revalidate();
        jPanel.repaint();

        calc();

    }



    void calc(){


        for(int step = 0 ; step < rules.getWordSize();step++){
            if(step==0){

                for(int i = 0 ; i < rules.getWordSize() ; i++){

//                state_store[0][i].add(rules.isAccept(i));
                    isCheck[step][i] = true;
                    state_store[step][i] = new Log();
                    rules.isAccept(i);
                    state.addState(gui , rules , step,i);

                }
            }

            else {
                System.out.println("here");
                if(step<rules.getWordSize()){

                    for(int i=0;i<rules.getWordSize()-step;i++){
                        isCheck[step][i] = true;
                        state_store[step][i] = new Log();
                        rules.isAccept(step,i);
                        state.addState(gui,rules,step,i);
                    }

                }
            }

        }

        jPanel.revalidate();
        jPanel.repaint();
        jScrollPane.revalidate();
        jScrollPane.repaint();

    }


    public void setPage(int index) {
        jScrollPane.setViewportView(state.get(index));
        jScrollPane.revalidate();
        jScrollPane.repaint();
    }


    public String getSummary(){

        String res = "";
         res += " Start symbol is : "+ state_store[rules.getWordSize()-1][0].get(0);

        return res;

    }



}
