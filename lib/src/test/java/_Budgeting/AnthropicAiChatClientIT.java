package _Budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_API_KEY", matches = ".+")
public class AnthropicAiChatClientIT {
	@Autowired
	AnthropicChatModel chatModel;
	
	@Test
	void should_executeSum_when_prompted() {
		
		var chatClient = ChatClient.builder(chatModel)
				.defaultSystem("Você é um matematico")
				.build();
		
		var resposta = ((ChatClient) chatClient).prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado.")
				.call().content();
		
		assertThat(resposta).contains("0");
		
		System.out.println(resposta);
	}

}
