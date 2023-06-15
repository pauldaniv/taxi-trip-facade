dependencies {
	implementation(project(":api"))
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("redis.clients:jedis:4.4.3")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("com.pauldaniv.promotion.yellowtaxi:persistence:0.0.6-SNAPSHOT")
	implementation("com.pauldaniv.promotion.yellowtaxi:api:0.0.6-SNAPSHOT")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}
