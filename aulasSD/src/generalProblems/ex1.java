package generalProblems;

import java.util.*;

public class ex1 {

	public static void main(String[] args) {
		Stack<Character> stack = new Stack<Character>();
		Queue<Character> queue = new LinkedList<Character>();
		
		Scanner read = new Scanner(System.in);
		
		String word;
		
		System.out.println("Inserir uma palavra: ");
		word = read.nextLine().split("\\s+")[0];
		
		char c;
		
		for(int i = 0; i < word.length(); i++) {
			c = word.charAt(i);
			stack.push(c);
			queue.add(c);
		}
		
		char x, y;
		
		// check iguality
		while(stack.size() > 0) {
			x = stack.pop();
			y = queue.remove();
			if(x != y) {
				System.out.println("Não é um palíndromo!");
				System.exit(0);
			}
		}
		System.out.println("É um palíndromo!");
	}

}
