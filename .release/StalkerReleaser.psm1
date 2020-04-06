
$semverRegex = "(0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?"

function Invoke-PrepareRelease {
  [CmdletBinding()]
  param (
    # Target version number
    [Parameter(Mandatory)]
    [ValidateScript( {
        if (-not( $_ -match $semverRegex ) ) {
          throw "$_ IS NOT A VALID SEMVER NUMBER"
        }
        else {
          $true
        }
      })]
    [String]
    $versionNumber,
    [switch]
    $dryRun
  )

  if (-not (Test-Path ".git")) {
    throw "you need to be in the repository root to run this script"
  }

  if (git status -s) {
    throw "YOU NEED TO CLEAR YOUR GIT STATUS OR AT LEAST STASH EVERYTHING"
  }

  $env:VERSION_NUMBER = $versionNumber

  Write-Output "Preparing the current release ..."

  Write-Output "------------------------------------------------------------------"
  Write-Warning "this is a dry run to check if everything is fine"
  Write-Output "------------------------------------------------------------------"
  Write-Output ""

  npx @(
    "standard-version"
    "--skip.changelog"
    "--skip.commit"
    "--skip.tag"
    "--release-as"; "$versionNumber"
    "--dry-run"
  )

  if ($LASTEXITCODE) {
    throw "Something went wrong during the dry run of standard-version. Fix it and try again!"
  }

  if ($PSCmdlet.ShouldContinue("-----------------------------------------",
      "Is the current versino bump correct?" )) {

    npx @(
      "standard-version"
      "--skip.tag"
      "--release-as"; $versionNumber
      if ($dryRun) {
        "--dry-run"
      }
    )
  }
}

function Invoke-TagRelease {
  [CmdletBinding()]
  param (
    [Parameter()]
    $versionNumber,
    [switch]
    $dryRun
  )


  if (-not (Test-Path ".git")) {
    throw "you need to be in the repository root to run this script"
  }

  if (-not $versionNumber) {
    $versionNumber = "$env:VERSION_NUMBER"
  }

  if (-not ($versionNumber -match $semverRegex)) {
    throw "The value ""$versionNumber"" is not a valid semver number"
  }

  Write-Output "Tagging current release with detected version number $versionNumber"

  if (git status -s) {
    throw "YOU NEED TO CLEAR YOUR GIT STATUS OR AT LEAST STASH EVERYTHING"
  }

  git checkout master
  git pull origin

  Write-Output "------------------------------------------------------------------"
  Write-Warning "this is a dry run to check if everything is fine"
  Write-Output "------------------------------------------------------------------"
  Write-Output ""

  npx @(
    "standard-version"
    "--skip.bump"
    "--skip.changelog"
    "--skip.commit"
    "--release-as"; $versionNumber
    "--dry-run"
  )

  if ($LASTEXITCODE) {
    throw "Something went wrong during the dry run of standard-version. Fix it and try again!"
  }

  if ($PSCmdlet.ShouldContinue("-----------------------------------------",
      "Is the current versino bump correct?" )) {

    npx @(
      "standard-version"
      "--skip.bump"
      "--skip.changelog"
      "--skip.commit"
      "--release-as"; $versionNumber
      "--dry-run"
      if ($dryRun) {
        "--dry-run"
      }
    )

    Write-Output "if everything is fine, run:"
    Write-Output "git push origin --tags"
  }
}
