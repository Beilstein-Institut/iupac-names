@Grab(group='io.github.egonw.bacting', module='managers-cdk', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-ui', version='1.0.5')
@Grab(group='io.github.egonw.bacting', module='managers-opsin', version='1.0.5')

import groovy.cli.commons.CliBuilder
import java.util.concurrent.ConcurrentHashMap

workspaceRoot = ".."
ui = new net.bioclipse.managers.UIManager(workspaceRoot);
cdk = new net.bioclipse.managers.CDKManager(workspaceRoot);
bioclipse = new net.bioclipse.managers.BioclipseManager(workspaceRoot);
opsin = new net.bioclipse.managers.OpsinManager(workspaceRoot);

def add(collectedNames, newName) {
  collectedNames.add(newName)
}

def selectValid(collectedNames) {
  Set validNames = ConcurrentHashMap.newKeySet();
  for (collectedName in collectedNames) {
    try {
      opsin.parseIUPACNameAsSMILES(collectedName)
      validNames.add(collectedName)
    } catch (Exception e) {
      // ignore, not valid
    }
  }
  return validNames
}

counter = 0
new File("iupac-names.txt").eachLine { name ->
  counter++
  Set collectedNames = ConcurrentHashMap.newKeySet();
  collectedNames.add(name)
  // smiles = opsin.parseIUPACNameAsSMILES(name)
  for (collectedName in collectedNames) {
    if (collectedName.contains("methyl")) {
      add(collectedNames, collectedName.replaceAll("methyl", "ethyl"))
      add(collectedNames, collectedName.replaceAll("methyl", "propyl"))
      add(collectedNames, collectedName.replaceAll("methyl", "butyl"))
      add(collectedNames, collectedName.replaceAll("methyl", "pentyl"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("butyl")) {
      add(collectedNames, collectedName.replaceAll("butyl", "ethyl"))
      add(collectedNames, collectedName.replaceAll("butyl", "propyl"))
      add(collectedNames, collectedName.replaceAll("butyl", "methyl"))
      add(collectedNames, collectedName.replaceAll("bytyl", "pentyl"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("propyl")) {
      add(collectedNames, collectedName.replaceAll("propyl", "methyl"))
      add(collectedNames, collectedName.replaceAll("propyl", "ethyl"))
      add(collectedNames, collectedName.replaceAll("propyl", "butyl"))
      add(collectedNames, collectedName.replaceAll("propyl", "pentyl"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("chloro")) {
      add(collectedNames, collectedName.replaceAll("chloro", "bromo"))
      add(collectedNames, collectedName.replaceAll("chloro", "iodo"))
      add(collectedNames, collectedName.replaceAll("chloro", "fluoro"))
      add(collectedNames, collectedName.replaceAll("chloro", "hydro"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("bromo")) {
      add(collectedNames, collectedName.replaceAll("bromo", "chloro"))
      add(collectedNames, collectedName.replaceAll("bromo", "iodo"))
      add(collectedNames, collectedName.replaceAll("bromo", "fluoro"))
      add(collectedNames, collectedName.replaceAll("bromo", "hydro"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("phenyl")) {
      add(collectedNames, collectedName.replaceAll("phenyl", "cyclohexyl"))
      add(collectedNames, collectedName.replaceAll("phenyl", "hexyl"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("methoxy")) {
      add(collectedNames, collectedName.replaceAll("methoxy", "ethoxy"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("chloride")) {
      add(collectedNames, collectedName.replaceAll("chloride", "bromide"))
      add(collectedNames, collectedName.replaceAll("chloride", "ioide"))
    }
    if (collectedName.contains("bromide")) {
      add(collectedNames, collectedName.replaceAll("bromide", "chloride"))
      add(collectedNames, collectedName.replaceAll("bromide", "ioide"))
    }
    if (collectedName.contains("ioide")) {
      add(collectedNames, collectedName.replaceAll("ioide", "chloride"))
      add(collectedNames, collectedName.replaceAll("ioide", "bromide"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("propane")) {
      add(collectedNames, collectedName.replaceAll("propane", "butane"))
      add(collectedNames, collectedName.replaceAll("propane", "pentane"))
      add(collectedNames, collectedName.replaceAll("propane", "hexane"))
      add(collectedNames, collectedName.replaceAll("propane", "heptane"))
      add(collectedNames, collectedName.replaceAll("propane", "octane"))
      add(collectedNames, collectedName.replaceAll("propane", "nonane"))
      add(collectedNames, collectedName.replaceAll("propane", "decane"))
    }
    if (collectedName.contains("butane")) {
      add(collectedNames, collectedName.replaceAll("butane", "pentane"))
      add(collectedNames, collectedName.replaceAll("butane", "hexane"))
      add(collectedNames, collectedName.replaceAll("butane", "heptane"))
      add(collectedNames, collectedName.replaceAll("butane", "octane"))
      add(collectedNames, collectedName.replaceAll("butane", "nonane"))
      add(collectedNames, collectedName.replaceAll("butane", "decane"))
    }
    if (collectedName.contains("pentane")) {
      add(collectedNames, collectedName.replaceAll("pentane", "hexane"))
      add(collectedNames, collectedName.replaceAll("pentane", "heptane"))
      add(collectedNames, collectedName.replaceAll("pentane", "octane"))
      add(collectedNames, collectedName.replaceAll("pentane", "nonane"))
      add(collectedNames, collectedName.replaceAll("pentane", "decane"))
    }
  }
  for (collectedName in collectedNames) {
    if (collectedName.contains("hydroxy")) {
      add(collectedNames, collectedName.replaceAll("hydroxy", "methoxy"))
      add(collectedNames, collectedName.replaceAll("hydroxy", "ethoxy"))
      add(collectedNames, collectedName.replaceAll("hydroxy", "acetoxy"))
    }
  }
  for (validName in selectValid(collectedNames)) println validName
  // if (counter >= 10) System.exit(0)
}
