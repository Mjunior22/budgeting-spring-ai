package _Budgeting.config;

import _Budgeting.domain.TransacaoTools;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {

    @Bean
    public ChatClient chatClient(AnthropicChatModel chatModel, TransacaoTools transacaoTools) {
        return ChatClient.builder(chatModel)
                .defaultSystem("""
                        Você é um assistente financeiro pessoal que ajuda a registrar e consultar gastos.

                        Regras:
                        - Sempre responda em português, de forma breve e amigável.
                        - Ao registrar uma transação, confirme o valor e a descrição de forma clara.
                        - Se o valor ou a descrição do gasto não estiverem claros no comando do usuário, peça esclarecimento em vez de adivinhar.
                        - Ao listar transações, resuma o total gasto além de listar os itens.
                        - Nunca invente valores ou transações que não foram informadas.
                        """)
                .defaultTools(transacaoTools)
                .build();
    }
}