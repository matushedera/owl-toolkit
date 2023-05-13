owl-toolkit
===========
owl-toolkit is a set of command line tools for OWL files based on the owl-api library.

This repositary is a clone of ghxiao's project ported to newer version of Java. The aim is both to retain the original functionality and make some additional improvements.

Download precompiled binaries
---------
https://github.com/matushedera/owl-toolkit/releases

Or build from source
---------
1. `git clone https://github.com/matushedera/owl-toolkit.git`
2. `cd owl-toolkit`
3. `mvn package`

Usage
----------

```console
$ cd owl-toolkit
```

Add the command script to the PATH, eg:
```console
$ ln -s "$(pwd)/bin/owltk" /usr/local/bin/owltk
```

### owltk convert

* converts the input OWL ontology to another format

```console
$ owltk convert {-rdfxml | -owlxml | -turtle | -fss | -latex | -manchester } input_file output_file
```

### owltk declarations

* extracts the OWL Declaration Axioms from the input ontology. 
It is very useful when user wants to disable the ontology reasoning.

```console
$ owltk declarations input_file output_file
```


### owltk merge

* merges several OWL files into a single one

```console
$ owltk merge output_file input_file [output_file]...
```

### owltk metrics

* prints the metrics (e.g. number of concepts/propertes/ABox assertions) of an OWL file

```console
$ owltk metrics [-v] input_file
```

### owltk pdf

* converts the OWL file to a pdf file in DL format
  
```console
$ owltk pdf input_file.tex
```

### owltk profilize

* cuts the OWL file to a profile (incl. RL, EL, QL) by dropping violated axioms

```console
$ owltk profilize {-rl | -el | -ql} input_file output_file
```

### owltk materialize

* materializes the imports of the input ontology, that is, it replaces the `import` declaration by the concrete axioms from the imported ontologies

```console
$ owltk materialize input_file output_file
```

### owltk dataprop

* extracts data properties
* *new* save to file option added

```console
$ owltk dataprop input_file [output_file]
```

### owltk objectprop

* extracts object properties
* *new* save to file option added

```console
$ owltk objectprop input_file [output_file]
```

### owltk individuals

* extracts individuals
* *new* save to file option added

```console
$ owltk individuals input_file [output_file]
```

### owltk classes

* extracts classes
* *new* save to file option added

```console
$ owltk individuals input_file [output_file]
```
