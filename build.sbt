lazy val shared = (project in file("shared"))
    .settings(
      libraryDependencies ++= dependencies
    )

lazy val persistence = (project in file("persistence"))
    .settings(
      libraryDependencies ++= dependencies
    )
    .dependsOn(shared)

lazy val generator = (project in file("generator"))
    .settings(
      libraryDependencies ++= dependencies
    )
    .dependsOn(shared)

lazy val controller = (project in file("controller"))
    .settings(
      libraryDependencies ++= dependencies
    )
    .dependsOn(shared, generator, persistence)

lazy val view = (project in file("view"))
    .settings(
      libraryDependencies ++= dependencies
    )
    .dependsOn(shared, controller)

lazy val root = project
    .in(file("."))
    .settings(
      name         := "minesweeper",
      version      := "0.1.0-SNAPSHOT",
      scalaVersion := "3.2.2",
      crossScalaVersions ++= Seq("2.13.5", "3.0.2"),
      libraryDependencies ++= dependencies,      
      javacOptions ++= Seq("-encoding", "UTF-8", "-unchecked", "-deprecation"),
      jacocoReportSettings := JacocoReportSettings(
        "Jacoco Coverage Report",
        None,
        JacocoThresholds(),
        Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML),
        "utf-8"
      )
    )
    .aggregate(view)
    .dependsOn(view)

lazy val dependencies =
    Seq(
      "com.novocode"             % "junit-interface" % "0.11"   % "test",
      "org.scalactic"           %% "scalactic"       % "3.2.10",
      "org.scalatest"           %% "scalatest"       % "3.2.10" % "test",
      "org.scala-lang.modules"  %% "scala-xml"       % "2.0.1",
      "com.typesafe.akka" %% "akka-actor-typed" % "2.8.0",
      "com.typesafe.akka" %% "akka-http" % "10.5.0",
      "com.typesafe.akka" %% "akka-stream" % "2.8.0",
      ("org.scala-lang.modules" %% "scala-swing"     % "3.0.0")
          .cross(CrossVersion.for3Use2_13)
    ) ++ Seq(
      "io.circe" %% "circe-core",
      "io.circe" %% "circe-generic",
      "io.circe" %% "circe-parser"
    ).map(_ % "0.14.1") ++ {
        // Determine OS version of JavaFX binaries
        lazy val osName = System.getProperty("os.name") match {
            case n if n.startsWith("Linux")   => "linux"
            case n if n.startsWith("Mac")     => "mac"
            case n if n.startsWith("Windows") => "win"
            case _                            => throw new Exception("Unknown platform!")
        }
        Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
            .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
    }
