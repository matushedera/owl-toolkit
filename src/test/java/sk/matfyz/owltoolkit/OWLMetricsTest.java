package sk.matfyz.owltoolkit;

import java.io.IOException;
import sk.matfyz.owltoolkit.OWLMetrics;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import org.junit.Test;


public class OWLMetricsTest {

	@Test
	public void test() throws OWLOntologyCreationException, IOException {
		OWLMetrics.main("./src/test/resources/pizza.owl");
	}

}
