# lein-docbkx

Leiningen plugin to render Docbook XML documents using Docbkx-tools.


## Usage

Include as a dev-dependency:

    :dev-dependencies [lein-docbkx "0.1-SNAPSHOT"]

You may optionally specify Docbook source and output directories in project.clj - *.xml files are picked:

    :docbkx-source-path "docbook-src"  ; default is "docbkx-src"
    :docbkx-target-path "docbook-out"  ; default is "target"


### Workaround for [Leiningen bug #244](https://github.com/technomancy/leiningen/issues/244)

    $ lein deps
    $ cp lib/dev/docbook-xsl-1.76.1-ns-resources.zip lib/dev/docbook-xsl-1.76.1-ns-resources.jar


### Render Docbook documents

    $ lein docbkx <type> [<type2> ...]

where <type> is either of epub, html, pdf and xhtml - see example below:

    $ lein docbkx           # shows help
    $ lein docbkx html      # renders as HTML (excluding external resources)
    $ lein docbkx pdf epub  # renders as PDF and EPUB


## Getting in touch

On Twitter: [@kumarshantanu](http://twitter.com/kumarshantanu)

On Leiningen mailing list: [http://groups.google.com/group/leiningen](http://groups.google.com/group/leiningen)

On Docbkx-tools-users mailing list: [http://groups.google.com/group/docbkx-tools-users](http://groups.google.com/group/docbkx-tools-users)


## License

Copyright (C) 2011-2012 Shantanu Kumar

Distributed under the Eclipse Public License, the same as Clojure.
