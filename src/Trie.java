import java.util.Arrays;


public class Trie {
    private static final Node root = new Node();
    private static class Node{
        Node[] children;
        boolean endOfWord;

        public Node(){
            children = new Node[26];
            Arrays.fill(children, null);
        }
    }

    public static void insert(String word){
        Node temp = root;
        for (int i=0; i<word.length(); i++){
            int index = word.charAt(i) - 'a';
            if (temp.children[index] == null){
                temp.children[index] = new Node();
            }
            if (i==word.length()-1){
                temp.children[index].endOfWord = true;
            }
            temp = temp.children[index];
        }
    }

    public static boolean search(String key){
        Node temp = root;
        for (int i=0; i<key.length(); i++){
            int index = key.charAt(i) - 'a';
            Node node = temp.children[index];
            if (node == null){
                return false;
            }
            if (i==key.length()-1 && !node.endOfWord){
                return false;
            }

            temp = temp.children[index];
        }
        return true;
    }
    public static void main(String[] args){
        String[] words = {"the", "a", "there", "their", "any"};
        for (String word : words) {
            insert(word); // O(L)
        }

        System.out.println(search("their")); // O(L)
        System.out.println(search("thor"));
        System.out.println(search("there"));
        System.out.println(search("an"));
        System.out.println(search("any"));
    }
}
