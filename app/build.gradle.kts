plugins {
    application

    /*
     * Adds tasks to export a runnable jar.
     * In order to create it, launch the "shadowJar" task.
     * The runnable jar will be found in build/libs/projectname-all.jar
     */
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("org.danilopianini.gradle-java-qa") version "1.6.0"
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("junit:junit:4.13.2")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.1-jre")
    
    // https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-chrome-driver
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.9.1")
    implementation("org.seleniumhq.selenium:selenium-support:3.141.59")
    
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:4.9.1")
    
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("org.slf4j:slf4j-simple:1.7.32")
	
    compileOnly("com.github.spotbugs:spotbugs-annotations:4.7.3") // Use the latest version
}


application {
    // Define the main class for the application.
    mainClass.set("application.App")
}
