package dk.dtu.sdn.project.core;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import dk.dtu.sdn.project.curl.SendCURL;
import dk.dtu.sdn.project.helpers.Device;
import dk.dtu.sdn.project.helpers.Link;
import dk.dtu.sdn.project.helpers.Switch;
import dk.dtu.sdn.project.utils.Constants;
import dk.dtu.sdn.project.utils.Utils;

public class ChangeRoute {

	private boolean resultPositve;
	private String inputPort = null;

	public ChangeRoute() {
		this.resultPositve = true;
	}

	public boolean newRoute(ArrayList<String> routeArray, SendCURL mSender, RefreshTopology doRefresh)
			throws IOException {
		String idSource = routeArray.get(0);
		String idDestination = routeArray.get(routeArray.size() - 1);

		for (int routeIterator = 0; routeIterator < (routeArray.size() - 1); routeIterator++) {

			if (routeIterator == 0) {
				ArrayList<Device> mDevicesArray = doRefresh.getmDevicesArray();
				for (int deviceIterator = 0; deviceIterator < mDevicesArray.size(); deviceIterator++) {
					if (routeArray.get(routeIterator).equals(mDevicesArray.get(deviceIterator).getID())) {
						inputPort = mDevicesArray.get(deviceIterator).getAttachmentPoint()[0].getPort() + "";
						break;
					}
				}
			} else if (routeIterator == (routeArray.size() - 2)) {
				ArrayList<Device> mDevicesArray = doRefresh.getmDevicesArray();
				for (int deviceIterator = 0; deviceIterator < mDevicesArray.size(); deviceIterator++) {
					if (routeArray.get(routeIterator + 1).equals(mDevicesArray.get(deviceIterator).getID())) {
						try {
							resultPositve = mSender.sendJSON(Utils.rulesToJSON(routeArray.get(routeIterator), idSource,
									idDestination, inputPort,
									mDevicesArray.get(deviceIterator).getAttachmentPoint()[0].getPort() + ""), false)
									&& resultPositve;
						} catch (JSONException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			} else {
				ArrayList<Link> mLinks = doRefresh.getmLinkArray();
				for (int linkIterator = 0; linkIterator < mLinks.size(); linkIterator++) {
					if (mLinks.get(linkIterator).getsrcswitch().equals(routeArray.get(routeIterator))
							&& mLinks.get(linkIterator).getdstswitch().equals(routeArray.get(routeIterator + 1))) {
						try {
							resultPositve = mSender.sendJSON(Utils.rulesToJSON(routeArray.get(routeIterator), idSource,
									idDestination, inputPort, mLinks.get(linkIterator).getsrcport()), false)
									&& resultPositve;
						} catch (JSONException e) {
							e.printStackTrace();
						}
						inputPort = mLinks.get(linkIterator).getdstport();
						break;
					} else if (mLinks.get(linkIterator).getdstswitch().equals(routeArray.get(routeIterator))
							&& mLinks.get(linkIterator).getsrcswitch().equals(routeArray.get(routeIterator + 1))) {
						try {
							resultPositve = mSender.sendJSON(Utils.rulesToJSON(routeArray.get(routeIterator), idSource,
									idDestination, inputPort, mLinks.get(linkIterator).getdstport()), false)
									&& resultPositve;
						} catch (JSONException e) {
							e.printStackTrace();
						}
						inputPort = mLinks.get(linkIterator).getsrcport();
					}
				}
			}

		}
		return resultPositve;
	}

	public boolean applyFirewall(String idSource, String idDestination, SendCURL mSender, RefreshTopology doRefresh)
			throws IOException {
		ArrayList<Device> mDevicesArray = doRefresh.getmDevicesArray();
		for (int deviceIterator = 0; deviceIterator < mDevicesArray.size(); deviceIterator++) {
			if (idSource.equals(mDevicesArray.get(deviceIterator).getID())) {
				inputPort = mDevicesArray.get(deviceIterator).getAttachmentPoint()[0].getPort() + "";
				try {
					resultPositve = mSender.sendJSON(Utils.rulesToJSONFirewall(mDevicesArray.get(deviceIterator)
							.getAttachmentPoint()[0].getSwitchDPID(), idSource, idDestination, inputPort), false);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return resultPositve;
	}

	public boolean clearSavedRoutes(ArrayList<String> arrayList, ArrayList<Switch> getmSwitchesArray) {
		for (int i = 0; i < getmSwitchesArray.size(); i++) {
			SendCURL curl;
			try {
				curl = new SendCURL(Constants.IP + Constants.wmStaticFlowEntryPusher);
				String src = Utils.colonReplace(arrayList.get(0));
				String dst = Utils.colonReplace(arrayList.get(arrayList.size() - 1));
				String swi = Utils.colonReplace(getmSwitchesArray.get(i).getID());
				curl.sendJSON("{\"name\":\"S" + src + "D" + dst + "T" + swi + "\"}", true);

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean clearAllSavedRoutes(String idSRC, String idDST, ArrayList<Switch> getmSwitchesArray) {
		for (int i = 0; i < getmSwitchesArray.size(); i++) {
			SendCURL curl;
			try {
				curl = new SendCURL(Constants.IP + Constants.wmStaticFlowEntryPusher);
				String src = Utils.colonReplace(idSRC);
				String dst = Utils.colonReplace(idDST);
				String swi = Utils.colonReplace(getmSwitchesArray.get(i).getID());
				curl.sendJSON("{\"name\":\"S" + src + "D" + dst + "T" + swi + "\"}", true);

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}
