println "Configuring logback"

def logPattern = "%d{HH:mm:ss.SSS} [%thread] %highlight(%-5level) %logger{40}: %msg%n%ex{full, org, sun, java.lang, java.util, javax, com.fasterxml, CGLIB}"

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = logPattern
    }
}

root(INFO, ["STDOUT"])

logger("layers", INFO)
logger("org.springframework", INFO)
logger("org.hibernate", INFO)
logger("org.hibernate.SQL", DEBUG)
logger("org.hibernate.type.descriptor.sql.BasicBinder", TRACE)
logger("org.hibernate.tool.hbm2ddl.SchemaExport", DEBUG)
