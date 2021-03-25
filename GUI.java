import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    static int screen_width_lg = 1920;
    static int screen_height_lg = 1080;
    static int screen_width_m = 1280;
    static int screen_height_m = 720;



    public static Font jFont = new Font("Dialog", Font.PLAIN, 18);


    // select your resolution
    static int screen_width = screen_width_lg;
    static int screen_height = screen_height_lg;
//
//    static int screen_height = screen_height_m;
//    static int screen_width = screen_width_m;
    //------------------------



    private int font_size = 16;
    public RuleSet rules;
    public Table table;
    RulePanel rulePanel;


    GUI() throws Exception{
        super("CYK");
        rules = new RuleSet();

        UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        setSize(screen_width,screen_height);
        setBackground(Color.GRAY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //method to Execute Container here
//        init_rule_text_area();
        rulePanel = new RulePanel(this, rules);
        table = new Table(this,rules);

        setLayout(null);
        setVisible(true);
    }


    /**
     * Rule Input
     */

    public void calc_cyk(){

        table.draw_cyk();
        System.out.println(table.jButton.length+ " "+rules.getWordSize());


        revalidate();
        repaint();

    }

    public void set_page(int index){
        table.setPage(index);
    }



    void alert(String msg){
        JOptionPane.showMessageDialog(null,msg,"Error",JOptionPane.ERROR_MESSAGE);
    }
    void success(String msg){
        JOptionPane.showMessageDialog(null,msg,"Success",JOptionPane.PLAIN_MESSAGE);
    }
}
