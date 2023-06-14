package com.votacao.dto;

import java.util.Date;

public class VotacaoVotarDTO {
	
	private int idUsuario;
	
	private String nomeJogador;
	
	private Date dtVotacao;
	
	private String posicaoVotacao;
	
	private int notaJogador;
	
	private String nomeJogadorVotado;
	
	private int idUsuarioVotado;

	public String getNomeJogador() {
		return nomeJogador;
	}

	public void setNomeJogador(String nomeJogador) {
		this.nomeJogador = nomeJogador;
	}

	public Date getDtVotacao() {
		return dtVotacao;
	}

	public void setDtVotacao(Date dtVotacao) {
		this.dtVotacao = dtVotacao;
	}
	

	public String getPosicaoVotacao() {
		return posicaoVotacao;
	}

	public void setPosicaoVotacao(String posicaoVotacao) {
		this.posicaoVotacao = posicaoVotacao;
	}

	public String getNomeJogadorVotado() {
		return nomeJogadorVotado;
	}

	public void setNomeJogadorVotado(String nomeJogadorVotado) {
		this.nomeJogadorVotado = nomeJogadorVotado;
	}

	public int getNotaJogador() {
		return notaJogador;
	}

	public void setNotaJogador(int notaJogador) {
		this.notaJogador = notaJogador;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioVotado() {
		return idUsuarioVotado;
	}

	public void setIdUsuarioVotado(int idUsuarioVotado) {
		this.idUsuarioVotado = idUsuarioVotado;
	}
		
	
}
