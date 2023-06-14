package com.votacao.util;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import com.votacao.exception.ValidacaoException;

public class DateUtils {

	public static final String FORMATO_LOCAL_DATE = "yyyy-MM-dd";
	public static final String FORMATO_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";

	private DateUtils() {
		super();
	}

	public static LocalDateTime createLocalDateTime(final String timeStampStr) {
		final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_LOCAL_DATE_TIME);
		return LocalDateTime.parse(timeStampStr.substring(0, FORMATO_LOCAL_DATE_TIME.length() - 2), formatter);
	}

	/**
	 * Retorna a quantidade total de dias contidos entre um periodo inicial e final
	 */
	public static Long diasEntre(final Temporal periodoInicial, final Temporal periodoFinal) {
		return recorrenciasEntre(periodoInicial, periodoFinal, ChronoUnit.DAYS);
	}

	/**
	 * Retorna a quantidade total de meses contidos entre um periodo inicial e final
	 */
	public static Long mesesEntre(final Temporal periodoInicial, final Temporal periodoFinal) {
		return recorrenciasEntre(periodoInicial, periodoFinal, ChronoUnit.MONTHS);
	}

	public static long recorrenciasEntre(final Temporal periodoInicial, final Temporal periodoFinal,
			final ChronoUnit tipoRecorrencia) {
		if (periodoInicial == null || periodoFinal == null || tipoRecorrencia == null) {
			throw new ValidacaoException();
		}
		return tipoRecorrencia.between(periodoInicial, periodoFinal);
	}

	/**
	 * <p>
	 * Cria uma data baseada no dia e data passados, caso a data criada esteja
	 * inválida, gera uma data com o último dia do mês.
	 *
	 * @param data
	 * @param dia
	 * @return
	 */
	public static LocalDate criarDataValida(final LocalDate data, final int dia) {
		try {
			return data.withDayOfMonth(dia);
		} catch (final DateTimeException e) {
			return ultimoDiaDoMesAnterior(data.getMonth().getValue(), data.getYear());
		}
	}

	/**
	 * <p>
	 * Cria uma data baseada no dia, mes e ano passados, caso a data criada esteja
	 * inválida, gera uma data com o último dia do mês.
	 *
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return
	 */
	public static LocalDate criarDataValida(final int ano, final int mes, final int dia) {
		try {
			return LocalDate.of(ano, mes, dia);
		} catch (final Exception e) {
			return ultimoDiaDoMesAnterior(mes, ano);
		}
	}

	/**
	 * <p>
	 * Dado o mês e ano, retorna uma {@link LocalDate data} formado pelo mês o ano e
	 * o último dia daquele mês.
	 *
	 * @param mes
	 * @param ano
	 * @return
	 */
	private static LocalDate ultimoDiaDoMesAnterior(final int mes, final int ano) {
		return LocalDate.of(ano, mes, 1).plusMonths(1).minusDays(1);
	}

	/**
	 * <p>
	 * Cria um objeto do tipo LocalDate de acordo com os parâmetros informados. Caso
	 * não seja uma data válida, o sistema tenta criar uma data utilizando o último
	 * dia do mês informado.
	 * 
	 * <p>
	 * Se o mês não for válido, uma exceção é lançada.
	 * 
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return
	 */
	public static LocalDate createLocalDate(int ano, int mes, int dia) {
		try {
			return LocalDate.of(ano, mes, dia);
		} catch (Exception e) {
			if (mes >= 1 && mes <= 12) {
				return recuperarUltimoDiaDoMes(mes, ano);
			} else {
				throw new ValidacaoException(StringUtils.getMensagemPadrao("exception.enum.codigoInvalido", mes));
			}
		}
	}

	/**
	 * <p>
	 * Cria um objeto do tipo LocalDate de acordo com os parâmetros informados. Caso
	 * não seja uma data válida, o sistema tenta criar uma data utilizando o último
	 * dia do mês informado.
	 * 
	 * @param ano
	 * @param mes
	 * @param dia
	 * @return
	 */
	public static LocalDate createLocalDate(LocalDate data, int dia) {
		try {
			return data.withDayOfMonth(dia);
		} catch (DateTimeException e) {
			return recuperarUltimoDiaDoMes(data.getMonth().getValue(), data.getYear());
		}
	}

	private static LocalDate recuperarUltimoDiaDoMes(int mes, int ano) {
		return LocalDate.of(ano, mes, 1).plusMonths(1).minusDays(1);
	}

	public static LocalDate recuperarUltimoDiaDoMes(LocalDate data) {
		return data.plusMonths(1).minusDays(1);
	}

	public static boolean isDiaDeSemana(final LocalDate data) {
		return !(data.getDayOfWeek().equals(SATURDAY) || data.getDayOfWeek().equals(SUNDAY));
	}
}