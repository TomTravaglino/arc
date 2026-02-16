// SPDX-FileCopyrightText: 2025 Deutsche Telekom AG and others
//
// SPDX-License-Identifier: Apache-2.0

dependencies {
    val graphqlKotlinVersion = "8.5.0"

    implementation(project(":arc-result"))
    implementation(project(":arc-agents"))
    implementation(project(":arc-assistants"))

    // Logging
    implementation("org.slf4j:slf4j-api:2.0.17")

    // MCP
    implementation(libs.mcp.sdk)
    implementation(libs.mcp.kotlin.sdk)

    // Ktor
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.cio)
    implementation(libs.ktor.server.cors)

    // GraphQL
    implementation("com.expediagroup:graphql-kotlin-schema-generator:${graphqlKotlinVersion}")
    implementation("com.expediagroup:graphql-kotlin-server:${graphqlKotlinVersion}")

    // Test
    testImplementation(project(":arc-spring-boot-starter"))
    testImplementation(libs.spring.ai.starter.mcp.server.webflux)
    testImplementation(libs.spring.boot.starter.webflux)
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.5.0")
}
