pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/milestone") }
		gradlePluginPortal()
	}
}

rootProject.name = "taxi-trip-facade"
include("api", "service")