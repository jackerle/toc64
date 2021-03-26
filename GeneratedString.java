
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MainPC
 */
class GeneratedString {
    
    ArrayList<String> listsOfString = new ArrayList<>();
    Set<Character> setOfAlphabet = new HashSet<>();

    void printAllKLength(Set<Character> set, int k) {
        int n = set.size();
        printAllKLengthRec(set, "", n, k);
    }

    void printAllKLengthRec(Set<Character> set,
            String prefix,
            int n, int k) {

        if (k == 0) {
            listsOfString.add(prefix);
            return;
        }

        for (Character c : set) {

            String newPrefix = prefix + String.valueOf(c);

            printAllKLengthRec(set, newPrefix,
                    n, k - 1);
        }
    }
    
    
    ArrayList<String> process(RuleSet rule,int k){
        for(int i = 0;i<rule.size();i++){
            Rule r = rule.get(i);
            String definiton =r.getDefinition();
            if(definiton.length() == 1){
                Character c = definiton.charAt(0);
                if(c>='a' && c<= 'z'){
                    setOfAlphabet.add(c);
                }
            }
        }
        if(!setOfAlphabet.isEmpty()){
            printAllKLength(setOfAlphabet, k);
        }
        return listsOfString;
    }
    

    
}
