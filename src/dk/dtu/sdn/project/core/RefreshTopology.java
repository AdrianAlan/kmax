package dk.dtu.sdn.project.core;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import dk.dtu.sdn.project.helpers.Device;
import dk.dtu.sdn.project.helpers.Link;
import dk.dtu.sdn.project.helpers.Switch;
import dk.dtu.sdn.project.utils.Constants;

public class RefreshTopology {

	private ArrayList<Switch> mSwitchesArray;
	private ArrayList<Device> mDevicesArray;
	private ArrayList<Link> mLinkArray;

	public RefreshTopology(boolean refreshHosts, boolean refreshLinks, boolean refreshSwitches,
			HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Map JSON data to Java objects
		ObjectMapper mMapper = new ObjectMapper();
		mSwitchesArray = mMapper.readValue(getArrayFromJSON(Constants.IP + Constants.wmGetSwitches, request, response),
				new TypeReference<ArrayList<Switch>>() {
				});
		mDevicesArray = mMapper.readValue(getArrayFromJSON(Constants.IP + Constants.wmGetDevice, request, response),
				new TypeReference<ArrayList<Device>>() {
				});
		mLinkArray = mMapper.readValue(getArrayFromJSON(Constants.IP + Constants.wmGetLinks, request, response),
				new TypeReference<ArrayList<Link>>() {
				});
	}

	protected String getArrayFromJSON(String sURL, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClientResource cResource = new ClientResource(sURL);
		StringWriter sWriter = new StringWriter();
		try {
			cResource.get(MediaType.APPLICATION_JSON).write(sWriter);
		} catch (ResourceException e) {
			request.setAttribute(Constants.ProjectTag, "Error" + e);
			request.getRequestDispatcher("WEB-INF/connectionError.jsp").forward(request, response);
			return null;
		}
		return sWriter.toString().replace("-", "");
	}

	public ArrayList<Switch> getmSwitchesArray() {
		return mSwitchesArray;
	}

	public ArrayList<Device> getmDevicesArray() {
		return mDevicesArray;
	}

	public ArrayList<Link> getmLinkArray() {
		return mLinkArray;
	}

}
