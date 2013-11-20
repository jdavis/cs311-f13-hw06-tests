import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;
import org.hamcrest.Matcher;
import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.List;
import java.util.Arrays;

import edu.iastate.cs311.f13.hw6.IGraph;
import edu.iastate.cs311.f13.hw6.IGraph.Pair;
import edu.iastate.cs311.f13.hw6.ITopologicalSortAlgorithms;

/**
 * Test topological algorithms.
 */
public class TestTopologicalSort {
    @Test
    public final void testTopoForEmptyGraph() {
        IGraph g = TestRunner.newGraph();
        ITopologicalSortAlgorithms topo = TestRunner.newTopoSort();

        List<String> actual = topo.topologicalSort(g);

        assertTrue("Empty topo sort should be empty", actual.isEmpty());
    }

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

        assertThat("Simple topo sort with 2 vertices", actual, anyOf(expected));
    }
}
