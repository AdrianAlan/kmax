package dk.dtu.sdn.project.core;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.jgrapht.GraphPath;
import org.jgrapht.graph.DefaultEdge;
import org.restlet.data.MediaType;
import org.restlet.resource.ClientResource;
import org.restlet.resource.ResourceException;

import dk.dtu.sdn.project.curl.SendCURL;
import dk.dtu.sdn.project.model.Nodes;
import dk.dtu.sdn.project.utils.Constants;

@WebServlet("/PrintInfo")
public class PrintInfo extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final int numberOfK = 3;
	private RefreshTopology doRefresh;
	private ArrayList<ArrayList<String>> alternativeRoutes;

	public PrintInfo() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doRefresh = new RefreshTopology(true, true, true, request, response);

		// Put objects in the request so we can use them later in the .jsp
		request.setAttribute(Constants.DevicesNumber, doRefresh.getmDevicesArray().size());
		request.setAttribute(Constants.Devices, doRefresh.getmDevicesArray());
		request.setAttribute(Constants.Loading, "0");
		request.setAttribute(Constants.Error, "0");
		request.setAttribute(Constants.RoutesNumber, "0");
		// Redirect to the .jsp
		request.getRequestDispatcher("WEB-INF/showInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		String selectedRoute = request.getParameter("selectRoute");
		String sourceHost = request.getParameter("sourceHost");
		String destinationHost = request.getParameter("destinationHost");
		String cleanAll = request.getParameter("cleanAll");

		if (cleanAll != null && !cleanAll.isEmpty()) {
			ChangeRoute newRouteChanger = new ChangeRoute();
			boolean mCleaner = false;
			mCleaner = newRouteChanger.clearAllSavedRoutes(alternativeRoutes.get(0).get(0), alternativeRoutes.get(0)
					.get(alternativeRoutes.get(0).size() - 1), doRefresh.getmSwitchesArray());

			request.setAttribute(Constants.DevicesNumber, doRefresh.getmDevicesArray().size());
			request.setAttribute(Constants.Devices, doRefresh.getmDevicesArray());
			request.setAttribute(Constants.Source, sourceHost);
			request.setAttribute(Constants.Destination, destinationHost);
			request.setAttribute(Constants.Error, "0");
			request.setAttribute(Constants.Routes, alternativeRoutes);
			request.setAttribute(Constants.RoutesNumber, alternativeRoutes.size());
			request.setAttribute(Constants.Cleaner, mCleaner);

			request.getRequestDispatcher("WEB-INF/showInfo.jsp").forward(request, response);
		} else if (selectedRoute != null && !selectedRoute.isEmpty()) {
			ChangeRoute newRouteChanger = new ChangeRoute();
			boolean mCleaner = false;
			boolean mChanger = false;
			if (selectedRoute.equals("-1")) {
				mCleaner = newRouteChanger.clearSavedRoutes(alternativeRoutes.get(0), doRefresh.getmSwitchesArray());
				mChanger = newRouteChanger.applyFirewall(alternativeRoutes.get(0).get(0),
						alternativeRoutes.get(0).get(alternativeRoutes.get(0).size() - 1), new SendCURL(Constants.IP
								+ Constants.wmStaticFlowEntryPusher), doRefresh);
			} else {
				mCleaner = newRouteChanger.clearSavedRoutes(alternativeRoutes.get(new Integer(selectedRoute)),
						doRefresh.getmSwitchesArray());
				mChanger = newRouteChanger.newRoute(alternativeRoutes.get(new Integer(selectedRoute)), new SendCURL(
						Constants.IP + Constants.wmStaticFlowEntryPusher), doRefresh);
			}
			request.setAttribute(Constants.DevicesNumber, doRefresh.getmDevicesArray().size());
			request.setAttribute(Constants.Devices, doRefresh.getmDevicesArray());
			request.setAttribute(Constants.Source, sourceHost);
			request.setAttribute(Constants.Destination, destinationHost);
			request.setAttribute(Constants.Error, "0");
			request.setAttribute(Constants.Routes, alternativeRoutes);
			request.setAttribute(Constants.RoutesNumber, alternativeRoutes.size());
			request.setAttribute(Constants.Cleaner, mCleaner);
			request.setAttribute(Constants.Changer, mChanger);

			request.getRequestDispatcher("WEB-INF/showInfo.jsp").forward(request, response);

		} else if (sourceHost != null && !sourceHost.isEmpty() && destinationHost != null && !destinationHost.isEmpty()) {

			alternativeRoutes = new ArrayList<ArrayList<String>>();
			GenerateKShortest mKShortest = new GenerateKShortest(doRefresh, sourceHost, destinationHost, numberOfK);

			if (mKShortest.getAlgoResult() == null) {
				request.setAttribute(Constants.DevicesNumber, doRefresh.getmDevicesArray().size());
				request.setAttribute(Constants.Devices, doRefresh.getmDevicesArray());
				request.setAttribute(Constants.Source, sourceHost);
				request.setAttribute(Constants.Destination, destinationHost);
				request.setAttribute(Constants.RoutesNumber, "0");
				request.setAttribute(Constants.Error, Constants.TopologyError);
			} else {
				int activeRoute = -1;
				for (int i = 0; i < mKShortest.getAlgoResult().size(); i++) {
					GraphPath<Nodes, DefaultEdge> mGraphPath = mKShortest.getAlgoResult().get(i);
					ArrayList<String> newRoute = new ArrayList<String>();
					newRoute.add(sourceHost);
					for (int j = 0; j < mGraphPath.getEdgeList().size(); j++) {

						Nodes sourceNode = (Nodes) mGraphPath.getEdgeList().get(j).getSource();
						Nodes targetNode = (Nodes) mGraphPath.getEdgeList().get(j).getTarget();

						if (newRoute.get(j).equals(sourceNode.getID())) {
							newRoute.add(targetNode.getID());
						} else if (newRoute.get(j).equals(targetNode.getID())) {
							newRoute.add(sourceNode.getID());
						}
					}

					if (checkIfActive(newRoute, request, response)) {
						activeRoute = i;
					}

					alternativeRoutes.add(newRoute);
				}

				// Put objects in the request so we can use them later in the
				// .jsp
				request.setAttribute(Constants.DevicesNumber, doRefresh.getmDevicesArray().size());
				request.setAttribute(Constants.Devices, doRefresh.getmDevicesArray());
				request.setAttribute(Constants.Source, sourceHost);
				request.setAttribute(Constants.Destination, destinationHost);
				request.setAttribute("ActiveRoute", activeRoute);
				request.setAttribute(Constants.Routes, alternativeRoutes);
				request.setAttribute(Constants.RoutesNumber, alternativeRoutes.size());
				request.setAttribute(Constants.Error, "0");

				// Redirect to the .jsp
				request.getRequestDispatcher("WEB-INF/showInfo.jsp").forward(request, response);
			}
		}
	}

	private boolean checkIfActive(ArrayList<String> newRoute, HttpServletRequest request, HttpServletResponse response)
			throws JsonParseException, JsonMappingException, IOException, ServletException {
		/*
		 * String src = newRoute.get(0); String dst =
		 * newRoute.get(newRoute.size() - 1); ObjectMapper mMapper = new
		 * ObjectMapper(); for (int i = 1; i < (newRoute.size() - 1); i++) {
		 * ArrayList<Flow> mFlowArray; mFlowArray = mMapper.readValue(
		 * getArrayFromJSON(Constants.IP + "/wm/core/switch/" + newRoute.get(i)
		 * + "/flow/json", request, response), new
		 * TypeReference<ArrayList<Flow>>() { }); }
		 */
		return false;
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
}
