import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import edu.iastate.cs311.f13.hw6.*;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestGraph.class,
    TestMaxFlow.class,
    TestTopologicalSort.class,
})

/**
 * Almost empty class for the suites.
 */
public final class TestRunner {
    /**
     * Create a new instance of the IGraph class automatically.
     * @return An instance that adheres to the IGraph interface
     *
     * Replace `Graph` class with the name of your implementation of the IGraph
     * interface.
     *
     */
    public static IGraph newGraph() {
        return new Graph();
    }

    /**
     * Create a new instance of the ITopologicalSortAlgorithms class
     * automatically.
     *
     * @return An instance that adheres to the ITopologicalSort interface
     *
     * Replace `TopologicalSort` class with the name of your implementation of the
     * ITopologicalSort interface.
     *
     */
    public static ITopologicalSortAlgorithms newTopoSort() {
        return new TopologicalSortAlgorithms();
    }

    /**
     * Create a new instance of the IMaxFlowAlgorithms class automatically.
     *
     * @return An instance that adheres to the ITopologicalSort interface
     *
     * Replace `TopologicalSort` class with the name of your implementation of the
     * ITopologicalSort interface.
     *
     */
    public static IMaxFlowAlgorithms newMaxFlow() {
        return new MaxFlowAlgorithms();
    }
}
