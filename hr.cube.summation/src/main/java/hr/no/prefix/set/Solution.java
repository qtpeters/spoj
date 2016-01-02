import java.io.*;
import java.util.*;
import java.io.*;



public class Solution {
    
    public static class Node {
        public char c;
        public boolean isLastLetterInWord;
        //public List<char> children;
        public HashMap<Character, Node> children;
        public Node(char c, boolean isLastLetterInWord) {
            this.c = c;
            this.isLastLetterInWord = isLastLetterInWord;
            this.children = new HashMap<Character, Node>();
        }

        public void addChild(Node child) {
            children.put(new Character(child.c), child);
        }

        public boolean hasChild(char c) {
            return children.containsKey(new Character(c));
        }

        public Node getChild(char c) {
            return children.get(new Character(c));
        }
    }

    public static Node rootNode = new Node('x', false);
    public static int numStrings;

    private static String process() {

        String input;
        String foundPrefixString = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            while((input=br.readLine())!=null) {
                char c;
                Node node = rootNode;
                for (int i = 0; i < input.length(); i++) {
                    c = input.charAt(i);
                    if (node.hasChild(c) ) {
                        if (i == input.length() - 1) {
                            foundPrefixString = input;
                            break;
                        }
                        // Set node to the child
                        node = node.getChild(c);
                    } else {
                        // Check if this node is the last letter in a word, meaning that 
                        // word was a prefix to the current word being added to the tree
                        if (node.isLastLetterInWord) {
                            foundPrefixString = input;
                            break;
                        }  
                        // Add the node as a child
                        Node child = new Node(c, i == input.length() -1);
                        node.addChild(child);
                        node = child;
                    }
                }

                if (foundPrefixString != null) break;    

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return foundPrefixString;
    }

    public static void main( String [] args ) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        String str = null;
        if ( ( str = process() ) != null ) {
            System.out.println( "BAD SET" );
            System.out.println( str );
        } else {
            System.out.println( "GOOD SET" );
        }
    }

}
