(ns polylith.clj.core.help.info
     (:require [polylith.clj.core.help.shared :as s]
               [polylith.clj.core.help.shared :as shared]
               [polylith.clj.core.util.interface.color :as color]))

(defn help-text [cm]
  (str "  Shows workspace information.\n"
       "\n"
       "  poly info [" (s/key "ARGS" cm) "]\n"
       "    ARGS = " (s/key ":loc" cm) "   -> Shows the number of lines of code for each brick\n"
       "                     and project.\n"
       "\n"
       "  In addition to " (s/key ":loc" cm) ", all the arguments used by the 'test' command\n"
       "  can also be used as a way to see what tests will be executed.\n"
       "\n"
       "    stable since: " (color/grey cm "dec73ec | stable-lisa\n")
       "\n"
       "    " (color/project "projects:" cm) " 2   " (color/interface "interfaces:" cm) " 3\n"
       "    " (color/base "bases:" cm) "    1   " (color/component "components:" cm) " 4\n"
       "\n"
       "    active profiles: " (color/profile "default" cm) "\n"
       "\n"
       "    project       alias  status   " (color/profile "dev  admin" cm) "\n"
       "    ---------------------------   ----------\n"
       "    " (color/project "command-line  cl      ---     ---   --" cm) "\n"
       "    " (color/project "development   dev     s--     s--   --" cm) "\n"
       "\n"
       "    interface  brick    " (color/project "cl    dev  admin\n" cm)
       "    -----------------   ---   ----------\n"
       "    " (color/interface "payer" cm) "      " (color/component "payer" cm) "    " (color/project "s--   st-   --" cm) "\n"
       "    " (color/interface "user" cm) "       " (color/component "admin" cm) "    " (color/project "s--   ---   s-" cm) "\n"
       "    " (color/interface "user" cm) "       " (color/component "user" cm) " *   " (color/project "---   st-   --" cm) "\n"
       "    " (color/interface "util" cm) "       " (color/component "util" cm) "     " (color/project "s--   st-   --" cm) "\n"
       "    -          " (color/base "cli" cm) "      " (color/project "s--   st-   --" cm) "\n"
       "\n"
       "  This example shows a sample workspace. Let's go through each section:\n"
       "\n"
       "  1. stable since: " (color/grey cm "dec73ec | stable-lisa\n")
       "\n"
       "     Shows the most recent commit marked as stable, or the last release if \n"
       "     " (s/key "since:release" cm) " or " (s/key "since:previous-release" cm) " was given, or the first commit\n"
       "     in the repository if no tag was found, followed by the tag (if found).\n"
       "     More information can be found in the 'diff' command help.\n"
       "\n"
       "  2. " (color/project "projects:" cm) " 2   " (color/interface "interfaces:" cm) " 3\n"
       "     " (color/base "bases:" cm) "    1   " (color/component "components:" cm) " 4\n"
       "\n"
       "     Shows how many " (color/project "projects" cm) ", " (color/base "bases" cm) ", " (color/component "components" cm) " and " (color/interface "interfaces" cm) " there are\n"
       "     in the workspace.\n"
       "\n"
       "  3. active profiles: " (color/profile "default" cm) "\n"
       "\n"
       "     Shows the names of active profiles. The profile paths are merged into the\n"
       "     " (color/project "development" cm) " project. A profiles is an alias in ./deps.edn that starts\n"
       "     with a " (color/purple cm "+") ". If no profile is selected, the " (color/profile "default" cm) " profile is automatically\n"
       "     selected.\n"
       "\n"
       "     Profiles are activated by passing them in by name (prefixed with '+'), e.g.:\n"
       "       poly info +admin +onemore\n"
       "\n"
       "     To deactivate all the profiles, and stop 'default' from being merged into\n"
       "     the development project, type:\n"
       "       poly info +\n"
       "\n"
       "  4. project       alias  status   " (color/profile "dev  admin" cm) "\n"
       "     ---------------------------   ----------\n"
       "     " (color/project "command-line  cl      ---     ---   --" cm) "\n"
       "     " (color/project "development   dev     s--     s--   --" cm) "\n"
       "\n"
       "    This table lists all projects. The 'project' column shows the name\n"
       "    of the projects, which are the directory names under the 'projects',\n"
       "    directory except for 'development' that stores its code under the\n"
       "    'development' directory.\n"
       "\n"
       "    The 'deps.edn' config files are stored under each project, except for\n"
       "    the development project that stores it at the workspace root.\n"
       "\n"
       "    Aliases are configured in " (color/purple cm ":projects") " in ./workspace.edn.\n"
       "\n"
       "    The 'status' column has three flags with different meaning:\n"
       "      " (color/project "s--" cm) "  The project has a 'src' directory, e.g.\n"
       "           'projects/command-line/src'.\n"
       "      " (color/project "-t-" cm) "  The project has a 'test' directory, e.g.\n"
       "           'projects/command-line/test'.\n"
       "      " (color/project "--x" cm) "  The project tests (its own) are marked for execution.\n"
       "\n"
       "    To show the 'resources' directory, also pass in :r or :resources, e.g.\n"
       "    'poly info :r':\n"
       "      " (color/project "s---" cm) "  The project has a 'src' directory, e.g.\n"
       "            'projects/command-line/src'.\n"
       "      " (color/project "-r--" cm) "  The project has a 'resources' directory, e.g.\n"
       "            'projects/command-line/resources'.\n"
       "      " (color/project "--t-" cm) "  The project has a 'test' directory, e.g.\n"
       "            'projects/command-line/test'\n"
       "      " (color/project "---x" cm) "  The project tests (its own) are marked for execution.\n"
       "\n"
       ""
       "    The " (s/key "dev" cm) " column has three flags with different meaning:\n"
       "      " (color/project "s--" cm) "  The project's 'src' directory, e.g.\n"
       "           'projects/command-line/src' is added to './deps.edn'\n"
       "           (or indirectly added as :local/root).\n"
       "      " (color/project "-t-" cm) "  The project's 'test' directory, e.g.\n"
       "           'projects/command-line/test' is added to './deps.edn'\n"
       "           (or indirectly added as :local/root).\n"
       "      " (color/project "--x" cm) "  The project tests are marked for execution from development.\n"
       "\n"
       "    The last " (s/key "admin" cm) " column, is a profile:\n"
       "      " (color/project "s-" cm) "  The profile contains a path to the 'src' directory, e.g.\n"
       "          'projects/command-line/src'.\n"
       "      " (color/project "-t" cm) "  The profile contains a path to the 'test' directory, e.g.\n"
       "          'projects/command-line/test'.\n"
       "\n"
       "    If also passing in :r or :resources, e.g. 'poly info +r':\n"
       "      " (color/project "s--" cm) "  The profile contains a path to the 'src' directory, e.g.\n"
       "           'projects/command-line/src'.\n"
       "      " (color/project "-r-" cm) "  The profile contains a path to the 'resources' directory, e.g.\n"
       "           'projects/command-line/resources'.\n"
       "      " (color/project "--t" cm) "  The profile contains a path to the 'test' directory, e.g.\n"
       "           'projects/command-line/test'.\n"
       "\n"
       "  5. interface  brick    " (color/project "cl    dev  admin\n" cm)
       "     -----------------   ---   ----------\n"
       "     " (color/interface "payer" cm) "      " (color/component "payer" cm) "    " (color/project "s--   st-   --" cm) "\n"
       "     " (color/interface "user" cm) "       " (color/component "admin" cm) "    " (color/project "s--   ---   st" cm) "\n"
       "     " (color/interface "user" cm) "       " (color/component "user" cm) " *   " (color/project "---   st-   --" cm) "\n"
       "     " (color/interface "util" cm) "       " (color/component "util" cm) "     " (color/project "s--   st-   --" cm) "\n"
       "     -          " (color/base "cli" cm) "      " (color/project "s--   st-   --" cm) "\n"
       "\n"
       "    This table lists all bricks and in which projects and profiles they are\n"
       "    added to.\n"
       "\n"
       "    The 'interface' column shows what " (color/interface "interface" cm) " the component has. The name\n"
       "    is the first namespace after the top namespace, e.g.:\n"
       "    " (shared/component-ns "interface" "user" cm) "\n"
       "\n"
       "    The 'brick' column shows the name of the brick, in " (color/component "green" cm) " if a component or\n"
       "    " (color/base "blue" cm) " if a base. Each component lives in a directory under the 'components'\n"
       "    directory and each base lives under the 'bases' directory. If any file for\n"
       "    a brick has changed since the last stable point in time, it will be marked\n"
       "    with an asterisk, * (" (color/component "user" cm) " in this example).\n"
       "\n"
       "    The changed files can be listed by executing 'poly diff'.\n"
       "\n"
       "    The next " (color/project "cl" cm) " column is the " (color/project "command-line" cm) " project that lives under the\n"
       "    'projects' directory. Each line in this column says whether a brick is\n"
       "    included in the project or not."
       "\n"
       "    The flags mean:\n"
       "      " (color/project "s--" cm) "  The project contains a path to the 'src' directory, e.g.\n"
       "           'components/user/src' (or is indirectly added by a :local/root).\n"
       "      " (color/project "-t-" cm) "  The project contains a path to the 'test' directory, e.g.\n"
       "           'components/user/test' (or is indirectly added by a :local/root).\n"
       "      " (color/project "--x" cm) "  The brick is marked to be executed from this project.\n"
       "\n"
       "    If :r or :resources is also passed in:\n"
       "      " (color/project "s---" cm) "  The project contains a path to the 'src' directory, e.g. \n"
       "            'components/user/src' (or is indirectly added by a :local/root).\n"
       "      " (color/project "-r--" cm) "  The project contains a path to the 'resources' directory, e.g.\n"
       "            'components/user/resources' (or is indirectly added by a :local/root).\n"
       "      " (color/project "--t-" cm) "  The project contains a path to the 'test' directory, e.g.\n"
       "            'components/user/test' (or is indirectly added by a :local/root).\n"
       "      " (color/project "---x" cm) "  The brick is marked to be executed from this project.\n"
       "\n"
       "    The next group of columns, " (color/project "dev admin" cm) ", is the development project with\n"
       "    its profiles. If passing in a plus with 'poly info +' then it will also show\n"
       "    the " (color/profile "default" cm) " profile. The flags for the " (color/project "dev" cm) " project works the same\n"
       "    as for " (color/project "cl" cm) ".\n"
       "\n"
       "    The flags for the " (color/profile "admin" cm) " profile means:\n"
       "      " (color/project "s-" cm) "  The profile contains a path to the 'src' directory, e.g.\n"
       "          'components/user/src'.\n"
       "      " (color/project "-t" cm) "  The profile contains a path to the 'test' directory, e.g.\n"
       "          'components/user/test'\n"
       "\n"
       "  It's not enough that a path has been added to a project to show an 'x',\n"
       "  the file or directory must also exist.\n"
       "\n"
       "  If any warnings or errors were found in the workspace, they will be listed at\n"
       "  the end, see the 'check' command help, for a complete list of validations.\n"
       "\n"
       "  Example:\n"
       "    poly info\n"
       "    poly info :loc\n"
       "    poly info since:release\n"
       "    poly info since:previous-release\n"
       "    poly info project:myproject\n"
       "    poly info project:myproject:another-project\n"
       "    poly info brick:mycomponent\n"
       "    poly info brick:mycomponent:mybase\n"
       "    poly info :project\n"
       "    poly info :dev\n"
       "    poly info :project :dev\n"
       "    poly info :all\n"
       "    poly info :all-bricks\n"
       "    poly info ws-dir:another-ws\n"
       "    poly info ws-file:ws.edn"))

(defn print-help [color-mode]
  (println (help-text color-mode)))

(comment
  (print-help "dark")
  #__)
