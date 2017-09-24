       
# Java Minesweeper Clone

Minesweeper is a game in which there is a grid of buttons that you, the player, can click.

When one of these buttons are pressed, the button checks to see if it has a bomb, and if it 
does, the game is over.

If the button doesn't have a bomb, every button surrounding the one you clicked is also checked
for a bomb, and the number of bombs nearby is displayed on the button you clicked.

If there are no bombs in the surrounding area, the game will check every surrounding button until
it has found nearby bombs. This way you don't have to keep clicking if there are no bombs around!

If you reveal every button that isn't a bomb, you win!

You can also "flag" spaces which you think are bombs. Simply click the "Click to flag" button
and click the spots you think contain a bomb. Now you can keep track of bombs without having to 
remember!

Known bugs: Ocassionaly a button on the corner is accidentaly set as checked even though it hasn't been, which can sometimes make the game unwinnable.
