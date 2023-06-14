package com.votacao.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public abstract class JdbcCrudRepository<T extends JdbcDomain, I extends Number>
extends JdbcRepository implements JdbcCrudRepositoryI<T, I> {
	
	protected abstract Class<T> getTClass();

	protected abstract String criarQuery();

	protected abstract String atualizarQuery();

	protected abstract String deletarQuery();

	protected abstract String recuperarPorIdQuery();

	protected abstract String listarQuery();

	protected abstract String listarContagemQuery();

	protected abstract String recuperarListagemPorIdQuery();

	protected abstract String obterIdQuery();
	
	@SuppressWarnings("unchecked")
	
	@Override
	public I criar(final T entidade) {
		final KeyHolder kh = new GeneratedKeyHolder();
		npjt.update(criarQuery(), new BeanPropertySqlParameterSource(entidade), kh);
		try {
			return (I) kh.getKey();
		} catch (final DataRetrievalFailureException e) {
			return null;
		}
	}

	
	@Override	
	public void atualizar(final T entidade) {
		npjt.update(atualizarQuery(), new BeanPropertySqlParameterSource(entidade));
	}

	@Override
	
	public void atualizarLista(final List<T> ls) {
		npjt.batchUpdate(atualizarQuery(), SqlParameterSourceUtils.createBatch(ls.toArray()));
	}

	@Override
	
	public void deletar(final I id, final String nmUsuario) {
		final MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("id", id);
		parametros.addValue("nmUsuario", nmUsuario);

		npjt.update(deletarQuery(), parametros);
	}

	@Override
	
	public Optional<T> recuperarPorId(final I id) {
		final MapSqlParameterSource parametros = new MapSqlParameterSource();
		parametros.addValue("id", id);

		return npjt.query(recuperarPorIdQuery(), parametros, BeanPropertyRowMapper.newInstance(getTClass())).stream()
				.findFirst();
	}

	@Override
	
	public Integer obterId() {
		final MapSqlParameterSource parametros = new MapSqlParameterSource();
		return npjt.queryForObject(obterIdQuery(), parametros, Integer.class);
	}


}
