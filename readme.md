mvn clean

mvn test
mvn cluecumber-report:reporting


mvn verify

mvn clean verify

mvn clean verify -Dbrowser=CH -DTAG=@Debug
mvn clean verify -Dbrowser=CH
mvn clean verify









https://github.com/SeleniumHQ/docker-selenium




Download chromedriver: login to Linux machine and execute these commands:

$ wget https://chromedriver.storage.googleapis.com/2.40/chromedriver_linux64.zip
$ unzip chromedriver_linux64.zip
$ chromedriver --version
ChromeDriver 2.40.565383
Make sure you're always installing the latest version!

Download Chromebrowser

$ wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
$ sudo yum install google-chrome-stable_current_x86_64.rpm

$ google-chrome --version
Google Chrome 67.0.3396.87

$ yum install Xvfb

$ ps -ef | grep Xvfb
username 12988     1  0 Oct05 ?        00:00:02 Xvfb :99 -screen 0 1152x900x8

$ yum install maven
$ mvn -version
Apache Maven 3.0.5 (Red Hat 3.0.5-17)"# SSMB7-BDD" 
