import org.junit.Test;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matcher;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import edu.iastate.cs311.f13.hw6.IGraph;
import edu.iastate.cs311.f13.hw6.IGraph.Pair;
import edu.iastate.cs311.f13.hw6.ITopologicalSortAlgorithms;

/**
 * Test topological algorithms.
 */
public class TestTopologicalSort {
    /**
     * Test toposort for empty graph.
     */
    @Test
    public final void testTopoForEmptyGraph() {
        IGraph g = TestRunner.newGraph();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        List<String> actual = topo.topologicalSort(g);

        assertThat("Empty topo sort should be empty", actual, is(empty()));
    }

    /**
     * Test toposort for single vertex graph.
     */
    @Test
    public final void testTopoForSimpleGraph() {
        IGraph g = TestRunner.newGraph();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v = "A";

        g.addVertex(v);

        List<String> expected = Arrays.asList(v);
        List<String> actual = topo.topologicalSort(g);

        assertThat("Simple topo sort for single vertex", actual, equalTo(expected));
    }

    /**
     * Test topograph for graph with two vertices.
     */
    @Test
    public final void testTopoForSimpleWithTwoVertices() {
        IGraph g = TestRunner.newGraph();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v = "A";
        String u = "B";

        g.addVertex(v);
        g.addVertex(u);

        Pair<String, String> e1 = new Pair<String, String>(v, u);

        g.addEdge(e1);

        List<String> expected = Arrays.asList(v, u);
        List<String> actual = topo.topologicalSort(g);

        assertThat("Simple topo sort with 2 vertices", actual, equalTo(expected));
    }

    /**
     * Test toposort with 7 vertices and 6 edges.
     *
     * There are ~300 different possible toposorts for this given DAG. The
     * total combinations of vertices is of course 7! = 5040;
     */
    @Test
    public final void testTopoExtreme() {
        IGraph g = TestRunner.newGraph();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "1";
        String v2 = "2";
        String v3 = "3";
        String v4 = "4";
        String v5 = "5";
        String v6 = "6";
        String v7 = "7";

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);

        Pair<String, String> e1 = new Pair<String, String>(v1, v4);
        Pair<String, String> e2 = new Pair<String, String>(v2, v3);
        Pair<String, String> e3 = new Pair<String, String>(v2, v4);
        Pair<String, String> e4 = new Pair<String, String>(v3, v4);
        Pair<String, String> e5 = new Pair<String, String>(v3, v7);
        Pair<String, String> e6 = new Pair<String, String>(v6, v7);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);

        Matcher<?>[] validTopos = {
            equalTo(Arrays.asList(v1, v2, v3, v4, v5, v6, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v4, v5, v6, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v4, v5, v6, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v5, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v5, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v5, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v3, v5, v1, v4, v6, v7)),
            equalTo(Arrays.asList(v1, v2, v5, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v1, v5, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v5, v1, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v2, v5, v3, v1, v4, v6, v7)),
            equalTo(Arrays.asList(v1, v5, v2, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v5, v1, v2, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v5, v2, v1, v3, v4, v6, v7)),
            equalTo(Arrays.asList(v5, v2, v3, v1, v4, v6, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v5, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v5, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v5, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v5, v1, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v5, v6, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v5, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v5, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v1, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v3, v1, v6, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v3, v6, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v5, v2, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v5, v1, v2, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v1, v3, v6, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v3, v1, v6, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v3, v6, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v5, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v5, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v1, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v6, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v5, v6, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v5, v2, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v1, v2, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v1, v6, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v6, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v2, v6, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v5, v6, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v1, v6, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v6, v1, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v6, v2, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v5, v6, v2, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v5, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v3, v5, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v1, v5, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v5, v1, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v5, v6, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v5, v6, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v5, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v5, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v1, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v3, v1, v6, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v3, v6, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v3, v6, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v5, v2, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v5, v1, v2, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v1, v3, v6, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v3, v1, v6, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v3, v6, v1, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v3, v6, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v5, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v5, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v1, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v6, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v6, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v5, v6, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v5, v2, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v1, v2, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v1, v6, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v6, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v6, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v5, v2, v6, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v5, v6, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v1, v6, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v6, v1, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v6, v2, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v5, v6, v2, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v5, v6, v2, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v3, v4, v6, v5, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v4, v6, v5, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v4, v6, v5, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v4, v5, v7)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v4, v5, v7)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v4, v5, v7)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v4, v5, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v3, v6, v5, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v5, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v3, v5, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v5, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v5, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v3, v5, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v6, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v1, v6, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v1, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v5, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v2, v6, v5, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v6, v2, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v1, v2, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v1, v5, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v5, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v2, v5, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v6, v5, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v1, v5, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v5, v1, v2, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v5, v2, v1, v3, v4, v7)),
            equalTo(Arrays.asList(v6, v5, v2, v3, v1, v4, v7)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v5, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v5, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v5, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v5, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v5, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v5, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v5, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v5, v1, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v5, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v6, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v1, v6, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v1, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v5, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v5, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v2, v6, v5, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v6, v2, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v1, v2, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v1, v5, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v5, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v5, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v6, v2, v5, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v6, v5, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v1, v5, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v5, v1, v2, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v5, v2, v1, v3, v7, v4)),
            equalTo(Arrays.asList(v6, v5, v2, v3, v1, v7, v4)),
            equalTo(Arrays.asList(v6, v5, v2, v3, v7, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v3, v4, v6, v7, v5)),
            equalTo(Arrays.asList(v2, v1, v3, v4, v6, v7, v5)),
            equalTo(Arrays.asList(v2, v3, v1, v4, v6, v7, v5)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v4, v7, v5)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v4, v7, v5)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v4, v7, v5)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v4, v7, v5)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v3, v6, v7, v1, v4, v5)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v7, v4, v5)),
            equalTo(Arrays.asList(v2, v6, v3, v7, v1, v4, v5)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v7, v4, v5)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v7, v4, v5)),
            equalTo(Arrays.asList(v6, v2, v3, v7, v1, v4, v5)),
            equalTo(Arrays.asList(v1, v2, v3, v6, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v1, v3, v6, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v3, v1, v6, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v1, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v7, v1, v5, v4)),
            equalTo(Arrays.asList(v2, v3, v6, v7, v5, v1, v4)),
            equalTo(Arrays.asList(v1, v2, v6, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v1, v6, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v6, v1, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v1, v7, v5, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v7, v1, v5, v4)),
            equalTo(Arrays.asList(v2, v6, v3, v7, v5, v1, v4)),
            equalTo(Arrays.asList(v1, v6, v2, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v6, v1, v2, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v6, v2, v1, v3, v7, v5, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v1, v7, v5, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v7, v1, v5, v4)),
            equalTo(Arrays.asList(v6, v2, v3, v7, v5, v1, v4)),
        };

        List<Matcher<?>> expected = Arrays.asList(validTopos);
        List<String> actual = topo.topologicalSort(g);

        assertThat("Extreme topo sort test", actual, anyOf(expected));
    }

    /**
     * Test paralell scheduling with empty graph.
     */
    @Test
    public final void testSchedulingForEmptyGraph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        int expected = 0;
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 0", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with single vertex graph.
     */
    @Test
    public final void testSchedulingForSingleVertexGraph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v = "A";
        int vTime = 42;

        g.addVertex(v);
        times.put(v, vTime);

        int expected = vTime;
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 42", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with simple graph.
     */
    @Test
    public final void testSchedulingForSimpleGraph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 42;

        String v2 = "B";
        int v2Time = 3;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);

        g.addVertex(v1);
        g.addVertex(v2);

        times.put(v1, v1Time);
        times.put(v2, v2Time);

        g.addEdge(e1);

        int expected = v1Time + v2Time;
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 42", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with three vertex graph.
     */
    @Test
    public final void testSchedulingFor3Graph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 42;

        String v2 = "B";
        int v2Time = 3;

        String v3 = "C";
        int v3Time = 7;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);
        Pair<String, String> e2 = new Pair<String, String>(v1, v3);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);

        times.put(v1, v1Time);
        times.put(v2, v2Time);
        times.put(v3, v3Time);

        g.addEdge(e1);
        g.addEdge(e2);

        int expected = v1Time + Math.max(v2Time, v3Time);
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 42 + max(3, 7)", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with multiple components.
     */
    @Test
    public final void testSchedulingForUnevenDepthComponentGraph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 42;

        String v2 = "B";
        int v2Time = 3;

        String v3 = "C";
        int v3Time = 7;

        String v4 = "D";
        int v4Time = 10;

        String v5 = "E";
        int v5Time = 10;

        String v6 = "F";
        int v6Time = 10;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);
        Pair<String, String> e2 = new Pair<String, String>(v1, v3);

        Pair<String, String> e3 = new Pair<String, String>(v4, v5);
        Pair<String, String> e4 = new Pair<String, String>(v4, v6);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);

        times.put(v1, v1Time);
        times.put(v2, v2Time);
        times.put(v3, v3Time);

        times.put(v4, v4Time);
        times.put(v5, v5Time);
        times.put(v6, v6Time);

        g.addEdge(e1);
        g.addEdge(e2);

        g.addEdge(e3);
        g.addEdge(e4);

        int expected = Math.max(v1Time + Math.max(v2Time, v3Time), v4Time + Math.max(v5Time, v6Time));
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be max(42 + max(3, 7, 10 + max(10, 10)))", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with uneven depth components.
     */
    @Test
    public final void testSchedulingForMulitpleComponentGraph() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 42;

        String v2 = "B";
        int v2Time = 3;

        String v3 = "C";
        int v3Time = 7;

        String v4 = "D";
        int v4Time = 5;

        String v5 = "E";
        int v5Time = 5;

        String v6 = "F";
        int v6Time = 5;

        String v7 = "G";
        int v7Time = 5;

        String v8 = "H";
        int v8Time = 5;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);
        Pair<String, String> e2 = new Pair<String, String>(v1, v3);

        Pair<String, String> e3 = new Pair<String, String>(v4, v5);
        Pair<String, String> e4 = new Pair<String, String>(v4, v6);
        Pair<String, String> e5 = new Pair<String, String>(v6, v7);
        Pair<String, String> e6 = new Pair<String, String>(v7, v8);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);

        times.put(v1, v1Time);
        times.put(v2, v2Time);
        times.put(v3, v3Time);

        times.put(v4, v4Time);
        times.put(v5, v5Time);
        times.put(v6, v6Time);
        times.put(v7, v7Time);
        times.put(v8, v8Time);

        g.addEdge(e1);
        g.addEdge(e2);

        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);

        int expected = Math.max(v1Time + Math.max(v2Time, v3Time), v4Time + Math.max(v5Time, v6Time) + v7Time + v8Time);
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be max(42 + max(3, 7), 5 + max(5, 5) + 5 + 5)", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with uneven depth components and one large
     * component.
     */
    @Test
    public final void testSchedulingWithLargeComponent() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 1;

        String v2 = "B";
        int v2Time = 1;

        String v3 = "C";
        int v3Time = 1;

        String v4 = "D";
        int v4Time = 1;

        String v5 = "E";
        int v5Time = 1;

        String v6 = "F";
        int v6Time = 1;

        String v7 = "G";
        int v7Time = 1;

        String v8 = "H";
        int v8Time = 1;

        String v9 = "I";
        int v9Time = 20;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);
        Pair<String, String> e2 = new Pair<String, String>(v1, v3);

        Pair<String, String> e3 = new Pair<String, String>(v4, v5);
        Pair<String, String> e4 = new Pair<String, String>(v4, v6);
        Pair<String, String> e5 = new Pair<String, String>(v6, v7);
        Pair<String, String> e6 = new Pair<String, String>(v7, v8);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);
        g.addVertex(v5);
        g.addVertex(v6);
        g.addVertex(v7);
        g.addVertex(v8);
        g.addVertex(v9);

        times.put(v1, v1Time);
        times.put(v2, v2Time);
        times.put(v3, v3Time);

        times.put(v4, v4Time);
        times.put(v5, v5Time);
        times.put(v6, v6Time);
        times.put(v7, v7Time);
        times.put(v8, v8Time);

        times.put(v9, v9Time);

        g.addEdge(e1);
        g.addEdge(e2);

        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);
        g.addEdge(e6);

        int expected = v9Time;
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 20", actual, equalTo(expected));
    }

    /**
     * Test paralell scheduling with large child.
     */
    @Test
    public final void testSchedulingWithLargeChild() {
        IGraph g = TestRunner.newGraph();
        HashMap<String, Integer> times = new HashMap<String, Integer>();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        String v1 = "A";
        int v1Time = 2;

        String v2 = "B";
        int v2Time = 2;

        String v3 = "C";
        int v3Time = 8;

        String v4 = "D";
        int v4Time = 10;

        Pair<String, String> e1 = new Pair<String, String>(v1, v2);
        Pair<String, String> e2 = new Pair<String, String>(v1, v3);
        Pair<String, String> e3 = new Pair<String, String>(v2, v4);

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        times.put(v1, v1Time);
        times.put(v2, v2Time);
        times.put(v3, v3Time);
        times.put(v4, v4Time);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);

        int expected = v1Time + Math.max(v2Time + v4Time, v3Time);
        int actual = topo.minScheduleLength(g, times);

        assertThat("Minimum schedule should be 2 + max(2 + 10, 8)", actual, equalTo(expected));
    }
}
