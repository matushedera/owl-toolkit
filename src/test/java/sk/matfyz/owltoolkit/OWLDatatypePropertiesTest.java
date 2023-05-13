package sk.matfyz.owltoolkit;

import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import java.io.IOException;


public class OWLDatatypePropertiesTest {

	@Test
	public void test() throws Exception {
		OWLDataProperties.main("./src/test/resources/pizza.owl");
	}

}
