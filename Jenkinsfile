pipeline {
    agent any

    triggers {
        pollSCM('H/5 * * * *')
    }

    environment {
        PROJECT_DIR = '/home/lo-bunleang/Desktop/DevOps/idcard'
    }

    stages {

        stage('Check Java') {
            steps {
                sh '''
                    java -version
                    javac -version
                    mvn -version
                '''
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
            }
        }

        stage('Test SQLite') {
            steps {
                sh './mvnw clean test -Dspring.profiles.active=test'
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