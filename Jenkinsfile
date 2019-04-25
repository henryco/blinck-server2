pipeline {
  agent any

  tools {
  		maven 'Default'
  }

  stages {
    stage('Check and Prepare') {
      steps {
        sh 'mvn clean'
      }
    }
    stage('Test') {
      steps {
        sh 'mvn -Dconsul.address=174.138.0.194 -Dconsul.tags=dev-test -Dspring.profiles.active=dev-test test'
      }
    }
    stage('Build') {
      steps {
        sh 'mvn -Dmaven.test.skip=true install'
      }
    }
    stage('Prepare artifacts') {
      steps {
        archiveArtifacts(artifacts: 'target/*.jar', onlyIfSuccessful: true)
      }
    }
    stage('Deploy') {
    	when {
    		branch 'release'
    	}
    	steps {
    		withEnv(overrides: ['JENKINS_NODE_COOKIE=dontKillMe']) {
    		  sh '(pkill -f BlinckServerApp) || true'
    		  sh '(rm -f BlinckServerApp.jar) || true'
    		  sh 'cp $(ls -p | grep -v / | grep -v .jar.original | grep .jar)' /home/henryco/Programs/BlinckServer/out/BlinckServerApp.jar
    		  sh 'cd /home/henryco/Programs/BlinckServer/out/ && java -Dconsul.address=174.138.0.194 -Dconsul.tags=develop -Dspring.profiles.active=develop -jar BlinckServerApp.jar'
        }
    	}
    }
  }

  post {
    always {
      junit 'target/surefire-reports/*.xml'
      sh 'rm -f -r test-arch'
      sh 'mkdir test-arch'
      sh 'zip -r test-arch/test-report.zip target/surefire-reports'
      archiveArtifacts 'test-arch/*.zip'
    }
  }
}
