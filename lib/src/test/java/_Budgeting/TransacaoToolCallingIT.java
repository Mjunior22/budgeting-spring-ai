package _Budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import _Budgeting.domain.TransacaoTools;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "ANTHROPIC_API_KEY", matches = ".+")
public class TransacaoToolCallingIT {

    @Autowired
    AnthropicChatModel chatModel;

    @Autowired
    TransacaoTools transacaoTools;

    @Test
    void should_registrarTransacao_when_prompted() {
        var chatClient = ChatClient.builder(chatModel)
                .defaultSystem("Você é um assistente financeiro que registra transações de orçamento pessoal.")
                .defaultTools(transacaoTools)
                .build();

        var resposta = chatClient.prompt("Gastei 50 reais no mercado hoje").call().content();

        assertThat(resposta).isNotEmpty();
        System.out.println(resposta);
    }
}