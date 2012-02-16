(ns leiningen.docbkx
  (:import
   (java.io File)
   (java.lang.reflect Field)
   (com.agilejava.docbkx.maven DocbkxEpubMojo DocbkxHtmlMojo
                               DocbkxPdfMojo  DocbkxXhtmlMojo)))


(defn ^Field get-field
  "Return Field object"
  [object ^String field-name] {:pre [(not (nil? object))]}
  (let [c (if (class? object) object
              (class object))
        f (.getDeclaredField c field-name)]
    (.setAccessible f true)
    f))


(def ^{:dynamic true} *docbkx-source-path* "docbkx-src")

(def ^{:dynamic true} *docbkx-output-path* "target")


(defn render
  "Render Docbook XML using `mojo` to a filename extension specied with `extn`"
  [extn mojo]
  (let [sourceDirectory     (get-field mojo "sourceDirectory")
        targetDirectory     (get-field mojo "targetDirectory")
        includes            (get-field mojo "includes")
        targetFileExtension (get-field mojo "targetFileExtension")]
    (.set sourceDirectory     mojo (java.io.File. *docbkx-source-path*))
    (.set targetDirectory     mojo (java.io.File. *docbkx-output-path*))
    (.set includes            mojo "*.xml")
    (.set targetFileExtension mojo extn)
    (.execute mojo)))


(def all-types {"epub"  (DocbkxEpubMojo.)
                "html"  (DocbkxHtmlMojo.)
                "pdf"   (DocbkxPdfMojo.)
                "xhtml" (DocbkxXhtmlMojo.)})


(defn help
  "Show help for this plugin"
  []
  (println "
Usage: $ lein docbkx <render-type> [<render-type> ...]

where, <render-type> can be epub, html, pdf, xhtml (should be all lowercase)

You may specify the Docbook source and output directory in project.clj:

    :docbkx-source-path \"docbook-src\"  ; default is \"docbkx-src\"
    :docbkx-target-path \"docbook-out\"  ; default is \"target\"

This plugin uses Docbkx-tools: http://code.google.com/p/docbkx-tools/
For rendering details you should refer the Docbkx-tools project.
"))


(defn docbkx
  "Leiningen plugin to render Docbook XML documents using Docbkx-tools"
  [project & [render-type & more]]
  (binding [*docbkx-source-path* (or (:docbkx-source-path project)
                                     *docbkx-source-path*)
            *docbkx-output-path* (or (:docbkx-output-path project)
                                     *docbkx-output-path*)]
    (doseq [each-type (into [render-type] more)]
      (if (contains? all-types each-type)
        (render each-type (get all-types each-type))
        (help)))))