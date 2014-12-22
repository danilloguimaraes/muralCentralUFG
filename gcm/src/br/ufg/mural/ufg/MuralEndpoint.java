package br.ufg.mural.ufg;

import java.io.IOException;

import javax.inject.Named;

import br.ufg.mural.ufg.controller.ServerMensagem;
import br.ufg.mural.ufg.model.LogMensagem;
import br.ufg.mural.ufg.model.Mensagem;
import br.ufg.mural.ufg.model.ServerStatus;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.api.oauth.OAuthRequestException;
import com.google.appengine.api.users.User;

@Api(name = "muralufggcm", namespace = @ApiNamespace(ownerDomain = "ufg.br", ownerName = "ufg.br", packagePath = "br.ufg.mural.ufg.model"))
public class MuralEndpoint{
	
	private ServerMensagem serverMensagem = new ServerMensagem();	

	@ApiMethod(name = "mensagem.post", path = "mensagem", httpMethod = ApiMethod.HttpMethod.POST)
	public LogMensagem enviarMensagem(Mensagem mensagem, User user) throws OAuthRequestException, IOException {
		return serverMensagem.enviarMensagem(mensagem);
	}

	@ApiMethod(name = "logmensagem.get", path = "logmensagem/{id}", httpMethod = ApiMethod.HttpMethod.GET)
	public LogMensagem getLogMensagem(@Named("id") long id, User user) throws OAuthRequestException, IOException {
		return serverMensagem.getLogMensagem(id);
	}

	@ApiMethod(name = "serverstatus.get", path = "serverstatus", httpMethod = ApiMethod.HttpMethod.GET)
	public ServerStatus getServerStatus(User user) throws OAuthRequestException, IOException {
		return serverMensagem.serverStatus();
	}

}
