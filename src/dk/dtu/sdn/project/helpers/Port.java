package dk.dtu.sdn.project.helpers;

public class Port {
	private int portNumber;
	private String hardwareAddress;
	private String name;
	private int config;
	private int state;
	private int currentFeatures;
	private int advertisedFeatures;
	private int supportedFeatures;
	private int peerFeatures;

	public Port() {
	}

	public Port(int pNum, String ha, String name, int conf, int state, int curF, int advF, int supF, int peerF) {
		this.portNumber = pNum;
		this.hardwareAddress = ha;
		this.advertisedFeatures = advF;
		this.name = name;
		this.config = conf;
		this.state = state;
		this.currentFeatures = curF;
		this.supportedFeatures = supF;
		this.peerFeatures = peerF;

	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

	public String getHardwareAddress() {
		return hardwareAddress;
	}

	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getConfig() {
		return config;
	}

	public void setConfig(int config) {
		this.config = config;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCurrentFeatures() {
		return currentFeatures;
	}

	public void setCurrentFeatures(int currentFeatures) {
		this.currentFeatures = currentFeatures;
	}

	public int getAdvertisedFeatures() {
		return advertisedFeatures;
	}

	public void setAdvertisedFeatures(int advertisedFeatures) {
		this.advertisedFeatures = advertisedFeatures;
	}

	public int getSupportedFeatures() {
		return supportedFeatures;
	}

	public void setSupportedFeatures(int supportedFeatures) {
		this.supportedFeatures = supportedFeatures;
	}

	public int getPeerFeatures() {
		return peerFeatures;
	}

	public void setPeerFeatures(int peerFeatures) {
		this.peerFeatures = peerFeatures;
	}

}
