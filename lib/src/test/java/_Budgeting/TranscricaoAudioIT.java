package _Budgeting;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@SpringBootTest
@EnabledIfEnvironmentVariable(named = "OPENAI_API_KEY", matches = ".+")
public class TranscricaoAudioIT {

    @Autowired
    OpenAiAudioTranscriptionModel transcriptionModel;

    @Test
    void should_transcreverAudio_when_arquivoValido() {
        Resource audio = new ClassPathResource("audio/teste.ogg");

        String texto = transcriptionModel.call(new AudioTranscriptionPrompt(audio))
                .getResult()
                .getOutput();

        assertThat(texto).isNotEmpty();
        System.out.println(texto);
    }
}