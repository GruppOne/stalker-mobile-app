if (($args -match " -h") -or ($args -match " --help")) {
  npx standard-version -h
  return
}

Write-Output "Preparing current release..."

npx standard-version --skip.tag $args

if ($LASTEXITCODE) {
  Write-Error "Something wrong with standard-version"
}
