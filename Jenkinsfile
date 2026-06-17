pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    stages {

        stage('Check Environment') {
            steps {
                sh '''
                    java -version
                    javac -version

                    chmod +x mvnw

                    ./mvnw -version
                '''
            }
        }

        stage('Build') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw clean package -DskipTests
                '''
            }
        }

        stage('Test SQLite') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw clean test -Dspring.profiles.active=test
                '''
            }
        }

        stage('Deploy') {
            steps {
                sh '''
                    cd ansible
                    ansible-playbook -i inventory.ini deploy.yml
                '''
            }
        }
    }

    post {

        success {
            echo 'Build, Test and Deploy Successful'
        }

        failure {
            emailext(
                subject: "Jenkins Build Failed: ${env.JOB_NAME}",
                body: """
Build Failed

Job: ${env.JOB_NAME}
Build Number: ${env.BUILD_NUMBER}

Check Jenkins for details.
""",
                recipientProviders: [
                    [$class: 'DevelopersRecipientProvider']
                ],
                to: 'srengty@gmail.com'
            )
        }
    }
}