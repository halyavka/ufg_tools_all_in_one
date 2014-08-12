#Killing all firefox instances before start
if((Get-Process firefox -ea SilentlyContinue) -ne $null) {Stop-Process -name firefox -force}
echo "1. selenium run begin"
$scriptpath = $MyInvocation.MyCommand.Path
$dir = Split-Path $scriptpath
Write-host "My directory is $dir"
$selenium_home = $dir+"\libs\selenium_data\"
$pidFile = $selenium_home + "pid.txt"
#$node1File = $selenium_home + "node1.txt"
#$node2File = $selenium_home + "node2.txt"
$jarFile = $selenium_home + "selenium-server-standalone-2.42.2.jar"
$port = "4444"
Write-Host "2. Retrieving processes for shutting down..."
$oldPid = Get-Content $pidFile
foreach( $p in $oldPid)
{   
   echo "Old process id - [$p]"
   Stop-Process -Id $p -ErrorAction SilentlyContinue
}
echo "2. Selenium server jar location -[$jarFile]"
$args = "-jar " + $jarFile + " -port " + $port
echo "3. Starting selenium server and hubs"
$app = Start-Process java -ArgumentList "-jar $jarFile -role hub -timeout 0" -PassThru -RedirectStandardOutput $selenium_home+"out.log" -RedirectStandardError $selenium_home+"err.log"
$appNode1 = Start-Process java -ArgumentList "-Xms512m -Xmx3072m -jar $jarFile -role webdriver -hub http://localhost:4444/grid/register -timeout 0 -maxSession 20 -browser browserName=firefox,maxInstances=30" -PassThru -RedirectStandardOutput $selenium_home+"node1out.log" -RedirectStandardError $selenium_home+"node1err.log"
#$appNode2 = Start-Process java -ArgumentList "-Xms512m -Xmx2048m -jar $jarFile -role webdriver -hub http://localhost:4444/grid/register -nodeTimeout 900 -maxSession 15 -browser browserName=firefox,maxInstances=20 -port 5556"
echo $app +" "+$appNode1
$app.id.toString()+"`n"+$appNode1.id.toString() | Out-File $pidFile 
#$appNode2.id | Out-File $node2File
echo "selemiun run end"