import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;

import java.util.ArrayList;
import java.util.Collection;

import edu.iastate.cs311.f13.hw6.IGraph;
import edu.iastate.cs311.f13.hw6.IGraph.Pair;

/**
 * Test IGraph implementation class.
 */
public class TestGraph {
    /** Number of vertices to use for testing. */
    private static final int MAX_VERTICES = 10;

    /**
     * Test that creating a new instance of the IGraph implementation doens't
     * give us an exception.
     *
     * If it does, check the TestRunner.java file to make sure your IGraph
     * implementation class is being imported and set correctly.
     */
    @Test
    public final void testCreateEmptyGraph() {
        TestRunner.newGraph();
    }

    /**
     * Test the empty graph.
     */
    @Test
    public final void testEmptyGraph() {
        IGraph g = TestRunner.newGraph();

        Collection<String> actual = g.getVertices();

        assertTrue("Check the graph has no vertices", actual.isEmpty());
    }

    /**
     * Test if adding one vertex works.
     */
    @Test
    public final void testAddOneVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);

        Collection<String> actual = g.getVertices();

        assertThat("Check the added vertex was added", actual, hasItems(v));
    }

    /**
     * Test that adding multiple vertices works.
     */
    @Test
    public final void testAddMultipleVertices() {
        IGraph g = TestRunner.newGraph();
        ArrayList<String> vertices = new ArrayList<String>();

        for (char c = 'A'; c < 'A' + MAX_VERTICES; c += 1) {
            String s = "" + c;
            vertices.add(s);
            g.addVertex(s);
        }

        Collection<String> actual = g.getVertices();


        for (char c = 'A'; c < 'A' + MAX_VERTICES; c += 1) {
            String s = "" + c;
            assertThat("Check for each added vertex", actual, hasItem(s));
        }
    }

    /**
     * Test deleting of a single vertex.
     */
    @Test
    public final void testDeleteSingleVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);
        g.deleteVertex(v);

        Collection<String> actual = g.getVertices();

        assertTrue("Check for empty set of vertices", actual.isEmpty());
    }

    /**
     * Test deleting of multiple vertices.
     */
    @Test
    public final void testDeleteMultipleVertices() {
        IGraph g = TestRunner.newGraph();

        for (char c = 'A'; c < 'A' + MAX_VERTICES; c += 1) {
            String s = "" + c;
            g.addVertex(s);
        }

        for (char c = 'A'; c < 'A' + MAX_VERTICES; c += 1) {
            String s = "" + c;
            g.deleteVertex(s);
        }

        Collection<String> actual = g.getVertices();

        assertTrue("Check for added vertices", actual.isEmpty());
    }

    /**
     * Test of a vertex with no outgoing edges.
     */
    @Test
    public final void testEmptyEdgesForSingleVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(v);

        assertTrue("Single vertex shouldn't have any edges", actual.isEmpty());
    }

    /**
     * Test of multiple vertices with no outgoing edges.
     */
    @Test
    public final void testEmptyEdgesForTwoVertices() {
        IGraph g = TestRunner.newGraph();
        String v = "A";
        String u = "B";

        g.addVertex(v);
        g.addVertex(u);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(v);

        assertTrue("Check the first vertex has no outgoing edges", actual.isEmpty());

        actual = g.getOutgoingEdges(u);

        assertTrue("Check the second vertex has no outgoing edges", actual.isEmpty());
    }

    /**
     * Test of multiple outgoing edges.
     */
    @Test
    public void testMultipleEdgesSimple() {
        IGraph g = TestRunner.newGraph();
        String v = "A";
        String u = "B";
        String w = "C";

        Pair<String, String> e1 = new Pair(v, u);
        Pair<String, String> e2 = new Pair(u, w);

        g.addVertex(v);
        g.addVertex(u);
        g.addVertex(w);

        g.addEdge(e1);
        g.addEdge(e2);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(v);

        assertThat("First vertex should have one edge", actual, hasItems(e1));

        actual = g.getOutgoingEdges(u);

        assertThat("Second vertex should have one edge", actual, hasItems(e2));

        actual = g.getOutgoingEdges(w);

        assertTrue("Third vertex should have no edges", actual.isEmpty());
    }

    /**
     * Test that deleting a vertex removes all edges.
     */
    @Test
    public final void testEdgesForDeletedVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";
        String u = "B";

        Pair<String, String> e1 = new Pair(v, u);

        g.addVertex(v);
        g.addVertex(u);

        g.addEdge(e1);

        g.deleteVertex(v);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(v);

        assertNull("Deleted vertex should have undefined edges", actual);

        actual = g.getOutgoingEdges(u);

        assertTrue("Single vertex should have no edges", actual.isEmpty());
    }

    /**
     * Test that a nonexistant vertex returns no edges.
     */
    @Test
    public final void testEdgesForNonexistantVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";
        String u = "B";

        g.addVertex(v);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(u);

        assertNull("Nonexistant vertex should have undefined edges", actual);
    }
}
