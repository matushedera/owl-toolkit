package sk.matfyz.owltoolkit;

import java.io.File;
import java.util.Optional;
import java.io.FileOutputStream;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class OWLClasses {

	public static void main(String... args) throws Exception {

		if (args.length == 0 || args.length > 2) {
			System.err.println("Usage: OWLClasses input_file\nOr: OWLClasses input_file output_file");
			System.exit(0);
		}

		OWLOntology ontology = OWLManager.createOWLOntologyManager()
				.loadOntologyFromOntologyDocument(new File(args[0]));

		final Optional<IRI> ontologyIRI = ontology.getOntologyID().getOntologyIRI();

		ontologyIRI.ifPresent(iri -> System.err.println("Ontology: " + iri));

		if (args.length == 1) {
			ontology.classesInSignature().forEach(System.out::println);
			return;
		}

		FileOutputStream outputFOS = new FileOutputStream(args[1]);

		ontology.classesInSignature().forEach(
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
