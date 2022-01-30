pipeline {
    agent 'HW_6'
    
    stages {
        stage('Build/Test') {
            steps {
                echo "Start building/testing project..."
                sh 'mvn clean package'
                echo "Finish building/testing project..."
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