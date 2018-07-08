package balancebracketsservice.business;

/**
 * 
 * @author Adriano
 *
 */
public class BalanceBracketBusiness {

	public boolean checkBracket(String input) {
		boolean result = false;
		
		int countParenthesisBegin = 0;
		int countParenthesisEnd = 0;
		int countParenthesisSequence = 0;
		
		int countSquareBraketBegin = 0;
		int countSquareBraketEnd = 0;
		int countSquareBraketSequence = 0;
		
		int countCurlyBraketsBegin = 0;
		int countCurlyBraketsEnd = 0;
		int countCurlyBraketSequence = 0;
		
		for (char c : input.toCharArray()) {
			
			switch (c) {
			case '(':
				countParenthesisBegin++;
				break;
				
			case ')':
				countParenthesisEnd++;
				break;
				
			case '[':
				countSquareBraketBegin++;
				break;
				
			case ']':
				countSquareBraketEnd++;
				break;
				
			case '{':
				countCurlyBraketsBegin++;
				break;
				
			case '}':
				countCurlyBraketsEnd++;
				break;
			}
		}
		
		countParenthesisSequence = countParenthesisBegin + countParenthesisEnd;
		countSquareBraketSequence = countSquareBraketBegin + countSquareBraketEnd;
		countCurlyBraketSequence = countCurlyBraketsBegin + countCurlyBraketsEnd;
		
		if(countParenthesisSequence%2 == 0 && 
				countSquareBraketSequence%2 == 0 && 
				countCurlyBraketSequence%2 == 0) {
			
			result = true;
		}
		
		return result;
	}

}
