JFILES = Edit_Sudoko.java \
	 Executeur_Sudoku.java \
	 EcouteurDeTouche.java \
	 ValiderGrille.java \
	 VerifierDoublon.java \
	 SauvegardeGrille.java \
	 Import.java \
	 ResolveurSudoku.java \
	 VerifierRegion.java


CC = javac


compile : $(JFILES)
	$(CC) $(JFILES)


clean :
	-rm -f *.class


Edit :
	java Edit_Sudoko


Exe :
	java Executeur_Sudoku
