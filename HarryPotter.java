/*Name: Mona Afshar and Misheel Batkhuu
  Teacher: Ms. Krasteva
  Date: November 16, 2020
  Assignment: ISP (Harry Potter themed Personality Quiz)
		includes a splashscreen and main menu from which
		the user can choose to play the quiz, view a previous result,
		view instructions, or exit. The quiz will include ten questions,
		although there is a total of 50 that can be selected, and afterwards,
		the user will see a screen displaying their resultant personality type
		along with an in-depth explanation.
		Features from the ICS3UF course are demonstrated in this program,
		including loops, instance variables, constants, private/public methods,
		return methods, parameter passing, files, arrays, if statements,
		try-catch statements, and graphics
  Sources used for questions: https://www.buzzfeed.com/eleanorbate/accurate-af-sorting-quiz
			      https://www.youtube.com/watch?v=wnPGmpymMLI
			      https://www.youtube.com/watch?v=9ypIQw_hato
			      https://www.youtube.com/watch?v=d7zjBtjUKF0
			      https://www.youtube.com/watch?v=34iULqGGwsM
			      https://www.youtube.com/watch?v=wnPGmpymMLI&t=344s
			      https://www.youtube.com/watch?v=wU_1qPSq9bs
			      https://heywise.com/quiz/which-hogwarts-house-do-you-belong-to/13/?hwq=1
Sources used for descriptions:https://harrypotter.fandom.com/wiki/Gryffindor#:~:text=Traits,-Gryffindor's%20founder%2C%20Godric&text=The%20Gryffindor%20house%20emphasises%20the,noted%20to%20be%20short%2Dtempered.
			      https://harrypotter.fandom.com/wiki/Slytherin
			      https://harrypotter.fandom.com/wiki/Hufflepuff
			      https://pottermore.fandom.com/wiki/Ravenclaw
			      https://www.wizardingworld.com/features/big-personalities-of-the-rather-small-house-elves

https://screenrant.com/harry-potter-slytherins-actually-good-wizarding-world/
https://screenrant.com/harry-potter-powerful-gryffindor-students-officially-ranked/

    Mini citation from Misheel: my dad taught me that you can initialize a for loop variable outside of the loop so that even after the
				running of the for loop, you can use the updated variable. I used this technique at around line 1020 in the displayResults method.
*/

import java.awt.*;  //imports java command libraries
import hsa.Console; //imports Console class file
import java.io.*;   //imports reader and writer classes
import java.awt.image.*; //for importing images
import javax.imageio.ImageIO; // so that we can import images

public class HarryPotter
{ //creates a class called HarryPotter
    //Variable declaration
    Console c;  //creates an instance variable of Console class so a new window can be created
    boolean[] isQuestionAsked = new boolean [50];   //if a possible question is already chosen to be asked (default value is false according to Java sources)

    static final int QUESTIONS_NUM = 10;    //unchangeable number of questions to be asked

    int menuChoice = 1; //for the user's Main Menu option (inputted in mainMenu method)

    String userAnswer;  //the answer the user enters for each multiple choice question (used temporarily and updated after question is asked so it can be reused efficiently)
    static final boolean IS_VALUE_VALID = false;  //will keep all while loops asking for user input or searching for a value running until the reaching of a break statement

    int[] housePoints = new int [5]; //array that stores the scores of each personality type in the question method
    int gryffindorPoints = 0;   //running total for the personality type of Gryffindor
    int slytherinPoints = 0;    //running total for the personality type of Slytherin
    int hufflepuffPoints = 0;   //running total for the personality type of Hufflepuff
    int ravenclawPoints = 0;    //running total for the personality type of Ravenclaw
    int houseElfPoints = 0; //running total for the personality type of House Elf

    BufferedReader myReader;    //instance variable of BufferedReader to read files
    PrintWriter myWriter;   //instance variable of PrintWriter to write files

    String gryffindorFile = "gryffindorFile.txt";   //saves the name of the Gryffindor personality file
    String slytherinFile = "slytherinFile.txt"; //saves the name of the Slytherin personality file
    String hufflepuffFile = "hufflepuffFile.txt";   //saves the name of the Hufflepuff personality file
    String ravenclawFile = "ravenclawFile.txt"; //saves the name of the Ravenclaw personality file
    String houseElfFile = "houseElfFile.txt";   //saves the name of the House Elf personality file

    String[] questionFiles = {"question1", "question2", "question3", "question4", "question5", "question6", "question7", "question8", "question9", "question10"  //array for the files with the 50 possible questions
	, "question11", "question12", "question13", "question14", "question15", "question16", "question17", "question18", "question19", "question20"
	, "question21", "question22", "question23", "question24", "question25", "question26", "question27", "question28", "question29", "question30"
	, "question31", "question32", "question33", "question34", "question35", "question36", "question37", "question38", "question39", "question40"
	, "question41", "question42", "question43", "question44", "question45", "question46", "question47", "question48", "question49", "question50"};

    Color backgroundBlue = new Color (0, 49, 120); //creates a dark navy blue shade
    Color navyBlue = new Color (10, 78, 119); //creates a dark greyish-blue shade
    Color moonBlue = new Color (223, 245, 253); //creates a light whitish blue shade
    Color lakeBlue = new Color (49, 131, 180);  //creates a deep royal blue shade
    Color grassGreen = new Color (14, 84, 13); //creates a dark green shade
    Color black = new Color (0, 0, 0);  //creates an obsidian black shade
    Color white = new Color (255, 255, 255);    //creates a pure white shade
    Color castleGrey = new Color (104, 106, 103);   //creates a darkish grey shade
    Color gold = new Color (250, 184, 7);   //creates a yellowish gold shade
    Color woodBrown = new Color (80, 40, 0);   //creates a medium brown shade
    Color brightRed = new Color (255, 0, 0);    //creates a vivid red shade
    Color brightGreen = new Color (0, 255, 0);  //creates a neon green shade
    Color brightOrange = new Color (255, 147, 6);   //creates an orange shade
    Color turquoise = new Color (0, 255, 197);  //creates a neon cyan/turquoise shade

    Font titleFont = new Font ("Cambria", Font.BOLD, 25);   //creates a title-like font
    Font answersFont = new Font ("Calibri", Font.PLAIN, 20);    //creates a font for options text

    public HarryPotter ()
    { //HarryPotter class constructor
	c = new Console (25, 100, "Harry Potter Personality Quiz"); //creates a new Console window of approximately 500x800 pixels, with the title "Harry Potter Personality Quiz" at the top
    } //end of HarryPotter class constructor


    public static void main (String[] args)     //main method of a java application
    {
	HarryPotter hP = new HarryPotter ();    //creates an instance of HarryPotter class
	hP.splashScreen ();     //executes splashScreen method
	while (hP.menuChoice != '4')      //while the user has not chosen to exit in Main Menu
	{
	    hP.mainMenu ();     //executes mainMenu method
	    if (hP.menuChoice == '1')   //if the user chooses 2 (instructions)
	    {
		hP.instructions ();     //execute instructions method
	    }
	    else if (hP.menuChoice == '2')
	    { //alternatively, if the user chooses 1 (quiz)              
		hP.displayQuestions (); //executes displayQuestions method
		hP.displayResults ();      //executes displayResults method
	    }
	    else if (hP.menuChoice == '3')
	    { //alternatively, if the user chooses 3 (previous result)
		hP.savedResults (); //executes savedResults method
	    }
	} //end of Main Menu while loop
	hP.goodbye ();  //executes goodbye method
    } //end of main method


    private void pauseProgram (String message, Color dispColor, Font dispFont, int xPos, int yPos)
    { //pauseProgram method
	String displayText = message;   //stores parameter passed string as the text to display
	Color displayColor = dispColor; //stores parameter passed color as the color to draw the text in
	Font displayFont = dispFont;    //stores parameter passed font as the font to draw the text in
	int horizontalPos = xPos;   //stores parameter passed integer xPos as the horizontal coordinate
	int verticalPos = yPos; //stores parameter passed integer yPos as the vertical coordinate

	c.setColor (displayColor);  //sets the drawing color to displayColor
	c.setFont (displayFont); //sets the drawing font to displayFont
	c.drawString (displayText, horizontalPos, verticalPos);   //draws the text stored as a string in displayText (originally parameter passed as "message") at the parameter passed and locally stored x and y coordinates

	c.getChar ();   //waits for user to enter a key before ending the method and returning to the method from which it was called
    } //end of pauseProgram method


    private boolean doesFileExist (String fileName)
    { //doesFileExist return method
	String fileNameToCheck = fileName;  //stores parameter passed string as the name of the file to check

	try
	{
	    myReader = new BufferedReader (new FileReader (fileNameToCheck));   //initializes a new instance of BufferedReader to read the file with the file name passed
	    String firstLineContents = myReader.readLine (); //reads and stores the first line of the file
	    //if myReader reads a value that matches any of the following house names specified, the file "exists"
	    if (firstLineContents.equals ("GRYFFINDOR"))
	    { 
		return true;   //end execution of this method and return true to calling method
	    }
	    else if (firstLineContents.equals ("SLYTHERIN"))
	    {
		return true;
	    }
	    else if (firstLineContents.equals ("HUFFLEPUFF"))
	    {
		return true;
	    }
	    else if (firstLineContents.equals ("RAVENCLAW"))
	    {
		return true;
	    }
	    else if (firstLineContents.equals ("HOUSE ELF"))
	    {
		return true;
	    }
	    else
	    { //otherwise, no valid values were written into the file and so, the file does not "exist"
		return false;    //end execution of this method and return false to calling method
	    } //end of return value if-else statements
	}
	catch (IOException e)
	{
	} //catches an IOException if necessary
	catch (NullPointerException e)
	{
	} //catches a NullPointerException if necessary

	return false;
    } //end of doesFileExist method


    private void drawBackground (int num)
    { //drawBackground method
	int backgroundChoice = num; //depending on what is passed when calling the method, this method may or may not draw certain things
	//here, the only numbers passed that will do something are 1, 2. and 3

	c.clear (); //clears Console window

	if (backgroundChoice == 1 || backgroundChoice == 2 || backgroundChoice == 3)
	{ //whether the calling method entered 1, 2, or 3, the method will always draw these essential elements
	    drawSky (); //executes drawSky method
	    drawLake (); //executes drawLake method
	    drawGrass ();   //executes drawGrass method
	    drawMoon (); //executes drawMoon method
	}

	if (backgroundChoice == 2)
	{ //if the calling method passed 2, they have chosen to add the castle
	    drawCastle ();  //executes drawCastle method
	}
	else if (backgroundChoice == 3)
	{ //if the calling method passed is 3, they have chosen to add a question screen layout
	    c.setColor (gold);  //sets the color to gold
	    c.fillRect (48, 136, 704, 56);  //draws 5 filled rectangle as borders, one for each plausible answer
	    c.fillRect (48, 209, 704, 56);
	    c.fillRect (48, 282, 704, 56);
	    c.fillRect (48, 355, 704, 56);
	    c.fillRect (48, 428, 704, 56);

	    for (int yPos = 138 ; yPos < 488 ; yPos += 73)
	    { //for loop that will run 5 times (once for each block
		int redVal = 0;
		int greenVal = 15;
		int blueVal = 160;
		for (int boxHeight = 52 ; boxHeight >= 4 ; boxHeight -= 2)
		{ //for loop that will draw rectangles of each block in decreasing height on top of each other, creating a gradient texture
		    c.setColor (new Color (redVal, greenVal, blueVal)); //sets a new color based on the RGB values written right outside this loop (will update inside this loop too)
		    c.fillRect (50, yPos, 700, boxHeight);  //draws a filled rectangle based on variables within loop

		    greenVal += 2;     //updates green and blue colour values
		    blueVal += 3;
		}
	    }
	}
    } //end of drawBackground method


    private void highlightChoice (String choice, int x, int y, int w, int h)
    { //highlightChoice method
	String possibleChoice = choice; //saves passed parameters
	int xPos = x;
	int yPos = y;
	int boxWidth = w;
	int boxHeight = h;

	//drawing the box
	int redVal = 255;   //sets RGB colour values
	int greenVal = 232;
	int blueVal = 0;
	for (int i = boxHeight ; boxHeight >= 4 ; boxHeight -= 2)
	{ //for loop that will draw rectangles on top of each other to create one gradient filled box
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets a new color based on the RGB values written right outside this loop (will update inside this loop too)
	    c.fillRect (xPos, yPos, boxWidth, boxHeight);   //draws a filled rectangle

	    greenVal++;   //updates green and blue colour values
	    blueVal += 4;
	}

	//drawing the answer option selected
	c.setColor (backgroundBlue);    //sets the color to blue
	c.drawString (possibleChoice, x + 10, y + 30);  //draws the answer again
    } //end of highlightChoice method


    public void splashScreen ()
    { //splashScreen method
	//animation
	int wizard2XPos = 800;  //declares and initializes the bottom right-incoming wizard's position
	int wizard4XPos = 700;  //declares and initializes the bottom lifting wizard's position
	int wizard4YPos = 400;

	for (int xPos = 0 ; xPos <= 700 ; xPos += 10)   //keeps drawing the background and a wizard over it until the wizard is in front of the moon
	{
	    synchronized (c)    //to prevent colours from changing at the same time
	    {
		drawBackground (2);  //executes drawBackground method with the castle    (redraws the background every time so there are no marks left)
		drawWizardLeft (xPos, 100, grassGreen); //executes drawWizardLeft method (draws the top wizard)
		drawWizardLeft (xPos - 100, 400, brightRed);    //executes drawWizardLeft method (draws the bottom left-incoming wizard)
		drawWizardRight (wizard2XPos, 350, backgroundBlue);    //executes drawWizardRight method    (draws the bottom right-incoming wizard)
		drawWizardRight (wizard4XPos, wizard4YPos, gold);   //executes drawWizardRight method (the one lifting)

		wizard2XPos -= 10;  //(updates the bottom right-incoming wizard's x position)

		wizard4XPos -= 5;  //(updates the lifting wizard's x and y position so it is moving upward diagonally)
		wizard4YPos -= 5;

		try
		{ //try to pause the program for 100 milliseconds
		    Thread.sleep (100);
		}
		catch (Exception e)
		{ //throw any exception if necessary
		}
	    }
	}


	drawBackground (2); //executes myBackground method with the castle (redraws the background so the bottom wizard's pose can be updated)
	drawWizardLeft (700, 100, grassGreen);  //executes drawWizardLeft method (so that the wizard on top stays drawn)
	drawStandingWizard (600, 400, brightRed);    //executes drawStandingWizard method so that the wizards on the bottom look like they just landed
	drawStandingWizard (100, 350, backgroundBlue);
	drawWizardRight (345, 45, gold);    //executes drawWizardRight method (so that the wizard who lifted off stays drawn)
	//**PROGRAMMER'S NOTE: for some reason, I couldn't calculate the rising wizard's coordinates through a calculator, so I got the console to print them out right after the animation for loop, and then wrote the above coordinates based on that;)

	//animated text
	String[] titleLine1 = {"H", "A", "R", "R", "Y", " ", "P", "O", "T", "T", "E", "R", " ", "H", "O", "U", "S", "E"};   //array for letters in the "Harry Potter House" title line
	String[] titleLine2 = {" ", "P", "E", "R", "S", "O", "N", "A", "L", "I", "T", "Y", " ", "Q", "U", "I", "Z", " "};   //array for letters in the "Personality Quiz" title line
	String[] names = {"B", "y", " ", "M", "o", "n", "a", " ", "A", "f", "s", "h", "a", "r", " ", "a", "n", "d", " ", "M", "i", "s", "h", "e", "e", "l", " ", "B", "a", "t", "k", "h", "u", "u"};    //array for letters in the authors acknowledgement line
	c.setColor (gold);  //sets the colour
	int titleLettersXPos = 120;  //sets the x coordinate of the title letters
	int nameLettersXPos = 500;  //sets the x coordinate of the by-line letters

	for (int i = 0 ; i < names.length ; i++)
	{ //for loop that will draw the letters of the title and authors credit line one by one (or three by three since there are three lines), will run for as many strings there are in the names array
	    synchronized (c)    //to prevent colours from changing at the same time
	    {
		//title
		if (i < titleLine1.length)
		{ //the title will only continue printing as long as the loop doesn't call an array index out of bounds
		    c.setFont (titleFont);  //sets the font to something fancy
		    c.drawString (titleLine1 [i], titleLettersXPos, 370); //draws the first line's letter
		    c.drawString (titleLine2 [i], titleLettersXPos, 420); //draws the second line's letter
		}
		//names
		c.setFont (new Font ("Cambria", Font.ITALIC, 15));   //sets another smaller font for the names
		c.drawString (names [i], nameLettersXPos, 490); //draws the name letters

		//updating
		nameLettersXPos += 8;  //updates the name letters horizontal position so they are not drawing on top of each other
		titleLettersXPos += 35;  //updates the title letters horizontal position so they are not drawing on top of each other
		try
		{ //try to pause the program for 20 milliseconds
		    Thread.sleep (20);
		}
		catch (Exception e)
		{ //throw any exception if necessary
		}
	    }
	}

	pauseProgram ("Press any key to continue", gold, new Font ("Cambria", Font.PLAIN, 30), 250, 470);   //executes the pauseProgram method to prompt the user to press a key and wait until they have
    } //end of splashScreen method


    public void mainMenu ()
    { //mainMenu method
	do //run this code segment once for sure, and repeat if the user does not enter a valid response and the break statement was not reached
	{
	    drawBackground (1); //draws the basic background attributes

	    //drawing the filled rectangle that will hold Main Menu information
	    int redVal = 0; //sets the RGB colours to draw the gradient blue background
	    int greenVal = 30;
	    int blueVal = 80;

	    for (int rectHeight = 300 ; rectHeight >= 20 ; rectHeight -= 10)
	    { //for loop to draw overlapping rectangles with decreasing lightness and height to create a gradient tone
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour to the updated RGB values
		c.fillRect (180, 100, 420, rectHeight); //draw a filled rectangle that crosses the entire width of the screen and the height defined by the for loop's variable

		greenVal += 2;     //updates the RGB values
		blueVal += 4;
	    } //end of for loop for drawing the background

	    c.setColor (gold);  //sets the color to gold
	    c.setFont (new Font ("Cambria", Font.ITALIC, 50));  //sets another font for the main menu title
	    c.drawString ("Main Menu", 250, 70); //draws the main menu letters

	    c.setFont (new Font ("Times New Roman", Font.PLAIN, 40));   //sets a new font
	    c.drawString ("1 = Instructions", 200, 160);    //draws user's options onto the screen
	    c.drawString ("2 = Quiz", 200, 220);
	    c.drawString ("3 = Previous Result", 200, 280);
	    c.drawString ("4 = Exit", 200, 340);
	    menuChoice = c.getChar ();  //reads and stores a key the user enters without displaying it on screen or forcing the user to press enter

	    if (menuChoice != '1' && menuChoice != '2' && menuChoice != '3' && menuChoice != '4')   //if the user presses a key other than the options provided
	    {
		drawErrorBox ("Error: Please only enter options 1, 2, 3 or 4"); //executes drawErrorBox method to draw an error box
	    }
	    else
	    { //if the user enters a valid key, the program can move on to the next segment of code
		break;
	    }
	}while (IS_VALUE_VALID == false); //while the user still hasn't entered a valid response, keep running the above code to prompt them to enter a valid response
	String userChoice;  //declares an instance String variable customized to the user's choice
	c.setColor (Color.green);   //sets the color to a bright green

	if (menuChoice == '1')  //if the user chose to read the instructions
	{
	    c.drawString ("1 = Instructions", 200, 160);    //redraws the option the user selected
	    userChoice = "to read Instructions.";    //assigns the userChoice variable a customized message
	}
	else if (menuChoice == '2') //if the user chose to start the quiz
	{
	    c.drawString ("2 = Quiz", 200, 220);
	    userChoice = "to the personality quiz.";
	}
	else if (menuChoice == '3')
	{
	    c.drawString ("3 = Previous Result", 200, 280);
	    userChoice = "to view your saved result."; // backslash n so that the text will not go beyond the screen and will instead move to the next line
	}
	else
	{
	    c.drawString ("4 = Exit", 200, 340);
	    userChoice = "to exit this program.";
	}
	pauseProgram ("Press a key to continue " + userChoice, gold, new Font ("Cambria", Font.PLAIN, 30), 100, 440); //wait for user input to continue
    } //end of mainMenu method


    public void instructions ()
    { //instructions method

	while (IS_VALUE_VALID == false)
	{
	    int redVal = 0; //sets the RGB colours to draw the gradient blue background
	    int greenVal = 30;
	    int blueVal = 80;

	    for (int rectHeight = 500 ; rectHeight >= 20 ; rectHeight -= 10)
	    { //for loop to draw overlapping rectangles with decreasing lightness and height to create a gradient tone
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour to the updated RGB values
		c.fillRect (0, 0, 800, rectHeight); //draw a filled rectangle that crosses the entire width of the screen and the height defined by the for loop's variable

		greenVal++;     //updates the RGB values
		blueVal += 2;
	    } //end of for loop for drawing the background

	    c.setColor (gold); // set the gold colour for the text
	    c.setFont (new Font ("Cambria", Font.ITALIC, 50));  //sets a new font for the instructions title
	    c.drawString ("Instructions", 250, 70); //writes the title

	    c.setFont (new Font ("Times New Roman", Font.PLAIN, 20)); //sets a new font for the explanation
	    c.drawString ("This program will determine your personality type based on the ", 120, 130); //explanation
	    c.drawString ("multiple choice questions given. In the end you will be able to see your", 120, 160);
	    c.drawString ("reults where you will be classified into one of the 5 personality types:", 120, 190);
	    c.drawString ("1. A Slytherin", 120, 220);
	    c.drawString ("2. A Hufflepuff", 120, 240);
	    c.drawString ("3. A Ravenclaw", 120, 260);
	    c.drawString ("4. A Gryffindor", 120, 280);
	    c.drawString ("5. A House Elf", 120, 300);
	    c.drawString ("The questions will have 5 options each. Simply press the letter option", 120, 330);
	    c.drawString ("you want to select and follow the prompts to continue.", 120, 360);
	    c.drawString ("Make sure to answer the questions truthfully, and HAVE FUN!", 120, 390);
	    pauseProgram ("Press any key to continue", gold, new Font ("Cambria", Font.PLAIN, 30), 220, 470); //calls pause program method
	    break;
	}

    } //end of instructions method


    private void createQuestionFiles ()
    { //createQuestionFiles method
	try
	{ //tries to execute the following statements
	    myWriter = new PrintWriter (new FileWriter (questionFiles [0])); //creates a new file to write into with the string at questionFiles[0] ("question1.txt") as name
	    myWriter.println ("If you caught your friend cheating on a test, you would:");  //prints in the question and skips a line
	    myWriter.println ("Let them copy you next time");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Look the other way and ignore");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("No cheating! Tell the professor!");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Help them study next time");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Convince them to confess");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [1])); //creates a new file to write into with the string at questionFiles[1] as name
	    myWriter.println ("What comment would make you the most hurt?");  //prints in the question and skips a line
	    myWriter.println ("You are weak");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("You are boring");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("You are unkind");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("You are ignorant");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("You are disobedient");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [2])); //creates a new file to write into with the string at questionFiles[2] as name
	    myWriter.println ("Which candy would you choose?");  //prints in the question and skips a line
	    myWriter.println ("Chocolate Frogs");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Bertie Botts Every Flavour Beans ");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Honeyduke Sherbet Lemons");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Fizzy Wizzy ");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I would eat anything");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [3])); //creates a new file to write into with the string at questionFiles[3] as name
	    myWriter.println ("Which pet would you choose?");  //prints in the question and skips a line
	    myWriter.println ("I would choose a dog");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I would choose a lizard");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I would choose a ferret");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I would choose a cat");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [4])); //creates a new file to write into with the string at questionFiles[4] as name
	    myWriter.println ("Which of the following is your favourite class?");  //prints in the question and skips a line
	    myWriter.println ("Potions is my favourite");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Transfiguration is my favourite");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Charms is my favourite");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("History of Magic");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I would rather clean something");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [5])); //creates a new file to write into with the string at questionFiles[5] as name
	    myWriter.println ("Choose one of the following colours:");  //prints in the question and skips a line
	    myWriter.println ("I choose orange");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I choose purple");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I choose pink");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I choose white");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose!");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [6])); //creates a new file to write into with the string at questionFiles[6] as name
	    myWriter.println ("If you could only do one thing for the rest of your life...");  //prints in the question and skips a line
	    myWriter.println ("I would travel");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I would start my own business");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I would raise a family");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I would study");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I would help others");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [7])); //creates a new file to write into with the string at questionFiles[7] as name
	    myWriter.println ("Pick one of the following characters to be your best friend:");  //prints in the question and skips a line
	    myWriter.println ("Probably Ron Weasley");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Probably Tom Riddle");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Probably Moaning Myrtle");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Probably Luna Lovegood");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Probably Harry Potter");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [8])); //creates a new file to write into with the string at questionFiles[8] as name
	    myWriter.println ("Your Patronus Charm would be…");  //prints in the question and skips a line
	    myWriter.println ("Definitely a dragon");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Definitely a panther");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Definitely a hedgehog");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Definitely a fox");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I don't care");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [9])); //creates a new file to write into with the string at questionFiles[9] as name
	    myWriter.println ("You make a mistake that costs your house a Quidditch match, you…");  //prints in the question and skips a line
	    myWriter.println ("Focus on the next match");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Blame your teammates");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Apologize to your teammates");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Practice so it doesn't happen again");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Never play again!!!!!!!!! ");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [10])); //creates a new file to write into with the string at questionFiles[10] as name
	    myWriter.println ("On your semester break from Hogwarts, you…");  //prints in the question and skips a line
	    myWriter.println ("Illegally practice magic");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Partyyyyy it uppppppp!");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Help around the house");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Study for next semester");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Hang out with my friends ");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [11])); //creates a new file to write into with the string at questionFiles[11] as name
	    myWriter.println ("Which trait best describes you:");  //prints in the question and skips a line
	    myWriter.println ("I'm very Intelligent");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I'm very Brave");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I'm very Kind");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I'm Cunning");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I'm Obedient");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [12])); //creates a new file to write into with the string at questionFiles[12] as name
	    myWriter.println ("Which of the following ghosts are your favourite?");  //prints in the question and skips a line
	    myWriter.println ("Definitely the Fat Friar");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Definitely the Grey Lady");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Nearly-Headless Nick");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Probably Bloody Baron");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose one ");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [13])); //creates a new file to write into with the string at questionFiles[13] as name
	    myWriter.println ("Which of the following would you consider your strength?");  //prints in the question and skips a line
	    myWriter.println ("Will power");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Leadership");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Patience");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Creativity");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Loyalty");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [14])); //creates a new file to write into with the string at questionFiles[14] as name
	    myWriter.println ("Which one of the following would you do in your free time?");  //prints in the question and skips a line
	    myWriter.println ("Play a trick on someone");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Sit back and relax");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Help someone do homework");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Read a book");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Organize something");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [15])); //creates a new file to write into with the string at questionFiles[15] as name
	    myWriter.println ("Which of the following are your favourite kind of people:");  //prints in the question and skips a line
	    myWriter.println ("Popular people");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Loyal people");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Kind people");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Smart people");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I love all people");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [16])); //creates a new file to write into with the string at questionFiles[16] as name
	    myWriter.println ("Where is your common room located?");  //prints in the question and skips a line
	    myWriter.println ("7th floor");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Dungeons");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Basement");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("5th floor");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Anywear");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [17])); //creates a new file to write into with the string at questionFiles[17] as name
	    myWriter.println ("What kind of house would you like to own?");  //prints in the question and skips a line
	    myWriter.println ("A Big Bungalow");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Definitely a mansion");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Doesn't matter");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Reasonable house");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("A house with lots of space");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [18])); //creates a new file to write into with the string at questionFiles[18] as name
	    myWriter.println ("If you made a mess what would you do?");  //prints in the question and skips a line
	    myWriter.println ("Clean up and own up");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Leave it and sneak away");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Call your friends to help");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Clean up quickly");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Clean it immediately, leave no trace");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [19])); //creates a new file to write into with the string at questionFiles[19] as name
	    myWriter.println ("How would the people you know describe you?");  //prints in the question and skips a line
	    myWriter.println ("Interested in everything");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Original and creative");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Normal and Helpful");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Smart and Resilient");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Loyal and Obedient");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [20])); //creates a new file to write into with the string at questionFiles[20] as name
	    myWriter.println ("What of the following is your favourite element?");  //prints in the question and skips a line
	    myWriter.println ("Fire");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Water");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Earth");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Air");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Depends on my mood");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [21])); //creates a new file to write into with the string at questionFiles[21] as name
	    myWriter.println ("What job would you take?");  //prints in the question and skips a line
	    myWriter.println ("Auror");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Minister of Magic");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("None of these");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Professor");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Maid/assistant");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [22])); //creates a new file to write into with the string at questionFiles[22] as name
	    myWriter.println ("Which of the following would you hate to be in this life?");  //prints in the question and skips a line
	    myWriter.println ("I hate being Ordinary");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I hate being Cowardly");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I hate being Selfish");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I hate being Ignorant");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I hate being Disobedient ");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [23])); //creates a new file to write into with the string at questionFiles[23] as name
	    myWriter.println ("Which subject interests you the most?");  //prints in the question and skips a line
	    myWriter.println ("Defense against the Dark Arts");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Definitely Potions");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I would like Herbology");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("All of the above");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("None of the above");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [24])); //creates a new file to write into with the string at questionFiles[24] as name
	    myWriter.println ("Which colour would you pick out of the following colours:");  //prints in the question and skips a line
	    myWriter.println ("I would pick Red");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I would pick Green");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I would pick Yellow");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I would pick Blue");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [25])); //creates a new file to write into with the string at questionFiles[25] as name
	    myWriter.println ("Which of these would you enjoy learning in Hogwarts?");  //prints in the question and skips a line
	    myWriter.println ("To fly a broomstick");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Hexes and jinxes");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Magical creatures");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("All of the above!");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("None of the above");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [26])); //creates a new file to write into with the string at questionFiles[26] as name
	    myWriter.println ("Which of these get to you the most?");  //prints in the question and skips a line
	    myWriter.println ("Boredom haunts me");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("People ignoring me");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I can't stand hunger");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I can't stand the cold");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Disorganization");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [27])); //creates a new file to write into with the string at questionFiles[27] as name
	    myWriter.println ("How would you like to be remembered?");  //prints in the question and skips a line
	    myWriter.println ("The Bold");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("The Great");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("The Good");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("The Wise");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("The Loyal");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [28])); //creates a new file to write into with the string at questionFiles[28] as name
	    myWriter.println ("Which potion would you rather drink?");  //prints in the question and skips a line
	    myWriter.println ("Guarantees glory");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Guarantees power");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Guarantees love");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Guarantees wisdom");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Guarantees self-love");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [29])); //creates a new file to write into with the string at questionFiles[29] as name
	    myWriter.println ("Which of the following would be your favourite animal?");  //prints in the question and skips a line
	    myWriter.println ("I like snakes");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I like badgers");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I like lions");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I like eagles");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [30])); //creates a new file to write into with the string at questionFiles[30] as name
	    myWriter.println ("Choose one of the following colours:");  //prints in the question and skips a line
	    myWriter.println ("I choose Red");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I choose Green");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I choose Yellow");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I choose Blue");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [31])); //creates a new file to write into with the string at questionFiles[31] as name
	    myWriter.println ("Choose one of the following colours:");  //prints in the question and skips a line
	    myWriter.println ("I choose Gold");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I choose Silver");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I choose Black");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I choose Bronze");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I can't choose");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [32])); //creates a new file to write into with the string at questionFiles[32] as name
	    myWriter.println ("Which quidditch position would you play?");  //prints in the question and skips a line
	    myWriter.println ("I would be Seeker");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I would be Chaser");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I would be Beater");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I would be Keeper");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I wouldn't play quidditch");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [33])); //creates a new file to write into with the string at questionFiles[33] as name
	    myWriter.println ("Why have you just received a Howler from your parents?");  //prints in the question and skips a line
	    myWriter.println ("Sneaking into the Forbidden Forest.");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Cheating in my Divination O.W.L.");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Nothing! I would never!");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Being caught in the library after hours.");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Stealing something for my friend");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [34])); //creates a new file to write into with the string at questionFiles[34] as name
	    myWriter.println ("Choose a Harry Potter character:");  //prints in the question and skips a line
	    myWriter.println ("I choose Harry Potter");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I choose Tom Riddle");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Artemisia Lufkin");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Garrick Olli render");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Depends on my mood");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [35])); //creates a new file to write into with the string at questionFiles[35] as name
	    myWriter.println ("Which of your skills are you most proud of?");  //prints in the question and skips a line
	    myWriter.println ("My ability to get what I want.");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("My ability to absorb new information");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("My ability to make new friends");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("My ability to keep secrets");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("My ability to obey orders");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [36])); //creates a new file to write into with the string at questionFiles[36] as name
	    myWriter.println ("After finishing homework, where would you go?");  //prints in the question and skips a line
	    myWriter.println ("The Forbidden Forest");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("The Room of Requirement");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Probably the library");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Maybe the kitchens");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Stay in the common room");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [37])); //creates a new file to write into with the string at questionFiles[37] as name
	    myWriter.println ("So which Hogwarts house do you feel you identify with most closely?");  //prints in the question and skips a line
	    myWriter.println ("Of course Gryffindor");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Of course Slytherin");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Of course Hufflepuff");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Of course Ravenclaw");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I don't really know");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [38])); //creates a new file to write into with the string at questionFiles[38] as name
	    myWriter.println ("What genre of movie is your favourite?");  //prints in the question and skips a line
	    myWriter.println ("Action Adventure");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Romantic Comedy");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Mystery Thriller");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Science fiction");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Documentary");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [39])); //creates a new file to write into with the string at questionFiles[39] as name
	    myWriter.println ("What do you do in your common room?");  //prints in the question and skips a line
	    myWriter.println ("Pursue private interests");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Drink tea and relax");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Hang out with friends");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Work on projects");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Organize and clean");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [40])); //creates a new file to write into with the string at questionFiles[40] as name
	    myWriter.println ("Do you think you can keep a friend's secret?");  //prints in the question and skips a line
	    myWriter.println ("I'll take their secrets to my grave");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I think secrets are lonely and should be shared");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Of course I can!");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Secrets are like loans I can gain interest on");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Depends on the friend");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [41])); //creates a new file to write into with the string at questionFiles[41] as name
	    myWriter.println ("What would you do if you got caught doing something wrong?");  //prints in the question and skips a line
	    myWriter.println ("What if it feels right to me?");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I refuse to admit it.");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("I start crying!!!!!!");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I calmly explain why I did it.");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I would never do it again.");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [42])); //creates a new file to write into with the string at questionFiles[42] as name
	    myWriter.println ("A stranger asks you for directions, would you walk them there?");  //prints in the question and skips a line
	    myWriter.println ("Depends on how interesting the place is");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Tell them to follow their instincts");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Walk them part way there");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Point them in the direction");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Lead them the whole way!");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [43])); //creates a new file to write into with the string at questionFiles[43] as name
	    myWriter.println ("You catch your boss breaking work protocol. You…");  //prints in the question and skips a line
	    myWriter.println ("Broke protocol with them...");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Keep it for blackmail file...");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Don't believe in protocol");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Keep it to myself");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Tell one close friend");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [44])); //creates a new file to write into with the string at questionFiles[44] as name
	    myWriter.println ("What does your wardrobe look like?");  //prints in the question and skips a line
	    myWriter.println ("Basic or sporty clothes");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("All black, all the time");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Cozy and comfy");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Layered flowy outfits");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I wear anything");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [45])); //creates a new file to write into with the string at questionFiles[45] as name
	    myWriter.println ("You have a paper due, when do you write it?");  //prints in the question and skips a line
	    myWriter.println ("Few hours before its due");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("On my way to class");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Slowly over a few days");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("I decide to do another topic");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("The day it was assigned");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [46])); //creates a new file to write into with the string at questionFiles[46] as name
	    myWriter.println ("You accidentally read your roommate's diary. What do you do?");  //prints in the question and skips a line
	    myWriter.println ("Read a passage to them");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Accidentally? Nooo");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Try to fix their problems");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Put it back, never speak");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Rewrite it to make it sound better");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [47])); //creates a new file to write into with the string at questionFiles[47] as name
	    myWriter.println ("A spell lets you speak to a specific creature. What kind?");  //prints in the question and skips a line
	    myWriter.println ("I would like Dogs");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("I would like Aliens");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Definitely Songbirds");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Cats, because they're smart");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("I don't care");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [48])); //creates a new file to write into with the string at questionFiles[48] as name
	    myWriter.println ("What decor would best suit your home?");  //prints in the question and skips a line
	    myWriter.println ("Sturdy and strong furniture");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Luxurious and elegant");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Warm and cozy blankets");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Flowy and dreamy designs");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Organizational shelves");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (questionFiles [49])); //creates a new file to write into with the string at questionFiles[49] as name
	    myWriter.println ("A skilled opponent fires an unknown spell at you, and you shout…");  //prints in the question and skips a line
	    myWriter.println ("Expelliarmus!");   //prints in a possible answer (Gryffindor) and skips a line
	    myWriter.println ("Protego!");   //prints in a second possible answer (Slytherin) and skips a line
	    myWriter.println ("Stupefy!");   //prints in a third possible answer (Hufflepuff) and skips a line
	    myWriter.println ("Crucio!");   //prints in a fourth possible answer (Ravenclaw) and skips a line
	    myWriter.println ("Abracadabra!");   //prints in a fifth possible answer (House elf) and skips a line
	    myWriter.close ();  //saves the file
	}

	catch (IOException e)
	{
	} //catches an IOException if necessary
    } //end of createQuestionFiles method


    public void displayQuestions ()
    { //displayQuestions method
	createQuestionFiles (); //executes createQuestionFiles method
	int[] questionsChosen = new int [QUESTIONS_NUM]; //creates an integer array called questionsChosen to hold exactly 10 numbers (10 chosen questions)
	int maxNumber = 50; //initializes an integer variable called maxNumber to store the value 50 so the maximum question number that can be chosen is 50
	int minNumber = 1;  //initializes an integer variable called minNumber to store the value 1 so the minimum question number that can be chosen is 1

	boolean isQuestionAsked[] = new boolean[]
	{
	    false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
	}
	; //resets all values in isQuestionAsked as false

	for (int i = 0 ; i < QUESTIONS_NUM ; i++)
	{ //for loop for determining values for questionsChosen array's indices, that will run exactly 10 times (once for each indice)

	    while (IS_VALUE_VALID == false)
	    { //while the boolean value IS_VALUE_VALID remains false, this while loop will run
		int tempRandQuestion = (int) (Math.random () * maxNumber - minNumber) + 1;   //formula to determine and store a random integer between 1 (minimum) and 50 (inclusive maximum)
		if (isQuestionAsked [tempRandQuestion - 1] == false)
		{ //if the value at the corresponding isQuestionAsked array index is false, this number has not been chosen yet and can be chosen now
		    questionsChosen [i] = tempRandQuestion; //adds this random number to the array questionsChosen
		    isQuestionAsked [tempRandQuestion - 1] = true; //sets the corresponding isQuestionAsked boolean value to true, so should the number be randomly selected again, it cannot be chosen
		    break;  //break out of while loop (and continue on to the next iteration of the for loop
		}
		else
		{ //otherwise, do nothing and keep searching for a valid number
		}
	    } //end of individual array index random number generating loop
	} //end of for loop determining values for all indices of the questionsQuestion array


	//Variable declaration for questions
	String question = "";   //String variable to store the text for the question
	String answer1 = "";    //String variable to store the text for the first option    (Gryffindor)
	String answer2 = "";    //String variable to store the text for the second option   (Slytherin)
	String answer3 = "";    //String variable to store the text for the third option    (Hufflepuff)
	String answer4 = "";    //String variable to store the text for the fourth option   (Ravenclaw)
	String answer5 = "";    //String variable to store the text for the fifth option (House elf)

	try
	{ //try to execute the following statements

	    //Questions
	    for (int i = 0 ; i < QUESTIONS_NUM ; i++) //for loop that will run for every question, displaying questions and answers, and then prompting the user to press/confirm a choice
	    {
		myReader = new BufferedReader (new FileReader (questionFiles [questionsChosen [i]]));   //assigns myReader the question file of the question number saved in questionsChosen array index i
		question = myReader.readLine ();    //reads and stores the values in the file line by line
		answer1 = myReader.readLine ();
		answer2 = myReader.readLine ();
		answer3 = myReader.readLine ();
		answer4 = myReader.readLine ();
		answer5 = myReader.readLine ();

		String[] possibleAnswers = new String[]  //creates a new String array called possibleAnswers to hold the previous answers collected
		{
		    answer1, answer2, answer3, answer4, answer5
		}
		;

		int[] orderOfAnswers = new int[]    //creates a new integer array to store the order in which the answers will be displayed
		{
		    0, 1, 2, 3, 4
		}
		;

		boolean[] isAnswerDisplayed = new boolean[]  //creates a new Boolean array to check if an answer has already been displayed or not and sets all values to false (so for every question, the values will be consistently reset to false so they can be recycled)
		{
		    false, false, false, false, false
		}
		;

		for (int answerNum = 0 ; answerNum < orderOfAnswers.length ; answerNum++)   //for loop that will run 5 times (until the full order of the questions to be asked is determined in answers[])
		{
		    while (IS_VALUE_VALID == false) //while loop that will run until told to break
		    {
			int temp = (int) (Math.random () * 5);  //calculates a random integer between 0 and 5 (exclusive)

			if (isAnswerDisplayed [temp] == false)  //if the answer has not been displayed yet
			{
			    orderOfAnswers [answerNum] = temp; //it can be added into the order of answers
			    isAnswerDisplayed [temp] = true;    //the answer's place in the boolean array will be changed to true so it cannot be added again into the order

			    break;  //break out of while loop and continue onto the next value in the order of answers array
			}
		    }
		} //end of for loop determining the order the answers will be displayed in

		while (IS_VALUE_VALID == false) //while loop that will run until explicitely told to break
		{
		    drawBackground (3); //executes the drawBackground method with question layout

		    c.setFont (titleFont);  //set font to a title font
		    c.setColor (gold);  //set color to gold
		    c.drawString (question, 0, 100);    //draws the question text as a string at the x and y coordinates 0 by 100

		    c.setFont (answersFont);    //set font to an answer font
		    c.setColor (gold);  //set color to gold
		    int textYPos[] = new int[]  //integer array for the different vertical positions the answers will be displayed at depending on their letter
		    {
			170, 243, 316, 389, 462
		    }
		    ;

		    String[] letterOptions = new String[]  //string array to hold all possible valid values the user can enter in terms of answering a question
		    {
			"A", "B", "C", "D", "E"
		    }
		    ;

		    for (int answerNum = 0 ; answerNum < letterOptions.length ; answerNum++)    //for loop that will draw every letter and answer associated with it. Conveniently (well, actually, I planned for this to happen), the index of the letter in the String array is the same as the place of the answers in the order of answers array. After all, they have the same length.
		    {
			c.drawString (letterOptions [answerNum] + " = " + possibleAnswers [orderOfAnswers [answerNum]], 60, textYPos [answerNum]);    //draws the letter at the letterOptions array's index answerNum, as well as the corresponding answer, and at the corresponding y coordinate (the x coordinate remains the same)
		    } //end of for loop displaying answers

		    userAnswer = ("" + c.getChar ()).toUpperCase ();  //assigns the userAnswer String variable the value of nothing plus a character entered by the user, and then converts the result to upper case.

		    boolean isChoiceValid = false;    //creates a boolean value to check if the user's value was valid or not
		    int letterNum = 0;  //sets the for loop variable outside of the for loop so its value can be accessed outside that specific for loop
		    for (; letterNum < letterOptions.length ; letterNum++)  //for loop that will run and check if the user entered any of the 5 letters (if they entered the non-capitalized version, the program will have uppercased their input automatically anyway
		    {
			if (letterOptions [letterNum].equals (userAnswer))  //if the user's entered choice matches a letter, the isChoiceValid boolean value will be changed to true and the program can break out of this for loop since it has found a match
			{
			    isChoiceValid = true;
			    break;
			}
		    }
		    if (isChoiceValid == true)  //if, in the previous for loop, a match was found and the isChoiceValid value was changed to true, the following code will execute
		    {
			highlightChoice ((userAnswer + " = " + possibleAnswers [orderOfAnswers [letterNum]]), 50, (textYPos [letterNum] - 31), 700, 50);   //highlight that answer box
			c.setColor (gold);  //sets the color to gold
			c.drawString ("Press enter '" + userAnswer + "' again to confirm your choice or another letter to cancel.", 100, 125);  //draws text, prompting the user to press a key to either confirm or cancel

			String confirmedChoice = ("" + c.getChar ()).toUpperCase ();    //assigns a new String variable called confirmedChoice, which is basically a user-entered character that is capitalized to match the capitalized alphabet it will be compared to

			if (confirmedChoice.equals (userAnswer))    //if the user's entered key matches their first answer, they have confirmed their choice
			{
			    housePoints [orderOfAnswers [letterNum]]++; //add the points of the house at the housePoints array's index that matches the orderOfAnswers current referral index
			    break;  //break out of the while loop and onto the next question
			}
			else    //otherwise, if the choice does not match, do nothing and go back to the top of the loop, redrawing everything before it was highlighted so the user has cancelled their original answer
			{

			}

		    }
		    else    //if the user's entered value is not A, B, C, D, or E, execute an errortrap
		    {
			drawErrorBox ("Error: Please only enter options A, B, C, D, or E."); //executes drawErrorBox method
		    } //end of if-else statements checking if the user entered a valid answer
		} //end of while loop displaying and drawing the question and answers
	    } //end of for loop that will run for every question
	}
	catch (IOException e)
	{
	} //catch an IOException if necessary
	try //draws the special circles like a loading symbol
	{
	    synchronized (c)
	    {
		drawSpecialCircle (400, 200);
		Thread.sleep (100);
		drawSpecialCircle (425, 225);
		Thread.sleep (100);
		drawSpecialCircle (450, 250);
		Thread.sleep (100);
		drawSpecialCircle (425, 270);
		Thread.sleep (100);
		drawSpecialCircle (400, 300);
		Thread.sleep (100);
		drawSpecialCircle (375, 270);
		Thread.sleep (100);
		drawSpecialCircle (350, 250);
		Thread.sleep (100);
		drawSpecialCircle (375, 225);
		Thread.sleep (100);
	    }
	}
	catch (Exception e)
	{
	}
    } //end of displayQuestions method


    private void createResultFiles ()
    { //createQuestionFiles method
	try
	{ //tries to execute the following statements
	    myWriter = new PrintWriter (new FileWriter (gryffindorFile)); //make a file to store an explanation of gryffindors
	    myWriter.println ("You are a Gryffindor!"); //explanation
	    myWriter.println ();
	    myWriter.println ("With a love for adventure and a heart of courage, you most ");
	    myWriter.println ("definitely belong to the House of Gryffindor. Charismatic, ");
	    myWriter.println ("bold, and justice-driven, it is your honorable kind that ");
	    myWriter.println ("people look up to to lead them through challenges, like a  ");
	    myWriter.println ("lion guiding a pack. However, what's truly special about");
	    myWriter.println ("your personality is how people-attractive it is. Your fierce ");
	    myWriter.println ("loyalty, integrity, and ability to have fun with comrades ");
	    myWriter.println ("makes you someone people love being around. Just remember that ");
	    myWriter.println ("while your nerve and bravery is admirable, you should never let ");
	    myWriter.println ("your short-temperedness and recklessness overcloud your better  ");
	    myWriter.println ("judgement.");
	    myWriter.println ();
	    myWriter.println ("Notable Gryffindors include Harry Potter, Minerva McGonagall, Sirius Black, Albus Dumbledore, ");
	    myWriter.println ("and Percy Wealsey.");
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (slytherinFile)); //make a file to store an explanation of slytherins
	    myWriter.println ("You are a Slytherin!"); //explanation
	    myWriter.println ();
	    myWriter.println ("With a strong will and a clever brain, you belong to the ");
	    myWriter.println ("House of Slytherin. You are destined for greatness and you ");
	    myWriter.println ("know it. Your work is always of the highest quality because ");
	    myWriter.println ("you are the first to start planning and never let distractions");
	    myWriter.println ("interfere. Ambition dominates your visions, confidence inhabits ");
	    myWriter.println ("your decisions, and resourcefulness is your philosophy, which ");
	    myWriter.println ("makes you a steady, professional leader, with refined, classic ");
	    myWriter.println ("taste. Just remember that life is more than writing up and");
	    myWriter.println ("attaining objectives. Cherish the journey too. Understand that ");
	    myWriter.println ("not everyone's the same, and that's okay. Your friends are your ");
	    myWriter.println ("comrades so help, rely on, and trust each other and you'll go farther. ");
	    myWriter.println ();
	    myWriter.println ("Notable Slytherins include Merlin, Severus Snape, Draco Malfoy, Andromeda Tonks, and Albus ");
	    myWriter.println ("Severus Potter. ");
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (hufflepuffFile)); //makes a file to store an explanation of hufflepuffs
	    myWriter.println ("You are a Hufflepuff!"); //explanation
	    myWriter.println ();
	    myWriter.println ("Trustworthy, loyal, and hardworking, just like your house");
	    myWriter.println ("founder, Helga Hufflepuff, you are among the few purely good ");
	    myWriter.println ("people in this world. While you are incredibly dedicated to ");
	    myWriter.println ("your goals, you neverlet that get in the way of your morals. ");
	    myWriter.println ("You always put others before yourself and never, ever take ");
	    myWriter.println ("credit for it because of your modesty. Overall, your virtues  ");
	    myWriter.println ("lead you to becoming the best version of yourself and the friend ");
	    myWriter.println ("everyone needs. Just remember that while other houses' character ");
	    myWriter.println ("character traits may seem more special than yours, you are ");
	    myWriter.println ("extremely special and valued as you are. Have confidence in your ");
	    myWriter.println ("abilities and in your beautiful personality.");
	    myWriter.println ();
	    myWriter.println ("Notable Gryffindors include Harry Potter, Minerva McGonagall, Sirius Black,");
	    myWriter.println ("Albus Dumbledore, and Percy Wealsey.");
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (ravenclawFile)); //makes a file to store an explanation of ravenclaws
	    myWriter.println ("You are a Ravenclaw!"); //explanation
	    myWriter.println ();
	    myWriter.println ("Defined by your sharp sense of wit and boundless creativity,");
	    myWriter.println (" your kind is responsible for filling our world with diversity");
	    myWriter.println ("while also advancing and developing society for the better");
	    myWriter.println ("with numerous new inventions and ideas. You value ");
	    myWriter.println ("intelligence and competitiveness, as you are extremely ");
	    myWriter.println ("competitive in practically every aspect of your life and have ");
	    myWriter.println ("impossible high standards, namely in academics. But more than ");
	    myWriter.println ("being clever, you are a unique, out-of-the-box thinking, and ");
	    myWriter.println ("original individual- a force to be reckoned with. However, ");
	    myWriter.println ("remember that other people have unique desirable traits too, ");
	    myWriter.println ("so try not to always be overly individualistic and instead, ");
	    myWriter.println ("share your dreams with those around you.");
	    myWriter.println ("Notable Ravenclaws include Filius Flitwick, Garrick Ollivander, Cho Chang, Luna Lovegood, and ");
	    myWriter.println ("Quirinus Quirrell.");
	    myWriter.close ();  //saves the file

	    myWriter = new PrintWriter (new FileWriter (houseElfFile)); //makes a file to store an explanation of house elfs
	    myWriter.println ("You are a House Elf!"); //explanation
	    myWriter.println ();
	    myWriter.println ("Sweet, protective, and dutiful up to the point of self-harm, your ");
	    myWriter.println ("integrity and purity renders you too virtuous for any official  ");
	    myWriter.println ("Hogwarts House. This down-to-earth creature is one who, ");
	    myWriter.println ("although small-sized, has an enormous heart and an enormous   ");
	    myWriter.println ("impact on the lives of those around it. You always put others   ");
	    myWriter.println ("before yourself and are the embodiment of loyalty and generosity. ");
	    myWriter.println ("It is people like you who make this world a better place and many, ");
	    myWriter.println ("whether they admit it or not, owe their lives to you. Just remember");
	    myWriter.println ("that sometimes it's okay to think about yourself too. You are just");
	    myWriter.println ("as precious as the people you protect so dearly, so take some time  ");
	    myWriter.println ("off to love yourself a bit more.");
	    myWriter.println ();
	    myWriter.println ("Notable House Elfs include Dobby, Winky, and Kreacher.");
	    myWriter.println ();
	    myWriter.close ();  //saves the file
	}
	catch (IOException e)
	{
	} //catches an IOException if necessary
    } //end of createQuestionFiles method


    public void displayResults ()
    { //displayResults method
	createResultFiles ();   //executes the createResultsFiles method

	String[] resultLines = new String [17]; //an array to store the file information

	gryffindorPoints = housePoints [0];   //set the gryffindor points to housepoints 0 value
	slytherinPoints = housePoints [1];  //set the slytherin points to housepoints 1 value
	hufflepuffPoints = housePoints [2];  //set the hufflepuff points to housepoints 2 value
	ravenclawPoints = housePoints [3];  //set the ravenclaw points to housepoints 3 value
	houseElfPoints = housePoints [4]; //set the house elf points to housepoints 4 value

	String resultingHouse = ""; //creates an instance variable to hold the final house the user is sorted into

	while (IS_VALUE_VALID == false)
	{
	    int redVal = 0; //sets the RGB colours to draw the gradient blue background
	    int greenVal = 30;
	    int blueVal = 80;

	    for (int rectHeight = 500 ; rectHeight >= 20 ; rectHeight -= 10)
	    { //for loop to draw overlapping rectangles with decreasing lightness and height to create a gradient tone
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour to the updated RGB values
		c.fillRect (0, 0, 800, rectHeight); //draw a filled rectangle that crosses the entire width of the screen and the height defined by the for loop's variable

		greenVal++;     //updates the RGB values
		blueVal += 2;
	    } //end of for loop for drawing the background

	    try
	    {
		if (gryffindorPoints >= slytherinPoints && gryffindorPoints >= hufflepuffPoints && gryffindorPoints >= ravenclawPoints && gryffindorPoints >= houseElfPoints)
		{ // if gryffindor is greater than all other points
		    resultingHouse = "GRYFFINDOR";  //assigns resultingHouse the value of Gryffindor
		    myReader = new BufferedReader (new FileReader (gryffindorFile)); //read from gryffindor file
		    BufferedImage logo = ImageIO.read (new File ("gryffindorImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("gryffindorImage.png")); //the gryffindor image found at https://www.freepnglogos.com/pics/hogwarts
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (slytherinPoints > gryffindorPoints && slytherinPoints >= hufflepuffPoints && slytherinPoints >= ravenclawPoints && slytherinPoints >= houseElfPoints)
		{ // if slytherin is greater than all other points
		    resultingHouse = "SLYTHERIN";  //assigns resultingHouse the value of Slytherin
		    myReader = new BufferedReader (new FileReader (slytherinFile)); //read from slytherin file
		    BufferedImage logo = ImageIO.read (new File ("slytheinImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("slytheinImage.png")); //the slytherin image found at https://www.pngkey.com/maxpic/u2w7r5q8i1w7t4e6/
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (hufflepuffPoints > gryffindorPoints && hufflepuffPoints > slytherinPoints && hufflepuffPoints >= ravenclawPoints && hufflepuffPoints >= houseElfPoints)
		{ // if hufflepuff is greater than all other points
		    resultingHouse = "HUFFLEPUFF";  //assigns resultingHouse the value of Hufflepuff
		    myReader = new BufferedReader (new FileReader (hufflepuffFile)); //read from hufflepuff file
		    BufferedImage logo = ImageIO.read (new File ("hufflepuffImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 485, -10, null); //call the image
			logo = ImageIO.read (new File ("hufflepuffImage.png")); //the hufflepuff image found at https://www.freepnglogos.com/images/hogwarts-7939.html
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (ravenclawPoints > gryffindorPoints && ravenclawPoints > hufflepuffPoints && ravenclawPoints > slytherinPoints && ravenclawPoints >= houseElfPoints)
		{ // if ravenclaw is greater than all other points
		    resultingHouse = "RAVENCLAW";  //assigns resultingHouse the value of Ravenclaw
		    myReader = new BufferedReader (new FileReader (ravenclawFile)); //read from ravenclaw file
		    BufferedImage logo = ImageIO.read (new File ("ravenclawImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("ravenclawImage.png")); //the ravenclaw image found at https://www.pinclipart.com/pindetail/Twxwho_crest-png-for-free-download-on-harry-potter/
		    }
		    catch (IOException e)
		    {
		    }

		}
		else
		{ //otherwise, house elf is the user's result
		    resultingHouse = "HOUSE ELF";  //assigns resultingHouse the value of House elf
		    myReader = new BufferedReader (new FileReader (houseElfFile)); //read from house elf file

		    BufferedImage logo = ImageIO.read (new File ("houseElfImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("houseElfImage.png")); //the ravenclaw image found at https://www.pinclipart.com/pindetail/Twxwho_crest-png-for-free-download-on-harry-potter/
		    }
		    catch (IOException e)
		    {
		    }
		}

		String line = ""; //stores the data from each line
		int count = 0; //counts the array location
		while (line != null) //while the line is not null
		{
		    resultLines [count] = line;   //stores the values in the file line by line
		    line = myReader.readLine (); //read each line
		    count++; //increase array place holder
		}
	    }
	    catch (IOException e)
	    {
	    } //catches an IOException if necessary


	    c.setColor (gold); //set text color to gold
	    c.setFont (new Font ("Cambria", Font.PLAIN, 18));  //sets a new font

	    int yAxis = 20; //the location of the line lowers
	    for (int display = 0 ; display < resultLines.length ; display++) //while display is smaller than result array
	    {
		c.drawString (resultLines [display], 20, yAxis); //draw the line of text
		yAxis += 25; //increase the y-axis location on the page
	    }

	    c.setColor (gold);  //sets the colour as gold
	    c.setFont (new Font ("Cambria", Font.PLAIN, 30));   //sets the font
	    c.drawString ("Press '1' to save and continue, and '2' to simply continue", 50, 470);   //prompts user to make a decision

	    char saveOrNot = c.getChar ();  //stores a user-pressed key into a character variable called saveOrNot

	    if (saveOrNot == '1' || saveOrNot == '2')
	    {
		if (saveOrNot == '1')   //if the user chose to save their results
		{
		    c.setColor (Color.green);   //sets the color as green
		    c.drawString ("'1' to save and continue", 127, 470);    //colors in the user's decision

		    try
		    { //try to execute the following statements
			myWriter = new PrintWriter (new FileWriter ("savedResults.txt"));   //assigns a Writer the duty to write data into a file called savedResults
			myWriter.print (resultingHouse); //prints into the file the user's resulting house
			myWriter.close ();  //saves the file
		    }
		    catch (IOException e)
		    {
		    } //throws an IOException if neccessary
		}
		else
		{ //otherwise, the user has chosen to simply continue, so do nothing except highlight the decisionand simply finish the method
		    c.setColor (Color.green);   //sets the color as green
		    c.drawString ("'2' to simply continue", 501, 470);  //colors in the user's decision
		}
		pauseProgram ("Press any key to return to Main Menu.", gold, new Font ("Cambria", Font.PLAIN, 20), 230, 490);    //pauses the program and prompts the user to press one more key
		break;  //break out of while loop and then out of the method

	    }
	    else
	    { //if the user entered anything that isn't 1 or 2
		drawErrorBox ("Error: Only enter either 1 or 2");   //executes drawErrorBox method, with no break statement so the while loop will run again to ask for valid user input
	    }
	}
    } //end of displayResults method


    public void savedResults ()
    { //savedResults method
	String savedHouse = "";  //initializes the variable that will store the user's saved house
	boolean isFileExisting = doesFileExist ("savedResults.txt");    //calls the doesFileExist return method to check if the user has a saved result

	if (isFileExisting == true) //if the file does indeed exist, print out the user's result based on what the file says
	{
	    int redVal = 0; //sets the RGB colours to draw the gradient blue background
	    int greenVal = 30;
	    int blueVal = 80;

	    for (int rectHeight = 500 ; rectHeight >= 20 ; rectHeight -= 10)
	    { //for loop to draw overlapping rectangles with decreasing lightness and height to create a gradient tone
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour to the updated RGB values
		c.fillRect (0, 0, 800, rectHeight); //draw a filled rectangle that crosses the entire width of the screen and the height defined by the for loop's variable

		greenVal++;     //updates the RGB values
		blueVal += 2;
	    } //end of for loop for drawing the background

	    String[] resultLines = new String [17]; //an array to store the file information

	    try
	    { //try to execute the following statements
		myReader = new BufferedReader (new FileReader ("savedResults.txt"));    //assigns myReader the duty of reading the savedResults file
		savedHouse = myReader.readLine ();   //reads the first line to discover what house the user previously had
	    }
	    catch (IOException e)
	    {
	    } //catches an IOException if neccessary


	    try
	    { //try to execute the following statements
		if (savedHouse.equals ("GRYFFINDOR"))
		{ //assigns myReader the house file that corresponds with the saved house in the savedResults file
		    myReader = new BufferedReader (new FileReader (gryffindorFile));
		    BufferedImage logo = ImageIO.read (new File ("gryffindorImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("gryffindorImage.png")); //the gryffindor image
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (savedHouse.equals ("SLYTHERIN"))
		{
		    myReader = new BufferedReader (new FileReader (slytherinFile));
		    BufferedImage logo = ImageIO.read (new File ("slytheinImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("slytheinImage.png")); //the gryffindor image
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (savedHouse.equals ("HUFFLEPUFF"))
		{
		    myReader = new BufferedReader (new FileReader (hufflepuffFile));
		    BufferedImage logo = ImageIO.read (new File ("hufflepuffImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 485, -10, null); //call the image
			logo = ImageIO.read (new File ("hufflepuffImage.png")); //the gryffindor image
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (savedHouse.equals ("RAVENCLAW"))
		{
		    myReader = new BufferedReader (new FileReader (ravenclawFile));
		    BufferedImage logo = ImageIO.read (new File ("ravenclawImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("ravenclawImage.png")); //the gryffindor image
		    }
		    catch (IOException e)
		    {
		    }
		}
		else if (savedHouse.equals ("HOUSE ELF"))
		{
		    myReader = new BufferedReader (new FileReader (houseElfFile));
		    BufferedImage logo = ImageIO.read (new File ("houseElfImage.png")); //buffered reader
		    try
		    {
			c.drawImage (logo, 500, 0, null); //call the image
			logo = ImageIO.read (new File ("houseElfImage.png")); //the ravenclaw image found at https://www.pinclipart.com/pindetail/Twxwho_crest-png-for-free-download-on-harry-potter/
		    }
		    catch (IOException e)
		    {
		    }
		} //end of else-if-else statements assigning the reader to the right file

		String line = ""; //stores the data from each line
		int count = 0; //counts the array location
		while (line != null) //while the line is not null
		{
		    resultLines [count] = line;   //stores the values in the file line by line
		    line = myReader.readLine (); //read each line
		    count++; //increase array place holder
		}
	    }
	    catch (IOException e)
	    {
	    } //catches an IOException if necessary

	    c.setColor (gold); //set text color to gold
	    c.setFont (new Font ("Cambria", Font.PLAIN, 18));  //sets a new font

	    int yAxis = 20; //the location of the line lowers
	    for (int display = 0 ; display < resultLines.length ; display++) //while display is smaller than result array
	    {
		c.drawString (resultLines [display], 20, yAxis); //draw the line of text
		yAxis += 25; //increase the y-axis location on the page
	    }
	    pauseProgram ("Press a key to return to Main Menu", gold, new Font ("Cambria", Font.PLAIN, 30), 100, 470);  //executes pauseProgram method
	}


	else
	{ //if the file does not exist
	    drawErrorBox ("Error: You do not have a saved result yet.");    //executes error box method
	}
    } //end of savedResults method


    public void goodbye ()
    { //goodbye method
	drawBackground (2);  //executes drawBackground method with the castle
	//drawing the same wizards from the splashscreen, but with no animation
	drawWizardLeft (700, 100, grassGreen);  //executes drawWizardLeft method (wizard on top next to moon)
	drawStandingWizard (600, 400, brightRed);    //executes drawStandingWizard method (wizards on the ground)
	drawStandingWizard (100, 350, backgroundBlue);
	drawWizardRight (345, 45, gold);    //executes drawWizardRight method (wizard on top near the left center of screen)

	//animated text
	String[] messageLine1 = {"T", "h", "a", "n", "k", " ", "y", "o", "u", " ", "f", "o", "r", " ", "t", "a", "k", "i", "n", "g", " ", "o", "u", "r", " ", "p", "e", "r", "s", "o", "n", "a", "l", "i", "t", "y", " ", "q", "u", "i", "z"};   //arrays for letters in each message line};
	String[] messageLine2 = {"t", "o", "d", "a", "y", ".", " ", "W", "e", " ", "h", "o", "p", "e", " ", "t", "h", "a", "t", " ", "y", "o", "u", " ", "e", "n", "j", "o", "y", "e", "d", " ", "y", "o", "u", "r", " ", "t", "i", "m", "e"};
	String[] messageLine3 = {" ", " ", "d", "i", "v", "i", "n", "g", " ", "i", "n", "t", "o", " ", "t", "h", "e", " ", "H", "a", "r", "r", "y", " ", "P", "o", "t", "t", "e", "r", " ", "U", "n", "i", "v", "e", "r", "s", "e", ".", " "};
	String[] messageLine4 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};
	String[] messageLine5 = {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", "H", "a", "v", "e", " ", "a", " ", "n", "i", "c", "e", " ", "d", "a", "y", "!", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " ", " "};

	c.setColor (gold);  //sets the colour
	c.setFont (new Font ("Cambria", Font.ITALIC, 25));  //sets a new font
	int messageLettersXPos = 120;  //sets the x coordinate of the title letters

	for (int i = 0 ; i < messageLine1.length ; i++)
	{ //for loop that will draw the letters of the message lines one by one, will run for as many strings there are in the array messageLine1 (since they are all the same length anyway)
	    synchronized (c)    //to prevent colours from changing at the same time
	    {
		c.drawString (messageLine1 [i], messageLettersXPos, 360);   //draws each line letter by letter at the same time, increasing the y (vertical value) per line
		c.drawString (messageLine2 [i], messageLettersXPos, 390);
		c.drawString (messageLine3 [i], messageLettersXPos, 420);
		c.drawString (messageLine4 [i], messageLettersXPos, 450);
		c.drawString (messageLine5 [i], messageLettersXPos, 480);

		messageLettersXPos += 15;   //update the horizontal position of the letters so that when they redraw, they don't redraw on top of each other
		try
		{ //try to pause the program for 20 milliseconds
		    Thread.sleep (20);
		}
		catch (Exception e)
		{ //throw any exception if necessary
		}
	    }
	}
    } //end of goodbye method


    private void drawCastle ()
    { //drawCastle method
	//base
	int redVal = 0; //sets the red colour value
	int greenVal = 0;   //sets the green colour value
	int blueVal = 0;    //sets the blue colour value

	for (int sizeLength = 150 ; sizeLength >= 10 ; sizeLength -= 4)
	{ //for loop to draw rectangles of grey on top of each other, with lessening height, from darker to lighter
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour using the RGB values assigned before the loop
	    c.fillRect (150, 170, 500, sizeLength); //draws a filled rectangle with the same coordinates and width, but decreasing height every iteration
	    redVal += 2; //increases red value by 4
	    greenVal += 2;  //increases green value by 4
	    blueVal += 2;   //increases blue value by 4
	}


	//towers
	for (int sizeLength = 70 ; sizeLength >= 5 ; sizeLength -= 5)
	{ //for loop to draw rectangles of grey on top of each other, with lessening height, from darker to lighter (continuing off from the base)
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour using the RGB values assigned before the loop
	    //leftmost tower
	    c.fillRect (150, 100, 100, sizeLength); //draws a filled rectangle with the same coordinates and width, but decreasing height every iteration
	    //rightmost tower
	    c.fillRect (550, 100, 100, sizeLength);
	    //middle tower
	    c.fillRect (350, 70, 100, sizeLength + 30);


	    //mini blocks
	    if (sizeLength >= 40)
	    { //as long as the size length is greater than 40, these mini blocks will draw (their full height is 40 less than that of the castle base)
		c.fillRect (265, 140, 25, sizeLength - 40); //leftmost mini block
		c.fillRect (310, 140, 25, sizeLength - 40); //left-middle mini block
		c.fillRect (470, 140, 25, sizeLength - 40); //right-middle mini block
		c.fillRect (510, 140, 25, sizeLength - 40); //rightmost mini block
	    }

	    redVal += 3;    //increases red value by 4
	    greenVal += 3;  //increases green value by 4
	    blueVal += 3;   //increases blue value by 4
	}


	int[] xPos = new int[]
	{
	    150, 200, 250
	}


	;                                       //creates an array of x coordinates
	int[] yPos = new int[]
	{
	    100, 50, 100
	}


	;                                       //creates an array of y coordinates
	c.fillPolygon (xPos, yPos, 3);  //draws a filled triangle as the leftmost tower top
	xPos [0] = 550; //updates x coordinates for rightmost tower position
	xPos [1] = 600;
	xPos [2] = 650;
	c.fillPolygon (xPos, yPos, 3);  //draws a filled triangle as the rightmost tower top
	xPos [0] = 350; //updates x coordinates for middle tower position
	xPos [1] = 400;
	xPos [2] = 450;
	yPos [0] = 70;  //updates y coordinates for middle tower position
	yPos [1] = 10;
	yPos [2] = 70;
	c.fillPolygon (xPos, yPos, 3);  //draws a filled triangle as the middle tower top


	//trapezoid path
	int[] xGroundPos = new int[]
	{
	    320, 370, 430, 480
	}


	;                                                   //creates an array of x coordinates
	int[] yGroundPos = new int[]
	{
	    490, 330, 330, 490
	}


	;                                                   //creates an array of y coordinates

	redVal = 0; //sets the RGB values to 0 (black)
	greenVal = 0;
	blueVal = 0;

	for (int lowerYPos = 490 ; lowerYPos >= 330 ; lowerYPos -= 3)
	{ //for loop that will draw trapezoids on top of each other (the lowerYPos is the lowest y-coordinate)
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour depending on the RGB values assigned before the loop and updated inside
	    c.fillPolygon (xGroundPos, yGroundPos, 4);  //draws a filled trapezoid

	    xGroundPos [0] += 1; //updates the lower x coordinates so that the trapezoids are consistently getting smaller and upwards
	    xGroundPos [3] -= 1;
	    yGroundPos [0] = lowerYPos; //updates the lower y coordinates
	    yGroundPos [3] = lowerYPos;

	    redVal += 2; //updates RGB values by 2
	    greenVal += 2;
	    blueVal += 2;
	}


	//door
	c.setColor (woodBrown); //sets the colour to a wooden brown
	c.drawOval (364, 189, 77, 37);  //draws upper arch of door's border
	c.fillRect (364, 209, 77, 102); //draws door's border

	c.setColor (new Color (111, 76, 0));    //sets the colour to a light brown
	c.fillOval (365, 190, 75, 35); //draws upper arch of door

	redVal = 45;    //sets RGB colour values
	greenVal = 10;
	blueVal = 0;

	for (int sizeLength = 100 ; sizeLength > 1 ; sizeLength -= 3)
	{ //for loop that will draw rectangles on top of each other, lessening the height every time
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
	    c.fillRect (365, 210, 75, sizeLength);  //draws a filled rectangle

	    redVal += 2; //updates red and green colour values
	    greenVal += 2;
	}


	//windows
	for (int i = 165 ; i <= 225 ; i += 20)
	{ //leftmost tower long windows
	    redVal = 255;   //sets the RGB values
	    greenVal = 200;
	    blueVal = 0;
	    for (int sizeLength = 60 ; sizeLength > 1 ; sizeLength -= 3)
	    { //for loop that will draw rectangles on top of each other with decreasing height, will repeat for every window drawn
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
		c.fillRect (i, 110, 10, sizeLength);    //draws a filled rectangle
		greenVal += 2;  //updates blue and green colour values
		blueVal += 4;
	    }
	}


	for (int i = 365 ; i <= 425 ; i += 20)
	{ //middle tower windows
	    redVal = 255;   //sets the RGB values
	    greenVal = 200;
	    blueVal = 0;
	    for (int sizeLength = 60 ; sizeLength > 1 ; sizeLength -= 3)
	    { //for loop that will draw rectangles on top of each other with decreasing height
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
		c.fillRect (i, 90, 10, sizeLength); //long windows
		if (sizeLength >= 50)
		{ //if the sizeLength is greater than or equal to 50, the tiny windows will be drawn, as their maximum height is 50 less than that of the long windows
		    c.fillRect (i, 70, 10, sizeLength - 50); //tiny windows on top
		    c.fillRect (i, 160, 10, sizeLength - 50);  //tiny windows on bottom
		}
		greenVal += 2;  //updates blue and green colour values
		blueVal += 4;
	    }
	}


	for (int i = 565 ; i <= 625 ; i += 20)
	{ //rightmost tower long windows
	    redVal = 255;   //sets the RGB values
	    greenVal = 200;
	    blueVal = 0;
	    for (int sizeLength = 60 ; sizeLength > 1 ; sizeLength -= 3)
	    { //for loop that will draw rectangles on top of each other with decreasing height, will repeat for every window drawn
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
		c.fillRect (i, 110, 10, sizeLength);    //draws a filled rectangle
		greenVal += 2;  //updates blue and green colour values
		blueVal += 4;
	    }
	}


	for (int i = 185 ; i <= 270 ; i += 25)
	{ //castle base tiny windows (for loop for rows)
	    for (int j = 165 ; j < 345 ; j += 20)
	    { //(for loop for columns)
		redVal = 255;   //sets the RGB values
		greenVal = 200;
		blueVal = 0;

		for (int sizeLength = 10 ; sizeLength > 1 ; sizeLength--)
		{ //for loop that will draw rectangles on top of each other with decreasing height
		    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
		    c.fillRect (j, i, 10, sizeLength);    //left side
		    c.fillRect (j + 300, i, 10, sizeLength); //right side

		    greenVal += 2;  //updates blue and green colour values
		    blueVal += 4;
		}
	    }
	}


	redVal = 255;   //sets the RGB values
	greenVal = 200;
	blueVal = 0;
	for (int i = 273 ; i <= 325 ; i += 45)
	{ //mini block windows
	    redVal = 255;   //sets the RGB values
	    greenVal = 200;
	    blueVal = 0;

	    for (int sizeLength = 10 ; sizeLength > 1 ; sizeLength--)
	    { //for loop that will draw rectangles on top of each other with decreasing height
		c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour based on the RGB values assigned before the loop and updated inside the loop
		c.fillRect (i, 150, 10, sizeLength);    //left side
		c.fillRect (i + 205, 150, 10, sizeLength);  //right side

		greenVal += 2;  //updates blue and green colour values
		blueVal += 4;
	    }
	}
    } //end of drawCastle method


    private void drawGrass ()
    { //drawGrass method
	int redVal = 32;    //sets the red colour value
	int greenVal = 86;  //sets the green colour value
	int blueVal = 46;   //sets the blue colour value

	for (int height = 250 ; height > 10 ; height -= 5)
	{ //for loop to draw rectangles of green on top of each other, with lessening height, from darker to lighter
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour using the RGB values assigned before the loop
	    c.fillRect (0, 250, 800, height);   //draws a filled rectangle with the same coordinates and width, but decreasing height every iteration
	    redVal++;   //increases red value by one
	    greenVal += 3;  //increases green value by 3
	    blueVal++;  //increases blue value by one
	}
    } //end of drawGrass method


    private void drawLake ()
    { //drawLake method
	int redVal = 25;    //sets the red colour value
	int greenVal = 120; //sets the green colour value
	int blueVal = 150;  //sets the blue colour value

	for (int height = 50 ; height > 5 ; height -= 5)
	{ //for loop to draw rectangles of blue on top of each other, with lessening height, from darker to lighter
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour using the RGB values assigned before the loop
	    c.fillRect (0, 200, 800, height);   //draws a filled rectangle with the same coordinates and width, but decreasing height every iteration
	    redVal += 3; //increases red value by 3
	    greenVal += 3;  //increases green value by 3
	    blueVal += 13;  //increases blue value by 13
	}
    } //end of drawLake method


    private void drawSky ()
    { //drawSky method
	int redVal = 0; //sets the red colour value
	int greenVal = 105; //sets the green colour value
	int blueVal = 170;  //sets the blue colour value

	for (int height = 500 ; height > 10 ; height -= 10)
	{ //for loop to draw rectangles of blue on top of each other, with lessening height, from lighter to darker
	    c.setColor (new Color (redVal, greenVal, blueVal)); //sets the colour using the RGB values initialized before the loop
	    c.fillRect (0, 0, 800, height); //draws a filled rectangle with the same coordinates and width, but decreasing height every iteration
	    greenVal -= 2;  //decreases the green value by 2
	    blueVal -= 3;   //decreases the blue value by 3
	}
    } //end of drawSky method


    private void drawMoon ()
    { //drawMoon method
	int redVal = 150;   //red colour value
	int greenVal = 180; //green colour value
	int xCoordinate = 650;  //x position (coordinates must change because of how the circle will slowly shrink as it draws on top of itself)
	int yCoordinate = 20;   //y position
	for (int sizeLength = 100 ; sizeLength >= 20 ; sizeLength -= 5)
	{ //for loop to begin with drawing a big circle and keep drawing smaller ones on top
	    c.setColor (new Color (redVal, greenVal, 255)); //sets the colour with the red value, the green value, and the blue value as 255 (complete)
	    c.fillOval (xCoordinate, yCoordinate, sizeLength, sizeLength);    //draws a filled circle with the changing coordinates and size
	    redVal += 5; //increase red and green value so that they get closer to 255 (thus, the circle is gradient, going from more coloured around the edges and whiter in the middle)
	    greenVal += 4;
	    xCoordinate++;  //updates the coordinates
	    yCoordinate++;
	}
    } //end of drawMoon method


    private void drawWizardLeft (int x, int y, Color colour)
    { //drawWizardLeft method
	int xPos = x;
	int yPos = y; //declares and initializes variables for the broomstick handle's leftmost edge coordinates and the wizard's house color. These will be used throughout the rest of the drawing of the wizard so that it can be changed and animated later.
	Color houseColor = colour;

	int[] broomXPos = new int[]
	{
	    xPos - 10, xPos + 2, xPos - 10
	}


	;                                                               //arrays of coordinates for the brush part of the broomstick
	int[] broomYPos = new int[]
	{
	    yPos - 10, yPos, yPos + 10
	}


	;
	c.setColor (woodBrown); //sets the colour to a wooden brown
	c.fillRect (xPos, yPos, 30, 3); //draws a filled rectangle as the broomstick handle
	c.fillPolygon (broomXPos, broomYPos, 3);    //draws a filled triangle as the broomstick brush
	c.setColor (black); //sets the colour to black
	c.fillOval (xPos + 20, yPos - 20, 8, 8); //draws the head of the wizard

	int[] bodyXPos = new int[]
	{
	    xPos + 22, xPos + 24, xPos + 4, xPos + 2
	}


	;                                                                       //arrays of coordinates for the torso part of the wizard
	int[] bodyYPos = new int[]
	{
	    yPos - 18, yPos - 16, yPos + 10, yPos + 8
	}


	;
	c.fillPolygon (bodyXPos, bodyYPos, 4);  //draws the torso of the wizard

	c.drawLine (xPos + 17, yPos - 10, xPos + 25, yPos); //draws the arm of the wizard
	c.drawLine (xPos + 15, yPos - 3, xPos + 13, yPos + 10); //draws the leg of the wizard

	c.setColor (houseColor); //sets the colour to the wizard's parameter passed house colour
	int[] wizardRobesXPos = new int[]
	{
	    xPos + 20, xPos - 5, xPos, xPos + 18
	}


	;                                                                          //arrays of coordinates for the wizard's triangular robe
	int[] wizardRobesYPos = new int[]
	{
	    yPos - 15, yPos - 20, yPos - 5, yPos - 12
	}


	;
	c.fillPolygon (wizardRobesXPos, wizardRobesYPos, 4); //draws the wizard's robe
    } //end of drawWizardLeft method


    private void drawWizardRight (int x, int y, Color colour)
    { //drawWizardRight method (reciprocal of WizardLeft)
	int xPos = x;
	int yPos = y; //declares and initializes variables for the broomstick handle's leftmost edge coordinates and the wizard's house color. These will be used throughout the rest of the drawing of the wizard so that it can be changed and animated later.
	Color houseColor = colour;

	int[] broomXPos = new int[]
	{
	    xPos + 10, xPos - 2, xPos + 10
	}


	;                                                               //arrays of coordinates for the brush part of the broomstick
	int[] broomYPos = new int[]
	{
	    yPos - 10, yPos, yPos + 10
	}


	;
	c.setColor (woodBrown); //sets the colour to a wooden brown
	c.fillRect (xPos - 30, yPos, 30, 3); //draws a filled rectangle as the broomstick handle
	c.fillPolygon (broomXPos, broomYPos, 3);    //draws a filled triangle as the broomstick brush

	c.setColor (black); //sets the colour to black
	c.fillOval (xPos - 25, yPos - 20, 8, 8); //draws the head of the wizard

	int[] bodyXPos = new int[]
	{
	    xPos - 22, xPos - 24, xPos - 4, xPos - 2
	}


	;                                                                       //arrays of coordinates for the torso part of the wizard
	int[] bodyYPos = new int[]
	{
	    yPos - 18, yPos - 16, yPos + 10, yPos + 8
	}


	;
	c.fillPolygon (bodyXPos, bodyYPos, 4);  //draws the torso of the wizard

	c.drawLine (xPos - 17, yPos - 10, xPos - 25, yPos); //draws the arm of the wizard
	c.drawLine (xPos - 15, yPos - 3, xPos - 13, yPos + 10); //draws the leg of the wizard

	c.setColor (houseColor); //sets the colour to the wizard's parameter passed house colour
	int[] wizardRobesXPos = new int[]
	{
	    xPos - 20, xPos + 5, xPos, xPos - 18
	}


	;                                                                          //arrays of coordinates for the wizard's triangular robe
	int[] wizardRobesYPos = new int[]
	{
	    yPos - 15, yPos - 20, yPos - 5, yPos - 12
	}


	;
	c.fillPolygon (wizardRobesXPos, wizardRobesYPos, 4); //draws the wizard's robe
    } //end of drawWizardRight method


    private void drawStandingWizard (int x, int y, Color colour)
    { //drawStandingWizard method
	int xPos = x;
	int yPos = y; //declares and initializes variables for the broomstick handle's leftmost edge coordinates and the wizard's house color. These will be used throughout the rest of the drawing of the wizard.
	Color houseColor = colour;

	int[] broomXPos = new int[]
	{
	    xPos - 10, xPos, xPos + 10
	}


	;                                                               //arrays of coordinates for the brush part of the broomstick
	int[] broomYPos = new int[]
	{
	    yPos - 10, yPos, yPos - 10
	}


	;
	c.setColor (woodBrown); //sets the colour to a wooden brown
	c.fillRect (xPos - 1, yPos, 3, 30); //draws a filled rectangle as the broomstick handle
	c.fillPolygon (broomXPos, broomYPos, 3);    //draws a filled triangle as the broomstick brush

	c.setColor (black); //sets the colour to black
	c.fillOval (xPos - 23, yPos - 5, 8, 8); //draws the head of the wizard

	c.setColor (houseColor);    //sets the colour to the wizard's parameter passed house colour
	int wizardRobeXPos[] = new int[]
	{
	    xPos - 10, xPos - 19, xPos - 28
	}


	;                                                                   //arrays of coordinates for the wizard's triangular robe
	int wizardRobeYPos[] = new int[]
	{
	    yPos + 30, yPos, yPos + 30
	}


	;
	c.fillPolygon (wizardRobeXPos, wizardRobeYPos, 3);  //draws the wizard's robe

	c.setColor (black); //sets the colour to black
	c.fillRect (xPos - 20, yPos, 3, 20); //draws the torso of the wizard

	c.drawLine (xPos - 20, yPos + 10, xPos - 10, yPos); //draws the right (user perspective) arm of the wizard
	c.drawLine (xPos - 20, yPos + 10, xPos - 30, yPos); //draws the left (user perspective) arm of the wizard
	c.drawLine (xPos - 19, yPos + 15, xPos - 14, yPos + 30); //draws the right (user perspective) leg of the wizard
	c.drawLine (xPos - 19, yPos + 15, xPos - 24, yPos + 30); //draws the left (user perspective) leg of the wizard
    } //end of drawStandingWizard method


    private void drawErrorBox (String msg)
    { //drawErrorBox method
	String message = msg;   //stores passed parameter as a message

	int redVal = 0; //sets RGB colour value variables so a gradient coloured box can be filled
	int greenVal = 0;
	int blueVal = 0;
	for (int boxHeight = 120 ; boxHeight >= 5 ; boxHeight -= 2)
	{ //for loop that will draw multiple boxes in decreasing height and lightening colour on top of each other to form a gradient
	    c.setColor (new Color (redVal, greenVal, blueVal));    //sets the colour according to RGB values defined outside of loop
	    c.fillRect (30, 250, 760, boxHeight);   //draws a filled rectangle
	    redVal += 2;    //updates RGB values
	    greenVal += 2;
	    blueVal += 2;
	}


	c.setFont (titleFont);  //reset the drawing font to a title font
	c.setColor (brightRed); //set the coloring color to bright red
	c.drawString (message, 35, 300);   //draws an error message

	pauseProgram ("Press any key to return", brightRed, titleFont, 270, 350); //executes pauseProgram to prompt user to return
    } //end of drawErrorBox method


    private void drawSpecialCircle (int x, int y)
    { //drawSpecialCircle method
	int circleRedVal = 225; //sets the RGB values
	int circleGreenVal = 188;
	int circleBlueVal = 0;
	int circleXPos = x;   //sets the x and y coordinate values
	int circleYPos = y;
	for (int radiusLength = 20 ; radiusLength >= 2 ; radiusLength -= 2)
	{ //will draw circles layer upon layer with decreasing size and lightening colour
	    c.setColor (new Color (circleRedVal, circleGreenVal, circleBlueVal));   //sets the colour based on updated RGB values
	    c.fillOval (circleXPos, circleYPos, radiusLength, radiusLength);    //draws the circle

	    circleXPos++;   //updates the coordinates so that the circle can remain appearing like a circle
	    circleYPos++;
	    circleRedVal++;     //updates the RGB values so as the circles are drawn layer upon layer, they appear in a gradient fashion
	    circleGreenVal += 5;
	    circleBlueVal += 10;
	}
    } //end of drawSpecialCircle method
} //end of HarryPotter class


