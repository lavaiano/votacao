package com.votacao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class JdbcRepository {
	
	@Autowired
	protected NamedParameterJdbcTemplate npjt;

	@Autowired
	protected JdbcTemplate jt;

}
