package com.votacao.dto;

/**
 * <p>
 * Classe responsável por representar um objeto de erro na aplicação. <br>
 * O atributo {@code mensagem} ser refere a mensagem principal do erro e o
 * atributo {@code detalhe} é um objeto genérico que se refere à maiores
 * detalhes no erro que ocorreu.
 *
 * <p>Essa classe extende de {@link VotacaoDTO}.
 *
 * @author CJFREITAS
 */
public class VotacaoErro extends VotacaoDTO {
	private static final long serialVersionUID = 737697641750503481L;

	private String mensagem;
	private transient Object detalhe;

	public VotacaoErro() {
		this(null, null);
	}

	public VotacaoErro(final String mensagem, final Object detalhe) {
		super();
		this.mensagem = mensagem;
		this.detalhe = detalhe;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(final String mensagem) {
		this.mensagem = mensagem;
	}

	public Object getDetalhe() {
		return detalhe;
	}

	public void setDetalhe(final Object detalhe) {
		this.detalhe = detalhe;
	}

	@Override
	public String toString() {
		return String.format("UltronErro [mensagem=%s, detalhe=%s]", mensagem, detalhe);
	}

}