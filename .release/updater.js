// source https://semver.org/#is-there-a-suggested-regular-expression-regex-to-check-a-semver-string
const versionNameRegex = /^( *)versionName "((0|[1-9]\d*)\.(0|[1-9]\d*)\.(0|[1-9]\d*)(?:-((?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*)(?:\.(?:0|[1-9]\d*|\d*[a-zA-Z-][0-9a-zA-Z-]*))*))?(?:\+([0-9a-zA-Z-]+(?:\.[0-9a-zA-Z-]+)*))?)"$/m;

const versionCodeRegex = /^( *)versionCode (\d+)$/m;

// readVersion(contents: string): string
module.exports.readVersion = function(contents) {
  const match = versionNameRegex.exec(contents);
  // const indent = match[1];
  const version = match[2];

  return version;
}

// writeVersion(contents: string, version: string): string
module.exports.writeVersion = function(contents, version) {
  // replaces version code with next int number
  const replacedVersionCode = contents.replace(versionCodeRegex, (match, indent, versionCode) => {
    if (!match) {
      throw 'MATCH NOT FOUND';
    }
    let nextVersionCode = parseInt(versionCode) + 1;
    return `${indent}versionCode ${nextVersionCode}`;
  });

  const replacedVersionName = replacedVersionCode.replace(versionNameRegex, `$1versionName "${version}"`);

  return replacedVersionName;
}
