/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fighting.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rewil
 */
public class GUI extends Identifiable {
    
    private State currentState = State.FirstGreeting;
    private Character character;
    
    private final int regenChance = 3;
    
    private final int width = 100; 
    private final int capSize = 2;
    private final char horBar = '~';
    private final char verBar = '|';
    private final char space = ' ';
    private final char openPart = '<';
    private final char closePart = '>';
    
    private final char escapeChar = (char)27;
    private final int escapeSize = 5;
    private final Random rand = new Random();
    private final Scanner ui = new Scanner(System.in);
    
    public GUI() {
        super();
        Distributor.addGUI(this);
    }
    
    /**
     * Changes the state of the GUI, controls how it functions
     * @param state 
     */
    public void changeState(State state) {
        currentState = state;
    }
    
    /**
     * Gets the state of the GUI
     * @return 
     */
    public State getState() {
        return currentState;
    }
    
    
    public enum State {
        FirstGreeting,
        Greeting,
        Passive,
        PassiveBack,
        Shop,
        ShopBack,
        Inn,
        InnBack,
        Battle,
        Attacking,
        Defending,
        Fleeing,
        BattleWon,
        Fled,
        
        LevelUp,
        Dead,
        GameOver;
    }
    
    @Override
    public String toString() {
        String output = "";
        
        switch(currentState) {
            case FirstGreeting: output += FirstGreetingGUI();
                break;
            case Greeting: output += GreetingGUI();
                break;
            case Passive: output += PassiveGUI();
                break;
            case PassiveBack: output += PassiveBackGUI();
                break;
            case Shop: output += ShopGUI();
                break;
            case ShopBack: output += ShopBackGUI();
                break;
            case Inn: output += InnGUI();
                break;
            case InnBack: output += InnBackGUI();
                break;
            case Battle: output += BattleGUI();
                break;
            case Attacking: output += AttackingGUI();
                break;
            case Defending: output += DefendingGUI();
                break;
            case Fleeing: output += FleeingGUI();
                break;
            case BattleWon: output += BattleWonGUI();
                break;
            case Fled: output += FledGUI();
                break;
                
                
            case LevelUp: output += LevelUpGUI();
                break;
            case Dead: output += DeadGUI();
                break;
            case GameOver: output += GameOverGUI();
                break;
        }
        
        return output;
    }
    
    
    
    // User Interface Elements
    
    private final String unknown = escapeChar + "[31m" + "Answer Unknown" + escapeChar + "[30m";
    private final String descriptionBreak = ":";
    private final String helpMark = "?";
    
    /**
     * Fetches and returns a Name, using the current line and ending with a new one
     * @return 
     */
    private String getName() {
        boolean done = false;
        String temp = "";
        while(!done) {
            String prompt = "Name: ";
            System.out.print(prompt);
            temp = ui.nextLine();
            if(temp.startsWith(helpMark)) {
                String[] message = {"Name" + descriptionBreak + "Enter a name to be used, this will be used throughout the game"};
                helpMenu(message);
            }
            else{
                done = true;
            }
        }
        return temp;
    }
    
    /**
     * Fetches and returns a State, using the options given in PassiveGUI, and only if the option is available; MUST BE UPDATED IF OPTIONS ARE CHANGED
     * @param options Truth values for availability of each option in order: Battle, Shop, Inn
     * @throws Unidentified 
     */
    private void passiveOptions(boolean[] options) {
        String unavailable = escapeChar + "[31m" + "Option Unavailable" + escapeChar + "[30m";
        boolean done = false;
        while(!done) {
            System.out.print("Select Option: ");
            switch(ui.nextLine().toLowerCase()) {
                case "a": {
                        if(options[0]) {
                            changeState(State.Battle);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case "b": {
                        if(options[1]) {
                            changeState(State.Shop);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case "c": {
                        if(options[2]) {
                            changeState(State.Inn);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case helpMark: {
                    String battle = "Battle" + descriptionBreak + "Fight a Monster to get Gold and Exp!";
                    String shop = "Shop" + descriptionBreak + "Buy gear to boost your stats!";
                    String inn = "Inn" + descriptionBreak + "Sleep at the Inn to restore health!";
                    String[] message = {battle, shop, inn};
                    helpMenu(message);
                }
                    break;
                default: System.out.println(unknown);
            }
        }
    }
    
    /**
     * Fetches and returns a State, using the options given in PassiveGUI, and only if the option is available; MUST BE UPDATED IF OPTIONS ARE CHANGED
     * @param options Truth values for availability of each option in order: Battle, Shop, Inn
     * @throws Unidentified 
     */
    private void passiveBackOptions(boolean[] options) {
        String unavailable = escapeChar + "[31m" + "Option Unavailable" + escapeChar + "[30m";
        boolean done = false;
        while(!done) {
            System.out.print("Select Option: ");
            switch(ui.nextLine().toLowerCase()) {
                case "a": {
                        if(options[0]) {
                            changeState(State.Battle);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case "b": {
                        if(options[1]) {
                            changeState(State.ShopBack);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case "c": {
                        if(options[2]) {
                            changeState(State.InnBack);
                            done = true;
                        }
                        else {
                            System.out.println(unavailable);
                        }
                    }
                    break;
                case helpMark: {
                    String battle = "Battle" + descriptionBreak + "Fight a Monster to get Gold and Exp!";
                    String shop = "Shop" + descriptionBreak + "Buy gear to boost your stats!";
                    String inn = "Inn" + descriptionBreak + "Sleep at the Inn to restore health!";
                    String[] message = {battle, shop, inn};
                    helpMenu(message);
                }
                    break;
                default: System.out.println(unknown);
            }
        }
    }
    
    /**
     * Awaiting Completion of ShopGUI
     */
    private void shopOptions() {
        
    }
    
    /**
     * Fetches and acts upon decision to stay in Inn
     * @param cost Cost of Staying at Inn
     * @return
     * @throws Unidentified 
     */
    private String innOptions(int cost) {
        String output = "";
        
        output += divider();
        
        boolean done = false;
        while(!done) {
            System.out.print("Select Option: ");
            switch(ui.nextLine().toLowerCase()) {
                case "a": {
                    if(character.getGold() >= cost) {
                        character.changeGold(character.getGold() - cost);
                        String message = "You spent " + escapeChar + "[33m" + cost + escapeChar + "[30m" + " gold to spend the night.";
                        String m2 = "You are now fully rested";
                        output += bodyLine(message);
                        character.changeHealth(character.getMaxHealth());
                        output += bodyLine(m2);
                        done = true;
                    }
                    else {
                        String message = escapeChar + "[31m" + "Insufficient Funds" + escapeChar + "[30m";
                        System.out.println(message);
                    }
                }
                    break;
                case "b": {
                    String message = "You leave the inn";
                    output += bodyLine(message);
                        done = true;
                }
                    break;
                case helpMark: {
                    String stay = "Stay the Night" + descriptionBreak + "Spend " + escapeChar + "[33m" + cost + escapeChar + "[30m" + " gold to fully heal.";
                    String leave = "Leave" + descriptionBreak + "Leave the Inn";
                    String[] message = {stay, leave};
                    helpMenu(message);
                }
                break;
                default: System.out.println(unknown);
            }
        }
        
        output += divider();
        changeState(State.PassiveBack);
        
        return output;
    }
    
    /**
     * Fetches and returns a State, using the options given in BattleGUI; MUST BE UPDATED IF OPTIONS ARE CHANGED
     * @return
     * @throws Unidentified 
     */
    private State selectBattleOption() {
        
        while(true) {
            System.out.print("Select Option: ");
            switch(ui.nextLine().toLowerCase()) {
                case "a": return State.Attacking;
                case "b": return State.Defending;
                case "c": return State.Fleeing;
                case helpMark: {
                    String attack = "Attack" + descriptionBreak + "Attack the monster and be attacked in turn. Each battler in turn attacks and defends.";
                    String attack2 = descriptionBreak + "You have a chance to do critical damage, which will double your damage.";
                    String defend = "Defend" + descriptionBreak + "Defending doubles your defense, Regen Chance, and Regen Amount,";
                    String defend2 = descriptionBreak + "making it easier to significantly regain health.";
                    String flee = "Flee" + descriptionBreak + "Run Away! Your speed is compared to your opponent's speed to determine";
                    String flee2 = descriptionBreak + "your chance of success. You are attacked if you fail.";
                    String[] message = {attack, attack2, defend, defend2, flee, flee2};
                    helpMenu(message);
                }
                    break;
                default: System.out.println(unknown);
            }
        }
        
    }
    
    /**
     * Fetches and upgrades a Character Stat
     * @throws Unidentified 
     */
    private void statLevelUp() {
        boolean done = false;
        while(!done) {
            System.out.print("Select Option: ");
            switch(ui.nextLine().toLowerCase()) {
                case "a": {done = true; character.changeStrength(character.getStrength() + 1);}
                    break;
                case "b": {done = true; character.changeDefense(character.getDefense() + 1);}
                    break;
                case "c": {done = true; character.changeSpeed(character.getSpeed() + 1);}
                    break;
                case "d": {done = true; character.changeMaxHealth(character.getMaxHealth() + 5);}
                    break;
                case helpMark: {
                    String strength = "Strength" + descriptionBreak + "Affects Damage";
                    String defense = "Defense" + descriptionBreak + "Affects Damage Blocked and Regen Amount";
                    String speed = "Speed" + descriptionBreak + "Affects success rate of Fleeing";
                    String health = "Max Health" + descriptionBreak + "Affects how much Health you have";
                    String[] message = {strength, defense, speed, health};
                    helpMenu(message);
                }
                    break;
                default: System.out.println(unknown);
            }
        }
    }
    
    /**
     * This will output a help menu listing each option and its description in a single line; If a description combined with the option then break it into another String starting with descriptionBreak;
     * Do not include spaces around the descriptionBreak when formatting strings
     * @param optionsDescriptions 
     */
    private void helpMenu(String[] optionsDescriptions) {
        String output = "";
        
        output += divider();
        String title = "Help Menu";
        output += bodyLine(title);
        String[] options = new String[optionsDescriptions.length];
        String[] descriptions = new String[optionsDescriptions.length];
        for(int i = 0; i < optionsDescriptions.length; ++i) {
            String[] temp = optionsDescriptions[i].split(descriptionBreak);
            options[i] = temp[0];
            descriptions[i] = temp[1];
        }
        for(int i = 0; i < options.length; ++i) {
            String message = options[i] + ">>  " + descriptions[i];
            output += bodyLine(message);
        }
        output += divider();
        
        System.out.println(output);
    }
    
    
    
    // Construction Methods
    
    /**
     * Runs if currentState is FirstGreeting; this establishes a name for the character
     * @return 
     */
    private String FirstGreetingGUI() {
        String output = "";
        int buffer = 3;
        
        output += divider();
        String[] temp = {"Name: ???", "Please Enter Your Name"};
        output += partitionedLine(temp, buffer);
        output += divider();
        System.out.print(output);
        String name = getName();
        character = new Character(name);
        
        changeState(State.Greeting);
        
        output = " ";
        return output;
    }
    
    /**
     * Runs if currentState is Greeting; this merely says Hello, followed by their name.
     * @return 
     */
    private String GreetingGUI() {
        String output = "";
        
        output += divider();
        String message = "Hello, ";
        message += character.getName();
        output += bodyLine(message);
        output += divider();
        
        changeState(State.Passive);
        
        return output;
    }
    
    private boolean[] visible;
    /**
     * Runs if currentState is Passive; this states Character Name, Gold, Level, and Exp, then gives options for how to continue, availability determined by percent chances
     * @return
     * @throws Unidentified 
     */
    private String PassiveGUI() {
        String output = "";
        
        // Monster, Shop, Inn
        visible = new boolean[3];
        int[] chances = {100, 20, 33};
        for(int i = 0; i < visible.length; ++i) {
            visible[i] = rand.nextDouble() <= ((double)chances[i]/100);
        }
        
        output += divider();
        String[] stats = {character.getName(), "Gold: " + character.getGold(), "Level: " + character.getLevel(), "Exp: " + character.getExp() + "/" + character.getNextExp()};
        output += partitionedLine(stats, 2);
        String message = "Choose an option:";
        output += bodyLine(message);
        String[] options = new String[visible.length];
        if(visible[0]) {
            options[0] = "A: Fight a Monster!";
        }
        else {
            options[0] = "No Monsters Available";
        }
        if(visible[1]) {
            options[1] = "B: Visit the Shop";
        }
        else {
            options[1] = "No Shop Available";
        }
        if(visible[2]) {
            options[2] = "C: Stay at the Inn";
        }
        else {
            options[2] = "No Inn Available";
        }
        output += optionsLine(options);
        System.out.println(output);
        output = "";
        passiveOptions(visible);
        
        
        return output;
    }
    
    /**
     * Runs if currentState is PassiveBack to return to Passive from Shop and Inn; this states Character Name, Gold, Level, and Exp, then gives options for how to continue, availability determined by boolean[] visible
     * @return
     * @throws Unidentified 
     */
    private String PassiveBackGUI() {
        String output = "";
        
        output += divider();
        String[] stats = {character.getName(), "Gold: " + character.getGold(), "Level: " + character.getLevel(), "Exp: " + character.getExp() + "/" + character.getNextExp()};
        output += partitionedLine(stats, 2);
        String message = "Choose an option:";
        output += bodyLine(message);
        String[] options = new String[visible.length];
        if(visible[0]) {
            options[0] = "A: Fight a Monster!";
        }
        else {
            options[0] = "No Monsters Available";
        }
        if(visible[1]) {
            options[1] = "B: Visit the Shop";
        }
        else {
            options[1] = "No Shop Available";
        }
        if(visible[2]) {
            options[2] = "C: Stay at the Inn";
        }
        else {
            options[2] = "No Inn Available";
        }
        output += optionsLine(options);
        System.out.println(output);
        output = "";
        passiveBackOptions(visible);
        
        
        return output;
    }
    
    /**
     * Runs if currentState is Shop; currently is deactivated, stating as much and returning to Passive
     * @return 
     */
    private String ShopGUI() {
        String output = "";
        
        output += divider();
        String message = "Shop is currently deactivated due to the game not being ready for it yet";
        output += bodyLine(message);
        output += divider();
        
        changeState(State.PassiveBack);
        
        return output;
    }
    
    /**
     * Runs if currentState is ShopBack to return to shop from PassiveBack; currently is deactivated, stating as much and returning to PassiveBack
     * @return 
     */
    private String ShopBackGUI() {
        String output = "";
        
        output += divider();
        String message = "Shop is currently deactivated due to the game not being ready for it yet";
        output += bodyLine(message);
        output += divider();
        
        changeState(State.PassiveBack);
        
        return output;
    }
    
    private int innCost;
    /**
     * Runs if currentState is Inn; generates a cost based on level, along with variance based on level, then gives them the option to leave or stay
     * Attempting to stay with insufficient funds will crash the game, because I'm being lazy right now
     * @return
     * @throws Unidentified 
     */
    private String InnGUI() {
        String output = "";
        
        output += divider();
        int cost = 15 * character.getLevel();
        int varianceUB = 5 * character.getLevel();
        int varianceLB = -5 * character.getLevel();
        cost += rand.nextInt(varianceUB + 1) + varianceLB;
        innCost = cost;
        String[] stats = {character.getName(), "Gold: " + character.getGold(), "Level: " + character.getLevel(), "Exp: " + character.getExp() + "/" + character.getNextExp()};
        String message = "Hello! Staying at the inn will cost " + escapeChar + "[33m" + cost + escapeChar + "[30m" + " gold. Would you like to stay the night?";
        output += partitionedLine(stats, 2);
        output += bodyLine(message);
        String[] options = {"A: Stay the Night", "B: Leave"};
        output += optionsLine(options);
        
        System.out.println(output);
        output = "";
        output += innOptions(cost);
        
        return output;
    }
    
    /**
     * Runs if currentState is InnCost; retrieves last cost generated, then gives them the option to leave or stay
     * Attempting to stay with insufficient funds will crash the game, because I'm being lazy right now
     * @return
     * @throws Unidentified 
     */
    private String InnBackGUI() {
        String output = "";
        
        output += divider();
        int cost = innCost;
        String[] stats = {character.getName(), "Gold: " + character.getGold(), "Level: " + character.getLevel(), "Exp: " + character.getExp() + "/" + character.getNextExp()};
        String message = "Hello! Staying at the inn will cost " + escapeChar + "[33m" + cost + escapeChar + "[30m" + " gold. Would you like to stay the night?";
        output += partitionedLine(stats, 2);
        output += bodyLine(message);
        String[] options = {"A: Stay the Night", "B: Leave"};
        output += optionsLine(options);
        
        System.out.println(output);
        output = "";
        output += innOptions(cost);
        
        return output;
    }
    
    private ArrayList<Integer> monsterIDs = new ArrayList<>();
    private Integer monsterID = null;
    /**
     * Runs if currentState is Battle; this states the names of the Character and the Monster, then the current stats of the Character and Monster, and gives options for how to continue
     * @return
     * @throws Unidentified 
     */
    private String BattleGUI() {
        String output = "";
        
        if(monsterID == null || Distributor.getMonster(monsterID).getDefeated()) {
            monsterID = new Goblin().getID();
            monsterIDs.add(monsterID);
        }
        Monster m = Distributor.getMonster(monsterID);
        String[] names = {character.getName(), escapeChar + "[31m" + m.getName() + escapeChar + "[30m"};
        String[] health = {character.getHealth() + "/" + character.getMaxHealth() + " HP", m.getHealth() + "/" + m.getMaxHealth() + " HP"};
        String[] strength = {"Str: " + character.getStrength(), "Str: " + m.getStrength()};
        String[] defense = {"Def: " + character.getDefense(), "Def: " + m.getDefense()};
        String[] speed = {"Spd: " + character.getSpeed(), "Spd: " + m.getSpeed()};
        String[] goldExp = {"Gold: " + character.getGold(), "Lvl: " + character.getLevel(), character.getExp() + "/" + character.getNextExp() + " EXP"};
        String message = "Select an option";
        String[] options = {"A: Attack", "B: Defend", "C: Flee"};
        
        output += divider();
        output += partitionedLine(names);
        output += partitionedLine(health);
        output += partitionedLine(strength);
        output += partitionedLine(defense);
        output += partitionedLine(speed);
        output += partitionedLine(goldExp, 3);
        output += bodyLine(message);
        output += optionsLine(options);
        
        System.out.println(output);
        changeState(selectBattleOption());
        
        output = "\n";
        
        return output;
    }
    
    /**
     * Runs if currentState is Attacking; this calculates the damage the Character does to the Monster, then vice versa if it's still alive, then checks for passive regeneration; Damage numbers are in red, regen is in green.
     * @return
     * @throws Unidentified 
     */
    private String AttackingGUI() {
        String output = "";
        
        Monster m = Distributor.getMonster(monsterID);
        output += divider();
        
        int attackDamage = (character.attack() - m.defend());
        if(attackDamage < 0) {
            attackDamage = 0;
        }
        String attack = character.getName() + " attacks " + escapeChar + "[31m" + m.getName() + escapeChar + "[30m" + " for " + escapeChar + "[31m" + attackDamage + escapeChar + "[30m" + " damage.";
        m.changeHealth(m.getHealth() - attackDamage);
        output += bodyLine(attack);
        if(m.getHealth() > 0) {
            int defenseDamage = (m.attack() - character.defend());
            if(defenseDamage < 0) {
                defenseDamage = 0;
            }
            String defense = escapeChar + "[31m" + m.getName() + escapeChar + "[30m" + " attacks " + character.getName() + " for " + escapeChar + "[31m" + defenseDamage + escapeChar + "[30m" + " damage.";
            character.changeHealth(character.getHealth() - defenseDamage);
            output += bodyLine(defense);
        }
        if(character.getHealth() > 0 && character.getHealth() < character.getMaxHealth()) {
            if(rand.nextInt(regenChance) == 0) {
                int regen = rand.nextInt(character.getDefense()) + 1;
                int temp = (character.getHealth() + regen) - character.getMaxHealth();
                if(temp > 0){
                    regen -= temp;
                }
                character.changeHealth(character.getHealth() + regen);
                String regeneration = character.getName() + " passively regenerates " + escapeChar + "[32m" + regen + escapeChar + "[30m" + " HP.";
                output += bodyLine(regeneration);
            }
        }
        
        output += divider();
        if(m.getHealth() <= 0) {
            changeState(State.BattleWon);
        }
        else if(character.getHealth() <= 0) {
            changeState(State.Dead);
        }
        else {
            changeState(State.Battle);
        }
        
        return output;
    }
    
    /**
     * Runs if currentState is Defending; this calculates the damage done to the character, then checks for passive regeneration; multiplies defense by defenseMultiplier, and regen chance and regen amount by regenMultiplier
     * @return
     * @throws Unidentified 
     */
    private String DefendingGUI() {
        String output = "";
        
        int defenseMultiplier = 2;
        int regenMultiplier = 2;
        Monster m = Distributor.getMonster(monsterID);
        
        output += divider();
        String message = character.getName() + " defends.";
        output += bodyLine(message);
        int defenseDamage = (m.attack() - (defenseMultiplier * character.defend()));
        if(defenseDamage < 0) {
            defenseDamage = 0;
        }
        String defense = escapeChar + "[31m" + m.getName() + escapeChar + "[30m" + " attacks " + character.getName() + " for " + escapeChar + "[31m" + defenseDamage + escapeChar + "[30m" + " damage.";
        character.changeHealth(character.getHealth() - defenseDamage);
        output += bodyLine(defense);
        if(character.getHealth() > 0 && character.getHealth() < character.getMaxHealth()) {
            if(rand.nextInt(regenChance / regenMultiplier) == 0) {
                int regen = rand.nextInt(character.getDefense()) + 1;
                regen *= regenMultiplier;
                int temp = (character.getHealth() + regen) - character.getMaxHealth();
                if(temp > 0){
                    regen -= temp;
                }
                character.changeHealth(character.getHealth() + regen);
                String regeneration = character.getName() + " passively regenerates " + escapeChar + "[32m" + regen + escapeChar + "[30m" + " HP.";
                output += bodyLine(regeneration);
            }
        }
        output += divider();
        
        if(character.getHealth() > 0) {
            changeState(State.Battle);
        }
        else {
            changeState(State.Dead);
        }
        
        return output;
    }
    
    /**
     * Runs if currentState is Fleeing; this compares the speed stats of the Character and the Monster to determine a success; if the attempt fails the monster attacks and it checks for regen
     * @return
     * @throws Unidentified 
     */
    private String FleeingGUI() {
        String output = "";
        
        Monster m = Distributor.getMonster(monsterID);
        double chance = (double)character.getSpeed()/(double)(character.getSpeed() + m.getSpeed());
        boolean success = rand.nextDouble() <= chance;
        
        System.out.println(chance + " " + success);
        
        output += divider();
        String message = character.getName() + " tries to flee.";
        output += bodyLine(message);
        if(success) {
            output += bodyLine("Success!");
            changeState(State.Fled);
            m.setDefeated();
        }
        else {
            output += bodyLine("Attempt Failed");
            int defenseDamage = (m.attack() - character.defend());
            if(defenseDamage < 0) {
                defenseDamage = 0;
            }
            String defense = escapeChar + "[31m" + m.getName() + escapeChar + "[30m" + " attacks " + character.getName() + " for " + escapeChar + "[31m" + defenseDamage + escapeChar + "[30m" + " damage.";
            character.changeHealth(character.getHealth() - defenseDamage);
            output += bodyLine(defense);
            if(character.getHealth() > 0 && character.getHealth() < character.getMaxHealth()) {
                if(rand.nextInt(regenChance) == 0) {
                    int regen = rand.nextInt(character.getDefense()) + 1;
                    int temp = (character.getHealth() + regen) - character.getMaxHealth();
                    if(temp > 0){
                        regen -= temp;
                    }
                    character.changeHealth(character.getHealth() + regen);
                    String regeneration = character.getName() + " passively regenerates " + escapeChar + "[32m" + regen + escapeChar + "[30m" + " HP.";
                    output += bodyLine(regeneration);
                }
            }
            if(character.getHealth() > 0) {
                changeState(State.Battle);
            }
            else {
                changeState(State.Dead);
            }
        }
        output += divider();
        
        return output;
    }
    
    /**
     * Runs if currentState is BattleWon; this calculates how much gold and Exp were earned by defeating the monster, applies them to the character, then checks for a level up
     * @return
     * @throws Unidentified 
     */
    private String BattleWonGUI() {
        String output = "";
        
        int earnedBuffer = 5;
        int updateBuffer = 3;
        
        int level = character.getLevel();
        
        output += divider();
        Monster m = Distributor.getMonster(monsterID);
        String message = m.getName() + " is defeated!";
        output += bodyLine(message);
        String gold = m.deathGold() + " gold earned";
        character.changeGold(character.getGold() + m.deathGold());
        String exp = m.deathExp() + " Exp earned";
        character.changeExp(character.getExp() + m.deathExp());
        String[] strings = {gold, exp};
        output += partitionedLine(strings, earnedBuffer);
        String[] goldExp = {"Gold: " + character.getGold(), "Lvl: " + character.getLevel(), character.getExp() + "/" + character.getNextExp() + " EXP"};
        output += partitionedLine(goldExp, updateBuffer);
        m.setDefeated();
        output += divider();
        
        if(character.getLevel() > level) {
            changeState(State.LevelUp);
        }
        else {
            changeState(State.Passive);
        }
        
        return output;
    }
    
    /**
     * Runs if currentState is Fled; this announces that they successfully ran away, then defaults to Passive state
     * @return 
     */
    private String FledGUI() {
        String output = "";
        
        output += divider();
        String message = "You successfully ran away!";
        output += bodyLine(message);
        output += divider();
        changeState(State.Passive);
        
        return output;
    }
    
    
    
    /**
     * Runs if currentState is LevelUp; this announces that they Leveled Up and prompts them to upgrade stats
     * @return
     * @throws Unidentified 
     */
    private String LevelUpGUI() {
        String output = "";
        
        for(int i = 0; i < character.getPointsPerLevel(); ++i) {
            output += divider();
            String levelup = escapeChar + "[32m" + "Level Up!" + escapeChar + "[30m";
            String[] stats = {"Str: " + character.getStrength(), "Def: " + character.getDefense(), "Spd: " + character.getSpeed(), "Max Health: " + character.getMaxHealth()};
            String[] options = {"A: +1 Str", "B: +1 Def", "C: +1 Spd", "D: +5 Max Health"};
            String points = escapeChar + "[34m" + (character.getPointsPerLevel() - i) + escapeChar + "[30m" + " points remaining";
            output += bodyLine(levelup);
            output += partitionedLine(stats);
            output += bodyLine(points);
            output += optionsLine(options);
            System.out.println(output);
            statLevelUp();
            output = "";
        }
        character.changeHealth(character.getMaxHealth());
        changeState(State.Passive);
        
        return output;
    }
    
    /**
     * Runs if currentState is Dead; this reads off the final stats of the character, then defaults to GameOver state
     * @return 
     */
    private String DeadGUI() {
        String output = "";
        
        output += divider();
        String message = escapeChar + "[31m" + "You Died" + escapeChar + "[30m";
        output += bodyLine(message);
        String[] finalStatsBasic = {"Str: " + character.getStrength(), "Def: " + character.getDefense(), "Spd: " + character.getSpeed()};
        String finalHealth = character.getHealth() + "/" + character.getMaxHealth() + " HP";
        String[] finalStatsSpecial = {"Gold: " + character.getGold(), "Lvl: " + character.getLevel(), character.getExp() + "/" + character.getNextExp() + " Exp"};
        output += partitionedLine(finalStatsBasic);
        output += bodyLine(finalHealth);
        output += partitionedLine(finalStatsSpecial);
        output += divider();
        changeState(State.GameOver);
        
        return output;
    }
    
    /**
     * Runs if currentState is GameOver; this merely says Game Over
     * @return 
     */
    private String GameOverGUI() {
        String output = "";
        
        output += divider();
        output += bodyLine("Game");
        output += bodyLine("Over");
        output += divider();
        
        return output;
    }
    
    
    
    // Construction Elements
    
    /**
     * Prints a divider line for GUI
     * @return 
     */
    private String divider() {
        String output = "";
        
        for(int i = 0; i < width; ++i) {
            output += horBar;
        }
        
        output += '\n';
        return output;
    }
    
    /**
     * Prints the body area given a String to use
     * @param message Body Text
     * @return 
     */
    private String bodyLine(String message) {
        String output = "";
        
        int length = message.length();
        for(char c : message.toCharArray()) {
            if (c == escapeChar) {
                length -= escapeSize;
            }
        }
        int whiteSpace = (width - length - (2*capSize))/2;
        
        for(int i = 0; i < capSize; ++i) {
            output += verBar;
        } 
        for(int i = 0; i < whiteSpace; ++i) {
            output += space;
        }
        output += message;
        for(int i = 0; i < whiteSpace; ++i) {
            output += space;
        }
        if(length % 2 != width % 2) {
            output += space;
        }
        for(int i = 0; i < capSize; ++i) {
            output += verBar;
        }
        
        output += '\n';
        return output;
    }    
    
    /**
     * Creates a line with multiple options listed in it
     * @param strings List of Options
     * @return 
     */
    private String optionsLine(String[] strings) {
        String output = "";
        String[] newStrings = new String[strings.length];
        for(int s = 0; s < strings.length; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += openPart;
            }
            temp += space;
            temp += strings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += closePart;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
    /**
     * Creates a line with multiple parts, not setup as options
     * @param strings Parts
     * @return 
     */
    private String partitionedLine(String[] strings) {
        String output = "";
        String[] newStrings = new String[strings.length];
        
        for(int s = 0; s < strings.length; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            temp += space;
            temp += strings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
    /**
     * Creates a line with multiple parts, not setup as options, and inset from the edges with buffers
     * @param strings Parts
     * @param buffers Size of buffers on ends
     * @return 
     */
    private String partitionedLine(String[] strings, int buffers) {
        String output = "";
        
        String openBuffer = "";
        String closeBuffer = "";
        for(int i = 0; i < capSize; ++i) {
            openBuffer += verBar;
        }
        for(int i = 0; i < buffers; ++i) {
            openBuffer += space;
            closeBuffer += space;
        }
        for(int i = 0; i < capSize; ++i) {
            closeBuffer += verBar;
        }
        
        String[] newStrings = new String[strings.length + 2];
        newStrings[0] = openBuffer;
        for(int i = 1; i <= strings.length; ++i) {
            newStrings[i] = strings[i - 1];
        }
        newStrings[newStrings.length - 1] = closeBuffer;
        
        for(int s = 1; s < newStrings.length - 1; ++s) {
            String temp = "";
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            temp += space;
            temp += newStrings[s];
            temp += space;
            for(int i = 0; i < capSize; ++i) {
                temp += verBar;
            }
            newStrings[s] = temp;
        }
        int length = 0;
        for(String s : newStrings) {
            length += s.length();
            for(char c : s.toCharArray()) {
                if (c == escapeChar) {
                    length -= escapeSize;
                }
            }
        }
        int whiteSpace = (width - length) / (newStrings.length - 1);
        
        for(int i = 0; i < newStrings.length; ++i) {
            output += newStrings[i];
            if(i != newStrings.length - 1) {
                for(int j = 0; j < whiteSpace; ++j) {
                    output += space;
                }
            }
            if(i == newStrings.length / 2) {
                for(int j = 0; j < (width - length) % (newStrings.length - 1); ++j) {
                    output += space;
                }
            }
        }
        
        
        output += '\n';
        return output;
    }
    
}
