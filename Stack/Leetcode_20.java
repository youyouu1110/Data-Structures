package Stack;
import java.util.Stack;
public class Leetcode_20 {

	public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
        	char c = s.charAt(i);
        	if(c == '(' || c == '[' || c == '{')
        		stack.push(c);
        	else {
        		if(stack.isEmpty())
        			return false;
        		
        		char topChar = stack.pop();
        		if(c == ')' && topChar != '(')
        			return false;
        		if(c == ']' && topChar != '[')
        			return false;
        		if(c == '}' && topChar != '{')
        			return false;
        	}
        }
        return stack.isEmpty();
            
    }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Leetcode_20 test = new Leetcode_20();
		String s = "{[]}" ;
		System.out.println(test.isValid(s));
	}

}
