import java.util.ArrayList;

public class RuleSet extends ArrayList<Rule> {

    private int count = -1;
    private String word = "";


    public void addRule(Rule r ){

        this.add(r);
        count++;
        System.out.println(this.toString());

    }

    public String toString (){

        String msg = "rule = { \n";

        for(int i = 0;i<this.size();i++){
            msg+=get(i).getTerminal()+" -> "+get(i).getDefinition()+" ,\n";
        }

        msg+="} \n ---------------------------------";

        return msg;
    }

    public int getCount() {
        return count;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word_count) {
        this.word = word_count;
    }

    public int getWordSize(){
        return this.word.length();
    }

    public String getWord(int index){

        return  Character.toString(getWord().charAt(index));
    }

    public void isAccept(int index){


        for(int j = 0 ; j < size() ; j++){
            if(get(j).isAccept(getWord(index))){
//                res+=get(j).getTerminal()+",";
                Table.state_store[0][index].add(get(j).getTerminal());
            }
        }


    }

    public void isAccept(int row , int index){

        int left = row; int right = 0;
        System.out.println("accept row "+left);

        for(int i = 0 ; i < row  ; i++){

            for(int j = 0 ; j < Table.state_store[left-1][index].size();j++){
                for(int k = 0 ; k < Table.state_store[right][index+left].size();k++){

                    System.out.println("["+Table.state_store[left-1][index].get(j)+" "+Table.state_store[right][index+left].get(k));

                    for(int l = 0 ; l < size() ; l++){
                        if(get(l).isAccept(Table.state_store[left-1][index].get(j)+Table.state_store[right][index+left].get(k))){
                            System.out.println("Yeah");
                            Table.state_store[row][index].add(get(l).getTerminal());
                        }
                    }
                }
            }

            left-=1;
            right+=1;
//            for(int j = 0 ; j < size() ; j++){
//                if(get(j).isAccept()){
//                    Table.state_store[0][index].add(get(j).getTerminal());
//                }
//            }

        }

    }


}
