package uy.gub.imm.sae.business.dto;

public class InformacionRequestDTO {

	private String scheme;
	
	private String serverName;
	
	private String contextPath;
	
	private int serverPort;

	public InformacionRequestDTO() {
		super();
	}
	
	public InformacionRequestDTO(String scheme, String serverName, String contextPath, int serverPort) {
		super();
		this.scheme = scheme;
		this.serverName = serverName;
		this.contextPath = contextPath;
		this.serverPort = serverPort;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public int getServerPort() {
		return serverPort;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	
}
