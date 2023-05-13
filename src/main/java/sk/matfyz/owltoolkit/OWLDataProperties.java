package sk.matfyz.owltoolkit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLDataProperties {

	public static void main(String... args) throws Exception {
		if (args.length == 0 || args.length > 2) {
			System.err.println("Usage: OWLDataProperties input_file\nOr: OWLDataProperties input_file output_file");
			System.exit(0);
		}

		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));

		ontology.getOntologyID().getOntologyIRI().ifPresent(x -> System.err.println("Ontology: " + x));

		if (args.length == 1) {
			ontology.dataPropertiesInSignature().forEach(System.out::println);
			return;
		}

		FileOutputStream outputFOS = new FileOutputStream(args[1]);

		ontology.dataPropertiesInSignature().forEach(
			dat ->  {
				try {
					outputFOS.write(dat.toString().getBytes());
					outputFOS.write(System.getProperty("line.separator").getBytes());
				} catch (IOException e) {
					System.err.println("File " + args[1] + " not found.");
				}
			}
		);

		outputFOS.close();
	}

}
