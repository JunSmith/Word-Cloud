# Word Cloud
***By Jun Smith***

## Brief
Generates a simple word cloud image in .png format of dimensions 1000x1000 to accommodate 100 words maximum on the image.

## Usage
The program can be run by using the console. The program can convert either a URL string or a text file as shown below. A text file must also be entered as the second parameter which contains words to be filtered out. The provided stopwords.txt can be used as such. What determines whether a text file is used or url string is used is the last parameter which is a boolean; true for a text file and false for a url string.

A minor caveat, the program may not be able to find a suitable space for the word so it may run indefinitely. Simply press Ctrl + C on Windows if the console appears to only stay on "Word map created" for more than five seconds and rerun the program, it should take less than five seconds to complete.

**Text File**
java -cp ./wordcloud.jar ie.gmit.runner.Runner [text file] [stopwords file] true

e.g. using a text file named res.txt with the provided stopwords.txt, run the command by typing:
  java -cp ./wordcloud.jar ie.gmit.runner.Runner "./res.txt" "./stopwords.txt" true

**URL string**
java -cp ./wordcloud.jar ie.gmit.runner.Runner [URL] [stopwords file] false

e.g. using a url www.gmit.ie along with stopwords.txt, run the command by typing:
  java -cp ./wordcloud.jar ie.gmit.runner.Runner "www.gmit.ie" "./stopwords.txt" false
  
## UML Class Diagram
UML diagram was generated using the ObjectAid Eclipse plugin. Same image can be found [here](https://github.com/JunSmith/Word-Cloud/blob/master/src/ie/gmit/uml/ClassDiagram.png)
![alt text](https://raw.githubusercontent.com/JunSmith/Word-Cloud/master/src/ie/gmit/uml/ClassDiagram.png "UML diagram")
