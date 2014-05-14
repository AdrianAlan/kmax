package dk.dtu.sdn.project.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dk.dtu.sdn.project.model.Nodes;

public class Device implements Nodes {

	private String entityClass;
	private String[] mac;
	private String[] ipv4;
	private String[] vlan;
	private AttachmentPoint[] attachmentPoint;
	private long lastSeen;

	public Device() {
	}

	public Device(String entity, String[] mac, String[] ip, String[] vlan, AttachmentPoint[] attach, long seen) {
		this.entityClass = entity;
		this.mac = mac;
		this.ipv4 = ip;
		this.vlan = vlan;
		this.attachmentPoint = attach;
		this.lastSeen = seen;
	}

	public String getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(String entityClass) {
		this.entityClass = entityClass;
	}

	public String[] getMac() {
		return mac;
	}

	public void setMac(String[] mac) {
		this.mac = mac;
	}

	public String[] getIpv4() {
		return ipv4;
	}

	public void setIpv4(String[] ipv4) {
		this.ipv4 = ipv4;
	}

	public String[] getVlan() {
		return vlan;
	}

	public void setVlan(String[] vlan) {
		this.vlan = vlan;
	}

	public AttachmentPoint[] getAttachmentPoint() {
		return attachmentPoint;
	}

	public void setAttachmentPoint(AttachmentPoint[] attachmentPoint) {
		this.attachmentPoint = attachmentPoint;
	}

	public long getLastSeen() {
		return lastSeen;
	}

	public void setLastSeen(long lastSeen) {
		this.lastSeen = lastSeen;
	}

	// return: a set of IPv4 addresses for this device
	public Set<String> getIpv4Set() {
		Set<String> ips = new HashSet<String>();
		for (int i = 0; i < ipv4.length; i++) {
			ips.add(ipv4[i]);
		}
		return ips;
	}

	// return: a list of IPv4 addresses for this device
	public List<String> getIpv4List() {
		List<String> ips = new ArrayList<String>();
		for (int i = 0; i < ipv4.length; i++) {
			ips.add(ipv4[i]);
		}
		return ips;
	}

	// return: a list of DPIDs of the switches that this device is attached to
	public List<String> getDpidList() {
		List<String> dpids = new ArrayList<String>();
		for (int i = 0; i < attachmentPoint.length; i++) {
			dpids.add(attachmentPoint[i].getSwitchDPID());
		}
		return dpids;
	}

	@Override
	public boolean isSwitch() {
		return false;
	}

	@Override
	public boolean isHost() {
		return true;
	}

	@Override
	public String getID() {
		return mac[0];
	}

}
