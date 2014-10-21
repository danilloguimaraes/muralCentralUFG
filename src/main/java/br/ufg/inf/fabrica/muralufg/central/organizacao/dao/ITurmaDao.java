package br.ufg.inf.fabrica.muralufg.central.organizacao.dao;

import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.organizacao.Disciplina;
import br.ufg.inf.fabrica.muralufg.central.organizacao.Turma;
import br.ufg.inf.fabrica.muralufg.central.seguranca.Usuario;

public interface ITurmaDao {
	
	public Turma consultar(Long id);
	
	public List<Turma> turmasValidosUsuario(Usuario idUsuario);

}
