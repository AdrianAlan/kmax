package dk.dtu.sdn.project.helpers;

public class Link {

	private String srcswitch;
	private String srcport;
	private String dstswitch;
	private String dstport;
	private String type;
	private String direction;

	public Link() {
	}

	Link(String srcswitch, String srcport, String dstswitch, String dstport, String type, String direction) {
		this.srcswitch = srcswitch;
		this.srcport = srcport;
		this.dstswitch = dstswitch;
		this.dstport = dstport;
		this.type = type;
		this.direction = direction;
	}

	public String getsrcswitch() {
		return srcswitch;
	}

	public void setsrcswitch(String srcswitch) {
		this.srcswitch = srcswitch;
	}

	public String getsrcport() {
		return srcport;
	}

	public void setsrcport(String srcport) {
		this.srcport = srcport;
	}

	public String getdstswitch() {
		return dstswitch;
	}

	public void setdstswitch(String dstswitch) {
		this.dstswitch = dstswitch;
	}

	public String getdstport() {
		return dstport;
	}

	public void setdstport(String dstport) {
		this.dstport = dstport;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

}
