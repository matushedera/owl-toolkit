package sk.matfyz.owltoolkit;
import java.io.File;
import java.util.Set;

import java.io.FileOutputStream;
import java.io.IOException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomVisitor;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;
import org.semanticweb.owlapi.profiles.OWLProfileViolation;


public class OWLProfilization {
	public static void main(String... args) throws Exception {
		if (args.length != 3){
			System.err.println("Usage: OWLProfilization {-rl | -el | -ql} input_file output_file");
			System.exit(0);
		}
		
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[1]));

		OWLProfile profile;
		switch (args[0]) {
		case "-rl":
			profile = new OWL2RLProfile();
			break;
		case "-el":
			profile = new OWL2ELProfile();
			break;
		case "-ql":
			profile = new OWL2QLProfile();
			break;
		default:
			throw new IllegalStateException("Unknown profile: " + args[0]);
		}

		ontology = new OWLEquvilanceToSubsumptionsConverter().convert(ontology,
				manager);
		
		OWLProfileReport report = profile.checkOntology(ontology);

		System.err.println(report.toString());

		for (OWLProfileViolation violation : report.getViolations()) {
			OWLAxiom axiom = violation.getAxiom();
			if (axiom != null){
				manager.removeAxiom(ontology, axiom);
			}
		}

		FileOutputStream outputFOS = new FileOutputStream(args[2]);

		manager.saveOntology(ontology, outputFOS);

		outputFOS.close();
	}

}

class OWLEquvilanceToSubsumptionsConverter implements OWLAxiomVisitor {

	OWLOntology ont;
	private OWLOntologyManager manager;

	public OWLOntology convert(OWLOntology ontology, OWLOntologyManager manager) {
		this.ont = ontology;
		this.manager = manager;
		
		for(OWLAxiom axiom : ontology.getAxioms()){
			axiom.accept(this);
		}
		
		return this.ont;
	}

	@Override
	public void visit(OWLEquivalentClassesAxiom axiom) {

		Set<OWLClassExpression> classes = axiom.getClassExpressions();
		for (OWLClassExpression cls1 : classes) {
			for (OWLClassExpression cls2 : classes) {
				if (cls1 != cls2) {
					manager.addAxiom(ont, manager.getOWLDataFactory()
							.getOWLSubClassOfAxiom(cls1, cls2));
				}
			}
		}

		manager.removeAxiom(ont, axiom);
	}

}
