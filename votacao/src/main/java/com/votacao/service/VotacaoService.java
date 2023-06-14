package com.votacao.service;

import java.util.List;
import java.util.Optional;

import com.votacao.dto.VotacaoVotarDTO;
import com.votacao.model.Votacao;

public interface VotacaoService {
	
	 Optional<Votacao> recuperarPorId(long id);
	 
	 List<VotacaoVotarDTO> recuperarPorJogador(long idUsuario);
	 
	 List<Votacao> criar(List<Votacao> votacaoList) throws Exception;

}
