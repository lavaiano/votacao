package com.votacao.exception;

import com.votacao.dto.VotacaoErro;

/**
 * <p>
 * Classe que serve como abstração para as outras exceções do sistema
 *
 * <p>
 * Todas excecoes dos modulos deverao extender esta classe.
 *
 * @author lavaiano
 */
public abstract class VotacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final VotacaoErro erro;

	public VotacaoException() {
		super();
		erro = new VotacaoErro(null, null);
	}

	public VotacaoException(final String mensagem, final Throwable causa) {
		super(mensagem, causa);
		erro = new VotacaoErro(mensagem, causa.getStackTrace());
	}

	public VotacaoException(final String mensagem) {
		this(mensagem, mensagem);
	}

	public VotacaoException(final String mensagem, final Object detalhe) {
		super(mensagem);
		erro = new VotacaoErro(mensagem, detalhe);
	}

	public VotacaoErro getErro() {
		return erro;
	}

	@Override
	public String toString() {
		return String.format("UltronException [erro=%s]", erro);
	}
}