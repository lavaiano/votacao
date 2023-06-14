package com.votacao.mapper;

import static java.time.LocalDateTime.now;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.votacao.dto.VotacaoDTO;
import com.votacao.model.Votacao;

@Component
public class VotacaoMapper {
	
	public List<Votacao> mapear(List<VotacaoDTO> votacaoDTO) {
		
				List<Votacao> votacaoList = new ArrayList<>();
		
		
		for (VotacaoDTO votacaoDTO2 : votacaoDTO) {
			
			Votacao votacaoAdd = new Votacao();
			
			votacaoAdd.setIdUsuario(votacaoDTO2.getIdUsuario());
			votacaoAdd.setNmPosicao(votacaoDTO2.getPosicaoVotacao());
			votacaoAdd.setIdUsuarioVoto(votacaoDTO2.getIdUsuarioVotado());
			votacaoAdd.setNotaJogador(votacaoDTO2.getNotaJogador());
			votacaoAdd.setDtInclusao(now());
			votacaoAdd.setDtVotacao(votacaoDTO2.getDtVotacao());
			
			votacaoList.add(votacaoAdd);			
			
		}		
		
		return votacaoList;
	}

}
