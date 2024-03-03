plugins {
  `java-library`
  id("io.papermc.paperweight.userdev") version "1.5.11"
  id("xyz.jpenilla.run-paper") version "2.1.0" // Adds runServer and runMojangMappedServer tasks for testing
//  Lombok
  id("io.freefair.lombok") version "8.6"
}

group = "me.melonboy10.blockphysics"
version = "1.0.0-SNAPSHOT"
description = "plugin for blockphysics"

java {
  // Configure the java toolchain. This allows gradle to auto-provision JDK 17 on systems that only have JDK 8 installed for example.
  toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
  mavenCentral()
  maven("https://repo.codemc.org/repository/maven-public/")
}

dependencies {
  paperweight.paperDevBundle("1.20.4-R0.1-SNAPSHOT")
  // paperweight.foliaDevBundle("1.20-R0.1-SNAPSHOT")
  // paperweight.devBundle("com.example.paperfork", "1.20-R0.1-SNAPSHOT")

  // Command API from maven
  compileOnly("dev.jorel:commandapi-bukkit-core:9.3.0")
}

tasks {
  // Configure reobfJar to run when invoking the build task
  assemble {
    dependsOn(reobfJar)
  }

  compileJava {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything

    // Set the release flag. This configures what version bytecode the compiler will emit, as well as what JDK APIs are usable.
    // See https://openjdk.java.net/jeps/247 for more information.
    options.release.set(17)
  }
  javadoc {
    options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
  }
  processResources {
    filteringCharset = Charsets.UTF_8.name() // We want UTF-8 for everything
    val props = mapOf(
      "name" to project.name,
      "version" to project.version,
      "description" to project.description,
      "apiVersion" to ("1.20"),
    )
    inputs.properties(props)
    filesMatching("plugin.yml") {
      expand(props)
    }
  }
  jar {
    // This is an example of how you might change the output location for jar. It's recommended not to do this
    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
    // Name with random at the end
    destinationDirectory.set(file("C:\\Users\\Aidan\\Desktop\\Minecraft Servers\\Spigot Server (Player Machine Learning)\\plugins\\update"))
  }
  reobfJar {
    // This is an example of how you might change the output location for reobfJar. It's recommended not to do this
    // for a variety of reasons, however it's asked frequently enough that an example of how to do it is included here.
    // Name with random at the end
    outputJar.set(file("C:\\Users\\Aidan\\Desktop\\Minecraft Servers\\Spigot Server (Player Machine Learning)\\plugins\\update\\${project.name}-${project.version}.jar"))
  }
}
