package _Budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_API_KEY", matches = ".+")

public class AnthropicAIChatModelIT {
	@Autowired
	AnthropicChatModel chatModel;

	@Test
	void should_receiveResponse_when_chatModelIsCalled() {
			var resposta = chatModel.call("Gere um registro de budgeting, com descricao de gasto, valor em reais e local");
			
			assertThat(resposta).isNotEmpty();
			
			System.out.println(resposta);
	}

}
