package br.ufg.inf.fabrica.muralufg.central.seguranca;

import java.util.List;

import br.ufg.inf.fabrica.muralufg.central.organizacao.Curso;
import br.ufg.inf.fabrica.muralufg.central.organizacao.Disciplina;
import br.ufg.inf.fabrica.muralufg.central.organizacao.Turma;
import br.ufg.inf.fabrica.muralufg.central.organizacao.dao.ICursoDao;
import br.ufg.inf.fabrica.muralufg.central.organizacao.dao.IDisciplinaDao;
import br.ufg.inf.fabrica.muralufg.central.organizacao.dao.ITurmaDao;

public class AutorizacaoServiceImpl implements AutorizacaoService {

	private ICursoDao cursoDao;

	private IDisciplinaDao disciplinaDao;

	private ITurmaDao turmaDao;

	@Override
	public boolean autoriza(Usuario usuario, String acao, String escopo) {
		
		if (acao.equals(AcaoEnum.ENVIAR_MENSAGEM) && escopo != null) {
			return autorizarEnvio(usuario, acao, escopo);
		}
		return false;
	}

	/**
	 * 
	 * @param usuario
	 * @param acao
	 * @param escopo
	 *            String definindo os destinarios para quais a mensagem será
	 *            enviada, seguindo a seguinte regra TIPO_ESCOPO_ENUM:ID_ESCOPO,
	 *            onde o TIPO_ESCOPO_ENUM representa um enumerado
	 *            TipoEscopoEnum, e o ID_ESCOPO o id do respectivo escopo que o
	 *            enum representa.
	 * @return
	 * 
	 */
	public boolean autorizarEnvio(Usuario usuario, String acao, String escopos) {
		
		String escopoValidoCurso = escopoValidoParaUsuarioCurso(usuario);
		String escopoValidoDisciplina = escopoValidoParaUsuarioDisciplina(usuario);
		String escopoValidoTurma = escopoValidoParaUsuarioTurma(usuario);

		String[] arrayEscopos = escopos.split(";");

		boolean autorizado = true;

		for (String escopo : arrayEscopos) {

			TipoEscopoEnum tipoEscopo = TipoEscopoEnum.valueOf(escopo.split(":")[0]);
			Long idEscopo = new Long(escopo.split(":")[1]);

			if (tipoEscopo.equals(TipoEscopoEnum.CURSO)) {
				autorizado = autorizarEnvioCurso(idEscopo, escopoValidoCurso);

			} else if (tipoEscopo.equals(TipoEscopoEnum.DISCIPLINA)) {
				autorizado = autorizarEnvioDisciplina(idEscopo, escopoValidoDisciplina);

			} else if (tipoEscopo.equals(TipoEscopoEnum.TURMA)) {
				autorizado = autorizarEnvioCurso(idEscopo, escopoValidoTurma);

			}

			if (!autorizado)
				break;
		}

		return autorizado;
	}

	private boolean autorizarEnvioTurma(Long idTurma, String usuario) {

		Turma turma = turmaDao.consultar(idTurma);
		
		String [] turmaValida = usuario.split(":");
		
		for(String turmaAux: turmaValida){
			Long idTurmaAux = new Long(turmaAux.split(":")[1]);
			if(idTurmaAux == idTurma){
				return true;
			}
		}
		
		return false;

	}

	private boolean autorizarEnvioDisciplina(Long idDisciplina, String usuario) {

		Disciplina disciplina = disciplinaDao.consultar(idDisciplina);
		
		String [] disciplinaValida = usuario.split(":");
		
		for(String disciplinaAux: disciplinaValida){
			Long idDisciplinaAux = new Long(disciplinaAux.split(":")[1]);
			if(idDisciplinaAux == idDisciplina){
				return true;
			}
		}

		return false;

	}

	private boolean autorizarEnvioCurso(Long idCurso, String usuario) {

		Curso curso = cursoDao.consultar(idCurso);
		
		String [] cursoValido = usuario.split(":");
		
		for(String cursoAux: cursoValido){
			Long idCursoAux = new Long(cursoAux.split(":")[1]);
			if(idCursoAux == idCurso){
				return true;
			}
		}

		return false;

	}
	
	/**
	 * @param usuario
	 * @return
	 * Retorna o escopo que o usuário pode enviar determinanda mensagem.
	 */
	public String escopoValidoParaUsuarioCurso (Usuario usuario){
		String escopoValidoCurso = "";
		
		List<Curso> cursosValidos = cursoDao.cursosValidosUsuario(usuario);
		if(cursosValidos!= null && !cursosValidos.isEmpty()){
			for(Curso curso : cursosValidos){
				escopoValidoCurso = escopoValidoCurso + TipoEscopoEnum.CURSO+":"+curso.getNome()+";";
			}
		}
		
		return escopoValidoCurso;
	}
	
	public String escopoValidoParaUsuarioDisciplina(Usuario usuario){
		String escopoValidoDisciplina= "";
		List<Disciplina> disciplinasValidos = disciplinaDao.disciplinasValidasUsuario(usuario);
		if(disciplinasValidos!= null && !disciplinasValidos.isEmpty()){
			for(Disciplina disciplina : disciplinasValidos){
				escopoValidoDisciplina = escopoValidoDisciplina + TipoEscopoEnum.DISCIPLINA+":"+disciplina.getNome()+";";
			}
		}
		return escopoValidoDisciplina;
	}
	
	public String escopoValidoParaUsuarioTurma(Usuario usuario){
		String escopoValidoTurma= "";
		List<Turma> turmasValidos = turmaDao.turmasValidosUsuario(usuario);
		if(turmasValidos!= null && !turmasValidos.isEmpty()){
			for(Turma turma : turmasValidos){
				escopoValidoTurma = escopoValidoTurma + TipoEscopoEnum.TURMA+":"+turma.getResponsavel()+";";
			}
		}
		return escopoValidoTurma;
	}
}
