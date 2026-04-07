pipeline{
	agent any
	
	stages{
		
		stage("build"){
			steps{
				echo("Build the project")
			}
		}
		
		stage("deploy to QA"){
			steps{
				echo("Deploy to QA env")
			}
		}
		
		stage("QA Regression"){
			steps{
				catchError(buildResult: 'SUCCESS', stageResult: 'FAILURE'){
					git 'https://github.com/Nareshkumar19/FrameworkPractice.git'
					bat "mvn clean test -Dsurefire.suiteXmlFiles=C:/Users/Naresh/eclipse-workspace/PomPractice/src/test/resources/testRunners/testng.xml -Denv=uat"
				}
			}
		}
	}
}