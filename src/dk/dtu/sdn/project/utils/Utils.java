package dk.dtu.sdn.project.utils;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

	public static String rulesToJSON(String iSwitch, String srcMAC, String dstMAC, String inputPort, String outputPort)
			throws JSONException {
		JSONObject mJSON = new JSONObject();
		mJSON.put("switch", iSwitch);
		mJSON.put("name", "S" + colonReplace(srcMAC) + "D" + colonReplace(dstMAC) + "T" + colonReplace(iSwitch));
		mJSON.put("src-mac", srcMAC);
		mJSON.put("dst-mac", dstMAC);
		mJSON.put("priority", Constants.FlowPriority);
		mJSON.put("ingress-port", inputPort);
		mJSON.put("active", Constants.FlowActive);
		mJSON.put("actions", "output=" + outputPort);
		return mJSON.toString();
	}

	public static String colonReplace(String input) {
		return input.replace(":", "");
	}

	public static Date getCurrentTime() {
		return new Date();
	}

	public static String rulesToJSONFirewall(String iSwitch, String srcMAC, String dstMAC, String inputPort)
			throws JSONException {
		JSONObject mJSON = new JSONObject();
		mJSON.put("switch", iSwitch);
		mJSON.put("name", "S" + colonReplace(srcMAC) + "D" + colonReplace(dstMAC) + "T" + colonReplace(iSwitch));
		mJSON.put("src-mac", srcMAC);
		mJSON.put("dst-mac", dstMAC);
		mJSON.put("priority", Constants.FlowPriority);
		mJSON.put("ingress-port", inputPort);
		mJSON.put("active", Constants.FlowActive);
		mJSON.put("actions", "");
		System.out.println(mJSON.toString());
		return mJSON.toString();
	}
}
