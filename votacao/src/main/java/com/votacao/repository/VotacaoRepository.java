package com.votacao.repository;

import java.util.List;
import java.util.Optional;

import com.votacao.dto.VotacaoDTO;
import com.votacao.model.Votacao;

public interface VotacaoRepository {
	
	Optional<Votacao> recuperarPorId(final long id);
	
	List<VotacaoDTO> recuperarPorJogador(final long idJogador);
	
	List<Votacao> criar(final List<Votacao> votacao);

}
