package balancebracketsservice.business;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BalanceBracketBusinessTest {
	
	private BalanceBracketBusiness business;
	
	@Before
	public void init() {
		business = new BalanceBracketBusiness();
	}
	
	// teste para o caso quando receber uma sequência simples válida: ()[]{}
	@Test
	public void shouldReturnTrueWhenReceiveValidSimpleBracketSequence() {
		// GIVEN
		String input = "()[]{}";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertTrue(result);
	}
	
	// teste para o caso quando receber uma sequência simples e composta válida: {[()]}
	@Test
	public void shouldReturnTrueWhenReceiveValidCompositionOfSimpleBracketSequence() {
		// GIVEN
		String input = "{[()]}";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertTrue(result);
	}
	
	// teste para o caso quando receber uma sequência complexa e composta válida: {[()]}[]{({})}[[()]](){}
	@Test
	public void shouldReturnTrueWhenReceiveValidCompositionOfComplexBracketSequence() {
		// GIVEN
		String input = "{[()]}[]{({})}[[()]](){}";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertTrue(result);
	}
	
	
	// teste para o caso quando receber uma sequência simples e inválida: []{()
	@Test
	public void shouldReturnFalseWhenReceiveInvalidSimpleBracketSequence() {
		// GIVEN
		String input = "[]{()";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertFalse(result);
	}
	
	// teste para o caso quando receber uma sequência simples e composta inválida: {[{[)}]}
	@Test
	public void shouldReturnFalseWhenReceiveInvalidCompositionOfSimpleBracketSequence() {
		// GIVEN
		String input = "{[{[)}]}";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertFalse(result);
	}
	
	// teste para o caso quando receer uma sequência complexa e composta inválida: (({[]}(])
	@Test
	public void shouldReturnFalseWhenReceiveInvalidCompositionOfComplexBracketSequence() {
		// GIVEN
		String input = "(({[]}(])";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertFalse(result);
	}
	
	// --- TESTES GENÉRICOS PARA VALIDAR QUALQUER ENTRADA ---
	@Test
	public void shouldReturnTrueWhenAnyBracketSequenceIsValid() {
		// GIVEN
		String input = "()";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertTrue(result);
	}
	
	@Test
	public void shouldReturnFalseWhenAnyBracketSequenceIsInvalid() {
		// GIVEN
		String input = "(";
		
		// WHEN
		boolean result = business.checkBracket(input);
		
		// THEN
		assertFalse(result);
	}
}
