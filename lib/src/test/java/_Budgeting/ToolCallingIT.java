package _Budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_API_KEY", matches = ".+")
public class ToolCallingIT {
	@Autowired
	AnthropicChatModel chatModel;
	
	static class MathTools{
		@Tool(description = "soma dois numeros inteiros, a + b")
		public int sum(int a, int b) {
			return a + b;
		}
		@Tool(description = "subtrai dois numeros inteiros, a - b")
		public int diff(int a, int b) {
			return a - b;
		}
	}
	
	@Test
	void should_executeSum_when_prompted() {
		
		var chatClient = ChatClient.builder(chatModel)
				.defaultSystem("Você é um matematico")
				.defaultTools(new MathTools())
				.build();
		
		var resposta = ((ChatClient) chatClient).prompt("Some 10 mais 20. Depois subtraia 30 do resultado anterior. Exiba apenas o resultado.")
				.call().content();
		
		assertThat(resposta).contains("0");
		
		System.out.println(resposta);
	}

}
