(ns polylith.clj.core.common.interface
  (:require [polylith.clj.core.common.class-loader :as class-loader]
            [polylith.clj.core.common.core :as core]
            [polylith.clj.core.common.config :as config]
            [polylith.clj.core.common.ns-extractor :as ns-extractor]
            [polylith.clj.core.common.validate-args :as validate-args]
            [polylith.clj.core.common.ws-dir :as ws-dir]))

(def entity->short core/entity->short)

(defn absolute-path [path entity-root-path]
  (core/absolute-path path entity-root-path))

(defn color-mode [user-input]
  (core/color-mode user-input))

(defn create-class-loader [paths color-mode]
  (class-loader/create-class-loader paths color-mode))

(defn eval-in [class-loader form]
  (class-loader/eval-in class-loader form))

(defn extract-namespace [suffixed-top-ns ns-to-extract]
  (ns-extractor/extract suffixed-top-ns ns-to-extract))

(defn filter-clojure-paths [paths]
  (core/filter-clojure-paths paths))

(defn find-base [base-name bases]
  (core/find-base base-name bases))

(defn find-brick [name workspace]
  (core/find-brick name workspace))

(defn find-component [name components]
  (core/find-component name components))

(defn find-project [project-name projects]
  (core/find-project project-name projects))

(defn compact? [workspace view]
  (core/compact? workspace view))

(defn ns-to-path [namespace]
  (core/ns-to-path namespace))

(defn path-to-ns [namespace]
  (when namespace
    (core/path-to-ns namespace)))

(defn suffix-ns-with-dot [namespace]
  (core/sufix-ns-with-dot namespace))

(defn validate-args [unnamed-args example]
  (validate-args/validate unnamed-args example))

(defn valid-ws-root-config-file-found? [ws-dir color-mode]
  (config/valid-ws-root-config-file-found? ws-dir color-mode))

(defn workspace-dir [user-input color-mode]
  (ws-dir/workspace-dir user-input color-mode))

(defn toolsdeps1? [workspace]
  (= :toolsdeps1 (-> workspace :version :from :ws :type)))

(defn user-path [path]
  (core/user-path path))
