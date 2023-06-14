package com.votacao.exception;

import org.springframework.web.client.HttpServerErrorException;

import com.votacao.util.UltronConstants;

public class ServicoException extends VotacaoException {
	private static final long serialVersionUID = 1L;

	/**
	 * <p>
	 * Construtor que já possui uma mensagem padrão
	 */
	public ServicoException() {
		getErro().setMensagem(UltronConstants.RESPONSE_500);
		getErro().setDetalhe(null);
	}

	public ServicoException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ServicoException(final String message) {
		super(message);
	}

	public ServicoException(final Object detalhe) {
		super(UltronConstants.RESPONSE_500, detalhe);
	}

	public ServicoException(final Throwable cause) {
		super(UltronConstants.RESPONSE_500, cause);
	}

	public ServicoException(final String mensagem, final Object detalhe) {
		super(mensagem, detalhe);
	}

	public ServicoException(final HttpServerErrorException exception) {
		super(exception.getMessage(), exception.getResponseBodyAsString());
	}
}