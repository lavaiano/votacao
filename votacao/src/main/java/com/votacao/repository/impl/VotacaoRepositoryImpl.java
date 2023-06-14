package com.votacao.repository.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.votacao.dto.VotacaoVotarDTO;
import com.votacao.jdbc.JdbcRepository;
import com.votacao.model.Votacao;
import com.votacao.repository.VotacaoRepository;

@Repository
@PropertySource("classpath:query/votacao-sql.properties")
public class VotacaoRepositoryImpl extends JdbcRepository implements VotacaoRepository {
	
	private final Logger log = LogManager.getLogger();

	
	@Value("${SPS.VOTACAO.POR_IDVOTACAO}")
	private String listarPorIdVotacao;
	
	@Value("${SPS.VOTACAO.POR_IDJOGADOR}")
	private String listarPorIdJogador;
	
	@Value("${SPI.VOTACAO.CRIAR}")
	private String criar;
	
	@Override
	public Optional<Votacao> recuperarPorId(long id) {

		final MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("id", id);
		return npjt.query(listarPorIdVotacao, parametros, BeanPropertyRowMapper.newInstance(Votacao.class)).stream()
				.findFirst();
	}

	@Override
	public List<VotacaoVotarDTO> recuperarPorJogador(long idUsuario) {
		
		final MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("idUsuario", idUsuario);
		
		return npjt.query(listarPorIdJogador, parametros,
				BeanPropertyRowMapper.newInstance(VotacaoVotarDTO.class));

	}

	@Override
	public List<Votacao> criar(List<Votacao> votacaoList) {
		
		for (int i = 0; i < votacaoList.size(); i++) {		
		
		{
			log.info("TESTE CRIAÇÃO DE VOTOS" +  votacaoList.get(i).getDtVotacao());
			final KeyHolder kh = new GeneratedKeyHolder();
			npjt.update(criar, new BeanPropertySqlParameterSource(votacaoList.get(i)), kh);
			try {
				votacaoList.get(i).setIdVotacao(Objects.requireNonNull(kh.getKey()).longValue());
			} catch (final DataRetrievalFailureException e) {
				return null;
			}
		}
		
		}
		return votacaoList;
	}

}
