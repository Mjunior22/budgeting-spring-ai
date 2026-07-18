// ComandoController.java
package _Budgeting.api;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ComandoController {

    private final ChatClient chatClient;

    @Autowired
    public ComandoController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @PostMapping("/comandos")
    public String processarComando(@RequestBody String comando) {
        return chatClient.prompt(comando).call().content();
    }
}