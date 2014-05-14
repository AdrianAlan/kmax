package dk.dtu.sdn.project.helpers;

import java.util.List;

import dk.dtu.sdn.project.model.Nodes;

public class Switch implements Nodes {

	private String dpid;
	private List<Port> ports;
	private int buffers;
	private Description description;
	private int capabilities;
	private String inetAddress;
	private long connectedSince;
	private Attributes attributes;
	private String harole;
	private int actions;

	public Switch() {
		description = null;
		attributes = null;
	}

	Switch(String dpid, List<Port> ports, int buffers, Description description, int cap, String inet, long conSince,
			Attributes attr, String role, int actions) {
		this.dpid = dpid;
		this.ports = ports;
		this.buffers = buffers;
		this.description = description;
		this.capabilities = cap;
		this.inetAddress = inet;
		this.connectedSince = conSince;
		this.attributes = attr;
		this.harole = role;
		this.actions = actions;
	}

	public String getDpid() {
		return dpid;
	}

	public void setDpid(String dpid) {
		this.dpid = dpid;
	}

	public List<Port> getPorts() {
		return ports;
	}

	public void setPorts(List<Port> ports) {
		this.ports = ports;
	}

	public int getBuffers() {
		return buffers;
	}

	public void setBuffers(int buffers) {
		this.buffers = buffers;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public int getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(int capabilities) {
		this.capabilities = capabilities;
	}

	public String getInetAddress() {
		return inetAddress;
	}

	public void setInetAddress(String inetAddress) {
		this.inetAddress = inetAddress;
	}

	public long getConnectedSince() {
		return connectedSince;
	}

	public void setConnectedSince(long connectedSince) {
		this.connectedSince = connectedSince;
	}

	public Attributes getAttributes() {
		return attributes;
	}

	public void setAttributes(Attributes attributes) {
		this.attributes = attributes;
	}

	public String getHarole() {
		return harole;
	}

	public void setHarole(String harole) {
		this.harole = harole;
	}

	public int getActions() {
		return actions;
	}

	public void setActions(int actions) {
		this.actions = actions;
	}

	@Override
	public boolean isSwitch() {
		return true;
	}

	@Override
	public boolean isHost() {
		return false;
	}

	@Override
	public String getID() {
		return dpid;
	}
}
