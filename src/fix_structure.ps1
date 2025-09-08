# Create a temporary directory
$tempDir = "temp_$(Get-Date -Format 'yyyyMMdd_HHmmss')"
New-Item -ItemType Directory -Path $tempDir | Out-Null

# Copy all files from in/ to the temp directory
Copy-Item -Path "in\*" -Destination $tempDir -Recurse -Force

# Remove the in directory
Remove-Item -Path "in" -Recurse -Force

# Move all files from temp to root
Move-Item -Path "$tempDir\*" -Destination ".\" -Force

# Remove the temp directory
Remove-Item -Path $tempDir -Recurse -Force

Write-Host "Directory structure fixed successfully!"
