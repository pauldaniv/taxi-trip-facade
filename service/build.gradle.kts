dependencies {
	implementation(project(":api"))
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("com.pauldaniv.promotion.yellowtaxi:persistence:0.0.6-SNAPSHOT")
	implementation("com.pauldaniv.promotion.yellowtaxi:api:0.0.6-SNAPSHOT")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}
