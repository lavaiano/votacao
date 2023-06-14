package com.votacao.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.votacao.jdbc.JdbcDomain;

@Entity
public class Votacao extends JdbcDomain {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private long idVotacao;
	
	private int idUsuario;
	
	private String nmPosicao;
	
	private int idUsuarioVoto;
	
	private int notaJogador;
	
	private LocalDateTime dtInclusao;
	
	private Date dtVotacao;

	public long getIdVotacao() {
		return idVotacao;
	}

	public void setIdVotacao(long idVotacao) {
		this.idVotacao = idVotacao;
	}

	
	public String getNmPosicao() {
		return nmPosicao;
	}

	public void setNmPosicao(String nmPosicao) {
		this.nmPosicao = nmPosicao;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdUsuarioVoto() {
		return idUsuarioVoto;
	}

	public void setIdUsuarioVoto(int idUsuarioVoto) {
		this.idUsuarioVoto = idUsuarioVoto;
	}

	public int getNotaJogador() {
		return notaJogador;
	}

	public void setNotaJogador(int notaJogador) {
		this.notaJogador = notaJogador;
	}

	public LocalDateTime getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(LocalDateTime dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Date getDtVotacao() {
		return dtVotacao;
	}

	public void setDtVotacao(Date dtVotacao) {
		this.dtVotacao = dtVotacao;
	}	

}
