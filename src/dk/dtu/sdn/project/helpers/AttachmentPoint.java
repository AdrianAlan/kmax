package dk.dtu.sdn.project.helpers;

public class AttachmentPoint {
	private String switchDPID;
	private int port;
	private String errorStatus;

	public AttachmentPoint() {
	}

	public AttachmentPoint(String dpid, int port, String error) {
		this.switchDPID = dpid;
		this.port = port;
		this.errorStatus = error;
	}

	public String getSwitchDPID() {
		return switchDPID;
	}

	public void setSwitchDPID(String switchDPID) {
		this.switchDPID = switchDPID;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getErrorStatus() {
		return errorStatus;
	}

	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
}
