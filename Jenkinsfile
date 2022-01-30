pipeline {
    agent any
    
    stages {
        stage('Build') {
            steps {
                echo "Start building project..."
                sh 'mvn -B -DskipTests clean package'
                echo "Finish building project..."
            }
        }
        stage('Test') { 
            steps {
                echo "Start testing..."  
                sh 'mvn test'
                echo "Finish testing..."
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml' 
                }
            }
        }
        stage('Deploy') {
            steps{
                echo "Start deploying..."    
                echo "Deploying..."    
                echo "Finish deploying..."    
            }
        }
    }
}