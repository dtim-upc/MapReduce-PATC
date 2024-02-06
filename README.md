 # Map Reduce - Hands On session

 ## Technical requirement
- [Download Java JDK 8](https://www.oracle.com/es/java/technologies/javase/javase8-archive-downloads.html)

**Note:** Just select the JDK specific to your OS and download it. Note: Oracle will ask you to create a free user in order to download any JDK distribution.

- Download Eclipse IDE
  - [Windows](https://archive.eclipse.org/technology/epp/downloads/release/2021-06/R/eclipse-java-2021-06-R-win32-x86_64.zip)
  - [Linux](https://archive.eclipse.org/technology/epp/downloads/release/2021-06/R/eclipse-java-2021-06-R-linux-gtk-x86_64.tar.gz)
  - [MacOS](https://archive.eclipse.org/technology/epp/downloads/release/2021-06/R/eclipse-java-2021-06-R-macosx-cocoa-x86_64.tar.gz)

**Note:** This Eclipse distribution does not require any installation. Just unzip the downloaded package and start the eclipse application

 ## Session materials
 - [Slides](https://github.com/dtim-upc/MapReduce-PATC/blob/main/materials/Hands-on-MapReduce-Slides.pdf)
 - [Assignment](https://github.com/dtim-upc/MapReduce-PATC/blob/main/materials/Hands-on-MapReduce-Assignment.pdf)
 - [Manual for Eclipse project management](https://github.com/dtim-upc/MapReduce-PATC/blob/main/materials/Eclipse-manual.pdf)
   - [Alternative for IntelliJ IDEA EXPERT users](https://github.com/dtim-upc/MapReduce-PATC/blob/main/materials/intellij-manual.pdf)
 - [Manual for running MapReduce jobs locally](https://github.com/dtim-upc/MapReduce-PATC/blob/main/materials/LocalMapreduce-manual.pdf)


## Input data - wines dataset
**key:** 
- type_1 / type_2 / type_3

**values:**
-"alc",					// Alchol
-"m_acid",				// Malic Acid
-"ash",					// Ash
-"alc_ash",				// Alcalinity of ash
-"mgn",					// Magnesium
-"t_phenols",			// Total phenols
-"flav",					// Flavanoids
-"nonflav_phenols",		// Nonflavanoid phenols
-"proant",				// Proanthocyanins
-"col",					// Color intensity
-"hue",					// Hue
-"od280/od315",			// OD280/OD315 of diluted wines
-"proline"				// Proline

### Example: 
key,alc,m_acid,ash,alc_ash,mgn,t_phenols,flav,nonflav_phenols,proant,col,hue,od280/od31,proline
type_3,812,10.393045,3.753219,2.912648,18.368978,142.480377,2.791596,0.481668,1.689406,0.278106,2.636879,1.941921,3.140656,432.403252
type_1,9,14.574036,1.838118,2.24712,22.591963,111.302988,3.245853,0.093356,1.425903,0.104519,0.454513,0.266051,1.412305,1764.370939
type_2,30,14.283455,4.213081,2.363584,17.915281,112.567175,2.644235,0.353343,3.703607,0.079568,3.48083,1.690289,3.995672,837.237237
