package com.votacao.exception;

import com.votacao.util.UltronConstants;

public class NaoEncontradoException extends VotacaoException {
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Construtor que já possuí uma mensagem padrão
	 */
	public NaoEncontradoException() {
		getErro().setMensagem(UltronConstants.RESPONSE_204);
		getErro().setDetalhe(null);
	}

	public NaoEncontradoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public NaoEncontradoException(final String message) {
		super(message);
	}

	public NaoEncontradoException(final Object detalhe) {
		super(UltronConstants.RESPONSE_204, detalhe);
	}

	public NaoEncontradoException(final Throwable cause) {
		super(UltronConstants.RESPONSE_204, cause);
	}

	public NaoEncontradoException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

}
