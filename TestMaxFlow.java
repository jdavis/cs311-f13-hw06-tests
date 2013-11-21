import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.Matchers.isIn;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import edu.iastate.cs311.f13.hw6.IGraph;
import edu.iastate.cs311.f13.hw6.IGraph.Pair;
import edu.iastate.cs311.f13.hw6.IMaxFlowAlgorithms;

/**
 * Test max flow algorithms.
 */
public class TestMaxFlow {
    /** Class under test. */
    private IMaxFlowAlgorithms mMax;

    /**
     * Instantiat the class we will test.
     */
    @Before
    public final void setUp() {
        mMax = TestRunner.newMaxFlow();
    }

    public static int calcFlow(final IGraph g,
            final Map<Pair<String, String>, Integer> f,
            final String s) {
        int flow = 0;

        for (String v : g.getVertices()) {
            Pair<String, String> in = new Pair<String, String>(s, v);
            Pair<String, String> out = new Pair<String, String>(v, s);

            if (f.containsKey(in)) {
                flow += f.get(in);
            }

            if (f.containsKey(out)) {
                flow -= f.get(out);
            }
        }

        return flow;
    }

    /**
     * Test graph with two vertices but no edges.
     */
    @Test
    public final void testTwoVertexNoEdgesMaxFlow() {
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String s = "A";
        String t = "B";

        g.addVertex(s);
        g.addVertex(t);

        int actual, expected;
        Map<Pair<String, String>, Integer> max = mMax.maxFlow(g, s, t, c);

        actual = calcFlow(g, max, s);
        expected = 0;

        assertThat("Source out flow equals 0", actual, equalTo(expected));

        actual = calcFlow(g, max, t);
        expected = 0;

        assertThat("Sink in flow equals 0", actual, equalTo(expected));
    }

    /**
     * Test graph with two vertices and a single edge between them.
     */
    @Test
    public final void testTwoVertexWithEdgeMaxFlow() {
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String s = "A";
        String t = "B";

        Pair<String, String> e1 = new Pair<String, String>(s, t);

        g.addVertex(s);
        g.addVertex(t);

        g.addEdge(e1);
        c.put(e1, 9);

        int actual, expected;
        Map<Pair<String, String>, Integer> max = mMax.maxFlow(g, s, t, c);

        actual = calcFlow(g, max, s);
        expected = 9;

        assertThat("Source out flow equals 9", actual, equalTo(expected));

        actual = calcFlow(g, max, t);
        expected = -9;

        assertThat("Sink in flow equals 9", actual, equalTo(expected));
    }

    /**
     * Test max flow with triangle like graph.
     */
    @Test
    public final void testTriangleMaxFlow() {
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String s = "A";
        String v2 = "B";
        String v3 = "C";
        String t = "D";

        Pair<String, String> e1 = new Pair<String, String>(s, v2);
        int e1C = 11;
        Pair<String, String> e2 = new Pair<String, String>(s, v3);
        int e2C = 7;
        Pair<String, String> e3 = new Pair<String, String>(v2, t);
        int e3C = 3;
        Pair<String, String> e4 = new Pair<String, String>(v3, t);
        int e4C = 5;

        g.addVertex(s);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(t);

        g.addEdge(e1);
        c.put(e1, e1C);

        g.addEdge(e2);
        c.put(e2, e2C);

        g.addEdge(e3);
        c.put(e3, e3C);

        g.addEdge(e4);
        c.put(e4, e4C);

        int actual, expected;
        Map<Pair<String, String>, Integer> max = mMax.maxFlow(g, s, t, c);

        actual = calcFlow(g, max, s);
        expected = 8;

        assertThat("Source out flow equals 23", actual, equalTo(expected));

        actual = calcFlow(g, max, t);
        expected = -8;

        assertThat("Sink in flow equals 23", actual, equalTo(expected));
    }

    /**
     * Test graph with more complex graph.
     *
     * Source:
     *      Intro to Algorithms, pg. 727, Figure 26.6
     */
    @Test
    public final void testComplexMaxFlow() {
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String s = "s";
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";
        String t = "t";

        Pair<String, String> e1 = new Pair<String, String>(s, v1);
        int e1C = 16;
        Pair<String, String> e2 = new Pair<String, String>(s, v2);
        int e2C = 13;
        Pair<String, String> e3 = new Pair<String, String>(v1, v3);
        int e3C = 12;
        Pair<String, String> e4 = new Pair<String, String>(v2, v1);
        int e4C = 4;
        Pair<String, String> e5 = new Pair<String, String>(v2, v4);
        int e5C = 14;
        Pair<String, String> e6 = new Pair<String, String>(v3, v2);
        int e6C = 9;
        Pair<String, String> e7 = new Pair<String, String>(v3, t);
        int e7C = 20;
        Pair<String, String> e8 = new Pair<String, String>(v4, v3);
        int e8C = 7;
        Pair<String, String> e9 = new Pair<String, String>(v4, t);
        int e9C = 4;

        g.addVertex(s);
        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(t);

        g.addEdge(e1);
        c.put(e1, e1C);

        g.addEdge(e2);
        c.put(e2, e2C);

        g.addEdge(e3);
        c.put(e3, e3C);

        g.addEdge(e4);
        c.put(e4, e4C);

        g.addEdge(e5);
        c.put(e5, e5C);

        g.addEdge(e6);
        c.put(e6, e6C);

        g.addEdge(e7);
        c.put(e7, e7C);

        g.addEdge(e8);
        c.put(e8, e8C);

        g.addEdge(e9);
        c.put(e9, e9C);

        int actual, expected;
        Map<Pair<String, String>, Integer> max = mMax.maxFlow(g, s, t, c);

        actual = calcFlow(g, max, s);
        expected = 23;

        assertThat("Source out flow equals 23", actual, equalTo(expected));

        actual = calcFlow(g, max, t);
        expected = -23;

        assertThat("Sink in flow equals 23", actual, equalTo(expected));
    }
}
