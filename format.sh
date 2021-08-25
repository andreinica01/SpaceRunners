#npx prettier --write "**/*.java"

java -jar google-java-format-1.11.0-all-deps.jar -replace $(git ls-files *.java)



