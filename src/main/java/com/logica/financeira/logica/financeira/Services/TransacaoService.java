import com.logica.financeira.logica.financeira.Repositories.TransacaoRepository;
import com.logica.financeira.logica.financeira.dtos.response.TopDespesaDTO;
import com.logica.financeira.logica.financeira.entities.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository transacaoRepository;

    // MÉTODO DE FECHAMENTO MENSAL E RANKING
    public List<TopDespesaDTO> obterTopDespesasDoMes(Long usuarioId, YearMonth anoMes) {
        LocalDate inicio = anoMes.atDay(1);
        LocalDate fim = anoMes.atEndOfMonth();

        // 1. Busca do banco
        List<Transacao> todas = transacaoRepository.findByUsuarioIdAndDataBetween(usuarioId, inicio, fim);

        // 2. Transforma em DTO, ordena pelo valor e pega as 5 maiores
        return todas.stream()
                .filter(t -> t.getTipoTransacao() == TipoTransacao.DESPESA)
                .sorted(Comparator.comparing(Transacao::getValor).reversed())
                .limit(5)
                .map(t -> new TopDespesaDTO(t.getDescricao(), t.getValor()))
                .collect(Collectors.toList());
    }
}