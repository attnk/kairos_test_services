# Balace Brackets Service
___

### O que é?
Porjeto criado contendo somente uma classe [BalanceBracketsBusiess](https://github.com/attnk/kairos_test_services/blob/master/balance-brackets-service/src/main/java/balancebracketsservice/business/BalanceBracketBusiness.java), onde a mesma possuí um método responsável por validar uma String cotém ou não uma sequencia de "símbolos" válidos.
Esses "símbolos" podem ser () parenteses, [] colchetes ou {} chaves, onde são considerados sequências válidas aquelas que sempre possuem um "símbolo" inniciando sendo encerrado pelo seu par em algum momento, por exemplo:
* ()[]{}, uma sequência válida;
* {[()]}(), outra sequênncia válida;
* ({[}), essa por sua vez é uma sequência innválida pelo fato do colchete [ só ter um deles;
* (], essa também seria uma sequência innválida por començar com parênteses ( porém encerrar com colchete ], assim não formando pares.

### Como utilizar?
Baixe o projeto, e inclua em seu "workspace" de sua IDE de preferêcia.

Para utilizar e testar essa funcionalidade basta somete executar o [teste unitário](https://github.com/attnk/kairos_test_services/blob/master/balance-brackets-service/src/test/java/balancebracketsservice/business/BalanceBracketBusinessTest.java), via JUnit presente no projeto.

Dentro deste teste existem dois métodos de teste que ã orepresenntam nenhum cenário em específico, somentne para um caso em que seja passado uma sequência qualquer válida, e uma sequência qualquer inválida (**basta somennte trocar o valor da variável input**).

```java
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
```