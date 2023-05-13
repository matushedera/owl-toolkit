package sk.matfyz.owltoolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLObjectProperties {

	public static void main(String... args) throws Exception {

		if (args.length == 0 || args.length > 2) {
			System.err.println("Usage: OWLObjectProperties input_file\nOr: OWLObjectProperties input_file output_file");
			System.exit(0);
		}
		
		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));

		System.err.println("Ontology " + ontology.getOntologyID().getOntologyIRI());

		if (args.length == 1) {
			ontology.objectPropertiesInSignature().forEach(System.out::println);
			return;
		}

		FileOutputStream outputFOS = new FileOutputStream(args[1]);

		ontology.objectPropertiesInSignature().forEach(
			ind ->  {
				try {
					outputFOS.write(ind.toString().getBytes());
					outputFOS.write(System.getProperty("line.separator").getBytes());
				} catch (IOException e) {
					System.err.println("File " + args[1] + " not found.");
				}
			}
		);

		outputFOS.close();

	}

}
