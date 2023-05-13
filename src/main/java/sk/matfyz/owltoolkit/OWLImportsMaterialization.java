package sk.matfyz.owltoolkit;

import java.io.File;
import java.util.Set;
import java.util.stream.Stream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.HasAxioms;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class OWLImportsMaterialization {
	public static void main(String... args) throws Exception {
		if (args.length != 2) {
			System.err.println("Usage: OWLImportsMaterialization input_file output_file");
			System.exit(0);
		}

		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[0]));


		OWLOntologyManager manager1 = OWLManager.createOWLOntologyManager();
		OWLOntology materializedOntology = manager1.createOntology(ontology.getOntologyID());

		final Stream<OWLAxiom> axioms = ontology.importsClosure().flatMap(HasAxioms::axioms);
		manager1.addAxioms(materializedOntology, axioms);


		FileOutputStream outputFOS = new FileOutputStream(args[1]);

		manager.saveOntology(materializedOntology, outputFOS);

		outputFOS.close();
	}

}

