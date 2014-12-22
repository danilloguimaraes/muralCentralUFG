/*
 * ====================================================================
 * Licença da Fábrica de Software (UFG)
 *
 * Copyright (c) 2014 Fábrica de Software
 * Instituto de Informática (Universidade Federal de Goiás)
 * Todos os direitos reservados.
 *
 * Redistribuição e uso, seja dos fontes ou do formato binário
 * correspondente, com ou sem modificação, são permitidos desde que
 * as seguintes condições sejam atendidas:
 *
 * 1. Redistribuição do código fonte deve conter esta nota, em sua
 *    totalidade, ou seja, a nota de copyright acima, as condições
 *    e a observação sobre garantia abaixo.
 *
 * 2. Redistribuição no formato binário deve reproduzir o conteúdo
 *    desta nota, em sua totalidade, ou seja, o copyright acima,
 *    esta lista de condições e a observação abaixo na documentação
 *    e/ou materiais fornecidos com a distribuição.
 *
 * 3. A documentação fornecida com a redistribuição,
 *    qualquer que seja esta, deve incluir o seguinte
 *    texto, entre aspas:
 *       "Este produto inclui software desenvolvido pela Fábrica
 *       de Software do Instituto de Informática (UFG)."
 *
 * 4. Os nomes Fábrica de Software, Instituto de Informática e UFG
 *    não podem ser empregados para endoçar ou promover produtos
 *    derivados do presente software sem a explícita permissão
 *    por escrito.
 *
 * 5. Produtos derivados deste software não podem ser chamados
 *    "Fábrica de Software", "Instituto de Informática", "UFG",
 *    "Universidade Federal de Goiás" ou contê-los em seus nomes,
 *    sem permissão por escrito.
 *
 * ESTE SOFTWARE É FORNECIDO "COMO ESTÁ". NENHUMA GARANTIA É FORNECIDA,
 * EXPLÍCITA OU NÃO. NÃO SE AFIRMA QUE O PRESENTE SOFTWARE
 * É ADEQUADO PARA QUALQUER QUE SEJA O USO. DE FATO, CABE AO
 * INTERESSADO E/OU USUÁRIO DO PRESENTE SOFTWARE, IMEDIATO OU NÃO,
 * ESTA AVALIAÇÃO E A CONSEQUÊNCIA QUE O USO DELE OCASIONAR. QUALQUER
 * DANO QUE DESTE SOFTWARE DERIVAR DEVE SER ATRIBUÍDO AO INTERESSADO
 * E/OU USUÁRIO DESTE SOFTWARE.
 * ====================================================================
 *
 * Este software é resultado do trabalho de voluntários, estudantes e
 * professores, ao realizar atividades no âmbito da Fábrica de Software
 * do Instituto de Informática (UFG). Consulte <http://fs.inf.ufg.br>
 * para detalhes.
 */

package br.ufg.inf.fabrica.muralufg.central.despertador;

import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Oferece serviço para "despertar" em instantes de tempo agendados previamente.
 *
 * <p>
 * O despertador recebe requisições de agendamento e as sinaliza no instante de
 * tempo correspondente. Um agendamento representa a "intenção" de ser avisado
 * no instante de tempo em questão. Um agendamento inclui um instante de tempo e
 * um identificador único.
 * </p>
 * <p>
 * Quando o instante de tempo correspondente a um agendamento é atingido, o
 * despertador sinaliza esta ocorrência, neste instante de tempo. O
 * identificador único do agendamento é fornecida ao sinalizar a ocorrência.
 * </p>
 * <p>
 * A sinalização é enviada para observadores. ({@link java.util.Observer}).
 * Todos os observadores cadastrados receberão a sinalização de um agendamento,
 * via método {@link Observer#update(java.util.Observable, Object)} no qual o
 * primeiro argumento será {@code null} e o segundo argumento será o
 * identificador único do agendamento).
 * </p>
 * <p>
 * Ao enviar a mensagem indicada acima, o despertador considera sua tarefa
 * realizada de forma satisfatória.
 * </p>
 */
public class Despertador {

	private static Despertador despertador = new Despertador();
	private ArrayList<Agendamento> agendamentosRegistrados;

	/**
	 * Requisita o início das operações do despertador.
	 * <p>
	 * Observe que, após interrupção de operação, os interessados ou
	 * observadores deverão novamente se cadastrar junto ao despertador ou, caso
	 * contrário, não receberão a sinalização que aguardam. Este método permite
	 * que o início das ações do despertador sejam postergadas até que
	 * interessados tenham tido a oportunidade necessária para registrar
	 * interesse nos serviços do despertador.
	 * </p>
	 * 
	 * @return
	 */

	private Despertador() {
		agendamentosRegistrados = new ArrayList<Agendamento>();
		Observador o = new Observador();

	}

	public boolean inicie() {
		File arq = new File("despertador.txt");

		// Arquivo não existe
		if (!arq.exists()) {
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
						new FileOutputStream("despertador.txt"), "utf-8"));
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
				return false;
			} finally {
				try {
					writer.close();
				} catch (Exception ex) {
					return false;
				}
			}
		} else {
			try {
				FileReader arq2 = new FileReader("despertador.txt");
				BufferedReader lerArq = new BufferedReader(arq2);
				String linha = lerArq.readLine();
				while (linha != null) {

					String argumentos[] = linha.split("@@");

					agendar(argumentos[0],
							new Date(Long.getLong(argumentos[1])), false);
					String observadores[] = argumentos[3].split("||");
					for (int i = 0; i < observadores.length; i++) {
						Observador obTemp = new Observador();
						obTemp.setId(observadores[i]);
						adicionaObservador(obTemp, argumentos[0], false);
					}
					linha = lerArq.readLine();
				}
				arq2.close();
			} catch (IOException e) {
				System.err.printf("Erro na abertura do arquivo: %s.\n",
						e.getMessage());
				return false;
			}

		}
		return true;
	}

	public ArrayList<Agendamento> getAgendamentosRegistrados() {
		return agendamentosRegistrados;
	}

	public void setAgendamentosRegistrados(
			ArrayList<Agendamento> agendamentosRegistrados) {
		this.agendamentosRegistrados = agendamentosRegistrados;
	}

	/**
	 * Agenda o despertador para enviar a mensagem {@link Runnable#run()} ao
	 * objeto fornecido no instante de tempo indicado.
	 * 
	 * @param identificador
	 *            Identificação única do agendamento.
	 * @param instante
	 *            Instante de tempo em que o despertador deverá
	 * @param atualizarPersistencia
	 *            indica se é para Atualizar a base de persistencia enviar a
	 *            mensagem ao objeto fornecido.
	 * @return O valor {@code true} caso o agendamento foi feito de forma
	 *         satisfatória ou o valor {@code false}, caso contrário.
	 * @throws java.lang.IllegalArgumentException
	 *             se o identificador fornecido ou o instante fornecido seja
	 *             {@code null}. Esta exceção também será gerada se o instante
	 *             de tempo fornecido for anterior ou igual ao instante
	 *             corrente.
	 * @see #remove(String)
	 */
	public String agendar(String identificador, Date instante,
			boolean atualizarPersistencia) {

		Agendamento novoAgendamento = new Agendamento(instante, identificador);
		agendamentosRegistrados.add(novoAgendamento);
		if (atualizarPersistencia) {
			// salvar no txt
			File arq = new File("despertador.txt");
			try {
				FileWriter fileWriter = new FileWriter(arq, false);
				PrintWriter printWriter = new PrintWriter(fileWriter);
				printWriter.print(identificador + "@@"
						+ String.valueOf(instante.getTime()) + "@@" + "\n");
				printWriter.flush();
				printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return identificador;
	}

	/**
	 * Remove um agendamento prévio.
	 * 
	 * @param identificador
	 *            O identificador único do agendamento a ser removido. Este
	 *            identificador é obtido do retorno do método
	 *            {@link #desperteEm(String, java.util.Date)}.
	 * @param atualizarPersistencia
	 *            indica se é para Atualizar a base de persistencia
	 * @return {@code true} se o agendamento foi removido de forma satisfatória
	 *         ou {@code false}, caso contrário.
	 * @throws java.lang.IllegalArgumentException
	 *             Se o {@code identificador} for {@code null} ou vazio.
	 * @see #desperteEm(String, java.util.Date)
	 */
	public boolean removerAgendamento(String identificador) {
		String novoTexto = "";
		for (Agendamento agendamento : agendamentosRegistrados) {
			if (agendamento.getId().equals(identificador)) {
				agendamento.cancelaAgendamento();
				agendamentosRegistrados.remove(agendamento);

				// salvanotxt
				try {
					FileReader arq2 = new FileReader("despertador.txt");
					BufferedReader lerArq = new BufferedReader(arq2);
					String linha = lerArq.readLine();
					while (linha != null) {
						String argumentos[] = linha.split("@@");
						if (!argumentos[0].equals(identificador)) {
							novoTexto = novoTexto.concat(linha + "\n");
						}
						linha = lerArq.readLine();
					}
					arq2.close();
				} catch (IOException e) {
					System.err.printf("Erro na abertura do arquivo: %s.\n",
							e.getMessage());
					return false;
				}
				File arq = new File("despertador.txt");
				Writer writer = null;
				try {
					writer = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream("despertador.txt"), "utf-8"));
					writer.write(novoTexto);
				} catch (IOException ex) {
					System.out.println(ex.getMessage());
					return false;
				} finally {
					try {
						writer.close();
					} catch (Exception ex) {
						return false;
					}
				}
				return true;
			}
		}

		return false;
	}

	/**
	 * Cadastra um {@link Observer} para receber sinalizações de ocorrências de
	 * agendamentos.
	 * <p>
	 * Quando o instante de tempo correspondente a um agendamento for atingido,
	 * o método {@link Observer#update(java.util.Observable, Object)} será
	 * chamado, ondo o primeiro argumento será {@code null} e o segundo o
	 * identificador do agendamento em questão. Observe que esta mensagem será
	 * enviada para todos os observadores cadastrados.
	 * </p>
	 * 
	 * @param observador
	 *            Objeto que será avisado da ocorrência de um agendamento.
	 * @param agendamento
	 *            id ao qual um observador sera ligado.
	 * @param atualizarPersistencia
	 *            indica se é para Atualizar a base de persistencia
	 * @return
	 */
	public boolean adicionaObservador(Observador observador, String agendamento,
			boolean atualizarPersistencia) {
		String textoNovo = "";
		for (Agendamento ag : agendamentosRegistrados) {
			if (ag.getId().equals(agendamento)) {
				ag.addObserver(observador);
				if (atualizarPersistencia) {
					// salvar no txt
					try {
						FileReader arq2 = new FileReader("despertador.txt");
						BufferedReader lerArq = new BufferedReader(arq2);
						String linha = lerArq.readLine();
						while (linha != null) {
							String argumentos[] = linha.split("@@");
							if (argumentos[0].equals(agendamento)) {
								linha = linha.concat(observador.getId()+"||");
							}
							textoNovo = textoNovo.concat(linha + "\n");
							linha = lerArq.readLine();
						}
						arq2.close();
					} catch (IOException e) {
						System.err.printf("Erro na abertura do arquivo: %s.\n",
								e.getMessage());
						return false;
					}
					File arq = new File("despertador.txt");
					Writer writer = null;
					try {
						writer = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream("despertador.txt"), "utf-8"));
						writer.write(textoNovo);
					} catch (IOException ex) {
						System.out.println(ex.getMessage());
						return false;
					} finally {
						try {
							writer.close();
						} catch (Exception ex) {
							return false;
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtém instância de {@link Despertador}. Este objeto é um
	 * <i>singleton</i>.
	 * 
	 * @return Objeto que implementa {@link Despertador}.
	 */
	public static Despertador getInstance() {
		return despertador;
	}
}
