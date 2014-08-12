#Killing all firefox instances before start
if((Get-Process firefox -ea SilentlyContinue) -ne $null) {Stop-Process -name firefox -force}
$scriptpath = $MyInvocation.MyCommand.Path
$dir = Split-Path $scriptpath
Write-host "My directory is $dir"
$selenium_home = $dir+"\libs\selenium_data\"
$pidFile = $selenium_home + "pid.txt"
Write-Host "1. Retrieving processes for shutting down..."
$oldPid = Get-Content $pidFile
foreach( $p in $oldPid)
{   
   echo "Old process id - [$p]"
   Stop-Process -Id $p -ErrorAction SilentlyContinue
}
Write-Host "2. Shutdown complete."