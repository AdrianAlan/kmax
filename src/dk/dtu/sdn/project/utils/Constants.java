package dk.dtu.sdn.project.utils;

public class Constants {

	// Settings: You can touch this
	public static final int theK = 3;
	public static final String FlowPriority = "20000";
	public static final String FlowActive = "true";
	public static final String IP = "http://127.0.0.1:8080";

	// General: You should not touch this
	public static final String ProjectTag = "SDN Project";
	public static final String Devices = "DevicesList";
	public static final String Source = "SourceHost";
	public static final String Destination = "DestinationHost";
	public static final String Routes = "Routes";
	public static final String wmGetDevice = "/wm/device/";
	public static final String wmGetLinks = "/wm/topology/links/json";
	public static final String wmStaticFlowEntryPusher = "/wm/staticflowentrypusher/json";
	public static final String wmGetSwitches = "/wm/core/controller/switches/json";
	public static final String DevicesNumber = "DevicesNumber";
	public static final String Loading = "Loading";
	public static final String Error = "Error";
	public static final String TopologyError = "Error captured. Cannot proceed due to errors in the topology description.";
	public static final String Cleaner = "Clean";
	public static final String Changer = "Change";
	public static final String RoutesNumber = "RoutesNumber";
	public static final String ResponseSuccess = "OK";
}
