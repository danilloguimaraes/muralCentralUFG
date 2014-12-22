package br.ufg.inf.fabrica.muralufg.central.organizacao.dao;

import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.organizacao.Curso;
import br.ufg.inf.fabrica.muralufg.central.seguranca.Usuario;

public interface ICursoDao {
	
	public Curso consultar(Long id);
	
	public List<Curso> cursosValidosUsuario(Usuario idUsuario);

}
