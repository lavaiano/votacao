package com.votacao.jdbc;

import java.util.List;
import java.util.Optional;

public interface JdbcCrudRepositoryI<T extends JdbcDomain, I extends Number> {

	Number criar(final T entidade);

	void atualizar(final T entidade);

	void atualizarLista(final List<T> ls);

	void deletar(final I id, String nmUsuario);

	public Optional<T> recuperarPorId(final I id);
	
	public Integer obterId();

}
