package com.logica.financeira.logica.financeira.Services; // Ajustado para 's' minúsculo

import com.logica.financeira.logica.financeira.entities.Transacao;
import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
class FechamentoService {

    private final TransacaoRepository transacaoRepository;

    // Construtor para injeção de dependência (melhor prática que @Autowired solto)
    public FechamentoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    public void gerarRelatorioMensal() {
        // 1. Busca todas as transações do banco
        List<Transacao> fatura = transacaoRepository.findAll();

        // 2. Calcular Total (Ajustado de getValorGasto para getValor)
        BigDecimal totalGeral = fatura.stream()
                .map(Transacao::getValor) // Agora bate com o campo da sua Entity
                .filter(valor -> valor != null) // Segurança contra valores nulos
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("FECHAMENTO MENSAL: R$ " + totalGeral);

        // 3. TOP DESPESAS (Ranking das 3 maiores)
        System.out.println("--- TOP DESPESAS ---");
        List<Transacao> tops = fatura.stream()
                .filter(t -> t.getValor() != null)
                .sorted(Comparator.comparing(Transacao::getValor).reversed())
                .limit(3)
                .collect(Collectors.toList());

        for (Transacao t : tops) {
            // Ajustado para usar os métodos reais da sua classe Transacao
            System.out.println(t.getDescricao() + ": R$ " + t.getValor());
        }
    }
}