package _Budgeting.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class TransacaoRepositoryTest {

    @Autowired
    TransacaoRepository repository;

    @Test
    void should_salvarEBuscarTransacao_when_dadosValidos() {
        Transacao transacao = new Transacao("Mercado", new BigDecimal("50.00"), "Supermercado ABC", LocalDate.now());

        repository.save(transacao);

        List<Transacao> transacoes = repository.findAll();

        assertThat(transacoes).hasSize(1);
        assertThat(transacoes.get(0).getDescricao()).isEqualTo("Mercado");
        assertThat(transacoes.get(0).getValor()).isEqualByComparingTo("50.00");
    }
}