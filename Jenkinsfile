node{
   stage('SCM Checkout'){
       git credentialsId: 'Git-cred', url: 'https://github.com/dattagore/UI-CRUD-Ops', branch: 'master'
   }
   stage('Compile Phase'){
     def mvnHome = tool name: 'maven363', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     bat "${mvnCMD} clean compile"
   }
   stage('Maven Test and Package'){
     def mvnHome = tool name: 'maven363', type: 'maven'
     def mvnCMD = "${mvnHome}/bin/mvn"
     bat "${mvnCMD} package"
   }
   stage('Build Docker Image'){
     bat 'docker build -t dattatraygoredockerhub/ui-crud-ops:1.0.0 .'
   }
   stage('Push Docker Image to docker hub'){
     withCredentials([string(credentialsId: 'docker-pwd', variable: 'dockerHubPwd')]) {
        bat "docker login -u dattatraygoredockerhub -p ${dockerHubPwd}"
     }
     bat 'docker push dattatraygoredockerhub/ui-crud-ops:1.0.0'
   }
}