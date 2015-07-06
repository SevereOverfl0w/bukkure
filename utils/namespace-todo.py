# Converts the cljminecraft namespace to cljbukkit,
# Then adds a TODO, for me to check the file out later.

import sys

fileLocations = sys.argv[1:]

for fileLoc in fileLocations:
    myfile = open(fileLoc, "r")

    contents = myfile.read()
    contents = contents.replace("cljbukkit", "bukkure")
    # contents = ";; TODO: Check this file manually\n" + contents

    myfileW = open(fileLoc, "w")
    myfileW.write(contents)
