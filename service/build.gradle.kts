dependencies {
	implementation(project(":api"))
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	implementation("redis.clients:jedis:4.4.3")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("com.pauldaniv.promotion.yellowtaxi:persistence:0.0.6-SNAPSHOT")
	implementation("com.pauldaniv.promotion.yellowtaxi:api:0.0.6-SNAPSHOT")
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
	implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
//	implementation("jakarta.xml.bind:jakarta.xml.bind-api:4.0.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.kafka:spring-kafka-test")
}
