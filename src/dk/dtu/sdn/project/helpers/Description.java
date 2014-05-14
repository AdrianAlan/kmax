package dk.dtu.sdn.project.helpers;

public class Description {

	private String software;
	private String hardware;
	private String manufacturer;
	private String serialNum;
	private String datapath;

	public Description() {
	}

	public Description(String soft, String hard, String mnf, String serial, String dp) {
		this.software = soft;
		this.hardware = hard;
		this.manufacturer = mnf;
		this.serialNum = serial;
		this.datapath = dp;
	}

	public String getHardware() {
		return hardware;
	}

	public void setHardware(String hardware) {
		this.hardware = hardware;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getSerialNum() {
		return serialNum;
	}

	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}

	public String getDatapath() {
		return datapath;
	}

	public void setDatapath(String datapath) {
		this.datapath = datapath;
	}

}
