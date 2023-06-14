package com.votacao.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.votacao.dto.VotacaoVotarDTO;
import com.votacao.model.Votacao;
import com.votacao.repository.VotacaoRepository;
import com.votacao.service.VotacaoService;

@Service
public class VotacaoServiceImpl implements VotacaoService {
	
	@Autowired
	private VotacaoRepository repositorio;
	
	
	@Override
	public Optional<Votacao> recuperarPorId(long id) {
		return repositorio.recuperarPorId(id);
	}
	
	@Override
	public List<VotacaoVotarDTO> recuperarPorJogador(long idUsuario) {
		return repositorio.recuperarPorJogador(idUsuario);
	}	
	
	@Override
	public List<Votacao> criar(List<Votacao> votacaoList) throws Exception {
		
		if(isVotacaoRepetida(votacaoList)){
			//Fixme: Retornar erro para a fonte chamadora.
			throw new Exception("Erro ao tentar votar em mais de um jogador");
		}
		return repositorio.criar(votacaoList);
	}
	
	private boolean isVotacaoRepetida(List<Votacao> votacaoList) {
		for (int i = 0, j = 0; j <= votacaoList.size(); i++) {

			if (i == votacaoList.size()) {
				j++;
				i = 0;
				if (j == votacaoList.size()) {
					break;
				}
			}

			if (compararString(votacaoList.get(j).getNmPosicao(), votacaoList.get(i).getNmPosicao()) && i != j) {
				System.out.println("Votação duplicada");
				return true;
			}
		}
		return false;
	}

	private boolean compararString(String posicao1, String posicao2) {

		return posicao1.equals(posicao2);

	}
	

}
