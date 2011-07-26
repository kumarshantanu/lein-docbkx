(defproject lein-docbkx "0.1-SNAPSHOT"
  :description "Leiningen pluin for rendering Docbook XML documents using Docbkx-tools"
  :url "https://github.com/kumarshantanu/lein-docbkx"
  :dependencies     [[org.codehaus.plexus/plexus-utils         "1.1"]
                     [com.agilejava.docbkx/docbkx-maven-base   "2.0.13"
                      :exclusions [org.codehaus.plexus/plexus-utils]]
                     [com.agilejava.docbkx/docbkx-maven-plugin "2.0.13"
                      :exclusions [org.codehaus.plexus/plexus-utils]]
                     [net.sf.docbook/docbook-xsl               "1.76.1"
                      :type "zip" :classifier "ns-resources"]]
  :dev-dependencies [[org.clojure/clojure "1.2.1"]] )
