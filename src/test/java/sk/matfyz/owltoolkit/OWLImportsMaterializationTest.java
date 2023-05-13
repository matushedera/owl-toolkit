package sk.matfyz.owltoolkit;
import sk.matfyz.owltoolkit.OWLImportsMaterialization;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;


public class OWLImportsMaterializationTest {

	@Test
	public void test() throws Exception {
		OWLImportsMaterialization.main("./src/test/resources/academy.owx", "out/materialized_academy.owl");
	}

}
