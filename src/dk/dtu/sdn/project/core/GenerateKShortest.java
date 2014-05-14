package dk.dtu.sdn.project.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.KShortestPaths;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import dk.dtu.sdn.project.helpers.Device;
import dk.dtu.sdn.project.helpers.Link;
import dk.dtu.sdn.project.model.Nodes;

public class GenerateKShortest {

	private HashMap<String, Nodes> mNodesHash = new HashMap<String, Nodes>();
	private List<GraphPath<Nodes, DefaultEdge>> algoResult;

	public List<GraphPath<Nodes, DefaultEdge>> getAlgoResult() {
		return algoResult;
	}

	public GenerateKShortest(RefreshTopology doRefresh, String sourceHost, String destinationHost, int k) {
		// Add to Nodes array
		ArrayList<Nodes> mNodesArray = new ArrayList<Nodes>();

		if (doRefresh.getmDevicesArray() != null) {

			mNodesArray.addAll(doRefresh.getmDevicesArray());
			mNodesArray.addAll(doRefresh.getmSwitchesArray());
			for (Nodes mNode : mNodesArray) {
				mNodesHash.put(mNode.getID(), mNode);
			}

			// Create undirected graph structure
			UndirectedGraph<Nodes, DefaultEdge> mGraph = new SimpleGraph<Nodes, DefaultEdge>(DefaultEdge.class);

			// Add nodes
			for (Nodes mNode : mNodesArray) {
				mGraph.addVertex(mNode);
			}

			// Add links for hosts
			for (Device mDevice : doRefresh.getmDevicesArray()) {
				if (mDevice.getAttachmentPoint().length > 0) {
					mGraph.addEdge(mNodesHash.get(mDevice.getAttachmentPoint()[0].getSwitchDPID()),
							mNodesHash.get(mDevice.getID()));
				}
			}

			// Add links for switches
			for (Link mLink : doRefresh.getmLinkArray()) {
				mGraph.addEdge(mNodesHash.get(mLink.getsrcswitch()), mNodesHash.get(mLink.getdstswitch()));
			}

			KShortestPaths<Nodes, DefaultEdge> ksp = new KShortestPaths<Nodes, DefaultEdge>(mGraph,
					mNodesHash.get(sourceHost), k);
			algoResult = ksp.getPaths(mNodesHash.get(destinationHost));
		}
	}
}
