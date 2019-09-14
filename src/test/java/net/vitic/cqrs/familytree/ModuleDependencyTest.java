package net.vitic.cqrs.familytree;

import jdepend.framework.DependencyConstraint;
import jdepend.framework.JDepend;
import jdepend.framework.JavaPackage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * An Atomic & Triggered Fitness Function defined at the Technical Architectural
 * Dimension to control the directionality of coupling between components.
 */
public class ModuleDependencyTest {

    private JDepend jdepend;

    @Before
    public void setUp() throws IOException {
        jdepend = new JDepend();
        jdepend.addDirectory("build/classes/main");
    }


    @Test
    public void testMatch() throws IOException {
        DependencyConstraint constraint = new DependencyConstraint();
        JavaPackage application = constraint.addPackage("net.vitic.cqrs.familytree.application.familymember");
        JavaPackage persistence = constraint.addPackage("net.vitic.cqrs.familytree.port.adaptor.persistence");

        application.dependsUpon(persistence);
        assertTrue("Dependency mismatch", jdepend.dependencyMatch(constraint));

    }

    /**
     * Tests that a single package does not contain
     * any package dependency cycles.
     */
    @Test
    public void testOnePackage() throws IOException {
        jdepend.analyze();
        JavaPackage application = jdepend.getPackage("net.vitic.cqrs.familytree.application.familymember");
        assertFalse("Cycle exists: " + application.getName(), application.containsCycle());
    }

    /**
     * Tests that a package dependency cycle does not
     * exist for any of the analyzed packages.
     */
    @Test
    public void testCycles() throws IOException {
        jdepend.analyze();
        assertFalse("Cycles exist", jdepend.containsCycles());
    }
}
