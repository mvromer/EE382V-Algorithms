package pset4;

import java.util.*;

public class Graph {
    private Set<Integer> nodes; // set of nodes in the graph
    private Map<Integer, List<Integer>> edges;
    // map between a node and a list of nodes that are connected to it by outgoing edges
    // class invariant: fields "nodes" and "edges" are non-null;
    //                  "this" graph has no node that is not in "nodes"

    public Graph() {
        nodes = new HashSet<Integer>();
        edges = new HashMap<Integer, List<Integer>>();
    }

    public String toString() {
        return "nodes=" + nodes + "; " + "edges=" + edges;
    }

    public void addNode( int n ) {
        // postcondition: adds the node "n" to this graph
        nodes.add( n );
    }

    public void addEdge( int from, int to ) {
        // postcondition: adds a directed edge "from" -> "to" to this graph
        addNode( from );
        addNode( to );
        List<Integer> neighbors = edges.computeIfAbsent( from, n -> new ArrayList<>() );

        // NOTE: By the definition given in our book, there's always a zero length path from a node
        // to itself. In some contexts it may be more appropriate to explicitly represent self loops
        // in a graph, but absent any other requirements, this implementation opts to not do that.
        // We ensure though that for a node n in this graph, this.unreachable( n, n ) returns false.
        if( from == to )
            return;

        // NOTE: Even though this class's data structures could potentially allow for it, there is
        // nothing in this assigment that says the Graph class has to support multiple outgoing
        // edges from a node S to a node T. As such, this implementation does not support an edge
        // multiplicity greater than one.
        if( !neighbors.contains( to ) )
            neighbors.add( to );
    }

    public boolean unreachable( Set<Integer> sources, Set<Integer> targets ) {
        if( sources == null || targets == null ) throw new IllegalArgumentException();
        // postcondition: returns true if (1) "sources" is a subset of "nodes", (2) "targets" is
        //                a subset of "nodes", and (3) for each node "m" in set "targets",
        //                there is no node "n" in set "sources" such that there is a
        //                directed path that starts at "n" and ends at "m" in "this"; and
        //                false otherwise

        // NOTE: The postcondition specifies 3 criteria connected by an AND that determine when this
        // function is to return true:
        //
        //    * sources is a subset of nodes
        //    * targets is a subset of nodes
        //    * for each target, no paths from any of the sources to that target
        //
        // Because the criteria are connected by an AND, if there is ANY source or target that is
        // NOT in this class's nodes field (and thus either sources or targets is NOT a subset of
        // nodes), then this method returns false. That may seem weird because if a target t is not
        // in the "this" graph at all, then it's definitely unreachable from any of the nodes in the
        // graph (so you would expect this method to return true); however, as per the postcondition
        // as it is written, this method will return false.
        //

        for( int target : targets ) {
            if( !nodes.contains( target ) )
                return false;
        }

        // Maintain this on the outside so we avoid constantly checking same paths that might be
        // shared between the depth-first walks of multiple sources.
        Set<Integer> visitedNodes = new HashSet<>();

        for( int source : sources ) {
            if( !nodes.contains( source ) )
                return false;

            // We want to check that none of the paths starting at each source leads to one of the
            // nodes in our set of targets. To do this, we'll do a depth-first walk starting at each
            // source and check each node along the way. If the current node in the walk is one of
            // our targets, we bail early by returning false. Otherwise we mark the node as visited
            // and continue with the walk.

            Stack<Integer> nodeStack = new Stack<>();
            nodeStack.push( source );

            while( !nodeStack.isEmpty() ) {
                int currentNode = nodeStack.pop();
                if( visitedNodes.add( currentNode ) ) {
                    // Current node hasn't been visited before. Is it a target we care about?
                    if( targets.contains( currentNode ) ) {
                        // Found a path from the current source to one of our targets. Return false.
                        return false;
                    }

                    // Push the next set of nodes in our walk. Need to make sure we have a valid
                    // list of neighbors in those cases where a node may have been added with
                    // addNode but had no outgoing edges added with addEdge.
                    List<Integer> neighbors = edges.computeIfAbsent( currentNode, n -> new ArrayList<>() );
                    for( int neighbor : neighbors ) {
                        nodeStack.push( neighbor );
                    }
                }
            }
        }

        // Couldn't find any of our targets.
        return true;
    }
}
