package com.votacao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.votacao.dto.VotacaoDTO;
import com.votacao.mapper.VotacaoMapper;
import com.votacao.model.Votacao;
import com.votacao.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {
	
	@Autowired
	private VotacaoService rep;
	
	
	@GetMapping("/recuperarPorId")
	public Optional<Votacao> recuperarPorId(@RequestParam("id") final Long id) {

		return rep.recuperarPorId(id);

	}
	
	@GetMapping("/recuperarPorJogadorId")
	public List<VotacaoDTO> recuperarPorJogadorId(@RequestParam("idUsuario") final Long idUsuario) {

		return rep.recuperarPorJogador(idUsuario);

	}
	
	@PostMapping("/votar")
	public ResponseEntity<List<Votacao>> criar(final @Validated @RequestBody List<VotacaoDTO> dto) throws Exception {

		VotacaoMapper votacaoMapper = new VotacaoMapper();

		return ResponseEntity.ok(rep.criar(votacaoMapper.mapear(dto)));
	}

}
