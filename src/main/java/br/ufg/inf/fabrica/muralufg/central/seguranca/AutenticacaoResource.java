package br.ufg.inf.fabrica.muralufg.central.seguranca;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/autentica")
@Produces(MediaType.APPLICATION_JSON)
public class AutenticacaoResource implements AutenticacaoService{

	@POST
	public boolean autentica(@FormParam("nome") String usuario, @FormParam("senha")String senha) {
		return AutenticacaoUtil.autenticaUsuario(usuario, senha);
	}

}
