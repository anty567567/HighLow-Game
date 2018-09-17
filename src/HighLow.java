/*
Anthony De Luca
Real Life The Game
May 9, 2017
May 9, 2017
 */


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

public class HighLow extends JPanel {

    //INITIALIZING ALL NECESSARY VARIABLES AND IMAGES
    private static boolean intro = true, goToMenu = false, instructions = false, game = false, menu = false, selectSex = true, screenClear = false;
    private static boolean textFieldClear = false, selectPreference = false, haleyChoice = false, sebastianChoice, nextDialogue = false;
    private static boolean win = false, higher = false, textClear, lower = false, wrong = false, lose = false, endGame, lastTry, god = false;
    static private BufferedImage haley, haleyAngry, haleyAnnoyed, haleyBlush, haleyHappy, sebastian, sebastianAnnoyed, sebastianBlush, sebastianSmile, sebastianConcerned;
    private static int haleyY = 800, sebastianY = 800, iEnd = 1, iStart = 0;
    private static int level = 1, strike = 0, guess, strikeCount, fails = 0;
    private static String input = "", message = "";
    private static JTextField start = new JTextField("Y");
    private static HighLow g = new HighLow();


    //CREATES A MILLISECOND DELAY TO ALLOW FOR PAINT COMPONENT TO FUNCTION PROPERLY
    private static void delay() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException io) {

        }
    }

    //SHORT DELAY USED FOR THE DISPLAYING OF TEXT IN THE SENSE OF SPEECH
    private static void shortDelay() {
        try {
            Thread.sleep(50);
        } catch (InterruptedException io) {

        }
    }

    //LONGER DELAY USED FOR THE BREAKING UP OF SPEECH
    private static void longDelay() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException io) {

        }
    }

    //INITIALIZES ALL IMAGES FROM FILES
    private HighLow() {
        try {
            haley = ImageIO.read(getClass().getResourceAsStream("sprites/Haley.png"));
            haleyAngry = ImageIO.read(getClass().getResourceAsStream("sprites/Haley_Angry.png"));
            haleyAnnoyed = ImageIO.read(getClass().getResourceAsStream("sprites/Haley_Annoyed.png"));
            haleyHappy = ImageIO.read(getClass().getResourceAsStream("sprites/Haley_Happy.png"));
            haleyBlush = ImageIO.read(getClass().getResourceAsStream("sprites/Haley_Blush.png"));
            sebastian = ImageIO.read(getClass().getResourceAsStream("sprites/Sebastian.png"));
            sebastianAnnoyed = ImageIO.read(getClass().getResourceAsStream("sprites/Sebastian_Annoyed.png"));
            sebastianSmile = ImageIO.read(getClass().getResourceAsStream("sprites/Sebastian_Smile.png"));
            sebastianBlush = ImageIO.read(getClass().getResourceAsStream("sprites/Sebastian_Blush.png"));
            sebastianConcerned = ImageIO.read(getClass().getResourceAsStream("sprites/Sebastian_Concerned.png"));
        } catch (IOException ex) {
        }
    }

    //KEEPS THE INTRO RUNNING WHILE ANIMATIONS OCCUR AND THEN SWITCHES OVER TO THE goToMenu METHOD
    private static void intro() {
        while (intro) {
            delay();
        }
        goToMenu();
    }

    //ADDS THE JTEXTFIELD AS WELL AS ASKS THE USER IF THEY WOULD LIKE TO PLAY AND THEN DIRECTS TO MORE QUESTIONS
    private static void goToMenu() {
        g.add(start);
        while (goToMenu) {

            delay();

            if (input.equalsIgnoreCase("y")) {
                menu = true;
                goToMenu = false;
                textFieldClear = true;

            } else if (input.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        }
        selectSex();
    }

    //ASKS THE USER WHETHER THEY ARE MALE OR FEMALE AND THEN DIRECTS TO PREFERENCE
    private static void selectSex() {
        while (selectSex) {
            delay();

            if (input.equalsIgnoreCase("male")) {
                selectSex = false;
                textFieldClear = true;
                selectPreference = true;

            } else if (input.equalsIgnoreCase("female")) {
                selectSex = false;
                textFieldClear = true;
                selectPreference = true;
            }
        }
        selectPreference();
    }

    //PLAYER SELECTS WHETHER THEY WOULD PREFER HALEY OR SEBASTIAN
    private static void selectPreference() {

        while (selectPreference) {
            delay();

            if (input.equalsIgnoreCase("sebastian")) {
                sebastianChoice = true;
                textFieldClear = true;
                selectPreference = false;

            } else if (input.equalsIgnoreCase("haley")) {
                haleyChoice = true;
                textFieldClear = true;
                selectPreference = false;
            }
        }
        menu();
    }

    //DISPLAYS THE MENU WHICH NAVIGATES TO THE GAME, INSTRUCTIONS, OR CHANGES THE PREFERENCE
    private static void menu() {
        while (menu) {
            delay();

            switch (input) {
                //PLAYS THE GAME
                case "1":
                    screenClear = true;
                    menu = false;
                    textFieldClear = true;
                    chooseLevel();
                    break;

                //OPENS THE MENU
                case "2":
                    menu = false;
                    textFieldClear = true;
                    screenClear = true;
                    instructions = true;
                    instruction();
                    break;

                //CHANGES THE PREFERENCE
                case "3":
                    if (haleyChoice) {
                        textFieldClear = true;
                        haleyChoice = false;
                        sebastianChoice = true;
                    } else {
                        textFieldClear = true;
                        haleyChoice = true;
                        sebastianChoice = false;
                    }
                    break;

                //CHECKS IF MR. G IS PLAYING
                case "g":
                    god = true;
                    textFieldClear = true;
                    break;
            }
            input = "0";
        }
    }

    //DETERMINES WHAT LEVEL THE GAME IS AT AND DECIDES A RANGE AND RANDOM NUMBER BASED ON IT. RUNS THE GAME
    private static void chooseLevel() {
        int num = 0, range = 0;

        if (level == 1) {
            range = 12;
            num = (int) ((range) * Math.random() + 1);
            strikeCount = 3;

        } else if (level == 2) {
            range = 100;
            num = (int) ((range) * Math.random() + 1);
            strikeCount = 5;

        } else if (level == 3) {
            range = 365;
            num = (int) ((range) * Math.random() + 1);
            strikeCount = 7;
        }

        game = true;
        playGame(num, range);

    }

    //PLAYS THE ACTUAL GUESSING GAME.
    private static void playGame(int num, int range) {
        input = "0";


        while (game) {
            delay();

            //GIVES MR. G HACKS
            if (god) {
                System.out.println(num);
            }

            //CHECKS IF THE GUESS IS CORRECT AND THEN MOVES ONTO NEXT LEVEL OR ENDS THE GAME IN VICTORY
            if (guess == num) {
                win = true;
                strike = 0;
                textFieldClear = true;
                textClear = true;
                level++;
                guess = 0;
                haleyY = 435;
                if (level < 4) {
                    chooseLevel();
                } else {
                    game = false;
                    endGame = true;
                    endGame();
                }

                //CHECKS IF THE NUMBER IS VALID AND THEN WRONG
            } else if (1 <= guess && guess <= range) {
                strike++;
                textFieldClear = true;
                textClear = true;
                wrong = true;

                //DETERMINES WHETHER YOU NEED TO GUESS HIGHER OR LOWER
                if (guess > num) {
                    lower = true;
                    higher = false;
                } else if (guess < num) {
                    higher = true;
                    lower = false;
                }
                guess = 0;
            }

            //CHECKS IF YOU LOST THE GAME
            if (strike == strikeCount) {
                fails++;

                //IF FIRST FAILURE GIVES A CHANCE AT REDEMPTION
                if (fails == 1) {
                    game = false;
                    lastTry = true;
                    lastTry(num, range);

                }

                //IF SECOND FAILURE LOSES THE GAME AND RUNS LOSE
                if (fails == 2) {
                    lose = true;
                    game = false;
                    lose();
                }
            }

        }
    }

    //DISPLAYS THE INSTRUCTIONS
    private static void instruction() {
        while (instructions) {
            delay();

            //RETURNS TO MENU
            if (input.equals("1")) {
                menu = true;
                textFieldClear = true;
                instructions = false;
                input = "";
            }
        }
        menu();

    }

    //GIVES THE CHANCE AT REDEMPTION
    private static void lastTry(int num, int range) {
        screenClear = true;

        //GIVES MR. G HACKS
        if (god) {
            if (haleyChoice) {
                System.out.println("blue");
            } else if (sebastianChoice) {
                System.out.println("black");
            }
        }

        while (lastTry) {
            delay();

            //CHECKS IF HALEY AND CHANGES FAVOURITE COLOUR
            if (haleyChoice) {

                //IF THEY GUESS RIGHT, GIVE SECOND CHANCE
                if (input.equalsIgnoreCase("blue")) {
                    strike = 0;
                    game = true;
                    lastTry = false;
                    screenClear = true;
                    textFieldClear = true;
                    nextDialogue = false;
                    playGame(num, range);

                    //WRONG GUESS LOSES THE GAME
                } else if (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("green") || input.equalsIgnoreCase("orange") || input.equalsIgnoreCase("black") || input.equalsIgnoreCase("purple") || input.equalsIgnoreCase("pink") || input.equalsIgnoreCase("white") || input.equalsIgnoreCase("brown")) {
                    screenClear = true;
                    textFieldClear = true;
                    lastTry = false;
                    lose = true;
                    game = false;
                    lose();
                }

                //SAME AS BEFORE BUT FOR SEBASTIAN
            } else {
                if (input.equalsIgnoreCase("black") || input.equalsIgnoreCase("purple")) {
                    strike = 0;
                    game = true;
                    lastTry = false;
                    screenClear = true;
                    textFieldClear = true;
                    nextDialogue = false;
                    playGame(num, range);

                } else if (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("green") || input.equalsIgnoreCase("orange") || input.equalsIgnoreCase("blue") || input.equalsIgnoreCase("pink") || input.equalsIgnoreCase("white") || input.equalsIgnoreCase("brown")) {
                    screenClear = true;
                    textFieldClear = true;
                    lastTry = false;
                    lose = true;
                    game = false;
                    lose();
                }
            }
        }
    }

    //ENDS THE GAME IN DEFEAT
    private static void lose() {
        fails = 0;
        screenClear = true;

        while (lose) {
            delay();

            //CHECKS IF THEY WANT TO PLAY AGAIN AND RESETS GAME
            if (input.equalsIgnoreCase("y")) {
                lose = false;
                menu = true;
                screenClear = true;
                textFieldClear = true;
                strike = 0;
                level = 1;
                nextDialogue = false;
                menu();
            } else if (input.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        }
    }

    //ENDS THE GAME IN VICTORY
    private static void endGame() {
        screenClear = true;
        while (endGame) {
            delay();

            //CHECKS IF THEY WANT TO PLAY AGAIN AND RESETS GAME
            if (input.equalsIgnoreCase("y")) {
                endGame = false;
                menu = true;
                screenClear = true;
                textFieldClear = true;
                strike = 0;
                level = 1;
                nextDialogue = false;
                menu();
            } else if (input.equalsIgnoreCase("n")) {
                System.exit(0);
            }
        }
    }

    //DISPLAYS ALL GRAPHICS/TEXT
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        Font menuFont = new Font(Font.DIALOG, Font.BOLD, 36), selectFont = new Font(Font.DIALOG, Font.PLAIN, 24);
        int haleyX = 782, sebastianX = 72, x, y, messageLength;

        //clears text within JTextField
        if (textFieldClear) {
            start.setText("");
            textFieldClear = false;
        }


        //Clears the screen
        if (screenClear) {
            g.clearRect(0, 0, 1000, 800);
            screenClear = false;
        }

        //Clears the text box
        if (textClear) {
            g.clearRect(175, 400, 650, 150);
            textClear = false;
        }


        //Intro sequence
        if (intro) {
            this.setBackground(Color.WHITE);
            g.clearRect(0, 0, 1000, 800);
            g.drawImage(haley, haleyX, haleyY, this);
            g.drawImage(sebastian, sebastianX, sebastianY, this);
            haleyY--;
            sebastianY--;
            if (haleyY <= 60) {
                intro = false;
                goToMenu = true;
            }
            delay();
        }


        //Asking whether they want to play or not
        else if (goToMenu) {
            g.setFont(menuFont);
            g.setColor(Color.PINK);
            g.drawString("REAL LIFE: THE GAME", 300, 100);
            g.setColor(Color.BLACK);
            g.setFont(selectFont);
            g.drawString("Would you like to play this game? (Y/N)", 285, 300);


        } else if (menu) {


            //User selects whether they are male or female, and who they want to date
            if (selectSex) {
                g.clearRect(0, 0, 1000, 800);
                g.setFont(menuFont);
                g.setColor(Color.PINK);
                g.drawString("REAL LIFE: THE GAME", 300, 100);
                g.setColor(Color.BLACK);
                g.setFont(selectFont);
                g.drawString("Please input whether you are 'male' or 'female'", 250, 300);
                g.drawImage(haley, 782, 61, this);
                g.drawImage(sebastian, 72, 61, this);
                start.setBounds(450, 390, 100, 20);


                //DISPLAYS QUESTION OF PREFERENCE
            } else if (selectPreference) {
                g.clearRect(0, 0, 1000, 800);
                g.setFont(menuFont);
                g.setColor(Color.PINK);
                g.drawString("REAL LIFE: THE GAME", 300, 100);
                g.setColor(Color.BLACK);
                g.setFont(selectFont);
                g.drawString("Would you like to try your chances with 'Sebastian' or 'Haley'?", 170, 300);
                g.drawImage(haley, 782, 61, this);
                g.drawImage(sebastian, 72, 61, this);


                //DISPLAYS THE MENU AND ALL OPTIONS
            } else {
                this.setBackground(Color.WHITE);
                g.clearRect(0, 0, 1000, 800);
                g.setFont(menuFont);
                g.setColor(Color.PINK);
                g.drawString("REAL LIFE: THE GAME", 300, 100);
                g.setColor(Color.BLACK);
                start.setBounds(450, 600, 100, 20);
                g.drawString("(1) PLAY GAME", 320, 200);
                g.drawString("(2) INSTRUCTIONS", 320, 300);
                g.drawString("(3) CHANGE INTEREST", 320, 400);
                g.setFont(selectFont);
                g.drawString("Mr. G, enter g for god mode.", 10, 600);
                g.drawString("Will output num in console.", 10, 640);


                //CHECKS FOR PREFERENCE AND CHANGES SPRITES BASED ON IT
                if (haleyChoice) {
                    g.drawImage(haleyBlush, 782, 61, this);
                    g.drawImage(sebastianConcerned, 72, 61, this);
                    g.drawString("(Current Choice: Haley)", 375, 500);


                    //SAME THING FOR SEBASTIAN
                } else if (sebastianChoice) {
                    g.drawImage(haleyAnnoyed, 782, 61, this);
                    g.drawImage(sebastianSmile, 72, 61, this);
                    g.drawString("(Current Choice: Sebastian)", 375, 500);
                }
            }


            //DISPLAYS THE INSTRUCTIONS
        } else if (instructions) {
            g.setFont(menuFont);
            g.drawString("INSTRUCTIONS", 360, 100);
            g.setFont(selectFont);
            g.drawString("You will be asked to guess numbers within a certain range", 175, 200);
            g.drawString("Depending on your guess you will be told to guess higher or lower", 135, 250);
            g.drawString("If you fail, you will be given one chance to redeem yourself", 175, 300);
            g.drawString("Enter (1) to go back", 375, 350);

            //DISPLAYS ALL ASPECTS OF THE GUESSING GAME
        } else if (game) {

            //CHECKS WHETHER HALEY OR SEBASTIAN IS SELECTED AND DISPLAYS CORRESPONDING IMAGES DEPENDING ON WRONG GUESSES
            if (haleyChoice) {
                if (strike == 0 && level == 1) {
                    g.drawImage(haley, 436, 236, this);
                } else if (strike > 0 && strike <= 3) {
                    g.drawImage(haleyAnnoyed, 436, 236, this);
                } else if (strike >= 4) {
                    g.drawImage(haleyAngry, 436, 236, this);
                } else {
                    g.drawImage(haleyHappy, 436, 236, this);
                }
            } else if (sebastianChoice) {
                if (strike == 0 && level == 1) {
                    g.drawImage(sebastian, 436, 236, this);
                } else if (strike > 0 && strike <= 3) {
                    g.drawImage(sebastianConcerned, 436, 236, this);
                } else if (strike >= 4) {
                    g.drawImage(sebastianAnnoyed, 436, 236, this);
                } else {
                    g.drawImage(sebastianSmile, 436, 236, this);
                }
            }

            //DRAWS THE TEXT BOX
            g.drawRect(175, 400, 650, 150);
            g.setFont(selectFont);

            //IF THE GUESS IS CORRECT, MOVES ONTO NEXT DIALOGUE
            if (win) {
                nextDialogue = false;
                win = false;
                iEnd = 1;
                screenClear = true;
            }


            //CHECKS LEVEL AND DISPLAYS CORRESPONDING MESSAGE
            if (level == 1 && !nextDialogue) {
                message = "Oh, hey there! What? You wanna know how old I am?!?Why don't you guess what month I was born in first. (1-12)";
                iStart = 52;
            } else if (level == 2 && !nextDialogue) {
                message = "Yay!!! You got it, you know you're not too shabby.Now for my actual age. (1-100)";
                iStart = 51;
            } else if (level == 3 && !nextDialogue) {
                message = "Haha, nice guess, but I am actually 19.How about you guess my favourite day of the year (1-365)";
                iStart = 40;
            }


            //DISPLAYS TEXT AS SPEECH
            if (iEnd <= message.length() || !nextDialogue) {
                if (iEnd >= iStart && !nextDialogue) {
                    haleyY = 465;
                    messageLength = message.length();
                    message = message.substring(iStart - 1, messageLength);
                    iEnd = 1;
                    longDelay();
                    nextDialogue = true;


                    //MOVES LINE OF TEXT DOWN A BIT
                } else if (haleyY != 465) {
                    haleyY = 435;
                }

                g.drawString(message.substring(0, iEnd), 185, haleyY);
                shortDelay();
                iEnd++;
            }

            //DISPLAYS THE STRIKE BOXES
            if (nextDialogue) {
                g.drawString("Strikes", 175, 305);
                y = 310;
                for (x = 205; x < 205 + 40 * strikeCount; x += 40) {
                    g.drawRect(x, y, 30, 30);
                    if (strikeCount == 7 && x >= 365) {
                        y = 350;
                        x = 165;
                    }
                    if (strikeCount == 7 && x >= 245 && y == 350) {
                        break;
                    }
                }
            }

            //THESE SAME CHUNKS OF CODE JUST MARKS THE STRIKE BOXES AND DISPLAYS LINE OF TEXT DEPENDING ON LEVEL AND STRIKE
            if (strike >= 1) {
                g.drawLine(205, 310, 235, 340);
                g.drawLine(205, 340, 235, 310);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }
                message = "";
                if (higher && strike == 1) {
                    message = "It's okay guess higher.";
                }
                if (lower && strike == 1) {
                    message = "It's okay guess lower.";
                }
                if (iEnd < message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }

            }
            if (strike >= 2) {
                g.drawLine(245, 310, 275, 340);
                g.drawLine(245, 340, 275, 310);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }

                message = "";
                if (level == 1 && strike == 2) {
                    if (higher) {
                        message = "Close, but guess higher.";
                    } else {
                        message = "Close, but guess lower.";
                    }
                }
                if (level == 2 && strike == 2) {
                    if (higher) {
                        message = "Wow you're bad. Guess higher.";
                    } else {
                        message = "Wow you're bad. Guess lower.";
                    }
                } else if (level == 3 && strike == 2) {
                    if (higher) {
                        message = "It isn't that hard. Try higher.";
                    } else {
                        message = "It isn't that hard. Try lower.";
                    }
                }
                if (iEnd < message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }
            }
            if (strike >= 3) {
                g.drawLine(285, 310, 315, 340);
                g.drawLine(285, 340, 315, 310);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }

                if (level == 2 && strike == 3) {
                    if (higher) {
                        message = "I wish I had a better taste in people. Higher!";
                    } else {
                        message = "I wish I had a better taste in people. Lower!";
                    }
                } else if (level == 3 && strike == 3) {
                    if (higher) {
                        message = "Come on, you've made it this far, just guess higher.";
                    } else {
                        message = "Come on, you've made it this far, just guess lower.";
                    }
                }
                if (iEnd < message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }

            }
            if (strike >= 4) {
                g.drawLine(325, 310, 355, 340);
                g.drawLine(325, 340, 355, 310);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }

                message = "";
                if (level == 2 && strike == 4) {
                    if (higher) {
                        message = "I'm starting to get bored. Guess higher.";
                    } else {
                        message = "I'm starting to get bored. Guess lower.";
                    }
                } else if (level == 3 && strike == 4) {
                    if (higher) {
                        message = "It can't be that hard. Higher.";
                    } else {
                        message = "It can't be that hard. Lower.";
                    }
                }
                if (iEnd <= message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }

            }
            if (strike >= 5) {
                g.drawLine(365, 310, 395, 340);
                g.drawLine(365, 340, 395, 310);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }

                message = "";
                if (level == 2 && strike == 5) {
                    if (higher) {
                        message = "I'm starting to get bored. Guess higher.";
                    } else {
                        message = "I'm starting to get bored. Guess lower.";
                    }
                } else if (level == 3 && strike == 5) {
                    if (higher) {
                        message = "Don't fail me now, guess higher.";
                    } else {
                        message = "Don't fail me now, guess lower.";
                    }
                }
                if (iEnd <= message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }

            }
            if (strike >= 6) {
                g.drawLine(205, 350, 235, 380);
                g.drawLine(205, 380, 235, 350);
                if (wrong) {
                    wrong = false;
                    textClear = true;
                    iEnd = 1;
                }

                if (higher) {
                    message = "You're on your last chance, Higher!";
                } else {
                    message = "You're on your last chance, Lower!";
                }

                if (iEnd <= message.length()) {
                    haleyY = 435;
                    g.drawString(message.substring(0, iEnd), 185, haleyY);
                    shortDelay();
                    iEnd++;
                }

            }
            if (strike >= 7) {
                g.drawLine(245, 350, 275, 380);
                g.drawLine(245, 380, 275, 350);
            }

            //DISPLAYS THE TERMS OF THE CHANCE OF REDEMPTION AND DRAWS CORRESPONDING CHARACTER
        } else if (lastTry) {
            g.setFont(selectFont);
            g.drawRect(175, 400, 650, 150);
            if (haleyChoice) {
                g.drawImage(haley, 436, 136, this);
            } else if (sebastianChoice) {
                g.drawImage(sebastian, 436, 136, this);
            }
            g.drawString("Hmph, you suck, guess my favourite colour to keep trying.", 180, 435);
            g.drawString("(Red/Green/Blue/Black/Purple/Orange/Pink/White/Brown)", 180, 475);


            //DISPLAYS THE LOSE SCREEN AND ASKS THE USER TO PLAY AGAIN
        } else if (lose) {
            g.setFont(selectFont);
            if (haleyChoice) {
                g.drawImage(haleyAngry, 436, 136, this);
                g.drawString("You have disappointed Haley, but she's willing to give you another shot.", 100, 500);
            } else if (sebastianChoice) {
                g.drawImage(sebastianAnnoyed, 436, 136, this);
                g.drawString("You have disappointed Sebastian, but he's willing to give you another shot.", 100, 500);
            }
            g.drawString("Play again? (Y/N)", 385, 540);


            //DISPLAYS THE WIN SCREEN AND ASKS THE USER TO PLAY AGAIN
        } else if (endGame) {
            g.setFont(selectFont);
            if (haleyChoice) {
                g.drawImage(haleyBlush, 436, 136, this);
                g.drawString("Haley thanks you for an amazing time, she'd like to be your friend and suck some dick on the side. this may lead to us having sex, but probs not", 135, 500);
            }
            if (sebastianChoice) {
                g.drawImage(sebastianBlush, 436, 136, this);
                g.drawString("Sebastian thanks you for an amazing time, he'd like to be your friend.", 100, 500);
            }
            g.drawString("Play again? (Y/N)", 385, 540);
        }
        repaint();
    }

    //MAIN METHOD STARTS THE GAME
    public static void main(String[] args) throws IOException {

        //INITIALIZES THE WINDOW
        JFrame program = new JFrame("Real Life The Game");

        //CREATES THE JTEXTFIELD
        start.setBounds(425, 390, 200, 20);
        start.setSize(175, 20);
        start.addActionListener(new ActionListener() {

            //MAKES AN ACTION LISTENER FOR THE TEXT FIELD AND TAKES INPUT AS WELL AS CHANGES IT INTO AN INT IF THE GAME IS RUNNING
            public void actionPerformed(ActionEvent e) {
                input = start.getText();
                if (game) {
                    guess = Integer.parseInt(input);
                }
            }
        });

        //SETS THE WINDOW SIZE AND BASIC NECESSITIES
        program.setSize(1000, 800);
        program.setVisible(true);
        program.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        program.add(g);

        //STARTS GAME
        intro();
        g.add(start);


    }
}