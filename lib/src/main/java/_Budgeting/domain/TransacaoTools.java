package _Budgeting.domain;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class TransacaoTools {

    private final TransacaoRepository repository;

    @Autowired
    public TransacaoTools(TransacaoRepository repository) {
        this.repository = repository;
    }

    @Tool(description = "Registra uma nova transação financeira com descrição, valor em reais e local onde ocorreu o gasto")
    public String registrarTransacao(String descricao, BigDecimal valor, String local) {
        Transacao transacao = new Transacao(descricao, valor, local, LocalDate.now());
        repository.save(transacao);
        return "Transação registrada: " + descricao + " no valor de R$ " + valor;
    }

    @Tool(description = "Lista todas as transações financeiras já registradas")
    public List<String> listarTransacoes() {
        return repository.findAll().stream()
                .map(t -> t.getDescricao() + " - R$ " + t.getValor() + " em " + t.getLocal())
                .toList();
    }
}