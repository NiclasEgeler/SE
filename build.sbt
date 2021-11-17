val scala3Version = "3.0.2"
val ScalatestVersion = "3.2.8"

lazy val root = project
  .in(file("."))
  .settings(
    name := "minesweeper",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,
    
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scalactic" %% "scalactic" % "3.2.10",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.10" % "test",
    javacOptions ++= Seq("-encoding", "UTF-8"),
    
    jacocoCoverallsServiceName := "github-actions", 
    jacocoCoverallsBranch := sys.env.get("CI_BRANCH"),
    jacocoCoverallsPullRequest := sys.env.get("GITHUB_EVENT_NAME"),
    jacocoCoverallsRepoToken := sys.env.get("COVERALLS_TOKEN")
  
  )
  .enablePlugins(JacocoCoverallsPlugin)
