package com.votacao.util;

import static com.votacao.util.StringUtils.getMensagemPadrao;

public class UltronConstants {

	private UltronConstants() {
		super();
	}

	/*
	 * Mensagens padrões de Response de acordo com o código HTTP retornado
	 */
	public static final String RESPONSE_200 = getMensagemPadrao("response.code200");
	public static final String RESPONSE_201 = getMensagemPadrao("response.code201");
	public static final String RESPONSE_204 = getMensagemPadrao("response.code204");

	public static final String RESPONSE_304 = getMensagemPadrao("response.code304");

	public static final String RESPONSE_400 = getMensagemPadrao("response.code400");
	public static final String RESPONSE_401 = getMensagemPadrao("response.code401");
	public static final String RESPONSE_404 = getMensagemPadrao("response.code404");

	public static final String RESPONSE_500 = getMensagemPadrao("response.code500");

	public static final String EXCECAO_ARGUMENTO_INVALIDO_MSG = "Erro de parâmetro(s) inválido(s).";
	public static final String EXCECAO_ARGUMENTO_INVALIDO_DTL = "%s; Campo = '%s'; Valor = '%s'.";

	public static final String LISTA_VAZIA_MSG = "A lista está vazia.";
	public static final String ENTIDADE_NAO_ENCONTRADA_MSG = "Não foi encontrada nenhuma entidade.";

	public static final String ID_TRANSACAO = "nmIdTransacao";
	public static final String SPAN_ID = "X-B3-SpanId";
	public static final String TOKEN = "nmIdSessao";
	public static final String AUTHORIZATION = "Authorization";
	public static final String REPROCESSAMENTO = "Reprocessamento";
	public static final String USUARIO = "userName";

	public static final String PARAMETRO_INVALIDO = getMensagemPadrao("erro.validador.parametro.invalido");

	/*
	 * Constantes SQS
	 */
	public static final String ATRIBUTO_ANEXO = "Anexo";
	public static final String ATRIBUTO_DOMINIO = "Dominio";
	public static final String ATRIBUTO_DESTINO = "Destino";
	public static final String ATRIBUTO_METODO = "Metodo";
	public static final String ATRIBUTO_TRACE_ID = "TraceId";
	public static final String ATRIBUTO_PORTA = "Porta";
	public static final String ATRIBUTO_TENTATIVA = "Tentativa";

	/*
	 * Constantes Braspag
	 */

	public static final String PAYMENT_PROVIDER_SANDBOX = "Simulado";
	public static final String PAYMENT_PROVIDER_PROD = "Cielo30";
	public static final String PAYMENT_TYPE = "CreditCard";
	public static final String PAYMENT_COUNTRY = "BRA";
	public static final String PAYMENT_SOFTDESCRIPTOR = "SEGURO RESIDENCIAL BRASILSEG";
	public static final String PAYMENT_INTERESSE = "ByMerchant";

	/*
	 * Constantes ambiente
	 */

	public static final String AMBIENTE_DEV = "DEV";
	public static final String AMBIENTE_HML = "HML";
	public static final String AMBIENTE_PROD = "PRD";

	/*
	 * Prazos
	 */
	public static final Long PRAZO_DIAS_CANCELAMENTO_ARREPENDIMENTO = 7L;


	/**
	 * @deprecated Não é necessário utilizar a URL do SQS ao manipular as filas.
	 *             Apenas as credenciais utilizadas em UltronApplication#sqsAws já
	 *             bastam.
	 */
	@Deprecated
	public static final String SQS_URL = "https://sqs.sa-east-1.amazonaws.com/533865883733/";
	public static final String NOME_PROJETO = "NOME_PROJETO";
}
