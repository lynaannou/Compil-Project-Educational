-Compilation Playground:
Projet pédagogique de compilation utilisant LEX/Flex, YACC/Bison et une interface JavaFX.
Il permet d’analyser et d’évaluer des expressions arithmétiques et scientifiques, avec gestion des erreurs syntaxiques et sémantiques.

-commande de compilation et execution du projet :
-Backend (LEX / YACC): (suivant cet ordre)
    bison -dy calc.y
    flex calc.l
    gcc y.tab.c lex.yy.c -o native\calc.exe -lm
    native\calc.exe

-Frontend (JavaFX):
le chemin vers JavaFX dépend de votre machine.
Remplacez C:/Users/lenovo/Downloads/openjfx-23.0.1_windows-x64_bin-sdk/javafx-sdk-23.0.1/ par le chemin de votre installation JavaFX.
-Compilation:
javac --class-path "CHEMIN_VERS_JAVAFX/lib/*" -d out \
src/main/java/app/*.java \
src/main/java/screens/*.java \
src/main/java/service/*.java \
src/main/java/controller/*.java

-Exécution
java --module-path "CHEMIN_VERS_JAVAFX/lib" \
--add-modules javafx.controls,javafx.fxml,javafx.media \
--class-path out app.MainApp
