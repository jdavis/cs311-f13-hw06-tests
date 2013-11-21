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

    /**
     * Test graph with two vertices but no edges.
     */
    @Test
    public final void testTwoVertexNoEdgesMaxFlow() {
        System.out.println("test1");
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        HashMap<Pair<String, String>, Integer> m = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String v = "A";
        String u = "B";

        g.addVertex(v);
        g.addVertex(u);

        Set<Map.Entry<Pair<String, String>, Integer>> actual = mMax.maxFlow(g, v, u, c).entrySet();
        Set<Map.Entry<Pair<String, String>, Integer>> expected = m.entrySet();

        assertThat("Edge with no edges should have no maxFlow", actual, equalTo(expected));
    }

    /**
     * Test graph with two vertices and a single edge between them.
     */
    @Test
    public final void testTwoVertexWithEdgeMaxFlow() {
        System.out.println("test2");
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        HashMap<Pair<String, String>, Integer> m = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String v = "A";
        String u = "B";

        Pair<String, String> e1 = new Pair<String, String>(v, u);

        g.addVertex(v);
        g.addVertex(u);

        g.addEdge(e1);
        c.put(e1, 9);
        m.put(e1, 9);

        Set<Map.Entry<Pair<String, String>, Integer>> actual = mMax.maxFlow(g, v, u, c).entrySet();
        Set<Map.Entry<Pair<String, String>, Integer>> expected = m.entrySet();

        assertThat("Two vertices with single edge should equal that edge capacity", actual, equalTo(expected));
    }

    /**
     * Test max flow with triangle like graph.
     */
    @Test
    public final void testTriangleMaxFlow() {
        System.out.println("test3");
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        HashMap<Pair<String, String>, Integer> m = new HashMap<Pair<String, String>, Integer>();
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
        m.put(e1, e3C);

        g.addEdge(e2);
        c.put(e2, e2C);
        m.put(e2, e4C);

        g.addEdge(e3);
        c.put(e3, e3C);
        m.put(e3, e3C);

        g.addEdge(e4);
        c.put(e4, e4C);
        m.put(e4, e4C);

        Set<Map.Entry<Pair<String, String>, Integer>> actual = mMax.maxFlow(g, s, t, c).entrySet();
        Set<Map.Entry<Pair<String, String>, Integer>> expected = m.entrySet();

        assertThat("Two vertices with single edge should equal that edge capacity", actual, equalTo(expected));
    }

    /**
     * Test graph with more complex graph.
     *
     * Source:
     *      Intro to Algorithms, pg. 727, Figure 26.6
     */
    @Test
    public final void testComplexMaxFlow() {
        System.out.println("test4");
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        HashMap<Pair<String, String>, Integer> m = new HashMap<Pair<String, String>, Integer>();
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
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(t);

        g.addEdge(e1);
        c.put(e1, e1C);
        m.put(e1, 12);

        g.addEdge(e2);
        c.put(e2, e2C);
        m.put(e2, 11);

        g.addEdge(e3);
        c.put(e3, e3C);
        m.put(e3, 12);

        g.addEdge(e4);
        c.put(e4, e4C);
        m.put(e4, 0);

        g.addEdge(e5);
        c.put(e5, e5C);
        m.put(e5, 11);

        g.addEdge(e6);
        c.put(e6, e6C);
        m.put(e6, 0);

        g.addEdge(e7);
        c.put(e7, e7C);
        m.put(e7, 19);

        g.addEdge(e8);
        c.put(e8, e8C);
        m.put(e8, 7);

        g.addEdge(e9);
        c.put(e9, e9C);
        m.put(e9, 4);

        Set<Map.Entry<Pair<String, String>, Integer>> actual = mMax.maxFlow(g, s, t, c).entrySet();
        Set<Map.Entry<Pair<String, String>, Integer>> expected = m.entrySet();

        assertThat("Two vertices with single edge should equal that edge capacity", actual, equalTo(expected));
    }

    /**
     * Test graph with more complex graph.
     *
     * Source:
     *      Intro to Algorithms, pg. 728, Figure 26.7
     */
    @Test
    public final void testLargeCapacityComplexMaxFlow() {
        System.out.println("test5");
        HashMap<Pair<String, String>, Integer> c = new HashMap<Pair<String, String>, Integer>();
        HashMap<Pair<String, String>, Integer> m = new HashMap<Pair<String, String>, Integer>();
        IGraph g = TestRunner.newGraph();

        String s = "A";
        String u = "B";
        String v = "C";
        String t = "D";

        Pair<String, String> e1 = new Pair<String, String>(s, u);
        int e1C = 1000000;
        Pair<String, String> e2 = new Pair<String, String>(s, v);
        int e2C = 1000000;
        Pair<String, String> e3 = new Pair<String, String>(u, v);
        int e3C = 1;
        Pair<String, String> e4 = new Pair<String, String>(u, t);
        int e4C = 1000000;
        Pair<String, String> e5 = new Pair<String, String>(v, t);
        int e5C = 1000000;

        g.addVertex(s);
        g.addVertex(u);
        g.addVertex(v);
        g.addVertex(t);

        g.addEdge(e1);
        c.put(e1, e1C);
        m.put(e1, e1C);

        g.addEdge(e2);
        c.put(e2, e2C);
        m.put(e2, e2C);

        g.addEdge(e3);
        c.put(e3, e3C);
        m.put(e3, 0);

        g.addEdge(e4);
        c.put(e4, e4C);
        m.put(e4, e4C);

        g.addEdge(e5);
        c.put(e5, e5C);
        m.put(e5, e5C);

        Set<Map.Entry<Pair<String, String>, Integer>> actual = mMax.maxFlow(g, s, t, c).entrySet();
        Set<Map.Entry<Pair<String, String>, Integer>> expected = m.entrySet();

        assertThat("Two vertices with single edge should equal that edge capacity", actual, equalTo(expected));
    }
}
