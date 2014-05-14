package dk.dtu.sdn.project.helpers;

import org.codehaus.jackson.annotate.JsonProperty;

public class Attributes {

	private boolean supportsOfppFlood;
	private boolean supportsNxRole;
	private int FastWildcards;
	private boolean supportsOfppTable;

	public Attributes() {
	}

	public Attributes(boolean sFlood, boolean sRole, int wild, boolean sTable) {
		this.supportsOfppFlood = sFlood;
		this.supportsNxRole = sRole;
		this.supportsOfppTable = sTable;
		this.FastWildcards = wild;

	}

	public boolean isSupportsOfppFlood() {
		return supportsOfppFlood;
	}

	public void setSupportsOfppFlood(boolean supportsOfppFlood) {
		this.supportsOfppFlood = supportsOfppFlood;
	}

	public boolean isSupportsNxRole() {
		return supportsNxRole;
	}

	public void setSupportsNxRole(boolean supportsNxRole) {
		this.supportsNxRole = supportsNxRole;
	}

	@JsonProperty("FastWildcards")
	public int getFastWildcards() {
		return FastWildcards;
	}

	@JsonProperty("FastWildcards")
	public void setFastWildcards(int fastWildcards) {
		FastWildcards = fastWildcards;
	}

	public boolean isSupportsOfppTable() {
		return supportsOfppTable;
	}

	public void setSupportsOfppTable(boolean supportsOfppTable) {
		this.supportsOfppTable = supportsOfppTable;
	}

}
