import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.hasItem;

import org.junit.Test;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collection;

import edu.iastate.cs311.f13.hw6.IGraph;
import edu.iastate.cs311.f13.hw6.IGraph.Pair;

/**
 * Test IGraph implementation class.
 */
public class TestGraph {

    /**
     * Test that creating a new instance of the IGraph implementation doens't
     * give us an exception.
     *
     * If it does, check the TestRunner.java file to make sure your IGraph
     * implementation class is being imported and set correctly.
     */
    @Test
    public void testCreateEmptyGraph() {
        TestRunner.newGraph();
    }

    @Test
    public void testEmptyGraph() {
        IGraph g = TestRunner.newGraph();

        Collection<String> actual = g.getVertices();

        assertTrue("Check the graph has no vertices", actual.isEmpty());
    }

    @Test
    public void testAddOneVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);

        Collection<String> actual = g.getVertices();

        assertThat("Check the added vertex was added", actual, hasItems(v));
    }

    @Test
    public void testAddMultipleVertices() {
        IGraph g = TestRunner.newGraph();
        ArrayList<String> vertices = new ArrayList<String>();

        for (char c = 'A'; c < 'A' + 10; c += 1) {
            String s = "" + c;
            vertices.add(s);
            g.addVertex(s);
        }

        Collection<String> expected = (Collection<String>) vertices;
        Collection<String> actual = g.getVertices();


        for (char c = 'A'; c < 'A' + 10; c += 1) {
            String s = "" + c;
            assertThat("Check for each added vertex", actual, hasItem(s));
        }
    }

    @Test
    public void testDeleteSingleVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);
        g.deleteVertex(v);

        Collection<String> actual = g.getVertices();

        assertTrue("Check for empty set of vertices", actual.isEmpty());
    }

    @Test
    public void testDeleteMultipleVertices() {
        IGraph g = TestRunner.newGraph();

        for (char c = 'A'; c < 'A' + 10; c += 1) {
            String s = "" + c;
            g.addVertex(s);
        }

        for (char c = 'A'; c < 'A' + 10; c += 1) {
            String s = "" + c;
            g.deleteVertex(s);
        }

        Collection<String> actual = g.getVertices();

        assertTrue("Check for added vertices", actual.isEmpty());
    }

    @Test
    public void testEmptyEdgesForSingleVertex() {
        IGraph g = TestRunner.newGraph();
        String v = "A";

        g.addVertex(v);

        Collection<Pair<String, String>> actual = g.getOutgoingEdges(v);

        assertTrue("Check a single vertex has no outgoing edges", actual.isEmpty());
    }

    @Test
    public void testEmptyEdgesForTwoVertices() {
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

        assertThat("Check the first vertex has 1 outgoing edge", actual, hasItems(e1));

        actual = g.getOutgoingEdges(u);

        assertThat("Check the second vertex has 1 outgoing edge", actual, hasItems(e2));

        actual = g.getOutgoingEdges(w);

        assertTrue("Check third vertex has no outgoing edges", actual.isEmpty());
    }
}
