package _Budgeting.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private String local;
    private LocalDate data;

    protected Transacao() {
        // construtor vazio exigido pelo JPA
    }

    public Transacao(String descricao, BigDecimal valor, String local, LocalDate data) {
        this.descricao = descricao;
        this.valor = valor;
        this.local = local;
        this.data = data;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public String getLocal() { return local; }
    public LocalDate getData() { return data; }
}