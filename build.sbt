val scala3Version    = "3.0.2"
val ScalatestVersion = "3.2.8"

lazy val root = project
    .in(file("."))
    .settings(
      name         := "minesweeper",
      version      := "0.1.0-SNAPSHOT",
      scalaVersion := scala3Version,
      crossScalaVersions ++= Seq("2.13.5", "3.0.2"),
      libraryDependencies += "com.novocode"   % "junit-interface" % "0.11"   % "test",
      libraryDependencies += "org.scalactic" %% "scalactic"       % "3.2.10",
      libraryDependencies += "org.scalatest" %% "scalatest"       % "3.2.10" % "test",
      libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.0.1",
      libraryDependencies ++= Seq(
        "io.circe" %% "circe-core",
        "io.circe" %% "circe-generic",
        "io.circe" %% "circe-parser"
      ).map(_ % "0.14.1"),
      //     libraryDependencies +=("org.json4s" %% "json4s-jackson" % "4.0.3"),
      // libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.0",
      libraryDependencies += ("org.scala-lang.modules" %% "scala-swing" % "3.0.0")
          .cross(CrossVersion.for3Use2_13),
      libraryDependencies ++= {
          // Determine OS version of JavaFX binaries
          lazy val osName = System.getProperty("os.name") match {
              case n if n.startsWith("Linux")   => "linux"
              case n if n.startsWith("Mac")     => "mac"
              case n if n.startsWith("Windows") => "win"
              case _                            => throw new Exception("Unknown platform!")
          }
          Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
              .map(m => "org.openjfx" % s"javafx-$m" % "16" classifier osName)
      },
      javacOptions ++= Seq("-encoding", "UTF-8", "-unchecked", "-deprecation"),
      jacocoReportSettings := JacocoReportSettings(
        "Jacoco Coverage Report",
        None,
        JacocoThresholds(),
        Seq(JacocoReportFormats.ScalaHTML, JacocoReportFormats.XML), // note XML formatter
        "utf-8"
      )
    )
