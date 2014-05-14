package dk.dtu.sdn.project.helpers;

public class Flow {

	private String dataLayerDestination;
	private String dataLayerSource;
	private String dstswitch;
	private String dstport;
	private String type;
	private String direction;

	public Flow() {
	}

	Flow(String dataLayerDestination, String dataLayerSource, String dstswitch, String dstport, String type,
			String direction) {
		this.dataLayerDestination = dataLayerDestination;
		this.dataLayerSource = dataLayerSource;
		this.dstswitch = dstswitch;
		this.dstport = dstport;
		this.type = type;
		this.direction = direction;
	}

}
