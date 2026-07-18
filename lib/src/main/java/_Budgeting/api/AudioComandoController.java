// AudioComandoController.java
package _Budgeting.api;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AudioComandoController {

    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final ChatClient chatClient;

    @Autowired
    public AudioComandoController(OpenAiAudioTranscriptionModel transcriptionModel, ChatClient chatClient) {
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatClient;
    }

    @PostMapping("/comandos/audio")
    public String processarComandoDeAudio(@RequestParam("arquivo") MultipartFile arquivo) {
        AudioTranscriptionPrompt prompt = new AudioTranscriptionPrompt(arquivo.getResource());
        String textoTranscrito = transcriptionModel.call(prompt).getResult().getOutput();
        return chatClient.prompt(textoTranscrito).call().content();
    }
}