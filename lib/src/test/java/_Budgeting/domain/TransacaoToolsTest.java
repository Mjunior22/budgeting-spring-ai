package _Budgeting.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
public class TransacaoToolsTest {

    @Autowired
    TransacaoRepository repository;

    TransacaoTools tools;

    @BeforeEach
    void setUp() {
        tools = new TransacaoTools(repository);
    }

    @Test
    void should_registrarTransacao_when_dadosValidos() {
        String resultado = tools.registrarTransacao("Cinema", new BigDecimal("30.00"), "Shopping");

        assertThat(resultado).contains("Cinema");
        assertThat(repository.findAll()).hasSize(1);
    }

    @Test
    void should_listarTransacoes_when_existemRegistros() {
        repository.save(new Transacao("Mercado", new BigDecimal("50.00"), "Supermercado ABC", LocalDate.now()));
        repository.save(new Transacao("Uber", new BigDecimal("20.00"), "Centro", LocalDate.now()));

        List<String> resultado = tools.listarTransacoes();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0)).contains("Mercado");
    }
}