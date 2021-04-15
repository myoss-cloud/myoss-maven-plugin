spring:
  application:
    name: ${artifactId}
  profiles:
    active: ${r"${DEPLOY_ENV:local}"}
