package br.ufg.inf.fabrica.muralufg.central;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Estabelece parâmetros de configuração da Central (componente).
 * @see br.ufg.inf.fabrica.muralufg.central.CentralApplication
 */
public class CentralConfiguration extends Configuration {
    
    @NotEmpty
    private String nome;

    @NotEmpty
    private String versao;
    
    @NotEmpty
    private String smtp;

    @NotEmpty
    private String portaEmail;
    
    @NotEmpty
    private String emailTo;

    @NotEmpty
    private String emailFrom;
    
    @NotEmpty
    private String loginEmail;
    
    @NotEmpty
    private String senhaEmail;
   

    @JsonProperty
    public String getNome() {
        return nome;
    }

    @JsonProperty
    public void setNome(String nome) {
        this.nome = nome;
    }

    @JsonProperty
    public String getVersao() {
        return versao;
    }

    @JsonProperty
    public void setVersao(String versao) {
        this.versao = versao;
    }
    
    @JsonProperty
	public String getSmtp() {
		return smtp;
	}

    @JsonProperty
	public void setSmtp(String smtp) {
		this.smtp = smtp;
	}

    @JsonProperty
	public String getPortaEmail() {
		return portaEmail;
	}

    @JsonProperty
	public void setPortaEmail(String portaEmail) {
		this.portaEmail = portaEmail;
	}

    @JsonProperty
	public String getEmailTo() {
		return emailTo;
	}

    @JsonProperty
	public void setEmailTo(String emailTo) {
		this.emailTo = emailTo;
	}

    @JsonProperty
	public String getEmailFrom() {
		return emailFrom;
	}

    @JsonProperty
	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

    @JsonProperty
	public String getLoginEmail() {
		return loginEmail;
	}

    @JsonProperty
	public void setLoginEmail(String loginEmail) {
		this.loginEmail = loginEmail;
	}

    @JsonProperty
	public String getSenhaEmail() {
		return senhaEmail;
	}

    @JsonProperty
	public void setSenhaEmail(String senhaEmail) {
		this.senhaEmail = senhaEmail;
	}
    
    
    
}
