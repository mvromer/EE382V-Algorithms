package pset4;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.TreeSet;
import java.util.Set;
import org.junit.Test;

public class GraphTester {
    // tests for method "addEdge" in class "Graph"
    @Test
    public void tae0() {
        Graph g = new Graph();
        g.addEdge( 0, 1 );
        assertEquals( g.toString(), "nodes=[0, 1]; edges={0=[1]}");

        // NOTE: The following was the original assertion given in the homework; however, since it
        // was expecting a different format than what the Graph class actually produces, I rewrote
        // it above so that it matches with what the Graph.toString method produces.
        //
        //assertEquals(g.toString(), "nodes: [0, 1]\nedges: {0=[1]}");
    }

    // your tests for method "addEdge" in class "Graph" go here

    // you must provide at least 4 test methods;
    // each test method has at least 1 invocation of addEdge;
    // each test method has at least 1 test assertion;
    // your test methods provide full statement coverage of your
    //   implementation of addEdge and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    // Test to make sure addEdge operation is idempotent.
    //
    // Graph structure: Two-node graph.
    // Nodes, 0, 1
    // Edges: 0 -> 1
    //
    @Test
    public void tae1() {
        Graph g = new Graph();
        final int numTries = 10; // Pick your favorite number > 1 and less than Integer.MAX_VALUE.
        for( int iTry = 0; iTry < numTries; ++iTry ) {
            g.addEdge( 0, 1 );
            assertEquals( g.toString(), "nodes=[0, 1]; edges={0=[1]}" );
        }
    }

    // Test to make sure addEdge represents self loops implicitly.
    //
    // Graph structure: One-node graph.
    // Nodes: 0
    // Edges: None
    //
    @Test
    public void tae2() {
        Graph g = new Graph();
        g.addEdge( 0, 0 );
        assertEquals( g.toString(), "nodes=[0]; edges={0=[]}" );
    }

    // Test incremental edge additions don't create duplicate objects.
    //
    // Graph structure: Four-node graph.
    // Nodes: 0, 1, 2, 3
    // Edges: 0 -> 1
    //        0 -> 2
    //        0 -> 3
    //
    @Test
    public void tae3() {
        Graph g = new Graph();
        g.addEdge( 0, 1 );
        g.addEdge( 0, 2 );
        g.addEdge( 0, 3 );
        assertEquals( g.toString(), "nodes=[0, 1, 2, 3]; edges={0=[1, 2, 3]}" );
    }

    // Test back edges are added correctly.
    //
    // Graph structure: Two-node graph.
    // Nodes: 0, 1
    // Edges: 0 -> 1
    //        1 -> 0
    //
    @Test
    public void tae4() {
        Graph g = new Graph();
        g.addEdge( 0, 1 );
        g.addEdge( 1, 0 );
        assertEquals( g.toString(), "nodes=[0, 1]; edges={0=[1], 1=[0]}" );
    }

    // Test can add nodes explicitly before adding edges on those nodes.
    //
    // Graph structure: Three-node graph.
    // Nodes: 0, 1, 2
    // Edges: 0 -> 1 -> 2
    //
    @Test
    public void tae5() {
        Graph g = new Graph();
        g.addNode( 0 );
        g.addNode( 1 );
        g.addNode( 2 );
        g.addEdge( 0, 1 );
        g.addEdge( 1, 2 );
        assertEquals( g.toString(), "nodes=[0, 1, 2]; edges={0=[1], 1=[2]}" );
    }

    // Test can add nodes explicitly after adding edges on those nodes.
    //
    // Graph structure: Three-node graph.
    // Nodes: 0, 1, 2
    // Edges: 0 -> 1 -> 2
    //
    @Test
    public void tae6() {
        Graph g = new Graph();
        g.addEdge( 0, 1 );
        g.addEdge( 1, 2 );
        g.addNode( 0 );
        g.addNode( 1 );
        g.addNode( 2 );
        assertEquals( g.toString(), "nodes=[0, 1, 2]; edges={0=[1], 1=[2]}" );
    }

    // tests for method "unreachable" in class "Graph"
    @Test
    public void tr0() {
        Graph g = new Graph();
        g.addNode( 0 );
        Set<Integer> nodes = new HashSet<Integer>();
        nodes.add( 0 );
        assertTrue( g.unreachable( new HashSet<Integer>(), nodes ) );
    }

    // your tests for method "reachable" in class "Graph" go here

    // you must provide at least 6 test methods;
    // each test method must have at least 1 invocation of reachable;
    // each test method must have at least 1 test assertion;
    // at least 2 test methods must have at least 1 invocation of addEdge;
    // your test methods must provide full statement coverage of your
    //   implementation of reachable and any helper methods
    // no test method directly invokes any method that is not
    //   declared in the Graph class as given in this homework

    // Test to make sure false is returned when sources is not a subset of nodes.
    //
    // Graph g: Empty graph.
    //     Nodes:
    //     Edges:
    // Sources: -1
    // Targets:
    //
    @Test
    public void tr1() {
        Graph g = new Graph();
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( -1 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure false is returned when targets is not a subset of nodes.
    //
    // Graph g: Empty graph.
    //     Nodes:
    //     Edges:
    // Sources:
    // Targets: -1
    //
    @Test
    public void tr2() {
        Graph g = new Graph();
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        targets.add( -1 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure a source pointed directly at a target returns false.
    //
    // Graph g: Two-node graph.
    //     Nodes: 0, 1
    //     Edges: 0 -> 1
    // Sources: 0
    // Targets: 1
    //
    @Test
    public void tr3() {
        Graph g = new Graph();
        g.addEdge( 0, 1 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        targets.add( 1 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure a graph with only a target pointing to a source returns true.
    //
    // Graph g: Two-node graph.
    //     Nodes: 0, 1
    //     Edges: 1 -> 0
    // Sources: 0
    // Targets: 1
    //
    @Test
    public void tr4() {
        Graph g = new Graph();
        g.addEdge( 1, 0 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        targets.add( 1 );
        assertTrue( g.unreachable( sources, targets ) );
    }

    // Test to make sure a graph with at least one reachable path from a single source returns false.
    //
    // Graph g: Three-node graph.
    //     Nodes: 0, 1, 2
    //     Edges: 0 -> 2
    // Sources: 0
    // Targets: 1, 2
    //
    @Test
    public void tr5() {
        Graph g = new Graph();
        g.addEdge( 0, 2 );
        g.addNode( 1 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        targets.add( 1 );
        targets.add( 2 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure a graph with at least one reachable path to a single destination returns false.
    //
    // Graph g: Three-node graph.
    //     Nodes: 0, 1, 2
    //     Edges: 1 -> 2
    // Sources: 0, 1
    // Targets: 2
    //
    @Test
    public void tr6() {
        Graph g = new Graph();
        g.addEdge( 1, 2 );
        g.addNode( 0 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        sources.add( 1 );
        targets.add( 2 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure an implicit self-loop returns false.
    //
    // Graph g: One-node graph.
    //     Nodes: 0
    //     Edges: 0 -> 0 (implicit)
    // Sources: 0
    // Targets: 0
    //
    @Test
    public void tr7() {
        Graph g = new Graph();
        g.addNode( 0 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        targets.add( 0 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make sure an explicit self-loop returns false.
    //
    // Graph g: One-node graph.
    //     Nodes: 0
    //     Edges: 0 -> 0 (explicit)
    // Sources: 0
    // Targets: 0
    //
    @Test
    public void tr8() {
        Graph g = new Graph();
        g.addEdge( 0, 0 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();
        sources.add( 0 );
        targets.add( 0 );
        assertFalse( g.unreachable( sources, targets ) );
    }

    // Test to make disjoint graph components returns true. Note this performs multiple checks.
    //
    // Graph g: Three-node graph.
    //     Nodes: 0, 1, 2
    //     Edges:
    // Check 1:
    //     Sources: 0
    //     Targets: 1, 2
    // Check 1:
    //     Sources: 1
    //     Targets: 0, 2
    // Check 1:
    //     Sources: 2
    //     Targets: 0, 1
    //
    @Test
    public void tr9() {
        Graph g = new Graph();
        g.addNode( 0 );
        g.addNode( 1 );
        g.addNode( 2 );
        Set<Integer> sources = new HashSet<>();
        Set<Integer> targets = new HashSet<>();

        // Check 1.
        sources.add( 0 );
        targets.add( 1 );
        targets.add( 2 );
        assertTrue( g.unreachable( sources, targets ) );

        // Check 2.
        sources.clear();
        targets.clear();
        sources.add( 1 );
        targets.add( 0 );
        targets.add( 2 );
        assertTrue( g.unreachable( sources, targets ) );

        // Check 3.
        sources.clear();
        targets.clear();
        sources.add( 2 );
        targets.add( 0 );
        targets.add( 1 );
        assertTrue( g.unreachable( sources, targets ) );
    }
}
