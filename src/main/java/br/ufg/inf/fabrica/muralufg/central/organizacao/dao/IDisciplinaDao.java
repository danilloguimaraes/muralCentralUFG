package br.ufg.inf.fabrica.muralufg.central.organizacao.dao;

import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.organizacao.Disciplina;
import br.ufg.inf.fabrica.muralufg.central.seguranca.Usuario;

public interface IDisciplinaDao {
	
	public Disciplina consultar(Long id);
	
	public List<Disciplina> disciplinasValidasUsuario(Usuario idUsuario);

}
