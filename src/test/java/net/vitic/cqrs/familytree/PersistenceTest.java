package net.vitic.cqrs.familytree;

import net.vitic.cqrs.familytree.port.adaptor.persistence.Database;
import org.flywaydb.core.Flyway;
import org.junit.After;
import org.junit.Before;

public class PersistenceTest {

    @Before
    public void setUp() {
        Database.url = "jdbc:h2:file:./build/tmp/h2/familytree";
        Database.migrate();
    }

    @After
    public void tearDown() {
        Flyway flyway = new Flyway();
        flyway.setDataSource(Database.url, "sa", null);
        flyway.clean();
    }
}
