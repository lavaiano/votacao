package com.votacao.exception;

import org.springframework.web.client.HttpClientErrorException;

import com.votacao.util.UltronConstants;

public class ValidacaoException extends VotacaoException {
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Construtor que já possuí uma mensagem padrão
	 */
	public ValidacaoException() {
		getErro().setMensagem(UltronConstants.RESPONSE_400);
		getErro().setDetalhe(null);
	}

	public ValidacaoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ValidacaoException(final String message) {
		super(message);
	}

	public ValidacaoException(final Object detalhe) {
		super(UltronConstants.RESPONSE_400, detalhe);
	}

	public ValidacaoException(final Throwable cause) {
		super(UltronConstants.RESPONSE_400, cause);
	}

	public ValidacaoException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

	public ValidacaoException(final HttpClientErrorException exception) {
		super(exception.getMessage(), exception.getResponseBodyAsString());
	}
}