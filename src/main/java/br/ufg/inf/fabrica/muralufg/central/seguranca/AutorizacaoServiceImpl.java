package br.ufg.inf.fabrica.muralufg.central.seguranca;

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

		String[] arrayEscopos = escopos.split(";");

		boolean autorizado = true;

		for (String escopo : arrayEscopos) {

			TipoEscopoEnum tipoEscopo = TipoEscopoEnum.valueOf(escopo.split(":")[0]);
			Long idEscopo = new Long(escopo.split(":")[1]);

			if (tipoEscopo.equals(TipoEscopoEnum.CURSO)) {
				autorizado = autorizarEnvioCurso(idEscopo, usuario);

			} else if (tipoEscopo.equals(TipoEscopoEnum.DISCIPLINA)) {
				autorizado = autorizarEnvioDisciplina(idEscopo, usuario);

			} else if (tipoEscopo.equals(TipoEscopoEnum.TURMA)) {
				autorizado = autorizarEnvioCurso(idEscopo, usuario);

			}

			if (!autorizado)
				break;
		}

		return autorizado;
	}

	private boolean autorizarEnvioTurma(Long idTurma, Usuario usuario) {

		Turma turma = turmaDao.consultar(idTurma);

		return false;

	}

	private boolean autorizarEnvioDisciplina(Long idDisciplina, Usuario usuario) {

		Disciplina disciplina = disciplinaDao.consultar(idDisciplina);

		return false;

	}

	private boolean autorizarEnvioCurso(Long idCurso, Usuario usuario) {

		Curso curso = cursoDao.consultar(idCurso);

		return false;

	}
}
