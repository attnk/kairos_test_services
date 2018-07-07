package balancebracketsservice.business;

/**
 * 
 * @author Adriano
 *
 */
public class BalanceBracketBusiness {

	public boolean checkBracket(String input) {
		boolean result = false;
		
		int countParenthesis = 0;
		int countSquareBraket = 0;
		int countCurlyBrakets = 0; 
		
		for (char c : input.toCharArray()) {
			if(c == '(' || c == ')') {
				countParenthesis++;
			} else if(c == '[' || c == ']' ) {
				countSquareBraket++;
			} else if(c == '{' || c == '}') {
				countCurlyBrakets++;
			}
		}
		
		if(countParenthesis % 2 == 0 && 
			countSquareBraket%2 == 0 && 
			countCurlyBrakets%2 == 0) {
			
			result = true;
		}
		
		return result;
	}

}
