# Move all files from in/ to the root directory
Get-ChildItem -Path "in\*" -Force | ForEach-Object {
    $destination = $_.FullName.Replace("in\\", "\\")
    Move-Item -Path $_.FullName -Destination $destination -Force
}

# Remove the now-empty in directory
Remove-Item -Path "in" -Recurse -Force
