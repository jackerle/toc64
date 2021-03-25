public class Rule {

    private String terminal;
    private String definition;



    private String[] definition_list;

    public Rule(String terminal, String definition) {
        this.terminal = terminal;
        this.definition = definition;
        this.definition_list = definition.split("[|]");
        System.out.println("Rule added Terminal= {"+terminal+ "} Definition={"+definition+"} List={"+definition_list.length+"}");

    }

    public String getTerminal() {
        return terminal;
    }



    public String getDefinition() {
        return definition;
    }

    public String[] getDefinition_list() {
        return definition_list;
    }

    public boolean isAccept(String s){

        for(int i = 0 ; i < definition_list.length ; i++){

            if (s.equals(definition_list[i])){
                return true;
            }

        }
        return false;
    }
}
